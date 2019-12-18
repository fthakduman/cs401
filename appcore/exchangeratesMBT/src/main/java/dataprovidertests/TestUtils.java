package dataprovidertests;



import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;

public final class TestUtils {


    private TestUtils() {}

    public static final String ADMIN_ROLE= "ADMIN";
    public static final String STANDART_ROLE= "STANDART";
    public static final String PREMIUM_ROLE= "PREMIUM";
    public static  ArrayList<String> PROVIDER_URLS;
    private static ArrayList<String> getProviderUrls(){
        PROVIDER_URLS = new ArrayList<String>();
        PROVIDER_URLS.add("localhost:8093/dataprovider/api/v1/rank/last6h");
        PROVIDER_URLS.add("localhost:8093/dataprovider/api/v1/rank/last12h");
        PROVIDER_URLS.add("localhost:8093/dataprovider/api/v1/rank/last18h");
        Random random = new Random();
        int maxMonth = 12;
        int maxHour = 23;
        int minHour = 0;
        int min = 1;
        int maxDay = 31;


        PROVIDER_URLS.add("localhost:8093/dataprovider/api/v1/rank/"+ Month.of(random.nextInt(maxMonth + 1 - min) + min).name() +"/"+
                random.nextInt(maxDay + 1 - min) + min);
        PROVIDER_URLS.add("localhost:8093/dataprovider/api/v1/rank/"+ Month.of(random.nextInt(maxMonth + 1 - min) + min).name() +"/"+
                random.nextInt(maxDay + 1 - min) + min + "/" +
                random.nextInt(maxHour + 1 - minHour) + minHour);

        return PROVIDER_URLS;
    }

}
