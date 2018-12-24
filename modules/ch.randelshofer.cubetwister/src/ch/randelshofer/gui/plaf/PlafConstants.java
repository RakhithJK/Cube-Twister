/* @(#)PlafConstants.java
 * Copyright (c) 2001 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.gui.plaf;

import javax.swing.*;

/**
 * PlafConstants.
 * @author  Werner Randelshofer
 * @version $Id$
 */
public interface PlafConstants {
    /**
     * Client property to specify bevel in ImageButtonUI,
     * ImageToggleButtonUI and ImageSliderUI.
     *
     * <p>Possible values for this property are:
     * EAST, WEST, NONE, ALL.
     */
    public final static String PROP_BEVEL = "ch.randelshofer.gui.plaf.Bevel";
    /**
     * The all property value.
     */
    public final static String ALL = "All";
    /**
     * The none property value.
     */
    public final static String NONE = "None";
    /**
     * The center property value.
     */
    public final static String CENTER = "Center";
    /**
     * The east property value.
     */
    public final static String EAST = "East";
    /**
     * The west property value.
     */
    public final static String WEST = "West";
}
