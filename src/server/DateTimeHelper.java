package server;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public interface DateTimeHelper {
    private int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.get(Calendar.DAY_OF_WEEK);
    }

    default BigDecimal getDateRatio(Date date) {
        if (this.getDay(date) == Calendar.MONDAY || this.getDay(date) == Calendar.TUESDAY || this.getDay(date) == Calendar.WEDNESDAY) {
            return PriceRatio.LOW.getRatio();
        } else if (this.getDay(date) == Calendar.THURSDAY) {
            return PriceRatio.MEDIUM.getRatio();
        }

        return PriceRatio.HIGH.getRatio();
    }
}
