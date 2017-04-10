package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.util.PhoneUtils;
import com.jafir.qingning.model.bean.User;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.widget.RoundImageView;

/**
 * Created by jafir on 16/5/10.
 */
public class JoinPeopleRecyclerAdapter extends BaseRecyclerAdapter<User> {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_join_people_recycler, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }
        User user = mDatas.get(position);
        ImageViewHolder holder = (ImageViewHolder) myholder;
        holder.mTitle.setText(user.getNickName());
        new KJBitmap.Builder().imageUrl(user.getHeadImgUrl()).view(holder.mPortrait).display();
        holder.mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneUtils.call(mDatas.get(position).getPhone(), mContext);
            }
        });
    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCall;
        private RoundImageView mPortrait;
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
            mPortrait = (RoundImageView) itemView.findViewById(R.id.item_user_avatar);
            mTitle = (TextView) itemView.findViewById(R.id.item_user_name);
            mCall = (ImageView) itemView.findViewById(R.id.item_user_call);
        }
    }

}
