package com.example.android.registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openLoginFragment();
    }

    //open Login form
    private void openLoginFragment() {
        Fragment fragment;
        fragment = new LoginFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragments,fragment,"NoFragment").addToBackStack(null)
                .commit();
    }
    //open workshop list
    private void openWorkshpFragment(){
        Fragment fragment;
        fragment = new LoginFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragments,fragment,"NoFragment").addToBackStack(null)
                .commit();
    }

}
