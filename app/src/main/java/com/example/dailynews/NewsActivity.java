package com.example.dailynews;

import static com.example.dailynews.NewsAdapter.item;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Typeface;
import android.net.Uri;
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

public class NewsActivity extends AppCompatActivity implements onNewsItemClickListener{
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
        //setting up action bar title
        setTitle("Quick news");
        //setting up font typeface
         typeface=Typeface.createFromAsset(getAssets(),"fonts/NexaBold.ttf");

        recyclerView=findViewById(R.id.recycler_view);
        //layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //setting layout manager to recyclerview
        recyclerView.setLayoutManager(layoutManager);

      adapter=new NewsAdapter(getApplicationContext(),newsdata,this);
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

    @Override
    public void onNewsImageClicked(int Position) {
        //openting browser tab inside the application
                String url =newsdata.get(Position).getNewsLink();  //news source link

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                //launching activity
                customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}



