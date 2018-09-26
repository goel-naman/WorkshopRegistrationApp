package com.example.android.registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragmentActionListener {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager= getSupportFragmentManager();
        openFragment("login");
    }

    //open  fragment
    private void openFragment(String string) {
        Fragment fragment;
        switch (string)
        {
            case "workshop":fragment = new WorkshopFragment();
                break;
            case "login":fragment=new LoginFragment();
                break;
            case "signup":fragment=new SignUpFragment();
                break;
            default :
                return;
        }
        fragmentManager.beginTransaction().add(R.id.fragments,fragment,"NoFragment").addToBackStack(null)
                .commit();
    }


    @Override
    public void actionPerformed(String string){
        openFragment(string);
    }
}
