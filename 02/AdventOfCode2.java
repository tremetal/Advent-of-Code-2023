import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

class AdventOfCode2{

	final static Boolean DEBUG = false;

	final static int MAX_RED = 12;
	final static int MAX_GREEN = 13;
	final static int MAX_BLUE = 14;

	// replacing word with nummber padded by first/last
	final static Map<String, String> staticMap = 
		Map.of("blue", "b", "red", "r", "green", "g");



	public static void processInput(ArrayList<String> inputArray)
	{
		int total = 0;
		int totalPower = 0;
		for(String line : inputArray)
		{
			
			Scanner darkly = new Scanner(line).useDelimiter("\\D+");
			int game = darkly.nextInt();
			darkly.close();
			int minRed = 0, minBlue = 0, minGreen= 0, power=0;
			Boolean tooHighFound = false;

			// jam all the dice picks into int / char pair by replacing color words by first letter
			String[] games = dumbReplacer(line.substring(line.indexOf(":"))).replaceAll(";", ",").split(",");
			for(String colorPicks : games)
			{	

				String[] diceNumberAndColor = colorPicks.split(" ");

				int numberOfDice =  Integer.parseInt(diceNumberAndColor[1]);
				switch (diceNumberAndColor[2].charAt(0))
				{
					case 'b': 
						if(MAX_BLUE< numberOfDice)
							tooHighFound = true;
						if(minBlue< numberOfDice)
							minBlue = numberOfDice;
					break;
					case 'g': 
						if(MAX_GREEN< Integer.parseInt(diceNumberAndColor[1]))
							tooHighFound = true;
						if(minGreen< numberOfDice)
							minGreen = numberOfDice;								
					break;
					case 'r': 
						if(MAX_RED< Integer.parseInt(diceNumberAndColor[1]))
							tooHighFound = true;
						if(minRed< numberOfDice)
							minRed = numberOfDice;								
					break;												
				}
				power = minRed * minBlue * minGreen;				
			}
			totalPower+= power;
			if(!tooHighFound)
				total += game;

			if(DEBUG)
			{
				System.out.println("[processInput]tooHighFound: " + tooHighFound);
				System.out.println("[processInput]game: " + game);
				System.out.println("[processInput]power: " + power);
				System.out.println("[processInput]totalPower: " + totalPower);

				
			}
		}

		System.out.println("[processInput]total: " + total);
		System.out.println("[processInput]totalPower: " + totalPower);

	}

	// Replaces all instances of replacementMap keys with the values in the line
	public static String dumbReplacer(final String line, Map<String, String> replacementMap)
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
			System.out.println("Usage: java AdventOfCode2 input.txt");

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


