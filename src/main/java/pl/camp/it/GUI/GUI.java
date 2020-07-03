package pl.camp.it.GUI;

import pl.camp.it.db.ProductCategoryRepository;
import pl.camp.it.db.SQLDb;
import pl.camp.it.db.ProductRepository;
import pl.camp.it.model.Product;
import pl.camp.it.model.ProductCategory;
import java.util.Scanner;

public class GUI {

    private static final Scanner scanner = new Scanner(System.in);

    public GUI() {
    }

    public static void showMainMenu() {
        System.out.println("1. Dostępne produkty");
        System.out.println("2. Dodaj produkt");
        System.out.println("3. Exit");
        System.out.println("4. Dostępne katerogie");
        System.out.println("5. Dodaj kategorię");
        System.out.println("6. Dostępne produkty z danej kategorii");
        System.out.println("7. Usuń kategorię");

        String choose = scanner.nextLine();

        switch (choose) {
            case "1":
                showProducts();
                break;
            case "2":
                addProduct();
                break;
            case "3":
                SQLDb.closeConnection();
                System.exit(0);
            case "4":
                showCategories();
                break;
            case "5":
                addCategory();
                break;
            case "6":
                showProductsByCategory();
                break;
            case "7":
                deleteCategory();
                break;
            default:
                System.out.println("Nieprawidłowy wybór !!");
                showMainMenu();
                break;
        }
    }

    private static void showProducts() {
        for (Product tempProduct : ProductRepository.getProductRepository().getProducts()) {
                System.out.println(tempProduct);
        }
        showMainMenu();
    }

    private static void showCategories() {
        for (ProductCategory tempProductCategory : ProductCategoryRepository.getProductCategoryRepository().getCategories()) {
            System.out.println(tempProductCategory);
        }
        showMainMenu();
    }

    private static void addProduct() {
        System.out.println("Wpisz nazwę produktu:");
        String name = scanner.nextLine();
        System.out.println("Wpisz ilość:");
        int amount = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz kod kreskowy:");
        int barcode = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz kategorię:");
        String category = scanner.nextLine();

        SQLDb.saveProduct(name, amount, barcode, category);
        showMainMenu();
    }

    private static void addCategory() {
        System.out.println("Wpisz nazwę kategorii:");
        String newCategory = scanner.nextLine();
        SQLDb.saveCategory(newCategory);
        showMainMenu();
    }

    private static void showProductsByCategory() {
        System.out.println("Wpisz kategorię:");
        String category = scanner.nextLine();

        for (Product tempProduct : ProductRepository.getProductRepositoryByCategory(category).getProducts()) {
            System.out.println(tempProduct);
        }
        showMainMenu();
    }

    private static void deleteCategory() {
        System.out.println("Wpisz kategorię:");
        String category = scanner.nextLine();
        if (category.equals("Brak kategorii")) {
            System.out.println("Nie można usunąć tej kategorii");
            deleteCategory();
        }
        SQLDb.updateProduct(category);
        SQLDb.updateCategory(category);
        showMainMenu();
    }
}