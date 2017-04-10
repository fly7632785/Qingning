package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.Order;

/**
 * Created by jafir on 16/5/10.
 */
public class MyOrderRecyclerAdapter extends BaseRecyclerAdapter<Order> {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_myorder_recycler, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }


        Order order = mDatas.get(position);
        ImageViewHolder holder = (ImageViewHolder) myholder;

        holder.mTitle.setText(order.getTitle());
        holder.mTime.setText(order.getTime());
        holder.mStatus.setText(order.getStatus());
        holder.mInfo.setText(order.getInfo());
        Glide.with(mContext).load(order.getAvatar()).into(holder.mImg);



    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg;
        private TextView mTitle;
        private TextView mStatus;
        private TextView mTime;
        private TextView mInfo;


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
            mImg = (ImageView) itemView.findViewById(R.id.item_myorder_recycler_img);
            mTitle = (TextView) itemView.findViewById(R.id.item_myorder_recycler_title);
            mTime = (TextView) itemView.findViewById(R.id.item_myorder_recycler_time);
            mStatus = (TextView) itemView.findViewById(R.id.item_myorder_recycler_status);
            mInfo = (TextView) itemView.findViewById(R.id.item_myorder_recycler_info);
        }
    }

}
