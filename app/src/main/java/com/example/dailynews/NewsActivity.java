package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements onNewsItemClickListener {
private RecyclerView recyclerView;
private ArrayList<NewsModel> newsdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView=findViewById(R.id.recycler_view);

        NewsAdapter newsAdapter=new NewsAdapter(this,newsdata,this);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onNewsUrlCLicked(String newsUrl) {

    }
}