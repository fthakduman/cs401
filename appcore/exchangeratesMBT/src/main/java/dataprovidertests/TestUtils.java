package dataprovidertests;


import provider.base.util.CommonUtils;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public final class TestUtils {


    private TestUtils() {
    }

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String STANDART_ROLE = "STANDART";
    public static final String PREMIUM_ROLE = "PREMIUM";
    public static final String REGISTER_USER = "http://localhost:8093/userapi/api/registeruser";
    public static final String DELETE_USER = "http://localhost:8093/userapi/api/user/";
    public static final String UPDATE_USER = "http://localhost:8093/userapi/api/user/";
    public static final String DELETE_USER_ALL = "http://localhost:8093/userapi/api/user/deleteall";
    public static ArrayList<String> PROVIDER_URLS;
    public static final ArrayList<String> USER_NAMES = new ArrayList<String>();
    public static final ArrayList<String> PASSWORDS = new ArrayList<String>();
    public static final ArrayList<String> ROLES = new ArrayList<String>();
    public static final ArrayList<String> MANIPULATED_USER_NAMES = new ArrayList<String>();
    public static final ArrayList<String> MANIPULATED_PASSWORDS = new ArrayList<String>();
    public static final ArrayList<String> MANIPULATED_ROLES = new ArrayList<String>();
    public static List<Map<String, String>> bankNames = new ArrayList<Map<String, String>>();

    public static String getProviderUrl() {
        PROVIDER_URLS = new ArrayList<String>();
        PROVIDER_URLS.add("http://localhost:8092/dataprovider/api/v1/rank/last6h");
        PROVIDER_URLS.add("http://localhost:8092/dataprovider/api/v1/rank/last12h");
        PROVIDER_URLS.add("http://localhost:8092/dataprovider/api/v1/rank/last18h");

        int maxMonth = 12;
        int maxHour = 23;
        int minHour = 0;
        int min = 1;
        int maxDay = 31;

        Random random = new Random();
        PROVIDER_URLS.add("http://localhost:8092/dataprovider/api/v1/rank/" + Month.of(random.nextInt(maxMonth + 1 - min) + min).name() + "/" +
                random.nextInt(maxDay + 1 - min) + min);
        PROVIDER_URLS.add("http://localhost:8092/dataprovider/api/v1/rank/" + Month.of(random.nextInt(maxMonth + 1 - min) + min).name() + "/" +
                random.nextInt(maxDay + 1 - min) + min + "/" +
                random.nextInt(maxHour + 1 - minHour) + minHour);

        return PROVIDER_URLS.get(random.nextInt(PROVIDER_URLS.size()));
    }

    public static String getRole() {
        Random random = new Random();
        return ROLES.get(random.nextInt(ROLES.size()));
    }
    public static String getManipulatedRole() {
        Random random = new Random();
        return MANIPULATED_ROLES.get(random.nextInt(MANIPULATED_ROLES.size()));
    }

    public static String getUsername() {
        Random random = new Random();
        return USER_NAMES.get(random.nextInt(USER_NAMES.size()));
    }
    public static String getManipulatedUserName() {
        Random random = new Random();
        return MANIPULATED_USER_NAMES.get(random.nextInt(MANIPULATED_USER_NAMES.size()));
    }
    public static String getPassword() {
        Random random = new Random();
        return PASSWORDS.get(random.nextInt(PASSWORDS.size()));
    }
    public static String getManipulatedPassword() {
        Random random = new Random();
        return MANIPULATED_PASSWORDS.get(random.nextInt(MANIPULATED_PASSWORDS.size()));
    }

    public static List<Map<String, String>> getBankNames() {
        Random random = new Random();

        int removecount = random.nextInt(bankNames.size());
        for (int i = 0; i < removecount; i++) {
            bankNames.remove(random.nextInt(bankNames.size()));
        }

        return bankNames;
    }

    public static void assignValues() {
        USER_NAMES.add("mustafa");
        USER_NAMES.add("fatih");
        USER_NAMES.add("ozu");
        USER_NAMES.add("selin");
        USER_NAMES.add("jack");
        USER_NAMES.add("sheldon");
        USER_NAMES.add("penny");
        MANIPULATED_USER_NAMES.addAll(USER_NAMES);
        MANIPULATED_USER_NAMES.add("selim");
        MANIPULATED_USER_NAMES.add("musa");
        MANIPULATED_USER_NAMES.add("nicholson");

        PASSWORDS.add("mustafapass");
        PASSWORDS.add("fatihpass");
        PASSWORDS.add("ozupass");
        PASSWORDS.add("selinpasss");
        PASSWORDS.add("jackpassw");
        PASSWORDS.add("sheldonpasswd");
        PASSWORDS.add("pennypasswd ");
        MANIPULATED_PASSWORDS.addAll(PASSWORDS);
        MANIPULATED_PASSWORDS.add("xyz");
        MANIPULATED_PASSWORDS.add("abc");
        MANIPULATED_PASSWORDS.add("def");


        HashMap<String, String> ykb = new HashMap<String, String>();
        ykb.put("bankName", CommonUtils.ykb);
        HashMap<String, String> isb = new HashMap<String, String>();
        isb.put("bankName", CommonUtils.isb);
        HashMap<String, String> dnz = new HashMap<String, String>();
        dnz.put("bankName", CommonUtils.dnz);
        HashMap<String, String> mockBank = new HashMap<String, String>();
        mockBank.put("bankName", "mockBank");
        bankNames.add(ykb);
        bankNames.add(isb);
        bankNames.add(dnz);
        bankNames.add(mockBank);

        ROLES.add(ADMIN_ROLE);
        ROLES.add(STANDART_ROLE);
        ROLES.add(PREMIUM_ROLE);

        MANIPULATED_ROLES.addAll(ROLES);
        MANIPULATED_ROLES.add("GOLD");
        MANIPULATED_ROLES.add("PLATINIUM");


    }
}
