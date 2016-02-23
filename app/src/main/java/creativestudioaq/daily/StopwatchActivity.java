package creativestudioaq.daily;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by honggyu on 2016-01-06.
 */
public class StopwatchActivity extends BaseActivity {

    Chronometer cm;
    LinearLayout resultLayout;
    int num = 1;
    long timeWhenStopped = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        cm = (Chronometer) findViewById(R.id.chronmeter1);
        final ToggleButton tb = (ToggleButton) findViewById(R.id.buttonstart);
        Button marking = (Button) findViewById(R.id.buttonmarking);
        Button end = (Button) findViewById(R.id.buttonend);
        Button back = (Button) findViewById(R.id.back);
        Button inventory = (Button) findViewById(R.id.buttoninventory);
        Button setting = (Button) findViewById(R.id.buttonsetting);
        resultLayout = (LinearLayout) findViewById(R.id.LinearLayout1);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.buttonstart:
                        if (tb.isChecked()) {
                            cm.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                            cm.start();
                            num = 1;

                        } else {
                            timeWhenStopped = cm.getBase() - SystemClock.elapsedRealtime();
                            cm.stop();
                        }
                        break;

                    case R.id.buttonmarking:
                        markRecord();
                        break;

                    case R.id.buttonend:
                        cm.setBase(SystemClock.elapsedRealtime());
                        resultLayout.removeAllViews();
                        num = 1;
                        timeWhenStopped = 0;
                        tb.setChecked(false);
                        cm.stop();
                        break;

                    case R.id.back:
                        finish();
                        break;

                    case R.id.buttoninventory:
                        Intent intent1 = new Intent(StopwatchActivity.this, InventoryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.buttonsetting:
                        Intent intent2 = new Intent(StopwatchActivity.this, SettingActivity.class);
                        startActivity(intent2);
                        break;

                }
            }
        };

        marking.setOnClickListener(click);
        end.setOnClickListener(click);
        tb.setOnClickListener(click);
        back.setOnClickListener(click);
        inventory.setOnClickListener(click);
        setting.setOnClickListener(click);

    }
    private void markRecord(){
        TextView recordView = new TextView(this);
        recordView.setTextAppearance(getApplicationContext(),R.style.BaseText);
        recordView.setText("   " + cm.getText()+"\n");
        recordView.setTextSize(20f);
        recordView.setTypeface(mTypeface);
        resultLayout.addView(recordView);
        num++;
    }

}


