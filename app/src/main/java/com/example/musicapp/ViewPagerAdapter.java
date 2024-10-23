package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new RecommendFragment();
            case 1:
                return new DiscoverFragment();
            case 2:
                return new RoamingFragment();
            case 3:
                return new CommunityFragment();
            default:
                return new ProfileFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;  // 五个页面
    }
}
