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
    private Service service;
    private Queue queue;

    public Client(String nameIn, Service serviceIn, Queue queueIn) {
        this.name = nameIn;
        this.service = serviceIn;
        this.queue = queueIn;
    }

    @Override
    public void run() {
        System.out.println(name + " ждет обслуживания для " + service.getName());
        queue.обслужить(service);
    }
}

class Queue {
    int numbersOfCashRegister;
    Semaphore semaphore;

    public Queue(int numbers) {
        this.numbersOfCashRegister = numbers;
        this.semaphore = new Semaphore(numbersOfCashRegister);
    }

    public void обслужить(Service service) {
        try {
            semaphore.acquire();
            System.out.println("Клиент обслуживается для " + service.getName() + ". Доступных касс: " + semaphore.availablePermits());
            Thread.sleep(2000); // Имитация времени обслуживания
            System.out.println("Клиент " + Thread.currentThread().getName() + " обслужен для " + service.getName());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество касс: ");
        int numbersOfCashRegister = scanner.nextInt();

        Queue очередьКасс = new Queue(numbersOfCashRegister);

        while (true) {
            System.out.print("Введите имя клиента (или 'exit' для выхода): ");
            String имяКлиента = scanner.next();

            if (имяКлиента.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Введите вид услуги: ");
            String видУслуги = scanner.next();

            Service service = new Service(видУслуги);
            Client клиент = new Client(имяКлиента, service, очередьКасс);
            new Thread(клиент).start();
        }
    }
}*/
