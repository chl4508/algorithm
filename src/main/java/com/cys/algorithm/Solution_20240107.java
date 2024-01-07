package com.cys.algorithm;

import java.util.Arrays;

public class Solution_20240107 {

    public int[] solution(int[] num_list){
        //r_pivot_sort(num_list, 0, num_list.length - 1);
        //return num_list;

        int[] answer = new int[5];
        Arrays.sort(num_list);
        for(int i=0; i<5;i++){
            answer[i] = num_list[i];
        }
        return answer;

    }


    public static void main(String[] args){
        Solution_20240107 solution20240107 = new Solution_20240107();

        int[] test1 = {12, 4, 15, 46, 38, 1, 14};
        int[] result = solution20240107.solution(test1);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 선택 정렬
     * @param num_list
     * @return
     */
    private static int[] select_sort(int[] num_list){
        //선택정렬 : 시간 복잡도 O(n^2)
        //선택 정렬은 어떤 정렬이 오더라도 비교하는 방식이 같기 때문에, 일반적인 상황에서 비슷한 성능을 보유한다.
        //시간 복잡도가 O(n^2)이기 때문에 실전에서 사용하기 힘든 정렬이다.
        for(int i = 0; i < num_list.length - 1; i++) {
            // 현재 탐색에서 가장 앞의 원소를 초기 값으로 설정해둔다.
            int MinIndex = i;
            // 탐색을 진행하며, 가장 작은 값을 찾는다.
            for(int j = i + 1; j < num_list.length; j++) {
                if(num_list[MinIndex] > num_list[j])
                    MinIndex = j;
            }
            // 탐색이 완료되면 가장 작은 값을 가장 앞의 원소와 가장 작은 원소의 위치를 바꾸어준다.
            int temp = num_list[MinIndex];
            num_list[MinIndex] = num_list[i];
            num_list[i] = temp;
        }
        return num_list;
    }


    /**
     *  오른쪽 피벗 선택 방식
     * @param a		정렬할 배열
     * @param lo	현재 부분배열의 왼쪽
     * @param hi	현재 부분배열의 오른쪽
     */
    private static void r_pivot_sort(int[] a, int lo, int hi) {

        /*
         *  lo가 hi보다 크거나 같다면 정렬 할 원소가
         *  1개 이하이므로 정렬하지 않고 return한다.
         */
        if(lo >= hi) {
            return;
        }

        /*
         * 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로
         * 만들어 준 뒤, 최종적으로 pivot의 위치를 얻는다.
         *
         * 그리고나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나누어
         * 분할 정복을 해준다.
         *
         * [과정]
         *
         * Partitioning:
         *
         *         left part                right part       a[right]
         * +---------------------------------------------------------+
         * |    element < pivot    |    element >= pivot   |  pivot  |
         * +---------------------------------------------------------+
         *
         *
         *  result After Partitioning:
         *
         *         left part         a[hi]          right part
         * +---------------------------------------------------------+
         * |   element < pivot    |  pivot  |    element >= pivot    |
         * +---------------------------------------------------------+
         *
         *
         *  result : pivot = hi
         *
         *
         *  Recursion:
         *
         * r_pivot_sort(a, lo, pivot - 1)     r_pivot_sort(a, pivot + 1, hi)
         *
         *         left part                           right part
         * +-----------------------+             +-----------------------+
         * |   element <= pivot    |    pivot    |    element > pivot    |
         * +-----------------------+             +-----------------------+
         * lo                pivot - 1        pivot + 1                 hi
         *
         */
        int pivot = partition(a, lo, hi);

        r_pivot_sort(a, lo, pivot - 1);
        r_pivot_sort(a, pivot + 1, hi);
    }



    /**
     * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
     *
     * @param a		정렬 할 배열
     * @param left	현재 배열의 가장 왼쪽 부분
     * @param right	현재 배열의 가장 오른쪽 부분
     * @return		최종적으로 위치한 피벗의 위치(lo)를 반환
     */
    private static int partition(int[] a, int left, int right) {

        int lo = left;
        int hi = right;
        int pivot = a[right];		// 부분리스트의 오른쪽 요소를 피벗으로 설정

        // lo가 hi보다 작을 때 까지만 반복한다.
        while(lo < hi) {

            /*
             * hi가 lo보다 크면서, lo의 요소가 pivot보다 큰 원소를
             * 찾을 떄 까지 lo를 증가시킨다.
             */
            while(a[lo] < pivot && lo < hi) {
                lo++;
            }

            /*
             * hi가 lo보다 크면서, hi의 요소가 pivot보다 작거나 같은 원소를
             * 찾을 떄 까지 hi를 감소시킨다.
             */
            while(a[hi] >= pivot && lo < hi) {
                hi--;
            }


            // 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
            swap(a, lo, hi);
        }


        /*
         *  마지막으로 맨 처음 pivot으로 설정했던 위치(a[right])의 원소와
         *  hi가 가리키는 원소를 바꾼다.
         */
        swap(a, right, hi);

        // 두 요소가 교환되었다면 피벗이었던 요소는 hi에 위치하므로 hi를 반환한다.
        return hi;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
