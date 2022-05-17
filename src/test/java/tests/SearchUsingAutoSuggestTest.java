package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultPage;

import java.util.Locale;

public class SearchUsingAutoSuggestTest extends BaseTest{

    String product = "macbook";
    SearchPage searchPage;
    SearchResultPage searchResultPage;
    @Test
    public void testAutoSuggest(){
        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        searchPage.searchByAutoSuggest(product);
        Assert.assertTrue(searchResultPage.productTitleAfterSearch.getText().toLowerCase(Locale.ROOT)
                .contains(product));
    }
}
