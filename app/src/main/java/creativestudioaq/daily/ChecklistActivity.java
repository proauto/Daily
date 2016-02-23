package creativestudioaq.daily;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by honggyu on 2016-01-06.
 */
public class ChecklistActivity extends BaseActivity {

    String dbName = "checklist.db";
    int dbVersion = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String sql;
    ArrayList<checkList> Datas = new ArrayList<>();
    checkListAdapter adapter;
    MainActivity Aactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        Aactivity = MainActivity.Aactivity;

        dbHelper = new DBHelper(this, dbName, null, dbVersion);
        db = dbHelper.getWritableDatabase();

        sql = "SELECT * FROM checklist;";
        Cursor cursor = db.rawQuery(sql, null);
        try {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    checkList s = new checkList(cursor.getInt(1), cursor.getString(2));
                    Datas.add(s);
                }
            } else {
            }
        } finally {
            cursor.close();
        }

        Button back = (Button) findViewById(R.id.back);
        Button inventory = (Button) findViewById(R.id.buttoninventory);
        Button setting = (Button) findViewById(R.id.buttonsetting);
        ListView checklist = (ListView) findViewById(R.id.checklist);
        Button plus = (Button) findViewById(R.id.buttonplus);



        adapter = new checkListAdapter(this, R.layout.checklist_contentnew, Datas);

        checklist.setAdapter(adapter);
        checklist.setOnItemClickListener(new ListViewItemClickListener());
        checklist.setOnItemLongClickListener(new ListViewItemLongClickListener());

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.back:

                        finish();
                        break;

                    case R.id.buttoninventory:
                        Intent intent1 = new Intent(ChecklistActivity.this, InventoryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.buttonsetting:
                        Intent intent2 = new Intent(ChecklistActivity.this, SettingActivity.class);
                        startActivity(intent2);
                        break;
                }

            }
        };


        back.setOnClickListener(click);
        inventory.setOnClickListener(click);
        setting.setOnClickListener(click);
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(ChecklistActivity.this);
                alert.setTitle("체크리스트 생성");

                final EditText name = new EditText(ChecklistActivity.this);
                name.setSingleLine();
                int maxLength = 15;
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(maxLength);
                name.setFilters(fArray);
                alert.setView(name);

                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String checkname = name.getText().toString();
                        checkname = checkname.replace("'", "''");

                        if (checkname.equals("")) {
                            Toast.makeText(ChecklistActivity.this, "체크리스트를 입력해주세요~!", Toast.LENGTH_SHORT).show();
                        } else {

                            db = dbHelper.getWritableDatabase();
                            sql = String.format("INSERT INTO checklist VALUES(NULL,1,'%s');", checkname);
                            db.execSQL(sql);


                            checkList s = new checkList(1, checkname);
                            Datas.add(s);
                            Aactivity.Datas.add(s);

                            adapter.notifyDataSetChanged();
                        }
                    }
                });


                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = alert.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(DialogInterface dialog) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
                    }
                });

                dialog.show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
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

    private class ListViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            db = dbHelper.getReadableDatabase();
            sql = "SELECT * FROM checklist;";
            Cursor cs = db.rawQuery(sql, null);
            cs.moveToPosition(position);
            int realid = cs.getInt(0);
            int button = cs.getInt(1);
            String content = cs.getString(2);
            cs.close();

            if (button == 1) {
                button = 2;
                sql = "UPDATE checklist SET button=" + button + ",content='" + content + "' WHERE _id=" + realid + ";";
                db.execSQL(sql);
                checkList s = new checkList(button, content);
                Datas.set(position, s);
                Aactivity.Datas.set(position, s);
                adapter.notifyDataSetChanged();
            } else {
                button = 1;
                sql = "UPDATE checklist SET button=" + button + ",content='" + content + "' WHERE _id=" + realid + ";";
                db.execSQL(sql);
                checkList s = new checkList(button, content);
                Datas.set(position, s);
                Aactivity.Datas.set(position,s);
                adapter.notifyDataSetChanged();

            }

        }

    }

    private class ListViewItemLongClickListener implements AdapterView.OnItemLongClickListener {

        int realposition;


        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


            //item 위치 얻기
            final AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
            alertDlg.setTitle("삭제하시겠습니까?");
            AlertDialog alert = alertDlg.create();
            realposition = position;


            // '예' 버튼이 클릭되면
            alertDlg.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db = dbHelper.getReadableDatabase();
                    sql = "SELECT * FROM checklist;";
                    Cursor cs = db.rawQuery(sql, null);
                    cs.moveToPosition(realposition);
                    int realid = cs.getInt(0);
                    Datas.remove(realposition);
                    Aactivity.Datas.remove(realposition);


                    sql = "DELETE FROM checklist WHERE _id=" + realid + ";";
                    db.execSQL(sql);
                    // 아래 method를 호출하지 않을 경우, 삭제된 item이 화면에 계속 보여진다.
                    adapter.notifyDataSetChanged();
                    cs.close();
                    dialog.dismiss();  // AlertDialog를 닫는다.
                }
            });

            alertDlg.setNeutralButton("수정", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();  // AlertDialog를 닫는다.


                    AlertDialog.Builder alert = new AlertDialog.Builder(ChecklistActivity.this);
                    alert.setTitle("카테고리 수정");

                    final EditText name = new EditText(ChecklistActivity.this);
                    name.setSingleLine();
                    name.setText("" + Datas.get(position).getcontent());
                    int maxLength = 15;
                    InputFilter[] fArray = new InputFilter[1];
                    fArray[0] = new InputFilter.LengthFilter(maxLength);
                    name.setFilters(fArray);
                    alert.setView(name);

                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String category = name.getText().toString();
                            category = category.replace("'", "''");

                            if (category.equals("")) {
                                Toast.makeText(ChecklistActivity.this, "카테고리를 수정해주세요~!", Toast.LENGTH_SHORT).show();
                            } else {

                                sql = "SELECT * FROM checklist;";
                                Cursor cs = db.rawQuery(sql, null);
                                cs.moveToPosition(realposition);
                                int realid = cs.getInt(0);
                                sql = "UPDATE checklist SET content='" + category + "' WHERE _id=" + realid + ";";
                                db.execSQL(sql);
                                checkList s = new checkList(1, category);
                                Datas.set(realposition, s);
                                Aactivity.Datas.set(realposition,s);
                                adapter.notifyDataSetChanged();


                                cs.close();
                            }
                        }
                    });
                    alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();


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
