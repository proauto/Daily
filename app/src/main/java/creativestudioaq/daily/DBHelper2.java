package creativestudioaq.daily;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by honggyu on 2015-12-26.
 */
public class DBHelper2 extends SQLiteOpenHelper {

    SharedPreferences settingshared;
    String sql;

    //생성자 - database 파일을 생성합니다.
    public DBHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        settingshared =context.getSharedPreferences("setting", 0);
    }

    //DB 처음 만들때 한번만 호출 -테이블을 생성합니다.

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE record2 (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   date TEXT, name TEXT, factor INTEGER);");
        String before = settingshared.getString("before2", "설치전");
        sql=String.format("INSERT INTO record2 VALUES(NULL,'00:00','%s',0);",before);
        db.execSQL(sql);
    }

    //버전이 업데이트되면 DB를 다시 만듭니다.

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS record2");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        Log.v("ㅇㅇ", "망함");
    }
}