package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.R;

import butterknife.ButterKnife;

public class ActivateKeyboardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activate_keyboard, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



}
