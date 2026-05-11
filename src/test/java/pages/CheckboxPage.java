package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object for the Checkboxes demo page.
 */
public class CheckboxPage extends BasePage {

    private static final String URL = "http://www.tizag.com/htmlT/htmlcheckboxes.php";
    private static final By CHECKBOXES = By.name("sports");

    public CheckboxPage(WebDriver driver) {
        super(driver);
    }

    public CheckboxPage open() {
        driver.get(URL);
        return this;
    }

    public List<WebElement> getAllCheckboxes() {
        return driver.findElements(CHECKBOXES);
    }

    public int getCheckboxCount() {
        return getAllCheckboxes().size();
    }

    public CheckboxPage selectAll() {
        for (WebElement cb : getAllCheckboxes()) {
            if (!cb.isSelected()) {
                cb.click();
            }
        }
        return this;
    }

    public CheckboxPage selectByIndex(int index) {
        List<WebElement> checkboxes = getAllCheckboxes();
        if (index < checkboxes.size() && !checkboxes.get(index).isSelected()) {
            checkboxes.get(index).click();
        }
        return this;
    }

    public boolean isChecked(int index) {
        return getAllCheckboxes().get(index).isSelected();
    }
}
