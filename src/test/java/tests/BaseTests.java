package tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ConfigReader;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

/**
 * Базовый класс для тестов, использующих Selenide.
 * Настраивает общие параметры и логирование для всех тестов.
 */
@ExtendWith(ScreenShooterExtension.class)
public class BaseTests {
    String baseUrl = ConfigReader.getProperty("baseUrl");

    /**
     * Настройка общих параметров для всех тестов.
     * Добавляет логирование с помощью Allure Selenide и настраивает скриншоты.
     */
    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    /**
     * Настраивает параметры браузера перед каждым тестом.
     * Использует Chrome в качестве браузера для тестирования.
     */
    @BeforeEach
    public void option() {
        Configuration.timeout = 40000;
        Configuration.browser = "chrome";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("start-maximized");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        System.setProperty("webdriver.Chrome.driver", System.getenv("CHROMEDRIVER"));
        WebDriver driver = new ChromeDriver(options);
        setWebDriver(driver);
    }
}
