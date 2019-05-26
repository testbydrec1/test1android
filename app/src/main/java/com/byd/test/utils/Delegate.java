package com.byd.test.utils;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface Delegate<VH extends RecyclerView.ViewHolder, T> {

    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH holder, T t);
}
