package com.fec.ex.exhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int FLAG_DEFAULT = 0;
    private static final int FLAG_THREAD = 1;
    private static final int FLAG_INNER_HANDLER = 3;
    private static final int FLAG_HANDLER_POST = 4;
    private TextView tvDetails;
    private Button btnThread;
    private Button btnInnerHandler;
    private Button btnHandlerPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDetails = findViewById(R.id.tvDetails);
        btnThread = findViewById(R.id.btnThread);
        btnInnerHandler = findViewById(R.id.btnInnerHandler);
        btnHandlerPost = findViewById(R.id.btnHandlerPost);

        final Handler handler = new MyHandler();
        Message msg = Message.obtain();
        msg.what = FLAG_DEFAULT;
        msg.obj = "MAIN THREAD; UI THREAD";
        handler.sendMessage(msg);

        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = FLAG_THREAD;
                        msg.obj = String.format("THREAD: %s", currentThread().getName());
                        handler.sendMessage(msg);
                    }
                }.start();
            }
        });

        btnInnerHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler1 = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        tvDetails.setText(String.format("INNER-Handler: %s", msg.obj.toString()));
                    }
                };

                new Thread(){
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = FLAG_INNER_HANDLER;
                        msg.obj = String.format("THREAD: %s", currentThread().getName());
                        handler1.sendMessage(msg);
                    }
                }.start();
            }
        });

        btnHandlerPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tvDetails.setText(String.format("Handler Post: %s", currentThread().getName()));
                            }
                        });
                    }
                }.start();
            }

        });

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case FLAG_THREAD:
                    tvDetails.setText(String.format("CLS: %s", msg.obj.toString()));
                    break;
                default:
                    tvDetails.setText(String.format("CLS: %s", msg.obj.toString()));
                    break;
            }
        }
    }
}

