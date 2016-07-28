package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity {

    String[] drinkNames=new String[]{"White gourd tea", "Black tea", "Pearl black tea", "Milk black tea"};

    int[] lPrices=new int[]{25,35,35,25};
    int[] mPrices=new int[]{15,25,25,15};
    int[] images=new int[]{R.drawable.drink1, R.drawable.drink4, R.drawable.drink3, R.drawable.drink2};

    List<Drink> drinkList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);

        setData();

        Log.d("Debug", "DrinkMenuActivity OnCreate");
    }

    public void setData()
    {
        for(int i=0;i<4;i++)
        {
            Drink drink=new Drink();
            drink.name=drinkNames[i];
            drink.lPrices=lPrices[i];
            drink.mPrices=mPrices[i];
            drink.imageId=images[i];
            drinkList.add(drink);
        }
    }

    protected void onStart(){
        super.onStart();
        Log.d("debug", "DrinkMenu OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "DrinkMenu OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "MainActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "DrinkMenu OnStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "DrinkMenu onRestart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("debug", "DrinkMenu onDestroy");
    }
}
