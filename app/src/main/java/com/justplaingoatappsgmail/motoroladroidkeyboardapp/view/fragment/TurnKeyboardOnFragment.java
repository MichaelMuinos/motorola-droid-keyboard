package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TurnKeyboardOnFragment extends Fragment {

    private static final int INPUT_METHOD_SETTING_RESULT_CODE = 9000;

    @BindView(R.id.step_one_check_wrapper) RelativeLayout checkWrapper;
    @BindView(R.id.explanation_text_view) TextView explanation;
    @BindView(R.id.turn_on_button) BootstrapButton turnOnButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turn_keyboard_on, container, false);
        ButterKnife.bind(this, view);
        isTurnedOn();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // check if the keyboard is turned on or not
    }

    @OnClick(R.id.turn_on_button)
    public void turnOnClick() {
        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        startActivity(intent);
    }

    private boolean isTurnedOn() {
        String enabledInputMethods = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ENABLED_INPUT_METHODS);
        Log.d("tag", enabledInputMethods);
        return false;
    }

}
