package com.example.john.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static String NUMBER_TYPE = "NUM";
    private static String SIGN_TYPE = "SIGN";
    private static String OPEN_BRACKET_TYPE = "OPEN";
    private static String CLOSED_BRACKET_TYPE = "CLOSED";
    private static String TEXT_VIEW_KEY = "text_view_key";

    public String sign = "";
    MathEval ans = new MathEval();
    int openBracketsCount = 0;
    String lastSymbolType = null;

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT_VIEW_KEY, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Bind({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button_dot, R.id.button_c,
            R.id.button_delete_last_symbol, R.id.button_multiplication, R.id.button_division,
            R.id.button_subtraction, R.id.button_equal, R.id.button_open_bracket,
            R.id.button_close_bracket, R.id.button_sqr})
    List<Button> button;
    @Bind(R.id.input_text_view)
    TextView textView;

    //Buttons 0-9
    @OnClick({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button_dot})
    void inputValue(Button button) {
        checkIfContainsEqual();
        if (lastSymbolType != null && !lastSymbolType.isEmpty() &&
                lastSymbolType.equals(CLOSED_BRACKET_TYPE)) {
            textView.append(getString(R.string.multiplication) + button.getText());
            lastSymbolType = NUMBER_TYPE;
        } else {
            textView.append(button.getText());
            lastSymbolType = NUMBER_TYPE;
        }
    }

    //Buttons plus, subtraction, division, multiplication
    @OnClick({R.id.button_plus, R.id.button_subtraction, R.id.button_division,
            R.id.button_multiplication})
    void inputSign(Button button) {
        checkIfContainsEqual();
        if(!textView.getText().toString().isEmpty()) {
            sign = button.getText().toString();
            textView.append(sign);
            lastSymbolType = SIGN_TYPE;
        }else{
            Toast.makeText(this, "Expression cannot begin with math sign!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Buttons open bracket|close bracket
    @OnClick({R.id.button_open_bracket, R.id.button_close_bracket})
    void inputBrackets(Button button) {
        checkIfContainsEqual();
        if (button.getText().toString().equals(getString(R.string.open_bracket))) {
            if (lastSymbolType != null && lastSymbolType.equals(SIGN_TYPE)) {
                textView.append(button.getText());
                lastSymbolType = OPEN_BRACKET_TYPE;
                openBracketsCount++;
            } else if (lastSymbolType != null && lastSymbolType.equals(NUMBER_TYPE)) {
                textView.append(getString(R.string.multiplication) + button.getText());
                lastSymbolType = OPEN_BRACKET_TYPE;
                openBracketsCount++;
            } else {
                textView.append(button.getText());
                lastSymbolType = OPEN_BRACKET_TYPE;
                openBracketsCount++;
            }
        } else {
            if (!textView.getText().toString().isEmpty()) {
                textView.append(button.getText());
                openBracketsCount--;
                lastSymbolType = CLOSED_BRACKET_TYPE;
            }else{
                Toast.makeText(this, "Expression cannot begin with closed brackets!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Button sqr
    @OnClick(R.id.button_sqr)
    void sqrButton(Button button){
        checkIfContainsEqual();
        if(lastSymbolType != null) {
            switch (lastSymbolType) {
                case "SIGN":
                    textView.append(button.getText());
                    lastSymbolType = SIGN_TYPE;
                    break;
                case "NUM":
                case "CLOSED":
                    textView.append(getString(R.string.multiplication) + button.getText());
                    lastSymbolType = SIGN_TYPE;
                    break;
                default:
                    textView.append(button.getText());
                    lastSymbolType = SIGN_TYPE;
                    break;
            }
        }else{
            textView.append(button.getText());
            lastSymbolType = SIGN_TYPE;
        }
    }


    //Buttons C
    @OnClick(R.id.button_c)
    void deleteText() {
        textView.setText(String.valueOf(""));
        lastSymbolType = null;
    }

    //Buttons ←
    @OnClick(R.id.button_delete_last_symbol)
    void deleteLastSymbol() {
        if (textView.getText().toString().length() > 0) {
            textView.setText(textView.getText().toString().substring(0,
                    textView.getText().toString().length() - 1));
            if(textView.getText().toString().isEmpty()){
                lastSymbolType = null;
            }
        }
    }

    //Buttons Equal
    @OnClick(R.id.button_equal)
    public void Equal(Button button) {
        lastSymbolType = null;
        String text = textView.getText().toString();
        if (openBracketsCount > 0) {
            textView.append(getString(R.string.closed_bracket) + button.getText().toString());
        } else {
            textView.append(button.getText().toString());
        }
        DecimalFormat df = new DecimalFormat("#.###");
        String formatDouble = df.format(ans.evaluate(text));
        textView.append(formatDouble);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void checkIfContainsEqual() {
        if (textView.getText().toString().contains(getString(R.string.equal))) {
            textView.setText("");
        }
    }
}