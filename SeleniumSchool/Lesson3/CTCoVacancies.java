import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CTCoVacancies {

    private static final By VACANCIES_TITLE = By.cssSelector(".text-block h1");

    private final By SKILLS =
            By.xpath("//h1[text()='Test Automation Engineer']/parent::div/descendant::*[text()='Professional skills and qualification:']/following" +
                    "::p[1]");

    WebDriver driver;

    public CTCoVacancies(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(VACANCIES_TITLE), "Vacancies"));
        System.out.println(driver.findElement(VACANCIES_TITLE).getText());
    }

    public CTCoVacancies clickOnVacancy(String vacancyName) {

        List<WebElement> menuItems = driver.findElements(By.cssSelector(".second-information-menu-inner >li a"));
        Optional<WebElement> vacancyItem = menuItems.stream().filter(item -> item.getText().equals(vacancyName)).findFirst();

        if (vacancyItem.isPresent()) {
            vacancyItem.get().click();
            return this;
        }

        throw new AssertionError("Vacancy not found");

    }

    public void verifyQASkills() {
        new WebDriverWait(driver, 5)
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .withMessage(" Test Automation Engineer page is not loaded")
                .until(ExpectedConditions.presenceOfElementLocated(SKILLS));

        String text = driver.findElement(SKILLS).getText();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(text.contains("Good level in English reading /speaking /writing")).isTrue();
        softAssertions.assertThat(text.contains("communication skills")).isTrue();
        softAssertions.assertThat(text.contains("Selenium")).isTrue();
        softAssertions.assertAll();
    }

}
