package put.io.testing.junit;

import org.junit.jupiter.api.*;


public class FailureOrErrorTest {
    @Test
    public void test1() {
        Assertions.assertEquals(-5, 5);
    }
    @Test
    public void test2(){
        throw new ArithmeticException("Za duży int");
    }
    @Test
    public void test3() {
        try{
            Assertions.assertEquals(-5, 5);
        }catch(Throwable i){
            System.out.println(i.getStackTrace());
        }
    }
}