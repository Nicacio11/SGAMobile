package br.com.vitornicacio.mensageirosdeemanuel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ErroActivity extends AppCompatActivity {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erro);
        Anuncio anuncio = new Anuncio(this);
        anuncio.run();
    }

    public void back(View view){
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ErroActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    public void onStop(){
        super.onStop();
        finish();
    }
}
