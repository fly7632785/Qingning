package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.MylikeRecyclerAdapter;
import com.jafir.qingning.model.bean.GuideBook;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/25.
 */
public class MyLikeActivity extends BaseActivity {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mRecyclerView;
    private MylikeRecyclerAdapter mAdapter;
    private ArrayList<GuideBook> list = new ArrayList<>();


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_my_like_book);
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
        initRecycler();

    }

    private void initRecycler() {

        //设置 管理器
        GridLayoutManager manager = new GridLayoutManager(aty,2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MylikeRecyclerAdapter();


        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(aty, GuideBookDetailActivity.class));
            }
        });
        for (int i = 0; i < 10; i++) {
            GuideBook guideBook = new GuideBook();
            guideBook.setTitle("美食是一道风景。挑战纽约经典面包亲手做一个糖霜包");
            guideBook.setTime("2015.4.4");
            guideBook.setImgUrl(imgs[i % imgs.length]);
            guideBook.setAvatar("http://img3.imgtn.bdimg.com/it/u=2103190071,4127559232&fm=23&gp=0.jpg");
            guideBook.setAddress("目的地：都江堰朝阳路12号");
            guideBook.setLikes("有5人喜欢");
            list.add(guideBook);
        }
        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);

    }


    public static String[] imgs = new String[]{
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
