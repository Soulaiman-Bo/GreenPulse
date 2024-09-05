package Utils;

import java.time.LocalDate;
import java.util.List;

public class DateUtils {
    public static boolean verifyDateExistence(LocalDate startDate, LocalDate endDate, List<LocalDate> range) {
        for (LocalDate s_date = startDate; !s_date.isAfter(endDate); s_date = s_date.plusDays(1)) {
            if(range.contains(s_date)){
                return false;
            }
        }
        return true;
    }
}
