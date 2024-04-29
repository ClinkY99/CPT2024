package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SaveFile {
    private final String Name;
    private final long dateLP;
    private final int Level;

    public SaveFile(String n){
        this(n,0);
    }
    public SaveFile(String n, int l){
        Name = n;
        Level = l;
        dateLP = TimeUtils.millis();

    }
    public SaveFile(JsonValue js){
        Name =js.getString("Name");
        Level =js.getInt("Level");
        dateLP = js.getLong("DateLP");
    }

    public String getName() {
        return Name;
    }

    public String getDateLP() {
        Date date = new Date(dateLP);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DATE)+"  "+calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+" "+(calendar.get(Calendar.AM_PM)==0? "AM":"PM");
    }
    public long getDateLPLong() {
        return dateLP;
    }

    public int getLevel() {
        return Level;
    }
}
