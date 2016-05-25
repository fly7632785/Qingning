package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.EventRecyclerAdapter;
import com.jafir.qingning.model.bean.Event;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.DensityUtils;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/25.
 */
public class MyEventActivity extends BaseActivity {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.view_pager)
    private ViewPager mViewpager;
    @BindView(id = R.id.tab)
    private TabLayout mTab;
    private String[] titles = new String[]{"我发布的", "我参与的"};
    private ArrayList<View> viewList = new ArrayList<>();
    private RecyclerView recyclerPublish;
    private RecyclerView recyclerJoin;
    private EventRecyclerAdapter mAdapterPublish;
    private EventRecyclerAdapter mAdapterJoin;
    private ArrayList<Event> listPublish;
    private ArrayList<Event> listJoin;


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_my_event);
    }



    @Override
    public void initData() {
        super.initData();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aty.finish();
            }
        });

        initViewList();
        initViewpager();

        mTab.setupWithViewPager(mViewpager);
    }

    private void initViewList() {
        recyclerPublish = new RecyclerView(aty);
        recyclerJoin = new RecyclerView(aty);
        recyclerJoin.setPadding(0,DensityUtils.dip2px(aty,5),0,0);
        recyclerPublish.setPadding(0,DensityUtils.dip2px(aty,5),0,0);
        recyclerJoin.setBackgroundResource(R.color.windowback);
        recyclerPublish.setBackgroundResource(R.color.windowback);


        //设置 管理器
        LinearLayoutManager manager1 = new LinearLayoutManager(aty);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        //设置 管理器
        LinearLayoutManager manager2 = new LinearLayoutManager(aty);
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerPublish.setLayoutManager(manager1);
        recyclerJoin.setLayoutManager(manager2);
        mAdapterPublish = new EventRecyclerAdapter();
        mAdapterJoin = new EventRecyclerAdapter();

        //设置不含轮播
        mAdapterJoin.setHasHeader(false);
        mAdapterPublish.setHasHeader(false);


        mAdapterPublish.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(aty, ChehangDetailActivity.class));
            }
        });
        mAdapterJoin.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(aty, ChehangDetailActivity.class));
            }
        });
        listPublish = new ArrayList<>();
        listJoin = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setName("云墨山水--青城山");
            event.setAuthor("jafir");
            event.setPeople("有15人参与");
            event.setBussiness("4家商家竞标");
            event.setTime("2015.4.4");
            event.setTimes("123次浏览");
            event.setSpareTime("2天");
            event.setImg(imgs[i%imgs.length]);
            event.setPortrait("http://img3.imgtn.bdimg.com/it/u=2103190071,4127559232&fm=23&gp=0.jpg");
            listPublish.add(event);
        }
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setName("云墨山水--青城山");
            event.setAuthor("jafir");
            event.setPeople("有15人参与");
            event.setBussiness("4家商家竞标");
            event.setTime("2015.4.4");
            event.setTimes("123次浏览");
            event.setSpareTime("2天");
            event.setImg(imgs[i%imgs.length]);
            event.setPortrait("http://img3.imgtn.bdimg.com/it/u=2103190071,4127559232&fm=23&gp=0.jpg");
            listJoin.add(event);
        }
        mAdapterPublish.setData(listPublish);
        mAdapterJoin.setData(listJoin);
        recyclerJoin.setAdapter(mAdapterJoin);
        recyclerPublish.setAdapter(mAdapterPublish);






        viewList.add(recyclerPublish);
        viewList.add(recyclerJoin);

    }


    private void initViewpager() {
        mViewpager.setAdapter(new PagerAdapter() {
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

                return titles[position];
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

        });
    }

    public static  String[] imgs = new String[]{
            "http://img1.imgtn.bdimg.com/it/u=1089582262,166446285&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3603943369,1952417318&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1390800033,3298177266&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1584429376,3184583112&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3999722898,2097813441&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1599679285,12778853&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1003865389,4203869868&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3910218416,3261859686&fm=21&gp=0.jpg"

    };
}
