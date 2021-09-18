package com.example.dailynews;


import android.content.Context;

import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;



public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.myNewsHolder> {
    Context context;
    static NewsModel item;
    ArrayList<NewsModel> newsdata = new ArrayList<>();


    public NewsAdapter(Context context, ArrayList<NewsModel> newsdata) {
        this.context = context;
        this.newsdata = newsdata;


    }

    @NonNull
    @Override
    public myNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.singlenewsview, parent, false);
        return new myNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myNewsHolder holder, int position) {
        //taking single news object out of newsarray-newsdata
        item = newsdata.get(position);
        //loading image into imageview
        Picasso.get().load(item.getImageUrl()).into(holder.img);
        //setting author text
        holder.author.setText(item.getAuthor());
        //setting news title text
        holder.title.setText(item.getNewsTitle().toString());
        holder.news_sourse.setText("source : "+item.getSource());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openting browser tab inside the application
                String url =item.getNewsLink();  //news source link

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                //launching activity
                customTabsIntent.launchUrl(context.getApplicationContext(), Uri.parse(url));
            }
        });

    }

    @Override
    public int getItemCount() {
        //returning totay size
        return newsdata.size();

    }

    public class myNewsHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView author, title,news_sourse;

        public myNewsHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.news_img);
            author = itemView.findViewById(R.id.news_author);
            title = itemView.findViewById(R.id.news_title);
            news_sourse=itemView.findViewById(R.id.news_source);
//            title.setTypeface(NewsActivity.typeface);
        }
    }


}
