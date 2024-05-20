package helper;

import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.FilenameFilter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

public class FileUtility
{
    public String getvisbilefilecount(String downloadDir)
    {
        // Get a list of CSV files in the directory excluding hidden files
        File dir = new File(downloadDir);
        File[] visibleCsvFiles = dir.listFiles(new CompositeFilter(new CSVFileFilter(), new VisibleFileFilter()));

        // Count the CSV files
        int visibleCsvFileCount = visibleCsvFiles != null ? visibleCsvFiles.length : 0;

        System.out.println("Total visible CSV files in directory: " + visibleCsvFileCount);

        // Sort visible CSV files by last modified time (latest first)
        Arrays.sort(visibleCsvFiles, Comparator.comparingLong(File::lastModified).reversed());

        // Get the latest visible CSV file
        File latestVisibleCsvFile = visibleCsvFiles.length > 0 ? visibleCsvFiles[0] : null;

        if (latestVisibleCsvFile != null) {
            System.out.println("Latest visible CSV file: " + latestVisibleCsvFile.getAbsolutePath());
        } else {
            System.out.println("No visible CSV files found in the directory.");
        }
        return latestVisibleCsvFile.getAbsolutePath();
    }
    public static class CompositeFilter implements FilenameFilter {
        private final FilenameFilter[] filters;

        CompositeFilter(FilenameFilter... filters) {
            this.filters = filters;
        }

        @Override
        public boolean accept(File dir, String name) {
            for (FilenameFilter filter : filters) {
                if (!filter.accept(dir, name)) {
                    return false;
                }
            }
            return true;
        }
    }
    // Custom filter to exclude hidden files
    static class VisibleFileFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return !name.startsWith(".");
        }
    }

    // Custom filter to filter only CSV files
    static class CSVFileFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".csv");
        }
    }

    public boolean waitforfiletodownload(String downloadDir,String filename)
    {
        boolean flag=false;
        File file= new File(downloadDir,filename);
        FluentWait<File> wait= new FluentWait<File>(file)
                .withTimeout(Duration.ofMinutes(5))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class)
                .withMessage("File is not downloaded");
       flag= wait.until(f->f.exists() && f.canRead());
        return flag;
    }
}
