package home.denis.dataviewer.network.impl.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    public static String BASE_URL = "http://3.u0156265.z8.ru/itmo2020/Student/K33202/Shebut/rest_api/controller/";

    public static RetrofitDataService apiInstance;

    public static synchronized RetrofitDataService getService() {
        if (apiInstance != null) return apiInstance;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInstance = retrofit.create(RetrofitDataService.class);
        return apiInstance;
    }
}
