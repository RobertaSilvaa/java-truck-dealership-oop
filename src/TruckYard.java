import java.util.List;
import java.util.ArrayList;

public class TruckYard {
    private List<Truck> trucks = new ArrayList<>();

    public void addTruck(Truck truck) throws Exception {
        if (trucks.size() == 5) {
            throw new TruckYardException("The truck yard is full!");
        }
        trucks.add(truck);
    }

    public void removeTruck(int id) throws TruckYardException {
        try {
            if (trucks.isEmpty()) {
                throw new TruckYardException("The truck yard is empty!");
            }

            boolean removed = false;
            for (int i = 0; i < trucks.size(); i++) {
                if (trucks.get(i).getId() == id) {
                    trucks.remove(i);
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                throw new TruckYardException("Truck with ID " + id + " not found.");
            }

        } catch (TruckYardException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printTrucks() {
        for (Truck truck : trucks) {
            truck.getInfo();
        }
    }

    public List<Truck> getTrucks() {
        return trucks;
    }
}