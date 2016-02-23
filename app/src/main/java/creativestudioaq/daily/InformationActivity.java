package creativestudioaq.daily;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by honggyu on 2015-12-21.
 */
public class InformationActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        TextView informationtext = (TextView)findViewById(R.id.informationtext);
        TextView blog = (TextView)findViewById(R.id.blog);
        TextView facebook = (TextView)findViewById(R.id.facebook);


        informationtext.setText("version : 1.0\n\n " +
                "개발자 : H\n\n" +
                "디자이너 : M\n\n" +
                "Creativestudio AQ\n\n");

        Button back = (Button) findViewById(R.id.back);


        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.back:
                        finish();
                        break;


                }

            }
        };


        back.setOnClickListener(click);


    }
}
