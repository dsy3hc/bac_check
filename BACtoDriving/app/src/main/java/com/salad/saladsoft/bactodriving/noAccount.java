package com.salad.saladsoft.bactodriving;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


public class noAccount extends ActionBarActivity {

    Button help, pBAC, qCalc;
    TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_account);
        qCalc = (Button) findViewById(R.id.qCalc);
        pBAC = (Button) findViewById(R.id.pBAC);
        help = (Button) findViewById(R.id.help);
        tvLoginLink = (TextView) findViewById(R.id.tvLoginLink);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_account, menu);
        return true;
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.qCalc:
                Intent quickIntent = new Intent(this, quickCalc.class);
                startActivity(quickIntent);
                break;
            case R.id.pBAC:
                Intent partyIntent = new Intent(this, partyCalc.class);
                startActivity(partyIntent);
                break;
            case R.id.help:
                Intent helpIntent = new Intent(this, help.class);
                startActivity(helpIntent);
                break;
            case R.id.tvLoginLink:
                Intent loginIntent = new Intent(this, Login.class);
                startActivity(loginIntent);
                break;
        }
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
