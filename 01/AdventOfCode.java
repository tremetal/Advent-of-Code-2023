import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;


class AdventOfCode{


    final static Boolean DEBUG = true;

	
	public static void processInput(ArrayList<String> inputArray)
	{
		int total = inputArray.stream().mapToInt(num ->Integer.parseInt(num)).sum();
		System.out.println("Total: " + total);
	}

    public static void main(String[] args) {

		if(args.length<1)
		{
			System.out.println("Usage: java AdventOfCode input.txt");

			return;
		}

		String fileName = args[0];
		ArrayList<String> inputArray  = null;
		inputArray = readFile(fileName);
		if(DEBUG)
			System.out.println("[main]inputArray:"+ inputArray);
		processInput(inputArray);
	}

	public static ArrayList<String> readFile(String fileName)
	{
		ArrayList<String> inputArray = new ArrayList<String>();

		String line = null;
		try {
            FileReader fileReader =
                new FileReader(fileName);

            BufferedReader bufferedReader =
                new BufferedReader(fileReader);
			
            while((line = bufferedReader.readLine()) != null)
            {
				inputArray.add(line);
				if(DEBUG)
					System.out.println("[readFile]line: " + line);
						
			}
            // Always close files.
            bufferedReader.close();
		}	
        catch(Exception e) {
            System.out.println(
                "Unable to open file '" +
                fileName + "'");
        }
		return inputArray;

	}


}


