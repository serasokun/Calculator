package com.example.calculator2;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.calculator2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    EditText display;
    Button number0,number1, number2, number3, number4,number5,number6, number7,number8,number9;
    Button plus, minus, divide, multiply,equal, delete, dot, clearAll;
    private void appendNumber(String num){
        String currentText= display.getText().toString();
        String[] parts= currentText.split("[+\\-x÷]");
        String lastNumber= parts[parts.length-1];
        if (lastNumber.length()>=12){
            return;
        }
        if(currentText.equals("0")){
            display.setText(num);
        }else{
            display.append(num);
        }
    }
    private void appendOperator(String op){
        String currentText=display.getText().toString();
        if(currentText.isEmpty()) {
            return;
        }
        char lastChar= currentText.charAt(currentText.length()-1);
        if(lastChar=='+'||lastChar=='-'||lastChar=='x'||lastChar=='÷'){
            return;
        }
        display.append(op);
    }
    private void appendDot(){
        String currentText= display.getText().toString();
        if(currentText.isEmpty()||currentText.endsWith("+")||currentText.endsWith("-")|| currentText.endsWith("x")|| currentText.endsWith("÷")){
            display.append("0.");
            return;
        }
        String[] nums= currentText.split("[+\\-x÷]") ;
        String lastNumber= nums[nums.length-1];
        if(lastNumber.contains(".")){
            return;
        }
        display.append(".");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        number0=findViewById(R.id.number0);
        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("0");
            }
        });
        number1=findViewById(R.id.number1);
        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("1");
            }
        });
        number2=findViewById(R.id.number2);
        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("2");
            }
        });
        number3=findViewById(R.id.number3);
        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("3");
            }
        });
        number4=findViewById(R.id.number4);
        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("4");
            }
        });
        number5=findViewById(R.id.number5);
        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("5");
            }
        });
        number6=findViewById(R.id.number6);
        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("6");
            }
        });
        number7=findViewById(R.id.number7);
        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("7");
            }
        });
        number8=findViewById(R.id.number8);
        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("8");
            }
        });
        number9=findViewById(R.id.number9);
        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("9");
            }
        });
        plus=findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("+");
            }
        });
        minus=findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("-");
            }
        });
        divide=findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("÷");
            }
        });
        multiply=findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator("x");
            }
        });
        dot=findViewById(R.id.dot);
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendDot();
            }
        });
        clearAll=findViewById(R.id.clearAll);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
            }
        });
        delete=findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText= display.getText().toString();
                if(!currentText.isEmpty()){
                    String newText= currentText.substring(0,currentText.length()-1);
                    display.setText(newText);
                }
            }
        });
        equal=findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression= display.getText().toString().replace("x","*").replace("÷","/");
                try {
                    Expression exp= new ExpressionBuilder(expression).build();
                    double result= exp.evaluate();
                    DecimalFormat df= new DecimalFormat("#.#####");
                        if(result== (int)result){
                        display.setText(String.valueOf((int)result));
                        }else {
                        display.setText(df.format(result));
                        }
                } catch (Exception e) {
                    display.setText("ERROR");
                }
            }
        });
    }
}