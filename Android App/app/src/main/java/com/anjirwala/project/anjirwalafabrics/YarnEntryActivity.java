package com.anjirwala.project.anjirwalafabrics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class YarnEntryActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView backbutton;
    private EditText date_yarnentry;
    private EditText challannumber;
    private EditText lotnumber;
    private Spinner qualitySpinner;
    private EditText noofcartoons;
    private EditText company;
    private EditText netweight;
    private Button addyarn;
    private Button backtohome;

    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yarn_entry);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Yarn_Entry");

        backbutton = (ImageView) findViewById(R.id.back);
        date_yarnentry = (EditText) findViewById(R.id.date_yarnentry);
        challannumber = (EditText) findViewById(R.id.takanumber);
        lotnumber = (EditText) findViewById(R.id.lotnumber);
        noofcartoons = (EditText) findViewById(R.id.noofcartoons);
        company = (EditText) findViewById(R.id.company);
        netweight = (EditText) findViewById(R.id.netweight);
        backtohome =(Button) findViewById(R.id.backtohome);
        addyarn =(Button) findViewById(R.id.addyarn);

        backbutton.setOnClickListener(this);
        date_yarnentry.setOnClickListener(this);
        challannumber.setOnClickListener(this);
        lotnumber.setOnClickListener(this);
        noofcartoons.setOnClickListener(this);
        company.setOnClickListener(this);
        netweight.setOnClickListener(this);
        backtohome.setOnClickListener(this);
        addyarn.setOnClickListener(this);

        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        date_yarnentry.setText( sdf.format( new Date() ));
    }

    @Override
    public void onClick(View v) {
        if (v == backbutton || v == backtohome) {
            Intent i = new Intent(YarnEntryActivity.this, HomeActivity.class);
            YarnEntryActivity.this.startActivity(i);
            finish();
        }
        else if(v == addyarn)  {
            startaddingyarn();
        }
    }

    private void startaddingyarn() {

        String dateyarnentry = date_yarnentry.getText().toString();
        String challanno = challannumber.getText().toString();
        String lotno = lotnumber.getText().toString();
        String cartoons = noofcartoons.getText().toString();
        String companyname = company.getText().toString();
        String weight = netweight.getText().toString();

        if(!TextUtils.isEmpty(dateyarnentry) && !TextUtils.isEmpty(challanno) && !TextUtils.isEmpty(lotno) && !TextUtils.isEmpty(cartoons) && !TextUtils.isEmpty(companyname) && !TextUtils.isEmpty(weight))    {
            DatabaseReference newEntry = mDatabase.push();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newEntry.child("TimeStamp").setValue(df.format(c.getTime()));
            newEntry.child("Date_of_YarnEntry").setValue(dateyarnentry);
            newEntry.child("Challan_Number").setValue(challanno);
            newEntry.child("Lot_Number").setValue(lotno);
            newEntry.child("Number_of_Cartoons").setValue(cartoons);
            newEntry.child("Company_of_Yarn").setValue(companyname);
            newEntry.child("Net_Weight").setValue(weight, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Toast.makeText(YarnEntryActivity.this, "DataBase Error",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(YarnEntryActivity.this, "Yarn Added Successfully.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
            date_yarnentry.setText( sdf.format( new Date() ));
            challannumber.setText(null);
            lotnumber.setText(null);
            noofcartoons.setText(null);
            company.setText(null);
            netweight.setText(null);
        }
        else {
            Toast.makeText(YarnEntryActivity.this, "Please Fill all Details.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
