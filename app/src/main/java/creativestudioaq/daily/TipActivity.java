package creativestudioaq.daily;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by honggyu on 2016-01-06.
 */
public class TipActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        TextView tiptext1 = (TextView)findViewById(R.id.tiptext1);
        TextView tiptext2 = (TextView)findViewById(R.id.tiptext2);
        TextView tiptext3 = (TextView)findViewById(R.id.tiptext3);
        TextView tiptext4 = (TextView)findViewById(R.id.tiptext4);
        TextView tiptext5 = (TextView)findViewById(R.id.tiptext5);
        TextView tiptext6 = (TextView)findViewById(R.id.tiptext6);
        TextView tiptext7 = (TextView)findViewById(R.id.tiptext7);
        TextView tiptext8 = (TextView)findViewById(R.id.tiptext8);
        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String text1 = "1. 위젯을 생성하여 오늘의 기록과 그래프를 볼 수 있습니다.";
        String text2 = "2. 오늘의 기록과 그래프는 1주일마다 갱신됩니다.";
        String text3 = "3. 화면 속 위젯을 삭제하면 그래프의 해당 부분은 회색처리 됩니다.";
        String text4 = "4. 목표는 한번 작성하면 수정할 수 없습니다.";
        String text5 = "5. 위젯 클릭 점수(+2)와 목표 성공 여부(+10) 점수에 따른 레벨업.";
        String text6 = "6. 팝업 알림 삭제를 원할 시에는 환경설정에서 삭제할 수 있습니다.";
        String text7 = "7. 무기슬롯에는 현재 레벨의 무기가 장착됩니다.";
        String text8 = "8. 레벨업 차트는 Facebook 페이지를 참고하시기 바랍니다.";

        SpannableStringBuilder builder1 = new SpannableStringBuilder(text1);
        SpannableStringBuilder builder2 = new SpannableStringBuilder(text2);
        SpannableStringBuilder builder3 = new SpannableStringBuilder(text3);
        SpannableStringBuilder builder4 = new SpannableStringBuilder(text4);
        SpannableStringBuilder builder5 = new SpannableStringBuilder(text5);
        SpannableStringBuilder builder6 = new SpannableStringBuilder(text6);
        SpannableStringBuilder builder7 = new SpannableStringBuilder(text7);
        SpannableStringBuilder builder8 = new SpannableStringBuilder(text8);


        builder1.setSpan(new ForegroundColorSpan(Color.RED), 3, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder2.setSpan(new ForegroundColorSpan(Color.RED), 16, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder3.setSpan(new ForegroundColorSpan(Color.RED), 8, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder4.setSpan(new ForegroundColorSpan(Color.RED), 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder5.setSpan(new ForegroundColorSpan(Color.RED), 38, 40, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder6.setSpan(new ForegroundColorSpan(Color.RED), 20, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder7.setSpan(new ForegroundColorSpan(Color.RED), 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder8.setSpan(new ForegroundColorSpan(Color.RED), 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        tiptext1.append(builder1);
        tiptext2.append(builder2);
        tiptext3.append(builder3);
        tiptext4.append(builder4);
        tiptext5.append(builder5);
        tiptext6.append(builder6);
        tiptext7.append(builder7);
        tiptext8.append(builder8);




    }

}

