import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prototype {
    static Map<String, Plays> playsMap = new HashMap<String, Plays>();
    static Map<String, Object> invoicesMap = new HashMap<String, Object>();

    static {
        //构造剧目数据
        playsMap.put("hamlet", new Plays("hamlet", "tragedy"));
        playsMap.put("as-like", new Plays("as-like", "comedy"));
        playsMap.put("othello", new Plays("othello", "tragedy"));

        //构造账单数据
        invoicesMap.put("customer", "BigCo");
        List<Invoices> invoicesList = Arrays.asList(
                new Invoices("hamlet", 55),
                new Invoices("as-like", 35),
                new Invoices("othello", 40));
        invoicesMap.put("performances", invoicesList);

    }

    private static String statement() {
        int totalAmount = 0;
        String result = "Statement for " + invoicesMap.get("customer") + "\n";
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
            result += "  " + playFor(perf).getName() + ": " + amountFor(perf) / 100 + "￥(" + perf.getAudience() + " seats)\n";
            totalAmount += amountFor(perf);
        }

        int volumeCredits = 0;
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
            volumeCredits += volumeCreditsFor(perf);
        }

        result += "Amount owed is " + totalAmount / 100 + "￥ \n";
        result += "You earned " + volumeCredits + " credits\n";
        return result;
    }

    private static int amountFor(Invoices perf) {
        int result = 0;
        switch (playFor(perf).getType()) {
            case "tragedy":
                result = 40000;
                if (perf.getAudience() > 30) {
                    result += 1000 * (perf.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.getAudience() > 20) {
                    result += 10000 + 500 * (perf.getAudience() - 20);
                }
                result += 300 * perf.getAudience();
                break;
            default:
                throw new Error("unknown type");
        }
        return result;
    }

    private static Plays playFor(Invoices perf) {
        return playsMap.get(perf.getPlayID());
    }

    private static int volumeCreditsFor(Invoices perf) {
        int result = 0;
        result += Math.max(perf.getAudience() - 30, 0);
        if ("comedy".equals(playFor(perf).getType())) {
            result += Math.floor(perf.getAudience() / 5);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(statement());
    }
}
