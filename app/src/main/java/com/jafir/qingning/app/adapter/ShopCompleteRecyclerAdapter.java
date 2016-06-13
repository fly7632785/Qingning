package com.jafir.qingning.app.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.ShopDetailActivity;
import com.jafir.qingning.model.bean.Shop;

import org.kymjs.kjframe.KJBitmap;

/**
 * Created by jafir on 16/5/10.
 */
public class ShopCompleteRecyclerAdapter extends BaseRecyclerAdapter<Shop> {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_shop_complete, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }
        ImageViewHolder holder = (ImageViewHolder) myholder;
        Shop shop = mDatas.get(position);
        new KJBitmap.Builder().imageUrl(shop.getAvatar()).view(holder.mAvatar).display();
        holder.mName.setText(shop.getName());
        holder.mContent.setText(shop.getIntroduce());
        holder.mRatingbar.setRating(shop.getRating());


    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mAvatar;
        private ImageView mCall;
        private TextView mName;
        private TextView mChoose;
        private TextView mGoTo;
        private RatingBar mRatingbar;
        private TextView mContent;


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
            mAvatar = (ImageView) itemView.findViewById(R.id.item_shop_avatar);
            mName = (TextView) itemView.findViewById(R.id.item_shop_name);
            mCall = (ImageView) itemView.findViewById(R.id.item_shop_call);
            mChoose = (TextView) itemView.findViewById(R.id.item_shop_choose);
            mContent = (TextView) itemView.findViewById(R.id.item_shop_content);
            mGoTo = (TextView) itemView.findViewById(R.id.item_shop_goto);
            mRatingbar = (RatingBar) itemView.findViewById(R.id.item_shop_ratingbar);

            mCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPhone();
                }
            });

            mChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            mGoTo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext,ShopDetailActivity.class));
                }
            });

        }
    }
    private void openPhone() {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("拨打电话")
                .setMessage("您要拨打" + "13982004324" + "吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:13982004324")));
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();

    }

}
