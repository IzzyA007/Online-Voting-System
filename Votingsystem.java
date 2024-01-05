import java.util.InputMismatchException;
import java.io.*;
import java.util.Scanner;

/* Interface that will be used by the classes to save data into txt files */
interface VoterDataBase {
	void saveVoteCounts();
	void loadVoteCounts();
}

/* VoterID class. This class will be used to store information about the user as well as implement
 * the VoterDabase to store their vote into a txt file.*/
class VoterID implements VoterDataBase {

	private int age;
	private String name;
	private boolean isCitizen;
	private String state;

	/* Saving Datat into txt file */
	// Save vote counts to a file

	// Vote counts
	private int voteCounterp1 = 0;
	private int voteCounterp2 = 0;
	private int voteCounterp3 = 0;
	private int voteCounters1 = 0;
	private int voteCounters2 = 0;
	private int voteCounters3 = 0;
	private int voteCounterh1 = 0;
	private int voteCounterh2 = 0;
	private int voteCounterh3 = 0;

	/* Method that save the amount of times the user votes for a particular candidate */
	public void saveVoteCounts() {
		//This function saves the vote counts into a text file so that votes are kept account of.
		try (PrintWriter writer = new PrintWriter(new FileWriter("vote_counts.txt"))) {

			writer.println(voteCounterp1);
			writer.println(voteCounterp2);
			writer.println(voteCounterp3);
			writer.println(voteCounters1);
			writer.println(voteCounters2);
			writer.println(voteCounters3);
			writer.println(voteCounterh1);
			writer.println(voteCounterh2);
			writer.println(voteCounterh3);

		} catch (IOException e) {
			System.out.println("Error saving vote counts to file: " + e.getMessage());
		}
	}

	/* Load vote counts from a file */
	public void loadVoteCounts() {
		//this function Takes the vote counts from the text files and inputs them into the individual vote counts in the code
		try (BufferedReader reader = new BufferedReader(new FileReader("vote_counts.txt"))) {
			voteCounterp1 = Integer.parseInt(reader.readLine());
			voteCounterp2 = Integer.parseInt(reader.readLine());
			voteCounterp3 = Integer.parseInt(reader.readLine());
			voteCounters1 = Integer.parseInt(reader.readLine());
			voteCounters2 = Integer.parseInt(reader.readLine());
			voteCounters3 = Integer.parseInt(reader.readLine());
			voteCounterh1 = Integer.parseInt(reader.readLine());
			voteCounterh2 = Integer.parseInt(reader.readLine());
			voteCounterh3 = Integer.parseInt(reader.readLine());

		} catch (FileNotFoundException e) {

			// Exception for if the file isnt found to be saved in the code

		} catch (IOException | NumberFormatException e) {

			System.out.println("Error loading vote counts from file: " + e.getMessage());
		}
	}


	/* Function that verfies if the user is eligible to vote. Checks if the user
	 if of voting age, their name, and if they are a citizen.*/
	public void getVoterInfo() throws Exception {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("What is your age: ");//asking user for their age
			age = scanner.nextInt();//user input

			if (age < 18) {
				if(age<0) {
					throw new Exception("Invalid age Entered. Please inpu a real age");//exception for if user inputs negative number
				}
				System.out.println("You are not at the legal age to vote yet.");//message for if user is under age
				System.exit(0);
			}

		} catch (InputMismatchException e) {
			System.out.println("Invalid age");//catches exception if the input is letters and not a number
			System.exit(0);
		}

		System.out.print("Whats is your name: ");//ask user for their name
		scanner.nextLine();//scans user input
		name = scanner.nextLine();//saves input as age
		if (!name.matches("[a-zA-Z]+")) {
			throw new Exception("Invalid name. Please Enter a name with only letters");//exception for if user input contains numbers
		}

		System.out.print("Citizenship(y/n): ");//asks user thier citezenship
		char citizenshipInput = scanner.next().charAt(0);//scans user input
		isCitizen = citizenshipInput == 'Y' || citizenshipInput == 'y';
		if (citizenshipInput != 'Y' && citizenshipInput != 'y') {
			if (citizenshipInput == 'n' || citizenshipInput == 'N') {//checking if the user is not citezen
				System.out.println("You are ineligible to vote.");//message if user types n/N as their citezenship
				System.exit(0);
			}

			else {

				throw new Exception("Please enter 'y' (yes) or 'n' (no) to confirm your citezenship");//exeption message if user puts an input that is not y or n
			}
		}

		System.out.print("State of residence: ");//asks user for state of residence
		state = scanner.next();//scans user input

		if (!state.matches("[a-zA-Z]+")) {
			throw new Exception("Invalid State. Please enter the name of your state");//exception for is user enters an input with numbers in it

		}

		System.out.print("\nYou are ready to start voting.\n");

	}

}

/* abstract election class with election type and year variables */
abstract class Election {
	public String electionType;
	public int year;

}

/* For president election type there is President class which is subclass of Election
declaring variables name age and Party in this subclass */
class President extends Election {
	public String name;
	public int age;
	public String Party;

