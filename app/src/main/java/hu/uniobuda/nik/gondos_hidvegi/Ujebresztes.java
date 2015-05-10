package hu.uniobuda.nik.gondos_hidvegi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by Peter on 2015.04.17..
 */
public class Ujebresztes extends Activity {


    Button btnOk;
    Button btnMegse;
    TimePicker timePicker;
    CheckBox cb;
    TextView hido,kido,szido,csido,pido,sztido,vido;
    TextView h,k,sz,cs,p,szt,vas;
    String[] sarray;
    int selected;
    boolean mehet;
    Ebresztes newEbresztes;
    NumberPicker np;
    int npvalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ujebresztes);

        mehet = true;
        selected = 0;
        sarray = new String[7];
        btnMegse = (Button) findViewById(R.id.btnMegse);
        btnOk = (Button) findViewById(R.id.btnOk);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        cb = (CheckBox) findViewById(R.id.cb1);
        //timePicker.setIs24HourView(true);
        timePicker.setAddStatesFromChildren(true);
        h = (TextView) findViewById(R.id.h);
        k = (TextView) findViewById(R.id.k);
        sz = (TextView) findViewById(R.id.sz);
        cs = (TextView) findViewById(R.id.cs);
        p = (TextView) findViewById(R.id.p);
        szt = (TextView) findViewById(R.id.szt);
        vas = (TextView) findViewById(R.id.v);

        hido = (TextView) findViewById(R.id.hido);
        kido = (TextView) findViewById(R.id.kido);
        szido = (TextView) findViewById(R.id.szido);
        csido = (TextView) findViewById(R.id.csido);
        pido = (TextView) findViewById(R.id.pido);
        sztido = (TextView) findViewById(R.id.sztido);
        vido = (TextView) findViewById(R.id.vido);
        np = (NumberPicker) findViewById(R.id.numberPickerUjEbr);
        np.post(new Runnable() {
            @Override
            public void run() {

                String[] nums = new String[21];
                for(int i=0; i<nums.length; i++) {
                    nums[i] = Integer.toString(i+1);
                }

                np.setMaxValue(20);
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



      timePicker.setOnTimeChangedListener( new TimePicker.OnTimeChangedListener() {
          @Override
          public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
              if(h.getCurrentTextColor() == Color.BLUE)
                hido.setText(timePickerKiolvas());
                selected++;

              if(k.getCurrentTextColor() == Color.BLUE)
                  kido.setText(timePickerKiolvas());
                  selected++;

              if(sz.getCurrentTextColor() == Color.BLUE)
                  szido.setText(timePickerKiolvas());
                  selected++;

              if(cs.getCurrentTextColor() == Color.BLUE)
                  csido.setText(timePickerKiolvas());
                  selected++;

              if(p.getCurrentTextColor() == Color.BLUE)
                  pido.setText(timePickerKiolvas());
                  selected++;

              if(szt.getCurrentTextColor() == Color.BLUE)
                  sztido.setText(timePickerKiolvas());
                  selected++;

              if(vas.getCurrentTextColor() == Color.BLUE)
                  vido.setText(timePickerKiolvas());
                  selected++;


          }
      });
    }


    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnMegse:

                finish();
                break;

            case R.id.btnOk:
                //kód

                if(cb.isChecked()) {
                    uresTomb();
                    newEbresztes = new Ebresztes(false, timePickerKiolvas(), "one", npvalue,1);
                    mehet = true;
                }
                else
                {
                   if(selected>0) {
                       nemEgyszeri(); //beállítja a heti tömböt (sarray)
                       newEbresztes = new Ebresztes(false, "", "more", npvalue, 0);
                       mehet = true;
                   }else
                   {
                       mehet = false;
                   }
                    //ide jön a több napos ébresztés...
                }

                if(mehet) {
                    newEbresztes.napokBeallit(sarray);
                    Intent i = new Intent(Ujebresztes.this, MainActivity.class);
                    i.putExtra("ujEbresztes", newEbresztes);
                    startActivity(i);
                    finish();
                    break;
                }
                else
                {
                    Toast.makeText(this,"Nem adott meg több ébresztést vagy jelölje be az egyszeri ébresztést!",Toast.LENGTH_LONG).show();
                }

                break;

            case(R.id.h) :
                if(h.getCurrentTextColor() != Color.BLUE) {
                    h.setTextColor(Color.BLUE);

                }else
                {
                    h.setTextColor(Color.BLACK);
                    selected--;
                }
                break;

            case(R.id.k) :
                if(k.getCurrentTextColor() != Color.BLUE) {
                    k.setTextColor(Color.BLUE);
                }else
                {
                    k.setTextColor(Color.BLACK);
                    selected--;
                }
                break;

            case(R.id.sz) :
                if(sz.getCurrentTextColor() != Color.BLUE) {
                    sz.setTextColor(Color.BLUE);
                }else
                {
                    sz.setTextColor(Color.BLACK);
                    selected--;
                }
                break;

            case(R.id.cs) :
                if(cs.getCurrentTextColor() != Color.BLUE) {
                    cs.setTextColor(Color.BLUE);
                }else
                {
                    cs.setTextColor(Color.BLACK);
                    selected--;
                }
                break;

            case(R.id.p) :
                if(p.getCurrentTextColor() != Color.BLUE) {
                    p.setTextColor(Color.BLUE);
                }else
                {
                    p.setTextColor(Color.BLACK);
                    selected--;
                }
                break;

            case(R.id.szt) :
                if(szt.getCurrentTextColor() != Color.BLUE) {
                    szt.setTextColor(Color.BLUE);
                }else
                {
                    szt.setTextColor(Color.BLACK);
                    selected--;
                }
                break;

            case(R.id.v) :
                if(vas.getCurrentTextColor() != Color.BLUE) {
                    vas.setTextColor(Color.BLUE);
                }else
                {
                    vas.setTextColor(Color.BLACK);
                    selected--;
                }
                break;



            default:
                break;

        }

    }

    public void uresTomb()
    {
        for(int i = 0 ; i < sarray.length; i++)
        {
            sarray[i] = " - ";
        }

    }

    public void nemEgyszeri()
    {
        uresTomb();
        if(h.getCurrentTextColor() == Color.BLUE)
            sarray[0]=hido.getText().toString();

        if(k.getCurrentTextColor() == Color.BLUE)
            sarray[1] = kido.getText().toString();

        if(sz.getCurrentTextColor() == Color.BLUE)
           sarray[2]=szido.getText().toString();

        if(cs.getCurrentTextColor() == Color.BLUE)
            sarray[3]=csido.getText().toString();

        if(p.getCurrentTextColor() == Color.BLUE)
            sarray[4]=pido.getText().toString();

        if(szt.getCurrentTextColor() == Color.BLUE)
            sarray[5]=sztido.getText().toString();

        if(vas.getCurrentTextColor() == Color.BLUE)
            sarray[6]=vido.getText().toString();


    }

    private String timePickerKiolvas()
    {
        /*return (timePicker.getCurrentHour() + ":" +
                timePicker.getCurrentMinute());*/
        return pad(timePicker.getCurrentHour())+ ":" + pad(timePicker.getCurrentMinute());
    }


    private String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


}
