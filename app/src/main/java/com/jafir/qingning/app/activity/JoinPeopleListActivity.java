package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.JoinPeopleRecyclerAdapter;
import com.jafir.qingning.model.bean.User;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/25.
 */
public class JoinPeopleListActivity extends BaseActivity {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mRecyclerView;
    private JoinPeopleRecyclerAdapter mAdapter;
    private ArrayList<User> list = new ArrayList<>();


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_join_people_book);
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
        mAdapter = new JoinPeopleRecyclerAdapter();


        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(aty, GuideBookDetailActivity.class));
            }
        });
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNickName("小明");
            user.setPhone("13982004324");
            user.setHeadImgUrl(imgs[i%imgs.length]);
            list.add(user);
        }
        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);

    }


    public static String[] imgs = new String[]{
            "http://img3.duitang.com/uploads/item/201604/24/20160424132044_ZzhuX.jpeg",
            "http://v1.qzone.cc/avatar/201408/03/23/44/53de58e5da74c247.jpg%21200x200.jpg",
            "http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f0c5c08030d3d539c16807c70fb7c566/8ad4b31c8701a18bbef9f231982f07082838feba.jpg",
            "http://img1.touxiang.cn/uploads/20140310/10-055807_930.jpg",
            "http://www.qqxoo.com/uploads/allimg/170408/2133192L2-1.jpg",
            "http://m.vstou.com/img/201512/lbs4.jpg"

    };
}
