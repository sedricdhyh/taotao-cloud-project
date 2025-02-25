package com.taotao.cloud.ip2region.impl;


import com.taotao.cloud.ip2region.model.DBReader;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机存取文件dbreader
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:28:39
 */
public class RandomAccessFileDBReader implements DBReader {

	protected RandomAccessFile raf;

	public RandomAccessFileDBReader(RandomAccessFile raf) {
		this.raf = raf;
	}

	@Override
	public byte[] full() throws IOException {
		byte[] buf = new byte[(int) raf.length()];
		raf.readFully(buf, 0, buf.length);
		return buf;
	}

	@Override
	public void readFully(long pos, byte[] buf, int offset, int length) throws IOException {
		raf.seek(pos);
		raf.readFully(buf, offset, length);
	}

	@Override
	public void close() throws IOException {
		raf.close();
	}
}
