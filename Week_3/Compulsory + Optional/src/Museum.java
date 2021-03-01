import java.time.LocalTime;

public class Museum extends Location implements Payable, Visitable{
    private int fee;
    private LocalTime openingHour;
    private LocalTime endingHour;

    public Museum(String name, int fee, LocalTime openingHour, LocalTime endingHour) {
        this.setName(name);
        this.fee = fee;
        this.openingHour = openingHour;
        this.endingHour = endingHour;
    }

    public void setFee(int price) {
        fee = price;
    }
    public int getFee() {
        return fee;
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
