package com.veirn.weatherapp;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.veirn.weatherapp.model.List;

import static com.veirn.weatherapp.pref.Metric;
import static com.veirn.weatherapp.pref.Temp;
import static com.veirn.weatherapp.pref.getsharedPref;

public class DetailActivity extends AppCompatActivity {
    public TextView date, weather_description ,high_temperature ,low_temperature;
    public SharedPreferences prefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        List list = getIntent().getParcelableExtra("model");
        TextView textView = findViewById(R.id.hun);
        textView.setText(String.format("%s ",String.valueOf(Math.round(list.getHumidity()))));
        TextView textView1 = findViewById(R.id.pren);
        textView1.setText(String.format("%s hpa",String.valueOf(Math.round(list.getPressure()))));
        TextView textView2 = findViewById(R.id.Windn);
        textView2.setText(String.format("%s km/h NE",String.valueOf(Math.round(list.getSpeed()))));

        date = findViewById(R.id.date);
        weather_description = findViewById(R.id. weather_description);
        high_temperature = findViewById(R.id.high_temperature);
        low_temperature =  findViewById(R.id.low_temperature);
        weather_description.setText(list.getWeather().get(0).getMain());
        date.setText("Today");
        high_temperature.setText(String.valueOf(Math.round(list.getTemp().getMax())));
        low_temperature.setText(String.valueOf(Math.round(list.getTemp().getMin())));
        prefs = getsharedPref(DetailActivity.this);
        String name3 = prefs.getString(Metric,"Default");

        if(name3.equals(Metric)){
            high_temperature.setText(String.valueOf(Math.round(list.getTemp().getMax())));
            low_temperature.setText(String.valueOf(Math.round(list.getTemp().getMin())));

        }
        else{
            double a = ((Math.round(list.getTemp().getMax())))*9/5 + 32 ;
            double c = ((Math.round(list.getTemp().getMin())))*9/5 + 32 ;
            String b = String.valueOf( a);
            String d = String.valueOf(c);

            high_temperature.setText(b);
            low_temperature.setText(d);


        }

        String a = String.format("%s  ,   %s",String.valueOf(Math.round(list.getTemp().getMax())),String.valueOf(Math.round(list.getTemp().getMin())));
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Metric", a);
        editor.apply();
        prefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Context context = DetailActivity.this;

        ImageView imageView = findViewById(R.id.weather_icon);
        String g = String.valueOf(list.getWeather().get(0).getMain());
        if(g.contains("Rain")){
            Glide.with(context)
                    .load(R.drawable.ic_rain)
                    .into(imageView);


        }
        else  if(g.contains("Snow")){
            Glide.with(context)
                    .load(R.drawable.ic_snow)
                    .into(imageView);


        }
        else  if(g.contains("Storm")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(imageView);


        }
        else  if(g.contains("Shower")){
            Glide.with(context)
                    .load(R.drawable.ic_light_rain)
                    .into(imageView);


        }
        else  if(g.contains("Wind")){
            Glide.with(context)
                    .load(R.drawable.ic_cloudy)
                    .into(imageView);


        }
        else  if(g.contains("Haze")){
            Glide.with(context)
                    .load(R.drawable.ic_fog)
                    .into(imageView);


        }
        else  if(g.contains("Hail")){
            Glide.with(context)
                    .load(R.drawable.ic_snow)
                    .into(imageView);


        }
        else  if(g.contains("Sleet")){
            Glide.with(context)
                    .load(R.drawable.ic_fog)
                    .into(imageView);


        }
        else  if(g.contains("Dust")){
            Glide.with(context)
                    .load(R.drawable.ic_fog)
                    .into(imageView);


        }
        else  if(g.contains("Gale")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(imageView);


        }
        else  if(g.contains("Breeze")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(imageView);


        }
        else  if(g.contains("Clouds")){
            Glide.with(context)
                    .load(R.drawable.ic_cloudy)
                    .into(imageView);


        }
        else  if(g.contains("Tornado")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(imageView);


        }
        else  {
            Glide.with(context)
                    .load(R.drawable.ic_light_clouds)
                    .into(imageView);


        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menud, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                return true;
            case R.id.Settings1:
                Intent intent = new Intent(DetailActivity.this,Settings.class);
                startActivity(intent);
                return true;
                case android.R.id.home:
                    super.onBackPressed();
                    return true;

                default:
                return super.onOptionsItemSelected(item);
        }
    }


}
