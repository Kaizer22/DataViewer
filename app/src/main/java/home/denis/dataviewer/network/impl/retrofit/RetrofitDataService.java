package home.denis.dataviewer.network.impl.retrofit;

import java.util.List;

import home.denis.dataviewer.model.Entity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitDataService {
    @GET("get_entities_by_type.php")
    Call<List<Entity>> getEntitiesByType(@Query("type") String entityType);

    @GET("get_child_entities.php")
    Call<List<Entity>> getChildEntities(@Query("parent_id") long parentEntity);

    @GET("get_entity.php")
    Call<Entity> getEntity(@Query("entityId") long entityId);
}
