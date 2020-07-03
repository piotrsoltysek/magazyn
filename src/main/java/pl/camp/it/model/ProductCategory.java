package pl.camp.it.model;

public class ProductCategory {
    int id;
    String name;
    boolean deleted;

    public ProductCategory(int id, String name, boolean deleted) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}