package hu.uniobuda.nik.gondos_hidvegi;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            //Ebreszteseklistájának megjelenítése fragmentben.

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ListaEbresztesek listaEbresztesek = (ListaEbresztesek) fm.findFragmentByTag(ListaEbresztesek.TAG);

            if(listaEbresztesek == null)
            {
                listaEbresztesek = new ListaEbresztesek();
            }

            ft.replace(R.id.fragmenthelye,listaEbresztesek);
            ft.commit();


    }



    public void onClick(View view)
    {
        if(view.getId() == R.id.button1)
        {
            Intent i = new Intent();
            i.setClass(this,Ujebresztes.class);
            startActivity(i);
            finish();

        }
    }


}