package rayckaprojects.tfg;

/**
 * Created by Bernat on 5/4/2017.
 */

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.widget.Toast;

public class BroadcastCustomReciever extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {

        mp = MediaPlayer.create(context, R.raw.wormwood);
        mp.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }

}