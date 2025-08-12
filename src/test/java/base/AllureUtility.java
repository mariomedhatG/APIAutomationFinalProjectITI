package base;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class AllureUtility {
    public static void cleanAllureResults()
    {
        FileUtils.deleteQuietly(new File("allure-results"));
    }
}
