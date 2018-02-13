package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.R;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TurnKeyboardOnFragment extends Fragment {

    @BindView(R.id.step_one_check_wrapper) RelativeLayout checkWrapper;
    @BindView(R.id.step_one_info_wrapper) RelativeLayout infoWrapper;
    @BindView(R.id.turn_on_button) BootstrapButton turnOnButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turn_keyboard_on, container, false);
        ButterKnife.bind(this, view);
        handleKeyboardEnabledLogic();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        handleKeyboardEnabledLogic();
    }

    @OnClick(R.id.turn_on_button)
    public void turnOnClick() {
        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        startActivity(intent);
    }

    private void handleKeyboardEnabledLogic() {
        if(Constants.isKeyboardTurnedOn(getActivity())) {
            infoWrapper.setVisibility(View.INVISIBLE);
            checkWrapper.setVisibility(View.VISIBLE);
        } else {
            infoWrapper.setVisibility(View.VISIBLE);
            checkWrapper.setVisibility(View.INVISIBLE);
        }
    }

}
