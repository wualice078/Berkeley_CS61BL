package hyponyms;

import edu.princeton.cs.algs4.In;
import ngrams.NGramMap;

import java.util.*;

public class HyponymGraph {

    DirectedGraph wordnet = new DirectedGraph();

    public HyponymGraph(String synsetsFilename, String hyponymsFilename) {

        In in = new In(synsetsFilename);

        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            wordnet.addWord(Integer.parseInt(splitLine[0]), splitLine[1]);
        }

        In in2 = new In(hyponymsFilename);

        while (!in2.isEmpty()) {
            String nextLine = in2.readLine();
            String[] splitLine = nextLine.split(",");
            int id = Integer.parseInt(splitLine[0]);
            for (int i = 1; i < splitLine.length; i++) {
                wordnet.addHyponym(id, Integer.parseInt(splitLine[i]));
            }
        }

    }

    private Set<String> getHyponymSet(String word) {
        Set<String> hyponyms = new HashSet<>(wordnet.getHyponyms(word));
        return hyponyms;
    }

    private Set<String> getHyponymSet(List<String> words) {

        if (words == null || words.isEmpty()) {
            return new HashSet<>();
        }

        Set<String> hyponyms = new HashSet<>(getHyponymSet(words.get(0)));

        for (int i = 1; i < words.size(); i++) {
            hyponyms.retainAll(getHyponymSet(words.get(i)));
        }

        return hyponyms;
    }

    public List<String> getHyponymSet(List<String> words, int k, NGramMap ngm, int startYear, int endYear) {

        Set<String> hyponyms = getHyponymSet(words);
        List<String> hl;

        if (k == 0) {
            hl = new ArrayList<>(hyponyms);
        } else {
            Comparator<String> oc = new OccurrenceComparator(ngm, startYear, endYear);
            PriorityQueue<String> minHeap = new PriorityQueue<>(oc);

            for (String s : hyponyms) {
                minHeap.add(s);
            }

            hl = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                String word = minHeap.poll();
                if (word == null || ngm.wordOccurrences(word, startYear, endYear) == 0) {
                    break;
                }
                hl.add(word);
            }
        }

        Comparator<String> hc = new HyponymComparator();
        hl.sort(hc);
        return hl;
    }

    private class HyponymComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return (s1).compareTo(s2);
        }
    }

    private class OccurrenceComparator implements Comparator<String> {
        NGramMap ngm;
        int startYear;
        int endYear;

        public OccurrenceComparator(NGramMap ngm, int startYear, int endYear) {
            this.ngm = ngm;
            this.startYear = startYear;
            this.endYear = endYear;
        }

        @Override
        public int compare(String s1, String s2) {
            return Double.compare(ngm.wordOccurrences(s2, startYear, endYear),
                    ngm.wordOccurrences(s1, startYear, endYear));
        }
    }
}

