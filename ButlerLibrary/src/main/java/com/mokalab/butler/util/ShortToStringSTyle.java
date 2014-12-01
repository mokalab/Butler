package com.mokalab.butler.util;

import org.apache.commons.lang3.builder.StandardToStringStyle;

/**
 * ToStringStyle.DEFAULT_STYLE with the change that it does not output null values.
 *
 * Created by joshallen on 14-12-01
 */
public class ShortToStringStyle extends StandardToStringStyle {

    public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
        if (value == null) {
            return;
        }
        super.append(buffer, fieldName, value, fullDetail);
    }

    public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail) {
        if (array == null) {
            return;
        }
        super.append(buffer, fieldName, array, fullDetail);
    }
}
