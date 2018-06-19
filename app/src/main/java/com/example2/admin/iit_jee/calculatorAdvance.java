package com.example2.admin.iit_jee;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class calculatorAdvance extends AppCompatActivity{
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    EditText editText;
    MyDBHandler handlesRank;
    private AdView mAdView;
    public static final String EXTRA_MESSAGE = "com.example2.myfirstapp.MESSAGE";
    ProgressBar loadingimage;
    String output="",s="";
    Integer rank=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_advance);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("XXXXXXXX FUCK OFF").build();
        System.out.println("this is my phone");
        mAdView.loadAd(adRequest);
        handlesRank = new MyDBHandler(this, null, null, 1);
        radioGroup = (RadioGroup) findViewById(R.id.RGroup);
        editText=(EditText)findViewById(R.id.editText2);
        loadingimage= (ProgressBar) findViewById(R.id.loadingdata_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        btnDisplay = (Button) findViewById(R.id.button2);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int selectedId = radioGroup.getCheckedRadioButtonId();
                String s1=editText.getText().toString();
                if (s1.matches("")) {
                    editText.setError("Enter your Rank");
                    return;
                }
                else{
                    rank=Integer.parseInt(s1);
                }

                 output=null;
                radioButton = (RadioButton) findViewById(selectedId);
                s=radioButton.getText().toString();
                new YourAsyncTask().execute();
                //calling asynctask

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... args) {
            // code where data is processing
            if (s.equalsIgnoreCase("General")) {
                output=handlesRank.databaseToString(1,rank);
            }
            else  if (s.equalsIgnoreCase("SC")) {
                output=handlesRank.databaseToString(2,rank);
            }
            else  if (s.equalsIgnoreCase("ST")) {
                output=handlesRank.databaseToString(3,rank);
            }
            else  if (s.equalsIgnoreCase("Obc")) {
                output=handlesRank.databaseToString(4,rank);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            loadingimage.setVisibility(View.INVISIBLE);
            super.onPostExecute(result);

            Intent intent = new Intent(calculatorAdvance.this, outputAdvance.class);
            intent.putExtra(EXTRA_MESSAGE,output);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingimage.setVisibility(View.VISIBLE);
        }
    }
}
