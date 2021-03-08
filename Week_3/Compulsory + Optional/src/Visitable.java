import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {
    public void setHoursAvailability(LocalTime openingTime, LocalTime closingTime);
    public LocalTime[] getHoursAvailability();
    default void setHoursAvailability() {
        setHoursAvailability(LocalTime.of(9,30), LocalTime.of(20,0));
    }
    static Duration getVisitingDuration(LocalTime openingTime, LocalTime closingTime) {
        return Duration.between(openingTime, closingTime);
    }
}
