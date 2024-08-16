import java.util.*;

/*
    코드 실행 -> 통과
    제출 후 채점하기 -> 실패
*/

class Solution {
    static class Point{
        Character s;        // 특수기호
        int count;          // 나온 횟수

        public Point(Character s, int count){
            this.s = s;
            this.count = count;
        }
    }

    public String solution(String sentence) {
        String answer = "";
        boolean flag = true;       // 이전 글자의 대소문자(대문자 -> True, 소문자 -> false)
        String word = "";   // 임시 저장
        Stack<Point> stack = new Stack<>();
        int n = sentence.length();
        if(sentence.trim().length() != n){
            return "invalid";
        }

        for(int i = 0; i < n; i++){
            Character ch = sentence.charAt(i);

            if(Character.isUpperCase(ch)){       // 대문자인 경우
                if(word.length() == 0){             // 아직 아무것도없는 경우
                    word = Character.toString(ch);
                }else{
                    if(!flag){          // 이전 문자가 소문자
                        word += Character.toString(ch);
                    }else{
                        Point p = stack.peek();        // stack의 마지막 값

                        if(p.count == 1){
                            word += Character.toString(ch);     // 규칙 2
                        }else{
                            if(answer.length() > 0){            // 새로운 문자
                                answer += " " + word;
                                word = Character.toString(ch);
                                stack.pop();        // 문자와 짝이었던 특수기호 pop
                            }else{
                                answer = word;
                                word = Character.toString(ch);
                                stack.pop();
                            }
                        }
                    }
                }
                flag = true;
            }else{                               // 소문자인 경우
                if(stack.size() == 0){                      // 아직 아무것도 없는 경우
                    stack.push(new Point(ch, 1));
                }else{
                    if(flag){
                        Point p = stack.peek();
                        if(p.s.equals(ch)){     // 마지막 요소와 같을경우 -> count 1증가
                            p.count++;
                        }else{
                            if(word.length() > 0){
                                if(answer.length() > 0){
                                    answer += " "+ word;
                                    word = "";
                                }else{
                                    answer = word;
                                    word = "";
                                }
                                stack.pop();
                                stack.push(new Point(ch, 1));
                            }else{
                                stack.push(new Point(ch, 1));
                            }
                        }
                    }

                }
                flag = false;
            }
        }

        if(word.length() > 0){
            while(!stack.isEmpty()){
                Point p = stack.pop();
                if(p.count < 2){
                    answer = "invalid";
                    break;
                }
                if(word.length() > 2 && p.count == word.length()){
                    answer = "invalid";
                    break;
                }

                if(answer.length() > 0){
                    answer += " " + word;
                }
            }
        }

        return answer;
    }
}