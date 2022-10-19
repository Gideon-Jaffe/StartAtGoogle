package GeneralProgramming;

import java.io.*;

public class CreatingAndDestroying {
    public static void main(String[] args) {
        writeLinesContaining("src/main/resources/textFile.txt", "src/main/resources/newText.txt", "story");
    }

    public static boolean writeLinesContaining(String inFile, String outFile, String checker) {
        try (FileReader fis = new FileReader(inFile);
             BufferedReader bufferedReader = new BufferedReader(fis);
             FileWriter fos = new FileWriter(outFile)){
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains(checker)) {
                    fos.write(line + "\n");
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        } catch (IOException e) {
            System.out.println("io exception");
            return false;
        }
        return true;
    }
}
