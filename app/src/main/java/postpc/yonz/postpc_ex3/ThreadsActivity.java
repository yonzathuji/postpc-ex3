package postpc.yonz.postpc_ex3;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThreadsActivity extends AppCompatActivity {

    TextView counterTv;
    Button createButton, startButton, cancelButton;
    int counter;
    Thread counterThread;
    Handler handler;
    boolean isCancelled;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        counter = 0;
        isCancelled = false;
        handler = new Handler();


        counterTv = findViewById(R.id.counter_threads_textview);
        counterTv.setText("0");
        createButton = findViewById(R.id.create_threads_button);
        startButton = findViewById(R.id.start_threads_button);
        cancelButton = findViewById(R.id.cancel_threads_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterThread = new Thread()
                {
                    @Override
                    public void run() {

                        handler.postDelayed(this, 500);

                        if(isInterrupted())
                        {
                            return;
                        }
                        if(isCancelled)
                        {
                            counterTv.setText("Cancelled!");
                            return;
                        }
                        if(counter == 10)
                        {
                            counterTv.setText("Done!");
                            return;
                        }

                        counter++;
                        counterTv.setText(Integer.toString(counter));
                    }
                };
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    counterThread.run();
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(v.getContext(), "Must create task before running",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(!counterThread.isInterrupted())
                    {
                        isCancelled = true;
                    }
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(v.getContext(), "Must create task before cancelling",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
