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
        System.out.println("------------------------");
        System.out.println("1. Dostępne produkty");
        System.out.println("2. Dostępne produkty z danej kategorii");
        System.out.println("3. Dostępne kategorie");
        System.out.println("4. Dodaj produkt");
        System.out.println("5. Dodaj kategorię");
        System.out.println("6. Usuń kategorię");
        System.out.println("7. Exit");
        System.out.print("Podaj cyfrę: ");

        String choose = scanner.nextLine();

        switch (choose) {
            case "1":
                showProducts();
                break;
            case "2":
                showProductsByCategory();
                break;
            case "3":
                showCategories();
                break;
            case "4":
                addProduct();
                break;
            case "5":
                addCategory();
                break;
            case "6":
                deleteCategory();
                break;
            case "7":
                SQLDb.closeConnection();
                System.exit(0);
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
        long barcode = Long.parseLong(scanner.nextLine());
        System.out.println("Wpisz kategorię:");
        String category = scanner.nextLine();

        SQLDb.saveProduct(name, amount, barcode, category);
        System.out.println("Dodano nowy produkt");
        showMainMenu();
    }

    private static void addCategory() {
        System.out.println("Wpisz nazwę kategorii:");
        String newCategory = scanner.nextLine();
        SQLDb.saveCategory(newCategory);
        System.out.println("Dodano nową kategorię");
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
        System.out.println("Kategoria została usunięta");
        showMainMenu();
    }
}