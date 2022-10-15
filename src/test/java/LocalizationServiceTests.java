import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceTests {

    @BeforeAll
    public static void startAllTests() {
        System.out.println("Начало тестирования");
    }

    @AfterAll
    public static void endAllTests() {
        System.out.println("Конец тестирования");
    }

    @MethodSource ("source")

    @ParameterizedTest

    public void localeTest(Country country, String text) {
        LocalizationServiceImpl test = new LocalizationServiceImpl();
        String result = test.locale(country);
        Assertions.assertEquals(text, result);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(Country.BRAZIL,  "Welcome"),
                Arguments.of(Country.GERMANY,  "Welcome"),
                Arguments.of(Country.RUSSIA,  "Добро пожаловать"),
                Arguments.of(Country.USA,  "Welcome"));
    }
}