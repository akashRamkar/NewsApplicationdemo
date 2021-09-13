package com.example.dailynews;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.myNewsHolder> {
     Context context;
     ArrayList<NewsModel> newsdata=new ArrayList<>();


    public NewsAdapter(Context context, ArrayList<NewsModel> newsdata) {
        this.context = context;
        this.newsdata = newsdata;


    }

    @NonNull
    @Override
    public myNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.singlenewsview,parent,false);
        return new myNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myNewsHolder holder, int position) {
//        Picasso.get().load(newsdata.get(position).getImageUrl()).into(holder.img);
        final NewsModel item =newsdata.get(position);
        holder.author.setText(item.getAuthor());

        System.out.println("Author : "+holder.author.getText());

        holder.title.setText(item.getNewsTitle().toString());
//        holder.title.setText("this is holder title");
        System.out.println("title is : "+holder.title.getText().toString());
    }

    @Override
    public int getItemCount() {

        return newsdata.size();

    }

    public class myNewsHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView author,title;
        public myNewsHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.news_img);
            author=itemView.findViewById(R.id.news_author);
            title=itemView.findViewById(R.id.news_title);
        }
    }

}
