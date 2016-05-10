package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.Chehang;

/**
 * Created by jafir on 16/5/10.
 */
public class ChehangRecyclerAdapter extends BaseRecyclerAdapter {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_chehang_recycler, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if(mDatas.isEmpty()){
            return;
        }
        Chehang chehang = (Chehang) mDatas.get(position);
        ImageViewHolder holder = (ImageViewHolder) myholder;
        holder.mDistance.setText(chehang.getDistance());
        holder.mDesc.setText(chehang.getDesc());
        holder.mName.setText(chehang.getName());
        Glide.with(mContext).load(chehang.getImg()).centerCrop().into(holder.mImageView);





    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mName;
        private TextView mDistance;
        private TextView mDesc;


        public ImageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null && mDatas.size()!= 0) {
                        mOnItemClickListener.onItemClick(v, getAdapterPosition() % mDatas.size());
                    }
                }
            });
            mImageView = (ImageView) itemView.findViewById(R.id.item_chehang_img);
            mName = (TextView) itemView.findViewById(R.id.item_chehang_name);
            mDesc = (TextView) itemView.findViewById(R.id.item_chehang_desc);
            mDistance = (TextView) itemView.findViewById(R.id.item_chehang_distance);
        }
    }

}
