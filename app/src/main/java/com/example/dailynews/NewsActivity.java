package com.example.dailynews;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

class volleySingleton{
    private static RequestQueue queue=null;
    private volleySingleton(){
    }
    public static synchronized RequestQueue getVolleyRequestQueue(Context context){
            if(queue==null){
                queue=Volley.newRequestQueue(context);
            }
            return queue;
    }
}
public class NewsActivity extends AppCompatActivity implements onNewsItemClickListener {
private RecyclerView recyclerView;
private static ArrayList<NewsModel> newsdata=new ArrayList<>();
private RequestQueue requestQueue;
private JsonObjectRequest objectRequest;
private static String tempAuthor,tempTitle,tempImage;
private  String NEWS_URL="https://newsapi.org/v2/top-headlines/sources?apiKey=0ff8e45f66b141b78a98f8ae83959841";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        requestQueue=volleySingleton.getVolleyRequestQueue(getApplicationContext());
        objectRequest=new JsonObjectRequest(Request.Method.GET,
         NEWS_URL, null,
         new Response.Listener<JSONObject>() {
     @Override
     public void onResponse(JSONObject response) {
         try {
//             Log.d("RES", "onResponse: succeed");
             JSONArray newsJsonArray = response.getJSONArray    ("sources");
            for (int i=0;i<newsJsonArray.length();i++){
////                JSONObject newsobj= (JSONObject) newsJsonArray.get(i);
                JSONObject newsobj=newsJsonArray.getJSONObject(i);
                tempTitle=newsobj.getString("description");
                tempImage=newsobj.getString("url");
                tempAuthor=newsobj.getString("name");
         NewsModel obj=new NewsModel(tempImage,tempAuthor,tempTitle);
         newsdata.add(obj);
//             tempImage = "kjjflksdmflm";
//             tempAuthor = "hwewkfosdkfkdf";
//             tempTitle = "and";
         }

         Toast.makeText(NewsActivity.this, tempTitle, Toast.LENGTH_SHORT).show();
            }
          catch (JSONException e) {
              Toast.makeText(getApplicationContext(), "exception", Toast.LENGTH_SHORT).show();
             e.printStackTrace();
         }

     }
 }, new Response.ErrorListener() {
     @Override
     public void onErrorResponse(VolleyError error) {
        error.printStackTrace();

         Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
     }
 });

//         requestQueue.add(objectRequest);
      requestQueue.add(objectRequest);

        NewsAdapter newsAdapter=new NewsAdapter(this,newsdata,this);
//        recyclerView.notifyAll();
        recyclerView.setAdapter(newsAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onNewsUrlCLicked(String newsUrl) {

    }
}


