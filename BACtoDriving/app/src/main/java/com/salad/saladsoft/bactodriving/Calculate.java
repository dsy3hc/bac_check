package com.salad.saladsoft.bactodriving;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import java.util.GregorianCalendar;

public class Calculate extends Activity
{
    UserLocalStore userLocalStore;
    EditText weight;
    EditText drinks;
    EditText hours;
    TextView weight2;
    TextView tt;
    TextView tt2;
    Button calculate;

    double x=0;
    double y=0;
    double w=0;
    double z=0;
    double z3=0;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        userLocalStore = new UserLocalStore(this);
        //      User user = userLocalStore.getLoggedInUser();
        //       x=Double.parseDouble(weight.getText().toString());
        //      z3=user.weight;
        //     String z4= String.format("%.2f", z);
        weight=(EditText)findViewById(R.id.editWeight);
        //      weight2.setText("Hello");
        drinks=(EditText)findViewById(R.id.editDrinks);
        hours=(EditText)findViewById(R.id.editHours);
        tt=(TextView)findViewById(R.id.tt);
        tt2=(TextView)findViewById(R.id.tt2);
        calculate=(Button)findViewById(R.id.calculate);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bCalc:
                User user = userLocalStore.getLoggedInUser();
                //      x=Double.parseDouble(weight.getText().toString());
                x=user.weight;
                x=x*0.453592;
                y=Double.parseDouble(drinks.getText().toString());
                w=Double.parseDouble(hours.getText().toString());
                z=((.967*y)/(x*0.58))-(0.015*w);
                String z2= String.format("%.3f", z);
                tt.setText(z2);
                break;
            case R.id.pBAC:
                Intent partyIntent = new Intent(this, partyCalc.class);
                startActivity(partyIntent);
                break;
        }
    }
    private void calculate()
    {
        User user = userLocalStore.getLoggedInUser();
  //      x=Double.parseDouble(weight.getText().toString());
        x=user.weight;
        x=x*0.453592;
        y=Double.parseDouble(drinks.getText().toString());
        w=Double.parseDouble(hours.getText().toString());
        z=((.967*y)/(x*0.58))-(0.015*w);
        String z2= String.format("%.3f", z);
        tt.setText(z2);
    }
}