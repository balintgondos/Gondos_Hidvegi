package hu.uniobuda.nik.gondos_hidvegi;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Peter on 2015.04.29..
 */
public class Megjelenik extends Activity implements SensorEventListener {

    Button btn1;
    MediaPlayer mp;
    Sensor accelero;
    SensorManager sm;
    TextView tv1set;
    TextView tv2set;
    TextView tv3set;
    AlarmManager alarmManager;
    Intent i;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    int tv1rand;
    int tv2rand;
    int tv3rand;
    Handler myHandler = new Handler();
    boolean Xfix =false;
    boolean Yfix =false;
    boolean Zfix =false;
    NumberPicker np;
    int npvalue;
    TextView szundiszam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.megjelenik);

      tv1set = (TextView) findViewById(R.id.tvXset);
      tv2set = (TextView) findViewById(R.id.tvYset);
      tv3set = (TextView) findViewById(R.id.tvZset);

      tv1 = (TextView) findViewById(R.id.tvX);
      tv2 = (TextView) findViewById(R.id.tvY);
      tv3 = (TextView) findViewById(R.id.tvZ);

      tv1rand = randInt(-8,8);
      tv2rand = randInt(-8,8);
      tv3rand = randInt(-8,8);

      tv1.setText("X: "+String.valueOf(tv1rand));
      tv2.setText("Y: "+String.valueOf(tv2rand));
      tv3.setText("Z: "+String.valueOf(tv3rand));

      szundiszam = (TextView) findViewById(R.id.tvSzundiSzam);
      szundiszam.setText("Ennyi szundi van még: "+ EbresztoBroadcast.szundi);

      np = (NumberPicker) findViewById(R.id.numberPicker);
        np.post(new Runnable() {
            @Override
            public void run() {

                String[] nums = new String[10];
                for(int i=0; i<nums.length; i++) {
                    nums[i] = Integer.toString(i+1);
                }

                np.setMaxValue(10);
                np.setMinValue(1);
                np.setWrapSelectorWheel(true);
                np.setDisplayedValues(nums);
                np.setValue(1);
                np.clearFocus();
                npvalue = np.getValue();
            }
        });

        np.setOnValueChangedListener( new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                np.clearFocus();
                npvalue = np.getValue();
            }
        });

      sm=(SensorManager)getSystemService(SENSOR_SERVICE);
      accelero = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
      sm.registerListener(this,accelero,SensorManager.SENSOR_DELAY_NORMAL);


      if(getIntent().getIntExtra("szundi", 0)>1) {
          mp = MediaPlayer.create(this, R.raw.a);
      }else if(getIntent().getIntExtra("szundi", 0)==1)
      {
          mp = MediaPlayer.create(this, R.raw.b);
      }
      else
      {
          mp = MediaPlayer.create(this, R.raw.c);

      }
          mp.start();

          if(getIntent().getIntExtra("szundi", 0)==0) {
              mp.setLooping(true);
              //rezgés
              Vibrator v = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
              v.vibrate(2000);
          }
          else
          {
              myHandler.postDelayed(close, 20000);

          }


        btn1 = (Button) findViewById(R.id.btn1megjelenik);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getIntExtra("szundi", 0)>0)
                {
                    EbresztoBroadcast.szundi--;
                    mp.release();
                    myHandler.removeCallbacks(close);
                    i = new Intent(Megjelenik.this.getApplicationContext(),EbresztoBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                            Megjelenik.this.getApplicationContext(), 1000+ EbresztoBroadcast.szundi, i, 0);
                    //Log.v("cal.getTimeInMillis()", String.valueOf(caltobb.getTimeInMillis()));
                    alarmManager = (AlarmManager) Megjelenik.this.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(npvalue*60*1000), pendingIntent);

                    mp.release();
                    finish();
                }
                else
                {
                    Toast.makeText(Megjelenik.this,"nincs több szundi!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private Runnable close = new Runnable() {
        public void run() {
            i = new Intent(getApplicationContext(),EbresztoBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getApplicationContext(), 1000+ EbresztoBroadcast.szundi, i, 0);
            //Log.v("cal.getTimeInMillis()", String.valueOf(caltobb.getTimeInMillis()));
            alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(10*1000), pendingIntent);
            mp.release();
            finish();
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(!Xfix && (int)event.values[0]!=tv1rand)
        {
             tv1set.setText(String.valueOf("= myX: "+(int)event.values[0]));
        }
        else if(!Xfix)
        {
             tv1.setText(" X");
             tv1set.setText(" = RDY!");
             Xfix = true;
        }

        if(!Yfix && (int)event.values[1]!=tv2rand)
        {
            tv2set.setText(String.valueOf("= myY: "+(int)event.values[1]));
        }
        else if(!Yfix)
        {
            tv2.setText("Y");
            tv2set.setText(" = RDY!");
            Yfix = true;
        }

        if(!Zfix && (int)event.values[2]!=tv3rand)
        {
            tv3set.setText(String.valueOf("= myZ: "+(int)event.values[2]));
        }
        else if(!Zfix)
        {
            tv3.setText("Z");
            tv3set.setText(" = RDY!");
            Zfix = true;
        }

        if(Xfix && Yfix && Zfix)
        {
            /*alarmManager.cancel(PendingIntent.getBroadcast(getApplicationContext(),1000+EbresztoBroadcast.szundi, i, 0));
            PendingIntent.getBroadcast(getApplicationContext(),1000+EbresztoBroadcast.szundi,i,0).cancel();
            */
            if(EbresztoBroadcast.szundi!=0) {
                myHandler.removeCallbacks(close);
            }
            mp.release();
            finish();
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
