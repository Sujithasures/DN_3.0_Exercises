
public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromServer();
    }

    private void loadImageFromServer() {
        System.out.println("Loading image from server: " + filename);
        // Simulate a delay in loading the image
        try {
            Thread.sleep(2000); // Simulating delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Image loaded: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

