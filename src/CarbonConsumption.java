import java.time.LocalDateTime;

public class CarbonConsumption {

    private int volume;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CarbonConsumption(int volume, LocalDateTime startDate, LocalDateTime endDate) {
        this.volume = volume;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
