package creativestudioaq.daily;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by honggyu on 2016-01-20.
 */
public class GraphAdapter  extends PagerAdapter {



    SharedPreferences settingshared;
    SharedPreferences.Editor editor;
    String dbName1;
    int dbVersion1;

    SQLiteDatabase db1;
    String sql1;

    private FrameLayout thirdlayout;
    private PieChart mChart;
    ArrayList<recordList> _lists= new ArrayList<>();
    ArrayList<Integer> ylist = new ArrayList<>();
    ArrayList<String> xlist = new ArrayList<>();
    String[] legendstringchange;
    int[] result;
    Context ctx;
    Typeface mTypeface;


    int realcolor1 = Color.rgb(253, 214, 90);
    int realcolor2 = Color.rgb(255, 64, 64);
    int realcolor3 = Color.rgb(250, 144, 44);
    int realcolor4 = Color.rgb(255, 136, 150);
    int realcolor5 = Color.rgb(198, 156, 109);
    int realcolor6 = Color.rgb(122, 201, 67);
    int realcolor7 = Color.rgb(41, 171, 226);
    int realcolor8 = Color.rgb(13, 89, 127);
    LayoutInflater inflater;

    public GraphAdapter(LayoutInflater inflater, Context context, Typeface typeface) {
        // TODO Auto-generated constructor stub

        //전달 받은 LayoutInflater를 멤버변수로 전달
        this.inflater=inflater;
        this.ctx=context;
        this.mTypeface=typeface;
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
        view= inflater.inflate(R.layout.graph_child, null);


        //만들어진 View안에 있는 ImageView 객체 참조
        //위에서 inflated 되어 만들어진 view로부터 findViewById()를 해야 하는 것에 주의.
        //ImageView img= (ImageView)view.findViewById(R.id.img_viewpager_childimage);

        //ImageView에 현재 position 번째에 해당하는 이미지를 보여주기 위한 작업
        //현재 position에 해당하는 이미지를 setting


        ylist = new ArrayList<>();
        xlist = new ArrayList<>();

        settingshared = ctx.getSharedPreferences("setting", 0);
        dbVersion1 = settingshared.getInt("dbversion",1);


        Calendar cal= Calendar.getInstance();
        int todayweek = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE,-6+position);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        int recent=0;

        if(todayweek<day_of_week){
            if(dbVersion1!=1) {
                dbVersion1 = dbVersion1 - 1;
            }

        }


        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
        Date currentTime = cal.getTime();
        String dTime = formatter.format(currentTime);
        String m_week="";


