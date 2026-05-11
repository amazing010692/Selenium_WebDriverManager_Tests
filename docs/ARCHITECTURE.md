# Architecture Overview

## Design Patterns

This project follows a **modular test automation architecture** with:

- **Page Object Model (POM)** — Encapsulates page locators and interactions in dedicated classes
- **Base Test Pattern** — Common setup/teardown logic in `BaseTest`
- **Base Page Pattern** — Common page interaction methods in `BasePage`
- **Utility Pattern** — Reusable helpers in `utils/` package
- **Configuration Pattern** — Externalized config via `config.properties`
- **Data-Driven Pattern** — Excel-based test data via `ExcelUtils`

## Package Structure

```
src/test/java/
├── base/           → Base classes with shared WebDriver lifecycle
├── config/         → Configuration readers and constants
├── pages/          → Page Object Model classes (locators + actions)
├── testcases/      → Test classes organized by feature
└── utils/          → Reusable utility classes
```

## Data Flow

```
config.properties → ConfigReader → BaseTest → TestCase
                                       ↓            ↓
                                  WebDriver → Page Objects (BasePage)
                                       ↓
                              ScreenshotUtils / WaitUtils
```

## Page Object Model

Each page in the application under test has a corresponding page class:

```
BasePage (abstract)
├── CheckboxPage    → Checkbox interactions
├── WebTablePage    → Table data extraction
└── [YourPage]      → Add new pages here
```

**Rules for page objects:**
- Extend `BasePage` for common methods (click, type, waitAndFind)
- Keep locators `private static final` within the page class
- Return `this` for method chaining where appropriate
- Never include assertions — assertions belong in test classes

## Key Decisions

| Decision | Rationale |
|----------|-----------|
| Page Object Model | Separates locators from test logic, improves maintainability |
| Selenium 4.x | Latest stable with built-in WebDriverManager support |
| TestNG | Rich annotations, parallel execution, data providers |
| Log4j2 | Industry-standard structured logging |
| Maven | Standard build tool with dependency management |
| Properties file | Simple externalized configuration |
| `@Optional` parameter | Tests work with or without testng.xml browser parameter |

## Extending the Framework

1. **Add a new page object:** Create a class in `pages/` extending `BasePage`
2. **Add a new test:** Create a class in `testcases/` extending `BaseTest`, inject page objects
3. **Add reusable methods:** Add to `utils/` or `BasePage` depending on scope
4. **Add test data:** Place Excel files in `src/test/resources/testdata/`
5. **Add to suite:** Register new test classes in `testng.xml`
