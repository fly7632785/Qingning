package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jafir.qingning.R;
import com.jafir.qingning.app.AppConstant;
import com.jafir.qingning.app.AppContext;
import com.jafir.qingning.app.activity.EditActivity;
import com.jafir.qingning.app.activity.GuideBookDetailActivity;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.GuideBookRecyclerAdapter;
import com.jafir.qingning.model.bean.GuideBook;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jafir on 16/4/19.
 */
public class GuideBookFragment extends SupportFragment {


    private Activity aty;
    private View layout;
    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mRecyclerView;
    private GuideBookRecyclerAdapter mAdapter;
    private ArrayList<GuideBook> list;
    @BindView(id = R.id.swipeLayout)
    private SwipeRefreshLayout mSwipeLayout;
    private int lastVisibleItem;
    @BindView(id = R.id.fab, click = true)
    private FloatingActionButton mFab;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_guidebook, null);
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
                    List<GuideBook> guideBooks = new ArrayList<>();
                    //加载数据
                    for (int i = 0; i < 10; i++) {
                        GuideBook guideBook = new GuideBook();
                        guideBook.setTitle("美食是一道风景。挑战纽约经典面包亲手做一个糖霜包");
                        guideBook.setTime("2015.4.4");
                        guideBook.setImgUrl(imgs[i % imgs.length]);
                        guideBook.setAvatar("http://img3.imgtn.bdimg.com/it/u=2103190071,4127559232&fm=23&gp=0.jpg");
                        guideBook.setAddress("目的地：都江堰朝阳路12号");
                        guideBook.setLikes("有5人喜欢");
                        guideBooks.add(guideBook);
                    }
                    mAdapter.addData(guideBooks);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public static class RefreshEvent {
        String url;

        public RefreshEvent(String url) {
            this.url = url;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshEvent event) {

        refresh(event.url);
        Log.d("url", event.url);
    }


    public void refresh(String url) {
        String html = PreferenceHelper.readString(getContext(), "html", "html");
        GuideBook guideBook = new GuideBook();
        guideBook.setHtml(html);
        guideBook.setTitle("青城山一日游");
        guideBook.setTime("2017.5.1");
        guideBook.setImgUrl(url);
        guideBook.setAvatar(AppConstant.avatar[10]);
        guideBook.setAddress("目的地：倾城后山");
        guideBook.setLikes("有5人喜欢");
        list.add(0, guideBook);
        mAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(aty);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new GuideBookRecyclerAdapter();
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(aty, GuideBookDetailActivity.class);
                intent.putExtra("data", list.get(position).getHtml());

                startActivity(intent);
            }
        });
        list = new ArrayList<>();

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
                startActivity(new Intent(aty, EditActivity.class));
                break;
        }
    }

    public static String[] imgs = new String[]{
            "http://img1.imgtn.bdimg.com/it/u=477258041,2687201414&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1855525785,2137748479&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3751941462,3742158046&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3449612747,435978486&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1505133905,1300758036&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=673565330,877674017&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=140996824,4196705688&fm=21&gp=0.jpg"
    };


}
