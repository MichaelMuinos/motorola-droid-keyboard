package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment.util;

import android.content.Context;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Constants {

    public static boolean isKeyboardTurnedOn(Context context) {
        String packageLocal = context.getPackageName();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        List<InputMethodInfo> list = inputMethodManager.getEnabledInputMethodList();
        // check if our keyboard is enabled as input method
        for (InputMethodInfo inputMethod : list) {
            String packageName = inputMethod.getPackageName();
            if (packageName.equals(packageLocal)) return true;
        }
        return false;
    }

}
