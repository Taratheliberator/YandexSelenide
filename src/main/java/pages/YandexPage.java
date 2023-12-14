package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс для взаимодействия со страницей Яндекс Маркета.
 * Автор: Гиндуллин Винер
 */
public class YandexPage {

    /**
     * Кликает по кнопке "Каталог".
     *
     * @return Возвращает YandexPage для дальнейших действий.
     */
    @Step("Клик по кнопке 'Каталог'")
    public YandexPage click() {
        $(By.xpath("//button[.//span[text()='Каталог']]")).click();
        return page(YandexPage.class);
    }

    /**
     * Наводит курсор на категорию.
     *
     * @param category Название категории.
     * @return Возвращает YandexPage для дальнейших действий.
     */
    @Step("Наведение на категорию {category}")
    public YandexPage hover(String category) {
        $(By.xpath("//a[not(ancestor::div[@role='heading'])]/span[text()='" + category + "']")).hover();
        return page(YandexPage.class);
    }

    /**
     * Кликает на подкатегорию.
     *
     * @param subcategory Название подкатегории.
     * @return Возвращает YandexPage для дальнейших действий.
     */
    @Step("Клик на подкатегорию {subcategory}")
    public YandexPage clickOnSubCategory(String subcategory) {
        $(By.linkText(subcategory)).click();
        return page(YandexPage.class);
    }

    /**
     * Выбирает производителя.
     *
     * @param manufacturer Название производителя.
     * @return Возвращает YandexPage для дальнейших действий.
     */
    @Step("Выбор производителя {manufacturer}")
    public YandexPage selectManufacturer(String manufacturer) {
        SelenideElement manufacturerElement = $(By.xpath("//span[text()='" + manufacturer + "']"));
        manufacturerElement.click();
        if (!manufacturerElement.exists()) {
            $(By.xpath("//span[text()='Показать всё']/parent::button[@aria-expanded='false']")).click();
            manufacturerElement.scrollTo().click();
        }
        return page(YandexPage.class);
    }

    /**
     * Проверяет фильтр по модели.
     *
     * @param model Название модели.
     * @return Возвращает YandexPage для дальнейших действий.
     */
    @Step("Проверка фильтра модели {model}")
    public YandexPage checkFilter(String model) {
        while (true) {
            ElementsCollection resultSearch = $$x("//div[@data-test-id='virtuoso-item-list']");
            SelenideElement item = resultSearch.find(text(model));
            item.should(exist.because("Товар с фильтром " + model + " не найден"));

            if (!goToNextPage()) {
                break;
            }
        }
        return page(YandexPage.class);
    }

    /**
     * Переходит на следующую страницу результатов поиска.
     *
     * @return Возвращает true, если переход возможен, иначе false.
     */
    @Step("Переход на следующую страницу результатов поиска")
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
