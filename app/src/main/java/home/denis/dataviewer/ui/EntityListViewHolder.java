package home.denis.dataviewer.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import home.denis.dataviewer.R;
import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.utils.CommonParamUtils;
import home.denis.dataviewer.model.utils.MetadataUtils;
import home.denis.dataviewer.ui.activity.DetailActivity;


public class EntityListViewHolder extends RecyclerView.ViewHolder {
    public static final String ENTITY_TO_TRANSFER = "entity_to_transfer";


    private TextView entityName;
    private ImageView entityImage;

    private LinearLayout paramsContainer;
    private LinearLayout arraysContainer;

    private Context context;
    public EntityListViewHolder(@NonNull View itemView) {
        super(itemView);
        entityName = itemView.findViewById(R.id.entity_name);
        entityImage = itemView.findViewById(R.id.entity_image);
        context = itemView.getContext();

    }



    public void bind(Entity entity) {
        entityName.setText(entity.getTextParam(
                CommonParamUtils.ENTITY_NAME.toString().toLowerCase()));

        String entityImageURL = entity
                .getTextMetadata(MetadataUtils.ENTITY_IMAGE.toString().toLowerCase());
        if (entityImageURL != null) {
            Picasso.get().load(entityImageURL).into(entityImage);
        }



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entityDetailsIntent = new Intent(context, DetailActivity.class);
                entityDetailsIntent.putExtra(ENTITY_TO_TRANSFER,
                        new Gson().toJson(entity));
                context.startActivity(entityDetailsIntent);
            }
        });
    }
}
