package dev.ujjwal.ProductService.utility;

import java.util.regex.Pattern;

public class PatternMatcher {
    private static final String UUID_REGEX =
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private static final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX);

    public static boolean isValidUUID(String uuid) {
        return UUID_PATTERN.matcher(uuid).matches();
    }
}
