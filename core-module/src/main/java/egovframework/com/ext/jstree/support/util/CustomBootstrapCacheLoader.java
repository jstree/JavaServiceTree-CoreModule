package egovframework.com.ext.jstree.support.util;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.bootstrap.BootstrapCacheLoader;

import java.util.List;

public class CustomBootstrapCacheLoader implements BootstrapCacheLoader {

    boolean	asynchronous;

    @Override
    public void load(Ehcache cache) throws CacheException {
        List<?> keys = cache.getKeys();

        if ((keys == null) || keys.isEmpty())
        {
            return;
        }

        for (Object key : keys)
        {
            Element el = cache.getQuiet(key);
            cache.removeQuiet(key);
            cache.putQuiet(el);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean isAsynchronous() {
        return asynchronous;
    }

    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }
}
