package com.example.john.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.buttonDot, R.id.buttonC, R.id.buttonDeleteLastSymbol, R.id.buttonMultiplication, R.id.buttonDivision, R.id.buttonSubtraction, R.id.buttonEqual})
    List<Button> button;
    @Bind(R.id.editText1)
    EditText editText;

    public String sign = "";
    public String total = "";
    public Double mathDouble, mathDouble2;

    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    //Buttons 0-9
    @OnClick({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.buttonDot})
    void inputValue(Button button) {
        editText.append(button.getText());
    }


    @OnClick(R.id.buttonMultiplication)
    void Multiply() {
        mathDouble = Double.parseDouble(editText.getText().toString());
        editText.setText("");
        sign = "*";
    }

    @OnClick(R.id.buttonDivision)
    void Divide() {
        mathDouble = Double.parseDouble(editText.getText().toString());
        editText.setText("");
        sign = "/";
    }

    @OnClick(R.id.buttonSubtraction)
    void Subtract() {
        mathDouble = Double.parseDouble(editText.getText().toString());
        editText.setText("");
        sign = "-";
    }

    @OnClick(R.id.buttonPlus)
    void Addition() {
        mathDouble = Double.parseDouble(editText.getText().toString());
        editText.setText("");
        sign = "+";
    }

    //Buttons Equal, CE and C
    @OnClick(R.id.buttonC)
    void WriteLeftBracket() {
        editText.setText(String.valueOf(""));
    }

    @OnClick(R.id.buttonDeleteLastSymbol)
    void WriteRightBracket() {
        if (editText.getText().toString().length() > 0) {
            editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
            editText.setSelection(editText.getText().length());
        }
    }

    @OnClick(R.id.buttonEqual)
    void Equal() {
        mathDouble2 = Double.parseDouble(editText.getText().toString());

        if (sign == "+") {
            editText.setText(Double.toString(mathDouble + mathDouble2));
        } else if (sign == "-") {
            editText.setText(Double.toString(mathDouble - mathDouble2));
        } else if (sign == "*") {
            editText.setText(Double.toString(mathDouble * mathDouble2));
        } else if (sign == "/") {
            if (mathDouble2 == 0) {
                editText.setText(R.string.illegal_argument_exeption);
            } else {
                editText.setText(Double.toString(mathDouble / mathDouble2));
            }
        }
        sign = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }
}
