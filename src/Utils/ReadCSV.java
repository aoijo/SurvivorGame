package Utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadCSV {

    public static String[][] read(String filePath) {
        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            List<CSVRecord> records = csvParser.getRecords();
            if (records.isEmpty()) {
                return new String[0][0];
            }

            int numRows = records.size();
            int numCols = records.get(0).size();
            String[][] data = new String[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                CSVRecord record = records.get(i);
                for (int j = 0; j < numCols; j++) {
                    data[i][j] = record.get(j);
                }
            }

            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return new String[0][0]; // Return an empty array on error
        }
    }

    public String[] readId(String filePath, int id) {
        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            List<CSVRecord> records = csvParser.getRecords();

            // Start from the second record (index 1)
            for (int i = 1; i < records.size(); i++) {
                CSVRecord record = records.get(i);
                if (record.size() > 0) {
                    try {
                        int recordId = Integer.parseInt(record.get(0));
                        if (recordId == id) {
                            String[] row = new String[record.size()];
                            for (int j = 0; j < record.size(); j++) {
                                row[j] = record.get(j);
                            }
                            return row;
                        }
                    } catch (NumberFormatException e) {
                        // Handle the case where the ID is not a valid integer
                        System.out.println("Invalid ID format: " + record.get(0));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0]; // Return an empty array if ID not found or on error
    }

    public static int[] readIntList(String input) {
        // Split the input string by spaces
        String[] stringNumbers = input.split(" ");

        // Create an int array with the same length as the string array
        int[] intArray = new int[stringNumbers.length];

        // Convert each string number to an int and store it in the int array
        for (int i = 0; i < stringNumbers.length; i++) {
            intArray[i] = Integer.parseInt(stringNumbers[i]);
        }

        return intArray;
    }
}
