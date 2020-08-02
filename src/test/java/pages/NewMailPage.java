package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Waiters.waitForVisible;

/**
 * Страница создания письма.
 */
public class NewMailPage extends BasePage {

    /**
     * Browser driver.
     */
    private final WebDriver driver;

    /**
     * Page url.
     */
    private final String PAGE_URL = "/";

    /**
     * Окно создания нового письма.
     */
    @FindBy(css = "div[class='compose-app__compose']")
    private WebElement newLetterWindow;

    /**
     * Поле Кому.
     */
    @FindBy(xpath = "//label[@class='container--zU301']//input")
    private WebElement whomField;

    /**
     * Поле Тема.
     */
    @FindBy(xpath = "//div[@class='subject__wrapper--2mk6m']//input")
    private WebElement themeField;

    /**
     * Кнопка Отправить.
     */
    @FindBy(css = "span[title='Отправить']")
    private WebElement sendMailButton;

    /**
     * Заголовок Письмо отправлено.
     */
    @FindBy(xpath = "//a[text()='Письмо отправлено']")
    private WebElement sentMailHeader;

    /**
     * Поле Тема.
     */
    @FindBy(css = "div[role='textbox']")
    private WebElement textField;

    /**
     * Конструктор. Проверяет наличие на экране окна создания нового письма.
     * @param driver драйвер браузера.
     */
    public NewMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForVisible("Окно создания нового письма", driver, newLetterWindow);
    }

    /**
     * Метод заполнения полей нового письма.
     * @param address e-mail получателя
     * @param theme тема письма
     * @param mailText текст письма
     * @return
     */
    @Step("Заполнение данных письма")
    public final NewMailPage fillLetterFields(String address, String theme, String mailText) {
        fillInputField("Поле Кому", whomField, address);
        fillInputField("Поле Тема", themeField, theme);
        fillInputField("Поле Текст письма", textField, mailText);
        return this;
    }

    public final NewMailPage clickSendMailButton() {
        clickElement("Кнопка Отправить", sendMailButton);
        waitForVisible("Заголовок 'Письмо отправлено'", driver, sentMailHeader);
        return this;
    }
}
