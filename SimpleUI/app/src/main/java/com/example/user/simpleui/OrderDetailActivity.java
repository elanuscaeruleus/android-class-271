package com.example.user.simpleui;

import android.graphics.Bitmap;
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

import java.lang.ref.WeakReference;
import java.util.logging.LogRecord;

public class OrderDetailActivity extends AppCompatActivity {

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

        ImageView staticImageView=(ImageView)findViewById(R.id.staticMapImageView);

        (new GeoCodingTask(staticImageView)).execute("台北市羅斯福路四段一號");
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

    public static class GeoCodingTask extends AsyncTask<String, Void, Bitmap>{

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
    }
}
