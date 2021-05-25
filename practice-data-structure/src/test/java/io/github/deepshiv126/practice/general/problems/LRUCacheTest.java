package io.github.deepshiv126.practice.general.problems;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LRUCacheTest {

    @Test
    public void testLRUCache() {
        LRUCache<Integer, Integer> lRUCache = new LRUCache(2);
        Assert.assertEquals(0, lRUCache.size());

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        Assert.assertEquals(new Integer(1), lRUCache.get(1));    // return 1

        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        Assert.assertEquals(null, lRUCache.get(2));  // returns -1 (not found)

        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        Assert.assertEquals(null, lRUCache.get(1));   // return -1 (not found)

        Assert.assertEquals(new Integer(3), lRUCache.get(3));   // return 3
        Assert.assertEquals(new Integer(4), lRUCache.get(4));   // return 4
    }
}
