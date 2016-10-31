package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jafir.qingning.R;
import com.jafir.qingning.app.AppContext;
import com.jafir.qingning.app.activity.rent.ChehangDetailActivity;
import com.jafir.qingning.app.activity.rent.CityPickerActivity;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
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

    private Handler hanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:

                    break;
                case 2:
                    //加载数据
                    for (int i = 0; i < 10; i++) {
                        Chehang c = new Chehang();
                        c.setName("奔奔租车行" + i);
                        c.setDesc("desc" + i);
                        c.setDistance("[青城山]距离：" + i + "m");
                        c.setImg(imgs[i % imgs.length]);
                        c.setZuci("租次" + i);
                        list.add(c);
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

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.ab_search){

                }else if (item.getItemId() == R.id.ab_locate){
                    startActivity(new Intent(aty, CityPickerActivity.class));
                }
                return false;
            }
        });

        mToolbar.inflateMenu(R.menu.rent_menu);



        SearchView searchView =
                (SearchView) mToolbar.findViewById(R.id.ab_search);

//        AppCompatImageView search = (AppCompatImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
//        search.setImageResource(R.mipmap.common_search);
//        AppCompatImageView close = (AppCompatImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
//        close.setImageResource(R.mipmap.ic_search_clear);
//        TextView textView = (TextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        textView.setHintTextColor(Color.WHITE);
//        textView.setTextColor(Color.WHITE);
        searchView.setQueryHint("输入商家名");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                KJLoger.debug("close");
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                KJLoger.debug("query...." + query);

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                KJLoger.debug("changed...." + newText);
//                mAdapter.getFilter().filter(newText);
                return false;
            }
        });


        mSwipeLayout.setProgressViewEndTarget(false, (int) (64 * aty.getResources().getDisplayMetrics().density));
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新加载数据
                hanlder.sendEmptyMessageDelayed(1, 2000);
                KJLoger.debug("正在刷新");

            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(aty);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ChehangRecyclerAdapter();
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(aty, ChehangDetailActivity.class));
            }
        });
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Chehang c = new Chehang();
            c.setName("奔奔租车行" + i);
            c.setDesc("desc" + i);
            c.setDistance("[青城山]距离：" + i + "m");
            c.setImg(imgs[i % imgs.length]);
            c.setZuci("租次" + i);
            list.add(c);
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
                    //网络请求  加载更多数据
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


    String[] imgs = new String[]{
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
