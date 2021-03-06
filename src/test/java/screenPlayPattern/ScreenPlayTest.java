package screenPlayPattern;

import net.serenitybdd.junit5.SerenityTest;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Disabled
@SerenityTest
@Tag("screenPlay") // mvn clean verify -tag="screenPlay"
public class ScreenPlayTest {

    @Steps
    ScreenPlaySteps mike;

    @Test
    public void testVoila() {
        // GIVEN
        mike.test1();
        // WHEN
        mike.test2();
        // THEN
        mike.test3();
    }
}
