package com.example.greencity;

import android.graphics.Color;
import android.service.autofill.Dataset;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class EstadisticaActivity extends AppCompatActivity {
    PieChart pieChart;

    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);

        mChart = (BarChart) findViewById(R.id.char1);
        mChart.getDescription().setEnabled(false);
        setData(10);
        mChart.setFitBars(true);



        //grafico de pie
        pieChart =(PieChart) findViewById(R.id.piechar);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f, "Peru"));
        yValues.add(new PieEntry(23f, "USA"));
        yValues.add(new PieEntry(14f, "UK"));
        yValues.add(new PieEntry(35, "India"));
        yValues.add(new PieEntry(40, "Rusia"));
        yValues.add(new PieEntry(23, "Japon"));


        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data= new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);

    }
    private void setData(int count)
    {
        ArrayList<BarEntry> yVals = new ArrayList<>();
        for (int i=0; i < count;i++)
        {
            float value = (float) (Math.random()*100);
            yVals.add(new BarEntry(i, (int) value));
        }
        BarDataSet set = new BarDataSet(yVals,"Data Set");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data= new BarData(set);
        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);
    }


}
