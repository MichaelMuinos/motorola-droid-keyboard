package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.justplaingoatappsgmail.motoroladroidkeyboardapp.R;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment.ActivateKeyboardFragment;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment.TurnKeyboardOnFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnableKeyboardActivity extends AppCompatActivity {

    @BindView(R.id.pager) ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_keyboard);
        ButterKnife.bind(this);
        pager.setAdapter(new EnableKeyboardAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            ActivateKeyboardFragment page = (ActivateKeyboardFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + 1);
            if(page != null) {
                page.handleChooseKeyboardLogic();
                Log.d("tag", "page is not null");
            } else {
                Log.d("tag", "page is null");
            }
        }
    }

    private class EnableKeyboardAdapter extends FragmentPagerAdapter {

        private static final int NUMBER_OF_FRAGMENTS = 2;

        public EnableKeyboardAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new TurnKeyboardOnFragment();
                case 1:
                    return new ActivateKeyboardFragment();
                case 2:
                    return null;
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_FRAGMENTS;
        }

    }

}
