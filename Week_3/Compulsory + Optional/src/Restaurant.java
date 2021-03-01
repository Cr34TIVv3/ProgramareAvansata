import java.time.LocalTime;

public class Restaurant extends Location implements Classifiable, Visitable {
    private int rank;
    private LocalTime openingHour;
    private LocalTime endingHour;

    public Restaurant(String name, int rank, LocalTime openingHour, LocalTime endingHour) {
        this.setName(name);
        this.rank = rank;
        this.openingHour = openingHour;
        this.endingHour = endingHour;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
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
