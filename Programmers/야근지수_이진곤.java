import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : works) {
            map.putIfAbsent(num, 0);
            map.put(num, map.get(num) + 1);
        }
        ArrayList<int[]> list = new ArrayList<>();
        for (int key : map.keySet()) {
            list.add(new int[] { key, map.get(key) });
        }
        Collections.sort(list, Comparator.comparingInt(a -> a[0]));

        for (int i = list.size() - 1; i >= 1; i--) {
            if (list.get(i)[1] <= n) {
                n -= list.get(i)[1];
                if (list.get(i - 1)[0] == list.get(i)[0] - 1) {
                    list.get(i - 1)[1] += list.get(i)[1];
                    list.remove(i);
                }
                else {
                    list.get(i)[0]--;
                    i++;
                }
            }
            else {
                if (list.get(i - 1)[0] == list.get(i)[0] - 1) {
                    list.get(i - 1)[1] += n;
                }
                else {
                    list.add(new int[] { list.get(i)[0] - 1, n });
                }
                list.get(i)[1] -= n;
                n = 0;
                break;
            }
        }

        if (n > 0) {
            int x = list.get(0)[0];
            int y = list.get(0)[1];
            if (n % y == 0) {
                list.add(new int[] {
                        x - n / y, y
                });
            }
            else {
                list.add(new int[]{
                        (int) (x - Math.ceil((double) n / y)), n % y
                });
                list.add(new int[]{
                        (int) (x - Math.ceil((double) n / y) + 1), y - n % y
                });
            }
            list.remove(0);
        }

        long answer = 0;
        for (int[] arr : list) {
            if (arr[0] < 0) {
                break;
            }
            answer += (long) Math.pow(arr[0], 2) * arr[1];
        }
        return answer;
    }
}