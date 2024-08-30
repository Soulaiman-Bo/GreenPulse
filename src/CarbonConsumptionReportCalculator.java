import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;

public class CarbonConsumptionReportCalculator {

    public Map<LocalDate, Double> calculateDailyVolumes(List<CarbonConsumption> consumptions) {
        Map<LocalDate, Double> dailyVolumes = new TreeMap<>();

        for (CarbonConsumption consumption : consumptions) {
            LocalDate startDate = consumption.getStartDate().toLocalDate();
            LocalDate endDate = consumption.getEndDate().toLocalDate();
            long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            double dailyVolume = (double) consumption.getVolume() / days;

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                dailyVolumes.merge(date, dailyVolume, Double::sum);
            }
        }

        return dailyVolumes;
    }

    public Map<YearMonth, Double> calculateMonthlyVolumes(List<CarbonConsumption> consumptions) {
        Map<YearMonth, Double> monthlyVolumes = new TreeMap<>();

        for (CarbonConsumption consumption : consumptions) {
            LocalDateTime startDate = consumption.getStartDate();
            LocalDateTime endDate = consumption.getEndDate();
            double totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            double dailyVolume = consumption.getVolume() / totalDays;

            YearMonth currentMonth = YearMonth.from(startDate);
            YearMonth endMonth = YearMonth.from(endDate);

            while (!currentMonth.isAfter(endMonth)) {
                LocalDate monthStart = currentMonth.atDay(1).isAfter(ChronoLocalDate.from(startDate)) ? currentMonth.atDay(1) : LocalDate.from(startDate);
                LocalDate monthEnd = currentMonth.atEndOfMonth().isBefore(ChronoLocalDate.from(endDate)) ? currentMonth.atEndOfMonth() : LocalDate.from(endDate);

                long daysInMonth = ChronoUnit.DAYS.between(monthStart, monthEnd) + 1;
                double monthlyVolume = dailyVolume * daysInMonth;

                monthlyVolumes.merge(currentMonth, monthlyVolume, Double::sum);
                currentMonth = currentMonth.plusMonths(1);
            }
        }

        return monthlyVolumes;
    }

    public Map<Integer, Double> calculateWeeklyVolumes(List<CarbonConsumption> consumptions){
        Map<Integer, Double> weeklyConsumption = new TreeMap<>();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        for (CarbonConsumption consumption : consumptions) {
            LocalDate startDate = consumption.getStartDate().toLocalDate();
            LocalDate endDate = consumption.getEndDate().toLocalDate();
            double totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            double dailyConsumption = consumption.getVolume() / totalDays;

            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)){
                int weekNumber = currentDate.get(weekFields.weekOfYear());
                int year = currentDate.getYear();
                int weekKey = year * 100 + weekNumber;

                double daysInCurrentWeek = 1;
                LocalDate weekEnd = currentDate.with(weekFields.dayOfWeek(), 7);
                if (weekEnd.isAfter(endDate)) {
                    weekEnd = endDate;
                }
                daysInCurrentWeek = ChronoUnit.DAYS.between(currentDate, weekEnd) + 1;

                double weekConsumption = dailyConsumption * daysInCurrentWeek;
                weeklyConsumption.merge(weekKey, weekConsumption, Double::sum);

                currentDate = weekEnd.plusDays(1);

            }

        }
            return weeklyConsumption;
    }

}
