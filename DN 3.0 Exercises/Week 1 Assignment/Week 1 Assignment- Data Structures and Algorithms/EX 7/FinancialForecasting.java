public class FinancialForecasting {

    // Recursive method to calculate future value
    public static double calculateFutureValueRecursive(double initialValue, double growthRate, int periods) {
        if (periods <= 0) {
            return initialValue; // Base case: No periods left to forecast
        }
        return calculateFutureValueRecursive(initialValue * (1 + growthRate), growthRate, periods - 1);
    }

    // Iterative method to calculate future value
    public static double calculateFutureValueIterative(double initialValue, double growthRate, int periods) {
        double futureValue = initialValue;
        for (int i = 0; i < periods; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double initialValue = 1000; // Initial amount
        double growthRate = 0.05; // 5% growth rate
        int periods = 10; // Number of periods (years, months, etc.)

        // Calculating future value using the recursive method
        double futureValueRecursive = calculateFutureValueRecursive(initialValue, growthRate, periods);
        System.out.printf("Future Value (Recursive): $%.2f\n", futureValueRecursive);

        // Calculating future value using the iterative method
        double futureValueIterative = calculateFutureValueIterative(initialValue, growthRate, periods);
        System.out.printf("Future Value (Iterative): $%.2f\n", futureValueIterative);
    }
}
