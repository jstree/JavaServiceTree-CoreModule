package egovframework;

import egovframework.com.ext.jstree.springHibernate.core.service.JsTreeHibernateService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "customCacheManager")
    CacheManager cacheManager;


    @Test
    public void functionalScannerTest() {
        System.out.println("springTestStart");
    }

    @Test
    public void chacheTest(){
        Cache tmpcache = cacheManager.getCache("LocalEhCache");


        String urlCacheKey = "/api/v1/test" + "/engine/2019.12.01.03/2019-12/48967";
        String strCacheValue = "test Value";

        tmpcache.put(new Element(urlCacheKey, strCacheValue));
        Element value = tmpcache.get(urlCacheKey);
        logger.info(value.getValue().toString());

        tmpcache.remove(urlCacheKey);

        tmpcache.put(new Element(urlCacheKey, strCacheValue));


    }


}
