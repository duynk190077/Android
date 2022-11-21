package com.example.currecy2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText fromCur, toCur;
    TextView fromT, toT;
    Map<String, Double> currency = new HashMap<String, Double>();
    String fromText, toText;
    private void buildCurrency() {
        currency.put("CNYCNY", 1.00);
        currency.put("CNYEUR", 0.14);
        currency.put("CNYJPY", 19.80);
        currency.put("CNYKRW", 189.99);
        currency.put("CNYUSD", 0.14);
        currency.put("CNYVND", 3466.62);
        currency.put("EUREUR", 1.00);
        currency.put("EURCNY", 7.34);
        currency.put("EURJPY", 145.33);
        currency.put("EURKRW", 1394.60);
        currency.put("EURUSD", 1.02);
        currency.put("EURVND", 25467.90);
        currency.put("JPYJPY", 1.00);
        currency.put("JPYCNY", 0.051);
        currency.put("JPYEUR", 0.0069);
        currency.put("JPYKRW", 9.60);
        currency.put("JPYUSD", 0.0071);
        currency.put("JPYVND", 168.7);
        currency.put("KRWKRW", 1.00);
        currency.put("KRWCNY", 0.0053);
        currency.put("KRWEUR",0.00072);
        currency.put("KRWJPY", 0.1);
        currency.put("KRWUSD", 0.00073);
        currency.put("KRWVND", 18.3321);
        currency.put("USDUSD", 1.00);
        currency.put("USDCNY", 7.17);
        currency.put("USDEUR", 0.98);
        currency.put("USDJPY", 141.76);
        currency.put("USDKRW", 1361.09);
        currency.put("USDVND", 24820.00);
        currency.put("VNDVND", 1.00);
        currency.put("VNDCNY", 0.00029);
        currency.put("VNDEUR", 0.000039);
        currency.put("VNDJPY", 0.0057);
        currency.put("VNDKRW", 0.055);
        currency.put("VNDUSD", 0.000040);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildCurrency();
        fromCur = findViewById(R.id.fromCur);
        toCur = findViewById(R.id.toCur);
        fromT = findViewById(R.id.fromText);
        toT = findViewById(R.id.toText);
        fromText = "CNY";
        toText = "CNY";
        fromT.setText("From " + fromText);
        toT.setText("To " + toText);

        Log.v("FROM", fromCur.getText().toString());


        findViewById(R.id.convert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchToChooseActivity = new Intent(MainActivity.this, ChooseActivity.class);
                startActivityForResult(switchToChooseActivity, 101);
            }
        });

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromCur.setText("0");
                toCur.setText("0");
            }
        });

        fromCur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (charSequence.toString() != "") {
                        double fromNumber = Double.parseDouble(charSequence.toString());
                        double toNumber = fromNumber * currency.get(fromText + toText);
                        toCur.setText(String.valueOf(toNumber));
                    } else {
                        fromCur.setText("0");
                        toCur.setText("0");
                    }
                } catch (Exception e) {
                    fromCur.setText("0");
                    toCur.setText("0");
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if ((requestCode == 101) && (resultCode == Activity.RESULT_OK)) {
                Bundle chooseData = data.getExtras();
                fromText = chooseData.getString("from");
                toText = chooseData.getString("to");
                fromT.setText("From " + fromText);
                toT.setText("To " + toText);
            }
        } catch (Exception e) {

        }
    }
}