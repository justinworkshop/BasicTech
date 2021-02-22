package com.example.junit;

        import java.util.Calendar;
        import java.util.Date;

/**
 * Created by zhengwei on 2021/2/21.
 */
public class DateUtil {

    public void getDate() {
        System.out.println("Date: " + new Date().toString());
    }

    public void getYear() {
        System.out.println("Year: " + Calendar.getInstance().get(Calendar.YEAR));
    }

    public void getMonth() {
        System.out.println("Month: " + Calendar.getInstance().get(Calendar.MONTH));
    }

    public int getValue() {
        return 100;
    }
}
