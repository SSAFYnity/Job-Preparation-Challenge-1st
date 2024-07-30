import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Solution {
    class  WebPage {
        String url;                 // 이 웹 페이지의 링크
        String[] outsideUrls;       // 외부 링크 모음
        double point, linkPoint;    // 해당 웹 페이지로 링크가 걸린 다른 웹 페이지의 기본 점수, 링크 점수
        int idx;                    // 이 웹 페이지의 인덱스
        
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
        
        /*
            링크 점수 계산
         */
        public void setLinkPoint() {
            this.linkPoint = this.point / outsideUrls.length;   // 해당 웹 페이지로 링크가 걸린 다른 웹 페이지의 기본 점수 / 외부 링크 수의 총합
        }
    }
    
    public int solution(String word, String[] pages) {
        int answer = 0;     // 매칭점수가 가장 높은 웹페이지의 index
        Map<String, WebPage> webPageMap = new HashMap();    //  현재 웹 페이지 링크 : 웹 페이지 객체 추가
        Pattern insideUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Pattern outsideUrlPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
        Pattern wordPattern=Pattern.compile("\\b(?i)"+word+"\\b");
        Matcher insideUrlMatcher, outsideUrlMatcher, wordMatcher;
        String insideUrl = "";       // 현재 웹 페이지 링크
        WebPage webPage;
        List<String> outsideUrls;       // 외부 사이트 링크를 담는 리스트
        double maxPoint = 0;            // 최대 매칭 점수
        
        for(int i=0; i<pages.length; i++) {
            outsideUrlMatcher = outsideUrlPattern.matcher(pages[i]);  // 외부 사이트 링크
            insideUrlMatcher = insideUrlPattern.matcher(pages[i]);    // 현재 웹 페이지 링크
            outsideUrls = new ArrayList();
            
            if(insideUrlMatcher.find()) {
                insideUrl = insideUrlMatcher.group().split("=")[2].replaceAll("\"", "");    // 현재 웹 사이트 url 추출
            }
            
            webPage = new WebPage(i, insideUrl);        // 인덱스, 현재 웹 사이트 url을 파라미터로 넣고 객체 생성
            
            while(outsideUrlMatcher.find()) {       // 외부 사이트 링크가 있을 동안에
                outsideUrls.add(outsideUrlMatcher.group().split("\"")[1]);      // 외부 웹 사이트 링크를 추출해서 추가
            }
            
            webPage.setOutsideUrls(outsideUrls);        // 외부 링크 추가
            String body = pages[i].split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " "); // 본문 추출
            wordMatcher = wordPattern.matcher(body);        // 본문에서 word가 몇 번 등장하는지 추출할거다.
            int wordCnt = 0;        // 찾으려는 word의 빈도 수
            
            while(wordMatcher.find()) {     // 검색할 word가 있을 동안에
                wordCnt++;      // word를 찾을 때마다 증가
            }
            
            webPage.point = wordCnt;        // 기본 점수 할당
            webPage.setLinkPoint();         // 링크 점수 할당
            webPageMap.put(webPage.url, webPage);       // 현재 웹 페이지 링크 : 웹 페이지 객체 추가
        }
        
        for(WebPage wp : webPageMap.values()) {    // 웹 페이지 해시맵에 있는 값들을 모두 꺼내기
            for(String outsideUrl : wp.outsideUrls) {       // 외부 링크
                if(webPageMap.containsKey(outsideUrl)) {    // 외부 링크 key가 있으면
                    webPageMap.get(outsideUrl).point += wp.linkPoint;       // 링크가 걸린 다른 웹페이지의 기본점수에 링크 점수를 더해준다. 
                }
            }
        }       

        for(WebPage wp : webPageMap.values()) {
            if(wp.point == maxPoint) {
                if(wp.idx < answer) {       // 인덱스가 더 낮은 값으로 갱신
                    answer = wp.idx;
                }
            } else if(wp.point > maxPoint) {        // 매칭 점수 갱신
                answer = wp.idx;
                maxPoint = wp.point;
            }
        }
        return answer;   // 매칭점수가 가장 높은 웹페이지의 index(그중 번호가 가장 작음)
    }
}