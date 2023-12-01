import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

class AdventOfCode{


    final static Boolean DEBUG = false;
	final static Map<String, String> numberMap = Map.of("one", "o1e", 
														"two", "t2o",
														"three", "t3e",
														"four", "f4r",
														"five", "f5e",
														"six", "s6x",
														"seven", "s7n",
														"eight", "e8t",
														"nine", "n9e");


	
	public static void processInput(ArrayList<String> inputArray)
	{
		int calibration = 0;

		for(String line : inputArray)
		{
			int first = 0; 
			int second = 0;
			String myLine = dumbReplacer(line);
			for(char inputChar: myLine.toCharArray())
			{
				if(Character.isDigit(inputChar))
				{ 
					if(first==0)
					{
						first = Character.getNumericValue(inputChar);
						second = first;
					}
					else
						second = Character.getNumericValue(inputChar);
				}
			}
				calibration += first * 10 + second;	
				if(DEBUG)
				{
					System.out.println("[processInput]first: " + first );
					System.out.println("[processInput]second: " + second );
					System.out.println("[processInput]calibration: " + calibration );
				}
			
			
		}
		System.out.println("[processInput]Calibration: " + calibration);
	}

	public static String dumbReplacer(String line)
	{
		if(DEBUG)
			System.out.println("[dumbReplacer]Start:"+line);
		String replaced = line;
		int indexOfFirst = -1;

		Iterator mapIterator = numberMap.entrySet().iterator();
		while(mapIterator.hasNext())
		{
			Map.Entry numberEntry = (Map.Entry)mapIterator.next();
			replaced = replaced.replaceAll((String)numberEntry.getKey(), (String)numberEntry.getValue());

		}

		if(DEBUG)
			System.out.println("[dumbReplacer]End:"+replaced);
		return replaced;
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


