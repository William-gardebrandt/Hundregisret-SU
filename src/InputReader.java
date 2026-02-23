import java.util.Scanner;

public class InputReader {
    private static final String END_COMMAND = "?>";
    private static final String ERROR = "Error!";

    private static boolean created;
    private Scanner input;


    public InputReader() {
        this(new Scanner(System.in));

    }

    public InputReader(Scanner input) {
        if (created) {
            throw new IllegalStateException(ERROR + "only one inputReader is allowed" + END_COMMAND);
        }
        this.input = input;
        created = true;

    }

    public int useInt(String prompt) {
        System.out.print(prompt + END_COMMAND);
        int value = input.nextInt();
        input.nextLine();

        if (value >= 0) {
            return value;
        }
        do {
            System.out.print(ERROR + "cannot take negative value! \n Try again" + END_COMMAND);
            value = input.nextInt();
            input.nextLine();

        } while (value < 0);
        return value;
    }

    public String useString(String prompt) {
        String value;
        do {
            System.out.print(prompt + END_COMMAND);
            value = input.nextLine().trim();

            if (value.isEmpty()) {
                System.out.print(ERROR + "value cannot be empty" + END_COMMAND);

            }
        } while (value.isEmpty());

        return value;
    }

    public double useDouble(String prompt) {
        System.out.print(prompt + END_COMMAND);
        double value = input.nextDouble();
        input.nextLine();

        if (value >= 0) {
            return value;
        }

        do {
            System.out.print(ERROR + "Cannot take negative value! \n Try again" + END_COMMAND);
            value = input.nextDouble();
            input.nextLine();

        } while (value < 0);
        return value;
    }


}
