import java.util.*;

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

    /**
     * 顶层的statement函数 现在只剩7行代码，而且它处理的都是与打印详单相关的逻辑
     * <p>
     * 与计算相关的逻辑从主函数中被移走，改由一组函数来支持。每个单独的计算过程和详单的整体结构，都因此变得更易理解了
     *
     * @return
     */
    private static String statement() {
        // 数据处理，进行封装
        StatementData statementData = new StatementData();
        statementData.setCustomer((String) invoicesMap.get("customer"));
        List<Performances> performances = new ArrayList<>();
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
            performances.add(enrichPerformance(perf));
        }
        statementData.setPerformances(performances);
        statementData.setTotalAmount(totalAmount(statementData));
        statementData.setVolumeCredits(totalVolumeCredits(statementData));

        //呈现纯文本
        return renderPlainText(statementData);
    }

    /**
     * 呈现纯文本
     *
     * @return
     */
    private static String renderPlainText(StatementData data) {
        String result = "Statement for " + data.getCustomer() + "\n";
        for (Performances perf : data.getPerformances())
            result += "  " + perf.getPlays().getName() + ": " + perf.getAmount() / 100 + "￥(" + perf.getPerf().getAudience() + " seats)\n";
        result += "Amount owed is " + data.getTotalAmount() / 100 + "￥ \n";
        result += "You earned " + data.getVolumeCredits() + " credits\n";
        return result;
    }

    private static int amountFor(Performances aPerformances) {
        int result = 0;
        switch (aPerformances.getPlays().getType()) {
            case "tragedy":
                result = 40000;
                if (aPerformances.getPerf().getAudience() > 30) {
                    result += 1000 * (aPerformances.getPerf().getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformances.getPerf().getAudience() > 20) {
                    result += 10000 + 500 * (aPerformances.getPerf().getAudience() - 20);
                }
                result += 300 * aPerformances.getPerf().getAudience();
                break;
            default:
                throw new Error("unknown type");
        }
        return result;
    }

    private static Performances enrichPerformance(Invoices perf) {
        Performances result = new Performances();
        result.setPlays(playFor(perf));
        result.setPerf(perf);
        result.setAmount(amountFor(result));
        result.setCredits(volumeCreditsFor(result));
        return result;
    }


    private static Plays playFor(Invoices perf) {
        return playsMap.get(perf.getPlayID());
    }

    private static int volumeCreditsFor(Performances perf) {
        int result = 0;
        result += Math.max(perf.getPerf().getAudience() - 30, 0);
        if ("comedy".equals(perf.getPlays().getType())) {
            result += Math.floor(perf.getPerf().getAudience() / 5);
        }
        return result;
    }

    private static int totalVolumeCredits(StatementData data) {
        int result = 0;
        for (Performances perf : data.getPerformances()) {
            result += perf.getCredits();
        }
        return result;
    }

    private static int totalAmount(StatementData data) {
        int result = 0;
        for (Performances perf : data.getPerformances()) {
            result += perf.getAmount();
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(statement());
    }
}
