public class aoc2 {

    public static void main(String[] args) {
        String input = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,1,19,5,23,2,9,23,27,1,5,27,31,1,5,31,35,1,35,13,39,1,39,9,43,1,5,43,47,1,47,6,51,1,51,13,55,1,55,9,59,1,59,13,63,2,63,13,67,1,67,10,71,1,71,6,75,2,10,75,79,2,10,79,83,1,5,83,87,2,6,87,91,1,91,6,95,1,95,13,99,2,99,13,103,1,103,9,107,1,10,107,111,2,111,13,115,1,10,115,119,1,10,119,123,2,13,123,127,2,6,127,131,1,13,131,135,1,135,2,139,1,139,6,0,99,2,0,14,0";

        String[] split = input.split(",");

        findInput(input);
        split[1] = "12";
        split[2] = "2";
        for (int i = 0; !split[i].equals("99"); i+=4) {
            int operand1 = Integer.valueOf(split[Integer.valueOf(split[i+1])]);
            int operand2 = Integer.valueOf(split[Integer.valueOf(split[i+2])]);
            int destination = Integer.valueOf(split[i+3]);
            int result;

            if (split[i].equals("1")) {
                result = operand1 + operand2;
            } else { // Opcode is 2, so multiply
                result = operand1 * operand2;
            }

            split[destination] = String.valueOf(result);
        }

        // Answer for part 1: 4090689
        System.out.printf("Value at position 0: %s", split[0]);
    }

    private static void findInput(String input) {
        String[] split = input.split(",");
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                split[1] = String.valueOf(i);
                split[2] = String.valueOf(j);

                for (int x = 0; !split[x].equals("99"); x += 4) {
                    int operand1 = Integer.valueOf(split[Integer.valueOf(split[x + 1])]);
                    int operand2 = Integer.valueOf(split[Integer.valueOf(split[x + 2])]);
                    int destination = Integer.valueOf(split[x + 3]);
                    int result;

                    if (split[x].equals("1")) {
                        result = operand1 + operand2;
                    } else { // Opcode is 2, so multiply
                        result = operand1 * operand2;
                    }

                    split[destination] = String.valueOf(result);
                    if (result == 19690720)
                    {
                        int ans = Integer.valueOf(split[1]) * 100 + Integer.valueOf(split[2]);
                        // Answer for part 2: 7733, noun is 77, verb is 33
                        System.out.printf("Answer for part 2: %d\n", ans);
                        return;
                    }
                }
                split = input.split(",");
            }
        }
    }
}
