package com.example.foodapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.Comparator;

public class Food implements Parcelable {
    private final String ID;
    private final String name;
    private final int size;
    private final int cost;
    private final String category;
    private final Auxdata auxdata;
    private long dbId;

    public Food(String id, String name, int size, int cost, String category, Auxdata auxdata) {
        this.ID = id;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.category = category;
        this.auxdata = auxdata;
    }

    public Food(String id, String name, int size, int cost, String category, Auxdata auxdata, long dbId) {
        this.ID = id;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.category = category;
        this.auxdata = auxdata;
        this.dbId = dbId;
    }

    protected Food(Parcel in) {
        ID = in.readString();
        name = in.readString();
        size = in.readInt();
        cost = in.readInt();
        category = in.readString();
        auxdata = in.readParcelable(Auxdata.class.getClassLoader());
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }

    public Auxdata getAuxdata() {
        return auxdata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeString(name);
        parcel.writeInt(size);
        parcel.writeInt(cost);
        parcel.writeString(category);
        parcel.writeParcelable(auxdata, i);
    }

    public long getDbId() {
        return dbId;
    }
}
