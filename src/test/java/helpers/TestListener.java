package helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import tests.BaseTest;

/**
 * Класс слушателей тестов.
 */
public class TestListener implements IInvokedMethodListener {

    /**
     * Метод создания скриншота экрана
     *
     * @param currentClass
     * @return скриншот как byte array
     */
    @Attachment(type = "image/png")
    public byte[] takeScreenshot(Object currentClass) {
        if (((BaseTest) currentClass).getDriver() != null) {
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return null;
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult result) {
        //Just implement method beforeInvocation() from IInvokedMethodListener interface
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getInstance());
            Allure.addAttachment("PageSource", ((BaseTest) result.getInstance()).getDriver().getPageSource());
        }
        if (iInvokedMethod.isTestMethod() && result.getStatus() == ITestResult.SKIP)
            result.setStatus(ITestResult.FAILURE);
    }

}
