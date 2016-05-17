package HangMan;

import java.io.IOException;
import java.util.Scanner;

public class HangmanApplication {

	
	public static void main(String[]args) throws IOException{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to Hangman, you have 6 times to guess my word");
		System.out.println();
		System.out.println("I have picked my word, below is a Picture and below that is"
				+ " your current guess."
				+ "Everytime you guess wrong, I add a body part to the picture. Full person loose.");
		//keep playing
		boolean doYouWantToPlay=true;
		while(doYouWantToPlay){
			System.out.println();
			System.out.println("Alright, lets play!");
			System.out.println();
			Hangman game=new Hangman();
			
			do{
				//Draw the things..
				System.out.println();
				System.out.println(game.drawPicture());
				System.out.println();
				System.out.println(game.formalCurrentGuess());
				//System.out.print(game.mysteryWord);
				System.out.println();
			//Get the guess
			System.out.println("Put your guess character");
				char guess=(sc.next().toLowerCase()).charAt(0);
				System.out.println();
				
				//Check if the guess has been guessed before
				while(game.isGuessedAlready(guess)){
					System.out.println("Try again, you guessed that character");
					 guess=(sc.next().toLowerCase()).charAt(0);
				}
				//Play the guess
				if(game.playGuess(guess)){
					System.out.println("Great guess, the character is in the word");
				}else{
					System.out.println("Unfortunately that character is not correct");
				}
			}
			while(!game.gameOver());

			//Play again or no
			System.out.println();
			System.out.println("If you want to play please press V");
			Character response=sc.next().charAt(0);
			doYouWantToPlay=(response=='V');
		}
	}


} 
