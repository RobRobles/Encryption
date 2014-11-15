import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Encrypt {

	public ArrayList<String> Done = new ArrayList<String>(); 
	public int key = 0; 


	public Encrypt(int key) throws FileNotFoundException{

		this.key = key; 

		//getting the new file
		File file = new File("test.txt");
		//Scanner/reading text file
		Scanner input = new Scanner(file);

		perfromEncryption(key, input);

	}

	private void perfromEncryption(int key, Scanner input) {

		//ArrayList for the original message
		ArrayList<String> message = new ArrayList<String>(); 
		//ArrayList for the Alphabet
		ArrayList<String> Alphabet = new ArrayList<String>(); 
		String Alpha = "abcdefghijklmnopqrstuvwxyz"; 

		while(input.hasNextLine())
		{
			//temp string for holding variables 
			String temp = "";
			//getting the message
			temp = input.nextLine();

			//showing the original message 
			System.out.println(temp);

			//adding the message to the ArrayList
			for(int j = 0; j < temp.length(); j++)
			{
				char add = temp.charAt(j);
				String added = "";
				added = added + add;
				message.add(added); 
			}

			//keeping track of overflow (reaching the end of the alphabet) Depending on what the key is I take that 
			//and add the alphabet to itself that many times. The purpose of this is to catch overflow. If someone has a key 
			//that is 10 and the letter to be changed is at letter X the amount of letters it needs to shift will got to the end of 
			//alphabet then start over at the begining. 
			int mult = (key/26) + 1;

			for(int s = 0; s < mult; s++)
			{
				Alpha = Alpha + Alpha; 
			}

			for(int i = 0; i < Alpha.length(); i++)
			{
				char temp1 = Alpha.charAt(i);
				String temps = "";
				temps = temps + temp1;
				Alphabet.add(temps); 
			}

			//Start encrypting

			for(int c = 0; c < message.size(); c++)
			{
				for (int t = 0; t < Alphabet.size(); t++)
				{
					if(message.get(c).equals(" "))
					{
						Done.add(" ");
						break;
					}
					if(message.get(c).equals("-"))
					{
						Done.add("-");
						break;
					}
					if(message.get(c).equals("'"))
					{
						Done.add("'");
						break;
					}
					if(message.get(c).toLowerCase().equals(Alphabet.get(t)))
					{
						Done.add(Alphabet.get(t + key));
						break; 
					}
				}
			}


		}

	}

	public void getFinalMessage(){
		//Printing out the encoded message
		System.out.println("This is the key: " + key); 

		for(int e = 0; e < Done.size(); e++)
		{
			System.out.print(Done.get(e)); 
		}
	}


	public static void main(String[] args) throws FileNotFoundException{

		Encrypt secrete = new Encrypt(9);
		secrete.getFinalMessage();

	}
}
