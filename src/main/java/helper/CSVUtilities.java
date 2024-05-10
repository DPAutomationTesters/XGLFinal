package helper;

import base.BaseClass;

import java.io.BufferedReader;
import java.io.FileReader;

public class CSVUtilities extends BaseClass
{
    public int countCSVRecords(String filePath)
    {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line represents a record
                count++;
            }
        } catch (Exception e)
        {
            ExceptionHandling.handleException(e);
        }
        return count;
    }
}
