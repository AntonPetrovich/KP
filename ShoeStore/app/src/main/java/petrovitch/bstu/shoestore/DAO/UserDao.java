package petrovitch.bstu.shoestore.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import petrovitch.bstu.shoestore.Entities.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM users WHERE login = :userLogin")
    User getUserByLogin(String userLogin);
}
