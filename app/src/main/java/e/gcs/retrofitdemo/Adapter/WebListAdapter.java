package e.gcs.retrofitdemo.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import java.util.List;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import e.gcs.retrofitdemo.Model.Web;
import e.gcs.retrofitdemo.MyDiffUtilCallback;
import e.gcs.retrofitdemo.R;

public class WebListAdapter  extends RecyclerView.Adapter<WebListAdapter.WebViewHolder>{

    private Context context;
    List<Web> webList;

    public WebListAdapter(Context context) {

        this.context =  context;

    }
    @NonNull
    @Override
    public WebViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout,parent,false);
        final WebViewHolder webViewHolder =new WebViewHolder(v);
        final Web web=webList.get(viewType);
        webViewHolder.btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                View view=LayoutInflater.from(context).inflate(R.layout.dialog_layout,null);

                TextView tvTitle=(TextView)view.findViewById(R.id.textTitle1);
                tvTitle.setText(web.getTitle());
                TextView tvUrl=(TextView)view.findViewById(R.id.textUrl1);
                tvUrl.setText(web.getUrl());
                tvUrl.setMovementMethod(LinkMovementMethod.getInstance());
                tvUrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                        browserIntent.setData(Uri.parse(web.getUrl()));
                        context.startActivity(browserIntent);
                    }
                });
                TextView tvexplan=(TextView)view.findViewById(R.id.textexplanation);
                tvexplan.setText(web.getExplanation());
                tvexplan.setMovementMethod(new ScrollingMovementMethod());

                builder.setView(view);

                builder.setNegativeButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog alertDialog=builder.create();
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.setLayout(RecyclerView.LayoutParams.FILL_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        
            }
        });
        return webViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull WebViewHolder holder, int position) {
        final Web web=webList.get(position);
        holder.title.setText(web.getTitle());
        holder.url.setText(web.getUrl());
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(web.getUrl()));
                context.startActivity(browserIntent);
            }
        });
    }

    public void getAllWebs(List<Web> webList)
    {
        this.webList=webList;
    }

    @Override
    public int getItemCount() {
        return webList.size();
    }

    public class WebViewHolder extends RecyclerView.ViewHolder{
        public TextView title,btnmore,url;

        public WebViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textTitle);
            url=itemView.findViewById(R.id.textUrl);
            btnmore=itemView.findViewById(R.id.btnmore);

        }
    }
    public void updateList(List<Web>  newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallback(this.webList, newList));
        diffResult.dispatchUpdatesTo(this);
    }
}

