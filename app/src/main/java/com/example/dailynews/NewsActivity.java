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

public class NewsActivity extends AppCompatActivity{
private RecyclerView recyclerView;
private static ArrayList<NewsModel> newsdata=new ArrayList<>();


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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

      adapter=new NewsAdapter(getApplicationContext(),newsdata);
      recyclerView.setAdapter(adapter);
      fetchdata();




    }

    private  void fetchdata(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(NEWS_URL)
                .get()
                .build();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response=client.newCall(request).execute();
                    requestResponseString=response.body().string();
                    Log.d("stringres",requestResponseString);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //
                            try {
                                JSONObject jsonObject=new JSONObject(requestResponseString);
                                JSONArray jsonArray=jsonObject.getJSONArray("sources");
                                for(int i=1;i<jsonArray.length();i++){
                                    tempJsonObject=jsonArray.getJSONObject(i);
                                    tempAuthor=tempJsonObject.getString("name");
                                    System.out.println(tempAuthor);
                                    tempTitle=tempJsonObject.getString("description");
                                    tempImage=tempJsonObject.getString("url");
//                                    NewsModel news=new NewsModel(tempImage,tempAuthor,tempTitle);
//                                    newsdata.add(new NewsModel(tempImage,tempAuthor,tempTitle));
                                    adapter.notifyDataSetChanged();

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });



                    System.out.println("size"+newsdata.size());




                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

}



