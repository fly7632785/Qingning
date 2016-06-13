package com.jafir.qingning.app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.app.adapter.CommentRecyclerAdapter;
import com.jafir.qingning.app.adapter.PicRecyclerAdapter;
import com.jafir.qingning.model.bean.Comment;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import java.util.ArrayList;

/**
 * Created by jafir on 16/5/10.
 */
public class ShopDetailActivity extends BaseActivity {


    @BindView(id = R.id.toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.cover)
    private ImageView mImg;


    private CommentRecyclerAdapter mAdapter;
    private PicRecyclerAdapter mPicAdapter;

    @BindView(id = R.id.shop_detail_introduce)
    private TextView mIntroduce;
    @BindView(id = R.id.shop_detail_address)
    private TextView mAddress;
    @BindView(id = R.id.shop_detail_orders)
    private TextView mOrders;
    @BindView(id = R.id.shop_detail_score)
    private TextView mScore;
    @BindView(id = R.id.shop_detail_ratingbar)
    private RatingBar mRatingbar;

    @BindView(id = R.id.recyler_pager)
    private RecyclerViewPager mRecyclerView;
    @BindView(id = R.id.recyclerview)
    private RecyclerView mCommentRecycler;

    private ArrayList<String> mPicData;
    private ArrayList<Comment> mCommentData;


    private String[] imgUrl = new String[]{
            "http://img4.imgtn.bdimg.com/it/u=3140872691,2829849339&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=4268278613,361861111&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1350047332,1494732171&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3052494958,2009729320&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1815829197,442667104&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1657246505,1863652736&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2992702144,3552013788&fm=21&gp=0.jpg"

    };

    private String[] avatarUrl = new String[]{
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=f3f6ab70cc134954611eef64664f92dd/dcc451da81cb39db1bd474a7d7160924ab18302e.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D200/sign=0afb9ebc4c36acaf46e091fc4cd88d03/bd3eb13533fa828b670a4066fa1f4134970a5a0e.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2147665307,4031352505&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2769901330,4132322556&fm=23&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=693362385,3280695814&fm=23&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3047710011,1274531363&fm=23&gp=0.jpg"
    };
    private CommentRecyclerAdapter mCommentAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_shop_detail);
    }


    @Override
    public void initData() {
        super.initData();


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("澳门海底捞");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopDetailActivity.this.finish();
            }
        });

        initRecylerViewPager();
        initCommentRecylerViewPager();


    }

    private void initCommentRecylerViewPager() {

        LinearLayoutManager lllayout = new LinearLayoutManager(aty, LinearLayoutManager.VERTICAL, false);
        mCommentRecycler.setLayoutManager(lllayout);
//        //模拟数据
        mCommentData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setName("我的名字");
            comment.setContent("评论评论评论评论评论评论评论评论评论评论评论评论");
            comment.setTime("2014-3-3 11:32");
            comment.setImgUrl(avatarUrl[i % avatarUrl.length]);
            mCommentData.add(comment);
        }

        mCommentAdapter = new CommentRecyclerAdapter();

        mCommentAdapter.setData(mCommentData);
        mCommentRecycler.setAdapter(mCommentAdapter);

        mCommentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ViewInject.toast("点击了" + position);
            }
        });


    }

    private void initRecylerViewPager() {
        //布局管理器  线性
        LinearLayoutManager lllayout = new LinearLayoutManager(aty, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(lllayout);
//        //模拟数据
        mPicData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mPicData.add(imgUrl[i % imgUrl.length]);
        }

        mPicAdapter = new PicRecyclerAdapter();

        mPicAdapter.setData(mPicData);
        mRecyclerView.setAdapter(mPicAdapter);

        mPicAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ViewInject.toast("点击了" + position);
            }
        });

        //设置现在的 卡片position
        mRecyclerView.scrollToPosition(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mPicData.size());

        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int childCount = recyclerView.getChildCount();
                int width = recyclerView.getChildAt(0).getWidth();
                int padding = (recyclerView.getWidth() - width) / 2;
                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);
                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                RecyclerViewPager mRecyclerView = (RecyclerViewPager) v;
                if (mRecyclerView.getChildCount() < 3) {
                    if (mRecyclerView.getChildAt(1) != null) {
                        View v1 = mRecyclerView.getChildAt(1);
                        v1.setScaleY(0.9f);
                    }
                } else {
                    if (mRecyclerView.getChildAt(0) != null) {
                        View v0 = mRecyclerView.getChildAt(0);
                        v0.setScaleY(0.9f);
                    }
                    if (mRecyclerView.getChildAt(2) != null) {
                        View v2 = mRecyclerView.getChildAt(2);
                        v2.setScaleY(0.9f);
                    }
                }

            }
        });
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {

            case R.id.shop_detail_call:
                openPhone();
                break;

        }
    }

    private void openPhone() {
        AlertDialog dialog = new AlertDialog.Builder(aty)
                .setTitle("拨打电话")
                .setMessage("您要拨打" + "13982004324" + "吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:13982004324")));
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();

    }


}




