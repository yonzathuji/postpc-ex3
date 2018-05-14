package postpc.yonz.postpc_ex3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {

    TextView counterTv;
    Button createButton, startButton, cancelButton;
    int counter;
    AsyncCounter counterTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        counter = 1;

        counterTv = findViewById(R.id.counter_async_textview);
        counterTv.setText("0");
        createButton = findViewById(R.id.create_async_button);
        startButton = findViewById(R.id.start_async_button);
        cancelButton = findViewById(R.id.cancel_async_button);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterTask = new AsyncCounter();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    counterTask.execute(10);
                    //new AsyncCounter().execute(10);
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
            public void onClick(View v)
            {
                try
                {
                    counterTask.cancel(true);
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(v.getContext(), "Must create task before running",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private class AsyncCounter extends AsyncTask<Integer, Integer, String>
    {
        @Override
        protected String doInBackground(Integer... params) {
            for(; counter <= params[0]; counter++)
            {
                try {
                    Thread.sleep(500);
                    publishProgress(counter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Done!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            counterTv.setText(Integer.toString(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            counterTv.setText(s);
        }

        @Override
        protected void onCancelled() {
            counterTv.setText("Cancelled");
        }
    }
}
