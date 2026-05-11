# Changelog

All notable changes to this project will be documented in this file.

## [1.0.0] - 2025-01-20

### Added
- Modular project architecture with `base/`, `utils/`, `config/` packages
- `BaseTest` class for reusable WebDriver setup/teardown
- `ConfigReader` for externalized configuration
- `ScreenshotUtils` for reusable screenshot capture
- `WaitUtils` for reusable explicit wait patterns
- `ExcelUtils` for data-driven testing support
- Log4j2 logging configuration
- TestNG XML suite configuration
- GitHub Actions CI/CD pipeline
- Comprehensive documentation (README, CONTRIBUTING, CHANGELOG)
- `.env.example` for environment variable reference

### Changed
- Upgraded Selenium from 3.x to 4.27.0
- Upgraded WebDriverManager from 4.x to 5.9.2
- Upgraded TestNG from 7.1.0 to 7.10.2
- Upgraded Apache POI from 4.1.2 to 5.3.0
- Upgraded Log4j from 2.13.3 to 2.24.3
- Modernized `pom.xml` with proper build plugins and version properties
- Improved `.gitignore` with comprehensive exclusions

### Removed
- System-scoped `mail-1.4.7.jar` dependency (replaced with Maven-managed dependency)
- Deprecated `protobuf-java` RC dependency
- Deprecated `reportng` dependency
- Eclipse `.settings/`, `.classpath`, `.project` files from tracking
- Hardcoded screenshot images from repository

### Security
- Removed hardcoded file paths and credentials from test files
- Externalized configuration to properties files
- Added `.env` to `.gitignore`
