import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;

public class 브라이언의고민_강민정 {
    private static final String INVALID = "invalid";

    public String solution(String sentence) {
        StringBuilder answerList = new StringBuilder();
        Map<Character, List<Integer>> lowerCount = new LinkedHashMap();     //HashMap과 달리 입력 순서 보장
        int stringIdx = 0;      // 다음 단어의 시작 위치
        int startChar, endChar;     // 현재 단어에서 소문자가 나타난 첫 번째 위치와 마지막 위치
        int lastStartChar = -1, lastEndChar = -1;   // 이전 단어의 소문자가 나타난 첫 번째 위치와 마지막 위치
        int startWord = 0, endWord = 0;     // 현재 단어의 시작 위치와 마지막 위치
        int lastStartWord= -1, lastEndWord = -1;    // 이전 단어의 시작 위치와 마지막 위치
        int count, distance;        // 소문자의 빈도 수, 첫 소문자와 마지막 소문자 사이의 거리
        int rule = 0;       // 규칙(1 또는 2)
        List<Integer> temp;     // 어떤 문자의 위치 리스트

        int size = sentence.length();

        // 소문자의 각 종류별 위치를 LinkedHashMap에 순서대로 담기
        for(int i=0; i<size; i++) {
            char c = sentence.charAt(i);        // i번째 문자

            if(Character.isLowerCase(c)) {       // 소문자라면
                lowerCount.computeIfAbsent(c, k -> new ArrayList<>()).add(i);       // 소문자가 등장하는 위치 추가    
            }
        }

        try{
            for(char c : lowerCount.keySet()) {
                temp = lowerCount.get(c);   // 소문자의 위치 리스트
                count = temp.size();        // 소문자의 빈도 수
                startChar = temp.get(0);    // 첫 번째 소문자 위치
                endChar = temp.get(count-1);    // 마지막 소문자 위치

                //AaA, AaAaAaA...
                if(count == 1 || count >= 3) {
                    for(int i=1; i<count; i++) {
                        //간격 2 넘어가면 x
                        if(temp.get(i) - temp.get(i-1) != 2) {      // 소문자들 간의 거리가 2가 아니면
                            return INVALID;
                        }
                    }
                    rule = 1;
                }

                else if (count == 2) {
                    distance = endChar - startChar;     // 첫 소문자와 마지막 소문자 사이의 거리

                    // 다른 기호 안에 있음 (규칙 2와 겹침) 
                    if(distance == 2 && (endChar < lastEndChar && startChar > lastStartChar)) {
                        rule = 1;
                    } else if(distance >= 2) {     // 거리가 2 이상이면 규칙 2
                        rule = 2;
                    } else {
                        return INVALID;     // 소문자 연속은 x
                    }
                }

                // 규칙에 따른 예외
                if(rule == 1) {
                    //기호 위치에서 앞뒤로 한칸씩
                    startWord = startChar - 1;
                    endWord = endChar + 1;

                    //이전 단어 안에 포함되어 있는 경우
                    if(lastStartWord < startWord && lastEndWord > endWord) {
                        if(startChar - lastStartChar == 2 && lastEndChar - endChar == 2) {  // 규칙 2 아니면 안됨  
                            continue;
                        }
                        else {
                            return INVALID;
                        }
                    }
                } else if (rule == 2) {
                    startWord = startChar;      // 단어의 시작 위치를 소문자 시작 위치로 함
                    endWord = endChar;          // 단어의 시작 위치를 소문자 종료 위치로 함
                    if(lastStartWord < startWord && lastEndWord > endWord) {        //규칙 2는 중복되면 안됨
                        return INVALID;
                    }
                }

                if(lastEndWord >= startWord) {      // 이전 단어와 현재 단어가 겹침
                    return  INVALID;
                }

                //소문자 등장 이전에 존재하던 앞의 단어 추가
                if(stringIdx < startWord) {
                    answerList.append(makeWord(sentence, stringIdx, startWord - 1));
                    answerList.append(" ");
                }

                answerList.append(makeWord(sentence, startWord, endWord));      // 현재 단어 추가
                answerList.append(" ");
                lastStartWord = startWord;      // 이전 단어 시작
                lastEndWord = endWord;          // 이전 단어 끝
                lastStartChar = startChar;      // 이전 단어의 소문자 시작 위치
                lastEndChar = endChar;          // 이전 단어의 소문자 마지막 위치
                stringIdx = endWord + 1;        // 그 다음 단어 시작 위치
            }
        } catch(Exception e) {
            return INVALID;
        }

        answerList.append(makeWord(sentence, stringIdx, size - 1));     // 뒤에 남는 단어들도 더하기

        return answerList.toString().trim();        // 앞뒤 공백 제거한 문자열을 반환
    }

    /*
        sentence 문자열에서 start ~ end만큼 자른 후 소문자를 제거한 문자열을 반환
    */
    public String makeWord(String sentence, int start, int end) {
        String word = sentence.substring(start, end + 1);       // end를 포함하려면 +1을 해줘야 함
        return word.replaceAll("[a-z]", "");        // 소문자 제거
    }
}