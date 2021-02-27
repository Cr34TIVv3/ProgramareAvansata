public class Church extends Location implements Visitable {
    private int openingHour;
    private int endingHour;

    public Church(String name, int openingHour, int endingHour) {
        this.name = name;
        this.openingHour = openingHour;
        this.endingHour = endingHour;
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
