package com.idn99.project.belajarjsonserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.idn99.project.belajarjsonserver.kelas.Mahasiswa;

public class ActivityGson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        Gson jsonConverter = new Gson();

        Mahasiswa mhs = new Mahasiswa("Eren","Yeager",19,"Teknik Informatika");

        // serialisasi
//        String json = jsonConverter.toJson(mhs);
//        Toast.makeText(this, json, Toast.LENGTH_SHORT).show();

        // deserialisasi

        String json = "{\"jurusan\":\"Teknik Informatika\",\"namaBelakang\":\"Yeager\",\"namaDepan\":\"Eren\",\"umur\":19}";
        Mahasiswa mhs1 = jsonConverter.fromJson(json, Mahasiswa.class);


    }
}
