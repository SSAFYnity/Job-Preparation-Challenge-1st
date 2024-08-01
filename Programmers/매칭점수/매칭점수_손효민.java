import java.util.*;
import java.util.regex.*;

class Solution {

    static class webInfo {

        int basicScore; // 기본 점수
        int linkCount; // 외부 링크 수
        double linkScore; //링크 점수
        double matchingScore; //매칭 점수
        List<String> linkList; //외부 링크 리스트

        webInfo(int basic, int linkCount, List<String> linkList) {
            this.basicScore = basic;
            this.linkCount = linkCount;
            this.linkList = linkList;
        }

        public void setMatchScore() {
            this.matchingScore = this.basicScore + this.linkScore;
        }
    }

    static String url_Pattern = "<a href=\"\\S*\">";
    static Pattern urlPattern = Pattern.compile(url_Pattern);

    public int solution(String word, String[] pages) {
        int answer = 0;

        webInfo[] webs = new webInfo[pages.length];
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < pages.length; i++) {

            String[] tag = pages[i].split("\n");
            int index = 0;
            String linkPattern = "<meta property=\"og:url\" content=\"https://\\S*\"/>"; // 해당 페이지의 주소의 content의 url을 찾는 정규식
            while (index < tag.length && !Pattern.matches(linkPattern, tag[index].trim())) {
                index++;
            }

            String link = findPage(tag[index]);
            map.put(link, i); // hashmap에 넣어서 index를 저장 - 링크 점수

            while (index < tag.length && !tag[index].equals("<body>")) {
                index++;
            }

            index++;
            List<String> linkList = new ArrayList<String>();
            int count = 0;
            while (index < tag.length && !tag[index].equals("</body>")) {

                Matcher urlMatcher = urlPattern.matcher(tag[index]);
                //외부 링크 개수 파악 및 링크 추가
                while (urlMatcher.find()) {
                    linkList.add(urlMatcher.group().split("\"")[1]);
                }

                //단어별로 잘라서 단어 일치하는지 확인 -> 기본점수 -> MuziMuzi는 Muzi랑 일치하지 않음
                String[] tags = tag[index].toLowerCase().split("[^a-z]");
                for (int j = 0; j < tags.length; j++) {
                    if (tags[j].equals(word.toLowerCase())) {
                        count++;
                    }
                }

                index++;
            }

            webs[i] = new webInfo(count, linkList.size(), linkList);
        }

       
        for (int i = 0; i < pages.length; i++) {
            double score = (double) webs[i].basicScore / (double) webs[i].linkCount;  // 링크점수 = 기본점수/외부링크수

            for (String link : webs[i].linkList) {
                if (map.containsKey(link)) {
                    int index = map.get(link);
                    webs[index].linkScore += score; //링크 점수 더해줌
                }
            }
        }

        double max = 0; //매칭 점수중에 최대값
        for (int i = 0; i < pages.length; i++) {
            webs[i].setMatchScore();
            if (max < webs[i].matchingScore) {
                max = webs[i].matchingScore;
                answer = i;
            }
        }

        return answer;
    }

    static String findPage(String tag) {
    // 페이지의 주소가 있는 태그를 찾는 함수 -> hashmap에 index저장
        String result = "";
        String[] content = tag.trim().split("\\s");

        result = content[2].substring(9, content[2].length() - 3);

        return result;
    }
}
