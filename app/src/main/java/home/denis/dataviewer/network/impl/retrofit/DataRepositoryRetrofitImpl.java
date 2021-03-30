package home.denis.dataviewer.network.impl.retrofit;

import androidx.recyclerview.widget.SortedList;

import java.util.List;

import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.ShortEntity;
import home.denis.dataviewer.network.DataRepository;
import retrofit2.Callback;

public class DataRepositoryRetrofitImpl implements DataRepository {

    //TODO retorfit2 independent callbacks
    @Override
    public void getAllEntities(Callback<List<Entity>> callback) {
        NetworkHelper.getService()
                .getAllEntities().enqueue(callback);
    }

    @Override
    public void getEntity(Callback<Entity> callback, long entityId) {
        NetworkHelper.getService()
                .getEntity(entityId).enqueue(callback);
    }

    @Override
    public void getShortEntities(Callback<List<ShortEntity>> callback) {
        NetworkHelper.getService()
                .getEntitiesList().enqueue(callback);

    }


}
