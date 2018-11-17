package br.com.vitornicacio.mensageirosdeemanuel;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);

        //pegando webview
        myWebView = findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClientImpl(this));
        if(temInternet()){
            //caregando site
            myWebView.loadUrl("https://vitor-nicacio.000webhostapp.com/SGA/Home");

            //pegando configurações para adicionar javascript
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            Anuncio anuncio = new Anuncio(this);
            anuncio.run();
        }else{
            startActivity(new Intent(MainActivity.this, ErroActivity.class));
        }
    }
    public void onResume(){
        super.onResume();
        verificar();

    }
    public void onStart(){
        super.onStart();
        verificar();
    }
    public void onStop(){
        super.onStop();
        verificar();
    }
    public void verificar(){
        if(!temInternet()){
            startActivity(new Intent(MainActivity.this, ErroActivity.class));
            this.finish();
        }
    }
    public boolean temInternet() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
    public Boolean getUrlDisponivel(){
        String urlName = null;
        if (urlName == null) {
            urlName = "https://vitor-nicacio.000webhostapp.com/SGA";
        }
        java.net.HttpURLConnection urlConnection = null;
        try {
            java.net.URL url = new java.net.URL(urlName.toString());
            urlConnection = (java.net.HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == java.net.HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed(){
        if(myWebView.canGoBack()){
            myWebView.goBack();
        }
        super.onBackPressed();

    }
}
