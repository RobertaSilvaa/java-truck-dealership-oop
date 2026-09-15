public class CarCarrier extends Truck {
    private int capacity;

    public CarCarrier(double price, int year, String model, int capacity) {
        super(price, year, model);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getInfo() {
        return "\n\nCar Carrier ID: " + getId() + "\n Year: " + getYear() + "\n Model: " + getModel() +
                "\n Car capacity: " + getCapacity() + "\n Price: " + getPrice();
    }
}
