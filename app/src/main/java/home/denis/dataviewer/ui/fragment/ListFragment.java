package home.denis.dataviewer.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import home.denis.dataviewer.ui.adapter.EntityListAdapter;
import home.denis.dataviewer.R;
import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.utils.EntityTypeUtils;
import home.denis.dataviewer.network.DataRepository;
import home.denis.dataviewer.network.impl.retrofit.DataRepositoryRetrofitImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private EntityTypeUtils type;

    private DataRepository dataRepository;
    private RecyclerView entityList;
    private EntityListAdapter entityListAdapter;

    public ListFragment(EntityTypeUtils type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        dataRepository = new DataRepositoryRetrofitImpl();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        entityList = view.findViewById(R.id.entity_list);
        entityList.setLayoutManager(new LinearLayoutManager(requireContext()));
        entityListAdapter = new EntityListAdapter();
        entityList.setAdapter(entityListAdapter);
        refreshList();
    }

    private void refreshList() {
        dataRepository.getEntityByType(new Callback<List<Entity>>() {
            @Override
            public void onResponse(@NonNull Call<List<Entity>> call, @NonNull Response<List<Entity>> response) {
                if (response.code() == 200) {
                    entityListAdapter.setEntities(response.body());
                } else {
                    Toast.makeText(requireContext(),
                            String.format("Не удалось получить данные от сервера (%s)", response.code()),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Entity>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        }, type.toString().toLowerCase());
    }
}
