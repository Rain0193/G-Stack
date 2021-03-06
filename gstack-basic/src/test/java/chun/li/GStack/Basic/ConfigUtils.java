package chun.li.GStack.Basic;

import static java.lang.String.format;
import static java.lang.System.getenv;

public class ConfigUtils {
    public static String env(String name) {
        return getenv(name);
    }

    public static String env(String pattern, Object... args) {
        return getenv(format(pattern, args));
    }

    public static String env(String name, String defaultValue){
        String value = getenv(name);
        if(value==null)
            value = defaultValue;
        return value;
    }
}
