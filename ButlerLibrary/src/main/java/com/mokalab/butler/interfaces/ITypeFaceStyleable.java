package com.mokalab.butler.interfaces;

/**
 * Implement this so {@link com.mokalab.butler.util.TypefaceHelper} can figure out which Styleable to use when the Type Face
 * Name is needed.
 *
 * <br><br>
 * Author: Pirdad S on 2014-08-05.
 */
public interface ITypeFaceStyleable {

    /**
     * Return your custom Styleable Attributes resource that contains the Type Face Name Styleable key.
     * <br><br>
     * Ex. R.styleable.TypefaceViewAttrs
     */
    public int[] getTypeFaceStyleableAttrRes();

    /**
     * Return your custom Styleable resource key that represents Type Face Name.
     * <br><br>
     * Ex. R.styleable.TypefaceViewAttrs_typeFaceName
     */
    public int getTypeFaceNameStyleable();
}
