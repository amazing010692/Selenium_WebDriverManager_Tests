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
- Use `WaitUtils.waitForVisibility()` instead of `Thread.sleep()`
- Check if the element is inside an iframe and switch to it first

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
