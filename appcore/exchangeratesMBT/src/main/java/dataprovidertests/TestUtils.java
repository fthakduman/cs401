package dataprovidertests;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;

public final class TestUtils {


    private TestUtils() {
    }

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String STANDART_ROLE = "STANDART";
    public static final String PREMIUM_ROLE = "PREMIUM";
    public static final String REGISTER_USER = "localhost:8092/userapi/registeruser";
    public static ArrayList<String> PROVIDER_URLS;
    public static final ArrayList<String> USER_NAMES = new ArrayList<String>();
    public static final ArrayList<String> PASSWORDS = new ArrayList<String>();

    public static String getProviderUrl() {
        PROVIDER_URLS = new ArrayList<String>();
        PROVIDER_URLS.add("localhost:8092/dataprovider/api/v1/rank/last6h");
        PROVIDER_URLS.add("localhost:8092/dataprovider/api/v1/rank/last12h");
        PROVIDER_URLS.add("localhost:8092/dataprovider/api/v1/rank/last18h");

        int maxMonth = 12;
        int maxHour = 23;
        int minHour = 0;
        int min = 1;
        int maxDay = 31;

        Random random = new Random();
        PROVIDER_URLS.add("localhost:8092/dataprovider/api/v1/rank/" + Month.of(random.nextInt(maxMonth + 1 - min) + min).name() + "/" +
                random.nextInt(maxDay + 1 - min) + min);
        PROVIDER_URLS.add("localhost:8092/dataprovider/api/v1/rank/" + Month.of(random.nextInt(maxMonth + 1 - min) + min).name() + "/" +
                random.nextInt(maxDay + 1 - min) + min + "/" +
                random.nextInt(maxHour + 1 - minHour) + minHour);

        return PROVIDER_URLS.get(random.nextInt(PROVIDER_URLS.size()));
    }

    public static String getRole() {
        Random random = new Random();
        ArrayList<String> roles = new ArrayList<String>();
        roles.add(ADMIN_ROLE);
        roles.add(STANDART_ROLE);
        roles.add(PREMIUM_ROLE);

        return roles.get(random.nextInt(roles.size()));
    }

    public static String getUsername() {
        Random random = new Random();
        USER_NAMES.add("mustafa");
        USER_NAMES.add("fatih");
        USER_NAMES.add("ozu");
        USER_NAMES.add("selin");
        USER_NAMES.add("jack");
        USER_NAMES.add("sheldon");
        USER_NAMES.add("penny");
        return USER_NAMES.get(random.nextInt(USER_NAMES.size()));

    }
    public static String getPassword() {
        Random random = new Random();
        PASSWORDS.add("mustafa");
        PASSWORDS.add("fatih");
        PASSWORDS.add("ozu");
        PASSWORDS.add("selin");
        PASSWORDS.add("jack");
        PASSWORDS.add("sheldon");
        PASSWORDS.add("penny");
        return PASSWORDS.get(random.nextInt(PASSWORDS.size()));
    }

}
