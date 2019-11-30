package com.example.greencity;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.greencity.Intercambio.GananciasRequest;
import com.example.greencity.Intercambio.GananciasResponse;
import com.example.greencity.Servicio.ApiService;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstadisticaFragment extends Fragment {

    Spinner opciones;

    PieChart pieChart;

    //private BarChart mChart;
    private BarChart mChart;
    private HorizontalBarChart chart;
    //private Typeface tf;
    public EstadisticaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_estadistica, container, false);


        opciones =(Spinner) view.findViewById(R.id.sp01);





        chart = view.findViewById(R.id.char1);
        //chart.setOnChartValueSelectedListener(this);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setTypeface(tfLight);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);

        YAxis yl = chart.getAxisLeft();
        //yl.setTypeface(tfLight);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis yr = chart.getAxisRight();
        //yr.setTypeface(tfLight);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.setFitBars(true);
        //chart.animateY(2500);
        chart.animateXY(2000, 2000);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
        setData(5,5);
        /*mChart = (BarChart) view.findViewById(R.id.char1);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(60);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        setData(10);
        mChart.setFitBars(true);*/


        //grafico de pie
        pieChart =(PieChart) view.findViewById(R.id.piechar);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);


        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);//WHITE
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);
        // add a selection listener

        pieChart.animateY(1400, Easing.EaseInOutQuad);

        Legend l2 = pieChart.getLegend();
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l2.setOrientation(Legend.LegendOrientation.VERTICAL);
        l2.setDrawInside(false);
        l2.setXEntrySpace(7f);
        l2.setYEntrySpace(0f);
        l2.setYOffset(0f);

        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);

        //apanta retrofit
//        final GananciasRequest oReq = new GananciasRequest(0,null);
//        Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(Global.URL_API)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<List<GananciasResponse>> call = apiService.getGananciasxUsuario(oReq);
//        call.enqueue(new Callback<List<GananciasResponse>>() {
//            @Override
//            public void onResponse(Call<List<GananciasResponse>> call, Response<List<GananciasResponse>> response) {
//                if (!response.isSuccessful()){
//                                return;
//                }
//                List<GananciasResponse> gananciasResponseList = response.body();
//                ArrayList<PieEntry> yValues = new ArrayList<>();
//                for (GananciasResponse gananciasResponse:gananciasResponseList){
//
//                     yValues.add(new PieEntry(34f, gananciasResponse.getNom_material()));
//                }
//                PieDataSet dataSet = new PieDataSet(yValues,"Materiales");
//            }
//
//            @Override
//            public void onFailure(Call<List<GananciasResponse>> call, Throwable t) {
//
//            }
//        });

        String[] parties = new String[] {
                "Latas y Envases de Refrescos", "Papel y Cartón", "Guías Telefónicas", "Metales", "Botellas", "Party G", "Party H",
                "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
                "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
                "Party Y", "Party Z"
        };

        //ArrayList<PieEntry> yValues = new ArrayList<>();
       // yValues.add(new PieEntry(34f, "Papel y Carton"));
        //yValues.add(new PieEntry(23f, "Metales"));
        //yValues.add(new PieEntry(14f, "Guias Telefonicas"));
        //yValues.add(new PieEntry(35, "Latas y envases de refrescos"));
        //yValues.add(new PieEntry(40, "Peru"));
        //yValues.add(new PieEntry(23, "Japon"));
        //yValues.add(new PieEntry(23, "Chile"));
        //PieDataSet dataSet = new PieDataSet(yValues,"Materiales");


        //apanta
        ArrayList<PieEntry> entries = new ArrayList<>();
         //NOTE: The order of the entries when being added to the entries array determines their position around the center of
         //the chart.
        //for (int i = 0; i < 5; i++) {
        entries.add(new PieEntry(42, parties[0 % parties.length]));
        entries.add(new PieEntry(87, parties[1 % parties.length]));
        entries.add(new PieEntry(57, parties[2 % parties.length]));
        entries.add(new PieEntry(33, parties[3 % parties.length]));
        entries.add(new PieEntry(44, parties[4 % parties.length]));
        //}
        PieDataSet dataSet = new PieDataSet(entries, "Recolección");


        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);//JOYFUL_COLORS
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData data= new PieData(dataSet);


        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);//YELLOW
        pieChart.setData(data);



        pieChart.setDrawHoleEnabled(false);
        pieChart.highlightValues(null);
        pieChart.invalidate();
        pieChart.animateX(1400);
        //apanta




        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(getActivity(), R.array.opciones,android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pieChart.animateX(1400);
                pieChart.animateY(1400, Easing.EaseInOutQuad);
                chart.animateXY(2000, 2000);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;

    }

    /*private void setData(int count)
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
    }*/
    private void setData(int count, float range) {
        float barWidth = 9f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> values = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * range);
//            values.add(new BarEntry(i * spaceForBar, val,getResources().getDrawable(R.drawable.lata)));
//        }
        values.add(new BarEntry(0 * spaceForBar, 12,getResources().getDrawable(R.drawable.lata)));
        values.add(new BarEntry(1 * spaceForBar, 24,getResources().getDrawable(R.drawable.papelycarton)));
        values.add(new BarEntry(2 * spaceForBar, 17,getResources().getDrawable(R.drawable.guias)));
        values.add(new BarEntry(3 * spaceForBar, 12,getResources().getDrawable(R.drawable.botella2)));
        values.add(new BarEntry(4 * spaceForBar, 9,getResources().getDrawable(R.drawable.metales)));


        BarDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Materiales");

            //set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            //data.setValueTypeface(tfLight);
            data.setBarWidth(barWidth);
            chart.setData(data);

        }


        List<IBarDataSet> sets = chart.getData()
                .getDataSets();

        for (IBarDataSet iSet : sets) {
            iSet.setDrawValues(!iSet.isDrawValuesEnabled());
        }

        chart.invalidate();


    }

}
