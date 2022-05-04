/**
 * (账单实体类)
 */
public class Invoices {
    private String playID;
    private Integer audience;

    public Invoices(String playID, Integer audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(String playID) {
        this.playID = playID;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }
}
