package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Screenshot {
    public static void takeScreenshot(WebDriver browser, String arquivo) {
        //Gera arquivo
        File screenshot = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);

        //Copia arquivo para pasta
        try {
            FileUtils.copyFile(screenshot, new File(arquivo));
        } catch (Exception e) {
            System.out.println("Houve arquivo ao copiar o arquivo para a pasta: " + e.getMessage());
        }

    }
}
