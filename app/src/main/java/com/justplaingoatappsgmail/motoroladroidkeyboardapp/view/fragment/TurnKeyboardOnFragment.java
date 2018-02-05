package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    @BindView(R.id.step_one_check_wrapper) RelativeLayout checkWrapper;
    @BindView(R.id.explanation_text_view) TextView explanation;
    @BindView(R.id.turn_on_button) BootstrapButton turnOnButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turn_keyboard_on, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.turn_on_button)
    public void turnOnClick() {

    }

}
