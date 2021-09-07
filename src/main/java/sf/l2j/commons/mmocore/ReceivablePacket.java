package sf.l2j.commons.mmocore;

import ru.finex.nif.IncomePacket;

import java.nio.ByteBuffer;

/**
 * @author KenM
 * @param <T>
 */
public abstract class ReceivablePacket<T extends MMOClient> extends AbstractPacket<T> implements IncomePacket {

	NioNetStringBuffer _sbuf;

	protected ReceivablePacket() {

	}

	protected abstract boolean read();

	protected final void readB(final byte[] dst) {
		_buf.get(dst);
	}

	protected final void readB(final byte[] dst, final int offset, final int len) {
		_buf.get(dst, offset, len);
	}

	public byte[] readB(int length) {
		final byte[] result = new byte[length];
		_buf.get(result);
		return result;
	}

	protected final int readC() {
		return _buf.get() & 0xFF;
	}

	protected final int readH() {
		return _buf.getShort() & 0xFFFF;
	}

	protected final int readD() {
		return _buf.getInt();
	}

	protected final long readQ() {
		return _buf.getLong();
	}

	protected final double readF() {
		return _buf.getDouble();
	}

	protected final String readS() {
		_sbuf.clear();

		char ch;
		while ((ch = _buf.getChar()) != 0) {
			_sbuf.append(ch);
		}

		return _sbuf.toString();
	}

	/**
	 * packet forge purpose
	 *
	 * @param data
	 * @param client
	 * @param sBuffer
	 */
	public void setBuffers(ByteBuffer data, T client, NioNetStringBuffer sBuffer) {
		_buf = data;
		_client = client;
		_sbuf = sBuffer;
	}
}
