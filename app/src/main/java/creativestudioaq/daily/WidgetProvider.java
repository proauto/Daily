package creativestudioaq.daily;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by honggyu on 2015-12-22.
 */
public class WidgetProvider extends AppWidgetProvider {

    public static String Click_ACTION0 = "creativestudioaq.daily.Click_Action0";
    public static String Click_ACTION1 = "creativestudioaq.daily.Click_Action1";
    public static String Click_ACTION2 = "creativestudioaq.daily.Click_Action2";
    public static String Click_ACTION3 = "creativestudioaq.daily.Click_Action3";
    public static String Click_ACTION4 = "creativestudioaq.daily.Click_Action4";
    public static String Click_ACTION5 = "creativestudioaq.daily.Click_Action5";
    public static String Click_ACTION6 = "creativestudioaq.daily.Click_Action6";
    public static String Click_ACTION7 = "creativestudioaq.daily.Click_Action7";



    String dbName;
    int dbVersion;
    SQLiteDatabase db;
    String sql;
    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    private String name;
    private static final int WIDGET_UPDATE_INTERVAL = 5000;
    private static PendingIntent mSender;
    private static AlarmManager mManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        // 위젯 업데이트 인텐트를 수신했을 때
        Log.v("",""+action);
        if(action.equals("android.appwidget.action.APPWIDGET_UPDATE"))
        {
            removePreviousAlarm();
            long firstTime = System.currentTimeMillis() + WIDGET_UPDATE_INTERVAL;
            mSender = PendingIntent.getBroadcast(context, 0, intent, 0);
            mManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            mManager.set(AlarmManager.RTC, firstTime, mSender);
        }
        // 위젯 제거 인텐트를 수신했을 때
        else if(action.equals("android.appwidget.action.APPWIDGET_DISABLED"))
        {

            removePreviousAlarm();
        }
       if(action.equals(Click_ACTION0)||action.equals(Click_ACTION1)||action.equals(Click_ACTION2)||action.equals(Click_ACTION3)||action.equals(Click_ACTION4)||action.equals(Click_ACTION5)||action.equals(Click_ACTION6)||action.equals(Click_ACTION7)){

           Calendar cal = Calendar.getInstance();
           int day_of_week = cal.get(Calendar.DAY_OF_WEEK);

            Calendar mCalendar = Calendar.getInstance();
            SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm",
                    Locale.KOREA);
            String date = mFormat.format(mCalendar.getTime());

           SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
           int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

           SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
           int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

           int factor = hour*60 + minute;
           settingshared =context.getSharedPreferences("setting", 0);
           String checkname="";

           if(day_of_week==1) {
               checkname = settingshared.getString("before2", "");
           }else if(day_of_week==2){
               checkname = settingshared.getString("before3", "");
           }else if(day_of_week==3){
               checkname = settingshared.getString("before4", "");
           }else if(day_of_week==4){
               checkname = settingshared.getString("before5", "");
           }else if(day_of_week==5){
               checkname = settingshared.getString("before6", "");
           }else if(day_of_week==6){
               checkname = settingshared.getString("before7", "");
           }else if(day_of_week==7){
               checkname = settingshared.getString("before1", "");
           }
               int checkfactor = settingshared.getInt("beforefactor",0);


            if(intent.getExtras().getString("name")!=null)
            name= intent.getExtras().getString("name");

