package com.nav.ordermanagementsystem.ui.table_view;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nav.ordermanagementsystem.R;

import java.util.List;



public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListModel> listListModels;
    private MainActivityView view;

    public ListAdapter(List<ListModel> listListModels, MainActivityView view ){
        this.listListModels = listListModels;
        this.view = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_row, parent,
                false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder listViewHolder = (ListViewHolder)holder;
        listViewHolder.bindData(listListModels.get(position));

        listViewHolder.getView().setOnLongClickListener(e -> {
            view.deleteItem(listListModels.get(position));
            return true;
        });
        listViewHolder.getView().setOnClickListener(e -> {
            view.onC1ickItem(listListModels.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return listListModels.size();
    }
}