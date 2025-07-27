import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Alarm {
    static Vector<Time> time_store= new Vector<>();
    //static FileOutputStream file = new FileOutputStream("alarm.ser");
    private static long get_delay(LocalTime time){

        LocalTime current = LocalTime.now();
        long Delay = Duration.between(current,time).getSeconds();
        if(Delay <0){
            Delay += 24*60*60;
        }
        return Delay;

    }
    static void set_alarm(Time time){
        LocalTime alarmtime = LocalTime.of(time.get_time().getHour(),time.get_time().getMinute());
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable alarm_task=()->{

            System.out.println("\n");
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("HH:mm:ss ");
            LocalTime time_1 = LocalTime.now();
            String str_time = time_1.format(formatter);

            System.out.println("⏰ Alarm is Ringning...."+ str_time );

        };
        long perioud = 24*60*60;
        long initial_delay = get_delay(alarmtime);
        Duration duration = Duration.ofSeconds(initial_delay);
        long hours = duration.toHours();
        long minitue = duration.toMinutesPart();
        long second = duration.toSecondsPart();
        System.out.println("⏰ Alarm will ring at "+ time.get_time());
        System.out.println("⌛"+hours+":"+minitue+":"+second+" to go...");

        scheduler.scheduleAtFixedRate(alarm_task,initial_delay,perioud, TimeUnit.SECONDS);





    }
    static void  add_alarm(){
        Scanner scan = new Scanner(System.in);
        System.out.println("⏰ Set the Time (HH:mm) : ");
        String time = scan.nextLine();
        //System.out.println("📅 Set the Date (dd/MM/yyyy) : ");
        //String date =  scan.nextLine();
        System.out.println("💭 Set the Label (Unless set null) : ");
        String label = scan.nextLine();
        Time obj = new  Time(time,label);
        time_store.add(obj);
        set_alarm(obj);


    }
    static void view(){
        for(Time time : time_store){
            System.out.println(" Alarm "+time.get_time()+" : "  + time.get_label());


        }


    }
    static void slow_down(int time){
        try {

            Thread.sleep(time);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    static void main_menu(){
        System.out.print("\n");
        System.out.println("📌 ADD TASK : Enter 1 ");
        //System.out.println("🗑 DELETE TASK : Enter 2 ");
        //System.out.println("📅 VIEW TASK : Enter 3 ");
        System.out.println("⚙ QUIT : Enter -1 ");
        System.out.print("\n");


    }
    static void welcome(){
        System.out.println("............Hello Set your Alarm ,Achieve the Goals 👁‍🗨........");
        for(int i=0;i<3;i++){
            System.out.print("🔴");
            slow_down(500);
            System.out.print("🟠");
            slow_down(300);
            System.out.print("🟡");
            slow_down(200);
            System.out.print("🟢");
            slow_down(100);

        }
        System.out.print("\n");
        LocalDate today= LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("hh:mm:ss a");
        String str_time = time.format(formatter);

        System.out.println("📅 Today is : " + today +" : " +day);
        System.out.println("➡ Log In at : "+str_time);
        slow_down(1000);
        System.out.print("\n");


    }
    static void delete(){

    }
    static void serelization(FileOutputStream file,Time time){
        try {
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(time);
            out.close();
            file.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }




    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        slow_down(30);
        welcome();
        slow_down(2000);
        while(true) {
            main_menu();
            try {
                System.out.print(" Command : ");
                Integer input = scan.nextInt();
                switch (input) {
                    case 1:
                        add_alarm();
                        break;


                    case -1:
                        System.out.println("GOOD BYE 😘👋 ");
                        return;

                }


            } catch (Exception e) {
                System.out.println("Invalid Input try Again ");

            }

        }







    }





}
