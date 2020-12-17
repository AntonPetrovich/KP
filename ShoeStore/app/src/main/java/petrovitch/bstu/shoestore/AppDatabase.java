package petrovitch.bstu.shoestore;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SupportFactory;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import petrovitch.bstu.shoestore.DAO.ProductDao;
import petrovitch.bstu.shoestore.DAO.UserDao;
import petrovitch.bstu.shoestore.Entities.Product;
import petrovitch.bstu.shoestore.Entities.User;

@Database(entities = {User.class, Product.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static char[] testBytes = {'t', 'e', 's', 't'};
    static final byte[] passphrase = SQLiteDatabase.getBytes(testBytes);
    static final SupportFactory factory = new SupportFactory(passphrase);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).openHelperFactory(factory).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                UserDao dao = INSTANCE.userDao();


                User user = null;
                try {
                    user = new User("Anton", "anton7788", Encryption.encrypt("123456"), "anonydragon@mail.ru", "ADMIN");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
                dao.insert(user);

            });
        }
    };
}
