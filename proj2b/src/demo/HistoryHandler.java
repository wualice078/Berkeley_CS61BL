package demo;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {

    NGramMap map;
    public HistoryHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        List<TimeSeries> counts = new ArrayList<>();
        int startYear = q.startYear();
        int endYear = q.endYear();

        for (String word : words) {
            counts.add(map.weightHistory(word, startYear, endYear));
        }

        XYChart chart = Plotter.generateTimeSeriesChart(words, counts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
