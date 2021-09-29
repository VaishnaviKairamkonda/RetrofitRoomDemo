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

    private RecyclerView recyclerView;
    List<Web> webList;
    private WebRepository webRespository;
    private WebListAdapter weblistAdapter;
   // String apikey="goLj8jNbuSbNDt3IRwbLGyModq3yUWPKob5zzao7";
    public String count ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        tvview=(TextView) findViewById(R.id.tvview);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        webRespository=new WebRepository(getApplication());
        webList=new ArrayList<>();
        weblistAdapter=new WebListAdapter(this,webList);

        webViewModel=new ViewModelProvider(this).get(WebViewModel.class);

        webViewModel.getAllwebs().observe(this, new Observer<List<Web>>() {
            @Override
            public void onChanged(List<Web> webList) {
                recyclerView.setAdapter(weblistAdapter);
                weblistAdapter.getAllWebs(webList);
               // weblistAdapter.notifyDataSetChanged();
                weblistAdapter.updateList(webList);
                Log.d("main", "onChanged: "+webList);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                count="1";
                tvview.setVisibility(View.INVISIBLE);
                webViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "One Record Successfully inserted", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn2:
                count="2";
                tvview.setVisibility(View.INVISIBLE);
                webViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "Two Records Successfully inserted", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn3:
                count="3";
                tvview.setVisibility(View.INVISIBLE);
                webViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "Three Records Successfully inserted", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn4:
                count="4";
                tvview.setVisibility(View.INVISIBLE);
                webViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "Four Records Successfully inserted", Toast.LENGTH_SHORT).show();

                break;


        }

    }



}
