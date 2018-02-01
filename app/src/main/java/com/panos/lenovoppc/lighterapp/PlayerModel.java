package com.panos.lenovoppc.lighterapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lenovo pPc on 25-Jan-18.
 */

public class PlayerModel implements Parcelable {

    private String name;
    private int image;

    public PlayerModel(String n, int i){
        name = n;
        image = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    /* Code needed for Parcelable Implementation */


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(image);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public PlayerModel createFromParcel(Parcel in) {
            return new PlayerModel(in);
        }

        public PlayerModel[] newArray(int size) {
            return new PlayerModel[size];
        }
    };

    // De-parcel object
    private PlayerModel(Parcel in) {
        name = in.readString();
        image = in.readInt();
    }



}
