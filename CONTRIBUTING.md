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

## Test Guidelines

- Each test class should extend `BaseTest`
- Use TestNG annotations (`@Test`, `@BeforeMethod`, `@AfterMethod`)
- Use descriptive test method names
- Add appropriate assertions
- Avoid hardcoded waits (`Thread.sleep`) — use `WaitUtils` instead
