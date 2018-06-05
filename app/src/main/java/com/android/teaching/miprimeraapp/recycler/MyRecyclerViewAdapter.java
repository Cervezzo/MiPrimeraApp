package com.android.teaching.miprimeraapp.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.teaching.miprimeraapp.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private String[] dataSet;

    public MyRecyclerViewAdapter(String[] dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.length : 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        Log.d("ADAPTER", "MyAdapter: creating ViewAdapter");
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_holder_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            int position) {
        Log.d("ADAPTER", "MyAdapter: creating ViewAdapter" + position);
        ((MyViewHolder) holder).bind(dataSet[position]);
    }
}
