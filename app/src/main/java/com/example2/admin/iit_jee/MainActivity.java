package com.example2.admin.iit_jee;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    InterstitialAd mInterstitialAd;
    TextView t;
    MyDBHandler dbHandler;
    private ImageButton b1,b2,b3,b4,b5;
    Button button;
    private AdView mAdView;
    private Cursor c;
    ProgressBar loadingimage;
    public static final String EXTRA_MESSAGE = "com.example2.myfirstapp.MESSAGE";
    private boolean firsttime=false;
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("fuck off").build();
        mAdView.loadAd(adRequest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new MyDBHandler(this, null, null, 1);//calling database
        t=(TextView)findViewById(R.id.textView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent i =new Intent(MainActivity.this,feedback.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest1 = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest1);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        //button on click functions
        ImageButton b=(ImageButton)findViewById(R.id.imageButton);
        b2=(ImageButton)findViewById(R.id.imageButton2);
        b3=(ImageButton)findViewById(R.id.imageButton3);
        b4=(ImageButton)findViewById(R.id.imageButton4);
        b5=(ImageButton)findViewById(R.id.imageButton5);
        loadingimage=(ProgressBar)findViewById(R.id.loadingdata_progress);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,collegePredictor.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://josaa.nic.in/Result2016/result/OpeningClosingRank.aspx");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://josaa.nic.in/webinfo/Handler/FileHandler.ashx?i=File&ii=62&iii=Y");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImportantDates.class);
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://josaa.nic.in/SeatInfo/root/SeatMatrix.aspx");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        new YourAsyncTask().execute();
    }
    private boolean isFirstTime() {
        if(firsttime==false){
            SharedPreferences mPrefrences =this.getSharedPreferences("firsttime", Context.MODE_PRIVATE);
            firsttime=mPrefrences.getBoolean("firsttime",true);
            if(firsttime){
                SharedPreferences.Editor editor=mPrefrences.edit();

                boolean a=loaddatabase1();
                boolean b=loaddatabase2();
                boolean c=loaddatabase3();
                editor.putBoolean("firsttime",false);
                editor.commit();
            }
        }
        return firsttime;
    }

    private boolean loaddatabase1() {
        String data1 = readTextFile1(this, R.raw.file1);
        return true;
    }
    private boolean loaddatabase2() {
        String data2=readTextFile2(this,R.raw.file2);
        return  true;
    }
    private boolean loaddatabase3() {
        String data3=readTextFile3(this,R.raw.file3);
        return  true;
    }
    public String readTextFile1(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            while (( line = bufferedreader.readLine()) != null)
            {
                stringBuilder.append(line);
                //stringBuilder.append('\n');add it to new lines
                String [] words=line.split(" ");//split into diffrent words
                String quota=words[0];
                String coll=words[1];
                String bran=words[2];
                String r1=words[3];
                String r2=words[4];
                String r3=words[5];
                String r4=words[6];
                String r5=words[7];
                String r6=words[8];
                String r7=words[9];
                String r8=words[10];
                //  t.setText(quota+coll+bran+r1+r2+r3+r4+r5+r6+r7+r8+"\n");

                int re1=Integer.parseInt(r1);
                int re2=Integer.parseInt(r2);
                int re3=Integer.parseInt(r3);
                int re4=Integer.parseInt(r4);
                int re5=Integer.parseInt(r5);
                int re6=Integer.parseInt(r6);
                int re7=Integer.parseInt(r7);
                int re8=Integer.parseInt(r8);
                //t.setText(quota+coll+bran+r1+r2+r3+r4+r5+r6+r7+r8+"\n");
                Product product = new Product(quota,coll,bran,re1,re2,re3,re4,re5,re6,re7,re8);
                dbHandler.addProduct(product);
                // printDatabase();
            }
        }
        catch (IOException e)
        {
            return null;
        }
        return stringBuilder.toString();
    }
    private String readTextFile2(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            while (( line = bufferedreader.readLine()) != null)
            {
                String [] words=line.split(" ");
                String a=words[0];
                String a2=line.substring(4);
                product1 product=new product1(a,a2);
                dbHandler.addProduct1(product);
                //   stringBuilder.append(line);
                //  stringBuilder.append('\n');
            }
        }
        catch (IOException e)
        {
            return null;
        }
        return stringBuilder.toString();

    }
    private String readTextFile3(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            while (( line = bufferedreader.readLine()) != null)
            {
                String [] words=line.split(" ");
                String a=words[0];
                String a2=line.substring(5);
                product2 product=new product2(a,a2);
                dbHandler.addProduct2(product);
                // stringBuilder.append(line);
                //stringBuilder.append('\n');
            }
        }
        catch (IOException e)
        {
            return null;
        }
        return stringBuilder.toString();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      if (id == R.id.action_feedback) {
          Intent i =new Intent(MainActivity.this,feedback.class);
          startActivity(i);
        }
      else if(id==R.id.action_termcondition){
          Intent i =new Intent(MainActivity.this,termsandcodition.class);
          startActivity(i);
      }

        else if(id==R.id.action_Rate_us){
          rateApp();
      }
        else if(id==R.id.action_exit){
          android.os.Process.killProcess(android.os.Process.myPid());
          System.exit(1);
      }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_calender) {
            Intent intent = new Intent(MainActivity.this,ImportantDates.class);
            startActivity(intent);

        } else if (id == R.id.nav_rating) {
            rateApp();
        }
        else if(id==R.id.nav_feedback){
            Intent i =new Intent(MainActivity.this,feedback.class);
            startActivity(i);
        }
        else if (id == R.id.nav_knowRank) {
            Intent i=new Intent(MainActivity.this,webview.class);
           // Uri uri = Uri.parse("http://cbseresults.nic.in/jee_main_zxc/Jee_cbse_2017.htm");
            i.putExtra(EXTRA_MESSAGE,"http://cbseresults.nic.in/jee_main_zxc/Jee_cbse_2017.htm");
            startActivity(i);

        } else if (id == R.id.nav_share) {
            final String appPackageName = getPackageName();
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "IIT JEE ADMISSION");
                String sAux = "IIT-JEE College Predictor\nPredict your college and get informed about latest Information\non admission notices\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id="+appPackageName;
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }

        }
        else if(id==R.id.nav_aboutUs){
            Intent i =new Intent(MainActivity.this,termsandcodition.class);
            startActivity(i);
        }
        else if (id== R.id.nav_devloper){
            Intent i= new Intent(MainActivity.this,devloper.class);
            startActivity(i);
        }
        else if(id==R.id.nav_jeeMain){
            Uri uri = Uri.parse("http://jeemain.nic.in/webinfo/Public/Home.aspx");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else if(id==R.id.nav_josaa){
            Uri uri = Uri.parse("http://josaa.nic.in/webinfo/Public/home.aspx");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else if(id==R.id.nav_jeeAdvance){
            Uri uri = Uri.parse("https://jeeadv.ac.in/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }
    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... args) {
            // code where data is processing

            //loading txt in database
            boolean a=isFirstTime();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            loadingimage.setVisibility(View.INVISIBLE);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingimage.setVisibility(View.VISIBLE);
        }
    }

}
