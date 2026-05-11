package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckboxPage;

/**
 * Tests for checkbox interactions using Page Object Model.
 */
public class TestCheckboxes extends BaseTest {

    @Test
    public void testSelectAllCheckboxes() {
        CheckboxPage page = new CheckboxPage(driver).open();

        int count = page.getCheckboxCount();
        logger.info("Total checkboxes found: {}", count);
        Assert.assertTrue(count > 0, "No checkboxes found on page");

        page.selectAll();

        for (int i = 0; i < count; i++) {
            Assert.assertTrue(page.isChecked(i), "Checkbox " + i + " was not selected");
        }
    }

    @Test
    public void testSelectSingleCheckbox() {
        CheckboxPage page = new CheckboxPage(driver).open();

        page.selectByIndex(0);
        Assert.assertTrue(page.isChecked(0), "First checkbox was not selected");
    }
}
