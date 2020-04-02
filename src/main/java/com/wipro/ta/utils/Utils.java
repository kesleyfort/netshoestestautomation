package com.wipro.ta.utils;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Utils {
    @Autowired
    protected WebDriverProvider webDriverProvider;

    public WebElement waitForElement(By by) {
        Wait<WebDriver> wait = new FluentWait<>(webDriverProvider.get())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(webDriver -> {
            if (webDriver == null) throw new AssertionError();
            return webDriver.findElement(by);
        });

    }
}
