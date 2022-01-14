package server;

import java.math.BigDecimal;
import java.util.Calendar;

public class MovieFactory {

    private static int counter = 0;

    private static Calendar calendar = Calendar.getInstance();

     public static Movie getMovie(){
        calendar.set(0,0,0,2,12);
        return new Movie(counter++ + " title", 13, "string", calendar, BigDecimal.valueOf(12,10), "person" );
    }
}
