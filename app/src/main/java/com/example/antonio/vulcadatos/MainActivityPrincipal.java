package com.example.antonio.vulcadatos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.antonio.vulcadatos.fragments.EstacionGpsFrag;
import com.example.antonio.vulcadatos.fragments.EstacionAceleroFrag;
import com.example.antonio.vulcadatos.fragments.InicioFrag;

public class MainActivityPrincipal extends AppCompatActivity


        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www2.sgc.gov.co/Noticias/Paginas/Historico-de-noticias.aspx");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Class fragmentClass= InicioFrag.class;
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Fragment fragment = null;
        Class fragmentClass= InicioFrag.class;
        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        setTitle("Servicio Geologico");

    }

    private class MyWebViewClient extends WebViewClient
    {

        public boolean shouldOverrideUrlLoading (WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            AlertDialog.Builder uBuilder2 = new AlertDialog.Builder(MainActivityPrincipal.this);
            View aView2 = getLayoutInflater().inflate(R.layout.acercade, null);
            uBuilder2.setView(aView2);
            final AlertDialog dialog2 = uBuilder2.create();
            dialog2.show();
            Button close = (Button) aView2.findViewById(R.id.close);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog2.cancel();
                }
            });

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;
        Class fragmentClass= InicioFrag.class;



        int id = item.getItemId();

        if (id == R.id.gps) {
            // Handle the camera action
            fragmentClass = EstacionGpsFrag.class;

        } else if (id == R.id.sismo) {
            fragmentClass = EstacionAceleroFrag.class;

        } else if (id == R.id.acercaDe) {

            AlertDialog.Builder uBuilder2 = new AlertDialog.Builder(MainActivityPrincipal.this);
            View aView2 = getLayoutInflater().inflate(R.layout.acercade, null);
            uBuilder2.setView(aView2);
            final AlertDialog dialog2 = uBuilder2.create();
            dialog2.show();
            Button close = (Button) aView2.findViewById(R.id.close);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog2.cancel();
                }
            });

        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }


        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
