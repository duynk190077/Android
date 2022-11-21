package com.example.currecy2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChooseActivity extends AppCompatActivity {
    RadioGroup fromG, toG;
    RadioButton checkedFrom, checkedTo;
    String fromCur, toCur;
    Button chooseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        fromG = findViewById(R.id.fromG);
        toG = findViewById(R.id.toG);
        chooseBtn = findViewById(R.id.choose_btn);

        fromG.check(R.id.from_cny);
        toG.check(R.id.to_cny);

        checkedFrom = findViewById(R.id.from_cny);
        checkedTo = findViewById(R.id.to_cny);

        fromCur = checkedFrom.getText().toString();
        toCur = checkedTo.getText().toString();
        Intent backIntent = new Intent();

        fromG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkedFrom = findViewById(i);
                fromCur = checkedFrom.getText().toString();
            }
        });

        toG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkedTo = findViewById(i);
                toCur = checkedTo.getText().toString();
            }
        });

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backIntent.putExtra("from", fromCur);
                backIntent.putExtra("to", toCur);
                setResult(Activity.RESULT_OK, backIntent);
                finish();
            }
        });

    }
}