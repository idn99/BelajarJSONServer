package com.idn99.project.belajarjsonserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.idn99.project.belajarjsonserver.kelas.ListProducts;
import com.idn99.project.belajarjsonserver.kelas.Mahasiswa;
import com.idn99.project.belajarjsonserver.kelas.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityGson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        Gson jsonConverter = new Gson();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Mahasiswa mhs = new Mahasiswa("Eren","Yeager",19,"Teknik Informatika");

//      serialisasi
        String json = gson.toJson(mhs);
        Toast.makeText(this, json, Toast.LENGTH_SHORT).show();

        // deserialisasi

//        String json = "{\"jurusan\":\"Teknik Informatika\",\"nama_belakang\":\"Yeager\",\"nama_depan\":\"Eren\",\"umur\":18}";
//        Mahasiswa mhs1 = jsonConverter.fromJson(json, Mahasiswa.class);


        //belajar volley
        RequestQueue obj = Volley.newRequestQueue(getApplicationContext());

        String url = "http://192.168.6.221:81/api/products";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //handle respons

                        ArrayList<Product> listProducts = new ArrayList<>();
                        try {
                            Gson gson = new Gson();
                            ListProducts list = gson.fromJson(response.getJSONObject("data").toString(), ListProducts.class);
                            listProducts.addAll(list.getProducts());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityGson.this, "Volley Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
