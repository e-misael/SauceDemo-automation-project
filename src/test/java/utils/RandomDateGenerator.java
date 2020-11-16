package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RandomDateGenerator {
    public static String generateTimestampToFile() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        return new SimpleDateFormat("yyyyMMddhhmmss").format(ts);
    }

}
