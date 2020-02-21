package com.idn99.project.belajarjsonserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.idn99.project.belajarjsonserver.kelas.ListProducts;
import com.idn99.project.belajarjsonserver.kelas.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Deskripsi extends AppCompatActivity {

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    private ImageView img;
    //private ArrayList<Product> product = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);

        Bundle bundle = getIntent().getExtras();
        String json = bundle.getString("data");

        Product product = new Gson().fromJson(json,Product.class);

        inisialisasi();
        String baseUrl = "http://210.210.154.65:212/storage/";
        String url = baseUrl + product.getProductImage();

        Glide.with(this).load(url).into(img);

        tv1.setText("ID Produk      :  " + String.valueOf(product.getProductId()));
        tv2.setText("Nama Produk    :  " + product.getProductNama());
        tv3.setText("Slug Produk    :  " + product.getProductSlug());
        tv4.setText("Qty Produk     :  " + String.valueOf(product.getProductQty()));
        tv5.setText("ID Merchant    :  " + String.valueOf(product.getMerchants().getMerchantId()));
        tv6.setText("Nama Merchant  :  " + product.getMerchants().getMerchantName());
        tv7.setText("Slug Merchant  :  " + product.getMerchants().getMerchantSLug());
        tv8.setText("ID Kategori    :  " + String.valueOf(product.getProductCategories().getCategoryId()));
        tv9.setText("Nama Kategori  :  " + product.getProductCategories().getCategoryName());

    }

    public void inisialisasi() {
        img = findViewById(R.id.img_des);
        tv1 = findViewById(R.id.tv_des_id);
        tv2 = findViewById(R.id.tv_des_nama);
        tv3 = findViewById(R.id.tv_des_slug);
        tv4 = findViewById(R.id.tv_des_qty);
        tv5 = findViewById(R.id.tv_des_mid);
        tv6 = findViewById(R.id.tv_des_mname);
        tv7 = findViewById(R.id.tv_des_mslug);
        tv8 = findViewById(R.id.tv_des_cid);
        tv9 = findViewById(R.id.tv_des_cname);

    }

}