// Author: Samer Ali
// Description: This program helps a small feeder line decide which
// cars to include in each trip. In addition, the
// program lets the customer try out different
//combinations of engine and cars and then computes the total
// value and weight of each combination.
package Program$5;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        final double maximumLoad;
        String name;
        System.out.println("Hello, this is a program helps you decide" +
                " which cars to include in each trip. In addition, the program " +
                "will assist you to try different combinations of engine " +
                "and cars " +
                "that would then be given their total weight and value " +
                "(for each combination)");
        System.out.println("to begin, please enter the maximum load for " +
                "a single car: ");
        maximumLoad = keyboard.nextDouble();
        keyboard.nextLine();

        char selection;
        do {
            System.out.println("What is the name of the engineer?");
            name = keyboard.nextLine();
            System.out.println("Enter pulling capacity of the engine:");
            double pullingCapacity = keyboard.nextInt();
            System.out.println("Enter the ID:");
            int ID = keyboard.nextInt();
            System.out.println("Enter weight of base frame (that includes " +
                    "the wheels): ");
            int weight = keyboard.nextInt();
            Engine engine = new Engine(pullingCapacity, name, ID, weight);
            Train train = new Train(name, engine);
            do {
                System.out.println("Select any of the following options to " +
                        "continue:\n");
                printMenu();
                selection = keyboard.next().charAt(0);

                menuAction(selection, train, pullingCapacity, maximumLoad);
            }while(selection != 'e' && selection != 'f');
        }while(selection == 'e');
    }
    private static void menuAction(char userOption, Train train,
                                      double pullingCapacity, double maximumLoad) {
        switch (userOption) {
            case 'a' : train.addFreightCar(addCar());
                System.out.println("Car successfully added to the train");
                break;

            case 'b' : displayCharacteristics(train);
                break;

            case 'c' : train.displayBriefSummary(maximumLoad);
                break;

            case 'd' : train.displayTotals(pullingCapacity);
                break;

            case 'e' :
                System.out.println("Starting new train...");
                System.out.println("Press [ENTER] to continue...");
                keyboard.nextLine();
                keyboard.nextLine();
                break;

            case 'f' :
                System.out.println("GoodBye!");
                break;


        }
    }
    private static void printMenu(){
        System.out.println("(a) add a car");
        System.out.println("(b) Display a completed description of the " +
                "characteristics " +
                "of the train, engine, and cars" +
                "\n(without the computed quantities like weight and value)");
        System.out.println("(c) Display a brief summary identifying each " +
                "car " +
                "(just the ID), and describing its weight and value. ");
        System.out.println("(d) Display just the total weight and value " +
                "of the " +
                "train, as well as the number of cars. ");
        System.out.println("(e) Start a new train");
        System.out.println("(f) Exit program");
    }
    private static Contents contentsOptions(){
        Contents[] contents = new Contents[5];
        int userOption = -1;
        int counter = 1;
        contents[0] = new Contents("Oil", 55, 7.85);
        contents[1] = new Contents("Coal", 69, 50);
        contents[2] = new Contents("Soybeans", 47, 2.72);
        contents[3] = new Contents("Linseed, meal", 32, 0.07);
        contents[4] = new Contents("Oranges", 64, 1.70);
        while(userOption > 5 || userOption < 1) {
            System.out.println("Enter a number (1-5) to choose any of " +
                    "options listed below:");

            System.out.printf("%-18s%-36s%s\n", "Contents", "Density " +
                    "(Pounds per Cubic Foot)", "Value (Dollars per Pound)");
            for (Contents content : contents) {
                System.out.printf(counter + ".) %-18s%-36f%f\n",
                        content.getName(),
                        content.getDensity(), content.getValue());
                counter++;
            }
            userOption = keyboard.nextInt();
            if(userOption > 5 || userOption < 1) System.out.println("Options " +
                    "only " +
                    "1-5 are available");
        }
        return contents[userOption-1];
    }

    private static FreightCar addCar() {
        int shape;
        Cylinder cylinder = null;
        RectangularBox rectangularBox = null;
        do {
            System.out.println("Pick a shape for the freight car: " +
                    "\n1.) Tank car (cylindrical)" +
                    "\n2.) Box car (rectangular box)");
            shape = keyboard.nextInt();
            if(shape > 2 || shape < 1) System.out.println("Only options " +
                    "1 & 2 are " +
                    "available (Enter choice as a integer that corresponds " +
                    "with the " +
                    "choices listed)");
        }while (shape > 2 || shape < 1);
        if(shape == 1) {
            System.out.println("Dimensions of tank car: ");
            System.out.print("Enter radius: ");
            double radius = keyboard.nextDouble();
            System.out.print("\nEnter height: ");
            double height = keyboard.nextDouble();
            System.out.print("\nEnter thickness for walls: ");
            double thickness = keyboard.nextDouble();
            System.out.print("\nEnter density for weight frame: ");
            double density = keyboard.nextDouble();
            System.out.println("");
            cylinder= new Cylinder(thickness, density, radius, height);
        }
        else {
            System.out.println("Dimensions of box car: ");
            System.out.print("Enter width: ");
            double width = keyboard.nextDouble();
            System.out.print("\nEnter height: ");
            double height = keyboard.nextDouble();
            System.out.print("\nEnter length: ");
            double length = keyboard.nextDouble();
            System.out.print("\nEnter thickness for walls: ");
            double thickness = keyboard.nextDouble();
            System.out.print("\nEnter density for weight frame: ");
            double density = keyboard.nextDouble();
            rectangularBox = new RectangularBox(thickness, density,
                    height, width, length);
        }
        Contents contents = contentsOptions();
        Container container;
        if(cylinder != null) {
            container = cylinder;
        }
        else {
            container = rectangularBox;
        }
        System.out.println("Enter load factor (the percentage full): ");
        double loadFactor = keyboard.nextDouble();
        keyboard.nextLine();
        System.out.println("To complete freight car, please enter name " +
                "of engineer, ID, and " +
                "weight of base frame (including wheels)");
        System.out.println("Enter name of engineer: ");
        String name = keyboard.nextLine();
        System.out.println("Enter ID: ");
        int ID = keyboard.nextInt();
        System.out.println("Enter weight: ");
        double weight = keyboard.nextDouble();
        FreightCar freightCar = new FreightCar( name, ID, weight,
                loadFactor, contents, container);

        return freightCar;
    }

    private static void displayCharacteristics(Train train) {
        System.out.println(train);
        System.out.println();
    }
}

