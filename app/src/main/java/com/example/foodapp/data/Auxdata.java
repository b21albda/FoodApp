package com.example.foodapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Auxdata implements Parcelable {
    private final String description;
    private final String img;
    private final String type;

    public Auxdata(String description, String img, String type) {
        this.description = description;
        this.img = img;
        this.type = type;
    }

    protected Auxdata(Parcel in) {
        description = in.readString();
        img = in.readString();
        type = in.readString();
    }

    public static final Creator<Auxdata> CREATOR = new Creator<Auxdata>() {
        @Override
        public Auxdata createFromParcel(Parcel in) {
            return new Auxdata(in);
        }

        @Override
        public Auxdata[] newArray(int size) {
            return new Auxdata[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(img);
        parcel.writeString(type);
    }
}
