# Architecture Overview

## Design Patterns

This project follows a **modular test automation architecture** with:

- **Base Test Pattern** — Common setup/teardown logic in `BaseTest`
- **Utility Pattern** — Reusable helpers in `utils/` package
- **Configuration Pattern** — Externalized config via `config.properties`
- **Data-Driven Pattern** — Excel-based test data via `ExcelUtils`

## Package Structure

```
src/test/java/
├── base/           → Base classes with shared WebDriver lifecycle
├── config/         → Configuration readers and constants
├── testcases/      → Test classes organized by feature
└── utils/          → Reusable utility classes
```

## Data Flow

```
config.properties → ConfigReader → BaseTest → TestCase
                                       ↓
                                  WebDriver
                                       ↓
                              ScreenshotUtils / WaitUtils
```

## Key Decisions

| Decision | Rationale |
|----------|-----------|
| Selenium 4.x | Latest stable with built-in WebDriverManager support |
| TestNG | Rich annotations, parallel execution, data providers |
| Log4j2 | Industry-standard structured logging |
| Maven | Standard build tool with dependency management |
| Properties file | Simple externalized configuration |

## Extending the Framework

1. Add new test classes in `testcases/` extending `BaseTest`
2. Add reusable methods in `utils/`
3. Add page objects in a new `pages/` package (recommended for scaling)
4. Add test data files in `src/test/resources/testdata/`
