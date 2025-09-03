package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import hyponyms.HyponymGraph;
import ngrams.NGramMap;

import java.util.List;

import static main.Main.*;

public class HyponymsHandler extends NgordnetQueryHandler {

    NGramMap map;
    HyponymGraph hg;

    public HyponymsHandler(NGramMap map, String synsetsFilename, String hyponymsFilename) {
        this.map = map;
        hg = new HyponymGraph(synsetsFilename, hyponymsFilename);
    }



    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        String response = hg.getHyponymSet(words, k, map, startYear, endYear).toString();
        return response;
    }
}
