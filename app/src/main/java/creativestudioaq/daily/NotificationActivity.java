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
public class NotificationActivity extends BaseActivity {

    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    SettingActivity Bactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        TextView notificationtext1 = (TextView)findViewById(R.id.notificationtext1);
        TextView notificationtext2 = (TextView)findViewById(R.id.notificationtext2);
        TextView notificationtext3 = (TextView)findViewById(R.id.notificationtext3);
        TextView notificationtext4 = (TextView)findViewById(R.id.notificationtext4);
        Button back = (Button) findViewById(R.id.back);


        notificationtext1.setText("10초");
        notificationtext2.setText("1분");
        notificationtext3.setText("10분");
        notificationtext4.setText("필요없다");


        Bactivity = SettingActivity.Bactivity;


        settingshared = getSharedPreferences("setting", 0);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.notificationtext1:
                        editor = settingshared.edit();
                        editor.putInt("noti", 0);
                        editor.apply();
                        stopService(new Intent(NotificationActivity.this, OnService.class));
                        startService(new Intent(NotificationActivity.this, OnService.class));
                        finish();
                        Bactivity.finish();
                        break;

                    case R.id.notificationtext2:
                        editor = settingshared.edit();
                        editor.putInt("noti", 1);
                        editor.apply();
                        stopService(new Intent(NotificationActivity.this, OnService.class));
                        startService(new Intent(NotificationActivity.this, OnService.class));
                        finish();


                        Bactivity.finish();
                        break;

                    case R.id.notificationtext3:
                        editor = settingshared.edit();
                        editor.putInt("noti", 2);
                        editor.apply();
                        stopService(new Intent(NotificationActivity.this, OnService.class));
                        startService(new Intent(NotificationActivity.this, OnService.class));
                        finish();



                        Bactivity.finish();
                        break;

                    case R.id.notificationtext4:
                        editor = settingshared.edit();
                        editor.putInt("noti", 3);
                        editor.apply();
                        stopService(new Intent(NotificationActivity.this, OnService.class));
                        startService(new Intent(NotificationActivity.this, OnService.class));
                        finish();



                        Bactivity.finish();
                        break;
                    case R.id.back:
                        finish();
                        break;


                }
            }
        };
        notificationtext1.setOnClickListener(click);
        notificationtext2.setOnClickListener(click);
        notificationtext3.setOnClickListener(click);
        notificationtext4.setOnClickListener(click);
        back.setOnClickListener(click);



    }
}
