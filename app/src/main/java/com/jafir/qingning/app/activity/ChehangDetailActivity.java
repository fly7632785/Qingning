package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jafir.qingning.R;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/10.
 */
public class ChehangDetailActivity extends BaseActivity implements NestedScrollView.OnScrollChangeListener {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.chehang_detail_viewpager)
    private ViewPager mViewpager;
    private ArrayList<View> viewList;
    private ArrayList<String> titleList;
//    @BindView(id = R.id.chehang_detail_xuanche,click = true)
//    private TextView mGoChooseBike;
    @BindView(id = R.id.fab,click = true)
    private FloatingActionButton mFab;
    @BindView(id = R.id.chehang_detail_tab)
    private TabLayout mTabLayout;
    @BindView(id = R.id.chehang_detail_cover)
    private ImageView mImg;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_chehang_detail);
    }


    @Override
    public void initData() {
        super.initData();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChehangDetailActivity.this.finish();
            }
        });

        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.chehang_detail_pager1, null);
        View view2 = lf.inflate(R.layout.chehang_detail_pager2, null);
        View view3 = lf.inflate(R.layout.chehang_detail_pager3, null);

        NestedScrollView n1 = (NestedScrollView) view1.findViewById(R.id.nestedScroll1);
        NestedScrollView n2 = (NestedScrollView) view2.findViewById(R.id.nestedScroll2);
        NestedScrollView n3 = (NestedScrollView) view3.findViewById(R.id.nestedScroll3);
        n1.setOnScrollChangeListener(this);
        n2.setOnScrollChangeListener(this);
        n3.setOnScrollChangeListener(this);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("车行详情");
        titleList.add("优惠信息");
        titleList.add("用户评论");

        mViewpager.setAdapter(pagerAdapter);
        initTabLayout();



        KJLoger.debug("width:"+aty.getResources().getDisplayMetrics().widthPixels+"height:"+aty.getResources().getDisplayMetrics().heightPixels+"density:"+aty.getResources().getDisplayMetrics().density+"densityDpi"+aty.getResources().getDisplayMetrics().densityDpi);



    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.getTabAt(0).setCustomView(R.layout.item_chehang_detail_1_tablayout);
        mTabLayout.getTabAt(1).setCustomView(R.layout.item_chehang_detail_2_tablayout);
        mTabLayout.getTabAt(2).setCustomView(R.layout.item_chehang_detail_3_tablayout);
        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.fab:
                startActivity(new Intent(aty,ChooseBikeActivity.class));
                break;

        }
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public int getCount() {

            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));

        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return titleList.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

    };


    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        KJLoger.debug(""+scrollX+"\n"+scrollY+"\n"+oldScrollX+"\n"+oldScrollY);
    }
}
