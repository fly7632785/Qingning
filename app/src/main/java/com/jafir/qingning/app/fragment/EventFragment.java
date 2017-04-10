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
import com.jafir.qingning.app.AppConstant;
import com.jafir.qingning.app.AppContext;
import com.jafir.qingning.app.activity.publishEvent.PublishEventActivity;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.EventRecyclerAdapter;
import com.jafir.qingning.model.bean.Event;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.util.ArrayList;
import java.util.Random;

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
                        event.setName(AppConstant.huodongname[new Random().nextInt(100)%AppConstant.huodongname.length]);
                        event.setAuthor("moria");
                        event.setAuthorAvatar(AppConstant.avatar[new Random().nextInt(100)%AppConstant.avatar.length]);
                        event.setPeople(new Random().nextInt(10) +"");
                        event.setJoinPeople(new Random().nextInt(4) + "");
                        event.setBussiness(new Random().nextInt(5)+"家商家竞标");
                        event.setTime("2016.7.4");
                        event.setTarget(AppConstant.target[new Random().nextInt(100)%AppConstant.target.length]);
                        event.setTimes(new Random().nextInt(15) + "次浏览");
                        event.setSpareTime(new Random().nextInt(4) + "天");
                        event.setDesc("desc");
                        event.setDays(new Random().nextInt(5)+"天");
                        event.setStartTime("2016-11-2 10:00am");
                        event.setEndTime("2016-11-4 7:00am");
                        event.setHowlong("" + new Random().nextInt(5));
                        event.setMoney(new Random().nextInt(40) + "元");
                        event.setImg(AppConstant.huodong[new Random().nextInt(100) % AppConstant.huodong.length]);
                        event.setPortrait(AppConstant.avatar[new Random().nextInt(4)]);
                        event.setRequire(AppConstant.require[new Random().nextInt(100) % AppConstant.require.length]);
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
            event.setName(AppConstant.huodongname[new Random().nextInt(100)%AppConstant.huodongname.length]);
            event.setAuthor("Jafir");
            event.setAuthorAvatar(AppConstant.avatar[new Random().nextInt(100)%AppConstant.avatar.length]);
            event.setPeople(new Random().nextInt(10) +"");
            event.setJoinPeople(new Random().nextInt(4) + "");
            event.setBussiness(new Random().nextInt(5)+"家商家竞标");
            event.setTime("2016.7.4");
            event.setTarget(AppConstant.target[new Random().nextInt(100)%AppConstant.target.length]);
            event.setTimes(new Random().nextInt(15) + "次浏览");
            event.setSpareTime(new Random().nextInt(4) + "天");
            event.setDesc("desc");
            event.setDays(new Random().nextInt(5)+"天");
            event.setStartTime("2016-11-2 10:00am");
            event.setEndTime("2016-11-4 7:00am");
            event.setHowlong("" + new Random().nextInt(5));
            event.setMoney(new Random().nextInt(40) + "元");
            event.setImg(AppConstant.huodong[new Random().nextInt(100) % AppConstant.huodong.length]);
            event.setPortrait(AppConstant.avatar[new Random().nextInt(4)]);
            event.setRequire(AppConstant.require[new Random().nextInt(100) % AppConstant.require.length]);
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
        switch (v.getId()) {
            case R.id.fab:
                startActivity(new Intent(aty, PublishEventActivity.class));
                break;
        }
    }


}
