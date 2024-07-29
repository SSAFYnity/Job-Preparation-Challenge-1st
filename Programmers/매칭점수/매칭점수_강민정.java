import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Solution {
    class  WebPage {
        String url;
        String[] outsideUrls;       // 외부 링크 모음
        double point, linkPoint;
        int idx;
        
        public WebPage(int idx, String url) {
            this.idx = idx;
            this.url = url;
        }
        
        /*
            외부 링크 추가 메서드
        */
        public void setOutsideUrls(List<String> urls) {
            this.outsideUrls = new String[urls.size()];
            for(int i=0; i<urls.size(); i++) {
                outsideUrls[i] = urls.get(i);
            }
        }
        
        public void setLinkPoint() {
            this.linkPoint = this.point / outsideUrls.length;
        }
    }
    
    public int solution(String word, String[] pages) {
        int answer = 0;
        Map<String, WebPage> webPageMap = new HashMap();
        Pattern insideUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Pattern outsideUrlPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
        Pattern wordPattern=Pattern.compile("\\b(?i)"+word+"\\b");
        Matcher insideUrlMatcher, outsideUrlMatcher, wordMatcher;
        String insideUrl = "";       // 현재 웹 페이지 링크
        WebPage webPage;
        List<String> outsideUrls;       // 외부 사이트 링크를 담는 리스트
        double maxPoint = 0;
        for(int i=0; i<pages.length; i++) {
            outsideUrlMatcher = outsideUrlPattern.matcher(pages[i]);  // 외부 사이트 링크
            insideUrlMatcher = insideUrlPattern.matcher(pages[i]);    // 현재 웹 페이지 링크
            outsideUrls = new ArrayList();
            if(insideUrlMatcher.find()) {
                insideUrl = insideUrlMatcher.group().split("=")[2].replaceAll("\"", "");
            }
            webPage = new WebPage(i, insideUrl);
            while(outsideUrlMatcher.find()) {       // 외부 사이트 링크가 있을 동안에
                outsideUrls.add(outsideUrlMatcher.group().split("\"")[1]);
            }
            webPage.setOutsideUrls(outsideUrls);
            String body = pages[i].split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
            wordMatcher = wordPattern.matcher(body);
            int wordCnt = 0;
            while(wordMatcher.find()) {     // 검색할 word가 있을 동안에
                wordCnt++;
            }
            webPage.point = wordCnt;
            webPage.setLinkPoint();
            webPageMap.put(webPage.url, webPage);
        }
        
        for(WebPage wp : webPageMap.values()) {    // 웹 페이지 해시맵에 있는 값들을 모두 꺼내기
            for(String outsideUrl : wp.outsideUrls) {
                if(webPageMap.containsKey(outsideUrl)) {
                    webPageMap.get(outsideUrl).point += wp.linkPoint;
                }
            }
        }        
        for(WebPage wp : webPageMap.values()) {
            if(wp.point == maxPoint) {
                if(wp.idx < answer) {       // 인덱스가 더 낮은 값으로 갱신
                    answer = wp.idx;
                }
            } else if(wp.point > maxPoint) {
                answer = wp.idx;
                maxPoint = wp.point;
            }
        }
        return answer;
    }
}