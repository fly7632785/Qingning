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

import java.util.ArrayList;
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
        holder.mc.setText(new Random().nextInt(8)+"元/h起");
        Glide.with(mContext).load(chehang.getImg()).centerCrop().into(holder.mImageView);


    }

    @Override
    public Filter getFilter() {
        return new MyFilter(this, mDatas);
    }


    class MyFilter extends Filter {

        private final ChehangRecyclerAdapter adapter;

        private final ArrayList<Chehang> originalList;

        private final ArrayList<Chehang> filteredList;

        private MyFilter(ChehangRecyclerAdapter adapter, ArrayList<Chehang> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new ArrayList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for (final Chehang chehang : originalList) {
                    if (chehang.getName().contains(filterPattern)) {
                        filteredList.add(chehang);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
//            adapter.mDatas.clear();
//            adapter.mDatas.addAll((ArrayList<Chehang>) results.values);
//            adapter.notifyDataSetChanged();
            adapter.updateData((ArrayList<Chehang>) results.values);
        }
    }


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
