package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Класс с методами ожиданий.
 */
public class Waiters {

    /**
     * Time out for waiters.
     */
    private static long timeOut = 0;

    /**
     * Not called.
     */
    private Waiters() {
    }

    /**
     * Метод явного ожидания элемента.
     *
     * @param driver  browser driver
     * @param element element by
     */
    @Step("Ожидание появления элемента {0}")
    public static void waitForVisible(final String elementName,
                                      final WebDriver driver,
                                      final By element) {
        timeOut = Long.parseLong(
                ParametersProvider.getPropertyByName("explicitTimeout")
        );
        try {
            new WebDriverWait(driver, timeOut).until(
                    ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException e) {
            Assert.fail(String.format(
                    "Элемент %s не представлен наэкране спустя %s секунд", elementName, timeOut));
        }

    }

    /**
     * Метод явного ожидания элемента.
     *
     * @param driver  browser driver
     * @param element WebElement
     */
    @Step("Ожидание появления элемента {0}")
    public static void waitForVisible(final String elementName,
                                      final WebDriver driver,
                                      final WebElement element) {
        timeOut = Long.parseLong(
                ParametersProvider.getPropertyByName("explicitTimeout")
        );
        try {
            new WebDriverWait(driver, timeOut).until(
                    ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            Assert.fail(String.format(
                    "Элемент %s не представлен наэкране спустя %s секунд", elementName, timeOut));
        }
    }

}
