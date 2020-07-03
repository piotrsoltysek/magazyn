package pl.camp.it.db;

import pl.camp.it.model.ProductCategory;
import java.util.List;

public class ProductCategoryRepository {
    private static List<ProductCategory> categories;
    private static final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();

    public List<ProductCategory> getCategories() {
        return this.categories;
    }

    public static ProductCategoryRepository getProductCategoryRepository() {
        categories = SQLDb.getAllCategories();
        return productCategoryRepository;
    }
}