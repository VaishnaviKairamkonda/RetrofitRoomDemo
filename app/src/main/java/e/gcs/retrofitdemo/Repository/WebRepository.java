package e.gcs.retrofitdemo.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import e.gcs.retrofitdemo.Dao.WebDao;
import e.gcs.retrofitdemo.Database.WebRoomDatabase;
import e.gcs.retrofitdemo.MainActivity;
import e.gcs.retrofitdemo.Model.Web;
import e.gcs.retrofitdemo.Network.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebRepository  {

    public String apikey="goLj8jNbuSbNDt3IRwbLGyModq3yUWPKob5zzao7";
    private LiveData<List<Web>> getAllWebs;

    private WebRoomDatabase webRoomDatabase;

    public WebRepository(Application application)
    {
        webRoomDatabase=WebRoomDatabase.getInstance(application);
        getAllWebs=webRoomDatabase.webDao().getAllWebs();
    }

    public void insert(List<Web> webList){
        new InsertAsynTask(webRoomDatabase).execute(webList);
    }

    public LiveData<List<Web>> getAllWebs()
    {
        return getAllWebs;
    }

    static class InsertAsynTask extends AsyncTask<List<Web>,Void,Void>{
        private WebDao webDao;
        InsertAsynTask(WebRoomDatabase webRoomDatabase)
        {
            webDao=webRoomDatabase.webDao();
        }
        @Override
        protected Void doInBackground(List<Web>... lists) {
            webDao.insert(lists[0]);
            return null;
        }
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
                }
            }

            @Override
            public void onFailure(Call<List<Web>> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
