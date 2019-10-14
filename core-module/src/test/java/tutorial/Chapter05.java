package tutorial;

import org.junit.Before;
import org.junit.Test;

public class Chapter05 {
    private int[] scores;
    private int[] sourceArray;
    private int[] targetArray;

    @Before
    public void setUpScores() {
        this.scores = new int[5];
        for(int i=0; i<scores.length; i++) {
            this.scores[i] = i;
        }

        this.sourceArray = new int[] {0, 1, 2, 3, 4};
        int[] testArray = {0, 1, 2, 3, 4}; // new int[] 는 생략 가능

        // testArray는 new int[] 를 생략이 가능해서 없을 뿐,
        // 생성하면서 초기화까지 한 상태이다.
        // this.targetArray = {0, 1, 2, 3, 4}; // 컴파일 에러. 생성하지 않고 값을 할당하는 것은 불가능
        this.targetArray = new int[5];
    }

    @Test
    public void printArrayByFor() {
        for(int i=0; i<this.scores.length; i++) {
            System.out.print(this.scores[i] + " ");
        }
        System.out.println();

        for(int score : this.scores) {
            System.out.print(score + " ");
        }
    }

    @Test
    public void copyArrayTest() {
        for (int i=0; i<this.sourceArray.length; i++) {
            this.targetArray[i] = this.sourceArray[i];
        }

        /*
        System.arraycopy()를 이용할 것.

        위 처럼 for문을 이용하여 복사할 수 있지만, 비효율적이다.
        System.arraycopy() 를 이용하면, 지정된 범위의 배열을 통째로 복사한다.
        요소들이 연속적으로 저장되어 있다는 (배열의) 특징을 이용한 것

        원하는 범위를 지정해서 원하는 만큼 복사가 가능하다.
        System.arraycopy(원본, 원본의 시작 인덱스, 복사 대상, 복사대상의 시작인덱스, 몇 개)
        */
        System.arraycopy(sourceArray, 0, targetArray, 0, sourceArray.length);
    }
}