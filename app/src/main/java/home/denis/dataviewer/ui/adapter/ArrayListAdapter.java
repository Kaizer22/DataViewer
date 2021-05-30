package home.denis.dataviewer.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import home.denis.dataviewer.R;
import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.utils.ParamTypeUtils;
import home.denis.dataviewer.ui.ArrayItemViewHolder;
import home.denis.dataviewer.ui.EntityListViewHolder;

public class ArrayListAdapter extends RecyclerView.Adapter<ArrayItemViewHolder> {
    private ParamTypeUtils arrayType;
    private List<Object> items;
    private List<Entity> childEntities;

    public ArrayListAdapter(ParamTypeUtils arrayType) {
        this.arrayType = arrayType;
        items = new LinkedList<>();
    }

    public void setItems(List<Object> items, List<Entity> childEntities) {
        this.items = items;
        this.childEntities = childEntities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArrayItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.array_item, parent, false);
        return new ArrayItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrayItemViewHolder holder, int position) {
        Object itemOnBind = items.get(position);
        holder.bind(itemOnBind, arrayType, childEntities);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
