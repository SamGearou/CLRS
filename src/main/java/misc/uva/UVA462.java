package misc.uva;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//UVA 462 - Bridge Hand Evaluator
public class UVA462 {
    private HashMap<Character, Integer> points;
    private HashSet<Character> spades;
    HashSet<Character> hearts;
    private HashSet<Character> diamonds;
    private HashSet<Character> clubs;
    private int numSpades;
    private int numHearts;
    private int numDiamonds;
    private int numClubs;
    private int mostFreqSuit;

    public UVA462() {
        points = new HashMap<>();
        points.put('A', 4);
        points.put('K', 3);
        points.put('Q', 2);
        points.put('J', 1);
        spades = new HashSet<>();
        hearts = new HashSet<>();
        diamonds = new HashSet<>();
        clubs = new HashSet<>();
        numSpades = 0;
        numHearts = 0;
        numDiamonds = 0;
        numClubs = 0;
        mostFreqSuit = 0;
    }

    public void readInput() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String[] tokens = scan.nextLine().split(" ");
            for (String token : tokens) {
                char rank = token.charAt(0);
                char suit = token.charAt(1);
                processCard(rank, suit);
            }
            processBid();
            reset();
        }
    }

    public void processCard(char rank, char suit) {
        if (suit == 'S') {
            numSpades++;
            if (isHigherThanTen(rank)) {
                spades.add(rank);
            }
        } else if (suit == 'H') {
            numHearts++;
            if (isHigherThanTen(rank)) {
                hearts.add(rank);
            }

        } else if (suit == 'D') {
            numDiamonds++;
            if (isHigherThanTen(rank)) {
                diamonds.add(rank);
            }
        } else {
            numClubs++;
            if (isHigherThanTen(rank)) {
                clubs.add(rank);
            }
        }
    }

    public void processBid() {
        int numPoints = 0;
        int numPointsSkippingRules;
        for (char c : spades) {
            numPoints += points.get(c);
        }
        for (char c : hearts) {
            numPoints += points.get(c);
        }
        for (char c : diamonds) {
            numPoints += points.get(c);
        }
        for (char c : clubs) {
            numPoints += points.get(c);
        }
        numPoints -= hasKingWithNoOtherCards();
        numPoints -= hasQueenWithZeroOrOneOtherCard();
        numPoints -= hasJackWithZeroOrOneOrTwoOtherCards();
        numPointsSkippingRules = numPoints;
        numPoints += twoCardsInSuit(); //rule 5
        numPoints += oneCardInSuit(); //rule 6
        numPoints += noCardsInSuit(); //rule 7
        if (numPoints < 14) {
            System.out.println("PASS");
        } else if (numPointsSkippingRules >= 16 && isStopped(spades, numSpades)
                && isStopped(hearts, numHearts) && isStopped(diamonds, numDiamonds) && isStopped(clubs, numClubs)) {
            System.out.println("BID NO-TRUMP");
        } else {
            mostFreqSuit = Math.max(mostFreqSuit, numSpades);
            mostFreqSuit = Math.max(mostFreqSuit, numHearts);
            mostFreqSuit = Math.max(mostFreqSuit, numDiamonds);
            mostFreqSuit = Math.max(mostFreqSuit, numClubs);
            if (numSpades == mostFreqSuit) {
                System.out.println("BID S");
            } else if (numHearts == mostFreqSuit) {
                System.out.println("BID H");
            } else if (numDiamonds == mostFreqSuit) {
                System.out.println("BID D");
            } else {
                System.out.println("BID C");
            }
        }
    }

    public int hasKingWithNoOtherCards() {
        int subtract = 0;
        if (spades.contains('K') && numSpades == 1) {
            subtract++;
        }
        if (hearts.contains('K') && numHearts == 1) {
            subtract++;
        }
        if (diamonds.contains('K') && numDiamonds == 1) {
            subtract++;
        }
        if (clubs.contains('K') && numClubs == 1) {
            subtract++;
        }
        return subtract;
    }

    public int hasQueenWithZeroOrOneOtherCard() {
        int subtract = 0;
        if (spades.contains('Q') && (numSpades == 1 || numSpades == 2)) {
            subtract++;
        }
        if (hearts.contains('Q') && (numHearts == 1 || numHearts == 2)) {
            subtract++;
        }
        if (diamonds.contains('Q') && (numDiamonds == 1 || numDiamonds == 2)) {
            subtract++;
        }
        if (clubs.contains('Q') && (numClubs == 1 || numClubs == 2)) {
            subtract++;
        }
        return subtract;
    }

    public int hasJackWithZeroOrOneOrTwoOtherCards() {
        int subtract = 0;
        if (spades.contains('J') && (numSpades == 1 || numSpades == 2 || numSpades == 3)) {
            subtract++;
        }
        if (hearts.contains('J') && (numHearts == 1 || numHearts == 2 || numHearts == 3)) {
            subtract++;
        }
        if (diamonds.contains('J') && (numDiamonds == 1 || numDiamonds == 2 || numDiamonds == 3)) {
            subtract++;
        }
        if (clubs.contains('J') && (numClubs == 1 || numClubs == 2 || numClubs == 3)) {
            subtract++;
        }
        return subtract;
    }

    public int twoCardsInSuit() {
        int add = 0;
        if (numSpades == 2) {
            add++;
        }
        if (numHearts == 2) {
            add++;
        }
        if (numDiamonds == 2) {
            add++;
        }
        if (numClubs == 2) {
            add++;
        }
        return add;
    }

    public int oneCardInSuit() {
        int add = 0;
        if (numSpades == 1) {
            add += 2;
        }
        if (numHearts == 1) {
            add += 2;
        }
        if (numDiamonds == 1) {
            add += 2;
        }
        if (numClubs == 1) {
            add += 2;
        }
        return add;
    }

    public int noCardsInSuit() {
        int add = 0;
        if (numSpades == 0) {
            add += 2;
        }
        if (numHearts == 0) {
            add += 2;
        }
        if (numDiamonds == 0) {
            add += 2;
        }
        if (numClubs == 0) {
            add += 2;
        }
        return add;
    }

    public void reset() {
        spades = new HashSet<>();
        hearts = new HashSet<>();
        diamonds = new HashSet<>();
        clubs = new HashSet<>();
        numSpades = 0;
        numHearts = 0;
        numDiamonds = 0;
        numClubs = 0;
        mostFreqSuit = 0;
    }

    public boolean isStopped(Set<Character> cards, int numCards) {
        return cards.contains('A')
                || cards.contains('K') && numCards >= 2
                || cards.contains('Q') && numCards >= 3;
    }

    public boolean isHigherThanTen(char c) {
        return c == 'A' || c == 'K' || c == 'Q' || c == 'J';
    }

    public static void main(String[] args) {
        UVA462 uva462 = new UVA462();
        uva462.readInput();
    }
}
