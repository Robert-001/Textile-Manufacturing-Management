package com.anjirwala.project.anjirwalafabrics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TakaEntryActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView backbutton;
    private EditText date_takaentry;
    private EditText takanumber;
    private EditText machinenumber;
    private Spinner qualitySpinner;
    private EditText meter;
    private Button addstock;
    private Button backtohome;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Taka_Entry");

        backbutton = (ImageView) findViewById(R.id.back);
        date_takaentry = (EditText) findViewById(R.id.date_takaentry);
        takanumber = (EditText) findViewById(R.id.takanumber);
        machinenumber = (EditText) findViewById(R.id.lotnumber);
        //qualitySpinner = (Spinner) findViewById(R.id.Quality_list);
        meter = (EditText) findViewById(R.id.netweight);
        backtohome =(Button) findViewById(R.id.backtohome);
        addstock =(Button) findViewById(R.id.addyarn);

        backbutton.setOnClickListener(this);
        date_takaentry.setOnClickListener(this);
        takanumber.setOnClickListener(this);
        machinenumber.setOnClickListener(this);
        meter.setOnClickListener(this);
        //qualitySpinner.setOnClickListener(this);
        backtohome.setOnClickListener(this);
        addstock.setOnClickListener(this);

        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        date_takaentry.setText( sdf.format( new Date() ));

        /* FirebasexDatabase.getInstance().getReference().child("Quality_List").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> list = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String listName = areaSnapshot.child("Quality_Name").getValue(String.class);
                    list.add(listName);
                }

                Spinner areaSpinner = (Spinner) findViewById(R.id.qualitylist);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(TakaEntryActivity.this, android.R.layout.simple_spinner_item, list);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areaSpinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    */}
    @Override
    public void onClick(View v) {
        if (v == backbutton || v == backtohome) {
            Intent i = new Intent(TakaEntryActivity.this, HomeActivity.class);
            TakaEntryActivity.this.startActivity(i);
            finish();
        }
        else if(v == addstock)  {
            startaddingtaka();
        }
    }

    private void startaddingtaka() {

        String dateofentry = date_takaentry.getText().toString();
        String takano = takanumber.getText().toString();
        String machinno = machinenumber.getText().toString();
        String meteroftaka = meter.getText().toString();
        //String quality = String.valueOf(qualitySpinner.getSelectedItem());

        if(!TextUtils.isEmpty(dateofentry) && !TextUtils.isEmpty(takano) && !TextUtils.isEmpty(machinno) && !TextUtils.isEmpty(meteroftaka))    {
            DatabaseReference newEntry = mDatabase.push();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newEntry.child("TimeStamp").setValue(df.format(c.getTime()));
            newEntry.child("Date_of_TakaEntry").setValue(dateofentry);
            newEntry.child("Taka_Number").setValue(takano);
            newEntry.child("Machine_Number").setValue(machinno);
            //newEntry.child("Quality").setValue(quality);
            newEntry.child("Meter_of_Taka").setValue(meteroftaka, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Toast.makeText(TakaEntryActivity.this, "DataBase Error",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TakaEntryActivity.this, "Stock Added Successfully.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
            date_takaentry.setText( sdf.format( new Date() ));
            takanumber.setText(null);
            machinenumber.setText(null);
            meter.setText(null);
        }
        else {
            Toast.makeText(TakaEntryActivity.this, "Please Fill all Details.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
