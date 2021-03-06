package com.jafir.qingning.app.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jafir.qingning.R;
import com.jafir.qingning.app.AppConfig;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import java.io.File;
import java.io.IOException;

/**
 * Created by jafir on 15/11/8.
 */
public class SelectActivity extends KJActivity {

    /**
     * 关于拍照和照片的选取
     * 拍照：拍摄照片之后会在本地生成一个图片，有一个url,（如果不进行压缩则为原图，
     * 可以设置参数，进行压缩生成缩略图 ），
     * 用原图然后进行裁剪，生成一个新的裁剪后存放的路径
     * <p/>
     * 从图库选取：从本地选取一张图片 有一个URL，用URL的图片进行裁剪，裁剪后存放到新的路径
     * <p/>
     * 为了确保每次的路径是不一致的，所以要有一个路径参数的传入 getIntent来获取
     * <p/>
     * 拍照和选取 后的裁剪都生成一张图片路径（图片的名字不同）
     * 如果是 头像的选取 只有一个路径 图片名字为：avatar.jpeg
     * 如果是发布菜谱的 5个步骤图 图片名字为：step1,step2,step3,step4,step5
     * <p/>
     * 使用模板
     * <p/>
     * Intent intent = new Intent(aty,SelectActivity.class);
     * intent.putExtra(SelectActivity.KEY_PHOTO_PATH,"avatar.jpeg");
     * intent.putExtra(SelectActivity.KEY_X_RATE,1);//x比例
     * intent.putExtra(SelectActivity.KEY_Y_RATE,1);//y比例
     * intent.putExtra(SelectActivity.KEY_WIDTH,100);//宽
     * intent.putExtra(SelectActivity.KEY_HEIGHT,100);//高
     * startActivityForResult(intent,TO_SELECT_PHOTO);
     * <p/>
     * 然后 根据返回结果获取 uri
     * <p/>
     * if (resultCode == Activity.RESULT_OK && requestCode == TO_SELECT_PHOTO) {
     * picPath = data.getStringExtra(SelectActivity.KEY_RETURN_PHOTO_PATH);
     * Bitmap bm = BitmapFactory.decodeFile(picPath);
     * mPortrait.setImageBitmap(bm);
     * }
     */

    //保存图片的本地路径
    public static final String IMG_PATH = Environment.getExternalStorageDirectory() + AppConfig.saveFolder + "/imgs/";
    public static final String IMG_AVATAR_PATH = Environment.getExternalStorageDirectory()  + "/avatars/";
    //图片的名称 是外面传入的参数     到时候保存的路径则为 IMG_PATH+fileName
    private String fileName;
    //文件
    private File filePhoto;
    private String path;

    private File originPhoto;

    /**
     * 从Intent获取图片路径的KEY
     */
    public static final String KEY_PHOTO_PATH = "photo_path";
    /**
     * 从Intent获取图片名称
     */
    public static final String KEY_PHOTO_NAME = "photo_name";
    /**
     * 传回Intent图片路径的KEY
     */
    public static final String KEY_RETURN_PHOTO_PATH = "return_photo_path";

    //版本比较：是否是4.4及以上版本
    final boolean mIsKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /**
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    /**
     * 使用系统自带的裁剪
     */
    public static final int CROP_PHOTO = 3;
    /**
     * 4.4以上使用系统自带的裁剪
     */
    public static final int CROP_PHOTO_KIAKAT = 5;
    /**
     * 4.4以上的从图库获取图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO_KITKAT = 4;


    public static final String KEY_WIDTH = "imgwidth";
    public static final String KEY_HEIGHT = "imgheight";
    public static final String KEY_X_RATE = "imgXrate";
    public static final String KEY_Y_RATE = "imgYrate";

    private int xRate;
    private int yRate;
    private int width;
    private int height;


    /**
     * 开启相机
     */
    @BindView(id = R.id.btn_pick_photo, click = true)
    private Button btn_take_photo;
    /**
     * 开启图册
     */
    @BindView(id = R.id.btn_take_photo, click = true)
    private Button btn_pick_photo;
    /**
     * 取消
     */
    @BindView(id = R.id.btn_cancel, click = true)
    private Button btn_cancel;