	public void DisplayPresidentCanidateInfo(String x, int y, String z) {
		//assigning the values passed into the function to the variables declared in this President sub class 
		name = x;
		age = y;
		Party = z;
		//lists out the candidate info from the variables
		System.out.println("Candidate name: " + name);
		System.out.println("   Candidate age: " + age);
		System.out.println("   Candidate Party: " + Party);
		System.out.println("\n");
	}

}

/* For president election type there is Senate class which is subclass of Election
declaring variables name age and Party in this subclass */
class Senate extends Election {
	//declaring variables name age and Party in this subclass
	public String name;
	public int age;
	public String Party;
	
	/* Method the prints 3 candidates with their name, age and political party */
	public void DisplaySenateCanidateInfo(String x, int y, String z) {
		//assigning the values passed into the function to the variables declared in this President sub class 
		name = x;
		age = y;
		Party = z;
		//lists out the candidate info from the variables
		System.out.println("Candidate name: " + name);
		System.out.println("   Candidate age: " + age);
		System.out.println("   Candidate Party: " + Party);
		System.out.println("\n");

	}
}

/* For president election type there is House class which is subclass of Election
declaring variables name age and Party in this subclass */
class House extends Election {
	//declaring variables name age and Party in this subclass
	public String name;
	public int age;
	public String Party;

	/* Method the prints 3 candidates with their name, age and political party */
	public void DisplayHouseCanidateInfo(String x, int y, String z) {
		//assigning the values passed into the function to the variables declared in this President sub class 
		name = x;
		age = y;
		Party = z;
		//lists out the candidate info from the variables
		System.out.println("Candidate name: " + name);
		System.out.println("   Candidate age: " + age);
		System.out.println("   Candidate Party: " + Party);
		System.out.println("\n");

	}

}

/* VotingSytem class that will utilize all the sub classes and interface */
public class Votingsystem {

