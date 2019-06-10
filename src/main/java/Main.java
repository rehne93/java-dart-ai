import de.baernreuther.dartai.bot.DartBot;
import de.baernreuther.dartai.bot.DartBotFactory;

public class Main {


    public static void main(String[] args) {
        DartBot one = DartBotFactory.createSimple501DartBot(65);
        DartBot two = DartBotFactory.createSimple501DartBot(67);
        int bestOf = 19;
        int p1Wins = 0, p2Wins = 0;
        // Running a best of X
        for (int i = 1; i <= bestOf; i++) {
            System.out.println("Match " + i);
            System.out.println("Player 1 \t\t Player2");
            System.out.println(one.getScoreLeft() + "\t\t" + two.getScoreLeft());
            while (!one.hasFinished() && !two.hasFinished()) {
                if (i % 2 != 0) {
                    one.playOneRound();
                    if (one.hasFinished()) {
                        System.out.println("Player 1 won");
                        p1Wins++;
                        break;
                    }
                    two.playOneRound();
                    if (two.hasFinished()) {
                        System.out.println("Player 2 won");
                        p2Wins++;
                        break;
                    }
                } else {
                    two.playOneRound();
                    if (two.hasFinished()) {
                        System.out.println("Player 2 won");
                        p2Wins++;
                        break;
                    }
                    one.playOneRound();
                    if (one.hasFinished()) {
                        System.out.println("Player 1 won");
                        p1Wins++;
                        break;
                    }
                }
                System.out.println(one.getScoreLeft() + "\t\t" + two.getScoreLeft());
            }
            one.reset();
            two.reset();
            System.out.println("--------------------------");
            if (p1Wins > bestOf / 2 || p2Wins > bestOf / 2) break;
        }
        System.out.println("\tResult\t");
        System.out.println("Player 1 \t\t Player2");
        System.out.println(p1Wins + "\t\t\t\t " + p2Wins);

    }
}
