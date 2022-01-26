# Investing-Machine
In this project I utilzed Linked Lists and Binary Search Trees. I created multiple classes with implemented methods that would allow me to construct, copy, mutate, and access objects within the Linked List, and Binary Search Trees.

The following project takes in data from a .txt file which includes information about the type of fund, the ticker symbol, and price. All this information is taken in and sored into a Linked List. The user is prompted with a welcome message, and instructions on what they are able to do.

The user is able to preform the following tasks in this program: purchasing a fund, selling a fund, displaying current funds, and exit. If the user tries to do something that they are not supposed to, it tells them that they chose an invalid option and sends them back to the orignal options.

If the user choses to purchase a fund they will be prompted with all the options that are able to be bought, they will then chose which fund to invest in, and then they will be asked about the quantity of shares they would like to purchase. This information is then stored into the BST Account that we created for the user.

If the user decides that they would like to sell, it will first check to see if they user has anything to sell, which will then be displayed. The user is then able to decide how many shares they would like to sell, which is then saved into the BST Account for the user.

If the user choses to display their current funds, they will get the choice to be able to have their funds sorted by either name or price. Once they make their decision, it will display all their owned funds. Lastly, if at any stage the user would like the exit, they can just type X, and it will print a goodbye message for the user.

Breakdown of Classes:
  - BST.java: Focuses directly on Binary Search Trees, implemented many methods in this class to help navigate through a BST. There are a wide range of constructors, accessors, and mutator methods.
  - BSTTest.java: Used for testing the functionality of all the methods in BST.java to make sure they are working properly.
  - List.java: Focuses directly on Linked Lists, implemented many methods in this class to help navigate through a List. There are a wide range of constructors, accessors, and mutator methods.
  - MutualFund.java: Creates a linked list along with methods specifically for reading the .txt file and storing all the data in their respective variables.
  - MutalFundAccount.java: Creates a BST along with methods specifically for the user's account and updates whenvever the user decided to purchase or sell any funds.
  - CustomerInterface.java: The main program which starts with reading the given .txt file and then moves onto the user interface. We are easliy able to update the Lists and the BSTs in this program because of all the methods that have been implemented in each of the classes.
  - mutual_funds.txt: Included a sample .txt file
