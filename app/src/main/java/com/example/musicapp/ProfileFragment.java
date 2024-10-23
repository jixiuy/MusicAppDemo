package com.example.musicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProfileFragment extends Fragment {

    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // 获取 DrawerLayout
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        // 获取按钮并设置点击监听
        ImageView openDrawerButton = view.findViewById(R.id.dehaze);
        openDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开侧边栏
                if (drawerLayout != null) {
                    drawerLayout.open();
                }
            }
        });
        ImageView imageView = view.findViewById(R.id.photo);
        Glide.with(this)
                .load(R.drawable.photo) // 或者使用本地资源 R.drawable.ic_profile
                .circleCrop() // 圆形裁剪
                .into(imageView);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        // 设置适配器
        ViewPagerAdapter2 adapter = new ViewPagerAdapter2(this);
        viewPager.setAdapter(adapter);

        // 设置 TabLayout 的标签
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("音乐");   // 第一个标签
                        break;
                    case 1:
                        tab.setText("博客");   // 第二个标签
                        break;
                    case 2:
                        tab.setText("动态");   // 第三个标签
                        break;
                }
            }
        }).attach();

        return view;
    }
}