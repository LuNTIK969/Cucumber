package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement buttonOne = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button__text");
    private SelenideElement buttonTwo = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button__text");

    public void shouldBeVisible() {
        heading.shouldBe(visible);
    }

    public TransferPage chooseCardOne() {
        buttonOne.click();
        return new TransferPage();
    }

    public TransferPage chooseCardTwo() {
        buttonTwo.click();
        return new TransferPage();
    }

    public String getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public String getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private String extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return value;
    }

    public TransferPage chooseCard(String countCard) {
        $$("[data-test-id='action-deposit'").get(Integer.parseInt(String.valueOf(countCard)) - 1).click();
        return new TransferPage();
    }

}