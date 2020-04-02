package com.wipro.ta.steps;

import com.wipro.ta.pages.NetshoesPage;
import com.wipro.ta.utils.Utils;
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

import java.util.Random;

@Component
public class NetshoesHomePageSteps extends AbstractSteps {
    @Autowired
    NetshoesPage netshoesPage;
    @Autowired
    Utils utils;
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
        Assert.assertTrue("The search bar was expected to be displayed, but it was not."
                , netshoesPage.searchBar.isDisplayed());
    }

    @Then("I should search for something")
    public void searchForSomething() {
        netshoesPage.searchBar.sendKeys("Tênis Nike");
        netshoesPage.searchBar.submit();

    }

    @Then("I should see the something's listing")
    public void seeSomethingsList() {
        Assert.assertTrue("Something was expected to be displayed, but it was not."
                , netshoesPage.itemList.isDisplayed());
    }

    @When("I click on a random item")
    public void iClickOnAItem() {
        Random random = new Random();
        netshoesPage.resultsFromSearch.get(random.nextInt(netshoesPage.resultsFromSearch.size())).click();


    }

    @Then("I should select a random available item's size")
    public void iShouldSelectTheItemsSize() {
        Random random = new Random();
        netshoesPage.productSizeOptions.get(random.nextInt(netshoesPage.productSizeOptions.size())).click();
    }

    @Then("I should add a item to the cart")
    public void iShouldAddAItemToTheCart() {
        utils.waitForElement(By.id("buy-button-now")).click();
    }

    @When("I fill the CEP field")
    public void iFillTheCEPField() {
        WebElement cep = utils.waitForElement(By.id("cep"));
        cep.sendKeys("80010180");
        cep.submit();

    }

    @Then("I should see the shipping value")
    public void iShouldSeeTheShippingValue() {
        Assert.assertTrue("Buy now button was expected to be displayed, but it was not."
                , netshoesPage.shippingValue.isDisplayed());
    }

}
