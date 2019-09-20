package com.example.contactlist;

import android.os.Parcel;
import android.os.Parcelable;

public class model_class implements Parcelable {
    private int image;
    private String name;

    public model_class(int photo, String name) {
        this.image = photo;
        this.name = name;
    }

    protected model_class(Parcel in) {
        image = in.readInt();
        name = in.readString();
    }

    public static final Creator<model_class> CREATOR = new Creator<model_class>() {
        @Override
        public model_class createFromParcel(Parcel in) {
            return new model_class(in);
        }

        @Override
        public model_class[] newArray(int size) {
            return new model_class[size];
        }
    };

    public int getImage() {

        return image;
    }

    public String getName() {

        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(name);
    }
}
