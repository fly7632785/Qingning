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

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.widget.RoundImageView;

/**
 * Created by jafir on 16/5/10.
 */
public class GuideBookRecyclerAdapter extends BaseRecyclerAdapter<GuideBook> {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guide_book_recycler, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }
        GuideBook guideBook = mDatas.get(position);
        ImageViewHolder holder = (ImageViewHolder) myholder;
        holder.mTitle.setText(guideBook.getTitle());
        holder.mTime.setText(guideBook.getTime());
        holder.mLikes.setText(guideBook.getLikes());
        holder.mAddress.setText(guideBook.getAddress());
        Glide.with(mContext).load(guideBook.getImgUrl()).into(holder.mImageView);
        new KJBitmap.Builder().imageUrl(guideBook.getAvatar()).view(holder.mPortrait).display();

    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private RoundImageView mPortrait;
        private TextView mTitle;
        private TextView mTime;
        private TextView mLikes;
        private TextView mAddress;


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
            mPortrait = (RoundImageView) itemView.findViewById(R.id.item_guide_book_avatar);
            mTime = (TextView) itemView.findViewById(R.id.item_guide_book_time);
            mTitle = (TextView) itemView.findViewById(R.id.item_guide_book_title);
            mAddress = (TextView) itemView.findViewById(R.id.item_guide_book_address);
            mLikes = (TextView) itemView.findViewById(R.id.item_guide_book_likes);
            mPortrait.setBorderThickness(6);
        }
    }

}
