public class PerformanceCalculator {

    private Performances performances;
    private Plays plays;

    public PerformanceCalculator(Performances performances, Plays plays) {
        this.performances = performances;
        this.plays = plays;
    }

    public int amount() {
        throw new Error("需要继承重写");
    }

    public int volumeCredits() {
        return Math.max(performances.getPerf().getAudience() - 30, 0);
    }

    public Performances getPerformances() {
        return performances;
    }

    public void setPerformances(Performances performances) {
        this.performances = performances;
    }

    public Plays getPlays() {
        return plays;
    }

    public void setPlays(Plays plays) {
        this.plays = plays;
    }
}
