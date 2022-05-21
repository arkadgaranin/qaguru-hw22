package com.gmail.arkgaranin.tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("selenide")
public class WikipediaAppAndroidTests extends TestBase {

  @Test
  void firstPageTest() {
    step("Проверка текста на экране", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text
          ("The Free Encyclopedia …in over 300 languages"));

      $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave(text
          ("We’ve found the following on your device"));
    });

    step("Клик на кнопку добавления языка", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer")).click();
    });

    step("Проверка текста заголовка на экране добавления языков", () -> {
      $(AppiumBy.className("android.widget.TextView")).shouldHave(text("Wikipedia languages"));
    });

    step("Клик на кнопку Добавить язык", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).last().click();
    });

    step("Выбор русского языка", () -> {
      $(AppiumBy.xpath("//android.widget.TextView[@text='Русский']")).click();
    });

    step("Проверка, что в списке есть русский язык", () -> {
      $(AppiumBy.xpath("//android.widget.TextView[@text='Русский']")).shouldHave(attribute
          ("text", "Русский"));
    });

    step("Возврат назад по кнопке стрелке", () -> {
      $(AppiumBy.className("android.widget.ImageButton")).click();
    });

    step("Проверка, что в списке есть русский язык", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/languagesList"))
          .$$(AppiumBy.id("org.wikipedia.alpha:id/option_label")).shouldHave(size(2));
    });
  }

  @Test
  void secondPageTest() {

    final String DESCR_TEXT = "Dive down the Wikipedia rabbit hole with a constantly updating Explore feed. " +
        "Customize the feed to your interests – whether it’s learning about historical events On this day, or " +
        "rolling the dice with Random.";

    step("Клик на кнопку Далее для перехода на 2-й экран", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });

    step("Проверка текста на экране", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave
          (attribute("text", "New ways to explore"));

      $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave
          (attribute("text", DESCR_TEXT));
    });
  }

  @Test
  void thirdPageTest() {

    final String DESCR_TEXT = "You can make reading lists from articles you want to read later, even when you’re " +
        "offline. Login to your Wikipedia account to sync your reading lists. Join Wikipedia";

    step("Клик на кнопку Далее для перехода на 3-й экран", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });

    step("Проверка текста на экране", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave
          (attribute("text", "Reading lists with sync"));

      $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave
          (attribute("text", DESCR_TEXT));
    });
  }

  @Test
  void fourthPageTest() {

    final String DESCR_TEXT = "Help make the app better by letting us know how you use it. " +
        "Data collected is anonymous. Learn more";

    step("Клик на кнопку Далее для перехода на 4-й экран", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });

    step("Проверка текста на экране", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave
          (attribute("text", "Send anonymous data"));
      $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave
          (attribute("text", DESCR_TEXT));
    });

    step("Выкл свитч отправки данных", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/switchView")).click();
    });

    step("Проверка, что свитч отправки данных выключен", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/switchView")).shouldHave(attribute
          ("checked", "false"));
    });

    step("Клик на GET STARTED", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
    });

    step("Проверка, что открылся главный экран", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar_wordmark")).shouldBe();
    });
  }

  @Test
  void searchTest() {
    step("Skip onboarding page", () -> back());

    step("Type search", () -> {
      $(AppiumBy.accessibilityId("Search Wikipedia")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
          .setValue("BrowserStack");
    });
    step("Verify content found", () ->
        $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
            .shouldHave(sizeGreaterThan(0)));
  }
}