	public static void main(String[] args) throws Exception {
		//initializing local vote count variables
		int voteCounterp1 = 0;
		int voteCounterp2 = 0;
		int voteCounterp3 = 0;
		int voteCounters1 = 0;
		int voteCounters2 = 0;
		int voteCounters3 = 0;
		int voteCounterh1 = 0;
		int voteCounterh2 = 0;
		int voteCounterh3 = 0;

		// load Voter counts from txt file
		VoterID voter = new VoterID();
		voter.loadVoteCounts();


		// Creating of Objects from multiple classes
		Scanner sc = new Scanner(System.in); // Create object from the scanner class to accept inputs from the user

		// Creation of Objects of President class
		President pres1 = new President();
		President pres2 = new President();
		President pres3 = new President();
		// Creation of Objects of Senate class
		Senate sene1 = new Senate();
		Senate sene2 = new Senate();
		Senate sene3 = new Senate();
		// Creation of Objects of House class
		House hous1 = new House();
		House hous2 = new House();
		House hous3 = new House();

		System.out.println("Welcome to the Election system.\nLets get some information from you before you can begin voting.");
		VoterID voter1 = new VoterID(); // Create instance of the the VoterID class
		voter1.getVoterInfo();

		System.out.println("Please select the year in which you are voting.");//asking the user for voting year

		int yearSelected = sc.nextInt(); //Scans the year the usr enters

		if (yearSelected % 2 == 0) {//If the year is divisible by 2, senate and house elections are an option
			if (yearSelected % 4 == 0) { // It is a presidential election year if divisible by 4 (can still a House/Senate year though
				System.out.println("Choose the election type you want to vote for this year.\n1. Presidential\n2. Senate\n3. House\n");
				System.out.println("Type 1, 2, or 3 then press enter.");//asks the user for what election they wish to vote for
				char electionSelected = sc.next().charAt(0);

				if (electionSelected == '1') {//if user selects presidential election, it presents the candidates
					System.out.println("You chose president selection");
					System.out.print("1. ");
					pres1.DisplayPresidentCanidateInfo("Donald Trump", 75, " Republican");// calls upon the DisplayPresidentCandidateinfo with each candidate's info
					System.out.print("2. ");
					pres2.DisplayPresidentCanidateInfo("Joe Biden", 81, " Democrat");
					System.out.print("3. ");
					pres3.DisplayPresidentCanidateInfo("Jacob Hornberger", 75, " Libertarian");

					// Allow the user to vote for their desired president

					char candidateSelected = sc.next().charAt(0);//scans for selected candidate
					//gives message for the candidate user voted for
					if (candidateSelected == '1') { // The user voted for President Candidate 1, Donald Trump
						System.out.println("You have voted for " + pres1.name);
						voteCounterp1++;//adds voter count

					}

					else if (candidateSelected == '2') { // The user voted for President Candidate 2, Joe Biden
						System.out.println("You have voted for " + pres2.name);
						voteCounterp2++;//add voter count
					}

					else if (candidateSelected == '3') { // The user voted for President Candidate 3, Jacob Hornberger
						System.out.println("You have voted for " + pres3.name);
						voteCounterp3++;//add voter count
					}

				}


				else if (electionSelected == '2') {//for the senate election
					//similar to the president election
					System.out.println("You chose Senate selection");
					System.out.print("1. ");
					sene1.DisplaySenateCanidateInfo("Mitt Romney", 76, "Republican");
					System.out.print("2. ");
					sene2.DisplaySenateCanidateInfo("Laphnonza Butler", 44, "Democrat");
					System.out.print("3. ");
					sene3.DisplaySenateCanidateInfo("Marc Victor", 54, "Libertarian");

					// Allow the user to vote for their desired candidate

					char candidateSelected = sc.next().charAt(0);

					if (candidateSelected == '1') { // The user voted for Senate Candidate 1, Mitt Romney

						System.out.println("You have voted for " + sene1.name);

						voteCounters1++;

						System.out.println("Your Candidate now has this number of votes:" + voteCounters1);

					}

					else if (candidateSelected == '2') { // The user voted for Senate Candidate 2, Laphonza Butler

						System.out.println("You have voted for " + sene2.name);

						voteCounters2++;

					}

					else if (candidateSelected == '3') { // The user voted for Senate Candidate 3, Marc Victor

						System.out.println("You have voted for " + sene3.name);

						voteCounters3++;

					}

				}


				else if (electionSelected == '3') {// for the house election
					//similar to the previous elections
					System.out.println("You chose House selection");
					System.out.print("1. ");
					hous1.DisplayHouseCanidateInfo("Earl Carter", 66, "Republican");
					System.out.print("2. ");
					hous2.DisplayHouseCanidateInfo("Sanford Bishop Jr.", 76, "Democrat");
					System.out.print("3. ");
					hous3.DisplayHouseCanidateInfo("David Gregors", 71, "Libertarian");

					// Allow the user to vote for their desired candidate
					char candidateSelected = sc.next().charAt(0);
					if (candidateSelected == '1') { // The user voted for House Candidate 1, Earl Carter
						System.out.println("You have voted for " + hous1.name);
						voteCounterh1++;
					}

					else if (candidateSelected == '2') { // The user voted for House Candidate 2, Sanford Bishop Jr
						System.out.println("You have voted for " + hous2.name);
						voteCounterh2++;

					}

					else if (candidateSelected == '3') { // The user voted for House Candidate 3, David Gregors
						System.out.println("You have voted for " + hous3.name);
						voteCounterh3++;

					}

				}

			}

			else { // Its a Senate and House election year (only divisible by 2)

				System.out.println("Choose the election type you want to vote for this year.\n1. Senate\n2. House\n");

				System.out.println("Type 1 or 2 then press enter.");

				char electionSelected = sc.next().charAt(0);

				// Display all the candidates for the Senate election

				if (electionSelected == '1') {
					System.out.println("You chose Senate selection.\nBelow are all the candiates for this election, select whichever you you please.");
					System.out.print("1. ");
					sene1.DisplaySenateCanidateInfo("Mitt Romney", 76, "Republican");
					System.out.print("2. ");
					sene2.DisplaySenateCanidateInfo("Laphonza Butler", 44, "Democrat");
					System.out.print("3. ");
					sene3.DisplaySenateCanidateInfo("Marc Victor", 54, "Libertarian");

					// Allow the user to vote for their desired candidate
					char candidateSelected = sc.next().charAt(0);
					if (candidateSelected == '1') { // The user voted for House Candidate 1, Mitt Romney
						System.out.println("You have voted for " + sene1.name);
						voteCounters1++;
					}

					else if (candidateSelected == '2') { // The user voted for House Candidate 1, Laphonza Butler
						System.out.println("You have voted for " + sene2.name);
						voteCounters2++;
					}

					else if (candidateSelected == '3') { // The user voted for House Candidate 1, Marc Victor
						System.out.println("You have voted for " + sene3.name);
						voteCounters3++;
					}
				}

				// Display all the candidates for the House election
				else if (electionSelected == '2') {

					System.out.println("You chose House selection");
					System.out.print("1. ");
					hous1.DisplayHouseCanidateInfo("Earl Carter", 66, "Republican");
					System.out.print("2. ");
					hous2.DisplayHouseCanidateInfo("Sanford Bishop Jr.", 76, "Democrat");
					System.out.print("3. ");
					hous3.DisplayHouseCanidateInfo("David Gregors", 71, "Libertarian");

					// Allow the user to vote for their desired candidate
					char candidateSelected = sc.next().charAt(0);
					if (candidateSelected == '1') { // The user voted for House Candidate 1, Earl Carter
						System.out.println("You have voted for " + hous1.name);
						voteCounterh1++;
					}

					else if (candidateSelected == '2') { // The user voted for House Candidate 1, Sanford Bishop Jr
						System.out.println("You have voted for " + hous2.name);
						voteCounterh2++;
					}

					else if (candidateSelected == '3') { // The user voted for House Candidate 1, David Gregors
						System.out.println("You have voted for " + hous3.name);
						voteCounterh3++;
					}
				}
			}
		}

		else {//for if its not an election year
			System.out.println("There are no elections in the year " + yearSelected);//message displayed for no elections that year
		}

		voter.saveVoteCounts();//saves the votercounts in the text file
	}
}