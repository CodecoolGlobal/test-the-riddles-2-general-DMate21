package com.codecool;

import com.codecool.pages.*;
import com.codecool.utilitiy.CreateGame;
import com.codecool.utilitiy.DBPopulateQuiz;
import com.codecool.utilitiy.DBPopulateUser;
import com.codecool.utilitiy.DatabaseMod;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiddleTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private QuizGamePage quizGamePage;
    private LobbyPage lobbyPage;
    private ResultPage resultPage;
    private GameListPage gameListPage;
    private DBPopulateUser dbPopulateUser;
    private DBPopulateQuiz dbPopulateQuiz;
    private String username, email, password, BASE_URL;
    private DatabaseMod databaseMod;


    @BeforeEach
    public void setUp() {
        databaseMod = new DatabaseMod();
        databaseMod.PostgresTruncateMultipleTables();
        ChromeOptions options = new ChromeOptions();
        options.setCapability("acceptInsecureCerts", true);
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        dbPopulateQuiz = new DBPopulateQuiz(driver, wait);
        List<String> answers = new ArrayList<>();
        answers.add("Answer1");
        answers.add("Answer2");
        Dotenv dotenv = Dotenv.load();
        username = dotenv.get("PLAYER");
        email = dotenv.get("PLAYER_EMAIL");
        password = dotenv.get("PLAYER_PASSWORD");
        driver.get("http://localhost:3000");
        dbPopulateUser = new DBPopulateUser(driver, wait, username, email, password);
        dbPopulateUser.populateUser();
        dbPopulateQuiz.populateQuiz("Quiz1", "Test Question1", answers);
        CreateGame.createGame(driver, wait, 0);
        quizGamePage = new QuizGamePage();
        lobbyPage = new LobbyPage(driver, wait);
    }


    @AfterEach
    public void tearDown() {
        databaseMod.PostgresTruncateMultipleTables();
        driver.quit();

    }

    @Test
    public void testPlayerSuccessfullyJoin() throws InterruptedException {

        quizGamePage.joinALobby();
        Assertions.assertTrue(quizGamePage.goodLuckIsVisible());
    }

    @Test
    public void testPlayerCanJoinOnceOnly() {
        quizGamePage.joinALobby();
        quizGamePage.driver.navigate().to("http://localhost:3000");
        quizGamePage.joinALobby();
        int actualPlayersNumber = quizGamePage.playersNumber();
        Assertions.assertEquals(1, actualPlayersNumber);
    }

    @Test
    public void testPlayerSeeTheDetailsOf() {
        quizGamePage.joinALobby();
        String playersText = quizGamePage.playersText();
        String questionsText = quizGamePage.questionText();
        Assertions.assertFalse(playersText.isEmpty());
        Assertions.assertFalse(questionsText.isEmpty());

    }

    @Test
    public void testGameMasterSeeTheResults() {
        quizGamePage.joinALobby();
        lobbyPage.clickStart();
        lobbyPage.clickResult();
        /* quizGamePage.clickRandomButton(); */
        String result = lobbyPage.scoreBoardText();
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    public void NoPlayersNoGame() {
        boolean actualResult = lobbyPage.startButton();
        assertFalse(actualResult);
    }
}