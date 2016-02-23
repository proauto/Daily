package creativestudioaq.daily;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by honggyu on 2016-01-07.
 */
public class ItemdetailActivity extends BaseActivity {

    private Picasso picasso;
    private LruCache picassoLruCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetail);

        Intent intent = getIntent();
        int lv = intent.getExtras().getInt("level");
        String item = "";

        picassoLruCache = new LruCache(this);
        picasso = new Picasso.Builder(this).memoryCache(picassoLruCache).build();



        Button back = (Button)findViewById(R.id.back);
        ImageView itemdetailimg = (ImageView)findViewById(R.id.itemdetailimg);
        TextView itemdetailtext = (TextView)findViewById(R.id.itemdetailtext);
        TextView itemdetailname = (TextView)findViewById(R.id.itemdetailname);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String resName1 = "@drawable/w"+ lv;
        int resID1 = getResources().getIdentifier(resName1, "drawable", getPackageName());
        picasso.with(this).load(resID1).into(itemdetailimg);
        String detailstory="";


        if(lv==18){
            detailstory="누가 나를 심판하는가! 내가 바로 정의다!\n정의의 천사가 하사한 검";
            item="엔드 오브 저스티스 스워드";
        }else if(lv==17){
            detailstory="모두 태워주지!\n프레임 드래곤의 숨결로 만든 검";
            item="프레임 드래곤 하트 스워드";
        }else if(lv==16){
            detailstory="시간은 내 편이다.\n조심하는게 좋을걸?\n시간을 되돌리는 전설의 검";
            item="타임에코 블링크 스워드";
        }else if(lv==15){
            detailstory="만렙겁\n천상의 루비나이트 빙룡검\n여느 게임에서와 마찬가지로\n만렙이라고 만렙이 아니다\n유료버전을 기대하시길! ";
            item="천상의 루비나이트 빙룡검";
        }else if(lv==14){
            detailstory="이제 추운건 익숙해졌다!\n무기에 루비를 박아넣어서 지능 +5";
            item="루비나이트 빙룡검";
        }else if(lv==13){
            detailstory="무기가 멋있어진건 좋은데...\n손이 얼고있다...\n죽을 것 같아\n데스나이트 빙룡검";
            item="데스나이트 빙룡검";
        }else if(lv==12){
            detailstory="내 무기에 이런 능력이??? \n너의 이름은 얼음꽃 빙룡검이다! ";
            item="얼음꽃 빙룡검";
        }else if(lv==11){
            detailstory="아이스 스워드가 강해졌다! \n 다음 무기는 뭔가 좀 느낌이 다른데???";
            item="만개를 기다리는 아이스 스워드";
        }else if(lv==10){
            detailstory="손잡이보다 검날이 더 길어졌다!\n그만큼 강려크해진 내 아이스 스워드";
            item="아이스 스워드";
        }else if(lv==9){
            detailstory="역시 무기는 반짝반짝☆★\n내 무기도 이제 반짝반짝☆★\n(손잡이가 길어보이는건 착시☆★)";
            item="크로스 스워드";
        }else if(lv==8){
            detailstory="레벨업으로는 부족해!\n흑화...아 아니 각성이닷!!!\n그동안 허접한 무기로 고생했어 ㅜ";
            item="깨어난 각성검";
        }else if(lv==7){
            detailstory="이놈의 슬라임\n잡다가 검도 성장해 버렸다....\n슬라임을 잡고 레벨업!!!";
            item="슬라임 잡는 중 레벨업한 검";
        }else if(lv==6){
            detailstory="이제는 진짜 검이다!\n 그런데.... 겨우 슬라임 잡는데 휘어???\n 누가 만들었지...";
            item="슬라임 잡다 휜 검";
        }else if(lv==5){
            detailstory="용주부님이 즐겨쓰신 주방용 칼\n하찮아 보이지만 생각보다 강력한 전투력???";
            item="용주부가 즐겨쓰는 주방용 칼";
        }else if(lv==4){
            detailstory="금도끼 은도끼가 아니다!!\n\n강철도끼!!\n\n신령님도 이런건 처음 볼껄?";
            item="산신령도 못만져본 강철도끼";
        }else if(lv==3){
            detailstory="흔한 나무 도끼\n신령님도 이런건 거들떠도 안볼 것 같다.";
            item="산신령은 거들떠도 안보는 나무도끼";
        }else if(lv==2){
            detailstory="비쥬얼은 치킨이지만 사실 이건 몽둥이 \n나뭇가지 보다 미약하게 좋아짐";
            item="치킨아니야 몽둥이야 몽둥이";
        }else if(lv==1){
            detailstory="<용사의 하루>에 오신 당신\n 환영하는 의미로 드리는 미천한 선물\n 만렙나무 나뭇가지라 그럭저럭 쓸만하다.";
            item="어쩌다 마주친 나뭇가지";
        }




        String lvitem = "LV."+lv+" " +item;
        int factor = (lv/10)+4;
        int color = Color.RED;
        SpannableStringBuilder builder = new SpannableStringBuilder(lvitem);
        builder.setSpan(new ForegroundColorSpan(color),0,factor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        itemdetailname.append(builder);
        itemdetailtext.setText(detailstory);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        findViewById(R.id.invenback).setBackground(null);
        picassoLruCache.clear();
        System.gc();
    }
}
