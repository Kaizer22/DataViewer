package home.denis.dataviewer.network.impl.retrofit;

import java.util.List;

import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.network.DataRepository;
import retrofit2.Callback;

public class DataRepositoryRetrofitImpl implements DataRepository {
    //TODO retorfit2 independent callbacks
    @Override
    public void getEntityByType(Callback<List<Entity>> callback, String entityType) {
        NetworkHelper.getService()
                .getEntitiesByType(entityType).enqueue(callback);
    }

    @Override
    public void getChildEntities(Callback<List<Entity>> callback, long parentId) {
        NetworkHelper.getService()
                .getChildEntities(parentId).enqueue(callback);
    }

    @Override
    public void getEntity(Callback<Entity> callback, long entityId) {
        NetworkHelper.getService()
                .getEntity(entityId).enqueue(callback);
    }





}
