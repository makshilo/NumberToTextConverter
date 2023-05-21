import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jazzteam.shilomy.numberToTextConverter.converter.ConverterChain;
import com.jazzteam.shilomy.numberToTextConverter.converter.impl.BillionHandler;
import com.jazzteam.shilomy.numberToTextConverter.converter.impl.HundredsHandler;
import com.jazzteam.shilomy.numberToTextConverter.converter.impl.MillionHandler;
import com.jazzteam.shilomy.numberToTextConverter.converter.impl.ThousandsHandler;

/**
 * Набор тестов конвертера чисел
 */
public class ConverterTest {

    /**
     * Цепочка конвертеров
     */
    private static ConverterChain converterChain;

    @BeforeAll
    public static void setUp() {
        converterChain = ConverterChain.link(
            new BillionHandler(),
            new MillionHandler(),
            new ThousandsHandler(),
            new HundredsHandler()
        );
    }

    @ParameterizedTest
    @DisplayName("Параметризованный данными тест")
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    public void testCalculation(long value, String expectedResult) {
        assertEquals(expectedResult, converterChain.handle(value, new StringBuilder()));
    }
}
