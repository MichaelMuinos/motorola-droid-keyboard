package com.justplaingoatappsgmail.motoroladroidkeyboardapp;

import android.inputmethodservice.InputMethodService;
import android.view.View;

public class MotorolaDroidMethodService extends InputMethodService {

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_view, null);
        return view;
    }

}
