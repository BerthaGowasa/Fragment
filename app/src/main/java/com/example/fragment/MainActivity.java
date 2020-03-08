package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
   BottomNavigationView buttonNavigationView;
   Fragment selectfragment = new HomeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNavigationView = findViewById(R.id.button_navigation);
        buttonNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(selectfragment);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                selectfragment = new HomeFragment();
            break;
            case R.id.menu_profile:
                selectfragment = new ProfileFragment();
                loadFragment(selectfragment);
            break;
        }

        return loadFragment(selectfragment);
    }

    private boolean loadFragment(Fragment selectfragment) {
        if (selectfragment !=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activitymain_container,selectfragment)
                    .commit();
            return true;
        }
        return false;
    }
}
