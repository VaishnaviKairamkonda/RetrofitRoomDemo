package e.gcs.retrofitdemo.ViewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import e.gcs.retrofitdemo.Model.Web;
import e.gcs.retrofitdemo.Repository.WebRepository;

public class WebViewModel extends AndroidViewModel {
    private WebRepository webRepository;

    private LiveData<List<Web>> getAllwebs;

    public WebViewModel(@NonNull Application application) {
        super(application);
        webRepository=new WebRepository(application);



    }
    public void retrofitCall(String count) {
           webRepository.retrofitRequest(count);
    }

    public LiveData<List<Web>> getAllwebs()
    {
        getAllwebs=webRepository.getAllWebs();
        return getAllwebs;
    }


}
