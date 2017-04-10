package com.jafir.qingning.app.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by jafir on 2017/4/6.
 */

public class PhoneUtils {

    public static void call(String phone, Activity context){

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("拨打电话")
                .setMessage("您要拨打" + phone + "吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ContextCompat.checkSelfPermission(context,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            // 没有获得授权，申请授权
                            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                                    Manifest.permission.CALL_PHONE)) {
                                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                                // 弹窗需要解释为何需要该权限，再次请求授权
                                Toast.makeText(context, "请授权！", Toast.LENGTH_LONG).show();

                                // 帮跳转到该应用的设置界面，让用户手动授权
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                intent.setData(uri);
                                context.startActivity(intent);
                            }else{
                                // 不需要解释为何需要该权限，直接请求授权
                                ActivityCompat.requestPermissions(context,
                                        new String[]{Manifest.permission.CALL_PHONE},
                                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            }
                        }else {
                            // 已经获得授权，可以打电话
                            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone)));
                        }

                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();

    }
    public static void call(String phone, Context mContext){
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("拨打电话")
                .setMessage("您要拨打" + phone + "吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + phone);
                        intent.setData(data);
                        mContext.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();

    }
}
