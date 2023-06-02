package com.celebrare.greentracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class ElectronicsFragment extends Fragment {
    private EditText editFanUsage;
    private EditText editTVUsage;
    private EditText editFridgeUsage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_electronics, container, false);
        editFanUsage = rootView.findViewById(R.id.editFanUsage);
        editTVUsage = rootView.findViewById(R.id.editTVUsage);
        editFridgeUsage = rootView.findViewById(R.id.editFridgeUsage);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//         activity = (OnboardingPagerAdapter) getActivity();
//        if (activity != null) {
//            activity.setElectronicsData(
//                    editFanUsage.getText().toString(),
//                    editTVUsage.getText().toString(),
//                    editFridgeUsage.getText().toString()
//            );
//        }
    }
}

