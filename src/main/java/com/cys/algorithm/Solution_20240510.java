package com.cys.algorithm;

import java.util.Arrays;

/**
 * 연속된 부분 수열의 합
 *
 * 문제 설명
 * 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
 *
 * 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
 * 부분 수열의 합은 k 입니다.
 * 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
 * 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
 * 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때, 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return 하는 solution 함수를 완성해주세요. 이때 수열의 인덱스는 0부터 시작합니다.
 *
 * 제한사항
 * 5 ≤ sequence의 길이 ≤ 1,000,000
 * 1 ≤ sequence의 원소 ≤ 1,000
 * sequence는 비내림차순으로 정렬되어 있습니다.
 * 5 ≤ k ≤ 1,000,000,000
 * k는 항상 sequence의 부분 수열로 만들 수 있는 값입니다.
 * 입출력 예
 * sequence					k	result
 * [1, 2, 3, 4, 5]			7	[2, 3]
 * [1, 1, 1, 2, 3, 4, 5]	5	[6, 6]
 * [2, 2, 2, 2, 2]			6	[0, 2]
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * [1, 2, 3, 4, 5]에서 합이 7인 연속된 부분 수열은 [3, 4]뿐이므로 해당 수열의 시작 인덱스인 2와 마지막 인덱스 3을 배열에 담아 [2, 3]을 반환합니다.
 *
 * 입출력 예 #2
 *
 * [1, 1, 1, 2, 3, 4, 5]에서 합이 5인 연속된 부분 수열은 [1, 1, 1, 2], [2, 3], [5]가 있습니다. 이 중 [5]의 길이가 제일 짧으므로 해당 수열의 시작 인덱스와 마지막 인덱스를 담은 [6, 6]을 반환합니다.
 *
 * 입출력 예 #3
 *
 * [2, 2, 2, 2, 2]에서 합이 6인 연속된 부분 수열은 [2, 2, 2]로 3가지 경우가 있는데, 길이가 짧은 수열이 여러 개인 경우 앞쪽에 나온 수열을 찾으므로 [0, 2]를 반환합니다.
 */
public class Solution_20240510 {

	public static int[] solution(int[] sequence, int k) {

		// 투 포인터 (lt, rt), 누적합 (sum), 현재 포인터 간 길이 (ptrLen) 초기화
		int lt = 0;
		int rt = 0;
		int ptrLen = Integer.MAX_VALUE;
		int sum = 0;
		int[] answer = new int[2];
		while (rt < sequence.length && lt <= rt) {

			// 첫 번째 접근 또는 rt가 바라보는 원소가 k와 같을 때
			if (lt == rt) {
				sum = sequence[lt];
			}

			// 현재 누적합이 k와 같은 경우
			if (sum == k) {

				// 현재 포인터 간 길이가 이전 포인터 간 길이보다 짧은 경우
				if (ptrLen > rt - lt + 1) {
					ptrLen = rt - lt + 1;
					answer[0] = lt;
					answer[1] = rt;
				}

				// 모든 원소는 양수이기 때문에 다음 경우의 수를 찾기 위해 lt를 증가 시킬 것이며, 누적합 계산을 위해 현재 sequence[lt] 값을 빼준다.
				sum -= sequence[lt];

				// rt도 마찬가지로 증가시킬 것이기 때문에 현재 누적합에 sequence[rt + 1]을 더해준다.
				if (rt + 1 < sequence.length) {
					sum += sequence[rt + 1];
				}

				// 두 포인터가 같은 경우 가장 짧은 길이이기 때문에 순회를 종료
				if (lt == rt) {
					break;
				}

				// 각 포인터 증가
				lt++;
				rt++;

			} else if (sum > k) {
				// 누적합이 k 보다 큰 경우 lt를 증가해가며 k와 같은 지를 비교
				sum -= sequence[lt];
				lt++;
			} else if (sum < k) {
				// 누적합이 k 보다 작은 경우 rt를 증가해가며 k와 같은 지를 비교
				if (rt + 1 < sequence.length) {
					sum += sequence[rt + 1];
				}
				rt++;
			}

		}
		return answer;
	}

	public static void main(String[] args) {
		int[] sequence = {1, 2, 3, 4, 5};
		int[] answer =  solution(sequence, 7);
		System.out.printf(Arrays.toString(answer));
	}

}
