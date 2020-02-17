package com.idn99.project.belajarjsonserver;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.idn99.project.belajarjsonserver.kelas.Merchant;
import com.idn99.project.belajarjsonserver.kelas.Product;
import com.idn99.project.belajarjsonserver.kelas.ProductCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AsyncTaskData extends AsyncTask<InputStream,Void,ArrayList<Product>> {

    WeakReference<AdapterRv> adapterRv;
    Context context;

    public AsyncTaskData(Context context, AdapterRv adapterRv){
        this.context = context;
        this.adapterRv = new WeakReference<>(adapterRv);
    }

    @Override
    protected ArrayList<Product> doInBackground(InputStream... inputStreams) {

        ArrayList<Product> products = null;
        String json = loadJsonDataFromRaw(inputStreams[0]);
        products = deserialisasiJSON(json);

        return products;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        AdapterRv adapternya = adapterRv.get();
        adapternya.addData(products);
        adapternya.notifyDataSetChanged();
    }

    public String loadJsonDataFromRaw(InputStream isParam){
        String json = null;
        try{
            InputStream is = isParam;
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            String eachLine;
            while ((eachLine = reader.readLine()) != null){
                sb.append(eachLine);
            }

            json = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public ArrayList<Product> deserialisasiJSON(String jsonParam){
        ArrayList<Product> data = new ArrayList<>();
        try {
            JSONObject jsonAwal = new JSONObject(jsonParam);
            JSONArray jsonData = jsonAwal.getJSONArray("data");

            for (int i=0; i < jsonData.length(); i++) {
                JSONObject jsonObject = jsonData.getJSONObject(i);

                int pId = jsonObject.getInt("productId");
                String pName = jsonObject.getString("productName");
                String pSlug = jsonObject.getString("productSlug");
                int pQty = jsonObject.getInt("productQty");
                String pImage = jsonObject.getString("productImage");

//                final int img = context.getResources().getIdentifier(pImage, "drawable", context.getPackageName());

                // merchant

                ArrayList<Merchant> merchants = new ArrayList<>();
                JSONObject jsonMerchant = jsonObject.getJSONObject("merchant");
                int mId = jsonMerchant.getInt("merchantId");
                String mName = jsonMerchant.getString("merchantName");
                String mSlug = jsonMerchant.getString("merchantSlug");

                Merchant merchant = new Merchant(mId,mName,mSlug);
                merchants.add(merchant);

                // category

                ArrayList<ProductCategory> productCategories = new ArrayList<>();
                JSONObject jsonCategory = jsonObject.getJSONObject("category");
                int cId = jsonCategory.getInt("categoryId");
                String cName = jsonCategory.getString("categoryName");

                ProductCategory productCategory = new ProductCategory(cId,cName);
                productCategories.add(productCategory);

                Product product = new Product(pId,pName,pSlug,pQty,pImage,merchant,productCategory);
                data.add(product);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
}
