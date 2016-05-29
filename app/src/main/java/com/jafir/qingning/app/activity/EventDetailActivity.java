package com.jafir.qingning.app.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.CommentRecyclerAdapter;
import com.jafir.qingning.model.bean.Comment;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/10.
 */
public class EventDetailActivity extends BaseActivity {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(id = R.id.viewpager)
    private ViewPager mViewpager;
    private ArrayList<View> viewList;
    private ArrayList<String> titleList;
    //    @BindView(id = R.id.chehang_detail_xuanche,click = true)
//    private TextView mGoChooseBike;
    @BindView(id = R.id.fab, click = true)
    private FloatingActionButton mFab;
    @BindView(id = R.id.tab)
    private TabLayout mTabLayout;
    @BindView(id = R.id.cover)
    private ImageView mImg;
    private RecyclerView mCommentRecycler;
    private CommentRecyclerAdapter mAdapter;
    private ArrayList<Comment> comments = new ArrayList<>();

    private String[] imgUrl = new String[]{
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D200/sign=0afb9ebc4c36acaf46e091fc4cd88d03/bd3eb13533fa828b670a4066fa1f4134970a5a0e.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2147665307,4031352505&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2769901330,4132322556&fm=23&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=693362385,3280695814&fm=23&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3047710011,1274531363&fm=23&gp=0.jpg"
    };

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_event_detail);
    }


    @Override
    public void initData() {
        super.initData();


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("云雾缭绕青城山");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventDetailActivity.this.finish();
            }
        });

        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.event_detail_pager1, null);
        View view2 = lf.inflate(R.layout.event_detail_pager2, null);
        View view3 = lf.inflate(R.layout.event_detail_pager3, null);

        NestedScrollView n1 = (NestedScrollView) view1.findViewById(R.id.nestedScroll1);
        NestedScrollView n2 = (NestedScrollView) view2.findViewById(R.id.nestedScroll2);

        mCommentRecycler = (RecyclerView) view3.findViewById(R.id.recyclerview);
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(aty, LinearLayoutManager.VERTICAL, false));
        mAdapter = new CommentRecyclerAdapter();
        //模拟数据
        for (int i = 0; i < 20; i++) {
            Comment comment = new Comment();
            comment.setName("我的名字");
            comment.setContent("评论评论评论评论评论评论评论评论评论评论评论评论");
            comment.setTime("2014-3-3 11:32");
            comment.setImgUrl(imgUrl[i % imgUrl.length]);
            comments.add(comment);
        }
        mAdapter.setData(comments);
        mCommentRecycler.setAdapter(mAdapter);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("活动须知");
        titleList.add("活动详情");
        titleList.add("商家竞标");
        mViewpager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewpager);

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.fab:
//                打电话联系
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


}




