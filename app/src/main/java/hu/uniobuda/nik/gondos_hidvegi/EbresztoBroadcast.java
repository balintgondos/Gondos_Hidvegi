package hu.uniobuda.nik.gondos_hidvegi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Peter on 2015.04.29..
 */
public class EbresztoBroadcast extends BroadcastReceiver {

    public static int szundi;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent();
        i.setClassName("hu.uniobuda.nik.gondos_hidvegi","hu.uniobuda.nik.gondos_hidvegi.Megjelenik");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(intent.getLongExtra("ebresztesid",0)!=0)
        {
            i.putExtra("id",intent.getLongExtra("ebresztesid",0));
        }
        if(intent.getIntExtra("szundiszam",0)!=0) {
            szundi = intent.getIntExtra("szundiszam", 0);
            intent.removeExtra("szundiszam");
        }
        i.putExtra("szundi",szundi);
        context.startActivity(i);

    }



}
