import java.util.HashMap;
import java.util.Map;

// Product class definition
class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

// Inventory class definition
class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    // Method to add a product
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        System.out.println("Product added: " + product);
    }

    // Method to update a product
    public void updateProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            products.put(product.getProductId(), product);
            System.out.println("Product updated: " + product);
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    // Method to delete a product
    public void deleteProduct(String productId) {
        if (products.containsKey(productId)) {
            Product removedProduct = products.remove(productId);
            System.out.println("Product removed: " + removedProduct);
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    // Method to get a product
    public Product getProduct(String productId) {
        return products.get(productId);
    }
}

// Main class to demonstrate the usage
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Adding products
        Product product1 = new Product("P001", "Product 1", 10, 100.0);
        Product product2 = new Product("P002", "Product 2", 20, 200.0);
        inventory.addProduct(product1);
        inventory.addProduct(product2);

        // Updating a product
        Product updatedProduct1 = new Product("P001", "Updated Product 1", 15, 150.0);
        inventory.updateProduct(updatedProduct1);

        // Deleting a product
        inventory.deleteProduct("P002");

        // Retrieving a product
        Product retrievedProduct = inventory.getProduct("P001");
        System.out.println("Retrieved Product: " + retrievedProduct);
    }
}
