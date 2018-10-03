package vn.nms.core.base;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private List<T> mData = new ArrayList<>();
    private AdapterListener adapterListener;
    private static final int LOAD_MORE_TYPE = 0;
    private static final int ITEM_TYPE = 1;
    private RecyclerView recyclerView;
    private final int visibleThreshold = 2;

    public BaseAdapter() {
    }

    public BaseAdapter(AdapterListener listener) {
        this.adapterListener = listener;
    }

    public BaseAdapter(List<T> data, AdapterListener listener) {
        this.mData = data;
        this.adapterListener = listener;
    }


    public BaseAdapter(AdapterListener listener, RecyclerView recyclerView
            , SwipeRefreshLayout swipeRefreshLayout) {
        this.recyclerView = recyclerView;
        this.adapterListener = listener;
        final LinearLayoutManager linearLayoutManager =
                (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 :
                                recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (totalItemCount > 0 && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (adapterListener != null) {
                        adapterListener.onLoadMore();
                    }
                }
            }
        });
    }

    @NonNull
    public abstract BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData == null || mData.isEmpty() ? 0 : mData.size();
    }

    public T getItemAtPosition(int position) {
        return mData == null
                || position < 0
                || mData.size() == 0
                || mData.size() < position
                ? null : mData.get(position);
    }

    public void resetData() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
    }

    public void addAll(List<T> items) {
        mData.addAll(items);
        notifyItemRangeChanged(mData.size() - 1, items.size());
    }

    public void addData(List<T> items) {
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void setData(List<T> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addFirst(T item) {
        mData.add(0, item);
        notifyItemInserted(0);
    }

    public interface AdapterListener<T> {
        void onOpenItem(T item);

        void onLoadMore();
    }

    @Override
    public int getItemViewType(int position) {
        T item = getItemAtPosition(position);
        return item == null ? LOAD_MORE_TYPE : ITEM_TYPE;
    }

    public void hideFooter() {
        if (mData.size() > 0 && mData.get(mData.size() - 1) == null) {
            if (mData.get(mData.size() - 1) == null) {
                mData.remove(mData.size() - 1);
                notifyItemRemoved(mData.size() - 1);
            }
        }
    }

    public void showFooter() {
        mData.add(null);
        notifyItemInserted(mData.size() - 1);
    }

    public AdapterListener getListener() {
        return adapterListener;
    }

    public void unregister() {
        this.adapterListener = null;
    }

    public class LoadingViewHolder extends BaseViewHolder {
        public LoadingViewHolder(ViewGroup parent, int itemLayoutId) {
            super(parent, itemLayoutId);
        }

        @Override
        public void onBind(Object o) {

        }
    }
}