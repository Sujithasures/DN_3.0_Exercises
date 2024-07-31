import java.util.Arrays;
import java.util.Comparator;

// Product class definition
class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

// Linear Search class definition
class LinearSearch {
    public static Product linearSearch(Product[] products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null; // Product not found
    }
}

// Binary Search class definition
class BinarySearch {
    public static Product binarySearch(Product[] products, String productName) {
        // Ensure the array is sorted
        Arrays.sort(products, Comparator.comparing(Product::getProductName));

        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareToIgnoreCase(productName);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Product not found
    }
}

// Main class to demonstrate the search algorithms
public class ECommerceSearch {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Smartphone", "Electronics"),
            new Product("P003", "Book", "Literature"),
            new Product("P004", "Headphones", "Electronics"),
            new Product("P005", "Coffee Maker", "Home Appliances")
        };

        // Linear Search
        Product foundProductLinear = LinearSearch.linearSearch(products, "earbuds");
        System.out.println("Linear Search Result: " + (foundProductLinear != null ? foundProductLinear : "Product not found"));

        // Binary Search
        Product foundProductBinary = BinarySearch.binarySearch(products, "Smartphone");
        System.out.println("Binary Search Result: " + (foundProductBinary != null ? foundProductBinary : "Product not found"));
    }
}
