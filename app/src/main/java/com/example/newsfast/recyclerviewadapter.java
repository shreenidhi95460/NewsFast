package com.example.newsfast;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.viewholder> {
    Context context;
    ArrayList<news> newsList;
    public recyclerviewadapter(Context context,ArrayList<news> newsList){
        this.context=context;
        this.newsList=newsList;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(context).inflate(R.layout.recyclerviewmain,parent,false);
        viewholder vh=new viewholder(view);
           return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
      try{
       holder.title.setText(newsList.get(position).getTitle());
       holder.description.setText(newsList.get(position).getDescription());
          Glide.with(context).load(newsList.get(position).urlimage).into(holder.urlimage);

    }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,description;
        ImageView urlimage;
        LinearLayout layout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            urlimage=itemView.findViewById(R.id.urlimage);
        layout=itemView.findViewById(R.id.linearLayout);
        layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos=this.getAdapterPosition();
            String url=newsList.get(pos).url;
            customonclick(url);
        }
    }
    public void customonclick(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
       customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
