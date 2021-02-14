package com.sumant.conglomeration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main objMain = new Main();
        // Path to text file arg[0]
        // Logic to read sample input file and store in collections...
        String pathToTextFile = args[0];
        String checkLine = "";
        File file = new File(pathToTextFile);
        Set<Item> itemsList = new HashSet<>();
        Scanner sc = null;
        int requiredWeightLimit = 0;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                checkLine = sc.nextLine();
                if (checkLine.contains(":")) {
                    String[] readLinesToProcess = (checkLine.split(":"));
                    requiredWeightLimit = (Integer.parseInt(readLinesToProcess[0].trim()));
                    String[] itemSpecifications = readLinesToProcess[1].split("[\\\\(\\\\)]");

                    for (int i = 0; i < itemSpecifications.length; i++) {
                        if (itemSpecifications[i].trim().length() > 0) {

                            Item item = new Item();
                            String[] priceEtc = itemSpecifications[i].split(",");
                            item.setItemNumber(Integer.parseInt(priceEtc[0]));
                            item.setWeight(Double.parseDouble(priceEtc[1]));
                            item.setCost(Double.parseDouble(priceEtc[2].replaceAll("€", "")));
                            itemsList.add(item);
                        }
                    }

                }
                if (itemsList.size() != 0) {
                    System.out.println(new OutputLine(objMain.findBestPackageBruteForce(requiredWeightLimit, itemsList)));
                    itemsList.clear();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    /**
     * Finds the best package by iterating through all possible packages and selecting the best one.
     */
    private Optional<PackageOfItems> findBestPackageBruteForce(double maxWeight, Set<Item> products) {
        // generate all subsets of the given set of products
        // as the max number of products is <= 15 the Guava Sets.powerSet algorithm can be used
        Set<Set<Item>> allPackages = powerSet(products);
        // find the best subset
        return allPackages.stream()
                .map(PackageOfItems::new)
                .filter(aPackage -> aPackage.getWeight() <= maxWeight)
                .max(PackageOfItems.BEST_PACKAGE_COMPARATOR);
    }

    /**
     *
     * @param set
     * @return power set, with all possible combinations.
     */
    public Set<Set<Item>> powerSet(Set<Item> set) {
        if (set.isEmpty()) {
            Set<Set<Item>> ret = new HashSet<>();
            ret.add(set);
            return ret;
        }

        Item element = set.iterator().next();
        Set<Item> subSetWithoutElement = getSubSetWithoutElement(set, element);
        Set<Set<Item>> powerSetSubSetWithoutElement = powerSet(subSetWithoutElement);

        Set<Set<Item>> powerSetSubSetWithElement = addElementToAll(powerSetSubSetWithoutElement, element);

        Set<Set<Item>> powerSet = new HashSet<>();
        powerSet.addAll(powerSetSubSetWithoutElement);
        powerSet.addAll(powerSetSubSetWithElement);
        return powerSet;
    }

    /**
     *
     * @param set Set to be considered for calculation of power set.
     * @param element element to be considered, which should not be in the returned set.
     * @return subset Without Element
     */
    private Set<Item> getSubSetWithoutElement(Set<Item> set, Item element) {
        Set<Item> subsetWithoutElement = new HashSet<>();
        for (Item s : set) {
            if (!s.equals(element))
                subsetWithoutElement.add(s);
        }
        return subsetWithoutElement;
    }

    /**
     * To add element to power set.
     * @param powerSetSubSetWithoutElement
     * @param element
     * @return
     */
    private Set<Set<Item>> addElementToAll(Set<Set<Item>> powerSetSubSetWithoutElement, Item element) {
        Set<Set<Item>> powerSetSubSetWithElement = new HashSet<>();
        for (Set<Item> subsetWithoutElement : powerSetSubSetWithoutElement) {
            Set<Item> subsetWithElement = new HashSet<>(subsetWithoutElement);
            subsetWithElement.add(element);
            powerSetSubSetWithElement.add(subsetWithElement);
        }
        return powerSetSubSetWithElement;
    }

}
