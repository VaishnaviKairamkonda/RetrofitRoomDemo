package e.gcs.retrofitdemo.Repository;

import android.app.Application;
import android.content.Context;

import android.util.Log;
import android.widget.Toast;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import e.gcs.retrofitdemo.Database.WebRoomDatabase;
import e.gcs.retrofitdemo.Model.Web;
import e.gcs.retrofitdemo.Network.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebRepository  {

    public String apikey="goLj8jNbuSbNDt3IRwbLGyModq3yUWPKob5zzao7";
    private LiveData<List<Web>> getAllWebs;
    private WebRoomDatabase webRoomDatabase;
    public Context context;
    private MutableLiveData<String> toastMessageObserver = new MutableLiveData();
    public WebRepository(Application application)
    {
        webRoomDatabase=WebRoomDatabase.getInstance(application);

    }

    public void insert(List<Web> webList){
        getListData(webList);
    }

    public LiveData<List<Web>> getAllWebs() {
        getAllWebs=webRoomDatabase.webDao().getAllWebs();
        return getAllWebs;
    }
    public void getListData(final List<Web> lists) {
        webRoomDatabase.executor.execute(new Runnable() {
            @Override
            public void run() {
                webRoomDatabase.webDao().insert(lists);
                }
        });
    }

    public void retrofitRequest(String count) {

        Retrofit retrofit=new Retrofit();
        Call<List<Web>> call=retrofit.api.getAllWebs(apikey,count);
        call.enqueue(new Callback<List<Web>>() {
            @Override
            public void onResponse(Call<List<Web>> call, Response<List<Web>> response) {
                if(response.isSuccessful())
                {
                    insert(response.body());
                    Log.d("main", "onResponse: "+response.body());

                    //toast(context,"Data Inserted Successfully");

                    toastMessageObserver.setValue("Data Inserted Successfully"+response.message());
                }

                else {
                    response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Web>> call, Throwable t) {
                Toast.makeText(context, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
