
package android.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView result;
    private Integer lastValue;
    String lastOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        result = (TextView) findViewById(R.id.resultValue);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        input.append(button.getText());
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String operation = button.getText().toString();
        if (operation.equals("c")) {
            input.setText("");
            result.setText("");
            lastValue = null;
            lastOperation = null;
        } else {
            if (!checkOperationWithoutValue()) {
                result.setText("Ошибка операции");
            } else {
                Integer currentValue = Integer.valueOf(input.getText().toString());
                input.setText("");
                if (operation.equals("Квадрат") || operation.equals("Корень")) {
                    switch (operation) {
                        case "Корень":
                            result.setText(String.valueOf((int) Math.sqrt(currentValue)));
                            break;
                        case "Квадрат":
                            result.setText(String.valueOf((int) Math.pow(currentValue, 2)));
                            break;
                    }
                }
                if (lastValue == null) {
                    lastValue = currentValue;
                    lastOperation = operation;
                } else {
                    getOperationResult(currentValue, operation);
                }
            }
        }
    }

    public void getOperationResult(int currentValue, String operation) {
        if (operation.equals("=")) {
            switch (lastOperation) {
                case "+":
                    result.setText(String.valueOf(lastValue + currentValue));
                    lastValue = null;
                    break;
                case "-":
                    result.setText(String.valueOf(lastValue - currentValue));
                    lastValue = null;
                    break;
                case "*":
                    result.setText(String.valueOf(lastValue * currentValue));
                    lastValue = null;
                    break;
                case "/":
                    if (currentValue == 0) {
                        result.setText("Ошибка операции");
                        lastValue = null;
                    } else {
                        result.setText(String.valueOf(lastValue / currentValue));
                        lastValue = null;
                        break;
                    };
            }
        }
    }

    private boolean checkOperationWithoutValue() {
        if (input.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}

