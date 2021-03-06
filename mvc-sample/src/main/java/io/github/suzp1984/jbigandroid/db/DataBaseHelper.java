package io.github.suzp1984.jbigandroid.db;

import java.util.Collection;
import java.util.List;

/**
 * Created by moses on 8/31/15.
 */
public interface DataBaseHelper {
    List<byte[]> getJbigs();

    byte[] getJbig(int position);

    void put(byte[] jbig);

    void put(Collection<byte[]> jbigs);

    void delete(byte[] jbig);
    void delete(int position);

    void deleteAll();
}
