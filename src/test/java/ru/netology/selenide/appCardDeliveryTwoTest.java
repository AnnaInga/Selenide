package ru.netology.selenide;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class appCardDeliveryTwoTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void successCompleteTwo() {
        open("http://localhost:9999/");
        int dayToAdd = 7;
        int defaultAddedDays = 3;

        $("[data-test-id='city'] input").setValue("Ма");
        $$(".menu-item__control").findBy(text("Майкоп")).click();

        $("[data-test-id='date'] input").click();
        if (!generateDate(defaultAddedDays, "MM").equals(generateDate(dayToAdd, "MM"))) {
            $("[data-step='1']").click();
        }
        $$(".calendar__day").findBy(text(generateDate(dayToAdd, "dd"))).click();


        $("[data-test-id='name'] input").setValue("Карагодина Анна Владимировна");
        $("[data-test-id='phone'] input").setValue("+79031234567");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + generateDate(dayToAdd, "dd.MM.yyyy")));
    }

}