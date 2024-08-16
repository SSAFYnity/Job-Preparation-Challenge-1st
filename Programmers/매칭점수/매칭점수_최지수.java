import java.util.*;

class Solution {
    
    static Set<String> wordSet = new TreeSet<>();
    static Map<String, Integer> urlMap = new HashMap<>();
    static String ol = "<a href=\"";
    static String ml = "<meta property=\"og:url\" content=\"";
    
    class Web implements Comparable<Web>{
        int index;
        double score;
        public Web(int index, double score) {
            this.index = index;
            this.score = score;
        }
        public int compareTo(Web o) {
            if (this.score == o.score) return this.index - o.index;
            return o.score - this.score < 0 ? -1 : 1;
        }
        public String toString() {
            return index + " score: " + score;
        }
    }
    
    boolean isThisAlphabet(int t) {
        return (t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z');
    }
    
    void myUrl(String page, int idx) {
        for (int i = 0; i < page.length() - ml.length(); i++) {
            String now = page.substring(i, i + ml.length());
            if (ml.equals(now)) {
                for (int j = i + ml.length(); j < page.length()-1; j++) {
                    String end = page.substring(j, j+1);
                    if (end.equals("\"")) {
                        urlMap.put(page.substring(i+ml.length(), j), idx);
                        break;
                    }
                }
                break;
            }
        }
    }
    
    int countWord(String word, String page) {
        int count = 0;
        for (int i = 0; i < page.length() - word.length(); i++) {
            String now = page.substring(i, i+word.length()).toUpperCase();
            if (!word.toUpperCase().equals(now)) continue;
            if (!(i==0 || isThisAlphabet(page.charAt(i-1))) && !isThisAlphabet(page.charAt(i+word.length()))) count++;
        }
        return count;
    }
    
    List<String> countOuterLink(String page) {
        List<String> list = new ArrayList<>();
        int len = ol.length();
        for (int i = 0; i < page.length() - len; i++) {
            String now = page.substring(i, i+len);
            if (!now.equals(ol)) continue;
            for (int j = i+len; j < page.length(); j++) {
                if (!page.substring(j, j+1).equals("\"")) continue;
                String outerLink = page.substring(i+len, j);
                list.add(page.substring(i+len, j));
                i = j+1;
                break;
            }
        }
        return list;
    }
    
    public int solution(String word, String[] pages) {
        int answer = 0;
        
        int[][] arr = new int[pages.length][2];
        double[] darr = new double[pages.length];
        PriorityQueue<Web> que = new PriorityQueue<>();
        
        for (int i = 0; i < pages.length; i++) {
            arr[i][0] = countWord(word, pages[i]);
            myUrl(pages[i], i);
        }
        
        List<String>[] olList = new ArrayList[pages.length];
        for (int i = 0; i < pages.length; i++) {
            olList[i] = countOuterLink(pages[i]);
            arr[i][1] = olList[i].size();
        }
        
        for (int i = 0; i < pages.length; i++) {
            for (String s : olList[i]) {
                if (!urlMap.containsKey(s)) continue;
                darr[urlMap.get(s)] += ((double) arr[i][0]/arr[i][1]);
            }
        }
        
        for (int i = 0; i < pages.length; i++) {
            que.add(new Web(i, (double)arr[i][0] + darr[i]));
        }
        
        return que.poll().index;
    }
}