package com.example.hclflim.Object;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> tabdata ;
    String[]tablist;

    public PagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    public void  MakeTab(ArrayList<Fragment> fragments,String[] tablist)
    {
        this.tabdata= fragments;
        this.tablist = tablist;
    }
    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return tabdata.get(position);
    }

    @Override
    public int getCount() {
        return tabdata.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tablist[position];
    }
}
