package pl.camp.it.model;

public class Product {
    private int id;
    private String name;
    private int amount;
    private long barcode;
    private String category;

    public Product(int id, String name, int amount, long barcode, String category) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
