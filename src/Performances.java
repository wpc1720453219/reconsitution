public class Performances {
    private Invoices perf;
    private Plays plays;
    private Integer amount;
    private Integer credits;

    public Performances(Invoices perf, Plays plays, Integer amount, Integer credits) {
        this.perf = perf;
        this.plays = plays;
        this.amount = amount;
        this.credits = credits;
    }

    public Performances() {
    }

    public Invoices getPerf() {
        return perf;
    }

    public void setPerf(Invoices perf) {
        this.perf = perf;
    }

    public Plays getPlays() {
        return plays;
    }

    public void setPlays(Plays plays) {
        this.plays = plays;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}

