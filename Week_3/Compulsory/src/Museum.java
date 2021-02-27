public class Museum extends Location implements Payable, Visitable{
    private int fee;
    private int openingHour;
    private int endingHour;

    public Museum(String name, int fee, int openingHour, int endingHour) {
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

    public int[] getHoursAvailability() {
        int []output = new int[2];
        output[0] = openingHour;
        output[1] = endingHour;
        return output;
    }
    public void setHoursAvailability(int opening, int ending) {
        openingHour = opening;
        endingHour = ending;
    }
}
