package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.jafir.qingning.R;
import com.jafir.qingning.app.AppContext;
import com.jafir.qingning.app.adapter.ChehangRecyclerAdapter;
import com.jafir.qingning.model.bean.Chehang;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;

/**
 * Created by jafir on 16/4/19.
 */
public class RentFragment extends SupportFragment {


    private Activity aty;
    private View layout;
    @BindView(id = R.id.rent_toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.swipeLayout)
    private SwipeRefreshLayout mSwipeLayout;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mRecyclerView;
    private ChehangRecyclerAdapter mAdapter;
    private int lastVisibleItem;
    private ArrayList<Object> list;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_rent, null);
        return layout;
    }

    private Handler hanlder = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSwipeLayout.setProgressViewEndTarget(false, (int) (64*aty.getResources().getDisplayMetrics().density));
            mSwipeLayout.setRefreshing(false);
        }
    };

    @Override
    protected void initData() {
        super.initData();
        mSwipeLayout.setProgressViewEndTarget(false, (int) (64*aty.getResources().getDisplayMetrics().density));
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 加载数据
                hanlder.sendEmptyMessageDelayed(1,2000);
                KJLoger.debug("正在刷新");

            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(aty);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ChehangRecyclerAdapter();
         list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Chehang c = new Chehang();
            c.setName("chehang" + i);
            c.setDesc("desc" + i);
            c.setDistance("距离：" + i);
            c.setImg(imgs[i % imgs.length]);
            list.add(c);
        }
        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()  && !mSwipeLayout.isRefreshing()) {
                    mSwipeLayout.setProgressViewEndTarget(true, AppContext.screenH-400);
                    mSwipeLayout.setRefreshing(true);

                    for (int i = 0; i < 10; i++) {
                        Chehang c = new Chehang();
                        c.setName("chehang" + i);
                        c.setDesc("desc" + i);
                        c.setDistance("距离：" + i);
                        c.setImg(imgs[i % imgs.length]);
                        list.add(c);
                    }
                    mAdapter.notifyDataSetChanged();
                    hanlder.sendEmptyMessageDelayed(1, 3000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

    }


    String[] imgs = new String[]{
            "http://img1.imgtn.bdimg.com/it/u=778667008,2576582091&fm=11&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2710176087,2375657815&fm=11&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1976928526,3694285366&fm=11&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=420941773,639909175&fm=11&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3965374379,1508737823&fm=11&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=976617532,2015171899&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1004958281,780364475&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=896221891,1227406065&fm=11&gp=0.jpg"
    };


}
