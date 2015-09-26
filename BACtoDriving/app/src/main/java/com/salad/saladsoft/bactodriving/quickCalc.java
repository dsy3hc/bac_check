package com.salad.saladsoft.bactodriving;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


public class quickCalc extends ActionBarActivity {
    EditText weight;
    EditText drinks;
    EditText hours;
    TextView bac;
    TextView timeLeft;
    Button calculate;
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;


    double x=0;
    double y=0;
    double w=0;
    double z=0;
    double a=0;
    double C1=0;
    double C2=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_calc);
        radioGenderGroup=(RadioGroup)findViewById(R.id.radioGroup);
        weight=(EditText)findViewById(R.id.editWeight);
        drinks=(EditText)findViewById(R.id.editDrinks);
        hours=(EditText)findViewById(R.id.editHours);
        calculate=(Button)findViewById(R.id.calculate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quick_calc, menu);
        return true;
    }

    public void onClick(View v) {
       // boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()){
            case R.id.calculate:
                int selectedId=radioGenderGroup.getCheckedRadioButtonId();
                radioGenderButton=(RadioButton)findViewById(selectedId);
                String gender= (String)radioGenderButton.getText();
                if(gender.equals("Male")){
                    C1=0.58;
                    C2=0.015;
                }
                else if(gender.equals("Female")){
                    C1=0.49;
                    C2=0.017;
                }
                setContentView(R.layout.quickcalc);
                timeLeft=(TextView)findViewById(R.id.Drive);
                bac=(TextView)findViewById(R.id.bac);
                x = Double.parseDouble(weight.getText().toString());
                y = Double.parseDouble(drinks.getText().toString());
                w = Double.parseDouble(hours.getText().toString());
                z = calculateBac(x, w, y);
                String z2 = String.format("%.3f", z);
                String z3 = "Your BAC is: " + z2;
                bac.setText(z3);
                if (z < 0.08) {
                    String messageSafe = "You are below the legal limit and are safe to drive.";
                    timeLeft.setText(messageSafe);
                } else {
                    String tLeft = calcTimeLeft(x, w, y);
                    timeLeft.setText(tLeft);
                }
                break;
            case R.id.qBack:
                Intent backIntent = new Intent(this, noAccount.class);
                startActivity(backIntent);
                break;

        }
    }
    public double calculateBac(Double weight, Double time, Double drinks){
        Double bac = 0.0;
        weight *=0.453592;
        bac = ((.967 * drinks) / (weight * C1)) - (C2 * time);
        return bac;
    }

    public String calcTimeLeft(Double weight, Double time, Double drinks){
        double hoursLeft = 0.0;
        double minLeft = 0.0;
        weight *= 0.453592;
        hoursLeft = ((((.967*drinks)/(weight*C1))-0.08)/C2)-time;
        int hoursLeft2 = (int)hoursLeft;
        minLeft =(10 * hoursLeft - 10 * hoursLeft2)/10;
        minLeft*= 60;
        int minLeft2 = (int)minLeft;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, hoursLeft2);
        cal.add(Calendar.MINUTE,minLeft2);
        Date timeRemaining=cal.getTime();
        String tRemaining=dateFormat.format(timeRemaining);
        String responseText = "You can drive in "+hoursLeft2+ " hour(s) and "+ minLeft2+" minutes at " +tRemaining;
        return responseText;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
