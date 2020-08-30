package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Callable;


public class MainActivity extends AppCompatActivity {

    private boolean isOpPressed = false;
    private double firstNumber = 0;
    private int secondNumberIndex = 0;
    private char currentOp = 0;
    private String screenContent;
    private boolean isDot = false;
    private TextView CalculatorScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);
        CalculatorScreen = findViewById(R.id.calculatorScreen);
        final Button n0 = findViewById(R.id.n0);
        final Button n1 = findViewById(R.id.n1);
        final Button n2 = findViewById(R.id.n2);
        final Button n3 = findViewById(R.id.n3);
        final Button n4 = findViewById(R.id.n4);
        final Button n5 = findViewById(R.id.n5);
        final Button n6 = findViewById(R.id.n6);
        final Button n7 = findViewById(R.id.n7);
        final Button n8 = findViewById(R.id.n8);
        final Button n9 = findViewById(R.id.n9);
        final Button dot = findViewById(R.id.dot);
        final Button equals = findViewById(R.id.equals);
        final Button addition = findViewById(R.id.addition);
        final Button subtraction = findViewById(R.id.subtraction);
        final Button multiplication = findViewById(R.id.multiplication);
        final Button division = findViewById(R.id.division);


        final View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = view.getId();
                switch (id) {
                    case R.id.n0:
                        CalculatorScreen.append("0");
                        break;
                    case R.id.n1:
                        CalculatorScreen.append("1");
                        break;
                    case R.id.n2:
                        CalculatorScreen.append("2");
                        break;
                    case R.id.n3:
                        CalculatorScreen.append("3");
                        break;
                    case R.id.n4:
                        CalculatorScreen.append("4");
                        break;
                    case R.id.n5:
                        CalculatorScreen.append("5");
                        break;
                    case R.id.n6:
                        CalculatorScreen.append("6");
                        break;
                    case R.id.n7:
                        CalculatorScreen.append("7");
                        break;
                    case R.id.n8:
                        CalculatorScreen.append("8");
                        break;
                    case R.id.n9:
                        CalculatorScreen.append("9");
                        break;
                    case R.id.dot:
                        if (isDot) {
                            String screenContent = CalculatorScreen.getText().toString();
                            final int screenContentLength = screenContent.length();
                            if(screenContentLength<1){
                                return;
                            }
                            char lastChar = screenContent.charAt(screenContentLength- 1);
                            if(lastChar=='+'||lastChar=='-'||lastChar=='*'||lastChar=='/'){
                                return;
                            }
                            CalculatorScreen.append(".");
                            isDot = true;
                        }
                        break;
                    case R.id.equals:
                        if (isOpPressed) {
                            String screenContent = CalculatorScreen.getText().toString();
                            char lastChar = screenContent.charAt(screenContent.length()- 1);
                            if(lastChar=='+'||lastChar=='-'||lastChar=='*'||lastChar=='/'){
                                return;
                            }
                            String secondNumberString = screenContent.substring(secondNumberIndex, screenContent.length());
                            double secondNumber = Double.parseDouble(secondNumberString);

                            if (currentOp == '+') {
                                secondNumber += firstNumber;
                            } else if (currentOp == '-') {
                                secondNumber = firstNumber - secondNumber;
                            } else if (currentOp == '*') {
                                secondNumber *= firstNumber;
                            } else if (currentOp == '/') {
                                if (secondNumber == 0) {
                                    return;
                                }
                                secondNumber = firstNumber / secondNumber;
                            }
                            String result = String.valueOf(secondNumber);
                            if (result.endsWith(".0")) {
                                result = result.substring(0, result.length() - 2);
                            }
                            CalculatorScreen.setText(result);
                            isOpPressed = false;
                        }
                        break;
                    case R.id.multiplication:
                        opPressed('*');
                        break;
                    case R.id.addition:
                        opPressed('+');
                        break;
                    case R.id.division:
                        opPressed('/');
                        break;
                    case R.id.subtraction:
                        opPressed('-');
                        break;
                }
            }
        };
        n0.setOnClickListener(calculatorListener);
        n1.setOnClickListener(calculatorListener);
        n2.setOnClickListener(calculatorListener);
        n3.setOnClickListener(calculatorListener);
        n4.setOnClickListener(calculatorListener);
        n5.setOnClickListener(calculatorListener);
        n6.setOnClickListener(calculatorListener);
        n7.setOnClickListener(calculatorListener);
        n8.setOnClickListener(calculatorListener);
        n9.setOnClickListener(calculatorListener);
        dot.setOnClickListener(calculatorListener);
        equals.setOnClickListener(calculatorListener);
        division.setOnClickListener(calculatorListener);
        multiplication.setOnClickListener(calculatorListener);
        addition.setOnClickListener(calculatorListener);
        subtraction.setOnClickListener(calculatorListener);

        final Button delete = findViewById(R.id.del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayedElements = CalculatorScreen.getText().toString();
                int lenght = displayedElements.length();
                if (lenght > 0) {
                    displayedElements = displayedElements.substring(0, lenght - 1);
                    CalculatorScreen.setText(displayedElements);
                }
            }
        });
        final Button clearEverything = findViewById(R.id.cleareverything);
        clearEverything.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalculatorScreen.setText("");
                isOpPressed = false;
                isDot = false;
            }
        });
    }
    private void opPressed(char operation){
        if(isOpPressed){
            return;
        }
        String screenContent = CalculatorScreen.getText().toString();
        final int screenContentLength = screenContent.length();
        if(screenContentLength<1){
            return;
        }
        secondNumberIndex = screenContent.length() + 1;
        firstNumber = Double.parseDouble(CalculatorScreen.getText().toString());
        CalculatorScreen.append(String.valueOf(operation));
        isOpPressed = true;
        currentOp = operation;
        isDot= false;
    }
}