package demo;

/**
 * 报时的钟
 */
public class Clock {

    // 日期格式化
    private final java.text.SimpleDateFormat clockDateFormat
            = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 状态检查
     */
    final void checkState() {
        throw new IllegalStateException("STATE ERROR!");
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    final java.util.Date now() {
        return new java.util.Date();
    }

    /**
     * 报告时间
     *
     * @return 报告时间
     */
    final String report(int i) {
        checkState();
        return clockDateFormat.format(now());
    }

    /**
     * 循环播报时间
     */
    final void loopReport() throws InterruptedException {
        int i = 1;
        while (true) {
            try {
                System.out.println(report(i));
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
            Thread.sleep(1000);
            i++;
        }
    }

    public static void main(String... args) throws InterruptedException {
        new Clock().loopReport();
    }

}