package home.denis.dataviewer.network.impl.retrofit;

import java.util.List;

import home.denis.dataviewer.model.Entity;
import home.denis.dataviewer.model.ShortEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitDataService {
    @GET("get_entities.php")
    Call<List<Entity>> getAllEntities();

    @GET("get_entities_list.php")
    Call<List<ShortEntity>> getEntitiesList();

    @GET("get_entity.php")
    Call<Entity> getEntity(@Query("entityId") long entityId);
}
