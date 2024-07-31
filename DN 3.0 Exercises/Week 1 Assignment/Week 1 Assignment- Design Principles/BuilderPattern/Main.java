// Main.java
public class Main {
    public static void main(String[] args) {
        // Create a basic computer
        Computer basicComputer = new Computer.Builder()
            .setCPU("hp i5")
            .setRAM("8GB")
            .setStorage("300GB SSD")
            .build();

        System.out.println("Basic Computer: ");
        System.out.println("CPU: " + basicComputer.getCPU());
        System.out.println("RAM: " + basicComputer.getRAM());
        System.out.println("Storage: " + basicComputer.getStorage());

        // Create a gaming computer
        Computer gamingComputer = new Computer.Builder()
            .setCPU("Intel i9")
            .setRAM("32GB")
            .setStorage("1TB SSD")
            .setGraphicsCard("NVIDIA RTX 3080")
            .setOperatingSystem("Windows 11")
            .build();

        System.out.println("\nGaming Computer: ");
        System.out.println("CPU: " + gamingComputer.getCPU());
        System.out.println("RAM: " + gamingComputer.getRAM());
        System.out.println("Storage: " + gamingComputer.getStorage());
        System.out.println("Graphics Card: " + gamingComputer.getGraphicsCard());
        System.out.println("Operating System: " + gamingComputer.getOperatingSystem());

        // Create a workstation computer
        Computer workstationComputer = new Computer.Builder()
            .setCPU("AMD Ryzen 9")
            .setRAM("64GB")
            .setStorage("2TB NVMe SSD")
            .setGraphicsCard("AMD Radeon Pro")
            .setOperatingSystem("Linux")
            .build();

        System.out.println("\nWorkstation Computer: ");
        System.out.println("CPU: " + workstationComputer.getCPU());
        System.out.println("RAM: " + workstationComputer.getRAM());
        System.out.println("Storage: " + workstationComputer.getStorage());
        System.out.println("Graphics Card: " + workstationComputer.getGraphicsCard());
        System.out.println("Operating System: " + workstationComputer.getOperatingSystem());
    }
}
