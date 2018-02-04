package com.justplaingoatappsgmail.motoroladroidkeyboardapp;

import android.inputmethodservice.InputMethodService;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputConnection;
import java.util.HashMap;
import java.util.Map;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MotorolaDroidMethodService extends InputMethodService {

    private static Map<Integer,Pair<Character,Character>> keyboardPairs = null;
    private boolean caps = false;
    private boolean alt = false;

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
        keyboardPairs.put(R.id.left_bracket_duo, new Pair<>('{', '['));
        keyboardPairs.put(R.id.at_squiggly, new Pair<>('@', '~'));
        keyboardPairs.put(R.id.slash_carrot, new Pair<>('/', '^'));
        keyboardPairs.put(R.id.right_bracket_duo, new Pair<>('}', ']'));
    }

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_view, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.q_1, R.id.w_2, R.id.e_3, R.id.r_4, R.id.t_5, R.id.y_6, R.id.u_7, R.id.i_8, R.id.o_9, R.id.p_0,
            R.id.a_pipe, R.id.s_exclamation, R.id.d_hashtag, R.id.f_dollar_sign, R.id.g_percentage, R.id.h_equal,
            R.id.j_and, R.id.k_astrick, R.id.l_left_paren, R.id.question_mark_right_paren, R.id.z_less_than, R.id.x_greater_than,
            R.id.c_underscore, R.id.v_dash, R.id.b_plus, R.id.n_quote, R.id.m_tick, R.id.comma_semi_colon, R.id.period_colon,
            R.id.left_bracket_duo, R.id.at_squiggly, R.id.slash_carrot, R.id.right_bracket_duo})
    public void onPairClick(View view) {
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection != null) {
            Pair<Character,Character> pair = keyboardPairs.get(view.getId());
            char c = alt ? pair.second : (caps ? Character.toUpperCase(pair.first) : pair.first);
            inputConnection.commitText(String.valueOf(c), 1);
        }
    }

}
