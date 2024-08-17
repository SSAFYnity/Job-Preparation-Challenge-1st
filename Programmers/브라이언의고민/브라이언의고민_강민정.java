import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final String INVALID = "invalid";

    public static String solution(String sentence) {
        StringBuilder sb = new StringBuilder();     // 결과를 저장할 StringBuilder(가변, 싱글 스레드 환경에 적합)
        Map<Character, List<Integer>> lowerCount = new LinkedHashMap();     // 소문자의 위치를 저장할 LinkedHashMap
        int size = sentence.length();       // 주어진 문자열의 길이
        int lastEnd = -1;   // 최근에 등장한 소문자의 위치
        int start = 0;      // 단어의 시작 인덱스
        int rule = 0;       // 규칙
        int lastEndChar = -1;
        int lastStartChar = -1;
        int startWord = 0;      // 단어 시작
        int endWord = 0;        // 단어 끝
        int lastStartWord = -1;
        int lastEndWord = -1;
        int idx = 0;

        // 소문자의 위치를 파악
        for (int i = 0; i < size; i++) {
            char c = sentence.charAt(i);        // i번째 위치에 있는 문자

            if (Character.isLowerCase(c)) {     // 소문자라면
                lowerCount.computeIfAbsent(c, k -> new ArrayList<>()).add(i);       // 소문자가 등장하는 위치 추가
            }
        }

        try {
            // 각 소문자의 위치를 순회
            for (Map.Entry<Character, List<Integer>> entry : lowerCount.entrySet()) {
                List<Integer> positions = entry.getValue();     // 문자가 등장하는 위치 리스트
                int count = positions.size();       // 문자의 빈도 수

                int firstPos = positions.get(0);    // 문자가 처음 등장한 위치
                int lastPos = positions.get(count - 1);     // 문자가 마지막에 등장한 위치

                if (count == 1 || count >= 3) {     // 규칙 1: 글자 사이마다 소문자가 등장
                    for (int i = 1; i < count; i++) {
                        if (positions.get(i) - positions.get(i - 1) != 2) {     // 소문자가 등장하는 거리가 2가 아니라면
                            return INVALID;     // 규칙 1 위반
                        }
                    }
                    rule = 1;
                } else if (count == 2) {        // 규칙 2: 단어의 앞 뒤에 소문자가 등장
                    int distance = lastPos - firstPos;      // 소문자가 등장한 마지막 위치와 시작 위치의 차이
                    if (distance == 2 && (firstPos > lastStartChar && lastPos < lastEndChar)) {
                        rule = 1;
                    } else if (distance >= 2) {     // bAb라면 거리가 2니까 규칙 2가 되려면 최소 거리 2를 만족해야 함
                        rule = 2;
                    }
                } else {
                    return INVALID;
                }

                if (rule == 1) {     // 규칙 1일 때
                    startWord = firstPos - 1;   // 단어의 시작은 소문자 시작 위치 바로 앞
                    endWord = lastPos + 1;       // 단어의 끝은 소문자 마지막 위치 바로 뒤

                    if (lastStartWord < startWord && lastEndWord > endWord) {
                        if (firstPos - lastStartChar == 2 && lastEndChar - lastPos == 2) {
                            continue;
                        } else {        // 규칙 1 중복 적용
                            return INVALID;
                        }
                    }
                } else if (rule == 2) {
                    startWord = firstPos;
                    endWord = lastPos;

                    if (lastStartWord < startWord && lastEndWord > endWord) {
                        return INVALID;
                    }
                }

                if(lastEndWord >= startWord) {      // 이전 단어의 끝과 현재 단어의 시작이 겹침
                    return INVALID;
                }

                if (idx < startWord) {
                    sb.append(removeLowerCase(sentence.substring(idx, startWord - 1)));
                    sb.append(" ");
                }

                sb.append(removeLowerCase(sentence.substring(startWord, endWord + 1)));
                sb.append(" ");
                lastStartWord = startWord;      // 현재 단어 시작 위치 할당
                lastEndWord = endWord;          // 현재 단어 끝 위치 할당
                lastStartChar = firstPos;       // 현재 소문자 시작 위치
                lastEndChar = lastPos;           // 현재 소문자 끝 위치
                idx = endWord + 1;              // 그다음 단어 시작 위치 할당
            }
        } catch(Exception e) {
            return INVALID;
        }

        if (idx < size) {     // 남은 단어를 결과에 추가
            sb.append(removeLowerCase(sentence.substring(idx, size - 1)));
        }

        return sb.toString().trim();        // 앞뒤 공백 제거 후 문자열 반환
    }

    /*
        소문자를 제거하는 메소드
    */
    private static String removeLowerCase(String str) {
        return str.replaceAll("[a-z]", "");     // 소문자를 공백으로 치환
    }

    public static void main(String[] args) {
        System.out.println(solution("SpIpGpOpNpGJqOqA"));
    }
}