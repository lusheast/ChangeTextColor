package moocollege.cn.changetextcolor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsd on 2017/8/4 10:57
 * desc:
 */

public class HomePageActivity extends AppCompatActivity {
    private String[] items = {"关注","推荐", "历史", "美食", "热点", "视频", "社会"};
    private List<ChangeTextColor> mIndicators;
    private ViewPager mViewPager;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        linearLayout = (LinearLayout) findViewById(R.id.ll_top);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mIndicators = new ArrayList<>();

        initIndicator();
        initViewPager();
    }

    private void initViewPager() {
        //设置adapter
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentContent.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }

        });
        mViewPager.setCurrentItem(1);
        //设置viewpager的监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ChangeTextColor leftDirection = mIndicators.get(position);
                leftDirection.setDirection(ChangeTextColor.Direction.FROME_RIGHT_TO_LEFT);
                leftDirection.setCurrentProgress(1-positionOffset);

                try {
                    ChangeTextColor rightDirection = mIndicators.get(position+1);
                    rightDirection.setDirection(ChangeTextColor.Direction.FROME_LEFT_TO_RIGHT);
                    rightDirection.setCurrentProgress(positionOffset);
                }catch (Exception e){

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicator() {
        for (int i = 0; i < items.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ChangeTextColor changeTextColor = new ChangeTextColor(this);
            // 设置颜色
            changeTextColor.setTextSize(25);
            changeTextColor.setChangeColor(Color.RED);
            changeTextColor.setText(items[i]);
            changeTextColor.setLayoutParams(params);
            // 把新的加入LinearLayout容器
            linearLayout.addView(changeTextColor);
            // 加入集合
            mIndicators.add(changeTextColor);
        }
    }
}
