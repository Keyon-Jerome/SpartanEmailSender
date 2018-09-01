import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusinessParser {
	
	final static String filename = "C:\\Users\\keyoj\\eclipse-workspace\\EmailCreator\\src\\businesses.csv";
	static Scanner keyreader;
	static ArrayList<String[]> names = new ArrayList<String[]>();
	static boolean isRunning = true;
	
	public void parseData() {
		Scanner scan = new Scanner(System.in);
		while (isRunning) {
			System.out.println("System: Program is loading data...");
			loadData(filename);
			// Interface with data here, as it has been loaded successfully by the
			// "loadData" function.
			for (int i = 0; i < names.size(); i++) {
				System.out.println("***Company# " + (i + 1) + "***");
				System.out.println("Name: " + names.get(i)[0]);
				System.out.println("Email: " + names.get(i)[1]);
			}
			isRunning = false;
		}
		scan.close();
	}
	

	public static void loadData(String filename) {
		String line = "";
		int lineNumber = 0;
		File file = new File(filename);
		try {
			System.out.println("System: Opening file.");
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			while (line == null) {
				line = fileReader.readLine();
			}
			System.out.println("Starting reading process");
			while (line != null) {
				String[] data = line.split(",");
				if (data.length >= 15 ) {
					String companyName = data[1];
					String companyEmail = data[14];

					if (companyEmail.equals("") || companyName.equals("")) {
						System.out.println("Line " + lineNumber + ": has an invalid input");
					} else {
						String lineData[] = { companyName, companyEmail };
						names.add(lineData);
					}
				}
				else {
					System.out.println("System: line# " + lineNumber+ " is empty");
				}
				line = fileReader.readLine();
				lineNumber++;
			}
			System.out.println("Data has been loaded, " + names.size() / lineNumber + "are valid.");
			fileReader.close();
		} catch (IOException e) {
			System.out.println("data crashed");

		}

	}
}
