package br.com.vitornicacio.mensageirosdeemanuel;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by josyn on 24/10/2018.
 */

public class WebViewClientImpl extends WebViewClient {
    private Activity activity = null;


    public WebViewClientImpl(Activity activity) {
        this.activity = activity;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url){
        if(url.contains("vitor-nicacio.000webhostapp.com/SGA/")) return false;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }

}

