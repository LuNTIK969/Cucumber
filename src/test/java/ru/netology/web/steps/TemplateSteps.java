package ru.netology.web.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;
import ru.netology.web.page.VerificationPage;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransferPage transferPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void login(String login, String password) {
        String url = "http://localhost:9999";
        loginPage = Selenide.open(url, LoginPage.class);
        verificationPage = loginPage.validLogin(login, password);
        String verificationCode = "12345";
        dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.shouldBeVisible();
        Configuration.holdBrowserOpen = true;
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы")
    public void transfer(String amount, String numberCard, String countCard) {
        transferPage = dashboardPage.chooseCard(countCard);
        transferPage.transfer(amount, numberCard);
    }

    @Тогда("баланс его 1 карты из списка на главной странице должен стать {string} рублей")
    public void getBalance(String expectedSum) {
        String actualBalance = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(expectedSum, actualBalance);
    }
}