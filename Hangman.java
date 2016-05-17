package HangMan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {

	String mysteryWord;
	//Allows String to be changable(even though they are immutable)
	StringBuilder currentGuess;
	ArrayList<Character>previousGuesses=new ArrayList<>();
	
	int maxTries=6;
	int currentTry=0;
	
	//Our database of the word to guess
	ArrayList<String> dictionary=new ArrayList<>();
	private static FileReader fileReader;
	//Allows us to iterate fileReader
	private static BufferedReader bufferFileReader;
	

	public Hangman() throws IOException{
		//initialize dictionary
		initializeStreams();
		mysteryWord=pickWord();
		currentGuess=initializeCurrentGuess();

	}


	public void initializeStreams()throws IOException{
		try{
			File inFile=new File("dictionary.txt");
			fileReader =new FileReader(inFile);
			bufferFileReader=new BufferedReader(fileReader);
			String currentLine=bufferFileReader.readLine();
		
			while(currentLine!=null){
				dictionary.add(currentLine);
				currentLine=bufferFileReader.readLine();
			}
			bufferFileReader.close();
			fileReader.close();
		}catch(IOException e){
			System.out.println("Could not init streams");
		}
	}
	
	public String pickWord(){
		Random rand=new Random();
		int wordIndex=Math.abs(rand.nextInt())% dictionary.size();
		return dictionary.get(wordIndex);
	}
	
	public StringBuilder initializeCurrentGuess(){
		StringBuilder current=new StringBuilder();
		for(int i=0; i<mysteryWord.length()*2; i++){
			if(i%2==0){
				current.append("_");
			}else {
				current.append(" ");
			}
		}
		return current;
	}
	
	//_ A_B...
	public String formalCurrentGuess(){
		return "Current Guess: "+currentGuess.toString();
	}
	
	public boolean gameOver(){
		if(didWeWin()){
			System.out.println();
			System.out.println("Congrats you guessed the right word");
			return true;
		}else if(didWeLoose()){
			System.out.println();
			System.out.println("Sorry you lost, you tried 6 guesses, the word was: "+mysteryWord);
			return true;
		}
		return false;
	}
	public boolean didWeWin(){
		String guess=getCondesedCurrentGuess();
		return guess.equals(mysteryWord);
	}
	
	public String getCondesedCurrentGuess(){
		String guess=currentGuess.toString();
		return guess.replace(" ", "");
		
	}
	
	public boolean didWeLoose(){
		return currentTry>=maxTries;
	}
	//Is guessed
	public boolean isGuessedAlready(char guess){
		//It returns true automatically if it contains it
		return previousGuesses.contains(guess);
	}
	
	public boolean playGuess(char guess){
		boolean isItAGoodGuess=false;
		for(int i=0; i<mysteryWord.length(); i++){
			if(mysteryWord.charAt(i)==guess){
				currentGuess.setCharAt(i*2, guess);
				isItAGoodGuess=true;
				previousGuesses.add(guess);
			}
		}
		if(!isItAGoodGuess){
			currentTry++;
		}
		return isItAGoodGuess;
			
	}
	
	//Draw a guy
	public String drawPicture(){
		switch(currentTry){
			case 0: return noPersonDraw();
			case 1: return addHeadDraw();
			case 2: return addBodyDraw();
			case 3: return addOneArmDraw();
			case 4: return addSecondArmDraw();
			case 5: return addFirstLegDraw();
			default: return fullPersonDraw();
		}
	}


	private String addFirstLegDraw() {
		// TODO Auto-generated method stub
		return " - - - - -\n"+
				"|   |\n"+
				"|   O\n" +
				"| / | \\ \n"+
				"|   |\n" +
				"|    \\ \n" +
				"|\n" +
				"|\n";
	}


	private String fullPersonDraw() {
		// TODO Auto-generated method stub
		return " - - - - -\n"+
				"|   |\n"+
				"|   O\n" +
				"| / | \\ \n"+
				"|   |\n" +
				"|  / \\ \n" +
				"|\n" +
				"|\n";
	}


	private String addSecondArmDraw() {
		// TODO Auto-generated method stub
		return " - - - - -\n"+
				"|   |\n"+
				"|   O\n" +
				"| / | \\ \n"+
				"|   |\n" +
				"|  	 \n" +
				"|\n" +
				"|\n";
	}


	private String addOneArmDraw() {
		// TODO Auto-generated method stub
		return " - - - - -\n"+
				"|   |\n"+
				"|   O\n" +
				"| / |  \n"+
				"|   |\n" +
				"|    \n" +
				"|\n" +
				"|\n";
	}


	private String addBodyDraw() {
		// TODO Auto-generated method stub
		return " - - - - -\n"+
				"|   |\n"+
				"|   O\n" +
				"| 	 |  \n"+
				"|   |	\n" +
				"|   	\n" +
				"|\n" +
				"|\n";
	}


	private String addHeadDraw() {
		// TODO Auto-generated method stub
		return " - - - - -\n"+
		"|   | \n"+
		"|   O \n" +
		"|     \n"+
		"|     \n" +
		"|     \n" +
		"|\n" +
		"|\n";
	}


	private String noPersonDraw() {
		return " - - - - -\n"+
				"|   |\n"+
				"|    \n" +
				"|     \n"+
				"|    \n" +
				"|   \n" +
				"|\n" +
				"|\n";
	}



	
	
	
	
}
