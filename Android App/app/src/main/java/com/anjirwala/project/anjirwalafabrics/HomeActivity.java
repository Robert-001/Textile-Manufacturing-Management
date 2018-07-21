package com.anjirwala.project.anjirwalafabrics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private TextView user;
    private Button mLogOutBtn;
    private FirebaseAuth mAuth;
    private Button takaentry;
    private Button stocklist;
    private Button yarnentry;
    private Button yarnlist;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private boolean doubleBackToExitPressedOnce=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            }
        };
        mLogOutBtn = (Button) findViewById(R.id.logout);
        takaentry = (Button) findViewById(R.id.takaentrybtn);
        stocklist = (Button) findViewById(R.id.stocklistbtn);
        yarnentry = (Button) findViewById(R.id.yarnentrybtn);
        yarnlist = (Button) findViewById(R.id.yarnlistbtn);
        //user = (TextView) findViewById(R.id.useremail);

        //user.setText(mAuth.getCurrentUser().getEmail());

        takaentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TakaEntryActivity.class);
                HomeActivity.this.startActivity(i);
            }
        });

        stocklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, StockListActivity.class);
                HomeActivity.this.startActivity(i);
            }
        });

        yarnentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, YarnEntryActivity.class);
                HomeActivity.this.startActivity(i);
            }
        });

        yarnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, YarnListActivity.class);
                HomeActivity.this.startActivity(i);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addsub) {
            startActivity(new Intent(HomeActivity.this,AddQualityActivity.class));
        } else if (id == R.id.sublist) {
            startActivity(new Intent(HomeActivity.this,QualityListActivity.class));
        }
        else if (id == R.id.addquality) {
            startActivity(new Intent(HomeActivity.this,AddQualityActivity.class));
        } else if (id == R.id.qualitylist) {
            startActivity(new Intent(HomeActivity.this, QualityListActivity.class));
        }
        else if (id == R.id.logout) {
            Toast.makeText(HomeActivity.this, "Successfully Signed Out", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            finish();
        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
