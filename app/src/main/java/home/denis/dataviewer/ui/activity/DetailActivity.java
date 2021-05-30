package home.denis.dataviewer.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import home.denis.dataviewer.model.Array;
import home.denis.dataviewer.model.utils.ParamTypeUtils;
import home.denis.dataviewer.network.DataRepository;
import home.denis.dataviewer.network.impl.retrofit.DataRepositoryRetrofitImpl;
import home.denis.dataviewer.ui.EntityListViewHolder;
import home.denis.dataviewer.R;
import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.Param;
import home.denis.dataviewer.model.utils.CommonParamUtils;
import home.denis.dataviewer.model.utils.MetadataUtils;
import home.denis.dataviewer.ui.adapter.ArrayListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private Entity entity;
    private List<Entity> childEntities;

    private DataRepository dataRepository;

    private ImageView entityImage;
    private TextView entityName;
    private LinearLayout paramsContainer;
    private LinearLayout arraysContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dataRepository = new DataRepositoryRetrofitImpl();

        Intent intent = getIntent();
        childEntities = new LinkedList<>();
        entity = new Gson().fromJson(
                intent.getStringExtra(EntityListViewHolder.ENTITY_TO_TRANSFER),
                Entity.class);
        Log.d("ARRAYS", String.valueOf(entity.getArrays().size()));

        entityImage = findViewById(R.id.entity_image_big);
        entityName = findViewById(R.id.entity_name_details);

        initPredefinedViews();
        refreshChildEntities();

        paramsContainer = findViewById(R.id.params_container);
        arraysContainer = findViewById(R.id.arrays_container);

        List<String> preconfiguredParams = Arrays.stream(CommonParamUtils.values())
                .map(item -> item.toString().toLowerCase())
                .collect(Collectors.toList());
        for (Param param:entity.getParams()) {
            if (!preconfiguredParams.contains(param.getName())){
                bindParam(param);
            }
        }
    }

    private void refreshChildEntities() {
        dataRepository.getChildEntities(new Callback<List<Entity>>() {
            @Override
            public void onResponse(Call<List<Entity>> call, Response<List<Entity>> response) {
                if (response.code() == 200) {
                    clearArrays();
                    childEntities = response.body();
                    for (Array array: entity.getArrays()) {
                        bindArray(array);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            String.format("Не удалось получить данные от сервера (%s)", response.code()),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<List<Entity>> call, Throwable t) {
                t.printStackTrace();
                for (Array array: entity.getArrays()) {
                    bindArray(array);
                }
            }
        }, entity.getId());
    }

    private void clearArrays() {
        arraysContainer.removeAllViews();
    }

    private void bindArray(Array array) {
        TextView arrayName = new TextView(this);
        arrayName.setText(String.format("%s:", array.getName()));
        arrayName.setTextSize(18);
        arrayName.setTypeface(arrayName.getTypeface(), Typeface.BOLD);
        arraysContainer.addView(arrayName);
        RecyclerView arrayElements = new RecyclerView(this);
        arrayElements.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        params.setMargins(4,4,4,16);
        arrayElements.setLayoutParams(params);
        ArrayListAdapter arrayAdapter = new ArrayListAdapter(
                ParamTypeUtils.valueOf(array.getArrayType().toUpperCase()));
        arrayAdapter.setItems(
                Arrays.asList(array.getValues().toArray()), childEntities);
        arrayElements.setAdapter(arrayAdapter);
        arraysContainer.addView(arrayElements);
    }

    private void initPredefinedViews() {
        Log.d("initPredefinedViews", "INIT");
        String imageURL = entity.getTextMetadata(MetadataUtils.ENTITY_IMAGE
                .toString().toLowerCase());
        if (imageURL != null) {
            Picasso.get().load(imageURL).into(entityImage);
        } else {
            entityImage.setVisibility(View.GONE);
        }

        String entityName = entity.getTextParam(CommonParamUtils.ENTITY_NAME
                .toString().toLowerCase());
        Log.d("initPredefinedViews", entityName);
        if (entityName != null) {
            this.entityName.setText(entityName);
        }
        Log.d("initPredefinedViews", String.valueOf(this.entityName.getText()));
    }

    private void bindParam(Param param) {
        TextView paramName = new TextView(this);
        paramName.setText(String.format("%s:", param.getName()));
        paramName.setTextSize(18);
        paramName.setTypeface(paramName.getTypeface(), Typeface.BOLD);
        paramsContainer.addView(paramName);
        TextView paramValue = new TextView(this);
        paramValue.setText(String.format("%s", param.getValue()));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(4,2,10,16);
        paramValue.setLayoutParams(params);

        paramsContainer.addView(paramValue);
    }
}
