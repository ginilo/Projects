import java.util.Scanner;
import java.util.Random;
public class Blackjack_Project {

static Scanner read = new Scanner(System.in);
public static int random (int ret)
{
	//return a randomized number
	Random rand = new Random ();
	ret = rand.nextInt(10)+ 1;
	return ret;
}

public static int hit (int a) // adds value?
{
	int temp1 = 0;
	int nextcard = random (temp1);
	
	System.out.println("The Dealer gives you the card. It's " + nextcard + ".");
	int temp = nextcard + a;
	return temp;
}

public static boolean run(  )
{
	System.out.println("The dealer deals the first two cards to you and themselves.");
	boolean answer = true;
	
	int temp = 0;
	int playercard1 = random (temp);
	int playercard2 = random (temp);
	int playertotal = playercard1 + playercard2;

	int dealercard1 = random (temp);
	int dealercard2 = random (temp);
	int dealertotal = dealercard1 + dealercard2;
	
	System.out.println("Your First Card: " + playercard1);
	System.out.println("Your Second Card: " + playercard2);
	System.out.println("The dealer's second card is: " + dealercard2);

	
	if (playertotal > 21) 
	{
		System.out.println("You've busted! Your total: " + playertotal 
				+ " was over 21. The Dealer wins this round.");
		answer = false;
	}
	else if (playertotal == 21)
	{
		System.out.println("You win! Good job~");
		answer = false;
	}
	else if (playertotal < 21)
	{
		System.out.println("\nWould you like to hit? (1 for yes or 0 for no)");
		int a = read.nextInt();
		
			if (a == 1)
			{
				answer = true;
			}
			else
			{
				answer = false;
			}
		while (answer == true)
		{
				playertotal = hit (playertotal);
				if (playertotal >= 21) 
				{
					answer = false;
				}
				System.out.println("\nYour current total is " +  playertotal + ". \nWould you like to hit again? (1 for yes or 0 for no)");
				a = read.nextInt();
				if (a == 0)
				{
					answer = false;
				}
				if (playertotal >= 21) 
				{
					answer = false;
				}	
		}
		while (answer == false)
		{
			while (dealertotal <= 16)
			{
				dealertotal = dealertotal + random (temp);
			}
			System.out.println("\n");
			if (dealertotal > 21) 
			{
				System.out.println("You've won! Your total was " + playertotal + ". The Dealer has busted with " + dealertotal + ".");
				return false;
			}
			else if (dealertotal == 21)
			{
				System.out.println("You lose! The Dealer has an exact 21.");
				return false;
			}
			else if (playertotal == 21)
			{
				System.out.println("You win! Good job on getting a perfect 21!~");
				return false;
			}	
			else if (playertotal > 21)
			{
				System.out.println("You've busted! Your total: " + playertotal 
						+ " was over 21. The Dealer wins this round.");
				return false;
			}
			else if (dealertotal > playertotal)
			{
				System.out.println("You lose! The dealer had a higher number than you. (" + dealertotal
						+ " > " + playertotal + ")");
				return false;
			}
			else if (playertotal > dealertotal)
			{
				System.out.println("You Win! You have a higher number than the dealer. (" + playertotal
						+ " > " + dealertotal + ")");
				return false;
			}
			else if (dealertotal == playertotal)
			{
				System.out.println("You and the Dealer have the same number but the Dealer wins this round. Your total value was: " + playertotal+ ". ");
				return false;
			}
			}
		}
	return false;	
}

public static void main(String[] args)	
{
	System.out.println("Hi. Welcome to Gini's Blackjack Game. "
			+ "You will be playing against a computer in this game.");
	System.out.println("Would you like to play? (yes or no)");
	String answer = read.next();
	boolean runpro = true;	

	if (answer == "no")
	{
		runpro = false;
		System.out.println("\nThank you for playing!");
	}
	
	while (runpro == true) 
	{
		runpro = run();
	}
	
	if (runpro == false)
	{
		System.out.println("\nThank you for playing!");
	}
}
}//finished~~~ :3