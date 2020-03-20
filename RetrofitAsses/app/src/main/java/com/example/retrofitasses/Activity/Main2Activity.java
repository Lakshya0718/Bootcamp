package com.example.retrofitasses.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.retrofitasses.Adapter.dataAdapter;
import com.example.retrofitasses.Model.Data;
import com.example.retrofitasses.Model.PostsItem;
import com.example.retrofitasses.Network.ApiClient;
import com.example.retrofitasses.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
    private Button button_load, clear_data;
    private RecyclerView data_list;
    private HttpURLConnection httpURLConnection = null;
    private URL url;
    private BufferedReader reader = null;
    private dataAdapter adapter;
    private List<PostsItem> postsItems = new ArrayList<>();
    private Button button_load2;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button_load = findViewById(R.id.load_dataButton);
        button_load2 = findViewById(R.id.load_dataButton2);
        data_list = findViewById(R.id.data_list);
        progressBar = findViewById(R.id.progressbar);
        clear_data = findViewById(R.id.clear_data);


        button_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new DataLoadTask().execute("https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json");


            }
        });

        button_load2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                adapter = new dataAdapter(Main2Activity.this, postsItems);
                LinearLayoutManager layoutManager = new LinearLayoutManager(Main2Activity.this, RecyclerView.VERTICAL, false);
                data_list.setLayoutManager(layoutManager);
                data_list.setItemAnimator(new DefaultItemAnimator());

                makeRequestForData();
                data_list.setAdapter(adapter);

            }
        });

        clear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_list.setVisibility(View.GONE);
            }
        });


    }

    private void makeRequestForData() {
        Call<Data> call = ApiClient.getDataService().getAllData();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    Data data = response.body();
                    List<PostsItem> posts = data.getPosts();
                    postsItems.addAll(posts);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("Respose", "onFailure: ", t);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public class DataLoadTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();


                InputStream inputStream = httpURLConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }
                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }

                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            adapter = new dataAdapter(Main2Activity.this, postsItems);
            LinearLayoutManager layoutManager = new LinearLayoutManager(Main2Activity.this, RecyclerView.VERTICAL, false);
            data_list.setLayoutManager(layoutManager);
            data_list.setItemAnimator(new DefaultItemAnimator());
            data_list.setAdapter(adapter);
        }


    }
}
