package com.codeminator.attndr.reports;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.codeminator.attndr.MainActivity;
import com.codeminator.attndr.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private PieChart mChart;
    protected ArrayList<String> mParties = new ArrayList<>();
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    Button details;

    private Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_summary);

        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setDescription("Attendance Analysis");
        mChart.setDescriptionTextSize(24);
        mChart.setUsePercentValues(true);
//        mChart.setHoleColorTransparent(true);

        int a = 0;
        if(MainActivity.p1.present)
            a++;
        if(MainActivity.p2.present)
            a++;
        if(MainActivity.p3.present)
            a++;
        if(MainActivity.p4.present)
            a++;
        if(MainActivity.p5.present)
            a++;
        int b = a*100/5;
        int c = 100 -b;
        String title = b+"% Present \n" + c + "%Absent";
        mChart.setCenterText(title);
        setData(1, 100);
        mChart.setCenterTextSizePixels(50);

        details = (Button) findViewById(R.id.detail_button);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SummaryActivity.this, GraphDetailActivity.class);
                startActivity(i);
            }
        });
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Attndr\ndeveloped by Codeminators");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        mParties.add("Absent");
        mParties.add("Present");

        for (int i = 0; i < count + 1; i++) {

        }

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
//        for (int i = 0; i < count + 1; i++) {
//            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
//        }
        yVals1.add(new Entry((float) 60.0, 0));
        yVals1.add(new Entry((float) 40.0, 1));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties.get(i % mParties.size()));



        PieDataSet dataSet = new PieDataSet(yVals1, "Key");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
}
