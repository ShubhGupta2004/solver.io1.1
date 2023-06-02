package com.celebrare.greentracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnNext;
    private Button btnSkip;
    private OnboardingPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

//        viewPager = findViewById(R.id.viewPager);
//        btnNext = findViewById(R.id.btn_next);
//        btnSkip = findViewById(R.id.btn_skip);
//
//        pagerAdapter = new OnboardingPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                // Not used
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                updateButtonVisibility(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // Not used
//            }
//        });
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int currentItem = viewPager.getCurrentItem();
//                if (currentItem < pagerAdapter.getCount() - 1) {
//                    viewPager.setCurrentItem(currentItem + 1);
//                } else {
//                    pagerAdapter.saveUserData();
//                    startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
//                    finish();
//                }
//            }
//        });
//
//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pagerAdapter.saveUserData();
//                startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
//                finish();
//            }
//        });
//
//        updateButtonVisibility(0);
//    }
//
//    private void updateButtonVisibility(int position) {
////        if (position == pagerAdapter.getCount() - 1) {
////            btnNext.setText(R.string.finish);
////            btnSkip.setVisibility(View.GONE);
////        } else {
////            btnNext.setText(R.string.next);
////            btnSkip.setVisibility(View.VISIBLE);
////        }
//    }

    }
}

