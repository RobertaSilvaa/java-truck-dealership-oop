public abstract class Truck {
    private static int idCounter = 0;
    private double price;
    private int id, year;
    private String model;

    public Truck(double price, int year, String model) {
        this.id = ++idCounter;
        this.price = price;
        this.year = year;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getInfo();
}