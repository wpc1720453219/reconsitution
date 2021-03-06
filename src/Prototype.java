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
        //呈现纯文本
        return renderPlainText(createStatementData());
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

    private static String htmlStatement() {
        // 呈现HTML版本
        return renderHtml(createStatementData());
    }

    /**
     * 呈现HTML版本
     *
     * @param data
     * @return
     */
    private static String renderHtml(StatementData data) {
        String result = "<h1>Statement for " + data.getCustomer() + "</h1>\n";
        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>\n";
        for (Performances performances : data.getPerformances()) {
            result += "<tr><td>" + performances.getPlays().getName() + "</td><td>" + performances.getPerf().getAudience() + "</td>";
            result += "<td>" + performances.getAmount() / 100 + "￥</td></tr>\n";
        }
        result += "</table>\n";
        result += "<p>Amount owed is <em>" + data.getTotalAmount() / 100 + "￥</em></p>\n";
        result += "<p>You earned <em>" + data.getVolumeCredits() + "</em> credits</p>\n";
        return result;
    }

    /**
     * 数据处理，进行封装
     *
     * @return
     */
    private static StatementData createStatementData() {
        StatementData statementData = new StatementData();
        statementData.setCustomer((String) invoicesMap.get("customer"));
        List<Performances> performances = new ArrayList<>();
        for (Invoices perf : (List<Invoices>) invoicesMap.get("performances")) {
            performances.add(enrichPerformance(perf));
        }
        statementData.setPerformances(performances);
        statementData.setTotalAmount(totalAmount(statementData));
        statementData.setVolumeCredits(totalVolumeCredits(statementData));
        return statementData;
    }



    private static Performances enrichPerformance(Invoices perf) {
        Performances result = new Performances();
        // 代替 过去的 amountFor方法 用数据结构封装返回
        PerformanceCalculator calculator = createPerformanceCalculator(result, playFor(perf));

        // result 有了数据，但因为calculator 里引用了result 也会回显到PerformanceCalculator里
        result.setPlays(calculator.getPlays());
        result.setPerf(perf);

        result.setAmount(calculator.amount());
        result.setCredits(calculator.volumeCredits());
        return result;
    }


    private static Plays playFor(Invoices perf) {
        return playsMap.get(perf.getPlayID());
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

    /**
     * 代替 过去的 amountFor方法 ，通过多态的性质，再用一个数据结构封装一层
     *
     * @param performances
     * @param plays
     * @return
     */
    private static PerformanceCalculator createPerformanceCalculator(Performances performances, Plays plays) {
        switch (plays.getType()) {
            case "tragedy":
                return new TragedyCalculator(performances, plays);
            case "comedy":
                return new ComedyCalculator(performances, plays);
            default:
                throw new Error("unknwn type:" + plays.getType());
        }
    }


    public static void main(String[] args) {
        System.out.println(statement());
        System.out.println(htmlStatement());
    }
}
