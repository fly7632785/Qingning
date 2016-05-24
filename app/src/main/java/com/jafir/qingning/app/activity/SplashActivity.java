package com.jafir.qingning.app.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jafir.qingning.R;

import org.kymjs.kjframe.utils.PreferenceHelper;

/**
 * Created by jafir on 10/15/15.
 * 应用程序的开始和入口，有一个动画界面
 */
public class SplashActivity extends BaseActivity {

    public static String TAG = SplashActivity.class.getSimpleName();
    private ImageView image;
    //检测证书是否有效
    //是不是第一次进入APP
    private boolean isFirst;

    @Override
    public void setRootView() {
        image = new ImageView(aty);
        image.setImageResource(R.mipmap.start);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        Animation anim = AnimationUtils.loadAnimation(aty, R.anim.splash_start);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束 跳转
                jumpTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        setContentView(image);
        image.startAnimation(anim);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置成没有标题
        Window window = this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// 让屏幕高亮
        super.onCreate(savedInstanceState);
    }


    /**
     * 1、判断是不是第一次进入，如果是就到引导页
     * 2、判断是否有网，没有网就切换至离线模式，进入APP后所有的数据都是从缓存提取
     * 3、有网的时候，检测证书是否有效 无效则去登录
     * 4、有效则进入主界面
     */
    private void jumpTo() {
        isFirst = PreferenceHelper.readBoolean(aty, TAG, "first_open",
                true);
        //有token验证
        if (isFirst) {
            //引导页
//            UIHelper.showGuide(aty);
            PreferenceHelper.write(aty, TAG, "first_open", false);
        }
        skipActivity(this,MainActivity.class);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
