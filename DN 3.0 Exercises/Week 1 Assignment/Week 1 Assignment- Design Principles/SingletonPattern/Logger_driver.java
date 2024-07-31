class Logger {
    // Step 1: Create a private static instance of Logger
    private static Logger instance;

    // Step 2: Make the constructor private so that this class cannot be instantiated from outside
    private Logger() {
        // Private constructor to prevent instantiation
    }

    // Step 3: Provide a public static method to get the single instance of the Logger class
    public static Logger getInstance() {
        // Create the instance if it does not exist
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate logging
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
public class Logger_driver {
    public static void main(String[] args) {
        // Get the single instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log some messages
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        // Check if both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 point to the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
    }
}
