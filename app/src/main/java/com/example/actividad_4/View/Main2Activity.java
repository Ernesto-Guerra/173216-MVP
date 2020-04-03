package com.example.actividad_4.View;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.actividad_4.R;
import com.example.actividad_4.View.FirstFragment;
import com.example.actividad_4.View.SecondFragment;

public class Main2Activity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener {
    FirstFragment firstFragment;
    SecondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
       String dato = getIntent().getStringExtra("dato");

        if (!dato.equals("smart")){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment2,firstFragment).commit();
            //getSupportFragmentManager().beginTransaction().add(R.id.fragment2,secondFragment).commit();
        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.fragment2,firstFragment).commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
