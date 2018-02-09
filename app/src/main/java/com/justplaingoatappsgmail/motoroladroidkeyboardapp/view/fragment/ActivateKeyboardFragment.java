package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.justplaingoatappsgmail.motoroladroidkeyboardapp.R;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment.util.Constants;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class ActivateKeyboardFragment extends Fragment {

    @BindView(R.id.step_two_check_wrapper) RelativeLayout checkWrapper;
    @BindView(R.id.step_two_info_wrapper) RelativeLayout infoWrapper;
    @BindView(R.id.step_two_warning_wrapper) RelativeLayout warningWrapper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activate_keyboard, container, false);
        ButterKnife.bind(this, view);
        handleChooseKeyboardLogic();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        handleChooseKeyboardLogic();
    }

    @OnClick(R.id.activate_button)
    public void chooseKeyboardClick() {
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showInputMethodPicker();
    }

    public void handleChooseKeyboardLogic() {
        boolean turnedOn = Constants.isKeyboardTurnedOn(getActivity());
        boolean chosen = Constants.isKeyboardChosen(getActivity());
        if(turnedOn && chosen) {
            infoWrapper.setVisibility(View.INVISIBLE);
            warningWrapper.setVisibility(View.INVISIBLE);
            checkWrapper.setVisibility(View.VISIBLE);
        } else if(turnedOn && !chosen) {
            infoWrapper.setVisibility(View.VISIBLE);
            warningWrapper.setVisibility(View.INVISIBLE);
            checkWrapper.setVisibility(View.INVISIBLE);
        } else {
            infoWrapper.setVisibility(View.INVISIBLE);
            warningWrapper.setVisibility(View.VISIBLE);
            checkWrapper.setVisibility(View.INVISIBLE);
        }
    }

}
