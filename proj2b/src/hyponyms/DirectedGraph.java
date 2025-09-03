package hyponyms;

import java.util.*;

public class DirectedGraph {

    private Map<Integer, Node> words = new HashMap<>();
    private Map<String, Set<Integer>> wordToID = new HashMap<>();

    private class Node {

        String[] synset;
        Set<Integer> hyponyms;

        public Node(String word) {
            synset = word.split(" ");
            hyponyms = new HashSet<>();
        }

    }

    public void addWord(int id, String word) {
        Node n = new Node(word);
        words.put(id, n);

        for (String w : n.synset) {
            if (!wordToID.containsKey(w)) {
                wordToID.put(w, new HashSet<>());
            }
            wordToID.get(w).add(id);
        }
    }

    public void addHyponym(int id, int hypid) {
        words.get(id).hyponyms.add(hypid);
    }

    public Set<String> getHyponyms(String word) {
        Set<String> hyponyms = new HashSet<>();
        Set<Integer> ids = getIDs(word);

        if (ids.size() != 0) {
            hyponyms.add(word);
        }

        for (int id : ids) {
            for (String synonyms : getWords(id)) {
                hyponyms.add(synonyms);
            }
            hyponyms.addAll(getHyponyms(id));
        }

        return hyponyms;
    }

    private Set<String> getHyponyms(int id) {
        Set<String> hyponyms = new HashSet<>();

        for (int i : words.get(id).hyponyms) {
            for (String synonyms : getWords(i)) {
                hyponyms.add(synonyms);
            }
            hyponyms.addAll(getHyponyms(i));
        }

        return hyponyms;
    }

    public String[] getWords(int id)  {
        return words.get(id).synset;
    }

    public Set<Integer> getIDs(String word) {
        Set<Integer> ids = wordToID.get(word);
        if (ids == null) {
            return new HashSet<>();
        }
        return ids;
    }
}
