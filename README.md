# Playwright Java Test Automation Framework

## ✅ Features
- Page Object Model (POM)
- TestNG & Cucumber Support
- Extent Reporting with Screenshots
- Multi-browser Support
- Parallel Execution
- Environment Config Management
- Maven Build Integration
  project-root/
  ├── pom.xml
  ├── testng.xml
  ├── README.md
  └── src/
  ├── main/
  │   └── java/
  │       └── framework/
  │           ├── core/
  │           │   ├── DriverFactory.java
  │           │   └── EnvironmentManager.java
  │           ├── pages/
  │           │   └── LoginPage.java
  │           ├── utils/
  │           │   └── ScreenshotUtil.java
  │           └── managers/
  │               └── PageManager.java
  └── test/
  └── java/
  ├── tests/
  │   ├── base/
  │   │   └── BaseTest.java
  │   └── scenarios/
  │       └── LoginTest.java
  ├── runners/
  │   └── TestRunner.java
  └── steps/
  └── LoginSteps.java
  └── src/test/resources/features/
  └── login.feature

// pom.xml
---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven

### Install Playwright Browsers
```bash
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

### Run Tests
#### TestNG
```bash
mvn clean test -Dbrowser=chromium
```

#### Cucumber
```bash
mvn test -Dcucumber.options="--tags @smoke"
```
---

## 🌍 Multi-Environment Support
Add environment files under `src/test/resources`

**Example: `config.properties`**
```properties
baseUrl=https://example.com/login
```

Run with:
```bash
mvn test -DbaseUrl="new url"
```

---

## 🧪 Parallel Execution
Configure `testng.xml`:
```xml
<suite name="ParallelTests" parallel="tests" thread-count="3">
  <test name="ChromiumTests">
    <parameter name="browser" value="chromium"/>
    <classes>
      <class name="tests.scenarios.LoginTest"/>
    </classes>
  </test>
</suite>
```

---

## 📘 Folder Structure
```
src
├── main/java/framework
│   ├── core → DriverFactory, Env Manager
│   ├── pages → POM classes
│   ├── utils → Screenshot utils
│   └── managers → Page manager factory
├── test/java
│   ├── tests → TestNG tests
│   ├── steps → Cucumber step defs
│   └── runners → Cucumber runner
└── resources/features → Cucumber features
```

---

## 📦 CI/CD Integration
Sample GitHub Actions: `.github/workflows/playwright-ci.yml`
```yaml
- uses: actions/checkout@v3
- name: Setup Java
  uses: actions/setup-java@v3
  with:
    java-version: '17'
- name: Run Tests
  run: mvn clean test
```

---

## 🧾 Sample Command Summary
| Task                  | Command                      |
|-----------------------|------------------------------|
| Run tests (default)   | `mvn test`                   |
| Run in Firefox        | `mvn test -Dbrowser=firefox` |
| Generate Report       | `reports`                    |   

Happy Testing 🚀
