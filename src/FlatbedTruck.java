public class FlatbedTruck extends Truck {
    private double platformLength;

    public FlatbedTruck(double price, int year, String model, double platformLength) {
        super(price, year, model);
        this.platformLength = platformLength;
    }

    public double getPlatformLength() {
        return platformLength;
    }

    @Override
    public String getInfo() {
        return "\n\nFlatbed Truck ID: " + getId() + "\n Year: " + getYear() + "\n Model: " + getModel() +
                "\n Platform length: " + getPlatformLength() + "m" + "\n Price: " + getPrice();
    }
}
