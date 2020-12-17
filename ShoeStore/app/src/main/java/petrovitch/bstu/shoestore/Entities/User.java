package petrovitch.bstu.shoestore.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "login", unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String name;
    private String login;
    private byte[] password;
    private String email;

    public void setRole(String role) {
        this.role = role;
    }

    @ColumnInfo(defaultValue = "USER")
    private String role;

    public User(String name, String login, byte[] password, String email, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getRole() {
        return role;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
