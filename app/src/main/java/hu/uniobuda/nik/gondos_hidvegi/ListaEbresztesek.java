package hu.uniobuda.nik.gondos_hidvegi;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

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
            if(!kijelolt.isActive()) {
                db.deleteTitle(kijelolt.getDbID());
                dbrecall();
            }
            else
            {
                Toast.makeText(getActivity(),"Aktív ébresztés nem törölhető!",Toast.LENGTH_LONG).show();
            }
            //((EbresztesAdapter)getListAdapter()).notifyDataSetChanged();
        }



        return true;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
           super.onCreateOptionsMenu(menu, inflater);
           inflater.inflate(R.menu.listmenu,menu);
            //getActivity().getMenuInflater().inflate(R.menu.listmenu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.listmenu) {
            db.deleteALL();
            dbrecall();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
             super.onResume();
             setListAdapter(ebresztesAdapter);
             registerForContextMenu(getListView());
             getListView().setClickable(true);


            if(getActivity().getIntent().getParcelableExtra("ujEbresztes")!=null)
            {
                Log.v("batyu","nem üres!");
                Ebresztes newEbresztes = getActivity().getIntent().getParcelableExtra("ujEbresztes");
                getActivity().getIntent().removeExtra("ujEbresztes");
                if(newEbresztes!=null)
                {
                    db.addEbresztes(newEbresztes.getEbresztesIdeje(), newEbresztes.getUzenet(), newEbresztes.getSzundiSzam(),newEbresztes.getNapok(),newEbresztes.getOnce(),newEbresztes.isActive());

                }
            }

            dbrecall();



    }

    private void dbrecall() {

        for(int i = 0; i < ebresztesek.size();i=i+1)
        {
            ebresztesek.remove(i);
        }
        ebresztesek.clear();
        Cursor c = db.getAllEbresztes();
        while(c.isAfterLast() == false)
        {
            String[] napok = new String[7];
            for (int i = 0; i<napok.length;i++)
            {
                napok[i] = c.getString(4+i);
            }
            boolean active = (c.getInt(12)==1)?true:false;
            Log.v("ACTÍÍÍÍÍÍÍÍÍVE",String.valueOf(c.getInt(12)));
            Ebresztes ebresztes = new Ebresztes(active,c.getLong(0),c.getString(1),c.getString(2),c.getInt(3),c.getInt(11));
            ebresztes.napokBeallit(napok);
            ebresztesek.add(ebresztes);
            c.moveToNext();
        }
        c.close();

        ((EbresztesAdapter) getListAdapter()).notifyDataSetChanged();

    }


}
