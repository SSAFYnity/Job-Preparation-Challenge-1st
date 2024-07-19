class Solution {
	
	static int resultuser = 0;
	static int resultprice = 0;
	
	public static int[] solution(int[][] users, int[] emoticons) {
		
		calc(users, emoticons, new int[emoticons.length], 0);
		
		int[] answer = {resultuser,resultprice};
		return answer;
	}
	
	private static void calc(int[][] users, int[] emoticons, int[] discount, int num) {
		if (num == emoticons.length) {
			plusmember(users, emoticons, discount);
			return;
		}
		
		for (int i = 10; i <= 40; i+=10) {
			discount[num] = i;
			calc(users, emoticons, discount, num+1);
		}
	}
	
	private static void plusmember(int[][] users, int[] emoticons, int[] discount) {
		int user = 0;
		int pullprice = 0;
		
		for (int i = 0; i < users.length; i++) {
			int price = 0;
			for (int j = 0; j < discount.length; j++) {
				if (discount[j] >= users[i][0]) {
					price += emoticons[j] / 100 * (100 - discount[j]);
				}
			}
			
			if (price >= users[i][1]) {
				user++;
			} else {
				pullprice += price;
			}
		}
		
		if (user > resultuser) {
			resultuser = user;
			resultprice = pullprice;
		} else if (user == resultuser) {
			resultprice = pullprice > resultprice ? pullprice : resultprice;
		}
		
	}
}
