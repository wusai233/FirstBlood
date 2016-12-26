package sai.org.com.firstboold.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import sai.org.com.firstboold.fragment.GuideBaseFragment;

/**
 * Created by wujamie on 16/12/27.
 */

public class GuideFragmentAdapter extends FragmentStatePagerAdapter {
    private List<GuideBaseFragment> list;

    public GuideFragmentAdapter(FragmentManager fragmentManager, List<GuideBaseFragment> list) {
        super(fragmentManager);
        this.list = list;
    }

    public GuideFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
