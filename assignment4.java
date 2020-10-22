import java.util.ArrayList;
import java.util.Scanner;

public class assignment4 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        int type;
        Inventory inventory = new Inventory();

        do {
            System.out.println("-----------------------------");
            System.out.println("Welcome to Inventory System!");
            System.out.println("-----------------------------");
            System.out.println("Please make your Selection");
            System.out.println("1. Add Inventory");
            System.out.println("2. Add Order");
            System.out.println("3. Show Inventory");
            System.out.println("4. Show Orders");
            System.out.println("5. Show All Details");
            System.out.println("6. Exit System");
            type = input.nextInt();
            switch (type) {
                case 1:
                    int addInvType, quantity;
                    String name;
                    double price;
                    System.out.println("You can add Items from the following List");
                    System.out.println("1. Clothes");
                    System.out.println("2. Cosmetics");
                    System.out.println("3. Electronics");
                    addInvType = input.nextInt();
                    switch (addInvType) {
                        case 1:
                            System.out.println("---Clothes---");
                            System.out.println("Enter Name:");
                            name = input.next();
                            System.out.println("Enter Quantity:");
                            quantity = input.nextInt();
                            System.out.println("Enter Price Per Unit:");
                            price = input.nextDouble();
                            inventory.addInventory(new Clothes(name, price, quantity));
                            break;
                        case 2:
                            System.out.println("---Cosmetics---");
                            System.out.println("Enter Name:");
                            name = input.next();
                            System.out.println("Enter Quantity:");
                            quantity = input.nextInt();
                            System.out.println("Enter Price Per Unit:");
                            price = input.nextDouble();
                            inventory.addInventory(new Cosmetics(name, price, quantity));
                            break;
                        case 3:
                            System.out.println("---Electronics---");
                            System.out.println("Enter Name:");
                            name = input.next();
                            System.out.println("Enter Quantity:");
                            quantity = input.nextInt();
                            System.out.println("Enter Price Per Unit:");
                            price = input.nextDouble();
                            inventory.addInventory(new Electronics(name, price, quantity));
                            break;
                        default:
                            System.out.println("Please enter correct choice");
                            break;
                    }
                    break;
                case 2:
                    Order order = new Order();
                    boolean flag2 = true;
                    int quantity2, type2;
                    do {
                        System.out.println("You can add to your cart from following list.");
                        String str = "";
                        for (Item obj : inventory.items) {
                            str += obj.toString() + "\n";
                        }
                        System.out.println(String.format("[\n %s \n]", str));
                        System.out.println("Enter Name:");
                        String name2 = input.next();
                        System.out.println("Enter Quantity:");
                        quantity2 = input.nextInt();
                        for (Item item : inventory.items) {
                            boolean found = false;
                            if (item.name.equals(name2)) {
                                for (Item oitem : order.orderItems) {
                                    if (oitem.name.equals(name2)) {
                                        found = true;
                                        oitem.quantity += quantity2;
                                    }
                                }
                                if (!found) {
                                    Item newItem = clone(item.getClass());
                                    newItem.name = item.name;
                                    newItem.setPrice(item.price);
                                    newItem.setQuantity(quantity2);
                                    order.addItem(newItem);
                                }
                                order.orderTotal += quantity2 * item.price;
                            }
                        }
                        System.out.println("Your order details:");
                        System.out.println("--------------------");
                        System.out.println("Order total:" + order.orderTotal);
                        String str4 = "";
                        for (Item obj : order.orderItems) {
                            str4 += obj.toString() + "\n";
                        }
                        System.out.println(String.format("items: [\n %s \n]", str4));
                        System.out.println("Enter");
                        System.out.println("1. for adding more items");
                        System.out.println("2. for Completing order");
                        type2 = input.nextInt();
                        if (type2 == 2) {
                            boolean err = false;
                            for (Item oitem : order.orderItems) {
                                for (Item item : inventory.items) {
                                    if (oitem.name.equals(item.name)) {
                                        if (item.quantity - oitem.quantity > 0) {
                                            item.quantity -= oitem.quantity;
                                        } else {
                                            err = true;
                                            order.orderTotal -= oitem.price * oitem.quantity;
                                            order.removeItem(oitem);
                                            System.out.println("------------Error-----------");
                                            System.out.println("The Product " + item.name + " is out of stock!!");
                                            break;
                                        }
                                    }
                                }
                                if (err)
                                    break;
                            }
                            if (!err) {
                                inventory.addOrders(order);
                                flag2 = false;
                            }
                        } else if (type2 == 1)
                            flag2 = true;
                        else
                            System.out.println("Wrong Options");
                    } while (flag2);
                    break;
                case 3:
                    String str = "";
                    for (Item obj : inventory.items) {
                        str += obj.toString() + "\n";
                    }
                    System.out.println(String.format("[\n %s \n]", str));
                    break;
                case 4:
                    System.out.println("[");
                    for (Order item : inventory.orders) {
                        String str2 = "";
                        for (Item obj : item.orderItems) {
                            str2 += obj.toString() + "\n";
                        }
                        System.out.println("Order Total: " + item.orderTotal);
                        System.out.println("Items: " + String.format("[\n %s \n]", str2) + ",");
                    }
                    System.out.println("]");
                    break;
                case 5:
                    System.out.println("------Inventory Items------");
                    String str3 = "";
                    for (Item obj : inventory.items) {
                        str3 += obj.toString() + "\n";
                    }
                    System.out.println(String.format("[\n %s \n]", str3));
                    System.out.println("------Inventory Orders------");
                    System.out.println("[");
                    for (Order item : inventory.orders) {
                        String str2 = "";
                        for (Item obj : item.orderItems) {
                            str2 += obj.toString() + "\n";
                        }
                        System.out.println("Order Total: " + item.orderTotal);
                        System.out.println("Items: " + String.format("[\n %s \n]", str2) + ",");
                    }
                    System.out.println("]");
                    break;
                case 6:
                    flag = false;
                    break;

                default:
                    System.out.println("Please Enter Correct Choice");
                    break;
            }

        } while (flag);
    }

    public static <T> T clone(Class<T> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * Inventory
 */
class Inventory {
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Order> orders = new ArrayList<Order>();

    public void addInventory(Item obj) {
        this.items.add(obj);
    }

    public void addOrders(Order obj) {
        this.orders.add(obj);
    }

}

class Order {
    String customerName;
    double orderTotal;
    ArrayList<Item> orderItems = new ArrayList<Item>();

    public void addItem(Item obj) {
        this.orderItems.add(obj);
    }

    public void removeItem(Item obj) {
        this.orderItems.remove(orderItems.indexOf(obj));
    }

}

abstract class Item {
    String name;
    double price;
    int quantity;

    Item() {
    };

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    };

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity(int quantity) {
        return this.quantity;
    }

    public String toString() {
        return String.format("Name: %s, Price is: %s, Quantity is: %s", this.name, this.price, this.quantity);
    }
}

/**
 * Clothes
 */
class Clothes extends Item {
    Clothes() {
    };

    Clothes(String name, double price, int quantity) {
        super(name, price, quantity);
    };

    public String toString() {
        return String.format("Type: %s, %s", "Clothes", super.toString());
    }

}

/**
 * Electronics
 */
class Electronics extends Item {
    Electronics() {
    };

    Electronics(String name, double price, int quantity) {
        super(name, price, quantity);
    };

    public String toString() {
        return String.format("Type: %s, %s", "Electronics", super.toString());
    }
}

/**
 * Cosmetics
 */
class Cosmetics extends Item {
    Cosmetics() {
    };

    Cosmetics(String name, double price, int quantity) {
        super(name, price, quantity);
    };

    public String toString() {
        return String.format("Type: %s, %s", "Cosmetics", super.toString());
    }
}