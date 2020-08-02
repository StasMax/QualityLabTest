package pages;

import helpers.ParametersProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Бызовый класс страниц.
 */
public class BasePage {

    /**
     * Метод проверки URL страницы
     *
     * @param elementName наименование страницы
     * @param pageUrl     url страницы
     */
    @Step(value = "Проверка url страницы {0}")
    public final void checkPageIsPresentByUrl(final String elementName,
                                              final WebDriver driver,
                                              final String pageUrl) {
        int timeout = Integer.parseInt(ParametersProvider
                .getPropertyByName("implicitWait"));
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.urlContains(pageUrl));
        } catch (IllegalStateException e) {
            Assert.fail(String.format("Страница %s не представлена на экране спустя время ожидания в %s",
                    elementName, timeout));
        } catch (TimeoutException e) {
            Assert.fail(String.format("Страница %s не представлена на экране спустя время ожидания в %s",
                    elementName, timeout));
        }
    }

    @Step("Клик по элементу {0}")
    public final void clickElement(final String elementName,
                                   final WebElement element) {
        if (element.isDisplayed())
            element.click();
        else
            Assert.fail("Элемент " + elementName + " не представлен на экране");
    }

    @Step("Заполнение поля ввода - {0} значением '{2}'")
    public final void fillInputField(final String elementName,
                                     final WebElement element,
                                     final String text) {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(text);
        } else
            Assert.fail("Элемент " + elementName + " не представлен на экране");
    }

    @Step("Проверка соответствия текста элемента - {0} тексту '{2}'")
    public final void checkElementText(final String elementName,
                                       final WebElement element,
                                       final String expectedText) {
        if (element.isDisplayed()) {
            Assert.assertEquals(expectedText, element.getText());
        } else
            Assert.fail("Элемент " + elementName + " не представлен на экране");
    }

    /**
     * Получение текста элемента.
     *
     * @param elementName название элемента для аннотации @Step
     * @param element     элемент
     * @return текст элемента
     */
    @Step("Получение текста элемента - {0}")
    public final String getElementText(final String elementName,
                                       final WebElement element) {
        if (element.isDisplayed())
            return element.getText();
        else
            Assert.fail("Элемент " + elementName + " не представлен на экране");
        return "";
    }
}
