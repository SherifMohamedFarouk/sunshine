package com.veirn.weatherapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import static com.veirn.weatherapp.pref.ENABLED;
import static com.veirn.weatherapp.pref.getsharedPref;

public class Settings extends AppCompatActivity {
     public static SharedPreferences  myPrefs;
    public  EditText editText ;
    public RadioButton Metric ;
    public  RadioButton Imperial;
    public TextView Unitss , en;
    public CheckBox checkBox ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView tview = findViewById(R.id.Location);
                tview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCreateDialog ();

                    }
                });
        TextView ttview = findViewById(R.id.uints);
        ttview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog1 ();

            }
        });
        myPrefs = Settings.this.getSharedPreferences("prefID", Context.MODE_PRIVATE);

        TextView textView= findViewById(R.id.Locationmap);
        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

        String name = myPrefs.getString("nameKey","Default");
        textView.setText(name);
        Metric = findViewById(R.id.metric);
        Imperial=findViewById(R.id.Imperial);

        Unitss = findViewById(R.id.uintss);

        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

        String name1 = myPrefs.getString("Metric","Default");
        Unitss.setText(name1);




        en = findViewById(R.id.En);
      myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        myPrefs.getString(ENABLED,"Enabled");
        String ena = myPrefs.getString("Enabled","Enabled");
        en.setText(ena);
        checkBox = findViewById(R.id.Notfications);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(checkBox.isChecked()){
                    String a= "Enabled";
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.putString("Enabled", a);
                    editor.apply();
                    myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

                    String ena = myPrefs.getString("Enabled","Enabled");
                    en.setText(ena);

                }
                else{
                    String a= "Disabled";
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.putString("Enabled", a);
                    editor.apply();
                    myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

                    String disa = myPrefs.getString("Enabled","Enabled");
                    en.setText(disa);
                }


            }
        });
        if (en.getText().equals("Enabled")){
            checkBox.setChecked(true);

        }
        else if(en.getText().equals("Disabled")){
            checkBox.setChecked(false);

        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);




    }


    public void onCreateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        // Get the layout inflater
        LayoutInflater inflater = Settings.this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_location, null);
        editText=view.findViewById(R.id.username);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        TextView textView= findViewById(R.id.Locationmap);
                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.putString("nameKey", editText.getText().toString());
                        editor.apply();
                        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

                        String name = myPrefs.getString("nameKey","Default");
                        textView.setText(name);




                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
         builder.create().show();
    }


    public void onCreateDialog1() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        // Get the layout inflater
        LayoutInflater inflater = Settings.this.getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.radiobutton_dialog, null);
        Metric = view.findViewById(R.id.metric);
        Imperial=view.findViewById(R.id.Imperial);

        if(Unitss.getText().equals("Metric")){
            Metric.setChecked(true);

        }
        else if (Unitss.getText().equals("Imperial")){
            Imperial.setChecked(true);

        }
        builder.setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if(Metric.isChecked()){
                    String a= "Metric";
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.putString("Metric", a);
                    editor.apply();
                    myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

                    String name1 = myPrefs.getString("Metric","Default");
                    Unitss.setText(name1);
                    Metric.setChecked(true);

                }
                else if (Imperial.isChecked()){
                    String a= "Imperial";
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.putString("Metric", a);
                    editor.apply();
                    myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

                    String name2 = myPrefs.getString("Metric","Default");
                    Unitss.setText(name2);
                    Imperial.setChecked(true);

                }



            }
        })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        builder.create().show();




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
