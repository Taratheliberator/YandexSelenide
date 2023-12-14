package org.example.custom.settings;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Расширение JUnit для автоматического создания скриншотов перед и после выполнения теста.
 */
public class ScreenshotExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    /**
     * Вызывается перед выполнением каждого теста.
     *
     * @param context Контекст выполняемого теста.
     */
    @Override
    public void beforeTestExecution(ExtensionContext context) {

    }

    /**
     * Вызывается после выполнения каждого теста.
     * Создаёт скриншоты в случае успеха или неудачи теста.
     *
     * @param context Контекст выполняемого теста.
     */
    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {

            Selenide.screenshot("failure_screenshot");
        } else {

            Selenide.screenshot("success_screenshot");
        }
    }
}
