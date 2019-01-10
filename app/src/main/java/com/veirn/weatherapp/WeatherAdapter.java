package com.veirn.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veirn.weatherapp.model.WeatherListmodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.veirn.weatherapp.pref.Imperial;
import static com.veirn.weatherapp.pref.Metric;
import static com.veirn.weatherapp.pref.getsharedPref;

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.CustomViewHolder> {

    private WeatherListmodel dataList;
    private Context context;
    public SharedPreferences prefs ;


    public WeatherAdapter(Context context,WeatherListmodel dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public ImageView imageView ;
        public TextView today ,Weather , wmax , wmin  ;



        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
                imageView= mView.findViewById(R.id.Image);
                today = mView.findViewById(R.id.today);
                Weather=mView.findViewById(R.id.Weather);
                wmax = mView.findViewById(R.id.wmax);
                wmin = mView.findViewById(R.id.wmin);



        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        String g = String.valueOf(this.dataList.getList().get(position).getWeather().get(0).getMain());
        if(g.contains("Rain")){
            Glide.with(context)
                    .load(R.drawable.ic_rain)
                    .into(holder.imageView);


        }
        else  if(g.contains("Snow")){
            Glide.with(context)
                    .load(R.drawable.ic_snow)
                    .into(holder.imageView);


        }
        else  if(g.contains("Storm")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(holder.imageView);


        }
        else  if(g.contains("Shower")){
            Glide.with(context)
                    .load(R.drawable.ic_light_rain)
                    .into(holder.imageView);


        }
        else  if(g.contains("Wind")){
            Glide.with(context)
                    .load(R.drawable.ic_cloudy)
                    .into(holder.imageView);


        }
        else  if(g.contains("Haze")){
            Glide.with(context)
                    .load(R.drawable.ic_fog)
                    .into(holder.imageView);


        }
        else  if(g.contains("Hail")){
            Glide.with(context)
                    .load(R.drawable.ic_snow)
                    .into(holder.imageView);


        }
        else  if(g.contains("Sleet")){
            Glide.with(context)
                    .load(R.drawable.ic_fog)
                    .into(holder.imageView);


        }
        else  if(g.contains("Dust")){
            Glide.with(context)
                    .load(R.drawable.ic_fog)
                    .into(holder.imageView);


        }
        else  if(g.contains("Gale")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(holder.imageView);


        }
        else  if(g.contains("Breeze")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(holder.imageView);


        }
        else  if(g.contains("Clouds")){
            Glide.with(context)
                    .load(R.drawable.ic_cloudy)
                    .into(holder.imageView);


        }
        else  if(g.contains("Torado")){
            Glide.with(context)
                    .load(R.drawable.ic_storm)
                    .into(holder.imageView);


        }
        else  {
            Glide.with(context)
                    .load(R.drawable.ic_light_clouds)
                    .into(holder.imageView);


        }



        holder.Weather.setText(String.valueOf(this.dataList.getList().get(position).getWeather().get(0).getMain()));


        Calendar  calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.US);

        if (position == 0 ) {
            holder.today.setText("Today");
        }
        else if (position==1){
            holder.today.setText("Tommorow");

        }
        else{

            calendar.add(Calendar.DATE,position);
            String x = simpleDateFormat.format(calendar.getTime());
            holder.today.setText(x);
        }
        prefs = getsharedPref(context);

        String name2 = prefs.getString(Imperial,"Default");

        String name3 = prefs.getString(Metric,"Default");

        if(name3.equals(Metric)){
            holder.wmax.setText(String.valueOf(Math.round(this.dataList.getList().get(position).getTemp().getMax())));
            holder.wmin.setText(String.valueOf(Math.round(this.dataList.getList().get(position).getTemp().getMin())));

        }
        else{
            double a = (Math.round(this.dataList.getList().get(position).getTemp().getMax()))*9/5 + 32 ;
            double c = (Math.round(this.dataList.getList().get(position).getTemp().getMin()))*9/5 + 32 ;
            String b = String.valueOf( a);
            String d = String.valueOf(c);

            holder.wmax.setText(b);
            holder.wmin.setText(d);


        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class).putExtra("model",dataList.getList().get(position));
                context.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return this.dataList.getList().size();
    }
}

