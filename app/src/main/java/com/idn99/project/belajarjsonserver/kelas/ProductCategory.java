package com.idn99.project.belajarjsonserver.kelas;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductCategory implements Parcelable {
    private int categoryId;
    private String categoryName;

    public ProductCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.categoryId);
        dest.writeString(this.categoryName);
    }

    protected ProductCategory(Parcel in) {
        this.categoryId = in.readInt();
        this.categoryName = in.readString();
    }

    public static final Parcelable.Creator<ProductCategory> CREATOR = new Parcelable.Creator<ProductCategory>() {
        @Override
        public ProductCategory createFromParcel(Parcel source) {
            return new ProductCategory(source);
        }

        @Override
        public ProductCategory[] newArray(int size) {
            return new ProductCategory[size];
        }
    };
}
