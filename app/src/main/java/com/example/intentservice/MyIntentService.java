package com.example.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import static com.example.intentservice.MainActivity.CUSTOM_ACTION;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    public static final String MESSAGE_KEY_EXTRA = "Key_message";

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
            mediaPlayer.start();
            Intent intent1 = new Intent();
            intent1.setAction(CUSTOM_ACTION);
            intent1.putExtra(MESSAGE_KEY_EXTRA, "My music");
            sendBroadcast(intent1);
        }
    }

}
