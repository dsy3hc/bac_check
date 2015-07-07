package com.salad.saladsoft.bactodriving;

import android.app.Activity;

import android.os.Bundle;

import android.widget.EditText;

import android.widget.TextView;

import android.widget.Button;

import android.view.View;


public class Calculate extends Activity
{
    EditText weight;
    EditText drinks;
    TextView tt;
    Button calculate;
    double x=0;
    double y=0;
    double z=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        initControls();
    }
    private void initControls()
    {
        weight=(EditText)findViewById(R.id.weight);
        drinks=(EditText)findViewById(R.id.drinks);
        tt=(TextView)findViewById(R.id.tt);
        calculate=(Button)findViewById(R.id.calculate);
        calculate.setOnClickListener(new Button.OnClickListener()
        {public void onClick
                    (View  v) { calculate();}});
    }
    private void calculate()
    {
        x=Double.parseDouble(weight.getText().toString());
        y=Double.parseDouble(drinks.getText().toString());
        z=x+y;
        tt.setText(Double.toString(z));
    }
}