# Troubleshooting Guide

## Common Issues

### 1. "ChromeDriver version mismatch"

**Cause:** WebDriverManager couldn't resolve the correct driver version.

**Fix:** Clear the WebDriverManager cache:
```bash
rm -rf ~/.cache/selenium
```

### 2. "SessionNotCreatedException"

**Cause:** Browser version is incompatible with the driver.

**Fix:** Update your browser to the latest version, then re-run tests.

### 3. Tests fail with "Element not found"

**Cause:** Page hasn't loaded or element is inside an iframe.

**Fix:**
- Use `WaitUtils.waitForVisibility()` or `BasePage.waitAndFind()` instead of `Thread.sleep()`
- Check if the element is inside an iframe and switch to it first
- Verify locators in the page object class are correct

### 4. Maven build fails with dependency errors

**Fix:**
```bash
mvn clean install -U
```
The `-U` flag forces Maven to update snapshots and releases.

### 5. Tests pass locally but fail in CI

**Cause:** CI runs in headless mode; some elements behave differently.

**Fix:** Ensure tests use explicit waits and don't rely on visual rendering.

### 6. "java.lang.UnsupportedClassVersionError"

**Cause:** Running with an older JDK version.

**Fix:** Ensure JDK 17+ is installed:
```bash
java -version
```

### 7. Screenshots not being saved

**Cause:** Target directory doesn't exist.

**Fix:** The `target/screenshots/` directory is created automatically. Run `mvn test` to trigger directory creation.

### 8. "ParameterException: Parameter 'browser' is required"

**Cause:** Test is run outside of testng.xml without the browser parameter.

**Fix:** The `@Optional` annotation on `BaseTest.setUp()` handles this. If you see this error, ensure your test class extends `BaseTest` and doesn't override `setUp()` without `@Optional`.

### 9. Page object returns stale elements

**Cause:** Page has reloaded or DOM has changed after an action.

**Fix:**
- Re-fetch elements after page navigation (don't cache `WebElement` references as fields)
- Use `BasePage.waitAndFind()` which always fetches fresh elements

### 10. Tests interfere with each other in parallel execution

**Cause:** Shared state between tests.

**Fix:**
- Each `@Test` method gets its own `driver` instance via `BaseTest`
- Page objects should be instantiated per test method, not shared across tests
- Avoid `static` mutable state in page objects
