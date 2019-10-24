package egovframework;

import egovframework.com.ext.jstree.springHibernate.core.service.JsTreeHibernateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2019-10-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/egovframework/spring/com/**/context-*.xml"})
@ActiveProfiles(profiles = "dev")
public class SpringUnitTestSample extends BaseUnitTest {

    @Autowired
    @Qualifier("JsTreeHibernateService")
    private JsTreeHibernateService jsTreeHibernateService;


    @Test
    public void functionalScannerTest() {
        System.out.println("springTestStart");
    }


}
