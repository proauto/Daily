package creativestudioaq.daily;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by honggyu on 2016-01-20.
 */
public class RecordAdapter extends PagerAdapter {


    LayoutInflater inflater;

    String dbName;
    String dbString;
    int dbVersion;
    SQLiteDatabase db;
    String sql;
    ArrayList<recordList> _lists1= new ArrayList<>();
    ArrayList<recordList> _lists2= new ArrayList<>();
    ArrayList<recordList> _lists3= new ArrayList<>();
    ArrayList<recordList> _lists4= new ArrayList<>();
    ArrayList<recordList> _lists5= new ArrayList<>();
    ArrayList<recordList> _lists6= new ArrayList<>();
    ArrayList<recordList> _lists7= new ArrayList<>();
    recordListAdapter adapter1;
    recordListAdapter adapter2;
    recordListAdapter adapter3;
    recordListAdapter adapter4;
    recordListAdapter adapter5;
    recordListAdapter adapter6;
    recordListAdapter adapter7;
    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    Context ctx;
    Typeface mTypeface;
    int[] nowposition = new int[]{0,0,0,0,0,0,0};
    MainActivity Aactivity;


    public RecordAdapter(LayoutInflater inflater,Context context,Typeface typeface) {
    // TODO Auto-generated constructor stub

    //전달 받은 LayoutInflater를 멤버변수로 전달
    this.inflater=inflater;
        this.ctx=context;
        this.mTypeface=typeface;
        Aactivity = MainActivity.Aactivity;
}

