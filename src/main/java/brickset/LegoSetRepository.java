package brickset;

import repository.Repository;

import java.util.Comparator;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;


/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns a comma separated string with the pieces of LEGO sets with the specified theme.
     *
     * @param theme a LEGO set theme
     * @return a string of the number of pieces for each of LEGO sets with the theme specified
     */
    public String returnPiecesOfLegoSetsWithTheme(String theme) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals(theme))
                .mapToInt(LegoSet::getPieces)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    /**
     * Returns the name of the widest LEGO set.
     *
     * @return the string containing the name of the widest LEGO set
     */
    public String returnWidestLegoSet() {
        return getAll().stream()
                .filter(x -> x.getDimensions() != null && x.getDimensions().getWidth() != null)
                .max(Comparator.comparingDouble(x -> x.getDimensions().getWidth()))
                .get()
                .getName();
    }

    /**
     * Returns the LEGO set with the largerst number of pieces.
     *
     * @return the name of the LEGO set with the largest number of pieces
     */
    public String returnLegoSetWithMostPieces() {
        return getAll().stream()
                .max(Comparator.comparing(LegoSet::getPieces))
                .get()
                .getName();
    }

    /**
     * Prints the LEGO set names sorted alphabetically.
     *
     */
    public void printLegoSetAlphabetically() {
        getAll().stream()
                .map(LegoSet::getName)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Prints the LEGO set number, name and the respective URL of the brickset
     *
     */
    public void printLegoSetNameAndBrickset() {
        getAll().stream()
                .distinct()
                .map(x -> "("+ x.getNumber() + ") " + x.getName() + ": " + x.getBricksetURL())
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.returnPiecesOfLegoSetsWithTheme("Duplo"));
        System.out.println(repository.returnLegoSetWithMostPieces());
        System.out.println(repository.returnWidestLegoSet());
        repository.printLegoSetAlphabetically();
        repository.printLegoSetNameAndBrickset();
    }

}
