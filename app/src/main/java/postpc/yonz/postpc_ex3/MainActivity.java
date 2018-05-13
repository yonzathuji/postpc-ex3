package postpc.yonz.postpc_ex3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button asyncButton, threadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncButton = findViewById(R.id.async_button);
        asyncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,
                        AsyncTaskActivity.class);
                MainActivity.this.startActivity(myIntent);

            }
        });

        threadButton = findViewById(R.id.threads_button);
        threadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,
                        ThreadsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
