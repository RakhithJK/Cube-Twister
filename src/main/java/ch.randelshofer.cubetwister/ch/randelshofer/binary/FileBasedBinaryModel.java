/*
 * @(#)FileBasedBinaryModel.java
 * CubeTwister. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.binary;

import org.jhotdraw.annotation.Nonnull;
import org.jhotdraw.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.WeakHashMap;

/**
 * FileBasedBinaryModel.
 * <p>
 * FIXME - The file based binary model may grow infinitely large.
 *
 * @author Werner Randelshofer
 * @version 1.0.1 2010-05-24 Method getBytes sometimes returned invalid length.
 * <br>1.0 2010-04-09 Created.
 */
public class FileBasedBinaryModel implements BinaryModel {

    private int segsize = 1024;
    private long offset;
    private long length;
    @Nullable
    private RandomAccessFile racf;
    @Nonnull
    private WeakHashMap<Long, byte[]> cache = new WeakHashMap<Long, byte[]>();
    private File file;

    public FileBasedBinaryModel(@Nonnull File file) throws IOException {
        this(file, 0, file.length());
    }

    public FileBasedBinaryModel(@Nonnull File file, long offset, long length) throws IOException {
        racf = new RandomAccessFile(file, "r");
        this.offset = offset;
        this.length = length;
        this.file = file;
    }

    @Nonnull
    @Override
    public String toString() {
        return "FileBasedBinaryModel " + file.getName() + " " + offset + " " + length;
    }

    @Override
    public long getLength() {
        return length;
    }

    @Override
    public int getBytes(long off, int len, @Nonnull byte[] target) {
        byte[] cdat = cache.get(off);

        if (off + len > length) {
            len = (int) (length - off);
        }

        if (cdat != null) {
            if (cdat.length >= len) {
                System.arraycopy(cdat, 0, target, 0, len);
                return len;
            }
        }

        try {
            //byte[] cdat = new byte[segsize];

            racf.seek(off + offset);
            racf.readFully(target, 0, len);
            byte[] cached = target.clone();
            cache.put(off, cached);

        } catch (IOException ex) {
            Arrays.fill(target, 0, len, (byte) 0);
            ex.printStackTrace();
        }
        return len;
    }

    @Override
    public void close() {
        if (racf != null) {
            try {
                racf.close();
                racf = null;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void finalize() {
        close();
    }
}
