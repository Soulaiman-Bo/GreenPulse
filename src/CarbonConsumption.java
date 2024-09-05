import java.time.LocalDate;

public class CarbonConsumption {

    private int volume;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userId;

    public CarbonConsumption(int volume, LocalDate startDate, LocalDate endDate, String userId) {
        this.volume = volume;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
