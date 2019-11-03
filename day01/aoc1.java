import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class aoc1 {

    public static void main(String[] args) {
        String fileName = args[0];
        int sum = 0;
        int p2 = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null;) {
                int i = Integer.valueOf(line);
                int x = i;

                // part 1 calculation
                i /= 3;
                i -= 2;
                sum += i;

                // part 2 calculation
                while (x / 3 - 2 > 0) {
                    x = x/3;
                    x -= 2;
                    p2 += x;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Answer for part 1: 3415076
        System.out.printf("Sum of fuel requirements: %d\n", sum);

        // Answer for part 2: 5119745
        System.out.printf("Sum of fuel requirements for part 2: %d", p2);
    }
}
