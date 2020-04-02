package com.wipro.ta.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class NetshoesPage {
    @FindBy(id = "search-input")
    WebElement searchBar;

}
