# Selenium WebDriverManager Test Automation Framework

A modern, modular Selenium WebDriver test automation framework built with Java, TestNG, and WebDriverManager — designed for cross-browser testing with reusable utilities and clean architecture.

---

## Author

**Prepared by:** Janielle Joy S. Gregorio  
**LinkedIn:** [linkedin.com/in/janiellejoygregorio](https://ph.linkedin.com/in/janiellejoygregorio)

---

## Project Overview

### What It Does
This project is a **Selenium WebDriver automation framework** that demonstrates:
- Cross-browser testing (Chrome, Firefox, Edge)
- Automated UI interactions (forms, checkboxes, dropdowns, tables)
- Screenshot capture (full-page, element-level, scrollable)
- Data-driven testing with Excel
- JavaScript execution within browser context
- Window/tab handling and iframe switching

### Why It Exists
- Provides a **reusable template** for Selenium test automation projects
- Demonstrates **modern best practices** in test automation architecture
- Serves as a **learning resource** for QA engineers and developers
- Showcases **enterprise-grade patterns** for portfolio presentation

### Key Features
- ✅ Cross-browser support (Chrome, Firefox, Edge)
- ✅ Automatic driver management via WebDriverManager
- ✅ Reusable base test class with setup/teardown
- ✅ Centralized configuration management
- ✅ Screenshot utilities (full-page & element-level)
- ✅ Excel-based data-driven testing
- ✅ Explicit wait utilities
- ✅ Structured logging with Log4j2
- ✅ CI/CD pipeline with GitHub Actions
- ✅ Headless execution support

---

## Architecture Overview

### Design Patterns
| Pattern | Purpose |
|---------|---------|
| Base Test | Shared WebDriver lifecycle management |
| Utility Classes | Reusable helpers (screenshots, waits, Excel) |
| Configuration Reader | Externalized, environment-aware settings |
| Data-Driven | Excel-based test data separation |

### Application Flow
```
config.properties → ConfigReader → BaseTest → Test Class
                                       ↓
                                  WebDriver Instance
                                       ↓
                              Utils (Screenshots, Waits)
```

---

## Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17 (LTS) | Programming language |
| Selenium WebDriver | 4.27.0 | Browser automation |
| WebDriverManager | 5.9.2 | Automatic driver management |
| TestNG | 7.10.2 | Test framework |
| Apache POI | 5.3.0 | Excel data reading |
| Log4j2 | 2.24.3 | Logging framework |
| Maven | 3.9+ | Build & dependency management |
| GitHub Actions | — | CI/CD pipeline |

---

## Folder Structure

```
├── .github/workflows/      → CI/CD pipeline configuration
├── docs/                   → Project documentation
│   ├── ARCHITECTURE.md     → Architecture decisions & patterns
│   └── TROUBLESHOOTING.md  → Common issues & fixes
├── src/test/
│   ├── java/
│   │   ├── base/           → Base test classes (shared setup/teardown)
│   │   ├── config/         → Configuration readers
│   │   ├── testcases/      → Test classes organized by feature
│   │   └── utils/          → Reusable utilities (screenshots, waits, Excel)
│   └── resources/
│       ├── config.properties   → Test configuration
│       ├── log4j2.xml          → Logging configuration
│       └── testng.xml          → TestNG suite definition
├── .env.example            → Environment variable template
├── .gitignore              → Git exclusion rules
├── CHANGELOG.md            → Version history
├── CONTRIBUTING.md         → Contribution guidelines
├── pom.xml                 → Maven project configuration
└── README.md               → This file
```

---

## Prerequisites

| Requirement | Minimum Version |
|------------|----------------|
| Java JDK | 17+ |
| Maven | 3.9+ |
| Chrome/Firefox/Edge | Latest stable |
| Git | 2.x+ |

---

## Installation Guide

### 1. Clone the Repository
```bash
git clone https://github.com/amazing010692/Selenium_WebDriverManager_Tests.git
cd Selenium_WebDriverManager_Tests
```

### 2. Install Dependencies
```bash
mvn clean install -DskipTests
```

### 3. Configure Environment (Optional)
```bash
cp .env.example .env
# Edit .env with your preferred settings
```

### 4. Run Tests
```bash
mvn test
```

### 5. Run with Specific Browser
```bash
mvn test -Dbrowser=firefox
```

### 6. Run in Headless Mode
```bash
mvn test -Dheadless=true
```

---

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `browser` | chrome | Browser to use for testing |
| `headless` | false | Run in headless mode |

Configuration is managed via `src/test/resources/config.properties`. System properties (`-D` flags) override file values.

---

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=TestCheckboxes
```

### Run with TestNG Suite
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Generate Test Reports
```bash
mvn surefire-report:report
# Reports available at: target/surefire-reports/
```

---

## CI/CD Workflow

The GitHub Actions pipeline (`.github/workflows/ci.yml`) runs on every push/PR:

1. **Checkout** → Pulls latest code
2. **Setup JDK 17** → Configures Java environment
3. **Install Browser** → Sets up Chrome/Firefox
4. **Run Tests** → Executes in headless mode
5. **Upload Reports** → Stores test artifacts

Tests run in parallel across Chrome and Firefox.

---

## Contribution Guide

See [CONTRIBUTING.md](CONTRIBUTING.md) for:
- Branch naming conventions
- Commit message format
- Pull request process
- Coding standards

---

## Troubleshooting

See [docs/TROUBLESHOOTING.md](docs/TROUBLESHOOTING.md) for common issues.

**Quick fixes:**
- Driver mismatch → `rm -rf ~/.cache/selenium`
- Dependency issues → `mvn clean install -U`
- Java version → Ensure JDK 17+ with `java -version`

---

## FAQ

**Q: Do I need to download browser drivers manually?**  
A: No. WebDriverManager handles driver downloads automatically.

**Q: Can I run tests on multiple browsers simultaneously?**  
A: Yes. Use the TestNG XML suite with `parallel="methods"`.

**Q: How do I add a new test?**  
A: Create a class in `src/test/java/testcases/` extending `BaseTest`, then use TestNG `@Test` annotations.

**Q: Where are screenshots saved?**  
A: In `target/screenshots/` by default (configurable in `config.properties`).

---

## Security Notes

- ❌ Never commit credentials or API keys
- ✅ Use `.env` files (excluded from Git) for sensitive data
- ✅ Use `config.properties` for non-sensitive configuration
- ✅ All hardcoded paths have been externalized to configuration
- ✅ CI/CD uses GitHub Secrets for sensitive values

---

## Future Improvements

| Priority | Improvement |
|----------|-------------|
| High | Add Page Object Model (POM) pattern |
| High | Add Allure reporting integration |
| Medium | Add Docker containerization for test execution |
| Medium | Add parallel execution with Selenium Grid |
| Low | Add API testing layer with RestAssured |
| Low | Add visual regression testing |

---

## License

This project is licensed under the [MIT License](LICENSE).

---

*Built with ❤️ for learning and portfolio demonstration.*
