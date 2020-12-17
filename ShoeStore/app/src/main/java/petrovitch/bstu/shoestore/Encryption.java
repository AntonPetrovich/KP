package petrovitch.bstu.shoestore;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encryption {
    public static byte[] encrypt(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[]salt = new byte[]{1, 3, 5, 7};
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[]hashPass = factory.generateSecret(spec).getEncoded();
        return hashPass;
    }
}