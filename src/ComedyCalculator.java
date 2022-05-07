public class ComedyCalculator extends PerformanceCalculator {

    public ComedyCalculator(Performances performances, Plays plays) {
        super(performances, plays);
    }

    @Override
    public int amount() {
        int result = 30000;
        if (super.getPerformances().getPerf().getAudience() > 20) {
            result += 10000 + 500 * (super.getPerformances().getPerf().getAudience() - 20);
        }
        result += 300 * super.getPerformances().getPerf().getAudience();
        return result;
    }

    @Override
    public int volumeCredits() {
        return super.volumeCredits() + (int) Math.floor(super.getPerformances().getPerf().getAudience() / 5);
    }
}
