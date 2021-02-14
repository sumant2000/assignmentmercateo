package com.sumant.conglomeration;

import java.util.Comparator;
import java.util.Set;

public class PackageOfItems {
    // Best package comparator
    public static final Comparator<PackageOfItems> BEST_PACKAGE_COMPARATOR =
            // compare first by price
            Comparator.comparingDouble(PackageOfItems::getCost)
                    .thenComparing(
                            // and in case of equality compare by weight in reverse order
                            Comparator.comparingDouble(PackageOfItems::getWeight).reversed()
                    );
    private final Set<Item> products;

    public PackageOfItems(Set<Item> products) {
        this.products = products;
    }

    public Set<Item> getProducts() {
        return products;
    }

    public double getCost() {
        return products.stream()
                .mapToDouble(Item::getCost)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    public double getWeight() {
        return products.stream()
                .mapToDouble(Item::getWeight)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return "PackageOfItems{" +
                "products=" + products +
                '}';
    }
}
