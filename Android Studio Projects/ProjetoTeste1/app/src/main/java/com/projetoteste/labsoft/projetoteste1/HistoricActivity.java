package com.projetoteste.labsoft.projetoteste1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class HistoricActivity extends AppCompatActivity {

    Button ultimasHoras;
    Button ultimosDias;
    Button mediaHoras;
    Button mediaDias;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("id",0L);
        String nomeMercado = intent.getStringExtra("nomeMercado");

        TextView textNomeMercado = findViewById(R.id.nomeMercado);
        textNomeMercado.setText(nomeMercado);
        textNomeMercado.setTextSize(25);

        final int green = Color.GREEN;
        final int yellow = Color.YELLOW;
        final int red = Color.RED;

        ultimasHoras = findViewById(R.id.historicoUltimasHoras);
        ultimasHoras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                BarChart barChart = findViewById(R.id.barchart);

                Calendar date = Calendar.getInstance();
                int hora = date.get(Calendar.HOUR_OF_DAY);

                ArrayList<BarEntry> entries = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<>();
                ArrayList<Integer> colors = new ArrayList<>();


                for (int i = 0; i < 24; i++){
                    Random r = new Random();
                    int Result = r.nextInt(100);
                    if (Result <= 30 ){
                        colors.add(green);
                    } else if (Result >= 70) {
                        colors.add(red);
                    } else {
                        colors.add(yellow);
                    }
                    int horaGraph = (i + hora) % 24;
                    entries.add(new BarEntry(Result, i));
                    labels.add(String.valueOf(horaGraph));
                }

                BarDataSet barDataSet = new BarDataSet(entries,"Cells");
                BarData data = new BarData(labels,barDataSet);

                barChart.setData(data);

                barChart.setDescription("Histórico das últimas 24 horas");

                barDataSet.setColors(colors);

                barChart.animateY(500);
            }
        });

        ultimosDias = findViewById(R.id.historicoUltimosDias);
        ultimosDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarChart barChart = findViewById(R.id.barchart);

                Calendar date = Calendar.getInstance();
                int diaDaSemana = date.get(Calendar.DAY_OF_WEEK);

                ArrayList<BarEntry> entries = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<>();
                ArrayList<Integer> colors = new ArrayList<>();

                for (int i = 0; i < 7; i++){
                    Random r = new Random();
                    int Result = r.nextInt(100);
                    if (Result <= 30 ){
                        colors.add(green);
                    } else if (Result >= 70) {
                        colors.add(red);
                    } else {
                        colors.add(yellow);
                    }
                    int dia = (i+diaDaSemana)%7 + 1;
                    if (dia == 1){
                        labels.add("Domingo");
                    } else if (dia == 2){
                        labels.add("Segunda");
                    } else if (dia == 3){
                        labels.add("Terça");
                    } else if (dia == 4){
                        labels.add("Quarta");
                    } else if (dia == 5){
                        labels.add("Quinta");
                    } else if (dia == 6){
                        labels.add("Sexta");
                    } else if (dia == 7){
                        labels.add("Sábado");
                    }
                    entries.add(new BarEntry(Result, i));
                }

                BarDataSet barDataSet = new BarDataSet(entries,"Cells");
                BarData data = new BarData(labels,barDataSet);

                barChart.setData(data);

                barChart.setDescription("Histórico dos últimos 7 dias");

                barDataSet.setColors(colors);

                barChart.animateY(500);

            }
        });

        mediaHoras = findViewById(R.id.historicoMediaHorária);
        mediaHoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BarChart barChart = findViewById(R.id.barchart);

                ArrayList<BarEntry> entries = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<>();
                ArrayList<Integer> colors = new ArrayList<>();

                for (int i = 0; i < 24; i++){
                    Random r = new Random();
                    int Result = r.nextInt(100);
                    if (Result <= 30 ){
                        colors.add(green);
                    } else if (Result >= 70) {
                        colors.add(red);
                    } else {
                        colors.add(yellow);
                    }
                    entries.add(new BarEntry(Result, i));
                    labels.add(String.valueOf(i));
                }

                BarDataSet barDataSet = new BarDataSet(entries,"Cells");
                BarData data = new BarData(labels,barDataSet);

                barChart.setData(data);

                barChart.setDescription("Histórico de médias horárias");

                barDataSet.setColors(colors);

                barChart.animateY(500);
            }
        });

        mediaDias = findViewById(R.id.historicoMediaSemanal);
        mediaDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BarChart barChart = findViewById(R.id.barchart);

                ArrayList<BarEntry> entries = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<>();
                ArrayList<Integer> colors = new ArrayList<>();

                for (int i = 0; i < 7; i++){
                    Random r = new Random();
                    int Result = r.nextInt(100);
                    if (Result <= 30 ){
                        colors.add(green);
                    } else if (Result >= 70) {
                        colors.add(red);
                    } else {
                        colors.add(yellow);
                    }
                    int dia = i + 1;
                    if (dia == 1){
                        labels.add("Domingo");
                    } else if (dia == 2){
                        labels.add("Segunda");
                    } else if (dia == 3){
                        labels.add("Terça");
                    } else if (dia == 4){
                        labels.add("Quarta");
                    } else if (dia == 5){
                        labels.add("Quinta");
                    } else if (dia == 6){
                        labels.add("Sexta");
                    } else if (dia == 7){
                        labels.add("Sábado");
                    }
                    entries.add(new BarEntry(Result, i));

                }

                BarDataSet barDataSet = new BarDataSet(entries,"Cells");
                BarData data = new BarData(labels,barDataSet);

                barChart.setData(data);

                barChart.setDescription("Histórico de médias horárias");

                barDataSet.setColors(colors);

                barChart.animateY(500);
            }
        });
    }
}
