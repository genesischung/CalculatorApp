package myzhong.com.ui.main;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import myzhong.com.R;

public class CalculatorFragment extends Fragment {

    private CalculatorViewModel mViewModel;
    private String previousNum = "0";
    private String currentNum = "0";
    private boolean dotClicked = false;
    private boolean equalClicked = false;
    private char operator = 0;
    private double lastOperand = 0;

    public static CalculatorFragment newInstance() {
        return new CalculatorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calculator_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        // TODO: Use the ViewModel

        Button button_0 = getActivity().findViewById(R.id.button_0);
        Button button_1 = getActivity().findViewById(R.id.button_1);
        Button button_2 = getActivity().findViewById(R.id.button_2);
        Button button_3 = getActivity().findViewById(R.id.button_3);
        Button button_4 = getActivity().findViewById(R.id.button_4);
        Button button_5 = getActivity().findViewById(R.id.button_5);
        Button button_6 = getActivity().findViewById(R.id.button_6);
        Button button_7 = getActivity().findViewById(R.id.button_7);
        Button button_8 = getActivity().findViewById(R.id.button_8);
        Button button_9 = getActivity().findViewById(R.id.button_9);

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(0);
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(1);
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(2);
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(3);
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(4);
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(5);
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(6);
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(7);
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(8);
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNumber(9);
            }
        });


        Button button_dot = getActivity().findViewById(R.id.button_dot);
        button_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDot();
            }
        });

        Button button_AC = getActivity().findViewById(R.id.button_AC);
        button_AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAC();
            }
        });

        Button button_plus = getActivity().findViewById(R.id.button_plus);
        Button button_minus = getActivity().findViewById(R.id.button_minus);
        Button button_multiply = getActivity().findViewById(R.id.button_multiply);
        Button button_divide = getActivity().findViewById(R.id.button_divide);

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOperator('+');
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOperator('-');
            }
        });

        button_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOperator('×');
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOperator('÷');
            }
        });


        Button button_equal = getActivity().findViewById(R.id.button_equal);
        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEqual(true);
            }
        });

    }

    /**
     * Take action when a number button is pressed
     * if last action is equal, clear for a new calculation
     * @param num the number button being pressed
     */
    private void clickNumber(int num){
        if(equalClicked){
            clickAC();
            System.out.println("AC");
        }
        if(currentNum == "0"){
            currentNum = String.valueOf(num);
        }
        else {
            currentNum = currentNum + String.valueOf(num);
        }
        updateDisplay();
    }

    /**
     * Add a decimal dot to the current number
     * ignore the input if dot has been clicked once
     */
    private void clickDot(){
        // ignore if dot has been clicked once
        if(dotClicked){
            Toast.makeText(getActivity(),
                    "invalid operation", Toast.LENGTH_SHORT).show();
        }
        else{
            currentNum = currentNum + ".";
            dotClicked = true;
            updateDisplay();
        }
    }

    /**
     * Take action when a operator button is pressed
     * if last action is equal, perform operation on the last result
     * if last action is a operator, evaluate the saved operation and perform
     * further operation on the result
     * @param operator the operator button pressed
     */
    private void clickOperator(char operator){
        // Only evaluate if the last action is not equal
        if(!equalClicked){
            clickEqual(false);
        }
        equalClicked = false;

        // save the current number and clear the display for new input
        previousNum = currentNum;
        currentNum = "0";
        dotClicked = false;

        // highlight the selected operator
        Button button;
        switch(operator){
            case '+':
                button = getActivity().findViewById(R.id.button_plus);
                break;
            case '-':
                button = getActivity().findViewById(R.id.button_minus);
                break;
            case '×':
                button = getActivity().findViewById(R.id.button_multiply);
                break;
            case '÷':
                button = getActivity().findViewById(R.id.button_divide);
                break;
            default:
                Toast.makeText(getActivity(), "invalid operation",
                        Toast.LENGTH_SHORT).show();
                return;
        }
        resetOperators();
        button.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.white));

        // save the selected operator
        this.operator = operator;
    }

    /**
     * Evaluate the operation when equal or a operator button is pressed
     * @param equalButton true if called by the equal button
     */
    private void clickEqual(boolean equalButton){
        double result = Double.valueOf(previousNum.replaceAll("\\.+$", ""));
        double operand = Double.valueOf(currentNum.replaceAll("\\.+$", ""));

        // if equal is pressed multiple times, repeat the last operation
        if(equalClicked){
            result = operand;
            operand = lastOperand;
        }
        lastOperand = operand;

        //System.out.println("result： " + result + "operand: " + operand);

        switch(operator){
            case '+':
                result += operand;
                break;
            case '-':
                result -= operand;
                break;
            case '×':
                result *= operand;
                break;
            case '÷':
                result /= operand;
                break;
            default:
                /*Toast.makeText(getActivity(), "invalid operation",
                        Toast.LENGTH_SHORT).show();*/
                return;
        }


        // Do not show decimals if the result is a whole number
        if(Math.floor(result) == result) {
            currentNum = String.valueOf((int)result);
        }
        else{
            currentNum = String.valueOf(result);
        }

        // do not set equalClicked if the method is called by other buttons
        if(equalButton) {
            equalClicked = true;
        }
        updateDisplay();
    }

    /**
     * update the display to reflect the current number
     */
    private void updateDisplay(){
        TextView tvDisplay = getActivity().findViewById(R.id.calculator_display);
        tvDisplay.setText(currentNum);
    }

    /**
     * reset all member variables back to default
     */
    private void clickAC(){
        previousNum = "0";
        currentNum = "0";
        dotClicked = false;
        equalClicked = false;
        operator = 0;
        lastOperand = 0;
        resetOperators();
        updateDisplay();
    }

    /**
     * Reset all operator buttons back to original background color
     */
    private void resetOperators(){
        Button button_plus = getActivity().findViewById(R.id.button_plus);
        Button button_minus = getActivity().findViewById(R.id.button_minus);
        Button button_multiply = getActivity().findViewById(R.id.button_multiply);
        Button button_divide = getActivity().findViewById(R.id.button_divide);

        button_plus.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.operator));
        button_minus.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.operator));
        button_multiply.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.operator));
        button_divide.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.operator));
    }
}