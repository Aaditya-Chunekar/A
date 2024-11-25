import java.util.Scanner;

class PizzaOrder {
    int orderNumber;
    String customerName;

    PizzaOrder(int orderNumber, String customerName) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Order #" + orderNumber + " - " + customerName;
    }
}

class CircularQueue {
    private PizzaOrder[] queue;
    private int front, rear, size, capacity;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new PizzaOrder[capacity];
        front = rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void insert(PizzaOrder order) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot add more orders.");
            return;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = order;
        size++;
        System.out.println("Order added: " + order);
    }

    public void delete() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No orders to process.");
            return;
        }
        PizzaOrder order = queue[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        System.out.println("Order processed: " + order);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("No orders in the queue.");
            return;
        }
        System.out.println("Current orders:");
        int count = 0;
        int i = front;
        while (count < size) {
            System.out.println(queue[i]);
            i = (i + 1) % capacity;
            count++;
        }
    }
}

public class Pizza{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the maximum number of orders the system can handle: ");
        int capacity = scanner.nextInt();
        CircularQueue orderQueue = new CircularQueue(capacity);
        int orderNumber = 1;

        while (true) {
            System.out.println("\nPizza Order Management System");
            System.out.println("1. Add new order");
            System.out.println("2. Process next order");
            System.out.println("3. Display all orders");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (!orderQueue.isFull()) {
                        System.out.print("Enter customer name: ");
                        scanner.nextLine();  // Consume newline
                        String customerName = scanner.nextLine();
                        //imp line below
                        PizzaOrder newOrder = new PizzaOrder(orderNumber++, customerName);
                        orderQueue.insert(newOrder);
                    } else {
                        System.out.println("Cannot add more orders. Queue is full.");
                    }
                    break;
                case 2:
                    orderQueue.delete();
                    break;
                case 3:
                    orderQueue.display();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    //imp line below
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
