package RMS_package;

import java.util.ArrayList;
import java.util.Scanner;

class MenuItem {
    String name;
    int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

public class Restaurant2 {

    private static void displayMenu(ArrayList<MenuItem> menu) {
        System.out.println("the menu items are -");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + " :  " + menu.get(i).name + " ₹-" + menu.get(i).price);
        }
    }

    private static void addMenuItem(ArrayList<MenuItem> menu, String name, int price) {
        menu.add(new MenuItem(name, price));
    }

    private static void deleteItem(int delno, ArrayList<MenuItem> menu) {
        menu.remove(delno);
        System.out.println("the item is deleted");
        displayMenu(menu);
    }

    private static void managerMenu(ArrayList<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("what you want to do:\n1. add item in menu\n2. delete item in menu\n3. display menu\n4. exit\nenter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (choice) {
                case 1:
                    System.out.println("Enter name of new item: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter price of new item: ");
                    int price = scanner.nextInt();
                    addMenuItem(menu, name, price);
                    displayMenu(menu);
                    break;
                case 2:
                    System.out.println("enter the item no. you want to delete:");
                    int delno = scanner.nextInt() - 1;
                    deleteItem(delno, menu);
                    break;
                case 3:
                    displayMenu(menu);
                    break;
                case 4:
                    System.out.println("exiting manager menu");
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    }

    private static void customerMenu(ArrayList<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        displayMenu(menu);
        System.out.println("please select the no. of item you want to eat : ");
        int selected = scanner.nextInt() - 1;
        ArrayList<MenuItem> cartItems = new ArrayList<>();
        while (true) {
            if (selected < 0 || selected >= menu.size()) {
                System.out.println("invalid item no., plz select again: ");
                selected = scanner.nextInt() - 1;
                continue;
            }
            System.out.println("selected item is: " + menu.get(selected).name + " ₹" + menu.get(selected).price);
            System.out.println("enter qty of item: ");
            int qty = scanner.nextInt();
            cartItems.add(new MenuItem(menu.get(selected).name, menu.get(selected).price * qty));
            System.out.println("anything else(y/n)?");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("y")) {
                break;
            }
            System.out.println("plz select the next item no.");
            selected = scanner.nextInt() - 1;
        }
        System.out.println("Your bill is: ");
        int totalprice = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + " : " + cartItems.get(i).name + " ₹" + cartItems.get(i).price);
            totalprice += cartItems.get(i).price;
        }
        System.out.println("total amount to pay is: ₹" + totalprice);
    }

    public static void main(String[] args) {
        ArrayList<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("Kachori", 10));
        menu.add(new MenuItem("Samosa", 10));
        menu.add(new MenuItem("Poha", 15));
        menu.add(new MenuItem("Jalebi-fafda", 30));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select who are you :\n 1. Manager \n 2. Customer\n Enter your choice:");
        int ManOrCus = scanner.nextInt();
        if (ManOrCus == 1) {
            managerMenu(menu);
        } else if (ManOrCus == 2) {
            customerMenu(menu);

        } else {
            System.out.println("invalid");
        }
    }
}


