package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BezierAcitvity;
import com.jafir.qingning.app.activity.MyEventActivity;
import com.jafir.qingning.app.activity.MyGuideBookActivity;
import com.jafir.qingning.app.util.OssUtil;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.widget.RoundImageView;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by jafir on 16/4/19.
 */
public class PersonFragment extends SupportFragment {


    private static final int TO_SELECT_PHOTO = 1;
    private static final int REQUEST_CODE_CAMERA = 2;
    private static final int REQUEST_CODE_GALLERY = 3;
    private static final int REQUEST_CODE_CROP = 4;
    private static final int REQUEST_CODE_EDIT = 5;
    private static final int REQUEST_CODE_MORE_CAMERA = 6;
    private Activity aty;
    private View layout;
    @BindView(id = R.id.person_my_likes, click = true)
    private LinearLayout mLike;
    @BindView(id = R.id.person_my_guidebook, click = true)
    private LinearLayout mGuidebook;
    @BindView(id = R.id.person_my_event, click = true)
    private LinearLayout mEvent;
    @BindView(id = R.id.person_my_order, click = true)
    private LinearLayout mOrder;
    @BindView(id = R.id.person_setting, click = true)
    private LinearLayout mSetting;
    @BindView(id = R.id.person_avatar, click = true)
    private RoundImageView mAvatar;
    @BindView(id = R.id.person_name)
    private TextView mName;
    @BindView(id = R.id.person_motto)
    private TextView mMotto;
    private String picPath;

    private ProgressDialog mProgress;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (mProgress == null) {
                        mProgress = new ProgressDialog(aty);
                    } else {
                        mProgress.show();
                    }
                    break;
                case 1:
                    mProgress.cancel();
                    break;
            }
        }
    };

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_person, null);
        return layout;
    }

    @Override
    protected void initData() {
        super.initData();

        mAvatar.setBorderThickness(0);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.person_my_event:
                startActivity(new Intent(aty, MyEventActivity.class));
                break;
            case R.id.person_my_likes:
                break;
            case R.id.person_my_order:
                break;
            case R.id.person_my_guidebook:
                startActivity(new Intent(aty, MyGuideBookActivity.class));
                break;
            case R.id.person_setting:
                startActivity(new Intent(aty, BezierAcitvity.class));
                break;
            case R.id.person_avatar:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        String path = null;
                        for (PhotoInfo info : resultList
                                ) {
                            path = info.getPhotoPath();
                            KJLoger.debug(path);
                            Bitmap bm = BitmapFactory.decodeFile(path);
                            if (bm != null && mAvatar != null) {
                                KJLoger.debug("youavatar");
                                mAvatar.setImageBitmap(bm);
                                OssUtil.upload(aty.getApplicationContext(),path,handler);
                            }

                        }


                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
//带配置
//                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
//                GalleryFinal.openEdit(REQUEST_CODE_EDIT, "/storage/emulated/0/GalleryFinal/edittemp/crop_crop.jpeg", new GalleryFinal.OnHanlderResultCallback() {
//                    @Override
//                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        for (PhotoInfo info : resultList
//                                ) {
//                            String path1 = info.getPhotoPath();
//                            KJLoger.debug(path1);
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onHanlderFailure(int requestCode, String errorMsg) {
//
//                    }
//                });

//带配置
//                FunctionConfig config = new FunctionConfig.Builder()
//                        .setMutiSelectMaxSize(8)
//                        .build();
//                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, config, new GalleryFinal.OnHanlderResultCallback() {
//                    @Override
//                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//                        for (PhotoInfo info : resultList
//                                ) {
//                            String path1 = info.getPhotoPath();
//                            KJLoger.debug(path1);
//                        }
//                    }
//
//                    @Override
//                    public void onHanlderFailure(int requestCode, String errorMsg) {
//
//                    }
//                });


                break;
        }


    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK && requestCode == TO_SELECT_PHOTO) {
//            picPath = data.getStringExtra(SelectActivity.KEY_RETURN_PHOTO_PATH);
//            KJLoger.debug("成功返回：picpath:" + picPath);
//            Bitmap bm = BitmapFactory.decodeFile(picPath);
//            if (bm != null && mAvatar != null) {
//                KJLoger.debug("youavatar");
//                mAvatar.setImageBitmap(bm);
//                upload();
//            }
//        }
//    }



}
