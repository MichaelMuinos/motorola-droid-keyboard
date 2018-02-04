package com.justplaingoatappsgmail.motoroladroidkeyboardapp;

import android.inputmethodservice.InputMethodService;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MotorolaDroidMethodService extends InputMethodService {

    private static final Map<Integer,Pair<Character,Character>> keyboardPairs = null;

    static {

    }

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_view, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick
    public void onOneClick() {
        InputConnection inputConnection = getCurrentInputConnection();
        inputConnection.commitText("1", 1);
    }

}
