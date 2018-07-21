package com.anjirwala.project.anjirwalafabrics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddQualityActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView backbutton;
    private EditText qualityname;
    private EditText weight;
    private EditText width;
    private EditText yarn;
    private Button addstock;
    private Button backtohome;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquality);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Quality_List");
        mProgress = new ProgressDialog(this);

        backbutton = (ImageView) findViewById(R.id.back);
        qualityname = (EditText) findViewById(R.id.qualityname);
        weight = (EditText) findViewById(R.id.weight);
        width = (EditText) findViewById(R.id.width);
        yarn = (EditText) findViewById(R.id.yarn);
        backtohome =(Button) findViewById(R.id.backtohome);
        addstock =(Button) findViewById(R.id.addyarn);

        backbutton.setOnClickListener(this);
        qualityname.setOnClickListener(this);
        weight.setOnClickListener(this);
        width.setOnClickListener(this);
        yarn.setOnClickListener(this);
        backtohome.setOnClickListener(this);
        addstock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == backbutton || v == backtohome) {
            Intent i = new Intent(AddQualityActivity.this, HomeActivity.class);
            AddQualityActivity.this.startActivity(i);
            finish();
        }
        else if(v == addstock)  {
            startaddingquality();
        }
    }

    private void startaddingquality() {
        String quality = qualityname.getText().toString();
        String weightofquality = weight.getText().toString();
        String widthofquality = width.getText().toString();
        String yarnofquality = yarn.getText().toString();

        if(!TextUtils.isEmpty(quality) && !TextUtils.isEmpty(weightofquality) && !TextUtils.isEmpty(widthofquality) && !TextUtils.isEmpty(yarnofquality))   {

            DatabaseReference newEntry = mDatabase.push();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newEntry.child("Time_TakaEntry").setValue(df.format(c.getTime()));
            newEntry.child("Quality_Name").setValue(quality);
            newEntry.child("Weight_of_Quality").setValue(weightofquality);
            newEntry.child("Width_of_Quality").setValue(widthofquality);
            newEntry.child("Yarn_Type").setValue(yarnofquality, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Toast.makeText(AddQualityActivity.this, "databaseError.getMessage()",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddQualityActivity.this, "Quality Added Successfully.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            qualityname.setText(null);
            weight.setText(null);
            width.setText(null);
            yarn.setText(null);
        }
        else {
            Toast.makeText(AddQualityActivity.this, "Please Fill all Details.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
