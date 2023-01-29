import java.util.ArrayList;
class Train {
    private String _name;
    private Engine _engine;
    ArrayList<FreightCar> freightCars;

    public Train(String name, Engine engine) {
        _name = name;
        _engine = engine;
        freightCars = new ArrayList<>();
    }

    public String getName() {
        return _name;
    }

    public void addFreightCar(FreightCar freightCar) {
        freightCars.add(freightCar);
    }

    public void deleteFreightCar(int ID) {
        FreightCar freightCar = null;
        for(FreightCar car : freightCars) {
            if(car.getID() == ID) {
                freightCar = car;
                freightCars.remove(car);
            }
        }
        if(freightCar == null) {
            System.out.println("Freight car not found...");
        }
    }

    public void setLoadFactorFreightCar(int ID, double loadFactor) {
        FreightCar freightCar = null;
        for(FreightCar car : freightCars) {
            if( car.getID() == ID) {
                freightCar = car;
                car.setLoadFactor(loadFactor);
            }
        }
        if(freightCar == null) {
            System.out.println("Freight car not found...");
        }
    }

    public double computeWeight() {
        double totalWeight = _engine.getWeight();
        for(FreightCar car : freightCars) {
            totalWeight += car.computeWeight();
        }
        return totalWeight;
    }
    public double computeValue() {
        double totalValue = 0;
        for(FreightCar car : freightCars) {
            totalValue += car.computeValue();
        }
        return totalValue;
    }

    public void displayBriefSummary(double maximumLoad){
        int counter = 1;
        boolean aboveMaxLoad = false;

        System.out.printf("%-21s%-21s%-17s%n", "ID", "Weight", "Value");
        for(FreightCar car : freightCars) {
            System.out.printf(counter +".) %-17d%-17s%-17s%n", car.getID(),
                    car.getWeight(), car.computeValue());
            counter++;
            if(car.getWeight() > maximumLoad) aboveMaxLoad = true;
        }
        if(aboveMaxLoad)
            System.out.println("**FREIGHT CAR(S) IS/ARE ABOVE MAXIMUM LOAD**");
    }

    public void displayTotals(double pullingCapacity){
        int totalCars = 0;
        int counter = 1;

        System.out.printf("%-25s%-25s%n","Total Weight", "Total Value");
        System.out.printf("%.3f%.2f%n",computeWeight(), computeValue());
        for(FreightCar car : freightCars) {
            totalCars++;
        }
        System.out.println("There is a total of " + totalCars + " freight " +
                "car(s) attached to train");
    }

    @Override
    public String toString() {
        String s = "\t";
        for(FreightCar car : freightCars) {
            s += "\t" + car + "\n";
        }

        return
                "\nName = " + _name +
                        "\nEngine : " + _engine +
                        "\nFreightCars : " + "\t" +s;
    }
}
