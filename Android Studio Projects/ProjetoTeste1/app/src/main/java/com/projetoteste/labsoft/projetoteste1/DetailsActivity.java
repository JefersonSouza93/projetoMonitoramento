package com.projetoteste.labsoft.projetoteste1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private Button consultarHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int lotacao = intent.getIntExtra("lotacao", 0);
        int maximo = intent.getIntExtra("maximo", 0);
        final String nomeMercado = intent.getStringExtra("nomeMercado");
        final Long idMercado = intent.getLongExtra("id",0L);

        TextView textNomeMercado = findViewById(R.id.nomeMercado);
        textNomeMercado.setText(nomeMercado);
        textNomeMercado.setTextSize(25);
        TextView textLotacao = findViewById(R.id.lotacaoValue);
        textLotacao.setText(String.valueOf(lotacao));
        TextView textMaximo = findViewById(R.id.MaximoValue);
        textMaximo.setText(String.valueOf(maximo));

        consultarHistorico = findViewById(R.id.consultarHistorico);
        consultarHistorico.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, HistoricActivity.class);
                intent.putExtra("nomeMercado", nomeMercado);
                intent.putExtra("id", idMercado );
                startActivity(intent);
            }
        });

    }

}
