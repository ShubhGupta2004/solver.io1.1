package com.celebrare.greentracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class TransportationFragment extends Fragment {
    private EditText editBikeDistance;
    private EditText editCarDistance;
    private EditText editBicycleDistance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transportation, container, false);
        editBikeDistance = rootView.findViewById(R.id.editBikeDistance);
        editCarDistance = rootView.findViewById(R.id.editCarDistance);
        editBicycleDistance = rootView.findViewById(R.id.editBicycleDistance);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        OnBoardingActivity activity = (OnBoardingActivity) getActivity();
//        if (activity != null) {
//            activity.setTransportationData(
//                    editBikeDistance.getText().toString(),
//                    editCarDistance.getText().toString(),
//                    editBicycleDistance.getText().toString()
//            );
//        }
    }
}

