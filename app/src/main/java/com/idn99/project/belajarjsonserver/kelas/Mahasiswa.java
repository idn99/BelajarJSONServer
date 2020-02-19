package com.idn99.project.belajarjsonserver.kelas;

public class Mahasiswa {
    private String namaDepan;
    private String namaBelakang;
    private int umur;
    private String jurusan;

    public Mahasiswa(String namaDepan, String namaBelakang, int umur, String jurusan) {
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.umur = umur;
        this.jurusan = jurusan;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public int getUmur() {
        return umur;
    }

    public String getJurusan() {
        return jurusan;
    }
}
