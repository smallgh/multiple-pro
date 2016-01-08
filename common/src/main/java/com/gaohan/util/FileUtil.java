package com.gaohan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * File Utilities<br>
 *
 * Created by liqipan on 6/3/15.
 */

public class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	public static void copy(File ori, File to) {

		FileInputStream fis = null;
		FileChannel rc = null;

		FileOutputStream fos = null;
		FileChannel wc = null;

		ByteBuffer bytf = null;
		try {
			fis = new FileInputStream(ori);
			rc = fis.getChannel();

			fos = new FileOutputStream(to);
			wc = fos.getChannel();

			bytf = ByteBuffer.allocate(1024);
			while (rc.read(bytf) != -1) {
                bytf.flip();
                wc.write(bytf);
                bytf.clear();
            }

        } catch (FileNotFoundException e) {
            LOGGER.info("FileUtil.copy", e);
        } catch (IOException e) {
            LOGGER.error("FileUtil.copy io error", e);
        } finally {
            flush(fos);
            close(wc, rc, fos, fis);
        }

    }

    /**
     * @param toFlush
     */
    public static void flush(Flushable... toFlush) {
        for (Flushable f : toFlush) {
            try {
                if (f != null) f.flush();
            } catch (Exception e) {
                LOGGER.info("flush", e);
            }
        }
    }

    /**
     * @param toCloss
     */
    public static void close(Closeable... toCloss) {
        for (Closeable c : toCloss) {
            try {
                if (c != null) c.close();
            } catch (Exception e) {
                LOGGER.info("close", e);
            }
        }
    }

}
