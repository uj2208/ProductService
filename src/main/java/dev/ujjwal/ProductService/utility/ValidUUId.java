package dev.ujjwal.ProductService.utility;

import dev.ujjwal.ProductService.exception.InValidPatternException;

public class ValidUUId {
    public static void isValidUUID(String id) throws InValidPatternException {
        boolean match = PatternMatcher.isValidUUID(id);
        if (!match)
            throw new InValidPatternException("ID :"+id+" is not valid");
    }
}
