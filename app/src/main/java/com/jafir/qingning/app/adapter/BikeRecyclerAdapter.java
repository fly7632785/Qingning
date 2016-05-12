package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jafir.qingning.R;
import com.jafir.qingning.app.view.MoreAndLessView;
import com.jafir.qingning.model.bean.Bike;

/**
 * Created by jafir on 16/5/10.
 */
public class BikeRecyclerAdapter extends BaseRecyclerAdapter<Bike> {


    private int totalCount;
    private TotalCountListener listener;

    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_bike_recycler, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        ImageViewHolder holder = (ImageViewHolder) myholder;
        holder.mMoreAndLessView.setListener(new MyListener(position));

        if(mDatas == null){
            return;
        }

        Bike bike = mDatas.get(position);
        Glide.with(mContext).load(bike.getImg()).into(holder.mImageView);
        holder.mName.setText(bike.getName());
        holder.mKind.setText(bike.getKind());
        holder.mPrice.setText(bike.getPrice());
        holder.mSpareCount.setText("（剩余"+bike.getSpareCount()+"辆)");


    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mName;
        private TextView mKind;
        private TextView mPrice;
        private TextView mSpareCount;
        private MoreAndLessView mMoreAndLessView;


        public ImageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null && mDatas.size() != 0) {
                        mOnItemClickListener.onItemClick(v, getAdapterPosition() % mDatas.size());
                    }
                }
            });
            mImageView = (ImageView) itemView.findViewById(R.id.item_bike_img);
            mName = (TextView) itemView.findViewById(R.id.item_bike_name);
            mKind = (TextView) itemView.findViewById(R.id.item_bike_kind);
            mPrice = (TextView) itemView.findViewById(R.id.item_bike_price);
            mSpareCount = (TextView) itemView.findViewById(R.id.item_bike_spare_count);
            mMoreAndLessView = (MoreAndLessView) itemView.findViewById(R.id.item_bike_more_less);


        }
    }


    private class MyListener implements MoreAndLessView.CountChangedListener {

        private int position;

        public MyListener(int position) {
            this.position = position;
        }

        @Override
        public void onCountChanged(int count) {
            mDatas.get(position).setOrderCount(count);
            calcTotalCount();
            listener.onTotalCountChanged(totalCount);
        }
    }


    private int calcTotalCount() {
        totalCount = 0;
        for(Bike bike : mDatas){
            totalCount +=bike.getOrderCount();
        }
        return totalCount;
    }


    public interface TotalCountListener{
        void onTotalCountChanged(int totalCount);
    }

    public TotalCountListener getListener() {
        return listener;
    }

    public void setListener(TotalCountListener listener) {
        this.listener = listener;
    }


    public int getTotalCount() {
        return totalCount;
    }
}
