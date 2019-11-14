/* (\/) (°,,,°) (\/) (\/) (°,,,°) (\/) (\/) (°,,,°) (\/) (\/) (°,,,°) (\/)
 * asciicondense.java
 *
 *   A program that prompts the user for an input file name and, if that file exists,
 *   displays each line of that file in reverse order.
 *   Used to practice simple File I/O and breaking code up into methods as well as a first
 *   step to implementing Lab13b.java - reversing the entire file and Lab13c.java writing
 *   output to a separate output file.
 *
 * @author Justin Riley
 * @date 04072016
 *
 */
package ohiofi;
import java.io.*;
import java.util.*;


public class asciicondense {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String inputFileName = getFileName(keyboard,"Enter an input file name: ");
		String outputFileName = getFileName(keyboard,"Enter an output file name: ");
		ArrayList<String> fullText = new ArrayList<String>();
		if(outputFileName.equals(inputFileName)){
			//System.out.println("");
			//inFile.close();
			System.out.println("ERROR! Your input file and output file MUST be different.");
			return;
		}

		try{
			File file = new File(inputFileName); // Open existing file
			Scanner inFile = new Scanner(file);

			try{
				PrintWriter outFile = new PrintWriter(outputFileName); // Create and open the file
				// By default, it will erase the old test.txt file if it already exists
				int lineCounter = 0;
				while(inFile.hasNext()){
					if(lineCounter % 2 == 0){ // only keep even numbered rows
						fullText.add(condenseLine(inFile.nextLine()));
					}
					else{
						String tempString = inFile.nextLine();
					}
					lineCounter++;

				}

				for(int i = 0; i < fullText.size(); i++){
					outFile.println(fullText.get(i));
				}
				outFile.close();
			}
			catch(IOException e){
				System.out.println("Error writing to file "+outputFileName);
			}
			catch(Exception e){
				System.out.println("Error");
			}
			inFile.close();
			System.out.println("Success!");
		}
		catch(FileNotFoundException e){ // catch inFile errors
			System.out.println("There was a problem reading from "+inputFileName);
		}
		catch(Exception e){
			System.out.println("Error");
		}
	}

	// Given a Scanner as input prompts the user to enter a file name.  If given an
	// empty line, respond with an error message until the user enters a non-empty line.
	// Return the string to the calling program.  Note that this method should NOT try
	// to determine whether the file name is an actual file - it should just get a
	// valid string from the user.
	private static String getFileName(Scanner inScanner,String prompt) {
		String result;
		System.out.print(prompt);
		result = inScanner.nextLine();
		while(result.length() < 1){
			System.out.println("Input file name cannot be empty!");
			System.out.print("Enter an input file name: ");
			result = inScanner.nextLine();
		}
		return result;
	}

	// Given a String as input return the expanded String to the calling program.
	private static String condenseLine(String inString) {
		String result = "";
		for(int i=0; i<inString.length(); i++){
			if(i % 2 == 0){ // only keep even numbered columns
				result+=inString.charAt(i);
			}
		}
		return result;
	}
}
