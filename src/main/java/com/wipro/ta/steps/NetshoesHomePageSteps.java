package com.wipro.ta.steps;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class NetshoesHomePageSteps extends AbstractSteps {

    @Value("${home.url}")
    private String NETSHOES_HOMEPAGE_URL;

    protected Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    protected WebDriverProvider webDriverProvider;

    @Given("the customer access the NetShoes home page")
    public void givenCustomerAccessHomePage() {
        LOG.info("Navigating user to page: " + NETSHOES_HOMEPAGE_URL);
        webDriverProvider.get().get(NETSHOES_HOMEPAGE_URL);
    }

    @Then("I should see the search bar")
    public void thenProductListIsDisplayed() {
        WebElement contentDiv = webDriverProvider.get().findElement(By.id("search-input"));
        Assert.assertTrue("The search bar was expected to be displayed, but it was not."
                , contentDiv.isDisplayed());
    }
    @Then("I should search for something")
    public void searchForSomething(){
        WebElement contentDiv = webDriverProvider.get().findElement(By.id("search-input"));
        contentDiv.sendKeys("tênis nike");
        contentDiv.submit();

    }
    @Then("I should see the something's listing")
    public void seeSomethingsList(){
        WebElement contentDiv = webDriverProvider.get().findElement(By.id("item-list"));
        Assert.assertTrue("Something was expected to be displayed, but it was not."
                , contentDiv.isDisplayed());
    }

    @When("I click on a item")
    public void iClickOnAItem() {
        WebElement item = webDriverProvider.get().findElement(By.className("item-card__description__product-name"));
        item.click();

    }
    @Then("I should select the item's size")
    public void iShouldSelectTheItemsSize(){
        WebElement item = webDriverProvider.get().findElement(By.xpath("//a[@data-label='39']"));
        item.click();
    }

    @Then("I should add a item to the cart")
    public void iShouldAddAItemToTheCart() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        webDriverProvider.get().manage().timeouts().implicitlyWait(10L, timeUnit);

        WebElement buyNowButton = webDriverProvider.get().findElement(By.id("buy-button-now"));
        if(buyNowButton.isDisplayed()) {
            buyNowButton.click();
        } else{
            Assert.assertTrue("Buy now button was expected to be displayed, but it was not."
                    , buyNowButton.isDisplayed());
        }
    }

    @When("I fill the CEP field")
    public void iFillTheCEPField() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        webDriverProvider.get().manage().timeouts().implicitlyWait(10l, timeUnit);
        WebElement cep = webDriverProvider.get().findElement(By.id("cep"));
        cep.sendKeys("80010180");
        cep.submit();
    }

    @Then("I should see the shipping value")
    public void iShouldSeeTheShippingValue() {
        WebElement shippingValue = webDriverProvider.get().findElement(By.className("summary__item-value"));
        Assert.assertTrue("Buy now button was expected to be displayed, but it was not."
                , shippingValue.isDisplayed());
    }

}
