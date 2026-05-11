# Contributing Guide

Thank you for considering contributing to this project!

## Branch Naming Convention

- `feature/description` — New features
- `bugfix/description` — Bug fixes
- `refactor/description` — Code refactoring
- `docs/description` — Documentation updates

## Commit Message Convention

Use [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: add new screenshot utility
fix: resolve browser timeout issue
docs: update README setup instructions
refactor: extract browser factory to base class
test: add checkbox validation tests
```

## Pull Request Process

1. Create a feature branch from `develop`
2. Make your changes with clear commit messages
3. Ensure all tests pass: `mvn test`
4. Update documentation if needed
5. Submit a PR to `develop` branch

## Coding Standards

- Follow Java naming conventions (camelCase for methods, PascalCase for classes)
- Use meaningful variable and method names
- Add JavaDoc for public methods
- Keep methods focused and under 30 lines when possible
- Use the `BaseTest` class for all new test classes
- Use utility classes (`ScreenshotUtils`, `WaitUtils`, `ExcelUtils`) instead of duplicating code

## Page Object Model Guidelines

- Every new page under test must have a corresponding class in `pages/` extending `BasePage`
- Keep locators as `private static final By` fields at the top of the page class
- Page methods should represent user actions (e.g., `login()`, `selectItem()`)
- Never put assertions in page objects — assertions belong in test classes only
- Return `this` for chainable actions, return data types for queries
- Name page classes after the page they represent (e.g., `LoginPage`, `DashboardPage`)

## Test Guidelines

- Each test class should extend `BaseTest`
- Instantiate page objects inside test methods using the `driver` from `BaseTest`
- Use TestNG annotations (`@Test`, `@BeforeMethod`, `@AfterMethod`)
- Use descriptive test method names
- Add appropriate assertions
- Avoid hardcoded waits (`Thread.sleep`) — use `WaitUtils` or `BasePage.waitAndFind()` instead
- Register new test classes in `testng.xml`
