package myproj.api.common.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@UtilityClass
public class PasswordUtils {

    public static String getEncryptedPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean isPasswordEqualBetween(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }
}
