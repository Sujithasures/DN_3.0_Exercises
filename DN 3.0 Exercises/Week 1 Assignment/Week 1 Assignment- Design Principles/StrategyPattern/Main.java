
public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234567890123456", "John Doe", "123", "12/24");
        context.setPaymentStrategy(creditCardPayment);
        context.pay(250.00);
        PaymentStrategy paypalPayment = new PayPalPayment("john.doe@example.com", "password123");
        context.setPaymentStrategy(paypalPayment);
        context.pay(150.00);
    }
}
