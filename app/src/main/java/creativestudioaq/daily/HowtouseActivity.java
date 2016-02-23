package creativestudioaq.daily;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by honggyu on 2016-01-24.
 */
public class HowtouseActivity extends BaseActivity {

    ViewPager pager;
    private Picasso picasso;
    private LruCache picassoLruCahce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtouse);


        pager= (ViewPager)findViewById(R.id.pager);

        picassoLruCahce = new LruCache(this);
        picasso = new Picasso.Builder(this).memoryCache(picassoLruCahce).build();

        //ViewPager에 설정할 Adapter 객체 생성
        //ListView에서 사용하는 Adapter와 같은 역할.
        //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
        //PagerAdapter를 상속받은 CustomAdapter 객체 생성
        //CustomAdapter에게 LayoutInflater 객체 전달
        HowtouseAdapter adapter= new HowtouseAdapter(getLayoutInflater(),this,picasso,picassoLruCahce);

        //ViewPager에 Adapter 설정
       // pager.setOffscreenPageLimit(4);
        pager.setAdapter(adapter);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        picassoLruCahce.clear();
        System.gc();
    }
}
