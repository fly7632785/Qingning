package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.Comment;

import org.kymjs.kjframe.KJBitmap;

/**
 * Created by jafir on 16/5/10.
 */
public class CommentRecyclerAdapter extends BaseRecyclerAdapter<Comment> {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = mInflater.inflate(R.layout.item_comment_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = mInflater.inflate(R.layout.item_comment, parent, false);
            return new ImageViewHolder(view);
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }
        if (myholder instanceof HeaderViewHolder) {
            HeaderViewHolder holder = (HeaderViewHolder) myholder;
            holder.mTotal.setText(mDatas.size()+"条评论");
        }else if(myholder instanceof  ImageViewHolder){
            ImageViewHolder holder = (ImageViewHolder) myholder;
            Comment comment = mDatas.get(position-1);
//            Glide.with(mContext).load(comment.getImgUrl()).into(holder.mImageView);
            new KJBitmap.Builder().imageUrl(comment.getImgUrl()).view(holder.mImageView).display();
            holder.mName.setText(comment.getName());
            holder.mTime.setText(comment.getTime());
            holder.mContent.setText(comment.getContent());
        }
    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mName;
        private TextView mTime;
        private TextView mContent;


        public ImageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null && mDatas.size() != 0) {
                        mOnItemClickListener.onItemClick(v, getAdapterPosition()-1 % mDatas.size());
                    }
                }
            });
            mImageView = (ImageView) itemView.findViewById(R.id.item_comment_img);
            mName = (TextView) itemView.findViewById(R.id.item_comment_name);
            mTime = (TextView) itemView.findViewById(R.id.item_comment_time);
            mContent = (TextView) itemView.findViewById(R.id.item_comment_content);
        }
    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mTotal;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            mTotal = (TextView) itemView.findViewById(R.id.item_comment_total);


        }
    }
}
