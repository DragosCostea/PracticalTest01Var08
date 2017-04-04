package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import service.PracticalTest01Var08Service;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    private EditText editText1 = null;
    private EditText editText2 = null;
    private EditText editText3 = null;
    private EditText editText4 = null;
    private Button setButton   = null;
    private int serviceStatus = 0;
    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.d("[Message]", intent.getStringExtra("message"));

            int val1 = intent.getIntExtra("val1", -1);
            int val2 = intent.getIntExtra("val2", -1);
            int val3 = intent.getIntExtra("val3", -1);
            int val4 = intent.getIntExtra("val4", -1);

            editText1.setText(String.valueOf(val1));
            editText2.setText(String.valueOf(val2));
            editText3.setText(String.valueOf(val3));
            editText4.setText(String.valueOf(val4));
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    int textVal1;
                    int textVal2;
                    int textVal3;
                    int textVal4;
                    try {
                        textVal1 = Integer.parseInt(editText1.getText().toString());
                        textVal2 = Integer.parseInt(editText2.getText().toString());
                        textVal3 = Integer.parseInt(editText3.getText().toString());
                        textVal4 = Integer.parseInt(editText4.getText().toString());
                    } catch (NumberFormatException e) {

                        break;
                    }
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08SecondaryActivity.class);
                    //int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) +
                    //        Integer.parseInt(rightEditText.getText().toString());
                    intent.putExtra("text1", textVal1);
                    intent.putExtra("text2", textVal2);
                    intent.putExtra("text3", textVal3);
                    intent.putExtra("text4", textVal4);

                    startActivityForResult(intent, 21); // startactivity for result ?

                    break;
                    // send intent
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);

        editText1.setText(String.valueOf(0));
        editText2.setText(String.valueOf(0));
        editText3.setText(String.valueOf(0));
        editText4.setText(String.valueOf(0));

        setButton = (Button)findViewById(R.id.button);

        setButton.setOnClickListener(buttonClickListener);

        Log.d("main", "to start service");
        if (serviceStatus == 0) {
            Intent intent = new Intent(getApplicationContext(), service.PracticalTest01Var08Service.class);
            //intent.putExtra("firstNumber", leftNumberOfClicks);
            //intent.putExtra("secondNumber", rightNumberOfClicks);
            //getApplicationContext().startService(intent);
            serviceStatus = 1; // service started
        }
        Log.d("main", "sent start service");
        intentFilter.addAction("random_action");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("editText1", editText1.getText().toString());
        savedInstanceState.putString("editText2", editText2.getText().toString());
        savedInstanceState.putString("editText3", editText3.getText().toString());
        savedInstanceState.putString("editText4", editText4.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("editText1")) {
            editText1.setText(savedInstanceState.getString("editText1"));
        } else {
            editText1.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("editText2")) {
            editText2.setText(savedInstanceState.getString("editText2"));
        } else {
            editText2.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("editText3")) {
            editText3.setText(savedInstanceState.getString("editText3"));
        } else {
            editText3.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("editText4")) {
            editText4.setText(savedInstanceState.getString("editText4"));
        } else {
            editText4.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
       // if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
        //    Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
       // }
    }
    // on activity result..

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        serviceStatus = 0;
        super.onDestroy();
    }
}
