public class TragedyCalculator extends PerformanceCalculator {

    public TragedyCalculator(Performances performances, Plays plays) {
        super(performances, plays);
    }

    @Override
    public int amount() {
        int result = 40000;
        if (super.getPerformances().getPerf().getAudience() > 30) {
            result += 1000 * (super.getPerformances().getPerf().getAudience() - 30);
        }
        return result;
    }
}
