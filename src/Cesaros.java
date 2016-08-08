/* Geoffrey Duong
 * 7/5/16
 * This program will generate 1 million pairs of random integers using Math.Random and the SHA1 PRNG.
 * Then it will get the GCD of all the pairs and use Cesaro's Theorem to statistically estimate the value of pi.
 */
import java.security.*;
import java.util.*;
public class Cesaros {
	//Method to find the GCD of two integers
	public static int gcd(int x, int y){
		  return (y == 0) ? x : gcd(y, x % y);
		}
	//Method to generate a random integer using Math.Random with a min and max passed as parameters
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	//Main method
	public static void main(String[] args) throws NoSuchAlgorithmException {
		//Create SecureRandom object with SHA1 PRNG and set a seed
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); 
		   //random.setSeed(629852305); 
		//Variables to temporarily hold the random integers generated   
		int x,y;
		//Holds the numbers of ones found
		int ones = 0;
		//Arrays to hold the GCD's
		int[] mathPairs = new int[1000000];
		int[] PRNGpairs = new int[1000000];
		//Generate random integers using Math.Random, then store the GCD's of the pairs
		for (int i = 0; i < mathPairs.length; i++)
		{
			x = randInt(0,999999999);
			y = randInt(0,999999999);
			mathPairs[i] = gcd(x,y);
		}
		//Find out how many ones there were
		for (int i = 0; i < mathPairs.length; i++)
		{
			if (mathPairs[i] == 1)
				ones++;
		}
		//Print out Math.Random results
		double probability = (double) ones/mathPairs.length;
		System.out.println("            PRNG          # of 1's               pi");
		System.out.println("            Math.Random    " + ones + "         " + Math.sqrt(6.0/probability));
		ones = 0;
		//Generate random integer pairs using SHA1, then store the GCD's
		for (int i = 0; i < PRNGpairs.length; i++)
		{
			x = random.nextInt(999999999);
			y = random.nextInt(999999999);
			PRNGpairs[i] = gcd(x,y);
		}
		//Count the number of ones
		for (int i = 0; i < PRNGpairs.length; i++)
		{
			if (PRNGpairs[i] == 1)
				ones++;
		}
		//Print out SHA1 results
		probability = (double) ones/PRNGpairs.length;
		System.out.println("            SHA1           " + ones + "         " + Math.sqrt(6.0/probability));
	}
}
