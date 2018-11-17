package br.com.vitornicacio.mensageirosdeemanuel;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by josyn on 06/11/2018.
 */

public class Anuncio {
    private final String APP_ID = "ca-app-pub-5069015322638131~997792826";
    private final String ADS_ID = "ca-app-pub-5069015322638131/9620913034";
    private final String TESTE = "ca-app-pub-3940256099942544/1033173712";
    private InterstitialAd anuncio;


    public Anuncio(Context context){
        MobileAds.initialize(context, APP_ID);
        anuncio = new InterstitialAd(context);
        anuncio.setAdUnitId(TESTE);

        anuncio.loadAd(new AdRequest.Builder().build());
        anuncio.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                exibir();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                anuncio.loadAd(new AdRequest.Builder().build());
            }
            @Override
            public void onAdLeftApplication() {
                anuncio.loadAd(new AdRequest.Builder().build());
                exibir();
            }
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                anuncio.loadAd(new AdRequest.Builder().build());
            }

        });
    }

    private void exibir(){

        if(anuncio.isLoaded()){
            anuncio.show();
        }

    }
    public void run(){
        //2 minutos e meio
        long TEMPO = (1000 * 150);
    
        Timer timer = null;
        if(timer == null){
            timer = new Timer();
            TimerTask tarefa = new TimerTask(){
                public void run(){
                    try{
                        exibir();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(tarefa, TEMPO, TEMPO);
        }
    }

}
