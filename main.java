/* Lauren Sherman */

package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int dealCard(int r){ //deals card that has not been used
        Random randGen = new Random();
        boolean [] used = new boolean [52];
        for (int i = 0; i < 52; i++) {
            used[i] = false;
        }
        while (used[r] == true) { //This while loop makes sure the same card is not dealt twice.
            r = randGen.nextInt(51);
        }
        used[r] = true;
        return r;
    }
    public static void announceOverallWinner(int dealerWins, int playerWins) {
        System.out.println("Dealer's points: " + dealerWins); //Displays points again
        System.out.println("Your points: " + playerWins);
        if (dealerWins > playerWins){ //Announces dealer as winner if dealer won more games
            System.out.println("Dealer wins! Thank you for playing.");
        }
        else if (dealerWins < playerWins){ //Announces player as winner if player won more games
            System.out.println("You win! Thank you for playing.");
        }
        else if (dealerWins == playerWins){ //Announces tie if both won equal games
            System.out.println("You and the dealer have tied! Thank you for playing.");
        }
    }
    public static boolean playGame(){ //represents each round a player wants to play
        String player = new String();
        String dealer = new String();
        int playerScore;
        int dealerScore;
        int numAcesP;
        int numAcesD;
        String [] cardName = {"AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC"};
        int [] cardVal = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        boolean [] used = new boolean [52];
        for (int i = 0; i < 52; i++) {
            used[i] = false;
        }
        Scanner scnr = new Scanner(System.in);
        Random randGen = new Random();
        int playerWins = 0;
        int dealerWins = 0;
        String answer1 = new String();
        String keepPlaying = new String();
        int playerCard1;
        int playerCard2;
        int playerNewCard;
        int dealerCard1;
        int dealerCard2;
        int dealerNewCard;
        String hitMe = new String();

        playerScore = 0;
        dealerScore = 0;
        numAcesP = 0;
        numAcesD = 0;
        int r = 0;
        System.out.println("Dealer's non-hidden card: ");
        r = randGen.nextInt(51);
        dealCard(r);
        dealerCard1 = r;
        if (cardName[dealerCard1].charAt(0) == 'A') { //These loops count the number of aces the dealer or player has
            ++numAcesD;
        }
        r = randGen.nextInt(51);
        dealCard(r);
        dealerCard2 = r;
        if (cardName[dealerCard2].charAt(0) == 'A') {
            ++numAcesD;
        }
        System.out.println(cardName[dealerCard2]); //Dealer's first card is hidden, and second is displayed here
        dealerScore = dealerScore + cardVal[dealerCard1] + cardVal[dealerCard2];
        System.out.print("Your cards: ");
        r = randGen.nextInt(51);
        dealCard(r);
        playerCard1 = r;
        if (cardName[playerCard1].charAt(0) == 'A') {
            ++numAcesP;
        }
        r = randGen.nextInt(51);
        dealCard(r);
        playerCard2 = r;
        if (cardName[playerCard2].charAt(0) == 'A') {
            ++numAcesP;
        }
        System.out.println(cardName[playerCard1] + " " + cardName[playerCard2]); //Both the player's cards are shown
        playerScore = playerScore + cardVal[playerCard1] + cardVal[playerCard2];

        if (playerScore == 21) { //If the player is dealt 21, it will not ask if he/she wants more cards
            System.out.println("You have 21!");
        }
        else { //Otherwise, it will offer the opportunity to get another card
            System.out.println("Would you like another card? (yes or no)");
            hitMe = scnr.nextLine();
        }
        if (numAcesP == 2) { //If the player is dealt two aces, one will count as 11 and the other as 1, so as to be as high as possible without breaking 21
            playerScore = 12;
        }
        while ((hitMe.equals("yes"))&&(playerScore < 21)) { //This continues while the player wants another card and has not reached or surpassed 21
            r = randGen.nextInt(51);
            dealCard(r);
            playerNewCard = r;
            if (cardName[playerNewCard].charAt(0) == 'A') {
                ++numAcesP;
            }
            System.out.println(cardName[playerNewCard]);
            playerScore = playerScore + cardVal[playerNewCard];

            while ((numAcesP != 0) && (playerScore > 21)) { //This allows the player's score to be decreased by 10 for every ace in her hand if she goes over 21. That way, the aces are counting as 1 instead of 11.
                playerScore = playerScore - 10;
                numAcesP = numAcesP - 1;
            }

            if (playerScore < 21) { //Here, the player will only be prompted again for another card if the score is under 21
                System.out.println("Would you like another card? (yes or no)");
                hitMe = scnr.nextLine();
            }
        }
        if (playerScore > 21) { //Alerts the player if the score has gone over 21
            System.out.println("Bust! You have gone over 21.");
        }
        System.out.print("\n");
        System.out.println("Dealer's cards are: " + cardName[dealerCard1] + " " + cardName[dealerCard2]); //Shows the dealer's cards
        if (numAcesD == 2) { //Again, sets score at 12 if dealt two aces
            dealerScore = 12;
        }
        if ((playerScore < 22)&&(dealerScore < playerScore)) { //If player didn't bust and the dealer's score is lower than the player's score, dealer has opportunity to get more cards
            while (dealerScore < 17) { //Dealer must get more cards only if score is less than 17
                r = randGen.nextInt(51);
                dealCard(r);
                dealerNewCard = r;
                if (cardName[dealerNewCard].charAt(0) == 'A') {
                    ++numAcesD;
                }
                System.out.println("Dealer hits. Dealer's new card is: " + cardName[dealerNewCard]);
                dealerScore = dealerScore + cardVal[dealerNewCard];

                while ((numAcesD != 0)&&(dealerScore > 21)) { //Accounts for aces, lowering score if necessary same as for player
                    dealerScore = dealerScore - 10;
                    numAcesD = numAcesD - 1;
                }
            }
        }

        System.out.print("\n");

        if ((playerScore > 21)||(playerScore == dealerScore)||((dealerScore< 22)&&(dealerScore > playerScore))) { //If player busts, dealer wins; if they tie, dealer wins; or if dealer is closer to 21, dealer wins
            System.out.println("Dealer wins!");
            return false; //returns false if dealer wins
        }
        else if ((dealerScore > 21)||((playerScore < 22)&&(playerScore > dealerScore))){ //If dealer busts, player must not have, so player wins; if neither bust and player is closer to 21, player wins.
            System.out.println("Dealer busts! Dealer has gone over 21.");
            System.out.print("\n");
            System.out.println("You win!");
            return true; //returns true if player wins
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int playerWins = 0;
        int dealerWins = 0;
        String keepPlaying = new String();

        boolean keepGoing = true;
        while (keepGoing) { //This loops as long as the player wants to continue playing rounds
            boolean playerWon = playGame(); //calls playGame method
            if (playerWon) {
                ++playerWins;
            }
            else {
                ++dealerWins;
            }
            System.out.println("Your points: " + playerWins); //Displays how many rounds won by dealer and player
            System.out.println("Dealer's points: " + dealerWins);
            System.out.print("\n");
            System.out.println("Do you want to keep playing? (yes or no)");
            keepPlaying = scnr.nextLine();
            if (keepPlaying.equals("no")) {
                keepGoing = false;
            }
            System.out.print("\n");
        }
        announceOverallWinner(dealerWins, playerWins); //calls announceOverallWinner method
    }
}
