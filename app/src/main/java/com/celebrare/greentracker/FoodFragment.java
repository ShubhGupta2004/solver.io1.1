package com.celebrare.greentracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FoodFragment extends Fragment {
    private EditText editMeatCalorieIntake;
    private EditText editGrainCalorieIntake;
    private EditText editDairyCalorieIntake;
    private EditText editFruitCalorieIntake;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);
        editMeatCalorieIntake = rootView.findViewById(R.id.editMeatCalorieIntake);
        editGrainCalorieIntake = rootView.findViewById(R.id.editGrainCalorieIntake);
        editDairyCalorieIntake = rootView.findViewById(R.id.editDairyCalorieIntake);
        editFruitCalorieIntake = rootView.findViewById(R.id.editFruitCalorieIntake);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        OnBoardingActivity activity = (OnBoardingActivity) getActivity();
//        if (activity != null) {
//            activity.setFoodData(editMeatCalorieIntake.getText().toString(),
//                    editGrainCalorieIntake.getText().toString(),
//                    editDairyCalorieIntake.getText().toString(),
//                    editFruitCalorieIntake.getText().toString()
//            );
//        }
    }
}

