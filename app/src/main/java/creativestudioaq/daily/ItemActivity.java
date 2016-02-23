package creativestudioaq.daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by honggyu on 2016-01-06.
 */
public class ItemActivity extends BaseActivity {

    SharedPreferences settingshared;
    RelativeLayout sharephoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Button back = (Button) findViewById(R.id.buttonback);
        Button inventory = (Button) findViewById(R.id.buttoninventory);
        Button setting = (Button) findViewById(R.id.buttonsetting);
        TextView toptext = (TextView)findViewById(R.id.toptext);
        TextView bottomtext = (TextView)findViewById(R.id.bottomtext);
        ImageView equipitem = (ImageView)findViewById(R.id.equip);
        final Button share = (Button)findViewById(R.id.buttonshare);
        sharephoto = (RelativeLayout)findViewById(R.id.sharelayout);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.buttonback:
                        finish();
                        break;

                    case R.id.buttoninventory:
                        Intent intent1 = new Intent(ItemActivity.this, InventoryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.buttonsetting:
                        Intent intent2 = new Intent(ItemActivity.this, SettingActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.buttonshare:


                        sharephoto.buildDrawingCache();
                        Bitmap captureView = sharephoto.getDrawingCache();
                        String realpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.daily/Daily"+"/capture.jpeg";
                        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.daily/Daily";


                        String str = Environment.getExternalStorageState();
                        if (str.equals(Environment.MEDIA_MOUNTED)) {
                            OutputStream outputStream = null;


                            File file = new File(dirPath);


                            if (!file.exists()) {  // 원하는 경로에 폴더가 있는지 확인
                                file.mkdirs();
                            }

                            try {

                                outputStream = new FileOutputStream(realpath);

                                captureView.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                outputStream.flush();
                                outputStream.close();


                            } catch (Exception e) {

                            }


                        } else {
                            Toast.makeText(ItemActivity.this, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();
                        }

                        Uri uri = Uri.fromFile(new File(realpath));
                        Intent shareintent = new Intent(Intent.ACTION_SEND);
                        shareintent.putExtra(Intent.EXTRA_STREAM,uri);
                        shareintent.setType("image/*");
                        startActivity(Intent.createChooser(shareintent,"공유"));
                        break;
                }

            }
        };

        back.setOnClickListener(click);
        inventory.setOnClickListener(click);
        setting.setOnClickListener(click);
        share.setOnClickListener(click);


        settingshared = getSharedPreferences("setting", 0);
        int speciallv = settingshared.getInt("speciallevel",0);
        int lv = settingshared.getInt("level", 1);
        String item = settingshared.getString("item", "시간 애송이를 위한 나무 막대기");

        if(speciallv==0) {




        }else if(speciallv==1){

            lv = 16;
            item ="타임에코 블링크 스워드";

        }else if(speciallv==2){
            lv = 17;
            item ="프레임 드래곤 하트 스워드";

        }else{
            lv = 18;
            item ="엔드 오브 저스티스 스워드";

        }
        String lvitem = "LV."+lv+" " +item;
        int factor = (lv/10)+4;
        int color = Color.RED;
        SpannableStringBuilder builder = new SpannableStringBuilder(lvitem);
        builder.setSpan(new ForegroundColorSpan(color),0,factor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        String resName1 = "@drawable/w"+ lv;
        int resID1 = getResources().getIdentifier(resName1, "drawable", getPackageName());

        Picasso.with(this).load(resID1).into(equipitem);
        bottomtext.append(builder);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        findViewById(R.id.sharelayout).setBackground(null);
        System.gc();
    }
}
