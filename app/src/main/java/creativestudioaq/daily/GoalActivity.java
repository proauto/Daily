package creativestudioaq.daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by honggyu on 2015-12-21.
 */
public class GoalActivity extends BaseActivity {


    DatePicker datepic;
    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    String realday;
    String goal;
    EditText goalinsert;
    MainActivity Aactivity;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            number=intent.getExtras().getInt("send");

        }

        settingshared =getSharedPreferences("setting", 0);


        TextView ddaytext = (TextView)findViewById(R.id.ddaytext);
        TextView whatday = (TextView)findViewById(R.id.whatisdday);
        TextView comeon = (TextView)findViewById(R.id.comeon);
        Button buttondday = (Button)findViewById(R.id.buttondday);
        datepic = (DatePicker)findViewById(R.id.dpday);
        Button back = (Button) findViewById(R.id.back);
        goalinsert = (EditText)findViewById(R.id.insertgoal);


        ddaytext.setText("용사여, 그대의 큰 뜻은 무엇인가");
        whatday.setText("결전의 날을 입력하시오 (듸-데이)");
        comeon.setText("목표와 디데이를 설정하면 상단바와 푸쉬알림으로\n너를 자극시켜줄게 콤온~");

        Aactivity = MainActivity.Aactivity;

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.back:
                        finish();
                        break;

                }

            }
        };


        back.setOnClickListener(click);


        buttondday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar ddaycalendar = new GregorianCalendar(
                        datepic.getYear(), datepic.getMonth(), datepic.getDayOfMonth()
                );
                Calendar calendar = Calendar.getInstance();

                long diffday = ddaycalendar.getTimeInMillis() - calendar.getTimeInMillis();


                if (diffday < 0) {

                    Toast toast = Toast.makeText(GoalActivity.this, "디데이를 올바르게 설정해주세요~!", Toast.LENGTH_SHORT);
                    toast.show();


                } else {
                    int res1 = (int)(diffday / (1000 * 60 * 60 * 24))+1;

                    String res = Long.toString((diffday / (1000 * 60 * 60 * 24)) + 1);
                    realday="D-" + res;

                    String goalchange = goalinsert.getText().toString();
                    goalchange = goalchange.replace("'", "''");

                    editor = settingshared.edit();
                    editor.putInt("realint", res1);
                    editor.putString("goal", goalchange);
                    editor.putInt("dayofweek", 100);
                    editor.apply();

                    Aactivity.goal.setText(goalchange);

                    stopService(new Intent(GoalActivity.this, OnService.class));
                    startService(new Intent(GoalActivity.this, OnService.class));

                    finish();



                }



            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
