import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Time {
    private LocalTime time;
    //private LocalDate date;
    private String label;

    LocalTime String_to_Localtime(String time){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime real_time = LocalTime.parse(time, formatter);
            return real_time;
        }

        catch(Exception e){
            System.out.println(" Error !!! ⛔⛔");
            e.printStackTrace();
            return null;
        }


    }

    LocalDate String_to_LocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            LocalDate real_date = LocalDate.parse(date,formatter);
            return real_date;
        }
        catch(Exception e){
            System.out.println(" Error !!! ⛔⛔");
            return null;
        }
    }

    public Time(String  time,String label){
        this.time = String_to_Localtime(time);
        //this.date = String_to_LocalDate(date);
        this.label = label;
    }
    /*public Time(String  time,String date,String label){
        this.time = String_to_Localtime(time);
        //this.date = String_to_LocalDate(date);
        this.label = label;
    }*/

    public LocalTime get_time(){
        return this.time;

    }
    /*public LocalDate get_date(){
        return this.date;
    }*/
    public void set_time(String time){
        this.time = String_to_Localtime(time);
    }
    /*public void set_date(String date){
        this.date= String_to_LocalDate(date);
    }*/
    public String get_label(){
        return this.label;
    }
    public void set_label(String label){
        this.label=label;

    }


}
