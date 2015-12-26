package ca.etsmtl.applets.seemobile.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gnut3ll4 on 26/12/15.
 */
public class Utils {
    public static boolean isUsernameValid(String username) {
        Pattern p = Pattern.compile("[a-zA-Z]{2}[0-9]{5}");
        Matcher m = p.matcher(username);
        return m.matches();
    }
}
