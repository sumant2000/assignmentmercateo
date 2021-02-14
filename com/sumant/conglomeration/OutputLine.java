package com.sumant.conglomeration;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * To get  desired output text line.
 */

public class OutputLine {
    private final Optional<PackageOfItems> optionalPackage;

    public OutputLine(Optional<PackageOfItems> optionalPackage) {
        this.optionalPackage = optionalPackage;
    }

    @Override
    public String toString() {
        if (optionalPackage.isPresent()) {
            Set<Item> products = optionalPackage.get().getProducts();
            if (products.isEmpty()) {
                return "-";
            } else {
                return products.stream().map(Item::getItemNumber).sorted().map(Object::toString).collect(Collectors.joining(","));
            }
        } else {
            return "-";
        }
    }
}
