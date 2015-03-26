package com.mokalab.butler.util;

import java.util.Collection;

/**
 * TODO: JAVA DOC
 */
public class CollectionUtils {

    private CollectionUtils() {}

    /**
     * Returns true if the collection is not null and it's size is not 0.
     */
    public static boolean isEmpty(Collection collection) {

        return (collection == null || collection.size() == 0);
    }

    /**
     * Returns true if the array is not null and it's size is not 0.
     */
    public static <T> boolean isEmpty(T[] array) {

        return (array == null || array.length == 0);
    }
}
