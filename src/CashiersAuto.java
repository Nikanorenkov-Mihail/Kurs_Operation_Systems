import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import javax.swing.Timer; //Будет вызыватся каждую секунду

class ClientRandomNames {
    static String[] names = {"Tom", "Rob", "Cary", "Kate", "Jim", "Jane", "Lou", "Bob"};
}

class ClientAuto {
    static String clientName;
    static String serviceType;
    static boolean payment;
    static int queuePrice;
    static int queueTypeOfService;
    static int queueSizeOfService;
}

class CashierAuto extends Thread {
    public static void clientName() {
        System.out.print("Enter client name (or '0' to stop session): ");
        Client.clientName = ClientRandomNames.names[(int) (Math.random() * 8)];
        System.out.println(Client.clientName);
    }

    public static void serviceType() {
        System.out.print("Enter service type: ");
        Client.serviceType = "" + ((int) (Math.random() * 3) + 1);
        System.out.println(Client.serviceType);

        int quantityOfService = -1;

        switch (Client.serviceType) {
            case "1":

                while (quantityOfService < 0) {
                    System.out.print("Amount of Fuel: ");
                    try {
                        quantityOfService = (int) (Math.random() * 150 + 1);
                    } catch (Exception e) {
                        System.out.println("Need numeric");
                    }
                }
                System.out.println(quantityOfService);
                Client.queuePrice = quantityOfService * 55;
                Client.queueTypeOfService = 1;
                Client.queueSizeOfService = quantityOfService;
                //Cashiers.allMoney += quantityOfService * 55;
                //Cashiers.fuelSold += quantityOfService;
                //return "Fuel: " + quantityOfService + "liters";
                break;
            case "2":

                while (quantityOfService < 0) {
                    System.out.print("Amount of Diesel: ");
                    try {
                        quantityOfService = (int) (Math.random() * 150 + 1);
                    } catch (Exception e) {
                        System.out.println("Need numeric");
                    }
                }
                System.out.println(quantityOfService);
                Client.queuePrice = quantityOfService * 65;
                Client.queueTypeOfService = 2;
                Client.queueSizeOfService = quantityOfService;
                //Cashiers.allMoney += quantityOfService * 65;
                //Cashiers.dieselSold += quantityOfService;
                //return "Diesel: " + quantityOfService + "liters";
                break;
            case "3":
                while (quantityOfService < 0) {
                    System.out.print("Amount of Coffee: ");

                    try {
                        quantityOfService = (int) (Math.random() * 5 + 1);
                    } catch (Exception e) {
                        System.out.println("Need numeric");
                    }
                }
                System.out.println(quantityOfService);
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
        String pay = "ok";
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

        try {
            Thread.sleep(1000);        //Приостановка потока на 1 сек.
        } catch (InterruptedException e) {
            return;    //Завершение потока после прерывания
        }
        CashierAuto.clientName();

        if (!Client.clientName.equalsIgnoreCase("0")) {
            //break;
            try {
                Thread.sleep(1000);        //Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
                return;    //Завершение потока после прерывания
            }

            CashierAuto.serviceType();

            try {
                Thread.sleep(1000);        //Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
                return;    //Завершение потока после прерывания
            }

            CashierAuto.payment();

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


public class CashiersAuto {
    public static int allMoney = 0;
    public static int fuelSold = 0;
    public static int dieselSold = 0;
    public static int coffeeSold = 0;

    static CashierAuto cashier2;
    static CashierAuto cashier3;
    static String clientName;
    static String serviceType;
    static boolean payment;

    public static void main(String[] args) throws InterruptedException {
        // Timer
        long startWorking = System.currentTimeMillis();

        Scanner scanner = new Scanner(System.in);
        boolean settingsForThreat = true; // переключение работы потоков
        // true - много потоков
        // false - один поток
        int temp = 0; // переменная для проверки времени работы
        while (temp < 10) {
            temp++;
            System.out.print("Enter the number of cashiers (1-3): ");
            int cashierCount = (settingsForThreat) ? (int) (Math.random() * 3 + 1) : 1;
            System.out.println(cashierCount);

            if (cashierCount == 2) {
                System.out.println("Second cashier");
                cashier2 = new CashierAuto();    //Создание потока
                cashier2.start();    //Запуск потока
                cashier2.setName("Second cashier");
                //cashier2.join();
            } else if (cashierCount == 3) {
                System.out.println("Third cashier");
                cashier3 = new CashierAuto();    //Создание потока
                cashier3.start();    //Запуск потока
                cashier3.setName("Third cashier");
                //cashier3.join();
            } else {

                System.out.println("First cashier");
                scanner = new Scanner(System.in);
                try {
                    Thread.sleep(1000);        //Приостановка потока на 1 сек.
                } catch (InterruptedException e) {
                    return;    //Завершение потока после прерывания
                }
                CashierAuto.clientName();
                if (!Client.clientName.equalsIgnoreCase("0")) {
                    //break;
                    try {
                        Thread.sleep(1000);        //Приостановка потока на 1 сек.
                    } catch (InterruptedException e) {
                        return;    //Завершение потока после прерывания
                    }
                    CashierAuto.serviceType();
                    try {
                        Thread.sleep(1000);        //Приостановка потока на 1 сек.
                    } catch (InterruptedException e) {
                        return;    //Завершение потока после прерывания
                    }
                    CashierAuto.payment();
                    try {
                        Thread.sleep(1000);        //Приостановка потока на 1 сек.
                    } catch (InterruptedException e) {
                        return;    //Завершение потока после прерывания
                    }
                }
                CashierAuto.goodLuck();
            }

        }
        if (settingsForThreat) {
            cashier2.join();
            cashier3.join();
        }
        System.out.println("End program: " + (System.currentTimeMillis() - startWorking));
    }
}