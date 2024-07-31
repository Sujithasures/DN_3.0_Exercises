import java.util.Arrays;
import java.util.Comparator;
class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    // Getters
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
class Library {
    private Book[] books;
    private int count;

    public Library(int capacity) {
        books = new Book[capacity];
        count = 0;
    }

    // Method to add a book
    public boolean addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
            return true;
        }
        return false; // Library is full
    }

    // Linear search to find a book by title
    public Book linearSearchByTitle(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null; // Book not found
    }

    // Binary search to find a book by title (assumes the list is sorted)
    public Book binarySearchByTitle(String title) {
        int left = 0;
        int right = count - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Book not found
    }

    // Method to sort books by title (for binary search)
    public void sortBooksByTitle() {
        Arrays.sort(books, 0, count, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library(10);

        // Adding books
        library.addBook(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("B002", "1984", "George Orwell"));
        library.addBook(new Book("B003", "To Kill a Mockingbird", "Harper Lee"));

        // Linear search for a book by title
        System.out.println("Linear Search for '1984':");
        Book book = library.linearSearchByTitle("1984");
        System.out.println(book != null ? book : "Book not found");

        // Sorting books by title for binary search
        library.sortBooksByTitle();

        // Binary search for a book by title
        System.out.println("\nBinary Search for '1984':");
        book = library.binarySearchByTitle("1984");
        System.out.println(book != null ? book : "Book not found");
    }
}
