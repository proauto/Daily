package creativestudioaq.daily;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by honggyu on 2016-01-06.
 */
public class VoidActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_void);


        ImageView Image1 = (ImageView)findViewById(R.id.Image1);
        Picasso.with(this).load(R.drawable.appicon).fit().into(Image1);


    }

}

