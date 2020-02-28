import java.util.List;
import java.util.Optional;

import com.sun.org.apache.regexp.internal.RE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CTCoVacancies {
    private static final By VACANCIES_TITLE = By.cssSelector(".text-block h1");

    WebDriver driver;

    public CTCoVacancies(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 30);
       // wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(VACANCIES_TITLE), "Vacancies"));
        System.out.println(driver.findElement(VACANCIES_TITLE).getText());
    }

    public void clickOnVacancy(String vacancyName){
        List<WebElement> menuItems = driver.findElements(By.cssSelector(".second-information-menu-inner >li a"));
        Optional<WebElement> vacancyItem = menuItems.stream().filter(item -> item.getText().equals(vacancyName)).findFirst();

        if (vacancyItem.isPresent()) {
            vacancyItem.get().click();
            return;
        }

        throw new AssertionError("Vacancy not found");
    }

    public boolean isSkillsPresent (String skill){
        return
    }
}
