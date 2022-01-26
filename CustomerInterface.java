
/**
* CustomerInterface.java
* Hassham Malik
* The following code is solely for getting data from the user and sending it
* to the right places to ensure that the calculations are made correctly.
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CustomerInterface {
	public static void main(String[] args) {
		BST<MutualFundAccount> account_value = new BST<>();
		BST<MutualFundAccount> account_name = new BST<>();
		List<MutualFund> funds = new List<>();

		String first, last, email, password, mutualName, ticker;
		double cash, sharePrice, numShares, fee;

		File file = new File("mutual_funds.txt");
		try {
			Scanner input = new Scanner(file);
			while (input.hasNextLine()) {
				String ReadName = input.nextLine();
				String ReadTicker = input.nextLine();
				double ReadPricePerShare = input.nextDouble();
				input.nextLine();
				MutualFund temp = new MutualFund(ReadName, ReadTicker, ReadPricePerShare);
				funds.addLast(temp);
			}
			input.close();
			input = new Scanner(System.in);
			System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!");
			char userinput = ' ';
			while (userinput != 'X') {
				System.out.println();
				System.out.println("Please select from the following options:");
				System.out.println();
				System.out.println("A. Purchase a Fund");
				System.out.println("B. Sell a Fund");
				System.out.println("C. Display Your Current Funds");
				System.out.println("X. Exit");
				System.out.println();
				System.out.print("Enter your choice: ");
				userinput = input.next().charAt(0);

				if (userinput == 'A') {
					System.out.println();
					System.out.println("Please select from the options below:");
					System.out.println();
					System.out.print(funds.toString());
					System.out.print("Enter your choice: (1-7): ");
					int fundChoice = input.nextInt();
					input.nextLine();
					System.out.println();
					System.out.print("Enter the number of shares to purchase: ");
					int buyNum = input.nextInt();
					input.nextLine();
					funds.iteratorToIndex(fundChoice);
					MutualFundAccount tempAccount = new MutualFundAccount(buyNum, funds.getIterator());
					if (account_name.search(tempAccount, false, new NameComparator()) == null) {
						account_name.insert(tempAccount, new NameComparator());
						account_value.insert(tempAccount, new ValueComparator());
					} else {
						account_name.search(tempAccount, false, new NameComparator()).updateShares(buyNum);
					}
				} else if (userinput == 'B') {
					System.out.println();
					if (account_name.isEmpty()) {
						System.out.println("You don't have any funds to sell at this time.");
					} else {
						input.nextLine();
						System.out.println("You own the following mutual funds:");
						account_name.inOrderPrint();
						System.out.print("Enter the name of the fund to sell: ");
						String sellFund = input.nextLine();
						System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");
						String sellNum = input.nextLine();
						MutualFundAccount find = new MutualFundAccount(new MutualFund(sellFund));
						MutualFundAccount location = account_name.search(find, false, new NameComparator());
						if (sellNum.equals("all") || Integer.valueOf(sellNum) == location.getNumShares()) {
							System.out.println("testing");
							account_name.remove(location, new NameComparator());
							account_value.remove(location, new NameComparator());
						} else {
							location.updateShares(-Integer.valueOf(sellNum));
							account_value = new BST<MutualFundAccount>(account_name, new ValueComparator());
						}
					}
				} else if (userinput == 'C') {
					if (account_name.getSize() == 0) {
						System.out.println();
						System.out.println("You don't have any funds to display at this time.");
					} else {
						System.out.println();
						System.out.println("View your Mutual Funds By:");
						System.out.println();
						System.out.println("1. Name");
						System.out.println("2. Value");
						System.out.println();
						System.out.print("Enter Your choice (1 or 2): ");
						userinput = input.next().charAt(0);
						System.out.println();
						if (userinput == '1') {
							account_name.inOrderPrint();
						} else if (userinput == '2') {
							account_value.inOrderPrint();
						} else {
							System.out.println("Invalid Choice!");
						}
					}
				} else if (userinput == 'X') {
					System.out.println();
					System.out.println("Goodbye!");
				} else {
					System.out.println();
					System.out.println("Invalid menu option. Please enter A-C or X to exit.");
				}
			}

			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file.");
		}
	}
}