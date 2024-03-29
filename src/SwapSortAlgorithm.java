/*
 * @(#)SwapSortAlgorithm.java	1.0 95/06/26 Jason Harrison
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
 * A swap sort demonstration algorithm
 *
 *  We know how the data was constructed and can use this to our advantage.
 *  THIS IS NOT A SORT!  DO NOT USE THIS ROUTINE FOR REAL APPLICATIONS.
 *  IT IS ONLY A DEMONSTRATION OF HOW LONG IT TAKES JAVA TO SWAP N ELEMENTS.
 *  
 *  EQUIVALENT CODE:
 *     for (int i = 0; i < a.length; i++ ) {
 *         a[i] = i;
 *     }
 *
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author Jason Harrison@cs.ubc.ca
 * @version 	1.0, 26 Jun 1995
 *
 */
class SwapSortAlgorithm extends SortAlgorithm {
    void sort(int a[]) throws Exception {
	int max = a[0];

        /* 
         *  Originally the SortItem class created arrays without duplicates
         *  and thus every item had a single correct location in the final
         *  sorted array.  With duplicates there are many correct final
         *  locations and we cannot just swap an item into the final spot.
         *
         *  So fill the array the old way, shuffle it and then continue with
         *  the old algorithm.  (This the old is SortItem::scramble())
         *  Even then this doesn't always work due to floating point 
         *  limitations.
         */

        /*
         *  The array a holds values from 0 to max where a.length = max / f.
         *  Here we find max.
         */
	for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
	    }
	}

        /*
         *  Now find f, the scaling factor for drawing the contents of
         *  the array a.  The correct value for a[j] is j / f.
         */
	double f = ((double) a.length - 1.0) / (double) max;

        /* 
         *  Fill the array with random numbers from 0..a.length-1
         */
        
        for (int i = a.length; --i >= 0;) {
            a[i] = (int)(i / f);
        }

        /* 
         *  Shuffle the array
         */
	for (int i = a.length; --i >= 0;) {
	    int j = (int)(i * Math.random());
	    int t = a[i];
	    a[i] = a[j];
	    a[j] = t;
	}

        pause();

        /*
         *  Now sort the array.
         *  Each time through the loop we remove a value, find it's correct
         *  position in the array, and place it there.  The displaced 
         *  value becomes the next value to place.
         */
	int T = a[0];
        int S = a[1];
	for (int i = 0; i < a.length; i++) {
	    if (stopRequested) {
		return;
            }
	    S = a[(int) (T * f)];
            /* 
             *  If the item we're trying to move is supposed to where the
             *  next item goes we'll get stuck in a fixed point.
             *  Pick a new starting point.
             */
            if( T == S) {
                T = a[(int) (Math.random() * a.length)];
                S = a[(int) (T * f)];
            }
	    a[(int) (T * f)] = T;
	    T = S;
	    pause((int) (S * f), (int) (T * f));

        }
    }
}

