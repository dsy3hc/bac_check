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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import android.view.View;
import android.widget.TextView;


public class partyCalc extends ActionBarActivity {
    EditText weight;
    EditText drinks;
    EditText hours;
    TextView bac;
    TextView weightDisplay;
    TextView DrinkDisplay;
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
                setContentView(R.layout.party);
                weightDisplay=(TextView)findViewById(R.id.WeightDisplay);
                DrinkDisplay=(TextView)findViewById(R.id.DrinkDisplay);
                x = Double.parseDouble(weight.getText().toString());
                String weight2=weight.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd");
                Calendar cal = Calendar.getInstance();
                java.util.Date now = cal.getTime();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
                //Date d2=cal.getTime();
                String da=dateFormat.format(currentTimestamp);
                weight2= "Your gender is: " + gender+ "\nYour weight is: "+ weight2+ " pounds"
                +"\nThe starting time is: " +da;
                weightDisplay.setText(weight2);
                String drinkNumber=String.valueOf(y);
                drinkNumber="Drinks Consumed: "+drinkNumber;
                DrinkDisplay.setText(drinkNumber);
                break;
            case R.id.pAdd:
             //   setContentView(R.layout.party_add);
             //   drinks=(EditText)findViewById(R.id.editDrinksP);
                y++;
                String drinkNumber2=String.valueOf(y);
                drinkNumber2="Drinks Consumed: "+drinkNumber2;
                DrinkDisplay.setText(drinkNumber2);
                break;
            case R.id.pAdd2:
                drinks=(EditText)findViewById(R.id.editDrinksP);
                y = Double.parseDouble(drinks.getText().toString())+y;
                setContentView(R.layout.party);
                DrinkDisplay=(TextView)findViewById(R.id.DrinkDisplay);
                String drinkNumberNew=String.valueOf(y);
                drinkNumberNew="Drinks Consumed: "+drinkNumberNew;
                DrinkDisplay.setText(drinkNumberNew);
                break;
           /* case R.id.pAdd:
                DrinkDisplay=(TextView)findViewById(R.id.DrinkDisplay);
                DateFormat dateFormat2 = new SimpleDateFormat("HH:mm MM/dd");
                Calendar cal2 = Calendar.getInstance();
                Date d2=cal2.getTime();
                String dat=dateFormat2.format(d2);
                DrinkDisplay.setText(dat);
                break;*/
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
