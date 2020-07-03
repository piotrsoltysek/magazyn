package pl.camp.it.db;

import pl.camp.it.model.Product;
import java.util.List;

public class ProductRepository {
    private static List<Product> products;
    private static final ProductRepository productRepository = new ProductRepository();

    public List<Product> getProducts() {
        return this.products;
    }

    public static ProductRepository getProductRepository() {
        products = SQLDb.getAllProducts();
        return productRepository;
    }


    public static ProductRepository getProductRepositoryByCategory(String whatCategory) {
        products = SQLDb.getAllProductsByCategory(whatCategory);
        return productRepository;
    }
}