package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity{
private RecyclerView recyclerView;
private static ArrayList<NewsModel> newsdata=new ArrayList<>();


private static String tempAuthor,tempTitle,tempImage,news_src,news_link;
private static String requestResponseString;
private static JSONObject tempJsonObject;
 NewsAdapter adapter;
   static Typeface typeface;
private  String NEWS_URL="https://saurav.tech/NewsAPI/top-headlines/category/science/in.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        //setting up font typeface
         typeface=Typeface.createFromAsset(getAssets(),"fonts/NexaBold.ttf");

        recyclerView=findViewById(R.id.recycler_view);
        //layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //setting layout manager to recyclerview
        recyclerView.setLayoutManager(layoutManager);

      adapter=new NewsAdapter(getApplicationContext(),newsdata);
      recyclerView.setAdapter(adapter);
      //fetching news data from server and storing into news-model array
      fetchdata();





    }

    private  void fetchdata(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(NEWS_URL)
                .method("GET",null)
                .get()
                .build();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response=client.newCall(request).execute();
                    requestResponseString=response.body().string();

                    JSONObject jsonObject = new JSONObject(requestResponseString);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        tempJsonObject = jsonArray.getJSONObject(i);
                        news_src="null";
                        news_link=tempJsonObject.getString("url");
                        tempAuthor = tempJsonObject.getString("author");
                        tempTitle = tempJsonObject.getString("title");
                        tempImage = tempJsonObject.getString("urlToImage");
                        NewsModel news = new NewsModel(tempImage, tempAuthor, tempTitle,news_src,news_link);
                        //adding news-model object into news-model array
                        newsdata.add(news);

                    }

                    //notifying main thread to update the data from invoking adapter methods
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

}



