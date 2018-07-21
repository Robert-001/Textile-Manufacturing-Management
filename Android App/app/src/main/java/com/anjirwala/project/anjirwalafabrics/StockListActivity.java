package com.anjirwala.project.anjirwalafabrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StockListActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView backbutton;
    private RecyclerView mStocklist;

    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("Taka_Entry");

        backbutton = (ImageView) findViewById(R.id.back);
        mStocklist = (RecyclerView) findViewById(R.id.stock_list);
        mStocklist.setHasFixedSize(true);
        mStocklist.setLayoutManager(new LinearLayoutManager(this));

        backbutton.setOnClickListener(this);
    }

    //ArrayList<Stock> stocks = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();

        //stocks.clear();
        FirebaseRecyclerAdapter<Stock,StockViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Stock,StockViewHolder> (
                Stock.class,
                R.layout.stock_row,
                StockViewHolder.class,
                mdatabase
        ){
          @Override
            protected void populateViewHolder(StockViewHolder viewHolder, Stock model, int position)    {

              //stocks.add(model);

              viewHolder.setDate(model.getDate_of_TakaEntry());
              viewHolder.setMeter(model.getMeter_of_Taka());
              viewHolder.setTakanumber(model.getTaka_Number());
              //viewHolder.setQuality(model.getQuality_Name());
              viewHolder.setMachinenumber(model.getMachine_Number());
          }
        };
        mStocklist.setAdapter(firebaseRecyclerAdapter);
    }

    public static class  StockViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public StockViewHolder(View itemView) {
            super(itemView);
            mView = itemView;


        }

        //void btnClick() { String sds = stocks.get(0).getTaka_Number(); }

        public void setDate(String Date_of_TakaEntry)  {
            TextView date=(TextView)mView.findViewById(R.id.date);
            date.setText(Date_of_TakaEntry);
        }
        public void setMeter(String Meter_of_Taka)  {
            TextView meters=(TextView)mView.findViewById(R.id.meters);
            meters.setText(Meter_of_Taka);
        }
        public void setTakanumber(String Taka_Number)  {
            TextView takanumber=(TextView)mView.findViewById(R.id.takanumber);
            takanumber.setText(Taka_Number);
        }
        public void setMachinenumber(String Machine_Number)  {
            TextView machinenumber=(TextView)mView.findViewById(R.id.machinenumber);
            machinenumber.setText(Machine_Number);
        }
        //public void setQuality(String Quality_Name)  {
        //    TextView quality=(TextView)mView.findViewById(R.id.quality);
        //    quality.setText(Quality_Name);
        //}
    }

    @Override
    public void onClick(View v) {
        if (v == backbutton) {
            Intent i = new Intent(StockListActivity.this, HomeActivity.class);
            StockListActivity.this.startActivity(i);
            finish();
        }
    }
}
