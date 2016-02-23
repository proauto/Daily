package creativestudioaq.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by honggyu on 2016-01-06.
 */
public class RecordActivity extends BaseActivity {

    ViewPager pager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Button back = (Button) findViewById(R.id.back);
        Button inventory = (Button) findViewById(R.id.buttoninventory);
        Button setting = (Button) findViewById(R.id.buttonsetting);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        pager = (ViewPager)findViewById(R.id.pager);
        RecordAdapter adapter = new RecordAdapter(getLayoutInflater(),this,mTypeface);
        pager.setAdapter(adapter);
        pager.setCurrentItem(6);




        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.back:
                        finish();

                        break;

                    case R.id.buttoninventory:
                        Intent intent1 = new Intent(RecordActivity.this, InventoryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.buttonsetting:
                        Intent intent2 = new Intent(RecordActivity.this, SettingActivity.class);
                        startActivity(intent2);
                        break;
                }

            }
        };

        back.setOnClickListener(click);
        inventory.setOnClickListener(click);
        setting.setOnClickListener(click);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
