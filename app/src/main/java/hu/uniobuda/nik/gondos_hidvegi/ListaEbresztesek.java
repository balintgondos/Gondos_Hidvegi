package hu.uniobuda.nik.gondos_hidvegi;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Peter on 2015.04.16..
 */
public class ListaEbresztesek extends ListFragment {

    public static final String TAG = "ListaEbresztesek";
    ArrayList<Ebresztes> ebresztesek;
    EbresztesAdapter ebresztesAdapter;
    Db db;
    TextView kivalasztott;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Db(getActivity());
        ebresztesek = new ArrayList<Ebresztes>();
        ebresztesAdapter = new EbresztesAdapter(ebresztesek);

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);

        if(v.equals(getListView()))
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(((Ebresztes) getListAdapter().getItem(info.position)).getUzenet());

        }
        String[] itemsarray = getResources().getStringArray(R.array.ebresztesmenu);

        for (int i = 0; i < itemsarray.length; i++)
        {

            menu.add(Menu.NONE,i,i,itemsarray[i]);

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == 0)
        {
            //((EbresztesAdapter)getListAdapter()).deleteItem(
            Ebresztes kijelolt = (Ebresztes)getListAdapter().getItem(info.position);

            db.deleteTitle(kijelolt.getDbID());
            dbrecall();
            ((EbresztesAdapter)getListAdapter()).notifyDataSetChanged();
        }



        return true;
    }

    @Override
    public void onResume() {
             super.onResume();
             setListAdapter(ebresztesAdapter);
             registerForContextMenu(getListView());


            getListView().setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView kivalasztott = (TextView) view.findViewById(R.id.kivalasztott);
                    TextView ebresztesido = (TextView) view.findViewById(R.id.ebresztesideje);
                        switch (view.getId())
                        {
                            case(R.id.hetfo) : kivalasztott.setText("H"); ebresztesido.setText(((Ebresztes)getListAdapter().getItem(position)).napok[0]); break;

                            case(R.id.kedd) : kivalasztott.setText("K"); ebresztesido.setText(((Ebresztes)getListAdapter().getItem(position)).napok[1]); break;

                            case(R.id.szerda) : kivalasztott.setText("SZ"); ebresztesido.setText(((Ebresztes)getListAdapter().getItem(position)).napok[2]); break;

                            case(R.id.csutortok) : kivalasztott.setText("CS"); ebresztesido.setText(((Ebresztes)getListAdapter().getItem(position)).napok[3]); break;

                            case(R.id.pentek) : kivalasztott.setText("P"); ebresztesido.setText(((Ebresztes)getListAdapter().getItem(position)).napok[4]); break;

                            case(R.id.szombat) : kivalasztott.setText("SZT"); ebresztesido.setText(((Ebresztes)getListAdapter().getItem(position)).napok[5]); break;

                            case(R.id.vasarnap) : kivalasztott.setText("V"); ebresztesido.setText(((Ebresztes)getListAdapter().getItem(position)).napok[6]);break;
                        }
                }
            });

            if(getActivity().getIntent().getParcelableExtra("ujEbresztes")!=null)
            {
                Log.v("batyu","nem üres!");
                Ebresztes newEbresztes = getActivity().getIntent().getParcelableExtra("ujEbresztes");
                Log.v("ujadatNapoktömb","asd");
                Log.v("ujadatNapoktömb",newEbresztes.napok[0]+" "+newEbresztes.napok[1]+" "+newEbresztes.napok[2]+" "+newEbresztes.napok[3]);

                if(newEbresztes!=null)
                {
                    db.addUser(newEbresztes.getEbresztesIdeje(), newEbresztes.getUzenet(), newEbresztes.getSzundiSzam(),newEbresztes.napok);

                }
            }

            dbrecall();



    }

    private void dbrecall() {

        ebresztesek.clear();
        Cursor c = db.getAllUser();
        while(c.isAfterLast() == false)
        {
            String[] napok = new String[6];
            for (int i = 0; i<napok.length;i++)
            {
                napok[i] = c.getString(4+i);
            }
            Ebresztes ebresztes = new Ebresztes(true,c.getLong(0),c.getString(1),c.getString(2),c.getInt(3),napok);
            ebresztesek.add(ebresztes);
            c.moveToNext();
        }
        ((EbresztesAdapter) getListAdapter()).notifyDataSetChanged();

    }


}
