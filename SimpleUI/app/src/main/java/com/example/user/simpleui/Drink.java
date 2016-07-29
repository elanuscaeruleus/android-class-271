package com.example.user.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/7/28.
 */
public class Drink implements Parcelable {
    String name;
    int lPrices;
    int mPrices;
    int imageId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.lPrices);
        dest.writeInt(this.mPrices);
        dest.writeInt(this.imageId);
    }

    public Drink() {
    }

    protected Drink(Parcel in) {
        this.name = in.readString();
        this.lPrices = in.readInt();
        this.mPrices = in.readInt();
        this.imageId = in.readInt();
    }

    public static final Parcelable.Creator<Drink> CREATOR = new Parcelable.Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel source) {
            return new Drink(source);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };
}
