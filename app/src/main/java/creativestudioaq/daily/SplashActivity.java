package creativestudioaq.daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by honggyu on 2016-01-06.
 */
public class SplashActivity extends BaseActivity {

    SharedPreferences settingshared;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingshared = getSharedPreferences("setting", 0);
        int score = settingshared.getInt("score",0);
        int lv = settingshared.getInt("level",0);
        int speciallv = settingshared.getInt("speciallevel",0);
        int tutorial =settingshared.getInt("tutorial",0);

        if(speciallv==0) {

            if (score >= 409) {
                editor = settingshared.edit();
                editor.putInt("level", 15);
                editor.putString("item", "천상의 루비나이트 빙룡검");
                editor.apply();
            } else if (score >= 359) {
                editor = settingshared.edit();
                editor.putInt("level", 14);
                editor.putString("item", "루비나이트 빙룡검");
                editor.apply();
            } else if (score >= 309) {
                editor = settingshared.edit();
                editor.putInt("level", 13);
                editor.putString("item", "데스나이트 빙룡검");
                editor.apply();
            } else if (score >= 269) {
                editor = settingshared.edit();
                editor.putInt("level", 12);
                editor.putString("item", "얼음꽃 빙룡검");
                editor.apply();

            } else if (score >= 229) {
                editor = settingshared.edit();
                editor.putInt("level", 11);
                editor.putString("item", "만개를 기다리는 아이스 스워드");
                editor.apply();

            } else if (score >= 189) {
                editor = settingshared.edit();
                editor.putInt("level", 10);
                editor.putString("item", "아이스 스워드");
                editor.apply();

            } else if (score >= 159) {
                editor = settingshared.edit();
                editor.putInt("level", 9);
                editor.putString("item", "크로스 스워드");
                editor.apply();

            } else if (score >= 129) {
                editor = settingshared.edit();
                editor.putInt("level", 8);
                editor.putString("item", "깨어난 각성검");
                editor.apply();

            } else if (score >= 99) {
                editor = settingshared.edit();
                editor.putInt("level", 7);
                editor.putString("item", "슬라임 잡는 중 레벨업한 검");
                editor.apply();

            } else if (score >= 79) {
                editor = settingshared.edit();
                editor.putInt("level", 6);
                editor.putString("item", "슬라임 잡다 휜 검");
                editor.apply();

            } else if (score >= 59) {
                editor = settingshared.edit();
                editor.putInt("level", 5);
                editor.putString("item", "용주부가 즐겨쓰는 주방용 칼");
                editor.apply();

            } else if (score >= 39) {
                editor = settingshared.edit();
                editor.putInt("level", 4);
                editor.putString("item", "산신령도 못만져본 강철도끼");
                editor.apply();

            } else if (score >= 26) {
                editor = settingshared.edit();
                editor.putInt("level", 3);
                editor.putString("item", "산신령은 거들떠도 안보는 나무도끼");
                editor.apply();

            } else if (score >= 13) {
                editor = settingshared.edit();
                editor.putInt("level", 2);
                editor.putString("item", "치킨아니야 몽둥이야 몽둥이");
                editor.apply();

            } else {
                editor = settingshared.edit();
                editor.putInt("level", 1);
                editor.putString("item", "어쩌다 마주친 나뭇가지");
                editor.apply();
            }


        }else if(speciallv==1){
            editor = settingshared.edit();
            editor.putInt("level", 16);
            editor.putString("item", "타임에코 블링크 스워드");
            editor.apply();

        }else if(speciallv==2){
            editor = settingshared.edit();
            editor.putInt("level", 17);
            editor.putString("item", "프레임 드래곤 하트 스워드");
            editor.apply();

        }else{
            editor = settingshared.edit();
            editor.putInt("level", 18);
            editor.putString("item", "엔드 오브 저스티스 스워드");
            editor.apply();

        }



        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(settingshared.getString("goal","")!=""){
            this.startService(new Intent(this, OnService.class));
        }

        if(tutorial==0){

        Intent intent = new Intent(this, OnceTip.class);
        startActivity(intent);
        finish();
        } else{
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
    }
}
