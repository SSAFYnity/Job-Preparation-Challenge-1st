class Solution {
	static int[] discount = {10,20,30,40};
	static int maxOfSubscribe;
	static int maxOfCost;
	public int[] solution(int[][] users, int[] emoticons) {
		findResult(0,emoticons.length, new int[emoticons.length],users,emoticons);
		return new int[]{maxOfSubscribe,maxOfCost};
	}

	public void findResult(int index,int emoticonsLength, int[] discounts,int[][] users, int[] emoticons){
		if (index == emoticonsLength){
			int subscribe = 0;
			int cost = 0;

			for (int[] user: users){
				int userDiscountRate = user[0];
				int userMaxCost = user[1];

				int sum = 0;

				for (int i = 0; i < emoticons.length; i++){
					if (discounts[i]>=userDiscountRate){
						sum += emoticons[i]/100*(100-discounts[i]);
					}
				}
				if (sum>=userMaxCost)subscribe++;
				else cost+=sum;
			}
			if (subscribe>maxOfSubscribe){
				maxOfSubscribe = subscribe;
				maxOfCost = cost;
			}else if (subscribe == maxOfSubscribe){
				maxOfCost = Math.max(maxOfCost,cost);
			}
			return;
		}
		for (int i = 0; i < 4; i++){
			discounts[index] = discount[i];
			findResult(index+1,emoticonsLength,discounts,users,emoticons);
		}
	}
}


// ####  런타임 에러 코드
/*

static int maxCost;
static int maxSubscribe;
static int[] discount = {10, 20, 30, 40};

public static int[] solution(int[][] users, int[] emoticons) {
	int[] dcArr = new int[4];
	permutation(0, dcArr, emoticons, users);
	return new int[] {maxSubscribe, maxCost};
}

static void permutation(int depth, int[] discounts, int[] emoticons, int[][] users) {
	if (depth == emoticons.length) {
		int cost = 0;
		int subscribe = 0;

			*/
/*
			1. 현재 이모티콘 할인율 >= 사용자 할인율
			 T - 사용자는 이모티콘 구매
			 F - 구메x
			2. 총 구매 금액 > 사용자 마지노선 금액
			 T - 이모티콘 플러스 가입자 ++
			 F - 금액
			 *//*

		for (int i = 0; i < users.length; i++) {
			int discountRate = users[i][0];
			int maxCost = users[i][1];
			int sum = 0;

			// 이모티콘 할인율 iter
			for (int j = 0; j < emoticons.length; j++) {
				if (discounts[j] >= discountRate) {
					sum += emoticons[j] / 100 * (100 - discounts[j]);
				}
			}

			if (maxCost <= sum) {
				subscribe++;
			} else {
				cost += sum;
			}
		}

		if (maxSubscribe < subscribe) {
			maxSubscribe = subscribe;
			maxCost = cost;
		} else if (maxSubscribe == subscribe){
			maxCost = Math.max(cost, maxCost);
		}
		return;
	}

	for (int i = 0; i < 4; i ++) {
		discounts[depth] = discount[i];
		permutation(depth + 1, discounts, emoticons, users);
	}
}*/
