package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.WebTablePage;

/**
 * Tests for web table interactions using Page Object Model.
 */
public class TestWebTable extends BaseTest {

    @Test
    public void testTableHasData() {
        WebTablePage page = new WebTablePage(driver).open();

        int rows = page.getRowCount();
        int cols = page.getColumnCount();

        logger.info("Table has {} rows and {} columns", rows, cols);
        Assert.assertTrue(rows > 0, "Table has no rows");
        Assert.assertTrue(cols > 0, "Table has no columns");
    }

    @Test
    public void testReadCellData() {
        WebTablePage page = new WebTablePage(driver).open();

        String firstCell = page.getCellText(1, 1);
        logger.info("First cell value: {}", firstCell);
        Assert.assertNotNull(firstCell, "First cell is null");
        Assert.assertFalse(firstCell.isEmpty(), "First cell is empty");
    }
}
