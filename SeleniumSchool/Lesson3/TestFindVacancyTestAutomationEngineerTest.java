import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFindVacancyTestAutomationEngineerTest {

    /**
     * Open CTCo page
     * Open Menu ->Careers-> Vacancies
     * Select test automation vacancy
     * Verify skills
     */

    @Test
    public void testFindVacancyTestAutomationEngineer() {
        System.setProperty("webdriver.gecko.driver", "C:\\SRDEV\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://www.ctco.lv");

        new CTCoSidebar(driver)
                .openVacancies()
                .clickOnVacancy("Test Automation Engineer")
                .verifyQASkills();
    }
}
