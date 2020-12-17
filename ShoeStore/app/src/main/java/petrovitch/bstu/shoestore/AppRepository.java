package petrovitch.bstu.shoestore;

import android.app.Application;

import androidx.room.Query;

import petrovitch.bstu.shoestore.DAO.ProductDao;
import petrovitch.bstu.shoestore.DAO.UserDao;
import petrovitch.bstu.shoestore.Entities.Product;
import petrovitch.bstu.shoestore.Entities.User;

import java.util.List;

public class AppRepository {

    private UserDao userDao;
    private ProductDao productDao;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        productDao = db.productDao();
    }

    public void insertUser(User user){
        userDao.insert(user);
    }

    public User getUserByLogin(String userLogin){
        return userDao.getUserByLogin(userLogin);
    }

    public void addProduct(Product product){
        productDao.insert(product);
    }

    public void deleteProduct(Product product){
        productDao.delete(product);
    }

    public void updateProduct(Product product){
        productDao.update(product);
    }

    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }
}
