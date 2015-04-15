package hu.uniobuda.nik.gondos_hidvegi;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

   // private ListView lv;
    EbresztesAdapter ebresztesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.id.idListView);

        ArrayList<Ebresztes> ebresztesek = new ArrayList<Ebresztes>();
        ebresztesek.add(new Ebresztes(false,"9:00","Ebresztő",3));
        ebresztesek.add(new Ebresztes(true,"9:10","Ebresztő",4));
        ebresztesek.add(new Ebresztes(false,"9:30","Ebresztő",2));


        ebresztesAdapter = new EbresztesAdapter(ebresztesek);
        setListAdapter(ebresztesAdapter);

        registerForContextMenu(getListView());

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);

        if(v.equals(getListView()))
        {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;

            menu.setHeaderTitle(((Ebresztes)getListAdapter().getItem(info.position)).getUzenet());

        }

        String[] itemsarray = getResources().getStringArray(R.array.ebresztesmenu);

        for (int i = 0; i < itemsarray.length; i++)
        {
           //menu.add(Menu.NONE,i,i,itemsarray[i]);
            menu.add(itemsarray[i]);

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
            //return super.onContextItemSelected(item);


            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if(item.getItemId() == 0)
            {
                ((EbresztesAdapter)getListAdapter()).deleteItem(
                        (Ebresztes)getListAdapter().getItem(info.position));
                ((EbresztesAdapter)getListAdapter()).notifyDataSetChanged();
            }
        return true;
    }
}
