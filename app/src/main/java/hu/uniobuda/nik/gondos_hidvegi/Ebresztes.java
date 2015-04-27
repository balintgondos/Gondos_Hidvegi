package hu.uniobuda.nik.gondos_hidvegi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Peter on 2015.04.14..
 */
public class Ebresztes implements Parcelable{

    private boolean active; //Ha 0 akkor nem ha 1 akkor igen
    private String ebresztesIdeje; //Első ébresztés
    private String uzenet; // Üzenet az ébresztéshez
    private int szundiSzam; // ismétlések száma
    private String[] napok; // a hétre beállított ébresztések
    private long dbID;      // id az sql táblához.
    private int once; // ez bool lenne de adatbázis miatt inkább int lett...


    public int getOnce() {
        return once;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEbresztesIdeje() {
        return ebresztesIdeje;
    }

    public String getUzenet() {
        return uzenet;
    }

    public int getSzundiSzam() {
        return szundiSzam;
    }

    public long getDbID() {
        return dbID;
    }

    public String[] getNapok() {
        return napok;
    }



    public Ebresztes(boolean active, long dbID, String ebresztesIdeje, String uzenet, int szundiSzam, int once)
    {
        this.napok = new String[7];
        for(int i = 0; i < napok.length; i=i+1)
        {
            napok[i]=" - ";
        }
        this.active = active;
        this.ebresztesIdeje = ebresztesIdeje;
        this.uzenet = uzenet;
        this.szundiSzam = szundiSzam;
        this.dbID = dbID;
        this.once = once;
    }


    public Ebresztes(boolean active, String ebresztesIdeje, String uzenet, int szundiSzam, int once)
    {
        this.napok = new String[7];
        for(int i = 0; i < napok.length; i=i+1)
        {
           napok[i]=" - ";
        }
        this.active = active;
        this.ebresztesIdeje = ebresztesIdeje;
        this.uzenet = uzenet;
        this.szundiSzam = szundiSzam;
        this.once = once;
    }

    public void napokBeallit(String[] tomb)
    {
        for (int i = 0; i<napok.length; i++)
        {
            napok[i]=tomb[i];
        }

    }

    public String napokElem(int i)
    {
        return napok[i];
    }



    public static final Creator<Ebresztes> CREATOR
            = new Creator<Ebresztes>() {
        public Ebresztes createFromParcel(Parcel in) {
            return new Ebresztes(in);
        }

        public Ebresztes[] newArray(int size) {
            return new Ebresztes[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeString(ebresztesIdeje);
        dest.writeString(uzenet);
        dest.writeInt(szundiSzam);
        dest.writeStringArray(napok);
        dest.writeInt(once);
    }

    private Ebresztes(Parcel in)
    {
        active = in.readByte() != 0;
        ebresztesIdeje = in.readString();
        uzenet = in.readString();
        szundiSzam = in.readInt();
        napok = in.createStringArray();
        once = in.readInt();
    }
}
