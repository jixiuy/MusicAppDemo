package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicapp.BlogFragment;
import com.example.musicapp.DynamicFragment;
import com.example.musicapp.MusicFragment;

public class ViewPagerAdapter2 extends FragmentStateAdapter {

    public ViewPagerAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapter2(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            // 音乐标签页
            case 1:
                return new BlogFragment();   // 博客标签页
            case 2:
                return new DynamicFragment();  // 动态标签页
            default:
                return new MusicFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // 三个标签页
    }
}
