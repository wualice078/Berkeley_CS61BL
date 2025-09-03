package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        if (ts != null) {
            this.putAll(ts.subMap(startYear, true, endYear, true));
        }
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
        return this.keySet().stream().toList();
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        List<Double> d = new LinkedList<>();
        this.years().forEach(year -> d.add(this.get(year)));
        return d;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        if (ts.isEmpty() && this.isEmpty()) {
            return new TimeSeries();
        } else if (ts.isEmpty()) {
            return this;
        } else if (this.isEmpty()) {
            return ts;
        } else {
            TimeSeries sum = new TimeSeries();
            int start = Math.min(this.firstKey(), ts.firstKey());
            int end = Math.max(this.lastKey(), ts.lastKey());

            for (int i = start; i <= end; i++) {
                if (ts.containsKey(i) || this.containsKey(i)) {
                    sum.put(i, 0.0);
                    if (ts.containsKey(i)) {
                        sum.replace(i, ts.get(i));
                    }
                    if (this.containsKey(i)) {
                        sum.replace(i, sum.get(i) + this.get(i));
                    }
                }
            }

            return sum;
        }
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        if (ts.isEmpty() || this.isEmpty()) {
            return new TimeSeries();
        } else {
            TimeSeries div = new TimeSeries();

            this.forEach((year, count) -> {
                Double divisor = ts.get(year);
                if (divisor == null) {
                    throw new IllegalArgumentException();
                } else {
                    div.put(year, count / divisor);
                }
            });

            return div;
        }
    }
}
