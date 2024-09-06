package Services;

import Entities.CarbonConsumption;
import Entities.User;
import Utils.CarbonReportCalculator;
import Utils.ConsolePrinter;
import Utils.DateUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public String createAccount(String name, int age) {
        Random random = new Random();
        int randomId = 1000 + random.nextInt(9000);  // Generates a random 4-digit number between 1000 and 9999
        User user = new User(name, age, Integer.toString(randomId));
        users.put(user.getId(), user);
        return user.getId();
    }

    public void modifyAccount(String id, String name, int age){
        User user = users.get(id);
        if (user != null) {
            user.setName(name);
            user.setAge(age);
            System.out.println("Account modified successfully.");
        } else {
            System.out.println("Entities.User not found.");
        }
    }

    public void deleteAccount(String id) {
        if (users.remove(id) != null) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Entities.User not found.");
        }
    }

    public void displayAllAccounts() {
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void addCarbonConsumption(String id, CarbonConsumption consumption) {
        User user = users.get(id);

        List<LocalDate> rangeDates =  parseConsumptionIntoDays(getConsumptions(id));

        if(DateUtils.verifyDateExistence(
               consumption.getStartDate(),
                consumption.getEndDate(),
                rangeDates))
        {
            user.getCarbonConsumption().add(consumption);

        }else {
            ConsolePrinter.printError("Date Already Exists.");
        }
    }

    public List<CarbonConsumption> getConsumptions(String id) {
        User user = users.get(id);
        return user.getCarbonConsumption();
    }

    public void getTotalConsumptionVolume(String id){
        List<CarbonConsumption> consumptions = this.getConsumptions(id);

        if (consumptions == null) {
            System.out.println("No consumptions found for this user");
        }

        int totalVolume = 0;
        if (consumptions != null) {
            for (CarbonConsumption consumption : consumptions) {
                totalVolume += consumption.getVolume();
            }
            System.out.println(totalVolume);
        }

    }

    public void getConsumptionReportDaily(String id){
        List<CarbonConsumption> consumptions = this.getConsumptions(id);

        CarbonReportCalculator dailyProcessor = new CarbonReportCalculator();

        Map<LocalDate, Double> dailyConsumption = dailyProcessor.calculateDailyVolumes(consumptions);

        for (Map.Entry<LocalDate, Double> entry : dailyConsumption.entrySet()) {
            System.out.printf("%s: Daily Consumption: %.2f%n", entry.getKey(), entry.getValue());
        }

    }

    public void getConsumptionReportMonthly(String id){
        List<CarbonConsumption> consumptions = this.getConsumptions(id);

        CarbonReportCalculator MonthlyProcessor = new CarbonReportCalculator();

        Map<YearMonth, Double> monthlyConsumption = MonthlyProcessor.calculateMonthlyVolumes(consumptions);

        for (Map.Entry<YearMonth, Double> entry : monthlyConsumption.entrySet()) {
            System.out.printf("%s: Monthly Consumption: %.2f%n", entry.getKey(), entry.getValue());
        }

    }

    public void getConsumptionReportWeekly(String id){
        List<CarbonConsumption> consumptions = this.getConsumptions(id);

        CarbonReportCalculator WeeklyProcessor = new CarbonReportCalculator();
        Map<Integer, Double> weeklyVolumes =  WeeklyProcessor.calculateWeeklyVolumes(consumptions);

        for (Map.Entry<Integer, Double> entry : weeklyVolumes.entrySet()) {
            int year = entry.getKey() / 100;
            int week = entry.getKey() % 100;
            System.out.printf("Year %d, Week %d: Total Consumption: %.2f%n", year, week, entry.getValue());
        }


    }

    public boolean userExists(String id) {
        return users.containsKey(id);
    }

    public static List<LocalDate> parseConsumptionIntoDays(List<CarbonConsumption> consumptions) {
        List<LocalDate> dates = new ArrayList<>();
        for(CarbonConsumption c : consumptions){
            for (LocalDate s_date = c.getStartDate(); !s_date.isAfter(c.getEndDate()); s_date = s_date.plusDays(1)){
                dates.add(s_date);
            }
        }
        return dates;
    }

}
