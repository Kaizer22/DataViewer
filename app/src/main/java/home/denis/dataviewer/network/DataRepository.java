package home.denis.dataviewer.network;

import java.util.List;

import home.denis.dataviewer.model.Entity;
import retrofit2.Callback;

public interface DataRepository {
    void getEntityByType(Callback<List<Entity>> callback, String entityType);
    void getChildEntities(Callback<List<Entity>> callback, long parentId);
    void getEntity(Callback<Entity> callback, long entityId);
}
