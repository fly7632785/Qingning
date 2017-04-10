package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.GuideBook;

/**
 * Created by jafir on 16/5/10.
 */
public class MylikeRecyclerAdapter extends BaseRecyclerAdapter<GuideBook> {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mylike_recycler, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }



        GuideBook guideBook = mDatas.get(position);
        ImageViewHolder holder = (ImageViewHolder) myholder;
        holder.mlike.setSelected(true);
        holder.mlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mlike.isSelected()){
                    holder.mlike.setSelected(false);
                }else {
                    holder.mlike.setSelected(true);
                }
            }
        });


        holder.mTitle.setText(guideBook.getTitle());
        Glide.with(mContext).load(guideBook.getImgUrl()).into(holder.mImageView);

    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private ImageView mlike;
        private TextView mTitle;


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
            mImageView = (ImageView) itemView.findViewById(R.id.item_guide_book_img);
            mlike = (ImageView) itemView.findViewById(R.id.item_guide_book_like);
            mTitle = (TextView) itemView.findViewById(R.id.item_guide_book_title);
        }
    }

}
