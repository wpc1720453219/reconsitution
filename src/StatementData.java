import java.util.List;

/**
 * statement 数据
 */
public class StatementData {
    private String customer;
    private Integer totalAmount;
    private Integer volumeCredits;
    private List<Performances> performances;

    public StatementData(String customer, Integer totalAmount, Integer volumeCredits, List<Performances> performances) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.volumeCredits = volumeCredits;
        this.performances = performances;
    }

    public StatementData() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getVolumeCredits() {
        return volumeCredits;
    }

    public void setVolumeCredits(Integer volumeCredits) {
        this.volumeCredits = volumeCredits;
    }

    public List<Performances> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performances> performances) {
        this.performances = performances;
    }
}
