package com.jafir.qingning.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.Chehang;

import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jafir on 16/5/10.
 */
public class ChehangRecyclerAdapter extends BaseRecyclerAdapter implements Filterable {


    @Override
    public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_chehang_recycler, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void bindMyViewHolder(RecyclerView.ViewHolder myholder, int position) {
        if (mDatas.isEmpty()) {
            return;
        }
        Chehang chehang = (Chehang) mDatas.get(position);
        ImageViewHolder holder = (ImageViewHolder) myholder;
        holder.mDistance.setText(chehang.getDistance());
        holder.mZuci.setText(chehang.getZuci());
        holder.mName.setText(chehang.getName());
        holder.mc.setText(new Random().nextInt(8) + "元/h起");
        Glide.with(mContext).load(chehang.getImg()).centerCrop().into(holder.mImageView);


    }

    MyFilter filter = new MyFilter();

    @Override
    public Filter getFilter() {
        return filter;
    }


    class MyFilter extends Filter {
        private ArrayList<Chehang> mOriginalItems;

        private MyFilter() {
            super();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            KJLoger.debug("performFiltering...." + constraint);
            final FilterResults results = new FilterResults();
            //如果为空就出事话 以addall的方式 而不是 直接指向
            if (mOriginalItems == null) {
                mOriginalItems = new ArrayList<>(mDatas);
            }
            //如果为空的话，就把结果集设置为 之前的数据源
            if (constraint == null || constraint.length() == 0) {
                results.values = mOriginalItems;
                results.count = mOriginalItems.size();
                //our filter was cleared we can now forget the old OriginalItems
                mOriginalItems = null;
            } else {
                //如果不为空就看那些符合 然后加到filterItems里面 返回结果集
                final String filterPattern = constraint.toString().toLowerCase().trim();
                List<Chehang> filteredItems = new ArrayList<>();


                for (final Chehang chehang : mOriginalItems) {
                    if (chehang.getName().contains(filterPattern)) {
                        filteredItems.add(chehang);
                    }
                }

                results.values = filteredItems;
                results.count = filteredItems.size();


            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //更新数据结果
            updateData((ArrayList<Chehang>) results.values);
        }
    }
//    class MyFilter extends Filter {
//
//
//        private final ArrayList<Chehang> filteredList;
//
//        private MyFilter() {
//            super();
//            this.filteredList = new ArrayList<>();
//        }
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//
//            KJLoger.debug("performFiltering...." + constraint);
//            final FilterResults results = new FilterResults();
//
//            if (constraint.length() == 0) {
//                filteredList.addAll(originalList);
//            } else {
//                final String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (final Chehang chehang : originalList) {
//                    if (chehang.getName().contains(filterPattern)) {
//                        filteredList.add(chehang);
//                    }
//                }
//            }
//            results.values = filteredList;
//            results.count = filteredList.size();
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            updateData((ArrayList<Chehang>) results.values);
//        }
//    }


    private class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mName;
        private TextView mDistance;
        //        private TextView mDesc;
        private TextView mZuci;
        private TextView mc;


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
            mImageView = (ImageView) itemView.findViewById(R.id.item_chehang_img);
            mName = (TextView) itemView.findViewById(R.id.item_chehang_name);
//            mDesc = (TextView) itemView.findViewById(R.id.item_chehang_desc);
            mZuci = (TextView) itemView.findViewById(R.id.item_chehang_zuci);
            mDistance = (TextView) itemView.findViewById(R.id.item_chehang_distance);
            mc = (TextView) itemView.findViewById(R.id.item_chehang_least_price);
        }
    }

}
