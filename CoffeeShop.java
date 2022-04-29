import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CoffeeShop {

    public static void main(String[] args) {
        MenuItem[] menu = new MenuItem[] {
                new MenuItem("orange juice", "drink", 2.13), new MenuItem("lemonade", "drink", 0.85),
                new MenuItem("cranberry juice", "drink", 3.36),
                new MenuItem("pineapple juice", "drink", 1.89), new MenuItem("lemon iced tea", "drink", 1.28),
                new MenuItem("apple iced tea", "drink", 1.28),
                new MenuItem("vanilla chai latte", "drink", 2.48), new MenuItem("hot chocolate", "drink", 0.99),
                new MenuItem("iced coffee", "drink", 1.12),
                new MenuItem("tuna sandwich", "food", 0.95), new MenuItem("ham and cheese sandwich", "food", 1.35),
                new MenuItem("bacon and egg", "food", 1.15),
                new MenuItem("steak", "food", 3.28), new MenuItem("hamburger", "food", 1.05),
                new MenuItem("cinnamon roll", "food", 1.05) };
    }

    private String name;
    private MenuItem[] menu;
    private String[] orders;
    private Queue<String> ordersQueue;

    public CoffeeShop(String name, MenuItem[] menu, String[] orders) {
        this.name = name;
        this.menu = menu;
        this.orders = orders;
        this.ordersQueue = new LinkedList<String>();
    }

    public String addOrder(String item) {
        boolean itemExists = false;
        for (int i = 0; i < menu.length; i++) {
            if (menu[i].getItem().equals(item)) {
                itemExists = true;
                ordersQueue.add(item);
                break;
            }
        }
        return itemExists ? "Order added!" : "This item is currently unavailable!";
    }

    public String fulfillOrder() {
        return ordersQueue.isEmpty() ? "All orders have been fulfilled!" : "The " + ordersQueue.poll() + " is ready!";
    }

    public String[] listOrders() {
        return ordersQueue.toArray(new String[0]);
    }

    public double dueAmount() {
        orders = ordersQueue.toArray(new String[0]);
        double totalPrice = 0d;
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < menu.length; j++) {
                if (menu[j].getItem().equals(orders[i])) {
                    totalPrice += menu[j].getPrice();
                }
            }
        }
        return totalPrice;
    }

    public String cheapestItem() {
        String cheapestString = "";
        double cheapestDouble = Double.MAX_VALUE;
        for (MenuItem a : menu) {
            if (a.getPrice() < cheapestDouble) {
                cheapestDouble = a.getPrice();
                cheapestString = a.getItem();
            }
        }
        return cheapestString;
    }

    public String[] drinksOnly() {
        List<String> drinksList = new ArrayList<>();
        for (MenuItem a : menu) {
            if (a.getType().equals("drink")) {
                drinksList.add(a.getItem());
            }
        }
        return drinksList.toArray(new String[0]);
    }

    public String[] foodOnly() {
        List<String> foodList = new ArrayList<>();
        for (MenuItem a : menu) {
            if (a.getType().equals("food")) {
                foodList.add(a.getItem());
            }
        }
        return foodList.toArray(new String[0]);
    }
}

class MenuItem {
    private String item;
    private String type;
    private double price;

    public MenuItem(String item, String type, double price) {
        this.item = item;
        this.type = type;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}