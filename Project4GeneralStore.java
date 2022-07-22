import java.util.ArrayList;
import java.util.Scanner;

class Product {
  int id;
  String name;
  int qty;
  double price;

  public Product(int id, String name, int qty, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.qty = qty;
  }

}

class Cart {
  int id = 0;
  String name = "";
  int qty = 0;
  double price = 0.0;

  public Cart(int id, String name, double price, int qty) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.qty = qty;
  }

}

public class Project4GeneralStore {

  ArrayList<Product> inventory = new ArrayList<Product>();
  static ArrayList<Cart> cart = new ArrayList<Cart>();

  public void showInventory() {
    inventory.add(new Product(1, "soap", 100, 20.00));
    inventory.add(new Product(2, "shampoo", 500, 10.00));
    inventory.add(new Product(3, "colgate", 300, 55.00));
    inventory.add(new Product(4, "Brush", 500, 10.00));

    System.out.println("----------------INVENTORY---------------");
    System.out.println("ID\tName\t\tPrice\tQuantity");
    for (Product p : inventory) {
      System.out.println(p.id + "\t" + p.name + "\t\t₹" + p.price + "\t" + p.qty);
    }
    System.out.println("----------------------------------------");

  }

  public Product getProduct(int pId) {

    for (int i = 0; i <= inventory.size(); i++) {
      if (pId == i) {
        return inventory.get(i);
      }
    }
    return null;
  }

  public Product updateInventory(int uId, int Uqty) {
    for (Product p : inventory) {
      if (uId == p.id) {
        p.qty = p.qty - Uqty;
      }
    }
    System.out.println("----------------INVENTORY---------------");
    System.out.println("ID\tName\t\tPrice\tQuantity");
    for (Product p : inventory) {
      System.out.println(p.id + "\t" + p.name + "\t\t₹" + p.price + "\t" + p.qty);
    }
    System.out.println("----------------------------------------");
    return null;
  }

  public void showCart() {
    int index = 1;
    System.out.println("------------------Cart------------------");
    System.out.println("S.no.\tName\t\tPrice\tQuantity");
    for (Cart c1 : cart) {
      System.out.println(index + "\t" + c1.name + "\t\t₹" + c1.price + "\t" + c1.qty);
      index++;

    }
    System.out.println("----------------------------------------");

  }

  public Cart removeId(int Id) {
    for (int i = 0; i <= cart.size(); i++) {
      if (Id == i) {
        return cart.remove(i);
      }
    }
    return null;
  }

  public void checkOut() {
    double subTotal = 0.0;
    int index = 1;
    System.out.println("------------------------Cart---------------------------");
    System.out.println("S.no.\tName\t\tPrice\tQuantity\tTotal");
    for (Cart c1 : cart) {
      System.out.println(index + "\t" + c1.name + "\t\t₹" + c1.price + "\t" + c1.qty + "\t\t" + (c1.price * c1.qty));
      subTotal += (c1.price * c1.qty);
      index++;
    }
    System.out.println("-------------------------------------------------------");
    System.out.println("\t\t\t\t       Subtotal: ₹" + subTotal);
    System.out.println("-------------------------------------------------------");

  }

  public static void main(String[] args) {
    Project4GeneralStore gStore = new Project4GeneralStore();

    Scanner sc = new Scanner(System.in);

    gStore.showInventory(); // call showInventory method which have products details

    System.out.println("Do you want to purchase product[Y/N]"); // purchase products
    char option = sc.next().charAt(0);

    if (option == 'y' || option == 'Y') {
      while (true) {
        try {
          System.out.print("Enter product id: ");
          int idCart = sc.nextInt();
          System.out.print("\n");

          idCart = idCart - 1;
          Product p = gStore.getProduct(idCart); // get products detail

          if (p == null) {
            System.out.println("Error! Product is not found.");
            continue;
          }

          System.out.println("Product Id              : " + p.id);
          System.out.println("Product Name            : " + p.name);
          System.out.println("Product Price           : ₹" + p.price);
          System.out.println("Total Available Quantity: " + p.qty);

          System.out.print("\nPlease Enter product quantity: ");
          int qtyCart = sc.nextInt();
          System.out.print("\n");

          if (p.qty < qtyCart) {
            System.out.println("Sorry! This quantity is not available");
            continue;
          }

          // Add products into cart
          Cart c = new Cart(qtyCart, null, qtyCart, qtyCart);
          c.id = p.id;
          c.name = p.name;
          c.price = p.price;
          c.qty = qtyCart;
          cart.add(new Cart(c.id, c.name, c.price, c.qty));

          System.out.println("Product Id              : " + p.id);
          System.out.println("Product Name            : " + p.name);
          System.out.println("Product Price           : ₹" + p.price);
          System.out.println("Selected Quantity       : " + qtyCart);

          System.out.println("\nDo you want to add more products[Y/N]");
          char choose = sc.next().charAt(0);

          if (choose == 'y' || choose == 'Y') {
            idCart = idCart + 1;
            gStore.updateInventory(idCart, qtyCart); // automatically update inventory after purchasing products
            continue;

          } else {
            break;
          }
        } catch (Exception e) {
          System.out.println("Product is not found or invalid id!\nplease try again.");
          continue;
        }
      }

    }

    // options
    while (true) {
      System.out.println("Select your option: \n1. Show cart\n2. Checkout\n3. Exit");
      int op = sc.nextInt();
      if (op == 1) {
        // show cart
        gStore.showCart();

        // remove product id
        try {
        System.out.println("select your option: \n1. Remove product\n2. Back");
        int op1 = sc.nextInt();
          if (op1 == 1) {
            System.out.print("Enter s.no. for remove product: ");
            int idR = sc.nextInt();
            idR = idR - 1;
            gStore.removeId(idR);

          }

        } catch (Exception e) {
          System.out.println("Oops!! wrong input.\nPlease try again.");
          continue;
        }
      } else if (op == 2) {
        gStore.checkOut();
        System.out.println("Thank you for visiting us.");
        return;
      } else if (op == 3) {
        System.out.println("We hope you will come again. Thank you!");
        return;
      }
      // sc.close();
    }
  }
}
