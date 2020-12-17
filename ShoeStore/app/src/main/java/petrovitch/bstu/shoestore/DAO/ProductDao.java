package petrovitch.bstu.shoestore.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import petrovitch.bstu.shoestore.Entities.Product;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Delete
    void delete(Product product);

    @Update
    void update(Product product);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();

    @Query("SELECT * FROM products WHERE price = :productPrice")
    List<Product> getAllProductsByPrice(String productPrice);

    @Query("SELECT * FROM products WHERE productName = :productName")
    Product getProductByName(String productName);

}
