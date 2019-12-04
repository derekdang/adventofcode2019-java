import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://adventofcode.com/2019/day/4
 */
public class aoc4 {
    public static void main(String[] args) {
        int input = 264360;
        int input2 = 746325;
        Set<Integer> ans = new HashSet<>();

        for(int i = input; i <= input2; i++) {
            String s = Integer.valueOf(i).toString();
            char[] ch = s.toCharArray();
            Map<Character, Integer> counts = new HashMap<>();
            boolean adjacent = false;
            for (int x = 0; x < ch.length-1; x++) {
                counts.put(ch[x],counts.getOrDefault(ch[x],0)+1);
                if (ch[x] > ch[x+1]) {
                    adjacent = false;
                    break;
                }

                if (ch[x] == ch[x+1] && !adjacent) {
                    adjacent = true;
                }
            }
            counts.put(ch[ch.length-1],counts.getOrDefault(ch[ch.length-1],0)+1);

            if (adjacent) {
                if (!counts.containsValue(2)) continue; // comment this line to remove constraint from part 2

                ans.add(i);
            }
        }

        // Answer for part 1: 945
        // Answer for part 2: 617
        System.out.printf("Number of valid passwords in range: %d", ans.size());
    }


}
