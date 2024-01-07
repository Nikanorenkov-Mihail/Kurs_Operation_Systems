/*import java.util.Scanner;
import java.util.concurrent.*;

class Service {
    String name;

    public Service(String nameIn) {
        this.name = nameIn;
    }

    public String getName() {
        return name;
    }
}

class Client implements Runnable {
    private String name;
    Service service;
    private CashierQueue cashierQueue;

    public Client(String name, Service service, CashierQueue cashierQueue) {
        this.name = name;
        this.service = service;
        this.cashierQueue = cashierQueue;
    }

    @Override
    public void run() {
        System.out.println(name + " is waiting for " + service.getName());
        cashierQueue.serve(service);
    }
}

class CashierQueue {
    private int cashierCount;
    private Semaphore semaphore;

    public CashierQueue(int cashierCount) {
        this.cashierCount = cashierCount;
        this.semaphore = new Semaphore(cashierCount);
    }

    public void serve(Service service) {
        try {
            semaphore.acquire();
            System.out.println("Client being served for " + service.getName() + ". Available cashiers: " + semaphore.availablePermits());
            Thread.sleep(1000); // Simulating service time
            System.out.println("Client " + Thread.currentThread().getName() + " served for " + service.getName());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class cashRegisters {
    int allMoney = 0;
    int fuelSold = 0;
    int dieselSold = 0;
    public void report(){
        System.out.println("Results for session: ");
        System.out.println("Money received: " + allMoney);
        System.out.println("Fuel sold: " + fuelSold);
        System.out.println("Diesel sold: " + dieselSold);
    }
    public static void main(String[] args) {
        cashRegisters mainCashier = new cashRegisters();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cashiers (1-3): ");
        int cashierCount = scanner.nextInt();
        while (!(cashierCount > 0 && cashierCount < 3)) {
            System.out.print("Enter the number of cashiers: ");
            cashierCount = scanner.nextInt();
        }

        CashierQueue cashierQueue = new CashierQueue(cashierCount);

        while (true) {
            System.out.print("Enter client name (or '0' to stop session): ");
            String clientName = scanner.next();

            if (clientName.equalsIgnoreCase("0")) {
                System.out.print("Sure? ('0' to stop session): ");
                clientName = scanner.next();
                if (clientName.equalsIgnoreCase("0")) {
                    mainCashier.report();
                    break;
                }
            }

            System.out.print("Enter service type: ");
            String serviceType = scanner.next();

            Service service = new Service(serviceType);
            Client client = new Client(clientName, service, cashierQueue);

            client.service.getName();
            new Thread(client).start();
        }
    }
}
*/