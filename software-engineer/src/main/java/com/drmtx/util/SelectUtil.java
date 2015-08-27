package com.drmtx.util;

import com.drmtx.frequency.Point;

import java.util.*;

/**
 * Created by antivo on 8/27/15.
 */
// partial sorting (select first k from n) algorithm would be best. This is ... ad hoc for first iteration of solution
public class SelectUtil {
    private static class ValueComparator implements Comparator<String> {
        private Map<String, Long> base;

        public ValueComparator(Map<String, Long> base) {
            this.base = base;
        }

        @Override
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static List<Point> selectFirst(long n, Map<String, Long> map) {
        if(0 > n) return null;
        List<Point> list = new ArrayList<>();
        if(0 == n) return list;
        if(map.size() <= n) {
            map.entrySet().stream().forEach(entry-> list.add(new Point(entry.getKey(), entry.getValue())));
        } else {
            ValueComparator valueComparator = new ValueComparator(map);
            TreeMap<String, Long> treeMap = new TreeMap(valueComparator);
            treeMap.putAll(map);
            for (Map.Entry<String, Long> entry : treeMap.entrySet()) {
                list.add(new Point(entry.getKey(), entry.getValue()));
                if (--n == 0) break;
            }
        }
        return list;
    }
}
