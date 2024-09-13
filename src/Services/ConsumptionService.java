package Services;

import Entities.CarbonConsumption;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public abstract class ConsumptionService {

    public abstract Boolean save(CarbonConsumption consumption) throws SQLException ;
    public abstract Optional<CarbonConsumption> find(Integer userId) throws SQLException ;
    public abstract  Boolean update(CarbonConsumption consumption) throws SQLException ;
    public abstract Boolean delete(Integer user) throws SQLException ;
    public abstract Optional<List<CarbonConsumption>> findAll() throws SQLException ;
    public abstract Optional<List<CarbonConsumption>> findAllById(Integer id) throws SQLException ;


    //  public void save(CarbonConsumption consumption) {
//        ConsumptionDAO foodDAO = new FoodDao();
//       try {
//           consumptionDAO.createConsumption(consumption);
//       } catch (SQLException e) {
//           System.out.println(e.getMessage());
//       }

        // check if user exists
        // check if date already exists
 //   }

   // public List<CarbonConsumption> getConsumptions(Integer id) {
       // return consumptionDAO.getAllConsumptions(id);

  //  }


//    public void getTotalConsumptionVolume(String id){
//        List<CarbonConsumption> consumptions = this.getConsumptions(id);
//
//        if (consumptions == null) {
//            System.out.println("No consumptions found for this user");
//        }
//
//        int totalVolume = 0;
//        if (consumptions != null) {
//            for (CarbonConsumption consumption : consumptions) {
//                totalVolume += consumption.getVolume();
//            }
//            System.out.println(totalVolume);
//        }
//
//    }
//
//    public void getConsumptionReportDaily(String id){
//        List<CarbonConsumption> consumptions = this.getConsumptions(id);
//
//        CarbonReportCalculator dailyProcessor = new CarbonReportCalculator();
//
//        Map<LocalDate, Double> dailyConsumption = dailyProcessor.calculateDailyVolumes(consumptions);
//
//        for (Map.Entry<LocalDate, Double> entry : dailyConsumption.entrySet()) {
//            System.out.printf("%s: Daily Consumption: %.2f%n", entry.getKey(), entry.getValue());
//        }
//
//    }
//
//    public void getConsumptionReportMonthly(String id){
//        List<CarbonConsumption> consumptions = this.getConsumptions(id);
//
//        CarbonReportCalculator MonthlyProcessor = new CarbonReportCalculator();
//
//        Map<YearMonth, Double> monthlyConsumption = MonthlyProcessor.calculateMonthlyVolumes(consumptions);
//
//        for (Map.Entry<YearMonth, Double> entry : monthlyConsumption.entrySet()) {
//            System.out.printf("%s: Monthly Consumption: %.2f%n", entry.getKey(), entry.getValue());
//        }
//
//    }
//
//    public void getConsumptionReportWeekly(String id){
//        List<CarbonConsumption> consumptions = this.getConsumptions(id);
//
//        CarbonReportCalculator WeeklyProcessor = new CarbonReportCalculator();
//        Map<Integer, Double> weeklyVolumes =  WeeklyProcessor.calculateWeeklyVolumes(consumptions);
//
//        for (Map.Entry<Integer, Double> entry : weeklyVolumes.entrySet()) {
//            int year = entry.getKey() / 100;
//            int week = entry.getKey() % 100;
//            System.out.printf("Year %d, Week %d: Total Consumption: %.2f%n", year, week, entry.getValue());
//        }
//
//
//    }
//
//    public static List<LocalDate> parseConsumptionIntoDays(List<CarbonConsumption> consumptions) {
//        List<LocalDate> dates = new ArrayList<>();
//        for(CarbonConsumption c : consumptions){
//            for (LocalDate s_date = c.getStartDate(); !s_date.isAfter(c.getEndDate()); s_date = s_date.plusDays(1)){
//                dates.add(s_date);
//            }
//        }
//        return dates;
//    }


}
