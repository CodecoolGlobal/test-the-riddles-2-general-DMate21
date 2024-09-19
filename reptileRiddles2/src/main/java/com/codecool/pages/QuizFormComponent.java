package com.codecool.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class QuizFormComponent {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By questionInputLocator = By.id("-1question");
    private final By timeLimitInputLocator = By.xpath("//*[@id=\"161time\"]");
    private final By addOptionButtonLocator = By.xpath("//button[contains(text(), '+ Add option')]");
    private final By saveButtonLocator = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/div/div[4]/button[1]");
    private final By deleteButtonLocator = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/div/div[4]/button[2]");
    public QuizFormComponent(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setQuestionText(String question) {
        WebElement questionInput = wait.until(ExpectedConditions.visibilityOfElementLocated(questionInputLocator));
        questionInput.clear();
        questionInput.sendKeys(question);
    }

    public void setTimeLimit(int seconds) {
        WebElement timeLimitInput = wait.until(ExpectedConditions.elementToBeClickable(timeLimitInputLocator));
        timeLimitInput.clear();
        timeLimitInput.sendKeys(String.valueOf(seconds));
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", timeLimitInput);
    }

    public void setAnswerOption(int index, String answer) {
        String dynamicAnswerInputId = "answer-" + index;
        By answerOptionLocator = By.id(dynamicAnswerInputId);
        WebElement answerOptionInput = wait.until(ExpectedConditions.visibilityOfElementLocated(answerOptionLocator));
        answerOptionInput.clear();
        answerOptionInput.sendKeys(answer);
    }

    public void markAnswerAsCorrect(int index) {
        String dynamicCheckboxId = "checkbox-" + index;
        By correctAnswerCheckboxLocator = By.id(dynamicCheckboxId);
        WebElement correctAnswerCheckbox = wait.until(ExpectedConditions.elementToBeClickable(correctAnswerCheckboxLocator));
        if (!correctAnswerCheckbox.isSelected()) {
            correctAnswerCheckbox.click();
        }
    }

    public void clickAddOption() {
        WebElement addOptionButton = wait.until(ExpectedConditions.elementToBeClickable(addOptionButtonLocator));
        addOptionButton.click();
    }

    public void clickSave() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
        saveButton.click();
    }

    public void clickDelete() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));
        deleteButton.click();
    }

    public void addQuestionToQuiz(String questiontext, int timeLimit, List<String> answerOptions, int correctAnswer){
        setQuestionText(questiontext);
        setTimeLimit(timeLimit);
        for (int i = 0; i < answerOptions.size(); i++) {
            setAnswerOption(i+1, answerOptions.get(i));
        }
        markAnswerAsCorrect(correctAnswer);
        clickSave();
    }

    public void acceptAlert(){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}

