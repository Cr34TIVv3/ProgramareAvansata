public class Restaurant extends Location implements Classifiable, Visitable {
    private int rank;
    private int openingHour;
    private int endingHour;

    public Restaurant(String name, int rank, int openingHour, int endingHour) {
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
