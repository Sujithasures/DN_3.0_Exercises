
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // For simplicity, returning a dummy customer name based on ID
        return "Customer" + id;
    }
}
