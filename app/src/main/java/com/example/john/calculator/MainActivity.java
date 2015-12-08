package com.example.john.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button_dot, R.id.button_c, R.id.button_delete_last_symbol, R.id.button_multiplication, R.id.button_division, R.id.button_subtraction, R.id.button_equal})
    List<Button> button;
    @Bind(R.id.editText1)
    EditText editText;

    public String sign = "";

    //Buttons 0-9
    @OnClick({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button_dot})
    void inputValue(Button button) {
        editText.append(button.getText());
    }

    //Buttons plus, substraction, division, multiplication
    @OnClick({R.id.button_plus, R.id.button_subtraction, R.id.button_division, R.id.button_multiplication})
    void inputSign(Button button) {
        sign = button.getText().toString();
        editText.append(sign);
    }

    //Buttons C
    @OnClick(R.id.button_c)
    void deleteText() {
        editText.setText(String.valueOf(""));
    }

    //Buttons ←
    @OnClick(R.id.button_delete_last_symbol)
    void deleteLastSymbol() {
        if (editText.getText().toString().length() > 0) {
            editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
            editText.setSelection(editText.getText().length());
        }
    }

    //Buttons Equal
    @OnClick(R.id.button_equal)
    public void Equal(Button button) {
        editText.append(button.getText());
        if (sign.equals("+")) {
            editText.append(Double.toString(firstValue() + secondValue()));
        } else if (sign.equals("-")) {
            editText.append(Double.toString(firstValue() - secondValue()));
        } else if (sign.equals("*")) {
            editText.append(Double.toString(firstValue() * secondValue()));
        } else if (sign.equals("/")) {
            if (secondValue() == 0) {
                editText.setText(R.string.illegal_argument_exeption);
            } else {
                editText.append(Double.toString(firstValue() / secondValue()));
            }
        }
    }

    public Double firstValue() {
        String writtenText = editText.getText().toString();
        String firstValue = writtenText.substring(0, writtenText.indexOf(sign));

        return Double.parseDouble(firstValue);
    }

    public Double secondValue() {
        String writtenText = editText.getText().toString();
        String secondValue = writtenText.substring(writtenText.indexOf(sign) + 1, writtenText.indexOf("="));
        return Double.parseDouble(secondValue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
}
