package pl.camp.it.db;

import pl.camp.it.model.Product;
import pl.camp.it.model.ProductCategory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDb {
    public static final Connection connection = connect();

    private static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://79.96.53.155:3306/18942511_magazyn?useUnicode=true&useJDBCCompliantTimezoneShift" +
                            "=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "18942511_magazyn", "piotrek123");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Brak połączenia z bazą");
        return null;
    }

    public static void saveProduct(String name, int amount, long barcode, String category) {

        ProductCategory tempProductCategory = null;

        for (ProductCategory productCategory : getAllCategories()) {
            if (productCategory.getName().equals(category)) {
                tempProductCategory = productCategory;
            }
        }

        if (tempProductCategory != null) {
            try {
                String sql = "INSERT INTO tproduct (name, amount, barcode, categoryId) VALUES (?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, amount);
                preparedStatement.setLong(3, barcode);
                preparedStatement.setInt(4, tempProductCategory.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Nie ma takiej kategorii");
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> resultList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tproduct");

            while (wyniki.next()) {
                int id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                int amount = wyniki.getInt("amount");
                long barcode = wyniki.getLong("barcode");
                int categoryId = wyniki.getInt("categoryId");

                resultList.add(new Product(id, name, amount, barcode, getProductCategoryById(categoryId)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static List<ProductCategory> getAllCategories() {
        List<ProductCategory> resultList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tcategory WHERE deleted = false;");

            while (wyniki.next()) {
                int id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                boolean deleted = wyniki.getBoolean("deleted");

                resultList.add(new ProductCategory(id, name, deleted));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static ProductCategory getProductCategoryById(int id) {
        ProductCategory tempProductCategory = null;

        for (ProductCategory productCategory : getAllCategories()) {
            if (productCategory.getId() == id) {
                tempProductCategory = productCategory;
            }
        }
        return tempProductCategory;
    }

    public static ProductCategory getProductCategoryByName(String name) {
        ProductCategory tempProductCategory = null;

        for (ProductCategory productCategory : getAllCategories()) {
            if (productCategory.getName().equals(name)) {
                tempProductCategory = productCategory;
            }
        }

        if (tempProductCategory != null) {
            return tempProductCategory;
        } else {
            System.out.println("Brak podanej kategorii");
            return null;
        }
    }

        public static void saveCategory(String name) {
        try {
            String sql = "INSERT INTO tcategory (name, deleted) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, false);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Product> getAllProductsByCategory(String category) {

        ProductCategory productCategory = getProductCategoryByName(category);

        List<Product> resultList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tproduct WHERE categoryId = '" + productCategory.getId() + "'");

            while (wyniki.next()) {
                int id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                int amount = wyniki.getInt("amount");
                long barcode = wyniki.getLong("barcode");
                int category2 = wyniki.getInt("categoryId");

                resultList.add(new Product(id, name, amount, barcode, getProductCategoryById(category2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static void deleteCategory(String name) {

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE tcategory SET deleted = true WHERE name = '" + name + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void updateProductWithDeletedProductCategory(String name) {
        ProductCategory productCategory = getProductCategoryByName(name);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE tproduct SET categoryId = 1 WHERE categoryId = '" + productCategory.getId() + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean productCategoryExist(String category) {
        for (ProductCategory productCategory : getAllCategories()) {
            if (productCategory.getName().equals(category)) {
                return true;
            }
        }
        return false;
    }
}