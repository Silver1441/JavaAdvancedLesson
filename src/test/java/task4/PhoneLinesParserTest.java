package task4;

import com.kishkan.epam.task4.service.PhoneLinesParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PhoneLinesParserTest {
    private PhoneLinesParser phoneLinesParser;

    @BeforeEach
    public void Init() {
        phoneLinesParser = new PhoneLinesParser();
    }

    @Test
    public void parsePhoneNumbers() {
        String input = "The call from number +4(456) 344 12 43 to +(3412)565656; \n another call " +
                "from +1(12)7-65-65-65 to + +7(912) 752 65 92";
        String result = phoneLinesParser.parsePhoneNumbers(input);
        String expected = "44563441243\n79127526592\n";
        assertThat(result, equalTo(expected));
    }
}
