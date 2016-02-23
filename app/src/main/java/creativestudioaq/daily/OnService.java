package creativestudioaq.daily;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by honggyu on 2015-12-23.
 */
public
class OnService extends Service {
    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    Notification mNoti;
    CountDownTimer mCountDown = null;
    int db=0;
    String goal;
    RemoteViews contentView;
    int real;

    private BroadcastReceiver br = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
              db=1;
            }

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                int noti = settingshared.getInt("noti", 0);
                int dbchanger = settingshared.getInt("dbchanger", 1);
                int dayofweekchanger = settingshared.getInt("dayofweek", 100);
                real = settingshared.getInt("realint",0);

                Calendar cal= Calendar.getInstance();
                int day_of_week = cal.get(Calendar.DAY_OF_WEEK);


                if(dayofweekchanger==100){
                    dayofweekchanger=day_of_week;
                    editor = settingshared.edit();
                    editor.putInt("dayofweek", day_of_week);
                    editor.apply();
                }


                if(day_of_week!=dayofweekchanger){

                        real = real - 1;
                        editor = settingshared.edit();
                        editor.putInt("realint", real);
                        editor.putInt("dayofweek", day_of_week);
                        editor.apply();
                        stopService(new Intent(OnService.this, OnService.class));
                        startService(new Intent(OnService.this, OnService.class));

                }


                if(day_of_week==1&&dbchanger==1){

                    int dbVersion = settingshared.getInt("dbversion", 1);
                    dbVersion++;
                    editor = settingshared.edit();
                    editor.putInt("dbversion", dbVersion);
                    editor.putInt("dbchanger",0);
                    editor.apply();

                }

                if((day_of_week==2||day_of_week==3||day_of_week==4||day_of_week==5||day_of_week==6||day_of_week==7)&&dbchanger==0){

                    editor = settingshared.edit();
                    editor.putInt("dbchanger",1);
                    editor.apply();

                }




                db=0;


                if(noti==0) {
                    mCountDown = new CountDownTimer(10000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            // TODO Auto-generated method stub
                            if (db == 1) {
                                this.cancel();
                            }
                        }

                        @Override
                        public void onFinish() {
                            // TODO Auto-generated method stub
                            View view = View.inflate(OnService.this, R.layout.toast_layout, null);
                            TextView text = (TextView) view.findViewById(R.id.texttoast);
                            ImageView image2 = (ImageView)view.findViewById(R.id.imageView2);
                            image2.setVisibility(View.INVISIBLE);
                            text.setText("\n\n\n핸드폰 그만하고! \n " + goal + "!!!!!");
                            Toast toast = new Toast(OnService.this);
                            toast.setView(view);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }.start();
                }else if(noti==1){
                    mCountDown = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            // TODO Auto-generated method stub
                            if (db == 1) {
                                this.cancel();
                            }
                        }

                        @Override
                        public void onFinish() {
                            // TODO Auto-generated method stub
                            View view = View.inflate(OnService.this, R.layout.toast_layout, null);
                            TextView text = (TextView) view.findViewById(R.id.texttoast);
                            ImageView image2 = (ImageView)view.findViewById(R.id.imageView2);
                            image2.setVisibility(View.INVISIBLE);
                            text.setText("\n\n\n핸드폰 그만하고! \n " + goal + "!!!!!");
                            Toast toast = new Toast(OnService.this);
                            toast.setView(view);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }.start();

                }else if(noti==2){
                    mCountDown = new CountDownTimer(600000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            // TODO Auto-generated method stub
                            if (db == 1) {
                                this.cancel();
                            }
                        }

                        @Override
                        public void onFinish() {
                            // TODO Auto-generated method stub
                            View view = View.inflate(OnService.this, R.layout.toast_layout, null);
                            TextView text = (TextView) view.findViewById(R.id.texttoast);
                            ImageView image2 = (ImageView)view.findViewById(R.id.imageView2);
                            image2.setVisibility(View.INVISIBLE);
                            text.setText("\n\n\n핸드폰 그만하고! \n " + goal + "!!!!!");
                            Toast toast = new Toast(OnService.this);
                            toast.setView(view);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }.start();
                }else{

                }

            }




        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        unregisterRestartAlarm();
        registerBR();
        settingshared = getSharedPreferences("setting", 0);
        goal = settingshared.getString("goal", "");
        real = settingshared.getInt("realint",0);
        int thema = settingshared.getInt("thema", 0);

        if(goal.equals("")){

        }else {

            NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            mNoti = new Notification.Builder(this)
                    .setContentTitle("TITLE")
                    .setContentText("TEXT")
                    .setAutoCancel(true)
                    .setOngoing(true)
                    .setSmallIcon(R.drawable.miniicon)
                    .build();

            //원하는 레이아웃을 만들어 2번째 값에 넣는다.
            if (thema == 0) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification);
            } else if (thema == 1) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification2);
            } else if (thema == 2) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification3);
            } else if (thema == 3) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification4);
            } else if (thema == 4) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification5);
            } else if (thema == 5) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification6);
            } else if (thema == 6) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification7);
            } else if (thema == 7) {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification8);
            } else {
                contentView = new RemoteViews(getPackageName(), R.layout.customnotification3);
            }
            if (real > 0) {

                contentView.setTextViewText(R.id.notiTextView, goal + " D - " + real);


            } else if (real < 0) {

                contentView.setTextViewText(R.id.notiTextView, goal + " D-day 지났습니다");

            } else {
                contentView.setTextViewText(R.id.notiTextView, goal + " D-day !");
            }

            mNoti.contentView = contentView;

        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null)
            registerBR();

        if(mNoti!=null)
        startForeground(1,mNoti);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(br);
        registerRestartAlarm();
        super.onDestroy();

    }

    private void registerBR() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(br, filter);
        IntentFilter filter1 = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(br, filter1);
    }

    void registerRestartAlarm(){
        Intent intent = new Intent(OnService.this,RestartService.class);
        intent.setAction("ACTION.Restart.OnService");
        PendingIntent sender = PendingIntent.getBroadcast(OnService.this,0,intent,0);
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 1*1000;
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 10 * 1000, sender);
    }

    void unregisterRestartAlarm(){
        Intent intent = new Intent(OnService.this, RestartService.class);
        intent.setAction("ACTION.Restart.OnService");
        PendingIntent sender = PendingIntent.getBroadcast(OnService.this,0,intent,0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.cancel(sender);
    }
}