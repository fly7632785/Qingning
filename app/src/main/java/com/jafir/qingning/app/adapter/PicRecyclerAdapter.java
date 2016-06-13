package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jafir.qingning.R;

/**
 */
public class PicRecyclerAdapter extends BaseRecyclerAdapter<String> {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(mContext, R.layout.item_pic_recyler, null);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }
        ImageViewHolder holder = (ImageViewHolder) myholder;
        position = position % mDatas.size();
        String url = mDatas.get(position);
        Glide.with(mContext).load(url).into(holder.mImageView);
    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;


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
            mImageView = (ImageView) itemView.findViewById(R.id.item_pic_img);
        }
    }


}
