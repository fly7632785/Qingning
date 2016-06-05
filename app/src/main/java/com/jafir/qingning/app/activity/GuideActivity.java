package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.pageindicator.indicator.FlycoPageIndicaor;
import com.jafir.qingning.R;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by jafir on 16/5/25.
 */
public class GuideActivity extends BaseActivity {


    @BindView(id = R.id.guide_signin, click = true)
    private TextView mSignin;
    @BindView(id = R.id.guide_register, click = true)
    private TextView mRegister;
    @BindView(id = R.id.guide_viewpager)
    private ViewPager mViewpager;
    @BindView(id = R.id.guide_indicator)
    private FlycoPageIndicaor mIndicator;

    private int[] imgIds = new int[]{R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3};

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_guide);
    }


    @Override
    public void initData() {
        super.initData();

        initViewpager();
        mIndicator.setViewPager(mViewpager);

        //检测版本更新



    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.guide_signin:
                login();
                break;

            case R.id.guide_register:
                register();
                break;
        }
    }

    private void register() {
        //创建dialog
        startActivity(new Intent(aty, RegisterActivity.class));

    }

    private void login() {
        //创建dialog
        startActivity(new Intent(aty, LoginActivity.class));

    }

    private void initViewpager() {
        mViewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(container.getContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(container.getContext()).load(imgIds[position % imgIds.length])
                        .into(imageView);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView(container.getChildAt(position));
                container.removeView((View) object);
            }
        });


    }
}
