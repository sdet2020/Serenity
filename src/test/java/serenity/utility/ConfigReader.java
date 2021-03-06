package serenity.utility;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;


public class ConfigReader {
    private EnvironmentVariables environmentVariables ; // this is how it should be according to the documentation

    @Step
    public String getProperty(String propertyName){
        return EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty(propertyName);
    }
}
