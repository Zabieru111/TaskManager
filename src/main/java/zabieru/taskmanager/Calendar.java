package zabieru.taskmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Month> mois = new ArrayList<>();
    private int year;

    public Calendar() {
        this.year = LocalDate.now().getYear();
        mois.add(new Month("JANUARY",31,"01"));
        mois.add(new Month("FEBRUARY",28,"02"));
        mois.add(new Month("MARCH",31,"03"));
        mois.add(new Month("APRIL",30,"04"));
        mois.add(new Month("MAY",31,"05"));
        mois.add(new Month("JUNE",30,"06"));
        mois.add(new Month("JULY",31,"07"));
        mois.add(new Month("AUGUST",31,"08"));
        mois.add(new Month("SEPTEMBER",30,"09"));
        mois.add(new Month("OCTOBER",31,"10"));
        mois.add(new Month("NOVEMBER",30,"11"));
        mois.add(new Month("DECEMBER",31,"12"));
    }

    public List<Month> getMois() {
        return mois;
    }

    public int getYear() {
        return year;
    }
    public Month findMonth(String name){
        for (Month month : mois){
            if (name.equals(month.getName())){
                return month;
            }
        }
        return null;
    }
    public Month getMonth(int index){
        return mois.get(index);
    }
    public int getIndex(Month month){
        return mois.indexOf(month);
    }
}
