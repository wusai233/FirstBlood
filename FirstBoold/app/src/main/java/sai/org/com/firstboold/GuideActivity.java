package sai.org.com.firstboold;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import sai.org.com.firstboold.adapter.GuideFragmentAdapter;
import sai.org.com.firstboold.fragment.FirstGuildFragment;
import sai.org.com.firstboold.fragment.FourGuildFragment;
import sai.org.com.firstboold.fragment.GuideBaseFragment;
import sai.org.com.firstboold.fragment.SecondGuildFragment;
import sai.org.com.firstboold.fragment.ThirdGuildFragment;
import sai.org.com.firstboold.widgets.GuidViewPager;

/**
 * Created by wujamie on 16/12/26.
 */

public class GuideActivity extends FragmentActivity {
    private GuidViewPager guidViewPager;
    private List<GuideBaseFragment> guildList = new ArrayList<GuideBaseFragment>();
    private GuideFragmentAdapter guildFragmentAdapter;

    private ImageView[] tips;
    private int currentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //初始化点点点控件
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        tips = new ImageView[3];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            tips[i] = imageView;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 20;//设置点点点view的左边距
            layoutParams.rightMargin = 20;//设置点点点view的右边距
            group.addView(imageView, layoutParams);
        }

        //获取自定义viewpager 然后设置背景图片
        guidViewPager = (GuidViewPager) findViewById(R.id.viewpager_launcher);
        guidViewPager.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.background_guide));

        /**
         * 初始化三个fragment  并且添加到list中
         */
        FirstGuildFragment firstGuildFragment = new FirstGuildFragment();
        SecondGuildFragment secondGuildFragment = new SecondGuildFragment();
        ThirdGuildFragment thirdGuildFragment = new ThirdGuildFragment();
        FourGuildFragment fourGuildFragment = new FourGuildFragment();
        guildList.add(firstGuildFragment);
        guildList.add(secondGuildFragment);
        guildList.add(thirdGuildFragment);
        guildList.add(fourGuildFragment);

        guildFragmentAdapter = new GuideFragmentAdapter(getSupportFragmentManager(), guildList);
        guidViewPager.setAdapter(guildFragmentAdapter);
        guidViewPager.setOffscreenPageLimit(2);
        guidViewPager.setCurrentItem(0);
        guidViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int index) {
                setImageBackground(index);//改变点点点的切换效果
                GuideBaseFragment fragment = guildList.get(index);

                guildList.get(currentIndex).stopAnimation();//停止前一个页面的动画
                fragment.startAnimation();//开启当前页面的动画

                currentIndex = index;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 改变点点点的切换效果
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
}
