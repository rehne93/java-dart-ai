import de.baernreuther.dartai.bot.SimpleDartBot;
import de.baernreuther.dartai.model.DartRound;

public class Main {


    public static void main(String[] args) {
        SimpleDartBot simpleDartBot = new SimpleDartBot(50.0);
        System.out.println(simpleDartBot.getScoreLeft());
        int round = 1;
        while(!simpleDartBot.hasFinished()){
            DartRound r = simpleDartBot.playOneRound();
            System.out.println("-" + r.getScore());
            System.out.println((simpleDartBot.getScoreLeft()));
            System.out.println("Round: " + round);
            round++;
        }


    }
}
