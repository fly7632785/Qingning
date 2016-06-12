package com.jafir.qingning.app.activity.rent;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BaseActivity;
import com.jafir.qingning.app.activity.OrderActivity;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.BikeRecyclerAdapter;
import com.jafir.qingning.model.bean.Bike;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/12.
 */
public class ChooseBikeActivity extends BaseActivity {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.choose_bike_go_order, click = true)
    private View mGoOrder;
    @BindView(id = R.id.choose_bike_count)
    private TextView mCount;
    @BindView(id = R.id.choose_bike_recycler)
    private RecyclerView mRecyclerView;

    private BikeRecyclerAdapter mAdapter;
    private ArrayList<Bike> list = new ArrayList();

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_choose_bike);
    }


    @Override
    public void initData() {
        super.initData();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseBikeActivity.this.finish();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(aty);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);


        for (int i = 0; i < 10; i++) {
            Bike bike = new Bike();
            bike.setName("死飞");
            bike.setImg(imgs[i%imgs.length]);
            bike.setKind("自由变速");
            bike.setPrice("￥5/h");
            bike.setSpareCount(20+i);
            list.add(bike);
        }
        mAdapter = new BikeRecyclerAdapter();
        mAdapter.setData(list);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        mAdapter.setListener(new BikeRecyclerAdapter.TotalCountListener() {
            @Override
            public void onTotalCountChanged(int totalCount) {
                mCount.setText("总计："+totalCount+"辆");
            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.choose_bike_go_order:
                goToOder();

                break;
        }
    }


    private void goToOder(){
        ArrayList<Bike> data = new ArrayList<>();

        for (Bike bike:list) {
            if(bike.getOrderCount()>0){
                data.add(bike);
            }
        }

        Intent intent = new Intent(aty,OrderActivity.class);
        intent.putExtra("orderdata",data);
        startActivity(intent);
    }

    public  static  String[] imgs = new String[]{
            "http://img3.imgtn.bdimg.com/it/u=2484382233,4084850626&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2483737534,1930849873&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1032061705,2918501124&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3339233174,4089304849&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1324935102,1789040288&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1287385319,2164096890&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4183000777,3156710299&fm=21&gp=0.jpg"
    };
}
