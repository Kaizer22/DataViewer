package home.denis.dataviewer.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import home.denis.dataviewer.ui.EntityListViewHolder;
import home.denis.dataviewer.R;
import home.denis.dataviewer.model.Entity;

public class EntityListAdapter extends RecyclerView.Adapter<EntityListViewHolder> {
    private List<Entity> entities;

    public EntityListAdapter() {
        this.entities = new LinkedList<>();
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EntityListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entity_item, parent, false);
        return new EntityListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityListViewHolder holder, int position) {
        Entity entityOnBind = entities.get(position);
        holder.bind(entityOnBind);

    }


    @Override
    public int getItemCount() {
        return entities.size();
    }
}
