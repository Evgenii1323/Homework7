import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.lang.reflect.Executable;
import java.util.stream.Stream;

public class GeoServiceTests {

    @BeforeAll
    public static void startAllTests() {
        System.out.println("Начало тестирования");
    }

    @AfterAll
    public static void endAllTests() {
        System.out.println("Конец тестирования");
    }

    @MethodSource("source")

    @ParameterizedTest
    public void byIp(String ip, Location location) {
        GeoServiceImpl test = new GeoServiceImpl();
        Location result = test.byIp(ip);
        Assertions.assertEquals(location.getCountry(), result.getCountry());
        Assertions.assertEquals(location.getStreet(), result.getStreet());
        Assertions.assertEquals(location.getBuiling(), result.getBuiling());
        Assertions.assertEquals(location.getCity(), result.getCity());
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null,  0)));
    }
    @Test
    public void byCoordinates() {
        GeoServiceImpl test = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class, () -> test.byCoordinates(100.5, 100.5));
    }

}