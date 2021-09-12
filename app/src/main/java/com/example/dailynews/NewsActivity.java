package com.example.dailynews;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;


import java.util.ArrayList;



public class NewsActivity extends AppCompatActivity implements onNewsItemClickListener {
private RecyclerView recyclerView;
private static ArrayList<NewsModel> newsdata=new ArrayList<>();

private static String tempAuthor,tempTitle,tempImage;
private  String NEWS_URL="https://newsapi.org/v2/top-headlines/sources?apiKey=0ff8e45f66b141b78a98f8ae83959841";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));





        NewsAdapter newsAdapter=new NewsAdapter(this,newsdata,this);

        recyclerView.setAdapter(newsAdapter);

    }

    @Override
    public void onNewsUrlCLicked(String newsUrl) {

    }
}



/*
 JSONObject newsobj=newsJsonArray.getJSONObject(i);
                tempTitle=newsobj.getString("description");
                tempImage=newsobj.getString("url");
                tempAuthor=newsobj.getString("name");
 */