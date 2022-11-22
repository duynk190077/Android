package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String preOperand, curOperand, curText, operator, calcText, preChar;
    TextView calc, result;
    Button button_ce, button_c, button_bs, button_div, button_multi,
            button_add, button_sub, button_negate, button_dot, button_result,
            button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7,
            button_8, button_9;

    private void build() {
        preChar = " ";
        preOperand = curOperand = operator = calcText = "";
        calc = findViewById(R.id.text_calculation);
        result = findViewById(R.id.text_result);
        curText = "0";
        button_ce = findViewById(R.id.button_ce); button_ce.setOnClickListener(this);
        button_c = findViewById(R.id.button_c); button_c.setOnClickListener(this);
        button_bs = findViewById(R.id.button_bs); button_bs.setOnClickListener(this);
        button_div = findViewById(R.id.button_div); button_div.setOnClickListener(this);
        button_multi = findViewById(R.id.button_multi); button_multi.setOnClickListener(this);
        button_add = findViewById(R.id.button_add); button_add.setOnClickListener(this);
        button_sub = findViewById(R.id.button_sub); button_sub.setOnClickListener(this);
        button_negate = findViewById(R.id.button_negate); button_negate.setOnClickListener(this);
        button_dot = findViewById(R.id.button_dot); button_dot.setOnClickListener(this);
        button_result = findViewById(R.id.button_result); button_result.setOnClickListener(this);
        button_0 = findViewById(R.id.button_0); button_0.setOnClickListener(this);
        button_1 = findViewById(R.id.button_1); button_1.setOnClickListener(this);
        button_2 = findViewById(R.id.button_2); button_2.setOnClickListener(this);
        button_3 = findViewById(R.id.button_3); button_3.setOnClickListener(this);
        button_4 = findViewById(R.id.button_4); button_4.setOnClickListener(this);
        button_5 = findViewById(R.id.button_5); button_5.setOnClickListener(this);
        button_6 = findViewById(R.id.button_6); button_6.setOnClickListener(this);
        button_7 = findViewById(R.id.button_7); button_7.setOnClickListener(this);
        button_8 = findViewById(R.id.button_8); button_8.setOnClickListener(this);
        button_9 = findViewById(R.id.button_9); button_9.setOnClickListener(this);
    }

    private void formatText() {
        while (curText.length() > 1 && curText.charAt(0) == '0' && curText.charAt(1) != '.') {

            curText = curText.substring(1);
        }

    }

    private void removeLast() {
        curText = curText.substring(0, curText.length() - 1);
        if (curText.length() == 0) curText = "0";
    }

    private void negate() {
        double curVal = Double.parseDouble(curText);
        if (curVal != 0)
        if (curText.charAt(0) == '-')
            curText = curText.substring(1);
        else curText = '-' + curText;
    }

    private void updateResult() {
        result.setText(curText);
    }

    private void updateCalc() {
        calc.setText(calcText);
    }

    private void calculation() {
        try {
            double curVal = Double.parseDouble(curOperand);
            double preVal = Double.parseDouble(preOperand);
            double result = 0;
            switch (operator) {
                case "+": {
                    result = preVal + curVal;
                    break;
                }
                case "-": {
                    result = preVal - curVal;
                    break;
                }
                case "x": {
                    result = preVal * curVal;
                    break;
                }
                case "/": {
                    result = preVal / curVal;
                    break;
                }
                default: {
                    result = curVal;
                    break;
                }
            }
            curText = Double.toString(result);
            curOperand = "";
            operator = "";
        } catch (Exception e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        build();
    }

    @Override
    public void onClick(View view)
    {
      switch (view.getId()) {
          case R.id.button_ce: {
              curText = "0";
              break;
          }
          case R.id.button_c: {
              curText = "0";
              preOperand = curOperand = operator = calcText = "";
              break;
          }
          case R.id.button_bs: {
              removeLast();
              break;
          }
          case R.id.button_negate: {
              negate();
              calcText = "";
              break;
          }
          case R.id.button_dot: {
              if (preChar.charAt(0) == '=') {
                  curText = "0.";
                  calcText = preOperand = curOperand = operator = "";
              }
              else
              if (curText.indexOf(".") == -1) curText += '.';
              break;
          }
          case R.id.button_add: {
              preOperand = curText;
              operator =((Button)findViewById(R.id.button_add)).getText().toString();
              calcText = preOperand + " " + operator + " ";
              break;
          }
          case R.id.button_sub: {
              preOperand = curText;
              operator =((Button)findViewById(R.id.button_sub)).getText().toString();
              calcText = preOperand + " " + operator + " ";
              break;
          }
          case R.id.button_multi: {
              Log.v("MULTI", "onClick: ");
              preOperand = curText;
              operator =((Button)findViewById(R.id.button_multi)).getText().toString();
              calcText = preOperand + " " + operator + " ";
              break;
          }
          case R.id.button_div: {
              preOperand = curText;
              operator =((Button)findViewById(R.id.button_div)).getText().toString();
              calcText = preOperand + " " + operator + " ";
              break;
          }
          case R.id.button_result: {
              curOperand = curText;
              if (operator != "") calcText = calcText + curOperand + " " + "= ";
              else calcText = curOperand + " " + "=";
              calculation();
              break;
          }
          default: {
              Log.v("NUMBER", ((Button)findViewById(view.getId())).getText().toString());
              if (preChar.charAt(0) >= '0' && preChar.charAt(0) <= '9')
                curText = curText + ((Button)findViewById(view.getId())).getText().toString();
              else curText = ((Button)findViewById(view.getId())).getText().toString();
              if (preChar.charAt(0) == '=') {
                  calcText = preOperand = curOperand = operator = "";
              }
              break;
          }
      }
      preChar = ((Button)findViewById(view.getId())).getText().toString();
      formatText();
      updateCalc();
      updateResult();
    }

}