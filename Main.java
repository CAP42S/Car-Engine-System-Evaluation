// Author: Samer Ali
// Description: This program helps a small feeder line decide which
// cars to include in each trip. In addition, the
// program lets the customer try out different
//combinations of engine and cars and then computes the total
// value and weight of each combination.

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
