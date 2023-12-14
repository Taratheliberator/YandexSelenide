package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.YandexPage;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс тестов для проверки функционала Яндекс Маркета.
 * Автор: Гиндуллин Винер
 */
public class MarketTest extends BaseTests {

    /**
     * Тест поиска товаров на Яндекс Маркете с использованием фильтров производителя и модели.
     * Данные для теста предоставляются через параметры.
     *
     * @param manufacturer Производитель товара.
     * @param model Модель товара.
     */
    @ParameterizedTest
    @CsvSource({"Apple,Iphone"})
    @Feature("Поиск товаров на Yandex market")
    @DisplayName("Проверка поиска товаров с фильтром 'Производитель', 'Модель'")
    public void runTest(String manufacturer, String model) {
        open(baseUrl, YandexPage.class)
                .click()
                .hover("Электроника")
                .clickOnSubCategory("Смартфоны")
                .selectManufacturer(manufacturer)
                .checkFilter(model);
    }
}
