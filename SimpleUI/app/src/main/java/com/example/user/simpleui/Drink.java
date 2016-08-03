package com.example.user.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;

/**
 * Created by user on 2016/7/28.
 */
@ParseClassName("Drink")
public class Drink extends ParseObject implements Parcelable {
    String NAME_COL="name";
    String LPRICES_COL="lPrice";
    String MPRICES_COL="mPrice";

    int imageId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(getObjectId()==null) {
            dest.writeInt(0);
            dest.writeString(this.getName());
            dest.writeInt(this.getlPrices());
            dest.writeInt(this.getmPrices());
        }
        else {
            dest.writeInt(1);
            dest.writeString(getObjectId());
        }
    }

    public Drink() {
    }

    protected Drink(Parcel in) {
        this.setName(in.readString());
        this.setlPrices(in.readInt());
        this.setmPrices(in.readInt());
    }

    public static final Parcelable.Creator<Drink> CREATOR = new Parcelable.Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel source) {
            int isDraft=source.readInt();
            if(isDraft==0)
            {
                return new Drink(source);
            }
            else
            {
                return getDrinkFromCache(source.readString());
            }
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

    public String getName() {
        return getString(NAME_COL);
    }

    public void setName(String name) {
        this.put(NAME_COL, name);
    }

    public int getlPrices() {
        return getInt(LPRICES_COL);
    }

    public void setlPrices(int lPrices) {
        this.put(LPRICES_COL, lPrices);
    }

    public int getmPrices() {
        return getInt(MPRICES_COL);
    }

    public void setmPrices(int mPrices) {
        this.put(MPRICES_COL, mPrices);
    }

    public ParseFile getParseFile()
    {
        return getParseFile("image");
    }

    public static ParseQuery<Drink> getQuery(){return ParseQuery.getQuery(Drink.class);}
    public static Drink getDrinkFromCache(String objectId)
    {
        try{
            Drink drink=getQuery().setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK).get(objectId);
            return drink;
        }catch(com.parse.ParseException e){
            return DrinkOrder.createWithoutData(Drink.class, objectId);
        }
    }
}
