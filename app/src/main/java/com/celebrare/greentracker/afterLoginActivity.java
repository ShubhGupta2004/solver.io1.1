package com.celebrare.greentracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class afterLoginActivity extends AppCompatActivity {
    EditText[] list = new EditText[16];
    Button submit;
    double roundedNumber;
    RadioButton sexM;
    RadioButton sexF;
    CheckBox carPool;
    CheckBox publicTrans;

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
                FirestoreUtils.pushDataToFirestore(roundedNumber);
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
        sexM = findViewById(R.id.editSexM);
        sexF = findViewById(R.id.editSexF);
        String sexM1="Male";
        if(sexF.isChecked()){
            sexM1="Female";
        }else if(sexM.isChecked()){
            sexM1="Male";
        }
        int star = 0;
        if(!list[6].getText().toString().equals("")){
            star=Integer.parseInt(list[6].getText().toString());
        };
        int a=0,b=0,c=0,d=0,e=0;
        switch (star){
            case 1:
                a=1;
                break;
            case 2:
                b=1;
                break;
            case 3:
                c=1;
                break;
            case 4:
                d=1;
                break;
            case 5:
                e=1;
                break;
            default:

        }

        Double cfc = helper.calculateCarbonEmission(Double.parseDouble(list[0].getText().toString()),
                Double.parseDouble(list[1].getText().toString()),
                Double.parseDouble(list[2].getText().toString()),
                Double.parseDouble(list[3].getText().toString()),
                Double.parseDouble(list[4].getText().toString()),
                Double.parseDouble(list[5].getText().toString()),a
                ,b,c,d,e,
                Double.parseDouble(list[7].getText().toString()),
                Double.parseDouble(list[8].getText().toString()),
                Double.parseDouble(list[9].getText().toString()),
                Double.parseDouble(list[10].getText().toString()),
                Double.parseDouble(list[11].getText().toString()),
                Double.parseDouble(list[12].getText().toString()),
                Double.parseDouble(list[13].getText().toString()),
                (int) Double.parseDouble(list[14].getText().toString()),
                sexM1,
                (int)Double.parseDouble(list[15].getText().toString()),carPool.isChecked(),publicTrans.isChecked());

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        roundedNumber = Double.parseDouble(decimalFormat.format(cfc));


        return  "Value of your carbon emission: " + String.valueOf(roundedNumber);
    }

    private void initalize() {
        submit = findViewById(R.id.submit);
        list[0]=findViewById(R.id.editACUsage);
        list[1]=findViewById(R.id.editWaterHeaterUsage);
        list[2]=findViewById(R.id.editWashingMachineUsage);
        list[3]=findViewById(R.id.editincandescentBulbsUsage);
        list[4]=findViewById(R.id.editflourocentBulbsUsageUsage);
        list[5]=findViewById(R.id.editledBulbsUsageUsage);
        list[6]=findViewById(R.id.editFridgeUsage);
        list[7]=findViewById(R.id.editOvenUsage);
        list[8]=findViewById(R.id.editWaterCalorieIntake);
        list[9]=findViewById(R.id.editBikeDistance);
        list[10]=findViewById(R.id.editCarDistance);
        list[11]=findViewById(R.id.editBicycleDistance);
        list[12]=findViewById(R.id.editBMI);
        list[13]=findViewById(R.id.editBmr);
        list[14]=findViewById(R.id.editAge);
        list[15]=findViewById(R.id.editexersicetime);
        carPool=findViewById(R.id.carPooling);
        publicTrans=findViewById(R.id.publicTransport);
    }
}

