package com.salad.saladsoft.bactodriving;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.view.View;
import android.widget.TextView;


public class partyCalc extends ActionBarActivity {
    EditText weight;
    EditText drinks;
    EditText hours;
    TextView bac;
    TextView weightDisplay;
    TextView DrinkDisplay;
    TextView bacDisplay;
    TextView hourDisplay;
    Button start;
    TextView hoursLeft;
    Button calculate;
    RadioGroup radioGenderGroup;
    RadioButton radioGenderButton;

    double drinksConsumed = 0;
    double x=0;
    double y=0;
    double w=0;
    double z=0;
    double a=0;
    double C1=0;
    double C2=0;
    double a3;
    double bac_check=0;
    double bac_check2=0;
    long diff;
    double timeDiff;
    int selectedId;
    int a2;

    DateFormat dateFormat;
    Calendar cal;
    Calendar cal2;
    java.util.Date now;
    Date dat2;
    Date dat3;
    java.sql.Timestamp currentTimestamp;
    TimeUnit timeUnit;

    String currentTime;
    String startTime;
    String drinkNumber;
    String weight2;
    String gender;
    String result1;
    String result2;
    String drive;
    String partyBac;
    String testBlank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_calc);
        radioGenderGroup=(RadioGroup)findViewById(R.id.radioGroupP);
        weight=(EditText)findViewById(R.id.editWeightP);
    }
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.pStart:
                selectedId=radioGenderGroup.getCheckedRadioButtonId();
                radioGenderButton=(RadioButton)findViewById(selectedId);
                gender= (String)radioGenderButton.getText();
                if(gender.equals("Male")){
                    C1=0.58;
                    C2=0.015;
                }
                else if(gender.equals("Female")){
                    C1=0.49;
                    C2=0.017;
                }
                setContentView(R.layout.party);
                weightDisplay=(TextView)findViewById(R.id.WeightDisplay);
                DrinkDisplay=(TextView)findViewById(R.id.DrinkDisplay);
                x = Double.parseDouble(weight.getText().toString());
                x=x*0.453592;
                weight2=weight.getText().toString();
                dateFormat = new SimpleDateFormat("HH:mm MM/dd");
                cal = Calendar.getInstance();
                now = cal.getTime();
                currentTimestamp = new java.sql.Timestamp(now.getTime());
                //Date d2=cal.getTime();
                startTime=dateFormat.format(currentTimestamp);
                weight2= "Your gender is: " + gender+ "\nYour weight is: "+ weight2+ " pounds"
                +"\nThe starting time is: " +startTime;
                weightDisplay.setText(weight2);
                drinkNumber=String.valueOf(y);
                drinkNumber="Drinks Consumed: "+drinkNumber;
                DrinkDisplay.setText(drinkNumber);
                break;
            case R.id.pAdd:
                setContentView(R.layout.party_add);
                break;
            case R.id.pAdd2:
                drinks=(EditText)findViewById(R.id.editDrinksP);
                testBlank=drinks.getText().toString();
                if(testBlank.equals("")){
                    //drinks.setHint("Required Field");
                    drinks.setError("Required Field");
                }
                else {
                    y = Double.parseDouble(drinks.getText().toString())+y;
                    setContentView(R.layout.party);
                    DrinkDisplay = (TextView) findViewById(R.id.DrinkDisplay);
                    weightDisplay = (TextView) findViewById(R.id.WeightDisplay);
                    String drinkNumberNew = String.valueOf(y);
                    drinkNumber = "Drinks Consumed: " + drinkNumberNew;
                    DrinkDisplay.setText(drinkNumber);
                    weightDisplay.setText(weight2);
                }
                break;
            case R.id.pCalc:
                setContentView(R.layout.party_calc);
                bacDisplay=(TextView)findViewById(R.id.bacDisplay);
                hourDisplay=(TextView)findViewById(R.id.hourDisplay);
                cal2 = Calendar.getInstance();
                dat2 = cal2.getTime();
                currentTime=dateFormat.format(dat2);
                diff= Math.abs(dat2.getTime() - currentTimestamp.getTime());
                timeDiff=((double)diff)/3600000;
                w=timeDiff;
                z = ((.967 * y) / (x * C1)) - (C2 * w);
                result1=String.format("%.3f", z);
                partyBac="Your BAC is: "+result1;
                if(z<.08){
                    drive="You are under the legal limit and safe to drive.";
                }
                else{
                    a=((((.967*y)/(x*C1))-0.08)/C2)-w;
                    a2=(int)a;
                    a3 = (10 * a - 10 * a2)/10;
                    a3 = a3*60;
                    int a4= (int)a3;
                    cal = Calendar.getInstance();
                    cal.add(Calendar.HOUR, a2);
                    cal.add(Calendar.MINUTE,a4);
                    dat3=cal.getTime();
                    drive=dateFormat.format(dat3);
                    drive = "You can drive in "+a2+ " hour(s) and "+ a4+" minutes at " +drive;
                }
                bacDisplay.setText(partyBac);
                hourDisplay.setText(drive);
                break;
            case R.id.end:
                Intent endIntent = new Intent(this, noAccount.class);
                startActivity(endIntent);
                break;
            case R.id.back1:
                Intent backIntent = new Intent(this, noAccount.class);
                startActivity(backIntent);
                break;
            case R.id.back2:
                setContentView(R.layout.party);
                weightDisplay=(TextView)findViewById(R.id.WeightDisplay);
                DrinkDisplay=(TextView)findViewById(R.id.DrinkDisplay);
                weightDisplay.setText(weight2);
                DrinkDisplay.setText(drinkNumber);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_party_calc, menu);
        return true;
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
