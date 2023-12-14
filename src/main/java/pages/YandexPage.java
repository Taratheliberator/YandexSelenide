package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexPage {

    public YandexPage click() {
        $(By.xpath("//button[.//span[text()='Каталог']]")).click();
        return page(YandexPage.class);

    }

    public YandexPage hover(String category) {
        $(By.xpath("//a[not(ancestor::div[@role='heading'])]/span[text()='" + category + "']")).hover();
        return page(YandexPage.class);
    }


    public YandexPage clickOnSubCategory(String subcategory) {
        $(By.linkText(subcategory)).click();
        return page(YandexPage.class);
    }


    public YandexPage selectManufacturer(String manufacturer) {

        SelenideElement manufacturerElement = $(By.xpath("//span[text()='" + manufacturer + "']"));
        manufacturerElement.click();
        if (!manufacturerElement.exists()) {
            $(By.xpath("//span[text()='Показать всё']/parent::button[@aria-expanded='false']")).click();
            manufacturerElement.scrollTo().click();

        }
        return page(YandexPage.class);
    }


    public YandexPage checkFilter(String model) {
        while (true) {
            ElementsCollection resultSearch = $$x("//div[@data-test-id='virtuoso-item-list']");
            SelenideElement item = resultSearch.find(text(model));

            item.should(exist.because("Товар с фильтром" + model + "не найден"));


            if (!goToNextPage()) {

                break;
            }
        }

        return page(YandexPage.class);
    }


    public boolean goToNextPage() {

        ElementsCollection nextPageButtons = $$x("//div[@data-baobab-name='next']");


        if (!nextPageButtons.isEmpty() && nextPageButtons.first().isDisplayed()) {
            nextPageButtons.first().click();
            return true;
        } else {
            return false;
        }
    }

}
