package com.example.david.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText bill;
    private SeekBar tipBar;
    private TextView tipProgress;
    private Button calculateButton;
    private TextView tipView;
    private TextView totalView;
    private float tipPercent;
    private float billFloat;
    private float resultTip;
    private float resultTotal;
    private TextView splitView;
    private SeekBar splitBar;
    private TextView splitProgress;
    private int splitNum;
    private Button newCalculateButton;
    private TextView newTipView;
    private TextView newTotalView;
    private Switch splitSwitch;
    private float splitResultTip;
    private float splitResultTotal;
    private TextView splitPeopleMin;
    private TextView splitPeopleMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Upper-half of screen
        bill = (EditText) findViewById(R.id.billEdit);
        tipBar = (SeekBar) findViewById(R.id.tipBar);
        tipProgress = (TextView) findViewById(R.id.tipProgress);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        tipView = (TextView) findViewById(R.id.tipView);
        totalView = (TextView) findViewById(R.id.totalView);

        // Bottom-half of screen (Invisible before Calculate button is clicked
        splitView = (TextView) findViewById(R.id.splitView);
        splitBar = (SeekBar) findViewById(R.id.splitBar);
        splitProgress = (TextView) findViewById(R.id.splitProgress);
        newCalculateButton = (Button) findViewById(R.id.newCalculateButton);
        newTipView = (TextView) findViewById(R.id.newTipView);
        newTotalView = (TextView) findViewById(R.id.newTotalView);
        splitSwitch = (Switch) findViewById(R.id.splitSwitch);
        splitPeopleMin = (TextView) findViewById(R.id.splitPeopleMin);
        splitPeopleMax = (TextView) findViewById(R.id.splitPeopleMax);


        // Seek Bar onClick
        tipBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipProgress.setText(String.valueOf((seekBar.getProgress())) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tipPercent = seekBar.getProgress();
                tipPercent = tipPercent/100;
                //Log.d("Tip", "onStopTrackingTouch: " + tipPercent);
            }
        }); // tipBar.onSeek()

        /* Calculate Button onClick:
            - Calculates tip and total
            - Makes bottom-half of screen visible
        */
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTip();
                calculateTotal();
                splitView.setVisibility(View.VISIBLE);
                splitBar.setVisibility(View.VISIBLE);
                splitProgress.setVisibility(View.VISIBLE);
                newCalculateButton.setVisibility(View.VISIBLE);
                newTipView.setVisibility(View.VISIBLE);
                splitSwitch.setVisibility(View.VISIBLE);
                splitPeopleMin.setVisibility(View.VISIBLE);
                splitPeopleMax.setVisibility(View.VISIBLE);
            }
        }); // calculateButton.onClick()

        // Split Tip/Total Button onClick
        newCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(splitSwitch.isChecked()){
                    newTotalView.setVisibility(View.VISIBLE);
                    splitTotal();
                    splitTip();
                } else {
                    splitTip();
                    newTotalView.setVisibility(View.INVISIBLE);
                }
            }
        }); // newCalculateButton onClick()

        // Split Tip/Total Seek Bar onSeek
        splitBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               splitProgress.setText(String.valueOf((seekBar.getProgress())));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                splitNum = seekBar.getProgress();
            }
        }); // splitBar.onSeek()

    } // onCreate()


    // Methods

    public void splitTip() {
        splitResultTip = resultTip/splitNum;
        newTipView.setText(String.format("Tip Per Person: $%.2f", splitResultTip));
    }

    public void splitTotal() {
        splitResultTotal = resultTotal/splitNum;
        newTotalView.setText(String.format("Total Per Person: $%.2f", splitResultTotal));
    }

    public void calculateTip() {

        // Checks if bill amount is entered
        if (!bill.getText().toString().equals("")) {
            billFloat = Float.parseFloat(bill.getText().toString());
            resultTip = billFloat*tipPercent;

            // Final Result Message
            tipView.setText(String.format("Tip: $%.2f", resultTip));

        } else {
            Toast.makeText(MainActivity.this, "Please enter a bill amount.",
                    Toast.LENGTH_LONG).show();
        }
    } // calculate()

    public void calculateTotal() {

        // Checks if bill amount is entered
        if (!bill.getText().toString().equals("")) {
            billFloat = Float.parseFloat(bill.getText().toString());
            resultTotal = billFloat+resultTip;

            // Final Result Message
            totalView.setText(String.format("New Total: $%.2f", resultTotal));

        } else {
            Toast.makeText(MainActivity.this, "Please enter a bill amount.",
                    Toast.LENGTH_LONG).show();
        }
    } // calculateTotal()

} // MainActivity
