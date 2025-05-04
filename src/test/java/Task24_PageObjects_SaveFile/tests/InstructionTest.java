package Task24_PageObjects_SaveFile.tests;

import Task24_PageObjects_SaveFile.base.BaseTest;
import Task24_PageObjects_SaveFile.pages.GaragePage;
import Task24_PageObjects_SaveFile.pages.InstructionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InstructionTest extends BaseTest {
    @Test(priority = 1, description = "Verify that File was download")
    public void downloadFileTest() throws InterruptedException {
        String expectedUrl = "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Garage page was opened");

        GaragePage garagePage = new GaragePage(driver);
        garagePage.goToInstructions();

        InstructionPage instructionPage = new InstructionPage(driver);
        instructionPage.downloadInstruction();
        String downloadDirectory = System.getProperty("user.home") + "\\Downloads\\";
        String fileName = "Front windshield wipers on Audi TT.pdf";
        File downloadedFile = new File(downloadDirectory + fileName);

        int waitSeconds = 10;
        int elapsed = 0;
        while (!downloadedFile.exists() && elapsed < waitSeconds * 10) {
            try {
                Thread.sleep(100);
                elapsed++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("File successfully saved to: " + downloadedFile.getAbsolutePath());
        Assert.assertTrue(downloadedFile.exists(),
                "The file was not downloaded to the path: " + downloadedFile.getAbsolutePath());
    }

    @Test(priority = 2, description = "Verify that Car list was saved to txt")
    public void saveCarListToTxtTest() {
        String expectedUrl = "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Garage page was opened");

        GaragePage garagePage = new GaragePage(driver);
        garagePage.goToInstructions();

        InstructionPage instructionPage = new InstructionPage(driver);
        instructionPage.clickCarSelectionButton();

        List<String> carList = instructionPage.getAvailableCars();
        Assert.assertFalse(carList.isEmpty(), "Car list is not empty");

        String filePath = "car_list.txt";

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String car : carList) {
                    writer.write(car);
                    writer.newLine();
                }
            }

            String absolutePath = file.getAbsolutePath();
            System.out.println("Car list saved to: " + absolutePath);

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to save car list to file");
        }
    }

}

