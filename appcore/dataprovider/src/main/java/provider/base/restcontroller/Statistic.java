package provider.base.restcontroller;

import java.util.Iterator;

public final class Statistic {



    /**
     * A method to calculate the mean and standard deviation of a series
     * of double. The objects are provided by an {@link Iterator} and the
     * double value is extracted using the passed extractor.
     * It skips <code>NaN</code> values.<br>
     *
     * Returns a <code>double[3]</code> where <br>
     * [0] is the number of values used in the mean<br>
     * [1] contains the mean value of the series<br>
     * [2] contains the standard deviation (sigma) of the complete
     * population<br>
     * Returns <code>NaN</code> for the mean and std dev if no valid value
     * could be found in the series.<br>
     *
     * Algorithm taken from "Computing the standard deviation efficiently"
     * by Mark Hoemmen
     * (http://www.cs.berkeley.edu/~mhoemmen/cs194/Tutorials/variance.pdf)
     *
     * @return the number of values, mean and std-dev of the serie
     *
     */
    public static double[] getMeanAndStdDev(Iterator it, DoubleExtractor e){
        while(it.hasNext()){// while initial value is NaN, try next
            double xk = e.doubleValue(it.next());
            if(Double.isNaN(xk)){
                continue;
            }
            int k = 1;
            double Mk = xk;
            double Qk = 0;
            while(it.hasNext()){
                xk = e.doubleValue(it.next());
                if(Double.isNaN(xk)){
                    continue;
                }
                k++;
                double d = xk-Mk; // is actually xk - Mk-1,
                // as Mk was not yet updated
                Qk += (k-1)*d*d/k;
                Mk += d/k;
            }
            return new double[]{k,Mk,Math.sqrt(Qk/k)};
        }
        return new double[]{0,Double.NaN,Double.NaN};
    }
}
