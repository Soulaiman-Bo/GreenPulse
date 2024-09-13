package Utils;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public static List<LocalDate> dateListRange(LocalDate startDate , LocalDate endDate){
        List<LocalDate> dateListRange = new ArrayList<>();
        for(LocalDate dateTest = startDate; !dateTest.isAfter(endDate); dateTest=dateTest.plusDays(1)){
            dateListRange.add(dateTest);
        }
        return dateListRange;
    }


}
