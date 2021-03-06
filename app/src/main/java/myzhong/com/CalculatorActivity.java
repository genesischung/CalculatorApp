package myzhong.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import myzhong.com.ui.main.CalculatorFragment;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CalculatorFragment.newInstance())
                    .commitNow();
        }
    }
}