abstract class Container {
    private double _thickness;
    private double _density;

    public Container(double thickness, double density) {
        _thickness = thickness;
        _density = density;
    }

    public double getThickness() {
        return _thickness;
    }

    public double getDensity() {
        return _density;
    }

    public abstract double computeInteriorVol();

    public abstract double computeExteriorVol();

    public double computeWeightWalls() {
        double volume = computeExteriorVol() - computeInteriorVol();
        double weight = _density * volume;

        return weight;
    }

    @Override
    public String toString() {
        return
                "\n\t\tThickness = " + _thickness +
                        "\n\t\tDensity = " + _density;
    }
}

class Contents {
    private String _name;
    private double _density;
    private double _value;

    public Contents(String name, double density, double value) {
        _name = name;
        _density = density;
        _value = value;
    }

    public String getName() {
        return _name;
    }

    public double getDensity() {
        return _density;
    }

    public double getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return
                "\n\t\tContent = " + _name +
                        "\n\t\tDensity = " + _density +
                        "\n\t\tValue = " + _value;
    }
}

abstract class RollingStock {
    private String _name;
    private int _ID;
    private double _weight;

    public RollingStock(String name, int ID, double weight) {
        _name = name;
        _ID = ID;
        _weight = weight;
    }

    public String getName() {
        return _name;
    }

    public int getID() {
        return _ID;
    }

    public double getWeight() {
        return _weight;
    }

    @Override
    public String toString() {
        return
                "\n\tName = " + _name +
                        "\n\tID = " + _ID +
                        "\n\tWeight = " + _weight;
    }
}

class Engine extends RollingStock {
    private double _pullingCapacity;

    public Engine(double pullingCapacity, String name, int ID, double weight) {
        super(name, ID, weight);
        _pullingCapacity = pullingCapacity;
    }

    public double getPullingCapacity() {
        return _pullingCapacity;
    }

    @Override
    public String toString() {
        return
                "\n\tPullingCapacity = " + _pullingCapacity +
                        super.toString();
    }
}

class FreightCar extends RollingStock {
    private double _loadFactor;
    private Contents _contents;
    private Container _container;

    public FreightCar(String name, int ID, double weight, double loadFactor,
                      Contents contents, Container container) {
        super(name, ID, weight);
        _loadFactor = loadFactor;
        _contents = contents;
        _container = container;
    }

    public double getLoadFactor() {
        return _loadFactor;
    }

    public void setLoadFactor(double loadFactor) {
        _loadFactor = loadFactor;
    }

    public double computeWeight() {
        double totalWeight =
                getWeight() +
                        _container.computeWeightWalls() +
                        weightContents();

        return totalWeight;
    }
    private double weightContents() {
        double weightContents =
                _container.computeInteriorVol() *
                        _loadFactor *
                        _contents.getDensity();
        return weightContents;
    }

    public double computeValue() {
        double totalValue = weightContents() *
                _contents.getValue();

        return totalValue;
    }

    @Override
    public String toString() {
        return
                super.toString() +
                        "\n\tLoadFactor = " + _loadFactor +
                        "\n\tContents : " + _contents +
                        "\n\tContainer : " + _container ;
    }
}

class Cylinder extends Container{
    private double _radius;
    private double _height;

    public Cylinder(double thickness, double density, double radius,
                    double height) {
        super(thickness, density);
        _radius = radius;
        _height = height;
    }

    public double getRadius() {
        return _radius;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public double computeInteriorVol() {
        double volume =
                Math.PI *
                        (_radius - Math.pow(getThickness(), 2)) *
                        (_height - 2 * getThickness());

        return volume;
    }

    @Override
    public double computeExteriorVol() {
        double volume =
                Math.PI *
                        (Math.pow(_radius, 2)) *
                        _height;

        return volume;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n\t\tRadius = " + _radius +
                "\n\t\tHeight = " + _height;
    }
}
class RectangularBox extends Container{
    private double _height;
    private double _width;
    private double _length;

    public RectangularBox(double thickness, double density, double height,
                          double width, double length) {
        super(thickness, density);
        _height = height;
        _width = width;
        _length = length;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getLength() {
        return _length;
    }

    @Override
    public double computeInteriorVol() {
        double volume =
                (_length - 2 * getThickness()) *
                        (_width - 2 * getThickness()) *
                        (_height - 2 * getThickness());

        return volume;
    }

    @Override
    public double computeExteriorVol() {
        double volume = _length * _width * _height;
        return volume;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n\t\tHeight = " + _height +
                "\n\t\tWidth = " + _width +
                "\n\t\tLength = " + _length ;
    }
}
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