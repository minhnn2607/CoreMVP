package vn.nms.core.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected BaseViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public abstract void onBind(T t);
}
