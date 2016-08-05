package com.example.user.simpleui;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

import java.lang.ref.WeakReference;

/**
 * Created by user on 2016/8/5.
 */
public class GeoCodingTask extends AsyncTask<String, Void, double[]> {

    WeakReference<GeoCodingRespone> geoCodingResponewWeakReference;

    @Override
    protected double[] doInBackground(String... params) {
        double[] latlng=Utils.getLatLngFromAddress(params[0]);
        if(latlng!=null)
        {
            return latlng;
        }
        return null;
    }

    @Override
    protected void onPostExecute(double[] latlng) {
        super.onPostExecute(latlng);
        if(latlng!=null)
        {
            LatLng result=new LatLng(latlng[0], latlng[1]);
            if(geoCodingResponewWeakReference.get()!=null)
            {
                GeoCodingRespone geoCodingRespone=geoCodingResponewWeakReference.get();
                geoCodingRespone.callbackWithGeoCodingResult(result);
            }
        }
    }
    public GeoCodingTask(GeoCodingRespone geoCodingRespone)
    {
        geoCodingResponewWeakReference=new WeakReference<GeoCodingRespone>(geoCodingRespone);
    }

    interface GeoCodingRespone{
        void callbackWithGeoCodingResult(LatLng latLng);
    }
}
