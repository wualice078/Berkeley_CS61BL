package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    HashMap<String, TimeSeries> data;
    TimeSeries years;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        data = new HashMap<>();
        In in = new In(wordsFilename);

        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            data.computeIfAbsent(splitLine[0],
                    temp -> new TimeSeries()).put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
        }

        years = new TimeSeries();
        In in2 = new In(countsFilename);

        while (!in2.isEmpty()) {
            String nextLine = in2.readLine();
            String[] splitLine = nextLine.split(",");
            years.put(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(data.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries hist = new TimeSeries();
        TimeSeries found = data.get(word);
        if (found != null) {
            hist.putAll(found);
        }
        return hist;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries hist = new TimeSeries();
        hist.putAll(years);
        return hist;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return new TimeSeries(data.get(word), startYear, endYear).dividedBy(years);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries found = data.get(word);
        if (found != null) {
            return found.dividedBy(years);
        }
        return new TimeSeries();
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries sum = new TimeSeries();
        for (String word : words) {
            sum = sum.plus(weightHistory(word, startYear, endYear));
        }
        return sum;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries sum = new TimeSeries();
        for (String word : words) {
            sum = sum.plus(weightHistory(word));
        }
        return sum;
    }

    /**
     * Returns number of occurrences of a word from startYear to endYear, inclusive.
     */
    public double wordOccurrences(String word, int startYear, int endYear) {
        double sum = 0;
        TimeSeries wordHist = countHistory(word, startYear, endYear);

        for (int i = startYear; i <= endYear; i++) {
            if (wordHist.containsKey(i)) {
                sum += wordHist.get(i);
            }
        }
        return sum;
    }
}
