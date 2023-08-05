import java.util.ArrayList;
import java.util.List;

class Newspaper {
    String name;
    double price;

    public Newspaper(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class NewspaperSubscriptions {
    public static void main(String[] args) {
        List<Newspaper> newspapers = new ArrayList<>();
        newspapers.add(new Newspaper("TOI", 3));
        newspapers.add(new Newspaper("Hindu", 2.5));
        newspapers.add(new Newspaper("ET", 4));
        newspapers.add(new Newspaper("BM", 1.5));
        newspapers.add(new Newspaper("HT", 2));

        double budget = 40;
        List<List<String>> possibleCombinations = findPossibleCombinations(newspapers, budget);


        for (List<String> combination : possibleCombinations) {
            System.out.println(combination);
        }
    }

    private static List<List<String>> findPossibleCombinations(List<Newspaper> newspapers, double budget) {
        List<List<String>> possibleCombinations = new ArrayList<>();
        List<String> currentCombination = new ArrayList<>();

        calculateCombinations(newspapers, budget, possibleCombinations, currentCombination, 0);
        return possibleCombinations;
    }

    private static void calculateCombinations(List<Newspaper> newspapers, double remainingBudget,
                                              List<List<String>> possibleCombinations, List<String> currentCombination,
                                              int index) {
        if (remainingBudget < 0) {
            return;
        }

        if (remainingBudget == 0) {
            possibleCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = index; i < newspapers.size(); i++) {
            Newspaper currentNewspaper = newspapers.get(i);
            if (currentNewspaper.price <= remainingBudget) {
                currentCombination.add(currentNewspaper.name);
                calculateCombinations(newspapers, remainingBudget - currentNewspaper.price, possibleCombinations,
                        currentCombination, i + 1);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }
}