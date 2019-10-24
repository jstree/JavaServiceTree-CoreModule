package egovframework;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class BaseUnitTest {

    @Before
    public void injectMocks(){
        MockitoAnnotations.initMocks(this);
    }
}
