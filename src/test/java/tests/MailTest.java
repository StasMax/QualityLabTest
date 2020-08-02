package tests;

import helpers.ParametersProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static helpers.StringUtils.getRandomCyrillicString;
import static helpers.StringUtils.getRandomText;

/**
 * Класс тестов mail.ru
 */
public class MailTest extends BaseTest {

    @Test(description = "TEST-1: Авторизация и отправка письма")
    public final void checkCorrectLoginPassAuth() {
        openBrowserOnMainPage();
        String login = ParametersProvider.getPropertyByName("login");
        String password = ParametersProvider.getPropertyByName("password");
        String domain = ParametersProvider.getPropertyByName("mailDomain");
        String mailTheme = getRandomCyrillicString(new Random().nextInt(10) + 5);
        String mailText = getRandomText(new Random().nextInt(20) + 5);

       mainPage.authorize(login, password, domain)
               .clickCreateLetterButton()
               .fillLetterFields(login + domain, mailTheme, mailText)
               .clickSendMailButton();
    }

}
