package org.muferobotics.olympics.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.core.ApiConstants;
import org.muferobotics.olympics.model.Announcement;
import org.muferobotics.olympics.model.rest.request.GetAnnouncementsRequest;
import org.muferobotics.olympics.ui.adapter.ResultAdapter;
import org.muferobotics.olympics.ui.fragment.ResultFragment;
import org.muferobotics.olympics.ui.fragment.ResultMatchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ResultsActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected int getContentViewLayoutResId() {
        return R.layout.activity_results;
    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableToolbarNavigation();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.istanbul);
        tabLayout.getTabAt(1).setIcon(R.drawable.fire);
        tabLayout.getTabAt(2).setIcon(R.drawable.ball);
        tabLayout.getTabAt(3).setIcon(R.drawable.color);
        tabLayout.getTabAt(4).setIcon(R.drawable.sumo);
        tabLayout.getTabAt(5).setIcon(R.drawable.flag);
        tabLayout.getTabAt(6).setIcon(R.drawable.juri);
        tabLayout.getTabAt(7).setIcon(R.drawable.free);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ResultFragment().newInstance("0"), getString(R.string.robot_istanbul));
        adapter.addFragment(new ResultFragment().newInstance("1"), getString(R.string.robot_fire));
        adapter.addFragment(new ResultMatchFragment().newInstance("2"), getString(R.string.robot_tenis));
        adapter.addFragment(new ResultFragment().newInstance("3"), getString(R.string.robot_color));
        adapter.addFragment(new ResultMatchFragment().newInstance("4"), getString(R.string.robot_sumo));
        adapter.addFragment(new ResultMatchFragment().newInstance("5"), getString(R.string.robot_flag));
        adapter.addFragment(new ResultFragment().newInstance("6"), getString(R.string.robot_judges));
        adapter.addFragment(new ResultFragment().newInstance("7"), getString(R.string.robot_free));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}