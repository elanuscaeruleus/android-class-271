package com.example.user.simpleui;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Handler;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.lang.ref.WeakReference;
import java.util.logging.LogRecord;

public class OrderDetailActivity extends AppCompatActivity implements GeoCodingTask.GeoCodingRespone {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Order order=getIntent().getParcelableExtra("order");

        TextView NotetextView=(TextView)findViewById(R.id.NoteTextView);
        TextView orderResultTextView=(TextView)findViewById(R.id.orderResultTextView);
        TextView storeInfoTextView=(TextView)findViewById(R.id.storeInfoTextView);

        NotetextView.setText(order.getNote());
        storeInfoTextView.setText(order.getStoreInfo());

        String orderResultText="";
        for(DrinkOrder drinkOrder:order.getDrinkOrders())
        {
            String mNumber=String.valueOf(drinkOrder.getmNumber());
            String lNumber=String.valueOf(drinkOrder.getlNumber());
            String drinkName=drinkOrder.getDrink().getName();
            orderResultText += drinkName + "  M: " + mNumber + "  L:  " + lNumber + "\n";
        }
        orderResultTextView.setText(orderResultText);

        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                (new GeoCodingTask(OrderDetailActivity.this)).execute("台北市羅斯福路四段一號");
            }
        });

        ImageView staticImageView=(ImageView)findViewById(R.id.staticMapImageView);


        Log.e("Main Thread ID", Long.toString(Thread.currentThread().getId()));

       /* final Handler handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                testTextView.setText("Hello Handler");
                Log.e("Handler Thread ID", Long.toString(Thread.currentThread().getId()));
                return false;
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                testTextView.setText("Hello Handler POST DELAY");
            }
        }, 10000);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    //testTextView.setText("Hello Thread");
                    Log.e("Thread ID", Long.toString(Thread.currentThread().getId()));
                    handler.sendMessage(new Message());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();*/
        Log.e("Main Thread ID", Long.toString(Thread.currentThread().getId()));
    }

    @Override
    public void callbackWithGeoCodingResult(LatLng latLng) {
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng, 17);
        map.moveCamera(cameraUpdate);
    }

    /*public static class GeoCodingTask extends AsyncTask<String, Void, Bitmap>{

        WeakReference<ImageView> imageViewWeakReference;

        @Override
        protected Bitmap doInBackground(String... params) {
            double[] latlng=Utils.getLatLngFromAddress(params[0]);
            if(latlng!=null)
            {
                return Utils.getStaticMapFromLatLng(latlng);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null)
            {
                if(imageViewWeakReference.get()!=null)
                {
                    ImageView imageView=imageViewWeakReference.get();
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
        public GeoCodingTask(ImageView imageView)
        {
            imageViewWeakReference=new WeakReference<ImageView>(imageView);
        }
    }*/
}
