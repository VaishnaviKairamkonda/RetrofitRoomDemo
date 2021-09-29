package e.gcs.retrofitdemo.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.Button;

import android.widget.ScrollView;
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

    public WebListAdapter(Context context, List<Web> webList) {
        this.context =  context;
        this.webList = webList;
    }
    @NonNull
    @Override
    public WebViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WebViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull WebViewHolder holder, int position) {
        final Web web=webList.get(position);
        holder.title.setText(web.getTitle());
       // holder.explan.setText(web.getExplanation());
        holder.url.setText(web.getUrl());
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(web.getUrl()));
                context.startActivity(browserIntent);
            }
        });

       // Glide.with(context)
         //       .load(actor.getImage())
           //     .into(holder.image);

        holder.btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                final ScrollView myScroll = new ScrollView(context);
                View view=LayoutInflater.from(context).inflate(R.layout.dialog_layout,null);

                TextView tvTitle=(TextView)view.findViewById(R.id.textTitle1);
                tvTitle.setTextSize(18);
                tvTitle.setText(web.getTitle());
                TextView tvUrl=(TextView)view.findViewById(R.id.textUrl1);
                tvUrl.setTextSize(14);
                tvUrl.setClickable(true);
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
                tvexplan.setTextSize(14);
                tvexplan.setMaxLines(20);
                tvexplan.setText(web.getExplanation());

               /* ImageView imageView=view.findViewById(R.id.imgView);
                String imgURL = String.valueOf(web.getMedia_type());
                Picasso.get()
                        .load(imgURL)
                        .into(imageView);
*/
                myScroll.addView(view);
                builder.setView(myScroll);
              //  builder.setView(view);

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


       // public ImageView image;

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
    public void insertdata(List<Web> insertList){
        /**
         * Insert list insert data to list
         */
        MyDiffUtilCallback diffUtilCallback = new MyDiffUtilCallback(webList,insertList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        webList.addAll(insertList);
        diffResult.dispatchUpdatesTo(this);
    }


}
