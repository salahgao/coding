import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定字符串找出最长不重复的字符串
 *
 * @author Administrator
 * @date 2021/1/20
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "abcda";
        HashMap map = new HashMap<Integer, List<Character>>();

        List<List<Character>> l = new ArrayList<List<Character>>();
        List list = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (list.contains(c)) {
                l.add(list);
                list = new ArrayList<Character>();
                break;
            }
            list.add(c);
        }
        int maxSize = 0;
        for (List<Character> item : l) {
            int size = item.size();
            if (size > maxSize) {
                maxSize = size;
            }
        }

        for (List<Character> item : l) {
            int size = item.size();
            if (size == maxSize) {
                System.out.println(item.toString());
            }
        }

    }
}
