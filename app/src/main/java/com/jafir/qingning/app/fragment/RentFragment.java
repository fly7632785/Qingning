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
import com.jafir.qingning.app.AppConstant;
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
import java.util.List;
import java.util.Random;

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
    private ArrayList<Chehang> list;
    private String json = "{\"chehangs\":[{\n" +
            "        \"name\":\"奔奔租车行\",\n" +
            "         \"img\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491821999532&di=a0cc70d58eac62d2842b1d79f2eacfe3&imgtype=0&src=http%3A%2F%2Fnimg.edushi.com%2FuserupImages%2Fpic_chengdu%2F45%2FCOM15779200671310424520185.jpg\",\n" +
            "         \"desc\":\"\",\n" +
            "         \"location\":\"青城山\",\n" +
            "         \"locationInfo\":\"青城山大道101号\",\n" +
            "         \"phone\":\"13982004324\",\n" +
            "         \"opentime\":\"10:00am-10:00pm\",\n" +
            "         \"score\":\"4.2\",\n" +
            "         \"discountInfo\":\"本店课随机提供以下面额代金券，还车时店主课随机发放不同面额的代金券，用户可相应抵消费用。\",\n" +
            "         \"distance\":\"500m\",\n" +
            "        \"zuci\":\"24\",\n" +
            "        \"comments\":[\n" +
            " {\n" +
            "     \"imgUrl\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491821999530&di=cf10397226a6cc5734a0e52dab7a36d3&imgtype=0&src=http%3A%2F%2Fwww.yuxiancity.com%2Fkeditor%2Fasp%2F..%2Fattached%2Fimage%2F20150523%2F20150523072693179317.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"这家车行好啊，不错，车很轻\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 13:32\",\n" +
            "      \"content\":\"车不错，而且价格便宜\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\".......\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"差评，车胎爆了0-0\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"双人车很不错\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"电瓶车，电量十足啊\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"跑了好远电量很牛逼\"\n" +
            " }\n" +
            " ]\n" +
            " },\n" +
            "{\n" +
            "        \"name\":\"自由租车行\",\n" +
            "         \"img\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491821999529&di=ae9629335fc6da18e3985069b98a2b6e&imgtype=0&src=http%3A%2F%2Fp1.pccoo.cn%2Fyp%2F20131028%2F201310281413332568.jpg\",\n" +
            "         \"desc\":\"\",\n" +
            "         \"location\":\"青城山\",\n" +
            "         \"locationInfo\":\"青城山大道121号\",\n" +
            "         \"phone\":\"13982004324\",\n" +
            "         \"opentime\":\"10:00am-10:00pm\",\n" +
            "         \"score\":\"3.2\",\n" +
            "         \"discountInfo\":\"本店课随机提供以下面额代金券，还车时店主课随机发放不同面额的代金券，用户可相应抵消费用。\",\n" +
            "         \"distance\":\"700m\",\n" +
            "        \"zuci\":\"34\",\n" +
            "        \"comments\":[\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"这家车行好啊，不错，车很轻\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 13:32\",\n" +
            "      \"content\":\"车不错，而且价格便宜\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\".......\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"差评，车胎爆了0-0\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"双人车很不错\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"电瓶车，电量十足啊\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"跑了好远电量很牛逼\"\n" +
            " }\n" +
            " ]\n" +
            " },\n" +
            "{\n" +
            "        \"name\":\"风行租车行\",\n" +
            "         \"img\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491822052859&di=f9a71baba78b9ef4e0baa09ed77c4931&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D465518959%2C1461045690%26fm%3D214%26gp%3D0.jpg\",\n" +
            "         \"desc\":\"\",\n" +
            "         \"location\":\"青城山\",\n" +
            "         \"locationInfo\":\"青城山大道11号\",\n" +
            "         \"phone\":\"13982004324\",\n" +
            "         \"opentime\":\"10:00am-10:00pm\",\n" +
            "         \"score\":\"4.6\",\n" +
            "         \"discountInfo\":\"本店课随机提供以下面额代金券，还车时店主课随机发放不同面额的代金券，用户可相应抵消费用。\",\n" +
            "         \"distance\":\"1.1km\",\n" +
            "        \"zuci\":\"203\",\n" +
            "        \"comments\":[\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"这家车行好啊，不错，车很轻\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 13:32\",\n" +
            "      \"content\":\"车不错，而且价格便宜\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\".......\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"差评，车胎爆了0-0\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"双人车很不错\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"电瓶车，电量十足啊\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"跑了好远电量很牛逼\"\n" +
            " }\n" +
            " ]\n" +
            " },\n" +
            "{\n" +
            "        \"name\":\"天哪租车行\",\n" +
            "         \"img\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491821999528&di=ee726d6a65357175054aff45423f13b0&imgtype=0&src=http%3A%2F%2Fpic15.huitu.com%2Fres%2F20131230%2F169904_20131230234850729331_1.jpg\",\n" +
            "         \"desc\":\"\",\n" +
            "         \"location\":\"青城山\",\n" +
            "         \"locationInfo\":\"青城山大道2101号\",\n" +
            "         \"phone\":\"13982004324\",\n" +
            "         \"opentime\":\"10:00am-10:00pm\",\n" +
            "         \"score\":\"4.0\",\n" +
            "         \"discountInfo\":\"本店课随机提供以下面额代金券，还车时店主课随机发放不同面额的代金券，用户可相应抵消费用。\",\n" +
            "         \"distance\":\"50m\",\n" +
            "        \"zuci\":\"44\",\n" +
            "        \"comments\":[\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"这家车行好啊，不错，车很轻\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 13:32\",\n" +
            "      \"content\":\"车不错，而且价格便宜\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\".......\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"差评，车胎爆了0-0\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"双人车很不错\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"电瓶车，电量十足啊\"\n" +
            " },\n" +
            " {\n" +
            "     \"imgUrl\":\"http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg\",\n" +
            "      \"name\":\"Jafir\",\n" +
            "      \"time\":\"2016-8-10 11:32\",\n" +
            "      \"content\":\"跑了好远电量很牛逼\"\n" +
            " }\n" +
            " ]\n" +
            " }\n" +
            "\n" +
            "\n" +
            "]}";

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

            switch (msg.what) {
                case 1:

                    break;
                case 2:
                    //加载数据
                    List<Chehang> chehangs = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        Chehang c = new Chehang();
                        c.setName(AppConstant.chenghangname[new Random().nextInt(100) % AppConstant.chenghangname.length]);
                        c.setDesc("desc" + i);
                        c.setDistance("[青城山]距离：" + new Random().nextInt(1000) + "m");
                        c.setImg(AppConstant.chehang[new Random().nextInt(100) % AppConstant.chehang.length]);
                        c.setZuci("租次" + new Random().nextInt(50));
                        c.setPhone("13982004324");
                        c.setOpentime("10:00am-10:00pm");
                        c.setDiscountInfo(AppConstant.discountInfo[new Random().nextInt(100) % AppConstant.discountInfo.length]);
                        c.setLocationInfo(AppConstant.locations[new Random().nextInt(100) % AppConstant.locations.length]);
                        c.setLocation(AppConstant.locations[new Random().nextInt(100) % AppConstant.locations.length]);
                        c.setScore("" + new Random().nextInt(5));
                        chehangs.add(c);
                    }
