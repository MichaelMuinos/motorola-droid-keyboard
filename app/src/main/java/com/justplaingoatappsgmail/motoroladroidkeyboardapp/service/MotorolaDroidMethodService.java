package com.justplaingoatappsgmail.motoroladroidkeyboardapp.service;

import android.inputmethodservice.InputMethodService;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ImageView;
import android.widget.TextView;

import com.justplaingoatappsgmail.motoroladroidkeyboardapp.R;
import com.justplaingoatappsgmail.motoroladroidkeyboardapp.service.util.Selection;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MotorolaDroidMethodService extends InputMethodService {

    @BindView(R.id.alt_left) TextView altLeft;
    @BindView(R.id.alt_right) TextView altRight;
    @BindView(R.id.cap_left) ImageView capsLeft;
    @BindView(R.id.cap_right) ImageView capsRight;
    @BindView(R.id.search) ImageView search;

    private int WHITE = 0;
    private int LIGHT_BLACK = 0;
    private int GRAY = 0;
    private int LIGHT_ORANGE = 0;
    private int CYAN = 0;

    private boolean deleteDown = false;
    private boolean spaceDown = false;

    private Thread deleteThread;
    private Thread spaceThread;

    private static Map<Integer,Pair<Character,Character>> keyboardPairs = null;
    private Selection caps = Selection.OFF;
    private Selection alt = Selection.OFF;

    static {
        keyboardPairs = new HashMap<>();
        keyboardPairs.put(R.id.q_1, new Pair<>('q', '1'));
        keyboardPairs.put(R.id.w_2, new Pair<>('w', '2'));
        keyboardPairs.put(R.id.e_3, new Pair<>('e', '3'));
        keyboardPairs.put(R.id.r_4, new Pair<>('r', '4'));
        keyboardPairs.put(R.id.t_5, new Pair<>('t', '5'));
        keyboardPairs.put(R.id.y_6, new Pair<>('y', '6'));
        keyboardPairs.put(R.id.u_7, new Pair<>('u', '7'));
        keyboardPairs.put(R.id.i_8, new Pair<>('i', '8'));
        keyboardPairs.put(R.id.o_9, new Pair<>('o', '9'));
        keyboardPairs.put(R.id.p_0, new Pair<>('p', '0'));
        keyboardPairs.put(R.id.a_pipe, new Pair<>('a', '|'));
        keyboardPairs.put(R.id.s_exclamation, new Pair<>('s', '!'));
        keyboardPairs.put(R.id.d_hashtag, new Pair<>('d', '#'));
        keyboardPairs.put(R.id.f_dollar_sign, new Pair<>('f', '$'));
        keyboardPairs.put(R.id.g_percentage, new Pair<>('g', '%'));
        keyboardPairs.put(R.id.h_equal, new Pair<>('h', '='));
        keyboardPairs.put(R.id.j_and, new Pair<>('j', '&'));
        keyboardPairs.put(R.id.k_astrick, new Pair<>('k', '*'));
        keyboardPairs.put(R.id.l_left_paren, new Pair<>('l', '('));
        keyboardPairs.put(R.id.question_mark_right_paren, new Pair<>('?', ')'));
        keyboardPairs.put(R.id.z_less_than, new Pair<>('z', '<'));
        keyboardPairs.put(R.id.x_greater_than, new Pair<>('x', '>'));
        keyboardPairs.put(R.id.c_underscore, new Pair<>('c', '_'));
        keyboardPairs.put(R.id.v_dash, new Pair<>('v', '-'));
        keyboardPairs.put(R.id.b_plus, new Pair<>('b', '+'));
        keyboardPairs.put(R.id.n_quote, new Pair<>('n', '"'));
        keyboardPairs.put(R.id.m_tick, new Pair<>('m', '\''));
        keyboardPairs.put(R.id.comma_semi_colon, new Pair<>(',', ';'));
        keyboardPairs.put(R.id.period_colon, new Pair<>('.', ':'));
        keyboardPairs.put(R.id.at_squiggly, new Pair<>('@', '~'));
        keyboardPairs.put(R.id.slash_carrot, new Pair<>('/', '^'));
        keyboardPairs.put(R.id.brackets, new Pair<>('[', ']'));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WHITE = ContextCompat.getColor(this, R.color.whiteColor);
        LIGHT_BLACK = ContextCompat.getColor(this, R.color.colorPrimary);
        GRAY = ContextCompat.getColor(this, R.color.colorPrimaryLight);
        LIGHT_ORANGE = ContextCompat.getColor(this, R.color.lightOrangeColor);
        CYAN = ContextCompat.getColor(this, R.color.colorAccent);
    }

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_view, null);
        ButterKnife.bind(this, view);
        search.setImageResource(android.R.drawable.ic_menu_search);
        capsLeft.setImageResource(android.R.drawable.ic_menu_upload);
        capsRight.setImageResource(android.R.drawable.ic_menu_upload);
        return view;
    }

    @OnClick(R.id.search)
    public void onSearchClick() {
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection != null) {
            final int options = this.getCurrentInputEditorInfo().imeOptions;
            final int actionId = options & EditorInfo.IME_MASK_ACTION;
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    sendDefaultEditorAction(true);
                    break;
                case EditorInfo.IME_ACTION_GO:
                    sendDefaultEditorAction(true);
                    break;
                case EditorInfo.IME_ACTION_SEND:
                    sendDefaultEditorAction(true);
                    break;
                default:
                    inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
            }
        }
    }

    @OnClick({R.id.alt_left, R.id.alt_right})
    public void onAltClick() {
        // change our alt button to the appropriate setting
        alt = alt == Selection.OFF ? Selection.ONCE : (alt == Selection.ONCE ? Selection.FOREVER : Selection.OFF);
        if(alt == Selection.OFF) turnSelectionOffOrForeverHelper(altLeft, altRight, LIGHT_ORANGE, LIGHT_BLACK);
        else if(alt == Selection.ONCE) turnSelectionOnceHelper(altLeft, altRight, CYAN);
        else turnSelectionOffOrForeverHelper(altLeft, altRight, LIGHT_ORANGE, CYAN);
    }

    @OnClick({R.id.cap_left, R.id.cap_right})
    public void onCapsClick() {
        // change our caps button to the appropriate setting
        caps = caps == Selection.OFF ? Selection.ONCE : (caps == Selection.ONCE ? Selection.FOREVER : Selection.OFF);
        if(caps == Selection.OFF) turnSelectionOffOrForeverHelper(capsLeft, capsRight, WHITE, LIGHT_BLACK);
        else if(caps == Selection.ONCE) turnSelectionOnceHelper(capsLeft, capsRight, CYAN);
        else turnSelectionOffOrForeverHelper(capsLeft, capsRight, WHITE, CYAN);
    }

    @OnTouch(R.id.enter)
    public boolean onEnterTouch(View view, MotionEvent event) {
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection != null) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                view.setBackgroundColor(GRAY);
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                inputConnection.commitText("\n", 1);
                view.setBackgroundColor(LIGHT_BLACK);
            }
            return true;
        }
        return false;
    }

    @OnTouch(R.id.space)
    public boolean onSpaceTouch(View view, MotionEvent event) {
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection != null) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                view.setBackgroundColor(GRAY);
                spaceDown = true;
                spaceThread = new Thread(() -> {
                    inputConnection.commitText(" ", 1);
                    sleepThread(spaceThread, 900);
                    while(spaceDown) {
                        inputConnection.commitText(" ", 1);
                        sleepThread(spaceThread, 90);
                    }
                });
                spaceThread.start();
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                view.setBackgroundColor(LIGHT_BLACK);
                spaceDown = false;
                if(spaceThread.isAlive()) spaceThread.interrupt();
            }
            return true;
        }
        return false;
    }

    @OnTouch(R.id.del)
    public boolean onDeleteTouch(View view, MotionEvent event) {
        final InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection != null) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                view.setBackgroundColor(GRAY);
                deleteDown = true;
                deleteThread = new Thread(() -> {
                    deleteLastCharacter(inputConnection);
                    sleepThread(deleteThread, 900);
                    while(deleteDown) {
                        deleteLastCharacter(inputConnection);
                        sleepThread(deleteThread, 90);
                    }
                });
                deleteThread.start();
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                view.setBackgroundColor(LIGHT_BLACK);
                deleteDown = false;
                if(deleteThread.isAlive()) deleteThread.interrupt();
            }
            return true;
        }
        return false;
    }

    @OnTouch({R.id.q_1, R.id.w_2, R.id.e_3, R.id.r_4, R.id.t_5, R.id.y_6, R.id.u_7, R.id.i_8, R.id.o_9, R.id.p_0,
            R.id.a_pipe, R.id.s_exclamation, R.id.d_hashtag, R.id.f_dollar_sign, R.id.g_percentage, R.id.h_equal,
            R.id.j_and, R.id.k_astrick, R.id.l_left_paren, R.id.question_mark_right_paren, R.id.z_less_than, R.id.x_greater_than,
            R.id.c_underscore, R.id.v_dash, R.id.b_plus, R.id.n_quote, R.id.m_tick, R.id.comma_semi_colon, R.id.period_colon,
            R.id.at_squiggly, R.id.slash_carrot, R.id.brackets})
    public boolean onPairTouch(View view, MotionEvent event) {
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection != null) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                view.setBackgroundColor(GRAY);
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                Pair<Character, Character> pair = keyboardPairs.get(view.getId());
                // find out what character needs to be displayed
                char c;
                if (alt == Selection.OFF) c = caps == Selection.OFF ? pair.first : Character.toUpperCase(pair.first);
                else c = pair.second;
                // display character
                inputConnection.commitText(String.valueOf(c), 1);
                // change background color to normal
                view.setBackgroundColor(LIGHT_BLACK);
                // reset alt and caps if needed
                if (alt == Selection.ONCE) {
                    alt = Selection.OFF;
                    turnSelectionOffOrForeverHelper(altLeft, altRight, LIGHT_ORANGE, LIGHT_BLACK);
                }
                if (caps == Selection.ONCE && Character.isLetter(c)) {
                    caps = Selection.OFF;
                    turnSelectionOffOrForeverHelper(capsLeft, capsRight, WHITE, LIGHT_BLACK);
                }
            }
            return true;
        }
        return false;
    }

    private void sleepThread(Thread thread, int milliseconds) {
        if(!thread.isInterrupted()) {
            try {
                thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteLastCharacter(InputConnection inputConnection) {
        CharSequence selectedText = inputConnection.getSelectedText(0);
        if (TextUtils.isEmpty(selectedText)) inputConnection.deleteSurroundingText(1, 0);
        else inputConnection.commitText("", 1);
    }

    private void turnSelectionOffOrForeverHelper(TextView left, TextView right, int textColor, int backgroundColor) {
        turnSelectionOnceHelper(left, right, textColor);
        left.setBackgroundColor(backgroundColor);
        right.setBackgroundColor(backgroundColor);
    }

    private void turnSelectionOffOrForeverHelper(ImageView left, ImageView right, int imageColor, int backgroundColor) {
        turnSelectionOnceHelper(left, right, imageColor);
        left.setBackgroundColor(backgroundColor);
        right.setBackgroundColor(backgroundColor);
    }

    private void turnSelectionOnceHelper(TextView left, TextView right, int textColor) {
        left.setTextColor(textColor);
        right.setTextColor(textColor);
    }

    private void turnSelectionOnceHelper(ImageView left, ImageView right, int imageColor) {
        left.setColorFilter(imageColor);
        right.setColorFilter(imageColor);
    }

}
