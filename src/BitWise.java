import java.io.*;
import java.util.Random;

public class BitWise
{
	public static void main(String[] args)
	{
		Console console = System.console();
		//String input = console.readLine("Decrypt or encrypt(decrypt/encrypt):");
		String input = "";
		if(args != null)
		{
			if(args.length > 0)
				input = args[0];
		}
		
		if(input.toLowerCase().equals("encrypt"))
		{
			input = console.readLine("File (and extension) to encrypt:");
			System.out.println("Encrypting....");
			StringBuilder sb = new StringBuilder();
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(input));
				char[] buff = new char[1024];
				int numRead = 0;
				while ((numRead = reader.read(buff)) != -1)
				{
					String line = String.valueOf(buff, 0, numRead);
					sb.append(line);
				}
			}
			catch(Exception e){}
		
			Random rand = new Random(System.currentTimeMillis());
			String fileIn = sb.toString();
			sb.delete( 0, sb.length());
			StringBuilder sb2 = new StringBuilder();
			for(int x = 0; x < fileIn.length(); x++)
			{
				//generate random piece of key
				char aChar = (char)rand.nextInt(127);
				sb.append(aChar);
			
				//generate encrypted piece of file
				sb2.append((char)(fileIn.charAt(x) ^ aChar));
			}
			
			//write out the encrypted file
			try 
			{
				StringBuilder tmpSB = new StringBuilder();
				for(int x = 0; x < input.length(); x++)
				{
					if(input.charAt(x) != '.')
					{
						tmpSB.append(input.charAt(x));
					}
					else
						break;
				}
				
    			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter( tmpSB.toString()+".cryp", false)));
				out.println(sb2.toString());
    			out.close();
			} 
			catch(IOException e){}
			
			//write out the encrypted key
			try 
			{
				StringBuilder tmpSB = new StringBuilder();
				for(int x = 0; x < input.length(); x++)
				{
					if(input.charAt(x) != '.')
					{
						tmpSB.append(input.charAt(x));
					}
					else
						break;
				}
    			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter( tmpSB.toString()+".key", false)));
				out.println(sb.toString());
    			out.close();
			} 
			catch(IOException e){}
		
		}
		else if(input.toLowerCase().equals("decrypt"))
		{
			input = console.readLine("Encrypted file (and extension) to decrypt:");
			String input2 = console.readLine("Key to decrypt with (and extension):");
			System.out.println("Decrypting....");
			
			//retrieve the encrypted file contents
			StringBuilder sb = new StringBuilder();
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(input));
				char[] buff = new char[1024];
				int numRead = 0;
				while ((numRead = reader.read(buff)) != -1)
				{
					String line = String.valueOf(buff, 0, numRead);
					sb.append(line);
				}
			}
			catch(Exception e){}
			String encryptedString = sb.toString();
			sb.delete( 0, sb.length());
			
			
			//retrieve the key
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(input2));
				char[] buff = new char[1024];
				int numRead = 0;
				while ((numRead = reader.read(buff)) != -1)
				{
					String line = String.valueOf(buff, 0, numRead);
					sb.append(line);
				}
			}
			catch(Exception e){}
			String keyString = sb.toString();
			sb.delete( 0, sb.length());
			
			//generate decrypted string
			String decryptedString = "";
			for(int x = 0; x < encryptedString.length(); x++)
			{
				sb.append((char)(keyString.charAt(x) ^ encryptedString.charAt(x)));
			}
			decryptedString = sb.toString();
			
			//write out the original file
			try 
			{
				StringBuilder tmpSB = new StringBuilder();
				for(int x = 0; x < input.length(); x++)
				{
					if(input.charAt(x) != '.')
					{
						tmpSB.append(input.charAt(x));
					}
					else
						break;
				}
    			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter( tmpSB.toString()+".decrypted", false)));
				out.println(decryptedString);
    			out.close();
			} 
			catch(IOException e){}
			
		}
		else
		{
			System.out.println("\nNo arguments supplied\nPlease run with 'java BitWise encrypt' or 'java BitWise decrypt'");
		}
		
		
	}
}