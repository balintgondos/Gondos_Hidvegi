package hu.uniobuda.nik.gondos_hidvegi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Ebresztes> ebresztesek = new ArrayList<Ebresztes>();
        ebresztesek.add(new Ebresztes(false,"9:00","Ebresztő",3));
        ebresztesek.add(new Ebresztes(true,"9:10","Ebresztő",4));
        ebresztesek.add(new Ebresztes(false,"9:00","Ebresztő",3));
        ebresztesek.add(new Ebresztes(true,"9:10","Ebresztő",4));
        ebresztesek.add(new Ebresztes(false,"9:00","Ebresztő",3));
        ebresztesek.add(new Ebresztes(true,"9:10","Ebresztő",4));
        ebresztesek.add(new Ebresztes(false,"9:00","Ebresztő",3));
        ebresztesek.add(new Ebresztes(true,"9:10","Ebresztő",4));
        ebresztesek.add(new Ebresztes(false,"9:00","Ebresztő",3));
        ebresztesek.add(new Ebresztes(true,"9:10","Ebresztő",4));
        ebresztesek.add(new Ebresztes(false,"9:00","Ebresztő",3));
        ebresztesek.add(new Ebresztes(true,"9:10","Ebresztő",4));

        EbresztesAdapter ebresztesAdapter = new EbresztesAdapter(ebresztesek);
        ListView lv = (ListView) findViewById(R.id.idListView);
        lv.setAdapter(ebresztesAdapter);

    }


}
