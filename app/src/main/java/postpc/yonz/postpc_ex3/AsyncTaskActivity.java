package postpc.yonz.postpc_ex3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    TextView counterTv;
    Button createButton, startButton, cancelButton;
    int counter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        counter = 0;

        counterTv = findViewById(R.id.counter_async_textview);
        counterTv.setText("0");
        createButton = findViewById(R.id.create_async_button);
        startButton = findViewById(R.id.start_async_button);
        cancelButton = findViewById(R.id.cancel_async_button);
    }
}
