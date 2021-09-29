package e.gcs.retrofitdemo.ViewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import e.gcs.retrofitdemo.Database.WebRoomDatabase;
import e.gcs.retrofitdemo.Model.Web;
import e.gcs.retrofitdemo.Repository.WebRepository;

public class WebViewModel extends AndroidViewModel {
    private WebRepository webRepository;

    private LiveData<List<Web>> getAllwebs;

  //  private MutableLiveData<List<Web>> modelMutableLiveData = new MutableLiveData<>();


    public WebViewModel(@NonNull Application application) {
        super(application);
        webRepository=new WebRepository(application);
        getAllwebs=webRepository.getAllWebs();
       // getRetrofitdata=webRepository.networkRequest();

    }
    public void retrofitCall(String count) {
        //.setValue(webRepository.networkRequest().);
        webRepository.retrofitRequest(count);
    }
    public void insert(List<Web> list)
    {
        webRepository.insert(list);
    }

    public LiveData<List<Web>> getAllwebs()
    {
        return getAllwebs;
    }


}
