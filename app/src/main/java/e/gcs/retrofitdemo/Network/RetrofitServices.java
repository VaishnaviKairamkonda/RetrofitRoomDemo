package e.gcs.retrofitdemo.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {
    private  static RetrofitServices retrofitServices;
    private static final String BASE_URL = "https://api.nasa.gov/planetary/";
    private Retrofit retrofit;

    private RetrofitServices(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static RetrofitServices getInstance(){
        if(retrofitServices == null){
            retrofitServices = new RetrofitServices();
        }
        return retrofitServices;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
