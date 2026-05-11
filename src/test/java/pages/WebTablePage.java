package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object for web table interactions.
 */
public class WebTablePage extends BasePage {

    private static final String URL = "https://money.rediff.com/gainers/bsc/daily/groupa";
    private static final By TABLE_ROWS = By.xpath("//*[@class='dataTable']/tbody/tr");
    private static final By TABLE_COLS = By.xpath("//*[@class='dataTable']/tbody/tr[1]/td");

    public WebTablePage(WebDriver driver) {
        super(driver);
    }

    public WebTablePage open() {
        driver.get(URL);
        return this;
    }

    public int getRowCount() {
        return driver.findElements(TABLE_ROWS).size();
    }

    public int getColumnCount() {
        return driver.findElements(TABLE_COLS).size();
    }

    public String getCellText(int row, int col) {
        By cell = By.xpath("//*[@class='dataTable']/tbody/tr[" + row + "]/td[" + col + "]");
        return getText(cell);
    }

    public List<WebElement> getRow(int rowIndex) {
        return driver.findElements(
                By.xpath("//*[@class='dataTable']/tbody/tr[" + rowIndex + "]/td"));
    }
}
