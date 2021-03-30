package home.denis.dataviewer.network;

import android.view.Window;

import java.util.List;

import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.ShortEntity;
import retrofit2.Callback;

public interface DataRepository {
    void getAllEntities(Callback<List<Entity>> callback);
    void getEntity(Callback<Entity> callback, long entityId);
    void getShortEntities(Callback<List<ShortEntity>> callback);
}
