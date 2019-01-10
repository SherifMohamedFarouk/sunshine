package com.veirn.weatherapp;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.veirn.weatherapp.model.WeatherListmodel;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static com.veirn.weatherapp.pref.ENABLED;
import static com.veirn.weatherapp.pref.Metric;
import static com.veirn.weatherapp.pref.getsharedPref;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private WeatherAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    public TextView date, weather_description, high_temperature, low_temperature;
    ConstraintLayout constraintLayout;
    SharedPreferences prefs;
    WeatherListmodel List;
    public static final String CHANNEL_ID = "123";
    public  String f ;
     public Intent notificationIntent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        constraintLayout = findViewById(R.id.primary_info);

        /*Create handle for the RetrofitInstance interface*/
        weatherface restApi = RetrofitClientInstance.getRetrofitInstance().create(weatherface.class);

        Call<WeatherListmodel> call = restApi.getWeatherListmodelCall();
        call.enqueue(new Callback<WeatherListmodel>() {
            @Override
            public void onResponse(Call<WeatherListmodel> call, Response<WeatherListmodel> response) {
                progressDoalog.dismiss();
                List = response.body(); prefs = getsharedPref(MainActivity.this);
                String name3 = prefs.getString(ENABLED,"Default");
                if (name3.equals(ENABLED)){

                    f = String.format("%s %s", String.valueOf(Math.round(List.getList().get(0).getTemp().getMax())), String.valueOf(Math.round(Math.round(List.getList().get(0).getTemp().getMin()))));
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    Intent notificationIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                    notificationIntent.putExtra("g", f);
                    PendingIntent broadcast = PendingIntent.getBroadcast(MainActivity.this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    Calendar alarmStartTime = Calendar.getInstance();
                    alarmStartTime.set(Calendar.HOUR_OF_DAY, 8);
                    alarmStartTime.set(Calendar.MINUTE, 00);
                    alarmStartTime.set(Calendar.SECOND, 0);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, broadcast);
                }
                generateDataList(response.body());





                Toast.makeText(MainActivity.this, f,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<WeatherListmodel> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });




}

    /*Method to generate List of data using RecyclerView with custom adapter*/

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private  void generateDataList(final WeatherListmodel List) {
        recyclerView = findViewById(R.id.WeatherREC);
        adapter = new WeatherAdapter(this, List);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        date = findViewById(R.id.date);
        weather_description = findViewById(R.id. weather_description);
        high_temperature = findViewById(R.id.high_temperature);
        low_temperature =  findViewById(R.id.low_temperature);
        weather_description.setText(List.getList().get(0).getWeather().get(0).getMain());
        date.setText("Today");


        prefs = getsharedPref(MainActivity.this);
        String name3 = prefs.getString(Metric,"Default");

        if(name3.equals(Metric)){
            high_temperature.setText(String.valueOf(Math.round(List.getList().get(0).getTemp().getMax())));
            low_temperature.setText(String.valueOf(Math.round(Math.round(List.getList().get(0).getTemp().getMin()))));

        }
        else{
            double a = ((Math.round(List.getList().get(0).getTemp().getMax())))*9/5 + 32 ;
            double c = ((Math.round(List.getList().get(0).getTemp().getMin())))*9/5 + 32 ;
            String b = String.valueOf( a);
            String d = String.valueOf(c);

            high_temperature.setText(b);
            low_temperature.setText(d);






        }


        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , DetailActivity.class ).putExtra("model",List.getList().get(0));
                startActivity(intent);


            }
        });
        Context context = MainActivity.this;


        ImageView imageView = findViewById(R.id.weather_icon);
        String g = String.valueOf(List.getList().get(0).getWeather().get(0).getMain());
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
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Location:
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                return true;
            case R.id.Settings:
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
   }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        if (List!= null) {
            generateDataList(List);
        }
    }


}
