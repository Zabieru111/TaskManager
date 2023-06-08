package zabieru.taskmanager;

public class Month {
    private String name;
    private int days;
    private String number;

    public Month(String name, int days,String number) {
        this.name = name;
        this.days = days;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }
    public String getNumber(){
        return number;
    }
}
