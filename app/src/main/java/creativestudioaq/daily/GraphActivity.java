package creativestudioaq.daily;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by honggyu on 2016-01-06.
 */
public class GraphActivity extends BaseActivity {



    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        pager = (ViewPager)findViewById(R.id.pager);
        GraphAdapter adapter = new GraphAdapter(getLayoutInflater(),this,mTypeface);
        pager.setAdapter(adapter);
        pager.setCurrentItem(6);

        Button back = (Button) findViewById(R.id.back);
        Button inventory = (Button) findViewById(R.id.buttoninventory);
        Button setting = (Button) findViewById(R.id.buttonsetting);
        Button share = (Button)findViewById(R.id.buttonshare2);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.back:
                        finish();
                        break;

                    case R.id.buttoninventory:
                        Intent intent1 = new Intent(GraphActivity.this, InventoryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.buttonsetting:
                        Intent intent2 = new Intent(GraphActivity.this, SettingActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.buttonshare2:

                        View img = (View)pager.findViewWithTag(pager.getCurrentItem());
                        Log.v("dd",""+img +" "+pager.getCurrentItem());
                        img.buildDrawingCache();
                        Bitmap captureView = img.getDrawingCache();
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
                            Toast.makeText(GraphActivity.this, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();
                        }

                        Uri uri = Uri.fromFile(new File(realpath));
                        Intent shareintent = new Intent(Intent.ACTION_SEND);
                        shareintent.putExtra(Intent.EXTRA_STREAM,uri);
                        shareintent.setType("image/*");
                        startActivity(Intent.createChooser(shareintent, "공유"));
                        break;
                }

            }
        };


        back.setOnClickListener(click);
        inventory.setOnClickListener(click);
        setting.setOnClickListener(click);
        share.setOnClickListener(click);


    }

}
