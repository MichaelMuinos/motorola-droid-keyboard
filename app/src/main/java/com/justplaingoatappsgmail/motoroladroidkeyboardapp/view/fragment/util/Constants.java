package com.justplaingoatappsgmail.motoroladroidkeyboardapp.view.fragment.util;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.List;

public class Constants {

    public static final String INPUT_METHOD_STR = "com.justplaingoatappsgmail.motoroladroidkeyboardapp/.service.MotorolaDroidMethodService";

    public static boolean isKeyboardTurnedOn(Context context) {
        String packageLocal = context.getPackageName();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> list = inputMethodManager.getEnabledInputMethodList();
        // check if our keyboard is enabled as input method
        for (InputMethodInfo inputMethod : list) {
            String packageName = inputMethod.getPackageName();
            if (packageName.equals(packageLocal)) return true;
        }
        return false;
    }

    public static boolean isKeyboardChosen(Context context) {
        String currentKeyboard = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        return currentKeyboard.equalsIgnoreCase(INPUT_METHOD_STR);
    }

}
