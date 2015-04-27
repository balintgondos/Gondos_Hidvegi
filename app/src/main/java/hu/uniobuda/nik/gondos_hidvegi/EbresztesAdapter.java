package hu.uniobuda.nik.gondos_hidvegi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Peter on 2015.04.14..
 */
public class EbresztesAdapter extends BaseAdapter {


    private final List<Ebresztes> ebresztesek;

    public EbresztesAdapter(List<Ebresztes> ebresztesek) {
        this.ebresztesek = ebresztesek;
    }


    public int getCount() {
        return ebresztesek.size();
    }

    public void addItem(Ebresztes egyEbresztes)
    {
        ebresztesek.add(egyEbresztes);
    }

    public void deleteItem(Ebresztes egyEbresztes)
    {
        ebresztesek.remove(egyEbresztes);

    }

    @Override
    public Object getItem(int position) {
        return ebresztesek.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder
    {
        ImageView AorM;
        TextView tvKivalaszt;
        TextView ebresztesIdeje;
        TextView hetfo;
        TextView kedd;
        TextView szerda;
        TextView csütörtök;
        TextView péntek;
        TextView szombat;
        TextView vasarnap;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final Ebresztes currEbresztes = ebresztesek.get(position);
        final ViewHolder holder;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ebresztessor,parent,false);
            holder = new ViewHolder();
            holder.AorM = (ImageView) convertView.findViewById(R.id.AorM);
            holder.ebresztesIdeje = (TextView) convertView.findViewById(R.id.ebresztesideje);
            holder.tvKivalaszt = (TextView) convertView.findViewById(R.id.kivalasztott);
            holder.hetfo = (TextView) convertView.findViewById(R.id.hetfo);
            holder.kedd = (TextView) convertView.findViewById(R.id.kedd);
            holder.szerda = (TextView) convertView.findViewById(R.id.szerda);
            holder.csütörtök = (TextView) convertView.findViewById(R.id.csutortok);
            holder.péntek = (TextView) convertView.findViewById(R.id.pentek);
            holder.szombat = (TextView) convertView.findViewById(R.id.szombat);
            holder.vasarnap = (TextView) convertView.findViewById(R.id.vasarnap);


            holder.AorM.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!currEbresztes.isActive()) {
                        currEbresztes.setActive(true);
                    }else
                    {
                       currEbresztes.setActive(false);
                    }

                    if(currEbresztes.isActive())
                    {
                        holder.AorM.setImageResource(R.drawable.red);
                    }else
                    {
                        holder.AorM.setImageResource(R.drawable.green);
                    }
                }
            });

            holder.hetfo.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        holder.tvKivalaszt.setText(holder.hetfo.getText().toString());
                        holder.ebresztesIdeje.setText(currEbresztes.napokElem(0));

                }
            });

            holder.kedd.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvKivalaszt.setText(holder.kedd.getText().toString());
                    holder.ebresztesIdeje.setText(currEbresztes.napokElem(1));
                }
            });

            holder.szerda.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvKivalaszt.setText(holder.szerda.getText().toString());
                    holder.ebresztesIdeje.setText(currEbresztes.napokElem(2));
                }
            });

            holder.csütörtök.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvKivalaszt.setText(holder.csütörtök.getText().toString());
                    holder.ebresztesIdeje.setText(currEbresztes.napokElem(3));
                }
            });

            holder.péntek.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvKivalaszt.setText(holder.péntek.getText().toString());
                    holder.ebresztesIdeje.setText(currEbresztes.napokElem(4));
                }
            });

            holder.szombat.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvKivalaszt.setText(holder.szombat.getText().toString());
                    holder.ebresztesIdeje.setText(currEbresztes.napokElem(5));
                }
            });

            holder.vasarnap.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvKivalaszt.setText(holder.vasarnap.getText().toString());
                    holder.ebresztesIdeje.setText(currEbresztes.napokElem(6));
                }
            });

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(currEbresztes.isActive())
        {
            holder.AorM.setImageResource(R.drawable.red);
        }else
        {
           holder.AorM.setImageResource(R.drawable.green);
        }

        if(currEbresztes.getOnce()==1)
        {
            holder.hetfo.setClickable(false);
            holder.kedd.setClickable(false);
            holder.szerda.setClickable(false);
            holder.csütörtök.setClickable(false);
            holder.péntek.setClickable(false);
            holder.szombat.setClickable(false);
            holder.vasarnap.setClickable(false);


        }else
        {
            holder.hetfo.setClickable(true);
            holder.kedd.setClickable(true);
            holder.szerda.setClickable(true);
            holder.csütörtök.setClickable(true);
            holder.péntek.setClickable(true);
            holder.szombat.setClickable(true);
            holder.vasarnap.setClickable(true);
        }

        holder.ebresztesIdeje.setText(currEbresztes.getEbresztesIdeje());

        return convertView;
    }
}


