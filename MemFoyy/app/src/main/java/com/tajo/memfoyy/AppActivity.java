package com.tajo.memfoyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Currency;
import java.util.Objects;

public class AppActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar actionbarApp;
    private ViewPager vpMain;
    private TabLayout tabsMain;
    private TabsAdapter tabsAdapter;
    private FirebaseUser currentUser;
    private FirebaseAuth auth;
    private DatabaseReference veriyolu;
    String userID;

    public void init() {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        veriyolu = FirebaseDatabase.getInstance().getReference();
        if (currentUser != null)
            userID = auth.getCurrentUser().getUid();

        vpMain = (ViewPager) findViewById(R.id.vpMain);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        vpMain.setAdapter(tabsAdapter);

        tabsMain = (TabLayout) findViewById(R.id.tabsMain);
        tabsMain.setupWithViewPager(vpMain);
    }

    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawe_layour);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfilFragment()).commit();
            navigationView.setCheckedItem(R.id.bilgilerim);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sayiGame:
                Intent sayiIntent = new Intent(AppActivity.this, SayiOyunu.class);
                startActivity(sayiIntent);
                break;
            case R.id.tekrarGame:
                Intent tekrarIntent = new Intent(AppActivity.this, TekrarOyunu.class);
                startActivity(tekrarIntent);
                break;
            case R.id.hafizaGame:
                Intent hafizaIntent = new Intent(AppActivity.this, HafizaOyunu.class);
                startActivity(hafizaIntent);
                break;

            case R.id.kisisel_ekle:
                Intent kisiselIntent = new Intent(AppActivity.this, Bilgilerim.class);
                startActivity(kisiselIntent);
                break;
            case R.id.d_kisi_ekle:
                Intent kisiIntent = new Intent(AppActivity.this, kisiKayitActivity.class);
                startActivity(kisiIntent);
                break;
            case R.id.d_ani_ekle:
                Intent aniIntent = new Intent(AppActivity.this, AniKayitActivty.class);
                startActivity(aniIntent);
                break;
            case R.id.d_not_ekle:
                Intent notIntent = new Intent(AppActivity.this, NotKayitActivity.class);
                startActivity(notIntent);
                break;

            case R.id.hakkinda:
                Intent hakkindaIntent = new Intent(AppActivity.this, Hakkinda.class);
                startActivity(hakkindaIntent);
                break;
            case R.id.cikis:
                auth.signOut();
                Intent cikisIntent = new Intent(AppActivity.this, MainActivity.class);
                startActivity(cikisIntent);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onStart() {
        if (currentUser == null) {
            Intent welcomeIntent = new Intent(AppActivity.this, MainActivity.class);
            startActivity(welcomeIntent);
            finish();
        }
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.bilgilerim) {
            Intent bilgiıntent = new Intent(AppActivity.this, Bilgilerim.class);
            startActivity(bilgiıntent);
        }
        if (item.getItemId() == R.id.info_kisi) {
            Intent kisiEkleIntent = new Intent(AppActivity.this, kisiKayitActivity.class);
            startActivity(kisiEkleIntent);
        }
        if (item.getItemId() == R.id.info_ani) {
            Intent aniEkleIntent = new Intent(AppActivity.this, AniKayitActivty.class);
            startActivity(aniEkleIntent);
        }
        if (item.getItemId() == R.id.info_not) {
            Intent aniEkleIntent = new Intent(AppActivity.this, NotKayitActivity.class);
            startActivity(aniEkleIntent);
        }
        if (item.getItemId() == R.id.logOut) {
            auth.signOut();
            Intent cikisIntent = new Intent(AppActivity.this, MainActivity.class);
            startActivity(cikisIntent);
            finish();
        }
        if (item.getItemId() == R.id.info) {
            Intent hakkindaIntent = new Intent(AppActivity.this, Hakkinda.class);
            startActivity(hakkindaIntent);
        }
        return true;
    }

    public void btnclk(View v) {
        Intent aniIntent = new Intent(AppActivity.this, AniKayitActivty.class);
        startActivity(aniIntent);
    }

    public void btnclk2(View v) {
        Intent kisiIntent = new Intent(AppActivity.this, kisiKayitActivity.class);
        startActivity(kisiIntent);
    }

    public void btnclk5(View v){
        Intent anıIntent = new Intent(AppActivity.this, NotKayitActivity.class);
        startActivity(anıIntent);
    }

    public void btnclk3(View v) {
        recreate();
    }

    public void btnclk4(View v) {
        recreate();
    }

    public void btnclk6(View v) {
        recreate();
    }





}