package com.jafir.qingning.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jafir on 16/5/10.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {


    /**
     * 自己维护一个list
     */
    protected ArrayList<T> mDatas = new ArrayList<T>();
    protected Context mContext;

    protected LayoutInflater mInflater;


    public void setData(ArrayList<T> list) {
        this.mDatas.addAll(list);
    }

    public ArrayList<T> getmDatas() {
        if (mDatas == null) {
            return null;
        }
        return mDatas;
    }

    public void updateData(ArrayList<T> list) {
        mDatas = new ArrayList<>();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mContext = parent.getContext();
            mInflater = LayoutInflater.from(mContext);
        }
        return createMyViewHolder(parent, viewType);
    }


    public abstract RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType);

    public abstract void bindMyViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myholder, int position) {
        bindMyViewHolder(myholder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void addData(List<T> data) {
        if (mDatas != null && data != null && !data.isEmpty()) {
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addItem(T obj) {
        if (mDatas != null) {
            mDatas.add(obj);
        }
        notifyDataSetChanged();
    }

    public void addItem(int pos, T obj) {
        if (mDatas != null) {
            mDatas.add(pos, obj);
        }
        notifyItemChanged(pos);
    }

    public void removeItem(Object obj) {
        mDatas.remove(obj);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }


    protected OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


}
