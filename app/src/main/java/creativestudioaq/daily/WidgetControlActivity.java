package creativestudioaq.daily;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by honggyu on 2016-01-06.
 */
public class WidgetControlActivity extends BaseActivity {

    private int mAppWidgetId;
    private EditText widgetTitle;
    SharedPreferences settingshared;
    private AppWidgetManager appWidgetManager;
    private RemoteViews remoteView;
    private Toast toast;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgetcontrol);


        Bundle mExtras = getIntent().getExtras();
        if (mExtras != null) {
            mAppWidgetId = mExtras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        settingshared = getSharedPreferences("setting", 0);

        if(settingshared.getInt("number",0)>8) {
            AppWidgetHost appwidgetHost = new AppWidgetHost(this, 0);
            appwidgetHost.deleteAppWidgetId(mAppWidgetId);

            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_CANCELED, resultValue);

            finish();
            toast = Toast.makeText(this, "8개의 위젯이 최대입니다!", Toast.LENGTH_SHORT);
            toast.show();
        }

        appWidgetManager = AppWidgetManager.getInstance(this);

        remoteView = new RemoteViews(this.getPackageName(),
                R.layout.widget);

        widgetTitle = (EditText) findViewById(R.id.input1);
        Button apply = (Button)findViewById(R.id.applybutton);
        TextView mention1 = (TextView)findViewById(R.id.mention1);
        TextView mention2 = (TextView)findViewById(R.id.mention2);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);



        String mention1for="카드 이름(4글자 이하 권장)";
        int color = Color.DKGRAY;
        SpannableStringBuilder builder = new SpannableStringBuilder(mention1for);
        builder.setSpan(new ForegroundColorSpan(color), 5, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mention1.append(builder);

        mention2.setText("카드 색상 선정");
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mText = widgetTitle.getText().toString();
                mText = mText.replace("'", "''");

                if (mText != "") {
                    remoteView.setTextViewText(R.id.widgettext, mText);
                    appWidgetManager.updateAppWidget(mAppWidgetId, remoteView);

                    PreferenceTool mTool = new PreferenceTool(getApplicationContext());
                    mTool.putString(String.valueOf(mAppWidgetId), mText);
                    mTool.putInt(String.valueOf(mAppWidgetId)+"int",i);

                    Intent resultValue = new Intent();
                    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                    setResult(RESULT_OK, resultValue);
                    finish();
                }
            }
        });


        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button1:
                        toast = Toast.makeText(WidgetControlActivity.this, "노랑", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget);
                        i=0;
                        break;
                    case R.id.button2:
                        toast = Toast.makeText(WidgetControlActivity.this, "빨강", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget1);
                        i=1;
                        break;
                    case R.id.button3:
                        toast = Toast.makeText(WidgetControlActivity.this, "주황", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget2);
                        i=2;
                        break;
                    case R.id.button4:
                        toast = Toast.makeText(WidgetControlActivity.this, "분홍", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget3);
                        i=3;
                        break;
                    case R.id.button5:
                        toast = Toast.makeText(WidgetControlActivity.this, "황토", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget4);
                        i=4;
                        break;
                    case R.id.button6:
                        toast = Toast.makeText(WidgetControlActivity.this, "초록", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget5);
                        i=5;
                        break;
                    case R.id.button7:
                        toast = Toast.makeText(WidgetControlActivity.this, "하늘", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget6);
                        i=6;
                        break;
                    case R.id.button8:
                        toast = Toast.makeText(WidgetControlActivity.this, "파랑", Toast.LENGTH_SHORT);
                        toast.show();
                        remoteView = new RemoteViews(WidgetControlActivity.this.getPackageName(),
                                R.layout.widget7);
                        i=7;
                        break;


                }
            }
        };

        button1.setOnClickListener(click);
        button2.setOnClickListener(click);
        button3.setOnClickListener(click);
        button4.setOnClickListener(click);
        button5.setOnClickListener(click);
        button6.setOnClickListener(click);
        button7.setOnClickListener(click);
        button8.setOnClickListener(click);


    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        AppWidgetHost appwidgetHost = new AppWidgetHost(this, 0);
        appwidgetHost.deleteAppWidgetId(mAppWidgetId);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_CANCELED, resultValue);

        finish();
    }

}
