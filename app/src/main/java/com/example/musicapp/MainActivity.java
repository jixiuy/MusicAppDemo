package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // 设置 ViewPager 的适配器
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // ViewPager 滑动监听，改变 BottomNavigationView 的选中项
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_recommend) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (itemId == R.id.menu_discover) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (itemId == R.id.menu_roaming) {
                    viewPager.setCurrentItem(2);
                    return true;
                } else if (itemId == R.id.menu_community) {
                    viewPager.setCurrentItem(3);
                    return true;
                } else if (itemId == R.id.menu_profile) {
                    viewPager.setCurrentItem(4);
                    return true;
                }

                return false;
            }
        });

        // 初始化 DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId==R.id.nav_home){
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                }
                // 关闭导航视图
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
}