import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

class AdventOfCode{

	final static Boolean DEBUG = false;

	// replacing word with nummber padded by first/last
	final static Map<String, String> staticMap = 
		Map.of("one", "o1e",
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
			int second=0, first = 0;
			// replace number words with padded numbers
			String myLine = dumbReplacer(line);
			for(char inputChar: myLine.toCharArray())
			{
				if(Character.isDigit(inputChar))
				{
					second =  Character.getNumericValue(inputChar);
					if(first==0)
						first = second;
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

	// Replaces all instances of replacementMap keys with the values in the line
	public static String dumbReplacer(final String line, final Map<String, String> replacementMap)
	{
		if(DEBUG)
			System.out.println("[dumbReplacer]Start:"+line);

		String replaced = new String(line);
		Iterator<Map.Entry<String,String>> mapIterator = replacementMap.entrySet().iterator();
		while(mapIterator.hasNext())
		{
			Map.Entry<String,String> numberEntry = mapIterator.next();
			replaced = replaced.replaceAll((String)numberEntry.getKey(), (String)numberEntry.getValue());
		}

		if(DEBUG)
			System.out.println("[dumbReplacer]End:"+replaced);
		return replaced;
	}

	// defaults to static map
	public static String dumbReplacer(String line)
	{
		return dumbReplacer(line, staticMap);
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
            System.out.println("Unable to open file '" + fileName + "'");
			System.exit(0);
        }
		return inputArray;

	}


}


