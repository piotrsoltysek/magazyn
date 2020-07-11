package pl.camp.it.model;

public class Product {
    private int id;
    private String name;
    private int amount;
    private long barcode;
    private ProductCategory category;// TODO: 11.07.2020 zmienić pole klasy z String na obiekt klasy ProductCategory

    public Product(int id, String name, int amount, long barcode, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.barcode = barcode;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", barcode=" + barcode +
                ", category='" + category + '\'' +
                '}';
    }
}
