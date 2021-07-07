import java.util.Scanner;

public class Dice {

    private static final int MIN_THROW = 1;
    private static final int MAX_THROW = 6;
    private static final int NUMBER_OF_TURNS = 5;
    private static final int MAX_NUMBER_OF_THROWS = 10;
    private static final int FIRST_THROW = 1;
    private static final int WINNING_ROLL_NUMBER_7 = 7;
    private static final int WINNING_ROLL_NUMBER_11 = 11;
    private static final int WINNING_ROUND_NUMBER_5 = 5;
    private static final int LOOSING_ROLL_NUMBER_2 = 2;
    private static final int LOOSING_ROLL_NUMBER_12 = 12;


    private void checkRollCorrect(int roll) throws IllegalArgumentException {
        if (roll < MIN_THROW || roll > MAX_THROW)
            throw new IllegalArgumentException("Niepoprawna wartość! Wprowadź liczbę z zakresu od 1 do 6 włącznie.");
    }


    private float round(Players players) {
        int rollNumber = 1;
        int firstDice, secondDice;
        float sumOfPointsOnDices = 0f, sumOfPlayerPoints = 0f;
        Scanner roll = new Scanner(System.in);
        while (rollNumber <= MAX_NUMBER_OF_THROWS) {
            System.out.println("\nZawodnik " + players.getName() +
                    " Podaj liczbę oczek pierwszej kości od 1 do 6:");

            firstDice = roll.nextInt();
            System.out.println("\nZawodnik " + players.getName() +
                    " Podaj liczbę oczek drugiej kości od 1 do 6:");

            secondDice = roll.nextInt();
            try {
                checkRollCorrect(firstDice);
            } catch (Exception e) {
                System.out.println("\nNiepoprawna liczba oczek !!! " + players.getName() +
                        " Podaj ponownie prawidłową liczke w zakresie 1 do 6: ");
                firstDice = roll.nextInt();
            }
            try {
                checkRollCorrect(secondDice);

            } catch (Exception e) {
                System.out.println("\nNiepoprawna liczba oczek !!! " + players.getName() +
                        " Podaj ponownie prawidłową liczke w zakresie 1 do 6:");
                secondDice = roll.nextInt();
            }

            sumOfPointsOnDices = firstDice + secondDice;
            System.out.println("Zawodnik " + players.getName() + " wykonał " + rollNumber + " rzutów: " + firstDice + " " + secondDice);
            System.out.println("Suma punktów w rzutach: " + sumOfPointsOnDices);

            if (((rollNumber == FIRST_THROW) && (sumOfPointsOnDices == WINNING_ROLL_NUMBER_7)) || (sumOfPointsOnDices == WINNING_ROLL_NUMBER_11)) {
                System.out.println(" Zawodnik " + players.getName() + " wygrał tę rundę!");
                break;
            } else if (((rollNumber == FIRST_THROW) && (sumOfPointsOnDices == LOOSING_ROLL_NUMBER_2)) || (sumOfPointsOnDices == LOOSING_ROLL_NUMBER_12)) {
                System.out.println("Zawodnik " + players.getName() + " przegrał tę rundę!");
                sumOfPlayerPoints += rollNumber;
                break;
            } else if (sumOfPointsOnDices == WINNING_ROUND_NUMBER_5) {
                System.out.println("Zawodnik " + players.getName() + " wygrał tę rundę");
                break;
            } else {
                sumOfPlayerPoints += sumOfPointsOnDices / rollNumber;
            }
            rollNumber++;
        }
        return sumOfPlayerPoints;
    }


    public void startGame() {
        Players player1 = new Players("Pierwszy -->");
        Players player2 = new Players("Drugi -->");
//        String currentPlayer = player1.getName();
        float sumOfPlayer1Points = 0f, sumOfPlayer2Points = 0f;
        int numberOfRounds = 1;
        while (numberOfRounds <= NUMBER_OF_TURNS) {
            System.out.println("\nRunda: " + numberOfRounds);
            sumOfPlayer1Points += round(player1);
            sumOfPlayer2Points += round(player2);
            System.out.println("\nPodsumowanie: ");
            System.out.println("Zawodnik " + player1.getName() + " zdobył : " + sumOfPlayer1Points + " punktów.");
            System.out.println("Zawodnik " + player2.getName() + " zdobył : " + sumOfPlayer2Points + " punktów.");
            numberOfRounds++;
        }
        System.out.println("\nWyniki Końcowe: ");
        System.out.println(" Suma punktów pierwszego gracza: " + sumOfPlayer1Points + ".");
        System.out.println(" Suma punktów drugiego gracza: " + sumOfPlayer2Points + ".");

        if (sumOfPlayer1Points < sumOfPlayer2Points)
            System.out.println("Zawodnik pierwszy wygrał !");
        else if (sumOfPlayer1Points == sumOfPlayer2Points)
            System.out.println(" Remis !");
        else
            System.out.println(" Zawodnik drugi wygrał !");
    }
}