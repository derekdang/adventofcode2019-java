public class IntcodeComputer {

    public static int evaluate(String instructions, int phaseSetting, int input) {
        String[] split = instructions.split(",");
        int ret = 0;
        boolean firstInputInstr = true;

        for (int i = 0; !split[i].equals("99"); ) {
            String opcode = split[i];
            while (opcode.length() < 5) opcode = '0' + opcode;
            String op = opcode.substring(opcode.length() - 1);

            int destination = (op.equals("3") || op.equals("4")) ? Integer.valueOf(split[i + 1]) : Integer.valueOf(split[i + 3]);
            int param1 = 0;
            int param2 = 0;

            if (!opcode.endsWith("4") && !opcode.endsWith("3")) {
                param1 = opcode.charAt(2) == '0' ? Integer.valueOf(split[Integer.valueOf(split[i + 1])]) : Integer.valueOf(split[i + 1]);
                param2 = opcode.charAt(1) == '0' ? Integer.valueOf(split[Integer.valueOf(split[i + 2])]) : Integer.valueOf(split[i + 2]);
            }

            switch (op) {
                case "1":
                    split[destination] = String.valueOf(param1 + param2);
                    i += 4;
                    break;
                case "2":
                    split[destination] = String.valueOf(param1 * param2);
                    i += 4;
                    break;
                case "3":
                    if (firstInputInstr) {
                        split[destination] = String.valueOf(phaseSetting);
                        firstInputInstr = false;
                    }
                    else split[destination] = String.valueOf(input);
                    i += 2;
                    break;
                case "4":
                    System.out.println(split[destination]);
                    ret = Integer.valueOf(split[destination]);
                    i += 2;
                    break;
                case "5":
                    if (param1 != 0) {
                        i = param2;
                    }
                    else {
                        i += 3;
                    }
                    break;
                case "6":
                    if (param1 == 0) {
                        i = param2;
                    }
                    else {
                        i += 3;
                    }
                    break;
                case "7":
                    split[destination] = param1 < param2 ? "1" : "0";
                    i += 4;
                    break;
                case "8":
                    split[destination] = param1 == param2 ? "1" : "0";
                    i += 4;
                    break;
                case "99":
                    return 0;
            }
        }
        return ret;
    }
}
