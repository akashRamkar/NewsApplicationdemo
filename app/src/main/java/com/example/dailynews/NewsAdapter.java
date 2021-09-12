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
    private Context context;
    private ArrayList<NewsModel> newsdata;
    private onNewsItemClickListener newsListener;

    public NewsAdapter(Context context, ArrayList<NewsModel> newsdata, onNewsItemClickListener newsListener) {
        this.context = context;
        this.newsdata = newsdata;
        this.newsListener = newsListener;
    }

    @NonNull
    @Override
    public myNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.singlenewsview,parent,false);
        myNewsHolder myNewsHolder=new myNewsHolder(view);
        return myNewsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myNewsHolder holder, int position) {
//        Picasso.get().load(newsdata.get(position).getImageUrl()).into(holder.img);
        NewsModel item =newsdata.get(position);
        holder.author.setText(item.getAuthor());
        System.out.println(item.getAuthor());
        Log.d(TAG, holder.author.getText().toString());
        holder.title.setText(item.getNewsTitle());
    }

    @Override
    public int getItemCount() {
        return newsdata.size();
//        return 1;
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
