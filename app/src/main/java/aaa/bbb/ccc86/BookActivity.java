package aaa.bbb.ccc86;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gohn.parallaxviewpager.ParallaxViewPager;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        ParallaxViewPager pager = (ParallaxViewPager) findViewById(R.id.pager);

        pager.addMovementToView(R.id.title, 0.0f);
        pager.addMovementToView(R.id.text, 0.1f);
        pager.addMovementToView(R.id.image, 0.1f);

        pager.setAdapter(new Adapter(getLayoutInflater(), BookActivity.this));
    }
}
