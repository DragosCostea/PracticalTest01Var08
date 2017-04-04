package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var08SecondaryActivity extends AppCompatActivity {

    private TextView text1 = null;
    private TextView text2 = null;
    private TextView text3 = null;
    private TextView text4 = null;

    private Button sum = null;
    private Button prod = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int val1 = Integer.parseInt(text1.getText().toString());
            int val2 = Integer.parseInt(text2.getText().toString());
            int val3 = Integer.parseInt(text3.getText().toString());
            int val4 = Integer.parseInt(text4.getText().toString());
            switch(view.getId()) {
                case R.id.sum:
                    int sum  = val1 + val2 + val3 + val4;
                    Toast.makeText(getApplicationContext() , "Sum is" + sum, Toast.LENGTH_LONG).show();
                    setResult(1, null);
                    break;
                case R.id.prod:
                    int prod  = val1 * val2 * val3 * val4;
                    Toast.makeText(getApplicationContext() , "Prod" + prod, Toast.LENGTH_LONG).show();
                    setResult(2, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_secondary);

        text1 = (TextView) findViewById(R.id.editText1);
        text2 = (TextView) findViewById(R.id.editText2);
        text3 = (TextView) findViewById(R.id.editText3);
        text4 = (TextView) findViewById(R.id.editText4);
        sum = (Button) findViewById(R.id.sum);
        prod = (Button) findViewById(R.id.prod);

        Intent intent = getIntent();
        if (intent != null) {
            int val1 = intent.getIntExtra("text1", -1);
            int val2 = intent.getIntExtra("text2", -1);
            int val3 = intent.getIntExtra("text3", -1);
            int val4 = intent.getIntExtra("text4", -1);

            //numberOfClicksTextView.setText(String.valueOf(numberOfClicks));
            text1.setText(String.valueOf(val1));
            text2.setText(String.valueOf(val2));
            text3.setText(String.valueOf(val3));
            text4.setText(String.valueOf(val4));

        }

        //okButton = (Button)findViewById(R.id.ok_button);
        sum.setOnClickListener(buttonClickListener);
        //cancelButton = (Button)findViewById(R.id.cancel_button);
        prod.setOnClickListener(buttonClickListener);

    }
}