    private Intent lastIntent;


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_select);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void initData() {
        super.initData();
        initFile();
    }


    private void initFile() {
        lastIntent = getIntent();
        xRate = lastIntent.getIntExtra(KEY_X_RATE, 1);
        yRate = lastIntent.getIntExtra(KEY_Y_RATE, 1);
        width = lastIntent.getIntExtra(KEY_WIDTH, 80);
        height = lastIntent.getIntExtra(KEY_HEIGHT, 80);

        fileName = lastIntent.getStringExtra(KEY_PHOTO_NAME);
        path = lastIntent.getStringExtra(KEY_PHOTO_PATH);
        //如果没有传则默认路径
        if(path == null|| path.equals("")){
            path = IMG_PATH;
        }

        //创建储存图片的 文件目录
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        //创建 图片文件（文件目录 + 文件名）
        filePhoto = new File(path, fileName);
        originPhoto = new File(path, "origin" + fileName);
        if (!filePhoto.exists()) {
            try {
                filePhoto.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!originPhoto.exists()) {
            try {
                originPhoto.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_take_photo: // 开启相机
                takePhoto();
                break;
            case R.id.btn_pick_photo: // 开启图册
                pickPhoto();
                break;
            case R.id.btn_cancel: // 取消操作
                this.finish();
                break;
            default:
                break;
        }
    }


    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        //打开相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置拍摄好之后 保存图片的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(originPhoto));
        startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
    }

    /***
     * 从相册中取图片
     */
    private void pickPhoto() {

        /**
         * 由于小米手机MIUI高版本  对于4.4那个新的选择器有问题 可能是ROM删除了
         * 所以采用原生的
         */

        //看是否是API大于4.4
//        if (mIsKitKat) {
//            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            //设置选择的内容都是图片
//            KJLoger.debug("4.4 选择图库");
//            intent.setType("image/*");
//            startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO_KITKAT);
//        } else {
            cropNormal();
//        }
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", xRate);
        intent.putExtra("aspectY", yRate);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(filePhoto));
        startActivityForResult(intent, CROP_PHOTO);


    }

    private void cropNormal() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", xRate);
        intent.putExtra("aspectY", yRate);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(filePhoto));
        Uri uri =  Uri.fromFile(filePhoto);
        KJLoger.debug("uri:"+Uri.fromFile(filePhoto));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, CROP_PHOTO);
    }


    /**
     * 4.4后的裁剪图片
     */
    private void cropPhotoKitKat(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        /**
         * 这里很重要    要注意，4.4以后的裁剪 必须要转化一次
         * 从 uri -> filepath ->uri
         * 不然 不能适配所有的手机
         * 如果用原来直接的uri 只能适配一些手机（小米，联想） 有一些手机则不行（魅族，htc..）
         */
        String url=getPath(aty,uri);
        intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");

        KJLoger.debug("原来裁剪的uri:" + uri);
        KJLoger.debug("现在裁剪的uri:" + Uri.fromFile(new File(url)));
//        intent.setDataAndType(uri, "image/jpeg");  原来的方式 太直接  不行
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", xRate);
        intent.putExtra("aspectY", yRate);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(filePhoto));
        KJLoger.debug("uri:"+Uri.fromFile(filePhoto));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CROP_PHOTO);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        doPhoto(requestCode, resultCode, data);
    }

    /**
     * 处理图片
     *
     * @param requestCode
     * @param data
     */
    private void doPhoto(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
//            case SELECT_PIC_BY_PICK_PHOTO:
//                if (resultCode == RESULT_OK) {
//
//                } else {
//                    Toast.makeText(aty, "选择图片失败", Toast.LENGTH_SHORT).show();
//                }
//                break;

            case SELECT_PIC_BY_PICK_PHOTO_KITKAT:
                if (resultCode == RESULT_OK && data != null) {
                    Uri uri = data.getData();
                    //裁剪图片
                    cropPhotoKitKat(uri);
                } else {
                    Toast.makeText(aty, "选择图片失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case SELECT_PIC_BY_TACK_PHOTO:
                if (resultCode == RESULT_OK) {
                    //拍照成功之后就去裁剪
                    cropPhoto(Uri.fromFile(originPhoto));
                } else {
                    Toast.makeText(aty, "拍照失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case CROP_PHOTO:
                KJLoger.debug("此时的result:" + resultCode);
                if (resultCode == RESULT_OK) {
//                    if (data != null) {
//                        Uri u = data.getData();
//                        Bitmap bitmap = data.getParcelableExtra("data");
//                        KJLoger.debug("uri:" + u);
//                        KJLoger.debug("birmap:" + bitmap);
//

//
//                        Bitmap mPhoto = data.getExtras().getParcelable("data");
//                        KJLoger.debug("mPhoto:" + mPhoto);
//                        String filePath = data.getExtras().getString("filePath");
//                        if (!StringUtils.isEmpty(filePath))
//                            KJLoger.debug("filepath:" + filePath);
//                    }


                    Toast.makeText(aty, "裁剪成功", Toast.LENGTH_SHORT).show();
                    KJLoger.debug("path:" + filePhoto.toString());
                    KJLoger.debug("path:" + filePhoto);
                    lastIntent.putExtra(KEY_RETURN_PHOTO_PATH, filePhoto.toString());
                    setResult(RESULT_OK, lastIntent);
                    this.finish();
                } else {
                    Toast.makeText(aty, "裁剪失败", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }


/**********************************分割线 下面是一些获取图片 和 判断方法*******************************************************/


    /**
     * <br>功能简述:4.4及以上获取图片的方法
     * <br>功能详细描述:
     * <br>注意:
     *
     * @param context
     * @param uri
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}
