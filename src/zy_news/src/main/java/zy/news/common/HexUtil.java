package zy.news.common;

/**
 * @author fanpei
 */
public class HexUtil {
    /**
     * 16进制字符
     */
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F'};

    public static String toHexWithOX(int n, int resultLen) {
        if (n < 0) {
            n += 256;
        }
        String hexStr = Integer.toHexString(n).toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0X");
        if (hexStr.length() < resultLen) {
            int caiV = resultLen - hexStr.length();
            for (int i = 0; i < caiV; i++) {
                stringBuilder.append('0');
            }
        }
        stringBuilder.append(hexStr);
        return stringBuilder.toString();
    }
    public  static String toHexWithOX(int n)
    {
        if (n < 0) {
            n += 256;
        }
        String hexStr = Integer.toHexString(n).toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0X");
        stringBuilder.append(hexStr);
        return stringBuilder.toString();
    }
}
