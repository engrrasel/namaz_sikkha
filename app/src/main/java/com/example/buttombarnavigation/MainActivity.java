package com.example.buttombarnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animatedBottomBar = findViewById(R.id.button_navigation_bar);

/*home fragment keep in startup*/

        if (savedInstanceState==null){
            animatedBottomBar.selectTabById(R.id.homeid,true);
            fragmentManager=getSupportFragmentManager();
            home home=new home();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,home)
                    .commit();

        }

/*on item click listner*/

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndes, @Nullable AnimatedBottomBar.Tab tab, int lastTab, @NotNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment=null;
                switch (newTab.getId()) {
                    case R.id.homeid:
                        fragment = new home();
                        break;


                    case R.id.compass:
                        fragment = new compass();
                        break;


                    case R.id.calender:
                        fragment = new calender();
                        break;

                    case R.id.devloper_profile:
                        fragment = new devloper();
                        break;
                }
                        if (fragment !=null){
                            fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment)
                                    .commit();
                        } else {
                            Log.e(TAG,"Error in creating Gragment");
                        }
                }


            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });
    }
}
