import java.time.LocalTime;

public class Church extends Location implements Visitable {
    private LocalTime openingHour;
    private LocalTime endingHour;

    public Church(String name, LocalTime openingHour, LocalTime endingHour) {
        this.name = name;
        this.openingHour = openingHour;
        this.endingHour = endingHour;
    }

    public LocalTime[] getHoursAvailability() {
        LocalTime []output = new LocalTime[2];
        output[0] = openingHour;
        output[1] = endingHour;
        return output;
    }
    public void setHoursAvailability(LocalTime openingTime, LocalTime closingTime) {
        this.openingHour = openingTime;
        this.endingHour = closingTime;
    }
}
