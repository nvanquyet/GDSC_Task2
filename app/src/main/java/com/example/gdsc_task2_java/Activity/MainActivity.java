package com.example.gdsc_task2_java.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gdsc_task2_java.Fragment.FindAppFragment;
import com.example.gdsc_task2_java.Fragment.InfoDeviceFragment;
import com.example.gdsc_task2_java.R;
import com.example.gdsc_task2_java.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isBackPress = false;
    private int timeBetweenTwoPress = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new InfoDeviceFragment());
        SetBottomNav(binding.btnNavigation);
    }

    private void ReplaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }

    private void SetBottomNav(BottomNavigationView btnNav) {
        btnNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_findApp:
                    {
                        ReplaceFragment(new FindAppFragment());
                        Log.e("Fragment","FindApp");
                        break;
                    }
                    default:{
                        ReplaceFragment(new InfoDeviceFragment());
                        Log.e("Fragment","Info Device");
                        break;
                    }
                }
                return true;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        int color = GetRanDomColor();
        TextView textView = binding.textView;
        if(textView != null){
            textView.setTextColor(color);
            Log.e("Life Circle","Change Color Text View" + color);
        }

    }

    //Push Value
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt("TextView_Color",binding.textView.getCurrentTextColor());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    //Return value
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        int color = savedInstanceState.getInt("TextView_Color");
        binding.textView.setTextColor(color);
        super.onRestoreInstanceState(savedInstanceState);
    }

    private int GetRanDomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public void onBackPressed() {
        if(!isBackPress){
            isBackPress = true;
            Toast.makeText(this,"Nhấn thêm 1 lần nữa để thoát",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPress = false;
                }
            }, this.timeBetweenTwoPress);
            return;
        }
        super.onBackPressed();
    }
}