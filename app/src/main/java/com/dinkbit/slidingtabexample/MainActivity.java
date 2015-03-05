package com.dinkbit.slidingtabexample;

import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dinkbit.slidingtabexample.ui.tabs.SlidingTabLayout;


/**
 * The type Main activity.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        //Setting up SupportActionBar with toolbar
        setSupportActionBar(toolbar);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //Inflating pager from current contentView -> activity_main.xml
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);

        //Set current mSectionsPagerAdapter to mViewPager
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //Inflating tabs from current contentView -> activity_main.xml
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);

        //Distributing tabs at width
        slidingTabLayout.setDistributeEvenly(true);

        //Setting a customTabview
        slidingTabLayout.setCustomTabView(R.layout.tab_item_layout, R.id.tab_title, R.id.tab_image);

        //In case you need to change indicatorColors
        //slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(android.R.color.holo_orange_light));

        //Setting current mViewPager to slidingTabLayout
        slidingTabLayout.setViewPager(mViewPager);

        //TODO::Don´t forget to "hide" current ActionBar in styles.xml
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        /**
         * Instantiates a new Sections pager adapter.
         *
         * @param fm the fm
         */
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        //TODO::You can work with a String array from strings.xml or a String Constants.PageTitleArray to avoid this bad practice.
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }

        //TODO::You can create your own getPageIcon method too. Example below.
        //TODO::You can work with an int array or int Constants.PageIconsArray to avoid this bad practice.
        //TODO::Don´t forget to return your drawable resources.
        /*
        public int getPageIcon(int position) {

            switch (position) {
                case 0:
                    return R.drawable.ic_home;
                case 1:
                    return R.drawable.ic_video;
                case 2:
                    return R.drawable.ic_trending;
                case 3:
                    return R.drawable.ic_categories;
            }
            return 0;
        }*/
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         * @param sectionNumber the section number
         * @return the placeholder fragment
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        /**
         * Instantiates a new Placeholder fragment.
         */
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

            TextView textView = (TextView)rootView.findViewById(R.id.section_label);
            textView.setText(String.valueOf(sectionNumber));

            return rootView;
        }
    }

}
