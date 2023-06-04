package com.celebrare.greentracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class afterLoginActivity extends AppCompatActivity {
    EditText[] list = new EditText[11];
    Button submit;
    double roundedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);


        initalize();


        try {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i=0;i<list.length;i++){
                        if(list[i].getText().toString().equals("")){
                            list[i].setText("0");
                        }
                    }
                    openCustomDialog();
                }
            });
        }catch (Exception e){
            Toast.makeText(afterLoginActivity.this,"Something went wrong..",Toast.LENGTH_SHORT).show();
        }
    }
    private void openCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);

        TextView editTextInput1 = dialog.findViewById(R.id.editTextInput1);
        TextView editTextInput2 = dialog.findViewById(R.id.editTextInput2);
        Button buttonSubmit = dialog.findViewById(R.id.buttonSubmit);
        Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

        String value1 = help() ;
        String value2 = prefer();

        editTextInput1.setText(value1);
        editTextInput2.setText(value2);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit button click
                Intent it = new Intent(afterLoginActivity.this,AnalyticsPage.class);
                it.putExtra("val1",roundedNumber);
                it.putExtra("val2",value2);
                startActivity(it);
                // Close the dialog
                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle cancel button click
                // Close the dialog without updating the values
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private String prefer() {
        List<String> tempAns = preferCarbonMl.calculateRecommendations((Double)Double.parseDouble(list[3].getText().toString()),
                (Double)Double.parseDouble(list[4].getText().toString()),
                (Double)Double.parseDouble(list[5].getText().toString()),
                (Double)Double.parseDouble(list[10].getText().toString()),
                (Double)Double.parseDouble(list[0].getText().toString()),
                (Double)Double.parseDouble(list[1].getText().toString()),
                (Double)Double.parseDouble(list[2].getText().toString()),
                (Double)Double.parseDouble(list[6].getText().toString()),
                (Double)Double.parseDouble(list[7].getText().toString()),
                (Double)Double.parseDouble(list[8].getText().toString()),
                (Double)Double.parseDouble(list[9].getText().toString()));

        String ans = "";
        for(int i=0;i<tempAns.size();i++){
            ans+= tempAns.get(i);
            ans+="\n";
        }
        return ans;
    }

    private String help() {
        Double cfc = helper.getTotalCarbonFootPrint((Double)Double.parseDouble(list[3].getText().toString()),
                (Double)Double.parseDouble(list[4].getText().toString()),
                (Double)Double.parseDouble(list[5].getText().toString()),
                (Double)Double.parseDouble(list[10].getText().toString()),
                (Double)Double.parseDouble(list[0].getText().toString()),
                (Double)Double.parseDouble(list[1].getText().toString()),
                (Double)Double.parseDouble(list[2].getText().toString()),
                (Double)Double.parseDouble(list[6].getText().toString()),
                (Double)Double.parseDouble(list[7].getText().toString()),
                (Double)Double.parseDouble(list[8].getText().toString()),
                (Double)Double.parseDouble(list[9].getText().toString()));

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        roundedNumber = Double.parseDouble(decimalFormat.format(cfc));


        return  "Value of your carbon emission: " + String.valueOf(roundedNumber);
    }

    private void initalize() {
        submit = findViewById(R.id.submit);
        list[0]=findViewById(R.id.editBikeDistance);
        list[1]=findViewById(R.id.editCarDistance);
        list[2]=findViewById(R.id.editBicycleDistance);
        list[3]=findViewById(R.id.editFanUsage);
        list[4]=findViewById(R.id.editTVUsage);
        list[5]=findViewById(R.id.editFridgeUsage);
        list[6]=findViewById(R.id.editMeatCalorieIntake);
        list[7]=findViewById(R.id.editGrainCalorieIntake);
        list[8]=findViewById(R.id.editDairyCalorieIntake);
        list[9]=findViewById(R.id.editFruitCalorieIntake);
        list[10]=findViewById(R.id.editWaterCalorieIntake);
    }
}

