package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Waiters.waitForVisible;

/**
 * Страница аккаунта почты на вкладке Входящие.
 */
public class IncomingMailPage extends BasePage {

    /**
     * Browser driver.
     */
    private final WebDriver driver;

    /**
     * Page url.
     */
    private final String PAGE_URL = "/inbox";

    /**
     * Всплывающее окно Добро пожаловать в новую почту.
     */
    @FindBy(id = "onboarding-popup")
    private WebElement welcomePopup;

    /**
     * Выделенная кнопка Входящие.
     */
    @FindBy(xpath =
            "//a[@class='nav__item js-shortcut nav__item_active nav__item_shortcut nav__item_child-level_0' " +
                    "and @href='/inbox/']")
    private WebElement incomingButton;

    /**
     * Кнопка Написать письмо.
     */
    @FindBy(css = "span[class='compose-button__wrapper']")
    private WebElement createLetterButton;

    /**
     * Конструктор. Проверяет URL и наличие на экране выделенной кнопки 'Входящие'.
     * @param driver драйвер браузера.
     */
    public IncomingMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        checkPageIsPresentByUrl("Почтовый аккаунт", driver, PAGE_URL);
        waitForVisible("Выделенная кнопка 'Входящие'", driver, incomingButton);
        checkElementText("Кнопка Входящие", incomingButton, "Исходящие");
    }

   public final NewMailPage clickCreateLetterButton() {
        clickElement("Кнопка 'Написать письмо'", createLetterButton);
        return new NewMailPage(driver);
   }


}
