package rayckaprojects.tfg;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.provider.AlarmClock.*;

public class alarma2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma2);
        if (getIntent().getExtras().getString("type").equals("alarm")){
            String mensaje =getIntent().getExtras().getString("mensaje");
            int hora = Integer.parseInt(getIntent().getExtras().getString("hora"));
            int min = Integer.parseInt(getIntent().getExtras().getString("min"));
            createAlarm(mensaje,hora,min);
        }else {
            int seconds = getIntent().getExtras().getInt("hora")*3600+getIntent().getExtras().getInt("minutos")*60;
            startTimer(seconds);
        }
    }
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void startTimer(int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
