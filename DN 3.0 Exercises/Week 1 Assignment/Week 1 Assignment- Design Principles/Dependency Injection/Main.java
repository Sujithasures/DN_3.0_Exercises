
public class Main {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepository);
        int customerId = 1;
        String customerName = customerService.getCustomerName(customerId);
        System.out.println("Customer Name for ID " + customerId + ": " + customerName);
    }
}