    //PagerAdapter가 가지고 잇는 View의 개수를 리턴
    //보통 보여줘야하는 이미지 배열 데이터의 길이를 리턴
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 7; //이미지 개수 리턴(그림이 10개라서 10을 리턴)
    }



    //ViewPager가 현재 보여질 Item(View객체)를 생성할 필요가 있는 때 자동으로 호출
    //쉽게 말해, 스크롤을 통해 현재 보여져야 하는 View를 만들어냄.
    //첫번째 파라미터 : ViewPager
    //두번째 파라미터 : ViewPager가 보여줄 View의 위치(가장 처음부터 0,1,2,3...)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        View view=null;


        //새로운 View 객체를 Layoutinflater를 이용해서 생성
        //만들어질 View의 설계는 res폴더>>layout폴더>>viewpater_childview.xml 레이아웃 파일 사용
        view= inflater.inflate(R.layout.record_child, null);

        ListView recordlist = (ListView)view.findViewById(R.id.listrecord);
        settingshared =ctx.getSharedPreferences("setting", 0);
        dbVersion = settingshared.getInt("dbversion", 1);
        nowposition[position]=1;

        TextView whatday = (TextView)view.findViewById(R.id.whatday);
        TextView backgroundthing = (TextView)view.findViewById(R.id.backgroundthing);





        Calendar cal= Calendar.getInstance();
        int todayweek = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE,-6+position);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);


        if(todayweek<day_of_week){
            if(dbVersion!=1) {
                dbVersion = dbVersion - 1;
            }

        }

        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
        Date currentTime = cal.getTime();
        String dTime = formatter.format(currentTime);
        String m_week="";

        if (day_of_week == 1) {
            _lists1= new ArrayList<>();

            m_week = "일요일";
            dbName = "record1.db";
            dbString = "record1";
            DBHelper1 dbHelper1 = new DBHelper1(ctx, dbName, null, dbVersion);
            db = dbHelper1.getWritableDatabase();

            sql = "SELECT * FROM record1;";
            Cursor cursor = db.rawQuery(sql, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s = new recordList(cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                        _lists1.add(s);
                        Log.v("일요일", "" + cursor.getString(2));
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            _lists1.remove(0);

            if(_lists1.size()==0){


                backgroundthing.setBackgroundResource(R.drawable.alarmtxt);


            }else {

                adapter1 = new recordListAdapter(ctx, R.layout.record_contentnew, _lists1);

                recordlist.setAdapter(adapter1);
                recordlist.setOnItemLongClickListener(new recordListViewItemLongClickListener());

            }

        } else if (day_of_week == 2) {
            _lists2= new ArrayList<>();

            m_week = "월요일";
            dbName = "record2.db";
            dbString = "record2";
            DBHelper2 dbHelper2 = new DBHelper2(ctx, dbName, null, dbVersion);
            db = dbHelper2.getWritableDatabase();

            sql = "SELECT * FROM record2;";
            Cursor cursor = db.rawQuery(sql, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s = new recordList(cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                        _lists2.add(s);
                        Log.v("월요일",""+cursor.getString(2));
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            _lists2.remove(0);

            if(_lists2.size()==0){


                backgroundthing.setBackgroundResource(R.drawable.alarmtxt);


            }else {

                adapter2 = new recordListAdapter(ctx, R.layout.record_contentnew, _lists2);

                recordlist.setAdapter(adapter2);
                recordlist.setOnItemLongClickListener(new recordListViewItemLongClickListener());

            }
        } else if (day_of_week == 3) {
            _lists3= new ArrayList<>();

            m_week = "화요일";
            dbName = "record3.db";
            dbString = "record3";
            DBHelper3 dbHelper3 = new DBHelper3(ctx, dbName, null, dbVersion);
            db = dbHelper3.getWritableDatabase();

            sql = "SELECT * FROM record3;";
            Cursor cursor = db.rawQuery(sql, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s = new recordList(cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                        _lists3.add(s);
                        Log.v("화요일", "" + cursor.getString(2));
                    }
                } else {
                }
            } finally {
                cursor.close();
            }

            _lists3.remove(0);

            if(_lists3.size()==0){


                backgroundthing.setBackgroundResource(R.drawable.alarmtxt);


            }else {

                adapter3 = new recordListAdapter(ctx, R.layout.record_contentnew, _lists3);

                recordlist.setAdapter(adapter3);
                recordlist.setOnItemLongClickListener(new recordListViewItemLongClickListener());

            }

        } else if (day_of_week == 4) {
            _lists4= new ArrayList<>();

            m_week = "수요일";
            dbName = "record4.db";
            dbString = "record4";
            DBHelper4 dbHelper4 = new DBHelper4(ctx, dbName, null, dbVersion);
            db = dbHelper4.getWritableDatabase();

            sql = "SELECT * FROM record4;";
            Cursor cursor = db.rawQuery(sql, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s = new recordList(cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                        _lists4.add(s);
                        Log.v("수요일", "" + cursor.getString(2));
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            _lists4.remove(0);

            if(_lists4.size()==0){


                backgroundthing.setBackgroundResource(R.drawable.alarmtxt);


            }else {

                adapter4 = new recordListAdapter(ctx, R.layout.record_contentnew, _lists4);

                recordlist.setAdapter(adapter4);
                recordlist.setOnItemLongClickListener(new recordListViewItemLongClickListener());

            }


        } else if (day_of_week == 5) {
            _lists5= new ArrayList<>();

            m_week = "목요일";
            dbName = "record5.db";
            dbString = "record5";
            DBHelper5 dbHelper5 = new DBHelper5(ctx, dbName, null, dbVersion);
            db = dbHelper5.getWritableDatabase();

            sql = "SELECT * FROM record5;";
            Cursor cursor = db.rawQuery(sql, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s = new recordList(cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                        _lists5.add(s);
                        Log.v("목요일", "" + cursor.getString(2));
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            _lists5.remove(0);

            if(_lists5.size()==0){


                backgroundthing.setBackgroundResource(R.drawable.alarmtxt);


            }else {

                adapter5 = new recordListAdapter(ctx, R.layout.record_contentnew, _lists5);

                recordlist.setAdapter(adapter5);
                recordlist.setOnItemLongClickListener(new recordListViewItemLongClickListener());

            }


        } else if (day_of_week == 6) {
            _lists6= new ArrayList<>();

            m_week = "금요일";
            dbName = "record6.db";
            dbString = "record6";
            DBHelper6 dbHelper6 = new DBHelper6(ctx, dbName, null, dbVersion);
            db = dbHelper6.getWritableDatabase();

            sql = "SELECT * FROM record6;";
            Cursor cursor = db.rawQuery(sql, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s = new recordList(cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                        _lists6.add(s);
                        Log.v("금요일", "" + cursor.getString(2));
                    }
                } else {
                }
            } finally {
                cursor.close();
            }

            _lists6.remove(0);

            if(_lists6.size()==0){


                backgroundthing.setBackgroundResource(R.drawable.alarmtxt);


            }else {

                adapter6 = new recordListAdapter(ctx, R.layout.record_contentnew, _lists6);

                recordlist.setAdapter(adapter6);
                recordlist.setOnItemLongClickListener(new recordListViewItemLongClickListener());

            }


        } else if (day_of_week == 7) {
            _lists7= new ArrayList<>();

            m_week = "토요일";
            dbName = "record7.db";
            dbString = "record7";
            DBHelper7 dbHelper7 = new DBHelper7(ctx, dbName, null, dbVersion);
            db = dbHelper7.getWritableDatabase();

            sql = "SELECT * FROM record7;";
            Cursor cursor = db.rawQuery(sql, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        recordList s = new recordList(cursor.getString(1), cursor.getString(2),cursor.getInt(3));
                        _lists7.add(s);
                        Log.v("토요일", "" + cursor.getString(2));
                    }
                } else {
                }
            } finally {
                cursor.close();
            }

            _lists7.remove(0);

            if(_lists7.size()==0){


                backgroundthing.setBackgroundResource(R.drawable.alarmtxt);


            }else {

                adapter7 = new recordListAdapter(ctx, R.layout.record_contentnew, _lists7);

                recordlist.setAdapter(adapter7);
                recordlist.setOnItemLongClickListener(new recordListViewItemLongClickListener());

            }


        }



        String day = dTime +" "+ m_week;


        whatday.setText(day);
        whatday.setTypeface(mTypeface);



        //ViewPager에 만들어 낸 View 추가
        container.addView(view);

        //Image가 세팅된 View를 리턴
        return view;
    }

    //화면에 보이지 않은 View는파쾨를 해서 메모리를 관리함.
    //첫번째 파라미터 : ViewPager
    //두번째 파라미터 : 파괴될 View의 인덱스(가장 처음부터 0,1,2,3...)
    //세번째 파라미터 : 파괴될 객체(더 이상 보이지 않은 View 객체)
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        nowposition[position]=0;

        //ViewPager에서 보이지 않는 View는 제거
        //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
        container.removeView((View)object);

    }

    //instantiateItem() 메소드에서 리턴된 Ojbect가 View가  맞는지 확인하는 메소드
    @Override
    public boolean isViewFromObject(View v, Object obj) {
        // TODO Auto-generated method stub
        return v==obj;
    }

    class recordList{
        private String _time;
        private String _card;
        private int _factor;

        public String getTime(){return _time;}
        public String getCard(){return _card;}

        public recordList(String time,String card,int factor){
            _time=time;
            _card=card;
            _factor=factor;

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
    private class recordListViewItemLongClickListener implements AdapterView.OnItemLongClickListener {

        int realposition;


        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


            //item 위치 얻기
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
            alertDlg.setTitle("삭제하시겠습니까?");
            AlertDialog alert = alertDlg.create();
            realposition = position;


            // '예' 버튼이 클릭되면
            alertDlg.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    int i=0;
                    int sum=0;
                    int pick=0;
                    int number=0;
                    for(i=0;i<7;i++){
                        if(nowposition[i]==1)
                        {
                            pick++;
                            sum=sum+i;
                        }
                    }
                    if (pick == 3) {
                        number = sum / 3;
                    } else if (sum == 1) {
                        number = 0;

                    } else {
                        number = 6;
                    }



                    Calendar cal= Calendar.getInstance();
                    int todayweek = cal.get(Calendar.DAY_OF_WEEK);
                    cal.add(Calendar.DATE,-6+number);
                    int day_of_week = cal.get(Calendar.DAY_OF_WEEK);

                    dbString = "record" + day_of_week;


                    if(todayweek<day_of_week){
                        if(dbVersion!=1) {
                            dbVersion = dbVersion - 1;
                        }

                    }

                    String dbName = dbString+".db";






                    if(day_of_week==6){
                        DBHelper6 dbHelper6 = new DBHelper6(ctx, dbName, null, dbVersion);
                        db = dbHelper6.getWritableDatabase();
                        _lists6.remove(realposition);
                        adapter6.notifyDataSetChanged();

                        if(todayweek==day_of_week)
                           Aactivity._lists.remove(realposition);
                    }else if(day_of_week==5){
                        DBHelper5 dbHelper5 = new DBHelper5(ctx, dbName, null, dbVersion);
                        db = dbHelper5.getWritableDatabase();
                        _lists5.remove(realposition);
                        adapter5.notifyDataSetChanged();
                        if(todayweek==day_of_week)
                            Aactivity._lists.remove(realposition);
                    }else if(day_of_week==4){
                        DBHelper4 dbHelper4 = new DBHelper4(ctx, dbName, null, dbVersion);
                        db = dbHelper4.getWritableDatabase();
                        _lists4.remove(realposition);
                        adapter4.notifyDataSetChanged();
                        if(todayweek==day_of_week)
                            Aactivity._lists.remove(realposition);
                    }else if(day_of_week==3){
                        DBHelper3 dbHelper3 = new DBHelper3(ctx, dbName, null, dbVersion);
                        db = dbHelper3.getWritableDatabase();
                        _lists3.remove(realposition);
                        adapter3.notifyDataSetChanged();
                        if(todayweek==day_of_week)
                            Aactivity._lists.remove(realposition);
                    }else if(day_of_week==2){
                        DBHelper2 dbHelper2 = new DBHelper2(ctx, dbName, null, dbVersion);
                        db = dbHelper2.getWritableDatabase();
                        _lists2.remove(realposition);
                        adapter2.notifyDataSetChanged();
                        if(todayweek==day_of_week)
                            Aactivity._lists.remove(realposition);
                    }else if(day_of_week==1){
                        DBHelper1 dbHelper1 = new DBHelper1(ctx, dbName, null, dbVersion);
                        db = dbHelper1.getWritableDatabase();
                        _lists1.remove(realposition);
                        adapter1.notifyDataSetChanged();
                        if(todayweek==day_of_week)
                            Aactivity._lists.remove(realposition);
                    }else{
                        DBHelper7 dbHelper7 = new DBHelper7(ctx, dbName, null, dbVersion);
                        db = dbHelper7.getWritableDatabase();
                        _lists1.remove(realposition);
                        adapter1.notifyDataSetChanged();
                        if(todayweek==day_of_week)
                            Aactivity._lists.remove(realposition);
                    }


                    sql = "SELECT * FROM "+dbString+";";
                    Cursor cs = db.rawQuery(sql, null);
                    cs.moveToPosition(realposition);
                    int realid = cs.getInt(0);



                    sql = "DELETE FROM "+dbString+" WHERE _id=" + realid + ";";
                    db.execSQL(sql);
                    // 아래 method를 호출하지 않을 경우, 삭제된 item이 화면에 계속 보여진다.

                    cs.close();
                    dialog.dismiss();  // AlertDialog를 닫는다.
                }
            });

            // '아니오' 버튼이 클릭되면
            alertDlg.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();  // AlertDialog를 닫는다.
                }
            });
            alertDlg.setMessage("삭제 하시겠습니까?");
            alertDlg.show();


            return true;
        }

    }
}
