package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class NotePage {

    @FindBy(css = "#nav-notes-tab")
    private WebElement notesTabField;

    @FindBy(css = "#addNoteButton")
    private WebElement addNote;

    @FindBy(css = "#note-title")
    private WebElement noteTitleField;

    @FindBy(css = "#table-noteTitle")
    private WebElement tableNoteTitle;

    @FindBy(css = ".note-elements")
    private List<WebElement> noteElements;

    @FindBy(css = "#note-description")
    private WebElement noteDescriptionField;

    @FindBy(css = "#noteSubmit")
    private WebElement saveNote;

    @FindBy(css = "#editNoteButton")
    private WebElement editNote;

    @FindBy(css = "#deleteNoteButton")
    private WebElement deleteNote;

    private final JavascriptExecutor js;

    private final WebDriverWait wait;

    public NotePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 1000);
        js = (JavascriptExecutor) webDriver;
    }

    public void openNoteTabJS() {
        js.executeScript("arguments[0].click();", notesTabField);
    }

    public void openModalJS() {
        js.executeScript("arguments[0].click();", addNote);
    }

    public void createNoteJS(String title, String description) {
        js.executeScript("arguments[0].value='" + title + "';", noteTitleField);
        js.executeScript("arguments[0].value='" + description + "';", noteDescriptionField);
    }

    public void saveNoteJS() {
        js.executeScript("arguments[0].click();", saveNote);
    }

    public void editNoteJS() {
        js.executeScript("arguments[0].click();", editNote);
    }

    public void deleteNoteJS() {
        js.executeScript("arguments[0].click();", deleteNote);
    }

    public String getTableNoteTitle() {
        return tableNoteTitle.getAttribute("innerHTML");
    }

    public Boolean noteElementsExist() {
        return noteElements.size() > 0;
    }
}