//                    Gson gson = new Gson();
//                    ChehangMock chehangMock = gson.fromJson(json, ChehangMock.class);
//                    list.addAll(chehangMock.getChehangs());


                    mAdapter.addData(chehangs);
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
                if (item.getItemId() == R.id.ab_search) {

                } else if (item.getItemId() == R.id.ab_locate) {
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
                mSwipeLayout.setEnabled(true);
                KJLoger.debug("query....onClose" );
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeLayout.setEnabled(false);
                KJLoger.debug("query....onClick" );
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                KJLoger.debug("query...." + query);
//                mAdapter.getFilter().filter(query);

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                KJLoger.debug("changed...." + newText);

                mAdapter.getFilter().filter(newText);
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
                Intent intent = new Intent(aty, ChehangDetailActivity.class);
                intent.putExtra("data", list.get(position));
                startActivity(intent);
            }
        });
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Chehang c = new Chehang();
            c.setName(AppConstant.chenghangname[new Random().nextInt(100) % AppConstant.chenghangname.length]);
            c.setDesc("desc" + i);
            c.setDistance("[青城山]距离：" + new Random().nextInt(1000) + "m");
//            c.setImg(AppConstant.chehang[new Random().nextInt(100) %AppConstant.chehang.length]);
            c.setImg(AppConstant.chehang[i % AppConstant.chehang.length]);
            c.setZuci("租次" + new Random().nextInt(50));
            c.setPhone("13982004324");
            c.setOpentime("10:00am-10:00pm");
            c.setDiscountInfo(AppConstant.discountInfo[new Random().nextInt(100) % AppConstant.discountInfo.length]);
            c.setLocationInfo(AppConstant.locations[new Random().nextInt(100) % AppConstant.locations.length]);
            c.setLocation(AppConstant.locations[new Random().nextInt(100) % AppConstant.locations.length]);
            c.setScore("" + new Random().nextInt(5));
            list.add(c);
        }
//        Gson gson = new Gson();
//        ChehangMock chehangMock = gson.fromJson(json, ChehangMock.class);
//        list = (ArrayList<Chehang>) chehangMock.getChehangs();
        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount() && !mSwipeLayout.isRefreshing()) {
                    if(mSwipeLayout.isEnabled()) {
                        mSwipeLayout.setProgressViewEndTarget(true, AppContext.screenH - 400);
                        mSwipeLayout.setRefreshing(true);
                        //网络请求  加载更多数据
                        hanlder.sendEmptyMessageDelayed(2, 2000);
                    }
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
