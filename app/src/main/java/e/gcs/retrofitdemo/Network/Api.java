package e.gcs.retrofitdemo.Network;

import java.util.List;

import e.gcs.retrofitdemo.Model.Web;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("apod")
    Call<List<Web>> getAllWebs(@Query("api_key") String apikey,@Query("count") String count);
}
