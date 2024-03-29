import java.util.Scanner;
import java.util.concurrent.*;

class Client {
    static String clientName;
    static String serviceType;
    static boolean payment;
    static int queuePrice;
    static int queueTypeOfService;
    static int queueSizeOfService;
}

class Cashier extends Thread {
    public static void clientName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter client name (or '0' to stop session): ");
        Client.clientName = scanner.next();

        if (Client.clientName.equalsIgnoreCase("0")) {
            System.out.print("Sure? ('0' to stop session or Client name): ");
            Client.clientName = scanner.next();
        }
    }

    public static void serviceType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter service type: ");
        Client.serviceType = scanner.next().toLowerCase().trim();
        while (!(Client.serviceType.equals("1") | Client.serviceType.equals("2") | Client.serviceType.equals("3"))) {
            System.out.print("Enter service type: ");
            Client.serviceType = scanner.next().toLowerCase().trim();
        }
        int quantityOfService = -1;

        switch (Client.serviceType) {
            case "1":

                while (quantityOfService < 0) {
                    System.out.println("Amount of Fuel: ");
                    try {
                        quantityOfService = Integer.parseInt(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Need numeric");
                    }
                }
                Client.queuePrice = quantityOfService * 55;
                Client.queueTypeOfService = 1;
                Client.queueSizeOfService = quantityOfService;
                //Cashiers.allMoney += quantityOfService * 55;
                //Cashiers.fuelSold += quantityOfService;
                //return "Fuel: " + quantityOfService + "liters";
                break;
            case "2":

                while (quantityOfService < 0) {
                    System.out.println("Amount of Diesel: ");
                    try {
                        quantityOfService = Integer.parseInt(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Need numeric");
                    }
                }
                Client.queuePrice = quantityOfService * 65;
                Client.queueTypeOfService = 2;
                Client.queueSizeOfService = quantityOfService;
                //Cashiers.allMoney += quantityOfService * 65;
                //Cashiers.dieselSold += quantityOfService;
                //return "Diesel: " + quantityOfService + "liters";
                break;
            case "3":
                while (quantityOfService < 0) {
                    System.out.println("Amount of Coffee: ");

                    try {
                        quantityOfService = Integer.parseInt(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Need numeric");
                    }
                }
                Client.queuePrice = quantityOfService * 100;
                Client.queueTypeOfService = 3;
                Client.queueSizeOfService = quantityOfService;
                //Cashiers.allMoney += quantityOfService * 100;
                //Cashiers.coffeeSold += quantityOfService;
                //return "Coffee: " + quantityOfService + "cups";
                break;
            default:
                System.out.println("Invalid service");
        }
        //return "";
    }

    public static void payment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Waiting for payment (Ok)");
        String pay = scanner.next().trim().toLowerCase();
        while (!pay.equals("ok")) {
            if (pay.equalsIgnoreCase("No money")) {
                //return false;
                Client.payment = false;
            }
            System.out.println("Waiting for payment (Ok)");
            pay = scanner.next().trim().toLowerCase();
        }
        switch (Client.queueTypeOfService) {
            case 1:
                Cashiers.fuelSold += Client.queueSizeOfService;
                Cashiers.allMoney += Client.queuePrice;
            case 2:
                Cashiers.dieselSold += Client.queueSizeOfService;
                Cashiers.allMoney += Client.queuePrice;
            case 3:
                Cashiers.coffeeSold += Client.queueSizeOfService;
                Cashiers.allMoney += Client.queuePrice;
        }
        //return true;
        Client.payment = true;
    }

    public static void goodLuck() {
        System.out.println("Good Luck");
        System.out.println();
    }

    @Override
    public void run() {
        //Проверка прерывания
        //Завершение потока
        //while (!Thread.interrupted()) {
        Scanner scanner = new Scanner(System.in);

        clientName();
        if (Client.clientName.equalsIgnoreCase("0")) {
            //break;
        }else {
            serviceType();

            payment();

            try {
                Thread.sleep(1000);        //Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
                return;    //Завершение потока после прерывания
            }
        }

        goodLuck();
        //}
    }
}


public class Cashiers {
    public static int allMoney = 0;
    public static int fuelSold = 0;
    public static int dieselSold = 0;
    public static int coffeeSold = 0;

    static Cashier cashier2;
    static Cashier cashier3;
    static String clientName;
    static String serviceType;
    static boolean payment;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the number of cashiers (1-3): ");
            int cashierCount = scanner.nextInt();
            while (!(cashierCount > 0 && cashierCount < 4)) {
                System.out.print("Enter the number of cashiers: ");
                cashierCount = scanner.nextInt();
            }



            if (cashierCount == 2) {
                System.out.println("Second cashier");
                cashier2 = new Cashier();    //Создание потока
                cashier2.start();    //Запуск потока
                cashier2.setName("Second cashier");
                cashier2.join();
            } else if (cashierCount == 3) {
                System.out.println("Third cashier");
                cashier3 = new Cashier();    //Создание потока
                cashier3.start();    //Запуск потока
                cashier3.setName("Third cashier");
                cashier3.join();
            } else {

                System.out.println("First cashier");
                scanner = new Scanner(System.in);

              Cashier.clientName();
                if (Client.clientName.equalsIgnoreCase("0")) {
                    //break;
                }else{
                    Cashier.serviceType();
                    Cashier.payment();
                }
                Cashier.goodLuck();
            }
        }
    }
}
