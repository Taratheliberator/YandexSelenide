package org.example.custom.settings;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ScreenshotExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext context) {

    }

    @Override
    public void afterTestExecution(ExtensionContext context)  {
        if (context.getExecutionException().isPresent()) {

            Selenide.screenshot("failure_screenshot");
        } else {

            Selenide.screenshot("success_screenshot");
        }
    }
}