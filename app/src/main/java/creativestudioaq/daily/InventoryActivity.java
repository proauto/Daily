package creativestudioaq.daily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by honggyu on 2016-01-06.
 */
public class InventoryActivity extends BaseActivity {

    SharedPreferences settingshared;
    ArrayList<inventoryList> Items = new ArrayList<>();
    inventoryListAdapter adapter1;
    int lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Button back = (Button) findViewById(R.id.back);
        Button settingbutton = (Button) findViewById(R.id.buttonsetting);
        ListView inventorylist = (ListView) findViewById(R.id.inventorylist);
        TextView shopshop = (TextView)findViewById(R.id.shopshop);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.shopshop:
                        Intent intent1= new Intent(InventoryActivity.this, ItemshopActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.back:
                        finish();
                        break;

                    case R.id.buttonsetting:
                        Intent intent2 = new Intent(InventoryActivity.this, SettingActivity.class);
                        startActivity(intent2);
                        break;
                }

            }
        };
        shopshop.setOnClickListener(click);
        back.setOnClickListener(click);
        settingbutton.setOnClickListener(click);


        settingshared = getSharedPreferences("setting", 0);
        lv = settingshared.getInt("level", 1);
        int speciallv = settingshared.getInt("speciallevel",0);

        if(speciallv==0) {
            if (lv > 15) {
                lv = 15;
            }

            int factor = lv / 3;
            int i;
            int remain = lv - factor * 3;

            inventoryList s = new inventoryList(1, 1, 1);

            if (factor > 0) {
                for (i = 0; i < factor; i++) {
                    Items.add(s);
                }
            }
            if (factor != 5) {
                if (remain == 0) {
                    s = new inventoryList(0, 0, 0);
                    Items.add(s);
                } else if (remain == 1) {
                    s = new inventoryList(1, 0, 0);
                    Items.add(s);
                } else {
                    s = new inventoryList(1, 1, 0);
                    Items.add(s);
                }
            }

            if (factor < 4) {
                for (i = 0; i < 4 - factor; i++) {
                    s = new inventoryList(0, 0, 0);
                    Items.add(s);
                }
            }
            //16~19
            s = new inventoryList(0, 0, 0);
            Items.add(s);
        }else if(speciallv==1){
            int j=0;
            inventoryList s1 = new inventoryList(1, 1, 1);
            for(j=0;j<5;j++){

                Items.add(s1);
            }
            s1 = new inventoryList(1,0,0);
            Items.add(s1);
            lv=16;

        }else if(speciallv==2){
            int j=0;
            inventoryList s1 = new inventoryList(1, 1, 1);
            for(j=0;j<5;j++){

                Items.add(s1);
            }
            s1 = new inventoryList(1,1,0);
            Items.add(s1);
            lv=17;

        }else {
            int j=0;
            inventoryList s1 = new inventoryList(1, 1, 1);
            for(j=0;j<5;j++){

                Items.add(s1);
            }
            s1 = new inventoryList(1,1,1);
            Items.add(s1);
            lv=18;

        }

        adapter1 = new inventoryListAdapter(this, R.layout.inventory_content, Items);

        inventorylist.setAdapter(adapter1);


        settingshared = getSharedPreferences("setting", 0);

        settingshared.getInt("clicknumber", 0);





    }


    class inventoryList{
        private int _item1;
        private int _item2;
        private int _item3;

        public int getItem1(){
            return _item1;
        }
        public int getItem2(){
            return _item2;
        }
        public int getItem3(){
            return _item3;
        }
        public inventoryList(int item1,int item2,int item3){

            _item1=item1;
            _item2=item2;
            _item3=item3;

        }

    }

    class inventoryListAdapter extends BaseAdapter {

        private LayoutInflater _inflater;
        private ArrayList<inventoryList> _lists;
        private int _layout;
        private Context m_ctx;
        private inventoryList list;


        public inventoryListAdapter(Context context, int layout, ArrayList<inventoryList> lists){
            _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _lists=lists;
            _layout = layout;
            m_ctx = context;
        }

        @Override
        public int getCount() {
            return _lists.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {

            ViewHolder1 holder1;
            if (convertView == null) {
                convertView = _inflater.inflate(_layout, parent, false);

                holder1 = new ViewHolder1();
                holder1.tem1 = (ImageView) convertView.findViewById(R.id.item1);
                holder1.tem2 = (ImageView) convertView.findViewById(R.id.item2);
                holder1.tem3 = (ImageView) convertView.findViewById(R.id.item3);
                holder1.tem1.setTag(position);
                holder1.tem2.setTag(position);
                holder1.tem3.setTag(position);

                View.OnClickListener click = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position1 = (Integer)v.getTag();


                        switch(v.getId()){
                            case R.id.item1:

                                if(lv>=position1*3+1)
                                {
                                    Intent intent1 = new Intent(InventoryActivity.this,ItemdetailActivity.class);
                                    intent1.putExtra("level",position1*3+1);
                                    startActivity(intent1);

                                }else{
                                   Toast toast = Toast.makeText(InventoryActivity.this, "좀 더 강해져야 볼 수 있어요!!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }


                                break;
                            case R.id.item2:
                                if(lv>=position1*3+2)
                                {
                                Intent intent2 = new Intent(InventoryActivity.this,ItemdetailActivity.class);
                              intent2.putExtra("level",position1*3+2);
                                startActivity(intent2);
                                }else{
                                    Toast toast = Toast.makeText(InventoryActivity.this, "좀 더 강해져야 볼 수 있어요!!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                break;
                            case R.id.item3:
                                if(lv>=position1*3+3)
                                {
                                Intent intent3 = new Intent(InventoryActivity.this,ItemdetailActivity.class);
                               intent3.putExtra("level",(position1+1)*3);
                                startActivity(intent3);
                                }else{
                                    Toast toast = Toast.makeText(InventoryActivity.this, "좀 더 강해져야 볼 수 있어요!!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                break;
                        }


                    }
                };

                holder1.tem1.setOnClickListener(click);
                holder1.tem2.setOnClickListener(click);
                holder1.tem3.setOnClickListener(click);
                convertView.setTag(holder1);
            } else {
                holder1 = (ViewHolder1) convertView.getTag();
                holder1 = new ViewHolder1();
                holder1.tem1 = (ImageView) convertView.findViewById(R.id.item1);
                holder1.tem2 = (ImageView) convertView.findViewById(R.id.item2);
                holder1.tem3 = (ImageView) convertView.findViewById(R.id.item3);
                holder1.tem1.setTag(position);
                holder1.tem2.setTag(position);
                holder1.tem3.setTag(position);



                View.OnClickListener click = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position1 = (Integer)v.getTag();


                        switch(v.getId()){
                            case R.id.item1:

                                if(lv>=position1*3+1)
                                {
                                    Intent intent1 = new Intent(InventoryActivity.this,ItemdetailActivity.class);
                                    intent1.putExtra("level",position1*3+1);
                                    startActivity(intent1);

                                }else{
                                    Toast toast = Toast.makeText(InventoryActivity.this, "좀 더 강해져야 볼 수 있어요!!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }


                                break;
                            case R.id.item2:
                                if(lv>=position1*3+2)
                                {
                                    Intent intent2 = new Intent(InventoryActivity.this,ItemdetailActivity.class);
                                    intent2.putExtra("level",position1*3+2);
                                    startActivity(intent2);
                                }else{
                                    Toast toast = Toast.makeText(InventoryActivity.this, "좀 더 강해져야 볼 수 있어요!!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                break;
                            case R.id.item3:
                                if(lv>=position1*3+3)
                                {
                                    Intent intent3 = new Intent(InventoryActivity.this,ItemdetailActivity.class);
                                    intent3.putExtra("level",(position1+1)*3);
                                    startActivity(intent3);
                                }else{
                                    Toast toast = Toast.makeText(InventoryActivity.this, "좀 더 강해져야 볼 수 있어요!!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                break;
                        }


                    }
                };

                holder1.tem1.setOnClickListener(click);
                holder1.tem2.setOnClickListener(click);
                holder1.tem3.setOnClickListener(click);
            }

            list = _lists.get(position);

            int number1 = position*3+1;
            int number2 = position*3+2;
            int number3 = (position+1)*3;

            String resName1 = "@drawable/itemimg_"+ number1 +"_" + list.getItem1();
            int resID1 = getResources().getIdentifier(resName1, "drawable", getPackageName());
            String resName2 = "@drawable/itemimg_"+ number2 +"_" + list.getItem2();
            int resID2 = getResources().getIdentifier(resName2, "drawable", getPackageName());
            String resName3 = "@drawable/itemimg_"+ number3 +"_" + list.getItem3();
            int resID3 = getResources().getIdentifier(resName3, "drawable", getPackageName());


                Picasso.with(m_ctx).load(resID1).into(holder1.tem1);
                Picasso.with(m_ctx).load(resID2).into(holder1.tem2);
                Picasso.with(m_ctx).load(resID3).into(holder1.tem3);


            return convertView;
        }
    }

    private class ViewHolder1 {
        public ImageView tem1, tem2, tem3;

    }

}
