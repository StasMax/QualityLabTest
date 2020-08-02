package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Waiters.waitForVisible;

/**
 * Главная страница
 */
public class MainPage extends BasePage {

    /**
     * Browser driver.
     */
    private final WebDriver driver;

    /**
     * Page url.
     */
    private final String PAGE_URL = "/";

    /**
     * Блок с полями для авторизации.
     */
    @FindBy(id = "mailbox-container")
    private WebElement loginWindow;

    /**
     * Поле логин.
     */
    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    /**
     * Поле пароль.
     */
    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    /**
     * Кнопка Ввести пароль.
     */
    @FindBy(id = "mailbox:submit")
    private WebElement enterPasswordButton;

    /**
     * Выбранная доменная зона.
     */
    @FindBy(xpath = "//select[@id='mailbox:domain']/option[@selected='selected']")
    private WebElement selectedDomainName;

    /**
     * Доменная зона.
     */
    @FindBy(id = "mailbox:domain")
    private WebElement domainName;

    /**
     * Кнопка Войти.
     */
    @FindBy(id = "mailbox:submit")
    private WebElement enterButton;

    /**
     * Конструктор. Проверяет URL и наличие на экране блока с полями ввода логина и пароля.
     * @param driver драйвер браузера.
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        checkPageIsPresentByUrl("Главная", driver, PAGE_URL);
        waitForVisible("Блок полей ввода логина и пароля", driver, loginWindow);
    }

    /**
     * Метод авторизации.
     *
     * @param login      логин (имя почты)
     * @param password   пароль от почты
     * @param domainName доменное имя почты
     * @return страница аккаунта почты
     */
    @Step("Вход в аккаунт с логином '{0}' и паролем '{1}'")
    public final IncomingMailPage authorize(String login, String password, String domainName) {
        if (!getElementText("Выбранная доменная зона", selectedDomainName).equals(domainName))
            chooseDomainName(domainName);
        fillInputField("Поле логин", loginField, login);
        clickElement("Кнопка Ввести пароль", enterPasswordButton);
        waitForVisible("Поле пароль", driver, passwordField);
        fillInputField("Поле пароль", passwordField, password);
        clickElement("Кнопка Войти", enterButton);
        return new IncomingMailPage(driver);
    }

    /**
     * Метод выбора доменной зоны.
     *
     * @param domain доменное имя почты
     * @return
     */
    @Step("Выбрать доменную зону {0}")
    public final MainPage chooseDomainName(String domain) {
        clickElement("Доменная зона", domainName);
        clickElement("Доменная зона " + domain,
                domainName.findElement(new By.ByXPath("//option[text()='" + domain + "']")));
        return this;
    }

}
