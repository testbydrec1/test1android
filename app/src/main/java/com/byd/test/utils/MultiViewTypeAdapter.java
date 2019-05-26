package com.byd.test.utils;


import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public abstract class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SparseArray<Delegate> delegateMap;
    private List<Integer> viewTypeList;
    private List<Object> itemList;

    public MultiViewTypeAdapter() {
        delegateMap = new SparseArray<>();
        viewTypeList = new ArrayList<>();
        itemList = new ArrayList<>();
    }

    protected void putDelegate(int viewType, Delegate delegate) {
        delegateMap.put(viewType, delegate);
    }

    protected void clearItemList() {
        viewTypeList.clear();
        itemList.clear();
        notifyDataSetChanged();
    }

    protected void setItemList(int viewType, List<?> items) {
        viewTypeList.clear();
        itemList.clear();
        for (int i = 0; i < items.size(); i++) {
            viewTypeList.add(viewType);
        }
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    protected void addItem(int viewType, Object item) {
        viewTypeList.add(viewType);
        itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
    }

    protected void addItems(int viewType, List<?> items) {
        for (int i = 0; i < items.size(); i++) {
            viewTypeList.add(viewType);
        }
        itemList.addAll(items);
        notifyItemRangeInserted(itemList.size() - items.size(), items.size());
    }

    protected void insertItem(int viewType, Object item, int position) {
        viewTypeList.add(position, viewType);
        itemList.add(position, item);
        notifyItemInserted(position);
    }

    protected void insertItems(int viewType, List<?> items, int position) {
        for (int i = 0; i < items.size(); i++) {
            viewTypeList.add(position + i, viewType);
        }
        itemList.addAll(position, items);
        notifyItemRangeInserted(position, items.size());
    }

    protected void replaceItem(int viewType, Object item, int position) {
        viewTypeList.set(position, viewType);
        itemList.set(position, item);
        notifyItemChanged(position);
    }

    protected void replaceItems(int viewType, List<?> items, int position) {
        for (int i = 0; i < items.size(); i++) {
            viewTypeList.set(position + i, viewType);
            itemList.set(position + i, items.get(i));
        }
        notifyItemRangeChanged(position, items.size());
    }

    protected void removeItem(int position) {
        viewTypeList.remove(position);
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    protected void removeItems(int position, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            viewTypeList.remove(position + i);
            itemList.remove(position + i);
        }
        notifyItemRangeRemoved(position, itemCount);
    }

    public int getViewType(int position) {
        return viewTypeList.get(position);
    }

    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateMap.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        delegateMap.get(viewType).onBindViewHolder(holder, itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypeList.get(position);
    }
}

