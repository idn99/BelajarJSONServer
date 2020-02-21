package com.idn99.project.belajarjsonserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.idn99.project.belajarjsonserver.kelas.Product;

import java.util.Hashtable;
import java.util.Map;

public class AddProductsActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    private EditText productId,qty,catId;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        inisialisasi();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                kirim products


                String url = "http://210.210.154.65:212/api/products";

                final String merchant ="1";

                final Map<String, String> data = new Hashtable<String, String>();
                data.put("productName",productId.getText().toString());
                data.put("productQty",qty.getText().toString());
                data.put("categoryId",catId.getText().toString());
                data.put("merchantId",merchant);


                requestQueue = Volley.newRequestQueue(AddProductsActivity.this);

//                Toast.makeText(AddProductsActivity.this, productId.getText().toString(), Toast.LENGTH_SHORT).show();

                StringRequest addProductReq = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(AddProductsActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new Hashtable<String, String>();
                        param = data;
                        return param;
                    }
                };

                requestQueue.add(addProductReq);
            }
        });

    }

    public void inisialisasi(){
        productId = findViewById(R.id.product_name);
        qty = findViewById(R.id.qty_products);
        catId = findViewById(R.id.cat_id);
        btn = findViewById(R.id.btn_kirim);
    }
}
