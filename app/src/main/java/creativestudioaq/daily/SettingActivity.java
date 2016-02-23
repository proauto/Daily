package creativestudioaq.daily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by honggyu on 2016-01-06.
 */
public class SettingActivity extends BaseActivity {

    public static SettingActivity Bactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Bactivity = SettingActivity.this;

        TextView status = (TextView) findViewById(R.id.status);
        TextView notification = (TextView) findViewById(R.id.notification);
        TextView information = (TextView) findViewById(R.id.information);
        TextView guildbook = (TextView) findViewById(R.id.guildbook);
        TextView tip = (TextView)findViewById(R.id.tip);
        Button back = (Button) findViewById(R.id.back);


        status.setText("상태바 스타일");
        notification.setText("알림 타이밍 설정");
        information.setText("Information");
        guildbook.setText("사용설명서");
        tip.setText("용사의 하루 팁");

        View.OnClickListener buttonslistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {


                    case R.id.status:

                        Intent intent2 = new Intent(SettingActivity.this, StatusActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.notification:

                        Intent intent3 = new Intent(SettingActivity.this, NotificationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.information:

                        Intent intent4 = new Intent(SettingActivity.this, InformationActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.back:
                        finish();
                        break;

                    case R.id.guildbook:
                        Intent intent5 = new Intent(SettingActivity.this, HowtouseActivity.class);
                        startActivity(intent5);
                        break;

                    case R.id.tip:
                        Intent intent6 = new Intent(SettingActivity.this, TipActivity.class);
                        startActivity(intent6);
                        break;

                }
            }
        };


        status.setOnClickListener(buttonslistener);
        notification.setOnClickListener(buttonslistener);
        information.setOnClickListener(buttonslistener);
        back.setOnClickListener(buttonslistener);
        guildbook.setOnClickListener(buttonslistener);
        tip.setOnClickListener(buttonslistener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        findViewById(R.id.test).setBackground(null);
        System.gc();
    }
}
