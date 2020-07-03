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



    public static void saveProduct(String name, int amount, int barcode, String category) {
        try {

            String sql = "INSERT INTO tproduct (name, amount, barcode, category) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, amount);
            preparedStatement.setInt(3, barcode);
            preparedStatement.setString(4, category);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static List<Product> getAllProducts() {
        List<Product> resultList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tproduct");

            while (wyniki.next()) {
                Integer id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                Integer amount = wyniki.getInt("amount");
                Integer barcode = wyniki.getInt("barcode");
                String category = wyniki.getString("category");

                resultList.add(new Product(id, name, amount, barcode, category));
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
                Integer id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                Boolean deleted = wyniki.getBoolean("deleted");

                resultList.add(new ProductCategory(id, name, deleted));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
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

    public static List<Product> getAllProductsByCategory(String whichCategory) {
        List<Product> resultList = new ArrayList<>();
        try {

            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tproduct WHERE category = '" + whichCategory + "'");

            while (wyniki.next()) {
                Integer id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                Integer amount = wyniki.getInt("amount");
                Integer barcode = wyniki.getInt("barcode");
                String category = wyniki.getString("category");

                resultList.add(new Product(id, name, amount, barcode, category));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static void updateCategory(String name) {

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE tcategory SET deleted = true WHERE name = '" + name + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateProduct(String name) {

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE tproduct SET category = 'Brak kategorii' WHERE category = '" + name + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }






}



