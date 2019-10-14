package tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Scanner;

public class Chapter02 {

    private int x;
    private int y;
    private int tmp;

    @Before
    public void setUpVariable() {
        this.x = 10;
        this.y = 5;
        this.tmp = 0;
    }

    @Test
    public void sortTest() {
        // 두 값 교환하기
        this.tmp = this.x;
        this.x = this.y;
        this.y = this.tmp;
        assertEquals(5, this.x);
        assertEquals(10, this.y);
    }

    @Test
    public void namingTest() {
        // 일반 변수는 camelCase로 표현
        int rectangleArea = 20 * 10;
        System.out.println("rectangleArea: " + rectangleArea);

        // 상수는 대문자를 이용하여 명명 (암묵적인 관례)
        final int MAX_VALUE = 5;
        System.out.println("MAX_VALUE: " + MAX_VALUE);

        // 컴파일러 에러. 상수는 재할당 불가능
        // MAX_VALUE = 10;

        // 상수는 선언과 동시에 초기화를 해야함
        final int MAX_SPEED;
        // System.out.println("MAX_SPEED: " + MAX_SPEED); // 컴파일러 에러.
    }

    @Test
    public void concatStringByAddOperatorTest() {
        // 일반 더하기 연산
        System.out.println(this.x + this.y);

        // 더하기 연산으로 문자열을 합칠 수 있다.
        System.out.println("Hello" + "World");

        // 연산은 왼쪽부터 실행
        assertEquals("77", 7 + "7");
        assertEquals("147", 7 + 7 + "7");
        assertEquals("777", 7 + "7" + 7);
        assertEquals("777", "7" + 7 + 7);

        // 응용하여 정수를 문자열로 casting 가능
        assertEquals("7", "" + 7);
        assertEquals("7", 7 + "");
    }

    @Test
    public void functionalPrintfTest() {
        // character 문자
        // \n 대신 %n을 사용한다. OS마다 개행하는 문자가 다를 수 있기 때문에 %n이 더 안전하다. (p.36 참고)
        System.out.printf("char: %c%n", 'A');

        // int 정수
        System.out.printf("int: %d%n", 5);
        System.out.printf("int(우측 정렬): [%3d]%n", 5);
        System.out.printf("int(좌측 정렬): [%-3d]%n", 5);
        System.out.printf("int(0으로 빈공간 채우기): [%03d]%n", 5);

        // float 실수
        System.out.printf("float: %f%n", 1.3f);
        System.out.printf("float(총 7자리까지 표현 가능): %f%n", 1.873467854678896f); // 나머지 반올림

        // exponent 지수
        System.out.printf("exponent: %e 또는 %E%n", 1234.56f, 1234.56f);

        // oct 8진수
        System.out.printf("oct: %o%n", 20); // 10진수를 8진수로 출력
        System.out.printf("oct: %o%n", 020); // 8진수는 앞에 0을 붙여서 표현

        // hex 16진수
        System.out.printf("hex: %x%n", 64); // 10진수를 16진수로 출력
        System.out.printf("hex: %x%n", 0x64); // 16진수는 0x를 붙여서 출력

        // String 문자열
        // (참고. String은 primitive 타입이 아니고 Class다.)
        System.out.printf("String: %s%n", "Hello, Java!");
        System.out.printf("String: [%.8s]%n", "Hello, Java!"); // 왼쪽에서 8글자만 출력

        // binary 2진수
        System.out.printf("binary: %s", Integer.toBinaryString(10));
    }

    @Test
    @Ignore
    public void functionalScannerTest() {
        Scanner scanner = new Scanner(System.in); // java.util.Scanner
        System.out.println(scanner.nextLine()); // 문자열 입력 받기
        System.out.println(scanner.nextInt()); // 정수형 입력 받기
    }

    @Test
    public void overflowTest() {
        short sMin = -32768; // short는 2바이트로 최솟값 -32768을 가짐
        short sMax = 32767; // 최댓값 32767을 가짐

        // Java는 연산시 피연산자를 4바이트로 변환하여 계산하기 때문에 short로 casting이 필요함
        assertEquals(sMax, (short) (sMin - 1));

        char cMin = 0; // char는 문자형 타입이지만, 실제로는 아스키코드(정수형)를 저장하고 있다.
        char cMax = 65535; // unsigned 2바이트이기 때문에 65535를 최댓값으로 가진다.

        // short와 마찬가지로 타입 캐스팅이 필요
        assertEquals(cMax, (char) (cMin - 1));

        // 너무 큰 수는 오버플로우가 발생하여 원하는 값이 안나올 수 있다.
        final int BIG_INTEGER = 100_000;
        assertFalse(BIG_INTEGER * BIG_INTEGER / BIG_INTEGER == BIG_INTEGER);
    }

    @Test
    public void floatAndDoubleRangeTest() {
        float f = 9.12345678901234567890f; // float를 선언할때는 f를 붙여야 함
        double d = 9.12345678901234567890;
        System.out.printf("float: %f%n", f); // 7자리만 출력하고, 마지막 자리에서 반올림

        // 전체 24자리에서 20자리는 소숫점을 표현
        System.out.printf("float: %24.20f%n", f); // 오버플로우 발생하여 7자리 뒤 나머지 자리는 예상하지 못한 값이 출력
        System.out.printf("double: %24.20f%n", d); // double은 8바이트로 오버플로우 발생하지 않음
    }
}
