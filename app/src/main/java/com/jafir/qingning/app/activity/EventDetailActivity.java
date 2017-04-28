package com.jafir.qingning.app.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jafir.qingning.R;
import com.jafir.qingning.app.AppConstant;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.ShopCompleteRecyclerAdapter;
import com.jafir.qingning.app.util.PhoneUtils;
import com.jafir.qingning.model.bean.Event;
import com.jafir.qingning.model.bean.Shop;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/10.
 */
public class EventDetailActivity extends BaseActivity {


    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.viewpager)
    private ViewPager mViewpager;
    private ArrayList<View> viewList;
    private ArrayList<String> titleList;
    @BindView(id = R.id.tab)
    private TabLayout mTabLayout;
    @BindView(id = R.id.cover)
    private ImageView mImg;
    private RecyclerView mShopRecyclerView;
    private ShopCompleteRecyclerAdapter mAdapter;
    private ArrayList<Shop> shops = new ArrayList<>();

    @BindView(id = R.id.add, click = true)
    private TextView mAdd;

    private Event event;



    //    详情相关
    private TextView userName;
    private ImageView userAvatar;
    private TextView userPhone;
    private TextView endTime;
    private TextView startTime;
    private TextView days;
    private TextView sparePeople;
    private TextView people;
    private TextView destination;
    private TextView money;
    private TextView require;
    private TextView joinPeople;
    private View gotoJoinPeople;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_event_detail);
    }


    @Override
    public void initData() {
        super.initData();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("虹口漂流");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventDetailActivity.this.finish();
            }
        });

        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.event_detail_pager1, null);
        View view2 = lf.inflate(R.layout.event_detail_pager3, null);
        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("活动详情");
        titleList.add("商家竞标");

        String url = getIntent().getStringExtra("cover");
        event = (Event) getIntent().getSerializableExtra("data");
        if(!TextUtils.isEmpty(url)) {
            ViewCompat.setTransitionName(mImg, "img");
            Glide.with(this).load(url).into(mImg);
        }


        initView1(view1);
        initView2(view2);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);

        initViewpager();

    }

    private void initViewpager() {

        mViewpager.setAdapter(new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));

            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return titleList.get(position);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

        });
        mTabLayout.setupWithViewPager(mViewpager);

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    mAdd.setVisibility(View.VISIBLE);
                } else {
                    mAdd.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView1(View view1) {
        userName = (TextView) view1.findViewById(R.id.event_detail_user_name);
        userAvatar = (ImageView) view1.findViewById(R.id.event_detail_user_avatar);
        userPhone = (TextView) view1.findViewById(R.id.event_detail_user_phone);
        destination = (TextView) view1.findViewById(R.id.event_detail_destination);
        money = (TextView) view1.findViewById(R.id.event_detail_money);
        people = (TextView) view1.findViewById(R.id.event_detail_people);
        joinPeople = (TextView) view1.findViewById(R.id.event_detail_join_people);
        sparePeople = (TextView) view1.findViewById(R.id.event_detail_spare_people);
        startTime = (TextView) view1.findViewById(R.id.event_detail_start_time);
        endTime = (TextView) view1.findViewById(R.id.event_detail_end_time);
        require = (TextView) view1.findViewById(R.id.event_detail_require);
        days = (TextView) view1.findViewById(R.id.event_detail_days);
        gotoJoinPeople = view1.findViewById(R.id.goto_join_user);

        getSupportActionBar().setTitle(event.getName());
        userName.setText(event.getAuthor());
//        Glide.with(this).load(event.getAuthorAvatar()).into(userAvatar);
        new KJBitmap.Builder().imageUrl(event.getAuthorAvatar()).view(userAvatar).display();
        destination.setText(event.getTarget());
        money.setText(event.getMoney());
        people.setText(event.getPeople()+"人");
        startTime.setText(event.getStartTime());
        endTime.setText(event.getEndTime());
        require.setText(event.getRequire());
        days.setText(event.getDays());
        joinPeople.setText(event.getJoinPeople()+"人");
        int cou = Integer.valueOf(event.getPeople())-Integer.valueOf(event.getJoinPeople());
        sparePeople.setText(""+cou+"人");




        gotoJoinPeople.setOnClickListener(this);
        userPhone.setOnClickListener(this);
        userAvatar.setOnClickListener(this);
        userName.setOnClickListener(this);

    }

    private void initView2(View view2) {
        mShopRecyclerView = (RecyclerView) view2.findViewById(R.id.recyclerview);
        mShopRecyclerView.setLayoutManager(new LinearLayoutManager(aty, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ShopCompleteRecyclerAdapter();
        //模拟数据
        for (int i = 0; i < 10; i++) {
            Shop shop = new Shop();
            shop.setName("澳门海底捞");
            shop.setAvatar(AppConstant.avatar[i % AppConstant.avatar.length]);
            shop.setIntroduce("本店可提供10人豪华特色餐食，更有钓鱼项目可免费烹饪。 更有一体的田更有一体的田更有一体的田");
            shop.setPhone("13982004324");
            shops.add(shop);
        }
        mAdapter.setData(shops);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(aty, ShopDetailActivity.class));
            }
        });
        mShopRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.add:
                break;
            case R.id.goto_join_user:
                startActivity(new Intent(EventDetailActivity.this,JoinPeopleListActivity.class));
                break;
            case R.id.event_detail_user_avatar:
                break;
            case R.id.event_detail_user_name:
                break;
            case R.id.event_detail_user_phone:
                openPhone();
                break;

        }
    }

    private void openPhone() {
        PhoneUtils.call("13982004324",this);
    }


}




