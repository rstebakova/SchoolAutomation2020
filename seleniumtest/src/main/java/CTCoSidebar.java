import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CTCoPage {

    private WebDriver driver;


    public CTCoPage(WebDriver driver) {
        this.driver = driver;
    }

    //   @Step
    public CTСoMenu hoverDesignMenu() {
        hoverMenu("Design");
        return new CTСoMenu();
    }
    public WebDriver getDriver() {
        return driver;
    }

   // @Step
    public CTCoPage hoverMenu(String menuName) {
        List<WebElement> menuItems = getDriver().findElements(By.cssSelector("#menu-main >li"));
        Optional<WebElement> ourMenuItem = menuItems.stream().filter(item -> {
            return item.findElement(By.cssSelector("a")).getText().equals(menuName);
        }).findFirst();

        if (ourMenuItem.isPresent()) {
            new Actions(getDriver())
                    .pause(500)
                    .moveToElement(ourMenuItem.get())
                    .build()
                    .perform();
        } else {
            throw new AssertionError("Menu item not found");
        }
        return this;
    }

    public void open(String menuName) {
        List<WebElement> menuItems = driver.findElements(By.cssSelector(".sub-menu >li a"));
        Optional<WebElement> ourMenuItem = menuItems.stream().filter(item -> item.getText().equals(menuName)).findFirst();

        if (ourMenuItem.isPresent()) {
            ourMenuItem.get().click();
            return;
        }

        throw new AssertionError("Menu item not found");
    }

     public CTCoVacancies openVacancies() {
        hoverMenu("CAREERS").open("VACANCIES");
        return new CTCoVacancies(driver);
    }
}