           if(checkname.equals(name)) {
               Toast.makeText(context, "이미 '" + name + "' 진행 중입니다.", Toast.LENGTH_SHORT).show();

           }else if(factor==checkfactor){
               Toast.makeText(context, "1분 이상 간격으로 누를수 있습니다.", Toast.LENGTH_SHORT).show();

           }else {

               Toast.makeText(context, name + "시작! ", Toast.LENGTH_SHORT).show();



               dbVersion = settingshared.getInt("dbversion", 1);
               int score = settingshared.getInt("score",0)+1;

               int lv = settingshared.getInt("level",1);
               int speciallv = settingshared.getInt("speciallevel",0);
               int reallv = 1;


                if(speciallv==0) {
                    if (score >= 409) {
                        reallv = 15;
                    } else if (score >= 359) {
                        reallv = 14;
                    } else if (score >= 309) {
                        reallv = 13;
                    } else if (score >= 269) {
                        reallv = 12;
                    } else if (score >= 229) {
                        reallv = 11;
                    } else if (score >= 189) {
                        reallv = 10;
                    } else if (score >= 159) {
                        reallv = 9;
                    } else if (score >= 129) {
                        reallv = 8;
                    } else if (score >= 99) {
                        reallv = 7;
                    } else if (score >= 79) {
                        reallv = 6;
                    } else if (score >= 59) {
                        reallv = 5;
                    } else if (score >= 39) {
                        reallv = 4;
                    } else if (score >= 26) {
                        reallv = 3;
                    } else if (score >= 13) {
                        reallv = 2;
                    } else {
                        reallv = 1;
                    }
                }else{
                    reallv=lv;
                }
               if(lv<reallv){
                   View view = View.inflate(context, R.layout.toast_layout, null);
                   TextView text = (TextView) view.findViewById(R.id.texttoast);
                   text.setVisibility(View.INVISIBLE);
                   Toast toast = new Toast(context);
                   toast.setView(view);
                   toast.setGravity(Gravity.CENTER, 0, 0);
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.show();
               }





               if (day_of_week == 1) {

                   dbName = "record1.db";
                   DBHelper1 dbHelper1 = new DBHelper1(context, dbName, null, dbVersion);
                   db = dbHelper1.getWritableDatabase();
                   sql = String.format("INSERT INTO record1 VALUES(NULL,'%s','%s',%d);", date, name, factor);
                   db.execSQL(sql);
                   editor = settingshared.edit();
                   editor.putInt("score",score);
                   editor.putInt("beforefactor", factor);
                   editor.putString("before2", name);
                   editor.apply();


               } else if (day_of_week == 2) {

                   dbName = "record2.db";
                   DBHelper2 dbHelper2 = new DBHelper2(context, dbName, null, dbVersion);
                   db = dbHelper2.getWritableDatabase();
                   sql = String.format("INSERT INTO record2 VALUES(NULL,'%s','%s',%d);", date, name, factor);
                   db.execSQL(sql);
                   editor = settingshared.edit();
                   editor.putInt("score",score);
                   editor.putInt("beforefactor", factor);
                   editor.putString("before3", name);
                   editor.apply();
               } else if (day_of_week == 3) {

                   dbName = "record3.db";
                   DBHelper3 dbHelper3 = new DBHelper3(context, dbName, null, dbVersion);
                   db = dbHelper3.getWritableDatabase();
                   sql = String.format("INSERT INTO record3 VALUES(NULL,'%s','%s',%d);", date, name, factor);
                   db.execSQL(sql);
                   editor = settingshared.edit();
                   editor.putInt("score",score);
                   editor.putInt("beforefactor", factor);
                   editor.putString("before4", name);
                   editor.apply();

               } else if (day_of_week == 4) {

                   dbName = "record4.db";
                   DBHelper4 dbHelper4 = new DBHelper4(context, dbName, null, dbVersion);
                   db = dbHelper4.getWritableDatabase();
                   sql = String.format("INSERT INTO record4 VALUES(NULL,'%s','%s',%d);", date, name, factor);
                   db.execSQL(sql);

                   editor = settingshared.edit();
                   editor.putInt("score",score);
                   editor.putInt("beforefactor", factor);
                   editor.putString("before5", name);
                   editor.apply();
               } else if (day_of_week == 5) {

                   dbName = "record5.db";
                   DBHelper5 dbHelper5 = new DBHelper5(context, dbName, null, dbVersion);
                   db = dbHelper5.getWritableDatabase();
                   sql = String.format("INSERT INTO record5 VALUES(NULL,'%s','%s',%d);", date, name, factor);
                   db.execSQL(sql);

                   editor = settingshared.edit();
                   editor.putInt("score",score);
                   editor.putInt("beforefactor", factor);
                   editor.putString("before6", name);
                   editor.apply();
               } else if (day_of_week == 6) {

                   dbName = "record6.db";
                   DBHelper6 dbHelper6 = new DBHelper6(context, dbName, null, dbVersion);
                   db = dbHelper6.getWritableDatabase();
                   sql = String.format("INSERT INTO record6 VALUES(NULL,'%s','%s',%d);", date, name, factor);
                   db.execSQL(sql);

                   editor = settingshared.edit();
                   editor.putInt("score",score);
                   editor.putInt("beforefactor", factor);
                   editor.putString("before7", name);
                   editor.apply();
               } else if (day_of_week == 7) {

                   dbName = "record7.db";
                   DBHelper7 dbHelper7 = new DBHelper7(context, dbName, null, dbVersion);
                   db = dbHelper7.getWritableDatabase();
                   sql = String.format("INSERT INTO record7 VALUES(NULL,'%s','%s',%d);", date, name, factor);
                   db.execSQL(sql);

                   editor = settingshared.edit();
                   editor.putInt("score",score);
                   editor.putInt("beforefactor", factor);
                   editor.putString("before1", name);
                   editor.apply();
               }

           }

        }



    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        settingshared = context.getSharedPreferences("setting", 0);


        appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass()));


        editor = settingshared.edit();
        editor.putInt("number", appWidgetIds.length);
        editor.apply();

        for(int i=0; i<appWidgetIds.length;i++){
            updateAppWidget(context, appWidgetManager,appWidgetIds[i],i);
        }
    }


    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetid, int number){
        String actiongo="";

        if(number==0)
            actiongo=Click_ACTION0;
        if(number==1)
            actiongo=Click_ACTION1;
        if(number==2)
            actiongo=Click_ACTION2;
        if(number==3)
            actiongo=Click_ACTION3;
        if(number==4)
            actiongo=Click_ACTION4;
        if(number==5)
            actiongo=Click_ACTION5;
        if(number==6)
            actiongo=Click_ACTION6;
        if(number==7)
            actiongo=Click_ACTION7;





        PreferenceTool mTool = new PreferenceTool(context);
        name = mTool.getString(""+appWidgetid,"");
        int i = mTool.getInt(""+appWidgetid+"int",0);
        RemoteViews updateViews;

        if(i==1){
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget1);
        }else if(i==2){
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget2);
        }else if(i==3){
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget3);
        }else if(i==4){
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget4);
        }else if(i==5){
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget5);
        }else if(i==6){
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget6);
        }else if(i==7){
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget7);
        }else{
            updateViews = new RemoteViews(context.getPackageName(),R.layout.widget);
        }


        updateViews.setTextViewText(R.id.widgettext, name);

        Intent intent = new Intent();
        intent.setAction(actiongo);
        intent.putExtra("name",name);


        PendingIntent pending = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        updateViews.setOnClickPendingIntent(R.id.widgettext,pending);

        appWidgetManager.updateAppWidget(appWidgetid, updateViews);
    }
    public void removePreviousAlarm()
    {
        if(mManager != null && mSender != null)
        {
            mSender.cancel();
            mManager.cancel(mSender);
        }
    }

}
