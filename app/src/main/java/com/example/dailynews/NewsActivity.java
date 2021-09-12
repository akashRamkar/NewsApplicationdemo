package com.example.dailynews;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

class OkHttpSingleton{
    private static OkHttpClient client=null;
    private static Request requestobj;
    private OkHttpSingleton(){
    }
    public static synchronized OkHttpClient getHttpClient(){
        if(client==null){
            client=new OkHttpClient();
        }
        return client;
    }
}

public class NewsActivity extends AppCompatActivity implements onNewsItemClickListener {
private RecyclerView recyclerView;
private static ArrayList<NewsModel> newsdata=new ArrayList<>();
private static OkHttpClient apiClient;
private static Request request;
private static Response response;
private static String tempAuthor,tempTitle,tempImage;
private static String requestResponseString;
private static JSONObject tempJsonObject;
 NewsAdapter adapter;
private  String NEWS_URL="https://newsapi.org/v2/top-headlines/sources?apiKey=0ff8e45f66b141b78a98f8ae83959841";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        apiClient=OkHttpSingleton.getHttpClient();
        request=new Request.Builder().url(NEWS_URL).get().build();

//      recyclerView.notify();

      adapter=new NewsAdapter(getApplicationContext(),newsdata,NewsActivity.this);
      recyclerView.setAdapter(adapter);
      fetchdata();
        adapter.updateNewsList(newsdata);
        adapter.notifyDataSetChanged();



    }

    @Override
    public void onNewsUrlCLicked(String newsUrl) {

    }
    private synchronized void fetchdata(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response=apiClient.newCall(request).execute();
                    requestResponseString=response.body().string();
                    JSONObject jsonObject=new JSONObject(requestResponseString);
                    JSONArray jsonArray=jsonObject.getJSONArray("sources");
                    for(int i=1;i<jsonArray.length();i++){
                        tempJsonObject=jsonArray.getJSONObject(i);
                        tempAuthor=tempJsonObject.getString("name");
                        tempTitle=tempJsonObject.getString("description");
                        tempImage=tempJsonObject.getString("url");
                        Log.d("imageurl",tempImage);
                        NewsModel news=new NewsModel(tempImage,tempAuthor,tempTitle);
                        newsdata.add(news);

                    }


                    System.out.println("size"+newsdata.size());




                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

}



