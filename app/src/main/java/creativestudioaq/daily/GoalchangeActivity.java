package creativestudioaq.daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by honggyu on 2016-01-06.
 */
public class GoalchangeActivity extends BaseActivity {

    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    int beforescore,score;
    MainActivity Aactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalchange);

        Button back = (Button)findViewById(R.id.backbutton);
        Button success = (Button)findViewById(R.id.success);
        Button giveup = (Button)findViewById(R.id.giveup);
        TextView goaltext = (TextView)findViewById(R.id.goaltext);
        TextView caution = (TextView)findViewById(R.id.caution);

        goaltext.setText("당신의 목표를\n모두 이루셨습니까?");
        caution.setText("*경고*\n\n용사님의 양심에 맡깁니다.\n목표는 한번 작성하면 수정할 수 없습니다.");

        Aactivity = MainActivity.Aactivity;

        settingshared = getSharedPreferences("setting", 0);

        beforescore = settingshared.getInt("score",0);


        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(v.getId()){
                    case R.id.backbutton:
                        finish();
                        break;
                    case R.id.success:
                        score=beforescore+10;
                        editor = settingshared.edit();
                        editor.putInt("score", score);
                        editor.putString("goal","");
                        editor.apply();

                        int lv = settingshared.getInt("level",1);
                        int speciallv= settingshared.getInt("speciallevel",0);
                        int reallv = 1;

                        if(speciallv==0) {

                            if (score >= 409) {
                                reallv = 15;
                                editor = settingshared.edit();
                                editor.putInt("level", 15);
                                editor.putString("item", "천상의 루비나이트 빙룡검");
                                editor.apply();
                            } else if (score >= 359) {
                                reallv = 14;
                                editor = settingshared.edit();
                                editor.putInt("level", 14);
                                editor.putString("item", "루비나이트 빙룡검");
                                editor.apply();
                            } else if (score >= 309) {
                                reallv = 13;
                                editor = settingshared.edit();
                                editor.putInt("level", 13);
                                editor.putString("item", "데스나이트 빙룡검");
                                editor.apply();
                            } else if (score >= 269) {
                                reallv = 12;
                                editor = settingshared.edit();
                                editor.putInt("level", 12);
                                editor.putString("item", "얼음꽃 빙룡검");
                                editor.apply();

                            } else if (score >= 229) {
                                reallv = 11;
                                editor = settingshared.edit();
                                editor.putInt("level", 11);
                                editor.putString("item", "만개를 기다리는 아이스 스워드");
                                editor.apply();

                            } else if (score >= 189) {
                                reallv = 10;
                                editor = settingshared.edit();
                                editor.putInt("level", 10);
                                editor.putString("item", "아이스 스워드");
                                editor.apply();

                            } else if (score >= 159) {
                                reallv = 9;
                                editor = settingshared.edit();
                                editor.putInt("level", 9);
                                editor.putString("item", "크로스 스워드");
                                editor.apply();

                            } else if (score >= 129) {
                                reallv = 8;
                                editor = settingshared.edit();
                                editor.putInt("level", 8);
                                editor.putString("item", "깨어난 각성검");
                                editor.apply();

                            } else if (score >= 99) {
                                reallv = 7;
                                editor = settingshared.edit();
                                editor.putInt("level", 7);
                                editor.putString("item", "슬라임 잡는 중 레벨업한 검");
                                editor.apply();

                            } else if (score >= 79) {
                                reallv = 6;
                                editor = settingshared.edit();
                                editor.putInt("level", 6);
                                editor.putString("item", "슬라임 잡다 휜 검");
                                editor.apply();

                            } else if (score >= 59) {
                                reallv = 5;
                                editor = settingshared.edit();
                                editor.putInt("level", 5);
                                editor.putString("item", "용주부가 즐겨쓰는 주방용 칼");
                                editor.apply();

                            } else if (score >= 39) {
                                reallv = 4;
                                editor = settingshared.edit();
                                editor.putInt("level", 4);
                                editor.putString("item", "산신령도 못만져본 강철도끼");
                                editor.apply();

                            } else if (score >= 26) {
                                reallv = 3;
                                editor = settingshared.edit();
                                editor.putInt("level", 3);
                                editor.putString("item", "산신령은 거들떠도 안보는 나무도끼");
                                editor.apply();

                            } else if (score >= 13) {
                                reallv = 2;
                                editor = settingshared.edit();
                                editor.putInt("level", 2);
                                editor.putString("item", "치킨 아니야 몽둥이야 몽둥이");
                                editor.apply();

                            } else {
                                reallv = 1;
                                editor = settingshared.edit();
                                editor.putInt("level", 1);
                                editor.putString("item", "어쩌다 마주친 나뭇가지");
                                editor.apply();
                            }
                        }else{
                            reallv=lv;
                        }

                        if(lv<reallv){

                            String resName1 = "@drawable/itemimg_"+ reallv +"_1";
                            int resID1 = getResources().getIdentifier(resName1, "drawable", getPackageName());
                            Picasso.with(GoalchangeActivity.this).load(resID1).into(Aactivity.itemimage);



                            View view = View.inflate(GoalchangeActivity.this, R.layout.toast_layout, null);
                            TextView text = (TextView) view.findViewById(R.id.texttoast);
                            text.setVisibility(View.INVISIBLE);
                            Toast toast = new Toast(GoalchangeActivity.this);
                            toast.setView(view);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                        }





                        Intent intent1 = new Intent(GoalchangeActivity.this,GoalActivity.class);
                        intent1.putExtra("send", 1);
                        stopService(new Intent(GoalchangeActivity.this, OnService.class));
                        Aactivity.goal.setText("클릭해서 목표 설정");
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.giveup:

                        editor = settingshared.edit();
                        editor.putString("goal", "");
                        editor.apply();

                        Intent intent2 = new Intent(GoalchangeActivity.this,GoalActivity.class);
                        intent2.putExtra("send",1);
                        stopService(new Intent(GoalchangeActivity.this, OnService.class));
                        Aactivity. goal.setText("클릭해서 목표 설정");
                        startActivity(intent2);
                        finish();
                        break;
                }

            }
        };

        back.setOnClickListener(click);
        success.setOnClickListener(click);
        giveup.setOnClickListener(click);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        findViewById(R.id.bigback).setBackground(null);
        findViewById(R.id.backbutton).setBackground(null);
        findViewById(R.id.success).setBackground(null);
        findViewById(R.id.giveup).setBackground(null);
        findViewById(R.id.imageView).setBackground(null);
        System.gc();

    }

}
