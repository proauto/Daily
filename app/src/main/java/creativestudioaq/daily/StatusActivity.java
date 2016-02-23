package creativestudioaq.daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by honggyu on 2015-12-21.
 */
public class StatusActivity extends BaseActivity {

    SharedPreferences settingshared;
    SharedPreferences.Editor editor;

    
    SettingActivity Bactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        TextView statustext1 = (TextView)findViewById(R.id.statustext1);
        TextView statustext2 = (TextView)findViewById(R.id.statustext2);
        TextView statustext3 = (TextView)findViewById(R.id.statustext3);
        TextView statustext4 = (TextView)findViewById(R.id.statustext4);
        TextView statustext5 = (TextView)findViewById(R.id.statustext5);
        TextView statustext6 = (TextView)findViewById(R.id.statustext6);
        TextView statustext7 = (TextView)findViewById(R.id.statustext7);
        TextView statustext8 = (TextView)findViewById(R.id.statustext8);
        Button back = (Button) findViewById(R.id.back);


        statustext1.setText("1번 테마");
        statustext2.setText("2번 테마");
        statustext3.setText("3번 테마");
        statustext4.setText("4번 테마");
        statustext5.setText("5번 테마");
        statustext6.setText("6번 테마");
        statustext7.setText("7번 테마");
        statustext8.setText("8번 테마");

        settingshared = getSharedPreferences("setting", 0);




        Bactivity = SettingActivity.Bactivity;

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.statustext1:
                        editor = settingshared.edit();
                        editor.putInt("thema", 0);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();

                        Bactivity.finish();
                        break;

                    case R.id.statustext2:
                        editor = settingshared.edit();
                        editor.putInt("thema", 1);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;

                    case R.id.statustext3:
                        editor = settingshared.edit();
                        editor.putInt("thema", 2);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;

                    case R.id.statustext4:
                        editor = settingshared.edit();
                        editor.putInt("thema", 3);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;
                    case R.id.statustext5:
                        editor = settingshared.edit();
                        editor.putInt("thema", 4);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;
                    case R.id.statustext6:
                        editor = settingshared.edit();
                        editor.putInt("thema", 5);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;
                    case R.id.statustext7:
                        editor = settingshared.edit();
                        editor.putInt("thema", 6);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;
                    case R.id.statustext8:
                        editor = settingshared.edit();
                        editor.putInt("thema", 7);
                        editor.apply();
                        stopService(new Intent(StatusActivity.this, OnService.class));
                        startService(new Intent(StatusActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;
                    case R.id.back:
                        finish();
                        break;

                }
            }
        };
        statustext1.setOnClickListener(click);
        statustext2.setOnClickListener(click);
        statustext3.setOnClickListener(click);
        statustext4.setOnClickListener(click);
        statustext5.setOnClickListener(click);
        statustext6.setOnClickListener(click);
        statustext7.setOnClickListener(click);
        statustext8.setOnClickListener(click);
        back.setOnClickListener(click);




    }
}
