package com.b21dccn216.pmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.b21dccn216.pmanager.adapter.ViewPagerAdapter;
import com.b21dccn216.pmanager.model.LoginUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.b21dccn216.pmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private LoginUser user;
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        user = (LoginUser) intent.getSerializableExtra("loginUser");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        anhxa();

    }


    private void anhxa(){
        // anh xa
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewPager);
        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        Bundle bundle = new Bundle();
        bundle.putSerializable("loginUser", user);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundle);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.tab_todo).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.tab_acc).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.tab_home){
                    mViewPager.setCurrentItem(0);
                }else if(itemId == R.id.tab_todo){
                    mViewPager.setCurrentItem(1);
                }else if(itemId == R.id.tab_acc){
                    mViewPager.setCurrentItem(2);
                }else{
                    mViewPager.setCurrentItem(0);
                }
                return true;
            }
        });
    }

}