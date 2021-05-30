package home.denis.dataviewer.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

import home.denis.dataviewer.R;
import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.utils.CommonParamUtils;
import home.denis.dataviewer.model.utils.MetadataUtils;
import home.denis.dataviewer.model.utils.ParamTypeUtils;
import home.denis.dataviewer.ui.activity.DetailActivity;

public class ArrayItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView itemIcon;
    private TextView itemName;

    private ConstraintLayout itemBackground;

    private Context context;

    public ArrayItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        itemBackground = itemView.findViewById(R.id.item_background);
        itemName = itemView.findViewById(R.id.array_item_name);
        itemIcon = itemView.findViewById(R.id.array_item_icon);
    }

    public void bind(Object item, ParamTypeUtils itemType, List<Entity> childEntities) {
        switch (itemType) {
            case TEXT:
            case NUMERIC:
                String itemText = (String) item;
                itemName.setText(itemText);
                break;
            case ID_LINK:
                String itemID= (String) item;
                Entity itemEntity = findEntityById(Long.parseLong(itemID), childEntities);

                itemName.setTextColor(Color.BLACK);
                itemName.setText(itemEntity.getTextParam(CommonParamUtils.ENTITY_NAME.toString().toLowerCase()));
                String iconUrl = itemEntity.getTextMetadata(MetadataUtils.ENTITY_IMAGE.toString().toLowerCase());
                if (iconUrl != null) {
                    Picasso.get().load(iconUrl).into(itemIcon);
                }
                itemBackground.setBackgroundColor(Color.GRAY);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemBackground.getLayoutParams();
                params.topMargin = 4;
                params.bottomMargin = 4;
                params.leftMargin = 4;
                params.rightMargin = 4;
                itemBackground.setLayoutParams(params);
                itemView.setOnClickListener(v -> {
                    Intent entityDetailsIntent = new Intent(context, DetailActivity.class);
                    entityDetailsIntent.putExtra(EntityListViewHolder.ENTITY_TO_TRANSFER,
                            new Gson().toJson(itemEntity));
                    context.startActivity(entityDetailsIntent);
                });
                break;
        }

    }

    private Entity findEntityById(long itemID, List<Entity> childEntities) {
        return childEntities.stream().filter(item -> item.getId() == itemID).findFirst().get();
    }
}
