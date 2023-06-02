package com.celebrare.greentracker;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
public class OnboardingPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 3;

    private String meatCalorieIntake;
    private String grainCalorieIntake;
    private String dairyCalorieIntake;
    private String fruitCalorieIntake;
    private String fanUsage;
    private String tvUsage;
    private String fridgeUsage;
    private String bikeDistance;
    private String carDistance;
    private String bicycleDistance;

    public OnboardingPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FoodFragment();
            case 1:
                return new ElectronicsFragment();
            case 2:
                return new TransportationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    public void setFoodData(String meatCalorieIntake, String grainCalorieIntake,
                            String dairyCalorieIntake, String fruitCalorieIntake) {
        this.meatCalorieIntake = meatCalorieIntake;
        this.grainCalorieIntake = grainCalorieIntake;
        this.dairyCalorieIntake = dairyCalorieIntake;
        this.fruitCalorieIntake = fruitCalorieIntake;
    }

    public void setElectronicsData(String fanUsage, String tvUsage, String fridgeUsage) {
        this.fanUsage = fanUsage;
        this.tvUsage = tvUsage;
        this.fridgeUsage = fridgeUsage;
    }

    public void setTransportationData(String bikeDistance, String carDistance, String bicycleDistance) {
        this.bikeDistance = bikeDistance;
        this.carDistance = carDistance;
        this.bicycleDistance = bicycleDistance;
    }

    public void saveUserData() {
        // Get the current user's UID
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();

            // Save the user's data to Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("users").document(uid);

            Map<String, Object> userData = new HashMap<>();
            userData.put("meatCalorieIntake", meatCalorieIntake);
            userData.put("grainCalorieIntake", grainCalorieIntake);
            userData.put("dairyCalorieIntake", dairyCalorieIntake);
            userData.put("fruitCalorieIntake", fruitCalorieIntake);
            userData.put("fanUsage", fanUsage);
            userData.put("tvUsage", tvUsage);
            userData.put("fridgeUsage", fridgeUsage);
            userData.put("bikeDistance", bikeDistance);
            userData.put("carDistance", carDistance);
            userData.put("bicycleDistance", bicycleDistance);

            userRef.set(userData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Toast.makeText(OnBoardingActivity.this, "User data saved successfully",
                                    //Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(OnBoardingActivity.this, "Failed to save user data",
                                    //Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}

