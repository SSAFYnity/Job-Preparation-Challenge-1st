import java.util.*;

class Solution {
    
    Map<Character, List<Integer>> symbols = new LinkedHashMap<>();
    StringBuilder answer = new StringBuilder();
    
    public String solution(String sentence) {
        if (sentence.contains(" ")) { // 공백 제거가 안된 경우
            return "invalid";
        }
        if (isUpperCase(sentence)) {  // 문자로만 이루어진 경우
            return sentence;
        }
        findPositionOfSymbol(sentence);
        
        int idx = 0;
        int lastStart = -1, lastEnd = -1;
        for (List<Integer> position : symbols.values()) {
            int count = position.size();
            int start = position.get(0);
            int end = position.get(count - 1);
            
            if (count == 1 || count >= 3) { // rule1
                if (gapIsNot2(position) || // rule1 간격 만족 X
                    isOutOfBounds(sentence, start - 1, end + 1) ||  // 기호가 처음이나 끝에 있는 경우
                    Character.isLowerCase(sentence.charAt(start - 1)) ||  // 기호가 연속되는 경우
                    Character.isLowerCase(sentence.charAt(end + 1))) {
                    return "invalid";
                }
                if (start > lastStart && end < lastEnd) { // rule2 내부의 rule1 -> 포함되므로 변환 X
                    continue;
                }
                if (idx > start - 1) {  // 이전 단어와 겹치는 경우
                    return "invalid";
                }
                appendAnswer(sentence, idx, start - 1);
                appendAnswer(sentence, start - 1, end + 2);
                idx = end + 2;
                lastStart = start;
                lastEnd = end;
            }
            if (count == 2) {   // rule2?
                int gap = end - start;
                if (gap < 2) {
                    return "invalid";   // 기호는 연속 불가
                }
                if (start > lastStart && end < lastEnd) { // rule2 내부의 rule1 -> 포함되므로 변환 X
                    if (gapIsNot2(position) ||  // rule1 간격 만족 X
                        Character.isLowerCase(sentence.charAt(start - 1)) ||  // 기호가 연속되는 경우
                        Character.isLowerCase(sentence.charAt(end + 1))) {
                        return "invalid";
                    }
                    continue;
                }
                if (idx > start + 1) {  // 이전 단어와 겹치는 경우
                    return "invalid";
                }
                appendAnswer(sentence, idx, start + 1);
                appendAnswer(sentence, start + 1, end);
                idx = end + 1;
                lastStart = start;
                lastEnd = end;
            }
        }
        String postfix = sentence.substring(idx, sentence.length());  // 남은 문자열 이어붙이기
        return answer.append(postfix).toString().trim();
    }
    
    private boolean isUpperCase(String sentence) {
        for (char c : sentence.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
    
    private void findPositionOfSymbol(String sentence) {
        for (int i = 0; i < sentence.length(); i++) {
            char symbol = sentence.charAt(i);
            if (Character.isLowerCase(symbol)) {    // 기호라면 map에 위치 저장
                if (!symbols.containsKey(symbol)) {
                    symbols.put(symbol, new ArrayList<>());
                }
                symbols.get(symbol).add(i);
            }
        }
    }
    
    private boolean isOutOfBounds(String sentence, int start, int end) {
        return start < 0 || end >= sentence.length();
    }
    
    private boolean gapIsNot2(List<Integer> position) {
        for (int i = 0; i < position.size() - 1; i++) {
            if (position.get(i + 1) - position.get(i) != 2) {
                return true;
            }
        }
        return false;
    }
    
    private void appendAnswer(String sentence, int start, int end) {
        String word = sentence.substring(start, end).replaceAll("[a-z]", "");
        if (!word.equals("")) {
            answer.append(word).append(" ");
        }
    }
}