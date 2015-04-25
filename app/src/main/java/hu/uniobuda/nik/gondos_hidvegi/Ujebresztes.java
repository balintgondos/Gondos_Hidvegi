package hu.uniobuda.nik.gondos_hidvegi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ujebresztes);

        sarray = new String[7];
        btnMegse = (Button) findViewById(R.id.btnMegse);
        btnOk = (Button) findViewById(R.id.btnOk);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        cb = (CheckBox) findViewById(R.id.cb1);
        timePicker.setIs24HourView(true);
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


      timePicker.setOnTimeChangedListener( new TimePicker.OnTimeChangedListener() {
          @Override
          public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
              if(h.getCurrentTextColor() == Color.BLUE)
                hido.setText(timePickerKiolvas());
                Log.v("hétfői idő",hido.getText().toString());

              if(k.getCurrentTextColor() == Color.BLUE)
                  kido.setText(timePickerKiolvas());

              if(sz.getCurrentTextColor() == Color.BLUE)
                  szido.setText(timePickerKiolvas());

              if(cs.getCurrentTextColor() == Color.BLUE)
                  csido.setText(timePickerKiolvas());

              if(p.getCurrentTextColor() == Color.BLUE)
                  pido.setText(timePickerKiolvas());

              if(szt.getCurrentTextColor() == Color.BLUE)
                  sztido.setText(timePickerKiolvas());

              if(vas.getCurrentTextColor() == Color.BLUE)
                  vido.setText(timePickerKiolvas());


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
                Ebresztes newEbresztes;
                if(cb.isChecked()) {
                    uresTomb();
                    newEbresztes = new Ebresztes(false, timePickerKiolvas(), "one", 1);
                    Log.v("rossz","awwwww");
                }
                else
                {
                   Log.v("jóág","ok");
                   nemEgyszeri(); //beállítja a heti tömböt (sarray)
                   newEbresztes = new Ebresztes(false,"","more",1);
                    //ide jön a több napos ébresztés...
                }
                newEbresztes.napokBeallit(sarray);
                Log.v("intentkezdodik",newEbresztes.napokElem(0));
                Intent i = new Intent(Ujebresztes.this, MainActivity.class);
                i.putExtra("ujEbresztes", newEbresztes);
                startActivity(i);
                finish();

                break;

            case(R.id.h) :
                if(h.getCurrentTextColor() != Color.BLUE) {
                    h.setTextColor(Color.BLUE);
                }else
                {
                    h.setTextColor(Color.BLACK);
                }
                break;

            case(R.id.k) :
                if(k.getCurrentTextColor() != Color.BLUE) {
                    k.setTextColor(Color.BLUE);
                }else
                {
                    k.setTextColor(Color.BLACK);
                }
                break;

            case(R.id.sz) :
                if(sz.getCurrentTextColor() != Color.BLUE) {
                    sz.setTextColor(Color.BLUE);
                }else
                {
                    sz.setTextColor(Color.BLACK);
                }
                break;

            case(R.id.cs) :
                if(cs.getCurrentTextColor() != Color.BLUE) {
                    cs.setTextColor(Color.BLUE);
                }else
                {
                    cs.setTextColor(Color.BLACK);
                }
                break;

            case(R.id.p) :
                if(p.getCurrentTextColor() != Color.BLUE) {
                    p.setTextColor(Color.BLUE);
                }else
                {
                    p.setTextColor(Color.BLACK);
                }
                break;

            case(R.id.szt) :
                if(szt.getCurrentTextColor() != Color.BLUE) {
                    szt.setTextColor(Color.BLUE);
                }else
                {
                    szt.setTextColor(Color.BLACK);
                }
                break;

            case(R.id.v) :
                if(vas.getCurrentTextColor() != Color.BLUE) {
                    vas.setTextColor(Color.BLUE);
                }else
                {
                    vas.setTextColor(Color.BLACK);
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
        Log.v("sarray[6]",sarray[6]);



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
