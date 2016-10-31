package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jafir.qingning.R;
import com.jafir.qingning.app.AppContext;
import com.jafir.qingning.app.activity.publishEvent.PublishEventActivity;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.EventRecyclerAdapter;
import com.jafir.qingning.model.bean.Event;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.util.ArrayList;

/**
 * Created by jafir on 16/4/19.
 */
public class EventFragment extends SupportFragment {


    private Activity aty;
    private View layout;
    @BindView(id = R.id.event_toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mRecyclerView;
    private EventRecyclerAdapter mAdapter;
    private ArrayList<Event> list;
    @BindView(id = R.id.swipeLayout)
    private SwipeRefreshLayout mSwipeLayout;
    private int lastVisibleItem;
    @BindView(id = R.id.fab, click = true)
    private FloatingActionButton mFab;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_event, null);
        return layout;
    }

    private Handler hanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:

                    break;
                case 2:
                    //加载数据
                    for (int i = 0; i < 10; i++) {
                        Event event = new Event();
                        event.setName("云墨山水--青城山");
                        event.setAuthor("jafir");
                        event.setPeople("有15人参与");
                        event.setBussiness("4家商家竞标");
                        event.setTime("2015.4.4");
                        event.setTimes("123次浏览");
                        event.setSpareTime("2天");
                        event.setImg(imgs[i % imgs.length]);
                        event.setPortrait("http://img3.imgtn.bdimg.com/it/u=2103190071,4127559232&fm=23&gp=0.jpg");
                        list.add(event);
                    }
                    mAdapter.notifyDataSetChanged();
                    //加载完之后要把swip设置为默认位置
                    mSwipeLayout.setProgressViewEndTarget(false, (int) (64 * aty.getResources().getDisplayMetrics().density));

                    break;
            }

            mSwipeLayout.setRefreshing(false);
        }
    };

    @Override
    protected void initData() {
        super.initData();
        int density = (int) aty.getResources().getDisplayMetrics().density;
//        mSwipelayout.setProgressViewOffset(false,50*density,80*density);
        mSwipeLayout.setProgressViewEndTarget(false, 50 * density);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hanlder.sendEmptyMessageDelayed(1, 3000);
            }
        });

        initRecyclerView();

    }

    private void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(aty);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new EventRecyclerAdapter();
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                startActivity(new Intent(aty, EventDetailActivity.class));
            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("www.qingning.com")
//                .build();
//         final ApiService service = retrofit.create(ApiService.class);
//        Call<List<Chehang>> call = service.listShop("uid");
//        call.enqueue(new Callback<List<Chehang>>() {
//            @Override
//            public void onResponse(Call<List<Chehang>> call, Response<List<Chehang>> response) {
//                List<Chehang> list = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<Chehang>> call, Throwable t) {
//
//            }
//        });
//        service.login("zhanghu", "password")
//                .flatMap(new Func1<String, Observable<User>>() {
//            @Override
//            public Observable<User> call(String s) {
//                return service.getUser(s);
//            }
//        }).subscribeOn(AndroidSchedulers.mainThread()) //请求完成后在io线程中执行
//                .observeOn(Schedulers.newThread())//请求在新的线程中执行请求
//                .doOnNext(new Action1<User>() {
//                    @Override
//                    public void call(User user) {
//
//                    }
//                })
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(User user) {
//
//                    }
//                });

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setName("云墨山水--青城山");
            event.setAuthor("jafir");
            event.setPeople("有15人参与");
            event.setBussiness("4家商家竞标");
            event.setTime("2015.4.4");
            event.setTimes("123次浏览");
            event.setSpareTime("2天");
            event.setImg(imgs[i % imgs.length]);
            event.setPortrait("http://img3.imgtn.bdimg.com/it/u=2103190071,4127559232&fm=23&gp=0.jpg");
            list.add(event);
        }
        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount() && !mSwipeLayout.isRefreshing()) {
                    mSwipeLayout.setProgressViewEndTarget(true, AppContext.screenH - 400);
                    mSwipeLayout.setRefreshing(true);
                    hanlder.sendEmptyMessageDelayed(2, 2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });


    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.fab:
                startActivity(new Intent(aty, PublishEventActivity.class));
                break;
        }
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
