package com.example.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.intentservice.MyIntentService.MESSAGE_KEY_EXTRA;

public class MainActivity extends AppCompatActivity {
    public static final String CUSTOM_ACTION = "com.example.intentservice.PLAY_AUDIO_MESSAGE";
    TextView tvMessage;
    Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = findViewById(R.id.tv_message);
        btnPlay = findViewById(R.id.btn_play_audio);

        //todo register broadcast
        IntentFilter filter = new IntentFilter();
        filter.addAction(CUSTOM_ACTION);
        GetMessageReceiver broadcast = new GetMessageReceiver();
        registerReceiver(broadcast, filter);
        //todo button trigger play audio
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyIntentService.class);
                startService(intent);
            }
        });
    }

    class GetMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null)
                throw new NullPointerException();

            if(intent.getAction().equals(CUSTOM_ACTION)){
                tvMessage.setText(intent.getStringExtra(MESSAGE_KEY_EXTRA));
            }
        }
    }
}
