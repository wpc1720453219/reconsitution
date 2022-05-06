public class ComedyCalculator extends PerformanceCalculator {

    public ComedyCalculator(Performances performances, Plays plays) {
        super(performances, plays);
    }

    @Override
    public int amount() {
        int result = 30000;
        if (this.performances.getPerf().getAudience() > 20) {
            result += 10000 + 500 * (performances.getPerf().getAudience() - 20);
        }
        result += 300 * perf.getAudience();
        return result;
    }
    @Override
    public int volumeCredits() {
        return super.volumeCredits(perf) + (int) Math.floor(perf.getAudience() / 5);
    }
}
