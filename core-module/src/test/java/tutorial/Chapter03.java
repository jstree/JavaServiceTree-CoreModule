package tutorial;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class Chapter03 {

    private String str1;
    private String str2;
    private String str3;

    @Before
    public void setUpVariable() {
        this.str1 = new String("hello");
        this.str2 = new String("hello");
        this.str3 = "hello"; // String은 예외로 new 를 생략할 수 있다 (편의성을 위해)
    }

    @Test
    public void compareStringValueTest() {
        // String은 클래스(참조형)이기 때문에 자체는 주소값을 가짐
        assertFalse(str1 == str2);

        // String의 equals() 메소드를 이용하여 값 비교
        assertTrue(str1.equals(str2));
    }

    @Test
    public void compareStringReferenceTest() {
        // String은 문자열 배열과 같지만 추가적으로 기타 유용한 기능(메소드)이 들어간 클래스이다.
        // String class = char array + util
        String str = "Hello";
        assertTrue(str == str); // 당연히 같은 주소값을 가짐

        // String 값을 변경하면, 값이 변경되는 것처럼 보이지만 실제로는 새로운 문자열을 반환한다
        String change_str = str + ", Java!";
        assertFalse(str == change_str); // 다른 주소값을 가짐
    }

}
