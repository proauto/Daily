package creativestudioaq.daily;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    private Timer mTimer;
    TextView image1;
    private BackPressCloseHandler backpress;
    public static MainActivity Aactivity;
    SharedPreferences settingshared;
    String dbName1;
    int dbVersion1;
    SQLiteDatabase db1;
    String sql1;
    ArrayList<recordList> _lists= new ArrayList<>();
    recordListAdapter adapter1;
    String dbName = "checklist.db";
    int dbVersion = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String sql;
    ArrayList<checkList> Datas = new ArrayList<>();
    checkListAdapter adapter;
    Animation animScale;
    Animation animScale2;
    Animation animScale3;
    Animation animScale4;
    Animation animScale5;
    Animation animScale6;
    private Picasso picasso;
    private LruCache picassoLruCache;
    TextView goal;
    ImageView itemimage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int number=0;

        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            number=intent.getExtras().getInt("gogo");

        }


        animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        animScale2 = AnimationUtils.loadAnimation(this, R.anim.anim_scale2);
        animScale3 = AnimationUtils.loadAnimation(this, R.anim.anim_scale3);
        animScale4 = AnimationUtils.loadAnimation(this, R.anim.anim_scale4);
        animScale5 = AnimationUtils.loadAnimation(this, R.anim.anim_scale5);
        animScale6 = AnimationUtils.loadAnimation(this, R.anim.anim_scale6);

        backpress = new BackPressCloseHandler(this);

        settingshared =getSharedPreferences("setting", 0);

        Aactivity = MainActivity.this;


        image1 = (TextView) findViewById(R.id.imageView1);
        LinearLayout image2 = (LinearLayout) findViewById(R.id.imageView2);
        LinearLayout image3 = (LinearLayout) findViewById(R.id.imageView3);
        LinearLayout image4 = (LinearLayout) findViewById(R.id.imageView4);
        ImageView image5 = (ImageView) findViewById(R.id.imageView5);
        ImageView image6 = (ImageView) findViewById(R.id.imageView6);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        goal = (TextView) findViewById(R.id.goal);

        itemimage = (ImageView)findViewById(R.id.itemimage);
        TextView itemname = (TextView)findViewById(R.id.itemname);
        ListView recordlist = (ListView)findViewById(R.id.recordlist);
        TextView recordname =(TextView)findViewById(R.id.recordname);
        ListView checklist = (ListView) findViewById(R.id.checklist);
        TextView checklistname = (TextView)findViewById(R.id.checkname);
        TextView emptyrecord = (TextView)findViewById(R.id.emptyrecord);
        TextView emptylist = (TextView)findViewById(R.id.emptylist);
        if(number!=1) {
            image1.startAnimation(animScale);
            image2.startAnimation(animScale2);
            image3.startAnimation(animScale3);
            image4.startAnimation(animScale4);
            image5.startAnimation(animScale5);
            image6.startAnimation(animScale6);
        }


        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.button1:
                        Intent intent1 = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.button2:
                        Intent intent2 = new Intent(MainActivity.this, InventoryActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.goal:


                        if(settingshared.getString("goal","")!="") {

                            Intent intentnew = new Intent(MainActivity.this,GoalchangeActivity.class);
                            startActivity(intentnew);


                        }else{
                            Intent intent3 = new Intent(MainActivity.this, GoalActivity.class);
                            startActivity(intent3);

                        }

                        break;
                    case R.id.imageView2:
                        Intent intent4 = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.imageView3:
                        Intent intent5 = new Intent(MainActivity.this, RecordActivity.class);
                        startActivity(intent5);
                        break;
                     case R.id.imageView4:
                        Intent intent6 = new Intent(MainActivity.this, ChecklistActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.imageView5:
                        Intent intent7 = new Intent(MainActivity.this, StopwatchActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.imageView6:
                        Intent intent8 = new Intent(MainActivity.this, GraphActivity.class);
                        startActivity(intent8);
                        break;

                    case R.id.emptyrecord:
                        Intent intent9 = new Intent(MainActivity.this, RecordActivity.class);
                        startActivity(intent9);
                        break;
                    case R.id.emptylist:
                        Intent intent10 = new Intent(MainActivity.this, ChecklistActivity.class);
                        startActivity(intent10);
                        break;
                }

            }
        };


        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy MM dd ", Locale.KOREA );
        Date currentTime = new Date ( );
        String dTime = formatter.format ( currentTime );
        recordname.setText("  "+dTime+"너의 기록 >");
        checklistname.setText("        체크리스트       >");



        int lv = settingshared.getInt("level", 1);


        String resName1 = "@drawable/itemimg_"+ lv +"_1";
        int resID1 = getResources().getIdentifier(resName1, "drawable", getPackageName());
        picassoLruCache = new LruCache(this);
        picasso = new Picasso.Builder(this).memoryCache(picassoLruCache).build();
        picasso.with(this).load(resID1).into(itemimage);
        itemname.setText("당신의 무기");


        button1.setOnClickListener(click);
        button2.setOnClickListener(click);
        goal.setOnClickListener(click);
        image1.setOnClickListener(click);
        image2.setOnClickListener(click);
        image3.setOnClickListener(click);
        image4.setOnClickListener(click);
        image5.setOnClickListener(click);
        image6.setOnClickListener(click);
        emptylist.setOnClickListener(click);
        emptyrecord.setOnClickListener(click);

        MainTimerTask timerTask = new MainTimerTask();
        mTimer = new Timer();
        mTimer.schedule(timerTask, 500, 1000);

        if(settingshared.getString("goal","")=="") {

            goal.setText("클릭해서 목표 설정");
        }else{
            goal.setText(settingshared.getString("goal", ""));
            this.startService(new Intent(this, OnService.class));
        }


        Calendar cal= Calendar.getInstance ( );
        int day_of_week = cal.get ( Calendar.DAY_OF_WEEK );

        dbVersion1 = settingshared.getInt("dbversion",1);


        if (day_of_week == 1) {

            dbName1 = "record1.db";
            DBHelper1 dbHelper1 = new DBHelper1(this, dbName1, null, dbVersion1);
            db1 = dbHelper1.getWritableDatabase();
            sql1 = "SELECT * FROM record1;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s1 = new recordList(cursor.getString(1), cursor.getString(2));
                        _lists.add(s1);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }

        } else if (day_of_week == 2) {

            dbName1 = "record2.db";
            DBHelper2 dbHelper2 = new DBHelper2(this, dbName1, null, dbVersion1);
            db1 = dbHelper2.getWritableDatabase();
            sql1 = "SELECT * FROM record2;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s1 = new recordList(cursor.getString(1), cursor.getString(2));
                        _lists.add(s1);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }

        } else if (day_of_week == 3) {

            dbName1 = "record3.db";
            DBHelper3 dbHelper3 = new DBHelper3(this, dbName1, null, dbVersion1);
            db1 = dbHelper3.getWritableDatabase();
            sql1 = "SELECT * FROM record3;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s1 = new recordList(cursor.getString(1), cursor.getString(2));
                        _lists.add(s1);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
        } else if (day_of_week == 4) {

            dbName1 = "record4.db";
            DBHelper4 dbHelper4 = new DBHelper4(this, dbName1, null, dbVersion1);
            db1 = dbHelper4.getWritableDatabase();
            sql1 = "SELECT * FROM record4;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s1 = new recordList(cursor.getString(1), cursor.getString(2));
                        _lists.add(s1);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
        } else if (day_of_week == 5) {

            dbName1 = "record5.db";
            DBHelper5 dbHelper5 = new DBHelper5(this, dbName1, null, dbVersion1);
            db1 = dbHelper5.getWritableDatabase();
            sql1 = "SELECT * FROM record5;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s1 = new recordList(cursor.getString(1), cursor.getString(2));
                        _lists.add(s1);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
        } else if (day_of_week == 6) {

            dbName1 = "record6.db";
            DBHelper6 dbHelper6 = new DBHelper6(this, dbName1, null, dbVersion1);
            db1 = dbHelper6.getWritableDatabase();
            sql1 = "SELECT * FROM record6;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s1 = new recordList(cursor.getString(1), cursor.getString(2));
                        _lists.add(s1);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
        } else if (day_of_week == 7) {

            dbName1 = "record7.db";
            DBHelper7 dbHelper7 = new DBHelper7(this, dbName1, null, dbVersion1);
            db1 = dbHelper7.getWritableDatabase();
            sql1 = "SELECT * FROM record7;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s1 = new recordList(cursor.getString(1), cursor.getString(2));
                        _lists.add(s1);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
        }
        _lists.remove(0);


        if(_lists.size()==0){

            TextView backgroundthing = (TextView)findViewById(R.id.backgroundthing);
            backgroundthing.setBackgroundResource(R.drawable.alarmtxt);

        }else {

            adapter1 = new recordListAdapter(this, R.layout.record_content, _lists);

            recordlist.setAdapter(adapter1);


        }


        dbHelper = new DBHelper(this, dbName, null, dbVersion);
        db = dbHelper.getWritableDatabase();

        sql = "SELECT * FROM checklist;";
        Cursor cursor1 = db.rawQuery(sql, null);
        try {
            if (cursor1.getCount() > 0) {
                while (cursor1.moveToNext()) {
                    checkList s = new checkList(cursor1.getInt(1), cursor1.getString(2));
                    Datas.add(s);
                }
            } else {
            }
        } finally {
            cursor1.close();
        }

        adapter = new checkListAdapter(this, R.layout.checklist_content, Datas);

        checklist.setAdapter(adapter);

        recordlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent7 = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent7);
            }
        });
        checklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent8 = new Intent(MainActivity.this, ChecklistActivity.class);
                startActivity(intent8);
            }
        });




    }
    private android.os.Handler mHandler = new android.os.Handler();
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {

            Calendar cnow = Calendar.getInstance();

            Calendar cend = Calendar.getInstance();

            Date now = new Date();


            cnow.setTime(now);

            cend.add(cnow.DATE, 1);  //하루를 더한다.

            cend.set(cend.HOUR, 0);

            cend.set(cend.MINUTE, 0);

            cend.set(cend.SECOND, 0);

            cend.set(cend.MILLISECOND, 0);



            long result = cend.getTimeInMillis() - cnow.getTimeInMillis();

            int remaintime = (int)((result / 1000) / 3600);
            int remainminute = (int)((result-(remaintime)*3600*1000)/1000/60);
            int remainsecond = (int)((result-(remaintime)*3600*1000-remainminute*1000*60)/1000);
            String realtime;

            if(cnow.getTime().getHours()>12||now.getHours()==12) {
                 realtime= (remaintime - 12) + "시간" + remainminute + "분" + remainsecond + "초";
            }else{
                 realtime = remaintime  + "시간" + remainminute + "분" + remainsecond + "초";
            }

            image1.setText("오늘 하루 남은시간\n"+realtime);
        }
    };
    class MainTimerTask extends TimerTask {
        public void run() {
            mHandler.post(mUpdateTimeTask);
        }
    }

    @Override
    protected void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
        picassoLruCache.clear();

        final ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);


