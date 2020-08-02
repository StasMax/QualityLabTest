package tests;

import helpers.ParametersProvider;
import helpers.TestListener;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

/**
 * Базовый тестовый класс.
 */
@Listeners(TestListener.class)
public class BaseTest {

    /**
     * Драйвер браузера.
     */
    private WebDriver driver;

    /**
     * Главная страница.
     */
    protected MainPage mainPage;

    /**
     * Метод подготовки данных и драйвера для тестов.
     *
     * @return новый драйвер.
     */
    @BeforeMethod(description = "Подготовка драйвера для теста")
    protected final void setUpTest() {
        String browserName = ParametersProvider.getPropertyByName("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors");
            driver = new ChromeDriver(options);
        } else if (browserName.equals("FF")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--ignore-certificate-errors");
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(
                Integer.parseInt(ParametersProvider.getPropertyByName("implicitWait")), TimeUnit.SECONDS);
    }

    @Step("Открытие браузера на главной странице")
    protected final void openBrowserOnMainPage() {
        String url = ParametersProvider.getPropertyByName("url");
        driver.get(url);
        mainPage = new MainPage(driver);
    }

    /**
     * Удаление драйвера при завершении теста.
     */
    @AfterMethod(alwaysRun = true, description = "Удаление драйвера")
    public final void deleteDriver() {
        if (driver != null)
            driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
