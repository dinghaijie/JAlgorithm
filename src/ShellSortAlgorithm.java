/*
 * @(#)ShellSortAlgorithm.java	1.1 2000/04/12 Jason Harrison
 *
 * Copyright (c) 1995 University of British Columbia
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * UBC MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UBC SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * A shell sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 * Note: Invented by Donald Lewis Shell [CACM, July, 1959, pages 30-32]
 * @author Jason Harrison@cs.ubc.ca
 * @version 	1.0, 23 Jun 1995
 * @version 	1.1, 12 Apr 2000 
 *              -- fixed java.lang.ArrayIndexOutOfBoundsException
 *                 Joel Berry <jmbshifty@yahoo.com> found this bug
 */

/* http://www.auto.tuwien.ac.at/~blieb/woop/shell.html 
 *
 * Shellsort is a simple extension of insertion sort which gains speed
 * by allowing exchanges of elements that are far apart. The idea is
 * to rearrange the array to give it the property that every hth
 * element (starting anywhere) yields a sorted array. Such an array
 * is said to be h-sorted.
 *
 * By h-sorting for some large values of h, we can move elements in
 * the array long distances and thus make it easier to h-sort for
 * smaller values of h. Using such a procedure for any sequence of
 * values h which ends in 1 will produce a sorted array.
 */

class ShellSortAlgorithm extends SortAlgorithm {
    void sort(int a[]) throws Exception {
	int h = 1;
        /* 
         * find the largest h value possible 
         */
        while ((h * 3 + 1) < a.length) {
	    h = 3 * h + 1;
	}

        /* 
         * while h remains larger than 0 
         */
        while( h > 0 ) {
            /* 
             * for each set of elements (there are h sets)
             */
            for (int i = h - 1; i < a.length; i++) {
                /* 
                 * pick the last element in the set
                 */
                int B = a[i];
                int j = i;
                /*
                 * compare the element at B to the one before it in the set
                 * if they are out of order continue this loop, moving
                 * elements "back" to make room for B to be inserted.
                 */
                for( j = i; (j >= h) && (a[j-h] > B); j -= h) {
                    if (stopRequested) {
		        return;
                    }
                    a[j] = a[j-h];
		    pause(i,j);
                }
                /*
                 *  insert B into the correct place
                 */
                a[j] = B;
	        pause(j);
            }
            /* 
             * all sets h-sorted, now decrease set size
             */
            h = h / 3;
        }
    }
}

