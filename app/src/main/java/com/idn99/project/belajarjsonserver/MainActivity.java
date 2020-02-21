package com.idn99.project.belajarjsonserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.idn99.project.belajarjsonserver.kelas.ListProducts;
import com.idn99.project.belajarjsonserver.kelas.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final AdapterRv adapterRv = new AdapterRv(this);
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialKomponen();
        VolleyLoad();
    }

    public void inisialKomponen(){
        recyclerView = findViewById(R.id.home_rv);
    }

    public void VolleyLoad(){
        requestQueue = Volley.newRequestQueue(this);
        String url = "http://210.210.154.65:212/api/products";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Product> data = new ArrayList<>();
                            Gson gson = new Gson();
                            ListProducts listProducts = gson.fromJson(response.toString(), ListProducts.class);
                            data.addAll(listProducts.getProducts());

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapterRv);
                            adapterRv.addData(data);

                            Toast.makeText(MainActivity.this, String.valueOf(adapterRv.getItemCount()), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Volley Error", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(req);
    }
}
