package e.gcs.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import e.gcs.retrofitdemo.Adapter.WebListAdapter;
import e.gcs.retrofitdemo.Model.Web;
import e.gcs.retrofitdemo.Repository.WebRepository;
import e.gcs.retrofitdemo.ViewModel.WebViewModel;
import e.gcs.retrofitdemo.Network.Retrofit;
import e.gcs.retrofitdemo.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4;
    TextView tvview;
    private WebViewModel webViewModel;
    ActivityMainBinding activityMainBinding;
    private RecyclerView recyclerView;
    private WebListAdapter weblistAdapter;
    public String count ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();

        setContentView(view);
        btn1=activityMainBinding.btn1;
        btn2=activityMainBinding.btn2;
        btn3=activityMainBinding.btn3;
        btn4=activityMainBinding.btn4;
        tvview=activityMainBinding.emptyView;


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        recyclerView=activityMainBinding.recyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        weblistAdapter=new WebListAdapter(this);

        webViewModel=new ViewModelProvider(this).get(WebViewModel.class);

        webViewModel.getAllwebs().observe(this, new Observer<List<Web>>() {
            @Override
            public void onChanged(List<Web> webList) {
                if(webList.isEmpty()){
                    tvview.setVisibility(View.VISIBLE);
                }
                else{
                    recyclerView.setAdapter(weblistAdapter);
                    weblistAdapter.getAllWebs(webList);
                    weblistAdapter.updateList(webList);
                    tvview.setVisibility(View.INVISIBLE);
                    Log.d("main", "onChanged: "+webList);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                count="2";
                break;
            case R.id.btn3:
                count="3";
                break;
            case R.id.btn4:
                count="4";
                break;

        }
        Button buttonCicked = (Button) v;
        String count = buttonCicked.getText().toString();
        webViewModel.retrofitCall(count);

    }



}
