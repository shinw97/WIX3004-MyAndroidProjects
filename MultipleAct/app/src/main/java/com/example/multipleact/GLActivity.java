package com.example.multipleact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GLActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl);
        int[] buttonID = {R.id.EvalBtn, R.id.DotBtn, R.id.ClearBtn, R.id.BackBtn, R.id.PlusBtn, R.id.MinusBtn, R.id.MulBtn, R.id.DivBtn, R.id.CalcButton0, R.id.CalcButton1, R.id.CalcButton2, R.id.CalcButton3, R.id.CalcButton4, R.id.CalcButton5, R.id.CalcButton6, R.id.CalcButton7, R.id.CalcButton8, R.id.CalcButton9};
        String notes = "=.C<+-*/0123456789";
        final HashMap<Integer, Character> hm = new HashMap<Integer, Character>();

        for (int i = 0; i < buttonID.length; i++) {
            hm.put(buttonID[i], notes.charAt(i));
        }

        final TextView displayPanel = findViewById(R.id.TVDisplay);
        final TextView answerPanel = findViewById(R.id.TVAnswer);

        for (final Map.Entry<Integer, Character> entry : hm.entrySet()) {
            int id = entry.getKey();
            ((Button) findViewById(id)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = displayPanel.getText().toString();
                    switch (entry.getValue()) {
                        case 'C':
                            displayPanel.setText("0");
                            answerPanel.setText("...");
                            break;
                        case '<':
                            if (text.length() == 1) {
                                if (text.charAt(0) == '0') {
                                    break;
                                } else {
                                    displayPanel.setText("0");
                                    answerPanel.setText("...");
                                    break;
                                }
                            }
                            if (text.charAt(text.length() - 1) == ' ') {
                                displayPanel.setText(text.substring(0, displayPanel.getText().length() - 3));
                                break;
                            }
                            displayPanel.setText(text.substring(0, displayPanel.getText().length() - 1));
                            break;
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                            String newText = text + " " + entry.getValue() + " ";
                            displayPanel.setText(newText);
                            break;
                        case '0':
                            if (text.length() == 1 && text.charAt(0) == '0') {
                                break;
                            }
                            displayPanel.setText(text + entry.getValue().toString());
                            break;
                        case '=':
                            String[] elems = text.split(" ");
                            int countNum = 0;
                            int countOp = 0;
                            for (int i = 0; i < elems.length; i++) {
                                if(elems[i].isEmpty()){
                                    continue;
                                }
                                if (elems[i].charAt(0) == '+' || elems[i].charAt(0) == '-' || elems[i].charAt(0) == '*' || elems[i].charAt(0) == '/') {
                                    countOp += 1;
                                } else {
                                    countNum += 1;
                                }
                            }
                            if (countNum - 1 != countOp || countOp >= countNum) {
                                Toast.makeText(getApplicationContext(), "Invalid expression.", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            String answer = calculate(elems);
                            answerPanel.setText(answer);
                            break;
                        default:
                            if (text.length() == 1 && text.charAt(0) == '0') {
                                displayPanel.setText(entry.getValue().toString());
                            } else {
                                displayPanel.setText(text + entry.getValue().toString());
                            }

                    }
                }
            });
        }


    }

    public String calculate(String[] elems) {
        double answer = Double.parseDouble(elems[0]);
        Stack<Character> stackOp = new Stack<>();
        Stack<Double> stackVal = new Stack<>();

        for (int i = elems.length - 1; i >= 0; i--) {
            switch (elems[i]) {
                case "/":
                case "*":
                case "+":
                case "-":
                    stackOp.push(elems[i].charAt(0));
                    break;
                default:
                    stackVal.push(Double.parseDouble(elems[i]));
                    break;
            }
        }
        try {


            while (!stackOp.isEmpty()) {
                char op = stackOp.pop();
                double n1 = stackVal.pop();
                double n2 = stackVal.pop();

                switch (op) {
                    case '/':
                        stackVal.push(n1 / n2);
                        break;
                    case '*':
                        stackVal.push(n1 * n2);
                        break;
                    case '+':
                        if (stackOp.isEmpty()) {
                            stackVal.push(n1 + n2);
                            break;
                        }
                        if (stackOp.peek() != '/' && stackOp.peek() != '*') {
                            stackVal.push(n1 + n2);
                        } else {
                            double n3 = stackVal.pop();
                            if (stackOp.pop() == '/') {
                                stackVal.push(n2 / n3);
                                stackVal.push(n1);
                                stackOp.push(op);
                            } else {
                                stackVal.push(n2 * n3);
                                stackVal.push(n1);
                                stackOp.push(op);
                            }
                        }
                        break;
                    case '-':
                        if (stackOp.isEmpty()) {
                            stackVal.push(n1 - n2);
                            break;
                        }
                        if (stackOp.peek() != '/' && stackOp.peek() != '*') {
                            stackVal.push(n1 - n2);
                        } else {
                            double n3 = stackVal.pop();
                            if (stackOp.pop() == '/') {
                                stackVal.push(n2 / n3);
                                stackVal.push(n1);
                                stackOp.push(op);
                            } else {
                                stackVal.push(n2 * n3);
                                stackVal.push(n1);
                                stackOp.push(op);
                            }
                        }
                        break;
                }
                if (stackVal.size() == 1) {
                    answer = stackVal.peek();
                    break;
                }
            }
        } catch (Exception e) {
            return "Division by zero!";
        }
        return Double.toString(answer);
    }

    public void onClickHomeButton(View view) {
        finish();
    }
}
