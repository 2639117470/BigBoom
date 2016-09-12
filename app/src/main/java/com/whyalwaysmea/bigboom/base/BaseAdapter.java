package com.whyalwaysmea.bigboom.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.whyalwaysmea.bigboom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long
 * on 2016/9/12.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    protected static final int TYPE_EMPTY = 0;
    protected static final int TYPE_DEFAULT = 1;
    protected List<T> mData;
    protected Context mContext;
    protected boolean mUseAnimation;
    private int mLastPosition = -1;


    public BaseAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
    }

    public BaseAdapter(Context context, List<T> data, boolean useAnimation) {
        this.mContext = context;
        this.mData = data;
        this.mUseAnimation = useAnimation;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_EMPTY) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty, parent, false);
            return new BaseViewHolder(emptyView);
        }
        return onCreateNormalViewHolder(parent, viewType);

    }

    protected abstract BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(mUseAnimation) {
            setAnimation(holder.itemView, position);
        }
        bindData(holder, position);
    }

    protected abstract void bindData(BaseViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        if(getItemCount() == 0) {
            return TYPE_EMPTY;
        } else {
            return TYPE_DEFAULT;
        }
    }


    private void setAnimation(View view, int position) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_bottom_in);
            view.startAnimation(animation);
            mLastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(BaseViewHolder holder) {
        holder.itemView.clearAnimation();
    }

    protected void addData(List<T> data) {
        if(mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void add(int pos, T item) {
        if(mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void delete(int pos) {
        if(mData == null) {
            return ;
        }
        mData.remove(pos);
        notifyItemRemoved(pos);
    }
}