        if (day_of_week == 1) {
            m_week = "일요일";
            dbName1 = "record1.db";
            DBHelper1 dbHelper1 = new DBHelper1(ctx, dbName1, null, dbVersion1);
            db1 = dbHelper1.getWritableDatabase();
            sql1 = "SELECT * FROM record1;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {

                        ylist.add(cursor.getInt(3)-recent);
                        xlist.add(cursor.getString(2));
                        recent=cursor.getInt(3);

                    }
                } else {
                }
            } finally {
                cursor.close();
            }


            if(todayweek==day_of_week){

                Calendar mCalendar = Calendar.getInstance();

                SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
                int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

                SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
                int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

                int factor = hour*60 + minute;

                xlist.add("남은 오늘");
                ylist.add(factor-recent);
                ylist.add(1440-factor);

            }else{

                ylist.add(1440-recent);
            }
            ylist.remove(0);

        } else if (day_of_week == 2) {
            m_week = "월요일";
            dbName1 = "record2.db";
            DBHelper2 dbHelper2 = new DBHelper2(ctx, dbName1, null, dbVersion1);
            db1 = dbHelper2.getWritableDatabase();
            sql1 = "SELECT * FROM record2;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ylist.add(cursor.getInt(3)-recent);
                        xlist.add(cursor.getString(2));
                        recent=cursor.getInt(3);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            if(todayweek==day_of_week){

                Calendar mCalendar = Calendar.getInstance();

                SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
                int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

                SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
                int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

                int factor = hour*60 + minute;

                xlist.add("남은 오늘");
                ylist.add(factor-recent);
                ylist.add(1440-factor);

            }else{

                ylist.add(1440-recent);
            }
            ylist.remove(0);

        } else if (day_of_week == 3) {
            m_week = "화요일";
            dbName1 = "record3.db";
            DBHelper3 dbHelper3 = new DBHelper3(ctx, dbName1, null, dbVersion1);
            db1 = dbHelper3.getWritableDatabase();
            sql1 = "SELECT * FROM record3;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ylist.add(cursor.getInt(3)-recent);
                        xlist.add(cursor.getString(2));
                        recent=cursor.getInt(3);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            if(todayweek==day_of_week){

                Calendar mCalendar = Calendar.getInstance();

                SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
                int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

                SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
                int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

                int factor = hour*60 + minute;

                xlist.add("남은 오늘");
                ylist.add(factor-recent);
                ylist.add(1440-factor);

            }else{

                ylist.add(1440-recent);
            }
            ylist.remove(0);
        } else if (day_of_week == 4) {
            m_week = "수요일";
            dbName1 = "record4.db";
            DBHelper4 dbHelper4 = new DBHelper4(ctx, dbName1, null, dbVersion1);
            db1 = dbHelper4.getWritableDatabase();
            sql1 = "SELECT * FROM record4;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ylist.add(cursor.getInt(3)-recent);
                        xlist.add(cursor.getString(2));
                        recent=cursor.getInt(3);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            if(todayweek==day_of_week){

                Calendar mCalendar = Calendar.getInstance();

                SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
                int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

                SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
                int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

                int factor = hour*60 + minute;

                xlist.add("남은 오늘");
                ylist.add(factor-recent);
                ylist.add(1440-factor);

            }else{

                ylist.add(1440-recent);
            }
            ylist.remove(0);
        } else if (day_of_week == 5) {
            m_week = "목요일";
            dbName1 = "record5.db";
            DBHelper5 dbHelper5 = new DBHelper5(ctx, dbName1, null, dbVersion1);
            db1 = dbHelper5.getWritableDatabase();
            sql1 = "SELECT * FROM record5;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ylist.add(cursor.getInt(3)-recent);
                        xlist.add(cursor.getString(2));
                        recent=cursor.getInt(3);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            if(todayweek==day_of_week){

                Calendar mCalendar = Calendar.getInstance();

                SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
                int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

                SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
                int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

                int factor = hour*60 + minute;

                xlist.add("남은 오늘");
                ylist.add(factor-recent);
                ylist.add(1440-factor);

            }else{

                ylist.add(1440-recent);
            }
            ylist.remove(0);
        } else if (day_of_week == 6) {
            m_week = "금요일";
            dbName1 = "record6.db";
            DBHelper6 dbHelper6 = new DBHelper6(ctx, dbName1, null, dbVersion1);
            db1 = dbHelper6.getWritableDatabase();
            sql1 = "SELECT * FROM record6;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ylist.add(cursor.getInt(3)-recent);
                        xlist.add(cursor.getString(2));
                        recent=cursor.getInt(3);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            if(todayweek==day_of_week){

                Calendar mCalendar = Calendar.getInstance();

                SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
                int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

                SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
                int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

                int factor = hour*60 + minute;

                xlist.add("남은 오늘");
                ylist.add(factor-recent);
                ylist.add(1440-factor);

            }else{

                ylist.add(1440-recent);
            }
            ylist.remove(0);
        } else if (day_of_week == 7) {
            m_week = "토요일";
            dbName1 = "record7.db";
            DBHelper7 dbHelper7 = new DBHelper7(ctx, dbName1, null, dbVersion1);
            db1 = dbHelper7.getWritableDatabase();
            sql1 = "SELECT * FROM record7;";
            Cursor cursor = db1.rawQuery(sql1, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ylist.add(cursor.getInt(3)-recent);
                        xlist.add(cursor.getString(2));
                        recent=cursor.getInt(3);
                    }
                } else {
                }
            } finally {
                cursor.close();
            }
            if(todayweek==day_of_week){

                Calendar mCalendar = Calendar.getInstance();

                SimpleDateFormat Hour = new SimpleDateFormat("HH",Locale.KOREA);
                int hour = Integer.parseInt(Hour.format(mCalendar.getTime()));

                SimpleDateFormat Minute = new SimpleDateFormat("mm",Locale.KOREA);
                int minute = Integer.parseInt(Minute.format(mCalendar.getTime()));

                int factor = hour*60 + minute;

                xlist.add("남은 오늘");
                ylist.add(factor-recent);
                ylist.add(1440-factor);

            }else{

                ylist.add(1440-recent);
            }
            ylist.remove(0);
        }

        Integer[] ylistgo = ylist.toArray(new Integer[ylist.size()]);
        String[] xlistgo = xlist.toArray(new String[xlist.size()]);

        String day = dTime +" "+ m_week;

        thirdlayout = (FrameLayout)view.findViewById(R.id.thirdlayout);
        TextView whatday = (TextView)view.findViewById(R.id.whatday);
        whatday.setText(day);
        whatday.setTypeface(mTypeface);


        mChart = new PieChart(ctx);
        //add pie chart to Layout
        int leng =ylistgo.length;

        TextView backgroundthing = (TextView)view.findViewById(R.id.backgroundthing);
        if(leng!=1){

            thirdlayout.addView(mChart);
        }else{
            backgroundthing.setBackgroundResource(R.drawable.alarmtxt);
            thirdlayout.setBackgroundColor(Color.rgb(255,255,255));
        }



        //configure pit chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("용사의 하루");

        //enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(60);
        mChart.setTransparentCircleRadius(50);

        //enable rotation of the chart by touch
        mChart.setRotationAngle(270);
        mChart.setRotationEnabled(false);

        AppWidgetManager mgr = AppWidgetManager.getInstance(ctx);
        int[] sol = mgr.getAppWidgetIds(new ComponentName(ctx, WidgetProvider.class));
        PreferenceTool mTool = new PreferenceTool(ctx);

        ArrayList<Integer> legendint = new ArrayList<>();
        ArrayList<String> legendstring = new ArrayList<>();

        for(int i=0; i<sol.length;i++){
            String name = mTool.getString(""+sol[i],"");
            int j = mTool.getInt(""+sol[i]+"int",0);

            if(j==0){
                legendint.add(realcolor1);
            }
            else if(j==1){
                legendint.add(realcolor2);
            }
            else if(j==2){
                legendint.add(realcolor3);
            }
            else if(j==3){
                legendint.add(realcolor4);
            }
            else if(j==4){
                legendint.add(realcolor5);
            }
            else if(j==5){
                legendint.add(realcolor6);
            }
            else if(j==6){
                legendint.add(realcolor7);
            }
            else if(j==7){
                legendint.add(realcolor8);
            }

            legendstring.add(name);
        }

        Integer[] legendintchange = legendint.toArray(new Integer[legendint.size()]);
        legendstringchange = legendstring.toArray(new String[legendstring.size()]);

        result = new int[legendintchange.length];
        for (int i = 0; i < legendintchange.length; i++) {
            result[i] = legendintchange[i].intValue();
        }

        //add Data
        addData(ylistgo, xlistgo);

        //customize legends
        mChart.getLegend().setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE);
        mChart.getLegend().setTextSize(8f);
        mChart.getLegend().setCustom(result, legendstringchange);


        //ViewPager에 만들어 낸 View 추가
        container.addView(view);
        view.setTag(position);


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

    private void addData(Integer[] yData, String[] xData) {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        //create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        //dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);



        ArrayList<Integer> colors = new ArrayList<Integer>();

        int i = 0;

        int count = xData.length;
        int newcount = legendstringchange.length;

        while (i < count) {
            int j = 0;
            int k = 0;

            if (xData[i].equals("남은 오늘")) {
                colors.add(Color.rgb(255, 255, 255));
            } else {
                while (j < newcount) {

                    if (xData[i].equals(legendstringchange[j])) {
                        colors.add(result[j]);
                        k++;
                    }
                    j++;
                }
                if (k == 0) {

                    colors.add(Color.rgb(166,166,166));
                }
            }
            i++;
        }


        dataSet.setColors(colors);

        //instantiate pie data object now

        PieData data = new PieData(xVals, dataSet);
        data.setDrawValues(false);
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.BLACK);

        mChart.setData(data);

        //undo all highlights
        mChart.highlightValue(null);

        //update pie chart
        mChart.invalidate();

        //now it's time for demo !!!
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

}
