package petrovitch.bstu.shoestore.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private Long pid;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product(String productName, double price, String imagePath, String description) {
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
    }

    @Ignore
    public Product(Long pid, String productName, double price, String imagePath, String description) {
        this.pid = pid;
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
    }

    private String productName;

    private double price;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    private String description;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
