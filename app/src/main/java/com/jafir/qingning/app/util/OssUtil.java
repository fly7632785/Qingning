package com.jafir.qingning.app.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.KJLoger;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jafir on 16/5/30.
 */
public class OssUtil {


    private static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    private static String baseurl = "http://qing-ning.oss-cn-shenzhen.aliyuncs.com/";
    /**
     * 返回key
     * @param path
     * @return
     */
    public static String upload(Context context,String path, final Handler handler) {
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("r1dGWhwgjAvUccho", "MVXr7Q1MvhCRGin2dtwrIL3tAjiSC4");

        OSS oss = new OSSClient(context, endpoint, credentialProvider);

        //命名用时间日期来命名
//        KJLoger.debug("date:" + new Date());
        String date = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
        KJLoger.debug("formatdata:" + date);

        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest("qing-ning", date, path);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                handler.sendEmptyMessage(0);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                ViewInject.toast("网络异常");
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });

        return baseurl+date;

    }

    public static void down(Context context,String key,Handler handler) {

// 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("r1dGWhwgjAvUccho", "MVXr7Q1MvhCRGin2dtwrIL3tAjiSC4");

        OSS oss = new OSSClient(context, endpoint, credentialProvider);


        // 构造下载文件请求
        GetObjectRequest get = new GetObjectRequest("qing-ning", key);

        OSSAsyncTask task = oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                // 请求成功
                Log.d("Content-Length", "" + result.getContentLength());

                InputStream inputStream = result.getObjectContent();

                byte[] buffer = new byte[2048];
                int len;

                try {
                    while ((len = inputStream.read(buffer)) != -1) {
                        // 处理下载的数据

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });

// task.cancel(); // 可以取消任务

// task.waitUntilFinished(); // 如果需要等待任务完成
    }


}
