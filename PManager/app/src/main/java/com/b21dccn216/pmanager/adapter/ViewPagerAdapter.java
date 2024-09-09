package com.b21dccn216.pmanager.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.b21dccn216.pmanager.tabfragment.AccountFragment;
import com.b21dccn216.pmanager.tabfragment.HomeFragment;
import com.b21dccn216.pmanager.tabfragment.TodoFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private Bundle bundle;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, Bundle bundle) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                HomeFragment hfm = new HomeFragment();
                hfm.setArguments(bundle);
                return hfm;
            case 1 :
                TodoFragment tdf = new TodoFragment();
                tdf.setArguments(bundle);
                return tdf;
            case 2 :
                AccountFragment acf = new AccountFragment();
                acf.setArguments(bundle);
                return acf;
            default:
                HomeFragment hfm1 = new HomeFragment();
                hfm1.setArguments(bundle);
                return hfm1;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
