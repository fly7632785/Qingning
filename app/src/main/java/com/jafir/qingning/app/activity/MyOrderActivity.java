package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.MyOrderRecyclerAdapter;
import com.jafir.qingning.model.bean.Order;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/25.
 */
public class MyOrderActivity extends BaseActivity {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mRecyclerView;
    private MyOrderRecyclerAdapter mAdapter;
    private ArrayList<Order> list = new ArrayList<>();


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_my_order_book);
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
        LinearLayoutManager manager = new LinearLayoutManager(aty);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MyOrderRecyclerAdapter();


        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(aty, GuideBookDetailActivity.class));
            }
        });
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setTitle("自由租车行");
            order.setTime("2017-4-4");
            order.setStatus(i%3==0?"已完成":"进行中");
            order.setAvatar(imgs[i % imgs.length]);
            order.setInfo("死飞2辆 山地自行车5辆 ");
            list.add(order);
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
