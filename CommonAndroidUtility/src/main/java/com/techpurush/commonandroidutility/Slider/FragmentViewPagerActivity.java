package com.techpurush.commonandroidutility.Slider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.techpurush.commonandroidutility.R;

public class FragmentViewPagerActivity extends AppCompatActivity {


    // tab titles
    private String[] titles = new String[]{"Movies", "Events", "Tickets"};

    Button btnNext, btnSkip;
    ImageView iconMore;
    LinearLayout layoutDots;
    TabLayout tabLayout;

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view_pager);

        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);

        init();
    }

    private void init() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);

       viewPager.setAdapter(new ViewPagerFragmentAdapter(this));

        // attaching tab mediator
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();
    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new MoviesFragment();
                case 1:
                    return new EventsFragment();
                case 2:
                    return new TicketsFragment();
            }
            return new MoviesFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}
