package brickset;

import repository.Repository;

import java.util.Comparator;
//import java.util.Optional;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     * Returns the LEGO set with the largerst number of pieces.
     *
     */
    public void printLegoSetWithMostPieces() {
        getAll().stream().max(Comparator.comparing(LegoSet::getPieces)).get();
    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));
        repository.printLegoSetWithMostPieces();
    }

}
