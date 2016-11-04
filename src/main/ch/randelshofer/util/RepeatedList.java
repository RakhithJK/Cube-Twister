/**
 * @(#)RepeatedList.java  2.0  2012-02-08
 *
 * Copyright (c) 2008-2012 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package ch.randelshofer.util;

import java.util.*;

/**
 * RepeatedList.
 *
 * @author Werner Randelshofer
 * @version 2.0 2012-02-08 Adds generics support.
 * <br>1.0 Jan 2, 2008 Created.
 */
public class RepeatedList<T> extends AbstractList<T> {
    private List<T> list;
    private int repeatCount;
    
    public RepeatedList(List<T> list, int repeatCount) {
        this.list = list;
        this.repeatCount = repeatCount;
    }

    @Override
    public int size() {
        return list.size() * repeatCount;
    }

    @Override
    public T get(int index) {
        return list.get(index % list.size());
    }
}
