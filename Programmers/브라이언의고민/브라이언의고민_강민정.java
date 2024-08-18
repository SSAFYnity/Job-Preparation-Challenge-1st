import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class 브라이언의고민_강민정 {
    private static final String INVALID = "invalid";

    public String solution(String sentence) {
        StringBuilder answerList = new StringBuilder();
        Map<Character, List<Integer>> lowerCount = new LinkedHashMap<>();       // HashMap과 달리 입력 순서 보장
        List<Integer> temp;
        int stringIdx = 0;
        int startChar, endChar;
        int lastStartChar = -1, lastEndChar = -1;
        int startWord = 0, endWord = 0;
        int lastStartWord= -1, lastEndWord = -1;
        int count, distance;
        int rule = 0;

        try {
            int size = sentence.length();

            //소문자의 각 종류 / 위치 파악
            for(int i=0; i<size; i++){
                char c = sentence.charAt(i);

                if(Character.isLowerCase(c)){
                    if(!lowerCount.containsKey(c)){
                        lowerCount.put(c, new ArrayList<Integer>());
                    }
                    lowerCount.get(c).add(i);
                }
            }

            for(char c : lowerCount.keySet()){
                temp = lowerCount.get(c);
                count = temp.size();
                startChar = temp.get(0);
                endChar = temp.get(count-1);

                //AaA, AaAaAaA...
                if(count == 1 || count >= 3){
                    for(int i=1; i<count; i++){
                        //간격 2 넘어가면 x
                        if(temp.get(i) - temp.get(i-1) != 2) return INVALID;
                    }
                    rule = 1;
                }

                else if (count == 2){
                    distance = endChar - startChar;

                    //다른 기호 안에 있음 (규칙 2와 겹침)
                    if(distance == 2 && (endChar < lastEndChar && startChar > lastStartChar)){
                        rule = 1;
                    }
                    else if(distance >= 2){
                        rule = 2;
                    }
                    //소문자 연속은 x
                    else  return INVALID;
                }

                //규칙에 따른 예외
                if(rule == 1){
                    //기호 위치에서 앞뒤로 한칸씩
                    startWord = startChar -1;
                    endWord = endChar+1;

                    //이전 단어 안에 포함되어 있는 경우
                    if(lastStartWord < startWord && lastEndWord > endWord){
                        //규칙 2 아니면 안됨
                        if(startChar - lastStartChar  == 2 && lastEndChar - endChar == 2){
                            continue;
                        }
                        else return INVALID;
                    }
                }

                else if (rule == 2){
                    startWord = startChar;
                    endWord = endChar;
                    //규칙 2는 중복되면 안됨
                    if(lastStartWord < startWord && lastEndWord > endWord) return INVALID;
                }

                if(lastEndWord >= startWord) return  INVALID;

                //소문자 등장 이전에 존재하던 앞의 단어 추가
//                if(stringIdx < startWord){
//                    answerList.append(makeWord(sentence, stringIdx,startWord-1));
//                    answerList.append(" ");
//                }
                answerList.append(makeWord(sentence, stringIdx, Math.min(endChar + 1, size - 1)));  // 단어의 시작부터
                answerList.append(" ");
                lastStartWord = startWord;      // 최근 단어가 시작한 위치
                lastEndWord = endWord;          // 최근 단어가 끝난 우치
                lastStartChar = startChar;      // 최근 단어에서 소문자가 시작한 위치
                lastEndChar = endChar;          // 최근 단어에서 소문자가 끝난 위치
                stringIdx = endChar + 2;        // 그다음 단어 시작
            }

            //뒤에 남는 단어들도 더하기
            if(stringIdx < size){
                answerList.append(makeWord(sentence,stringIdx,size-1));
            }
        }
        catch (Exception e){
            return INVALID;
        }
        return answerList.toString().trim();
    }

    public String makeWord(String sentence, int start, int end){
        String word = sentence.substring(start, end+1);
        return word.replaceAll("[a-z]","");
    }
}