//stop running service inside current process.
        List<ActivityManager.RunningServiceInfo> serviceList = am.getRunningServices(100);
        for(ActivityManager.RunningServiceInfo service : serviceList){
            if( service.pid == android.os.Process.myPid()){
                Intent stop = new Intent();
                 stop.setComponent(service.service);
                stopService(stop);
            }
        }


//move current task to background.
        Intent launchHome = new Intent(Intent.ACTION_MAIN);
        launchHome.addCategory(Intent.CATEGORY_DEFAULT); launchHome.addCategory(Intent.CATEGORY_HOME);
        startActivity(launchHome);


//post delay runnable(waiting for home application launching)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                am.killBackgroundProcesses(getPackageName());
              //  startService(new Intent(MainActivity.this, OnService.class));
            }
        }, 3000);



    }

    @Override
    protected void onPause() {
        mTimer.cancel();
        super.onPause();


    }

    @Override
    protected void onResume() {
        MainTimerTask timerTask = new MainTimerTask();
        mTimer = new Timer();
        mTimer.schedule(timerTask, 500, 1000);

        if(adapter!=null)
        adapter.notifyDataSetChanged();
        if(adapter1!=null)
        adapter1.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backpress.onBackPressed();
    }


    class recordList{
        private String _time;
        private String _card;

        public String getTime(){return _time;}
        public String getCard(){return _card;}

        public recordList(String time,String card){
            _time=time;
            _card=card;

        }

    }

    class recordListAdapter extends BaseAdapter {

        private LayoutInflater _inflater;
        private ArrayList<recordList> _lists;
        private int _layout;
        private Context m_ctx;
        private recordList list;

        public recordListAdapter(Context context, int layout,ArrayList<recordList> lists){
            _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _lists = lists;
            _layout = layout;
            m_ctx = context;
        }


        @Override
        public int getCount() {
            return _lists.size();
        }

        @Override
        public String getItem(int position) {
            return _lists.get(position).getTime();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                convertView = _inflater.inflate(_layout,parent,false);

                holder = new ViewHolder();
                holder.time = (TextView)convertView.findViewById(R.id.time1);
                holder.card = (TextView)convertView.findViewById(R.id.card1);
                holder.time.setTypeface(mTypeface);
                holder.card.setTypeface(mTypeface);
                convertView.setTag(holder);

            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            list = _lists.get(position);

            holder.time.setText(list.getTime());
            holder.card.setText(list.getCard());


            return convertView;
        }


        private  class ViewHolder{
            public TextView time,card;
        }
    }



    class checkListAdapter extends BaseAdapter {

        private LayoutInflater _inflater;
        private ArrayList<checkList> _lists;
        private int _layout;
        private Context m_ctx;
        private checkList list;


        public checkListAdapter(Context context, int layout, ArrayList<checkList> lists) {
            _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _lists = lists;
            _layout = layout;
            m_ctx = context;
        }


        @Override
        public int getCount() {
            return _lists.size();
        }

        @Override
        public String getItem(int position) {
            return _lists.get(position).getcontent();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = _inflater.inflate(_layout, parent, false);

                holder = new ViewHolder();
                holder.buttonclick = (ImageView) convertView.findViewById(R.id.checkbutton);
                holder.contentclick = (TextView) convertView.findViewById(R.id.checkcontent);
                holder.contentclick.setTypeface(mTypeface);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();

            }

            list = _lists.get(position);

            String resName = "@drawable/clickimg_" + list.getbutton();
            int resID = getResources().getIdentifier(resName, "drawable", getPackageName());

            Log.v("", "" + resName);
            if (holder.buttonclick != null) {
                Picasso.with(m_ctx).load(resID).into(holder.buttonclick);
            }
            if (holder.contentclick != null) {
                holder.contentclick.setText(list.getcontent());
            }
            if (list.getbutton() == 1) {
                holder.contentclick.setBackgroundResource(R.drawable.whitebackground);

            } else {
                holder.contentclick.setBackgroundResource(R.drawable.checkline);

            }

            return convertView;
        }


        private class ViewHolder {
            public ImageView buttonclick;
            public TextView contentclick;
            public int position;
        }
    }


}
