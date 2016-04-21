import java.util.concurrent.atomic.AtomicLong;

/**
 * LongBuffer using AtomicLong
 * @author cavinas
 *
 */
public class AtomicLongBuffer implements LongBuffer{
	int maxSize;
	AtomicLong[] array;
	AtomicLong idx;
	
	public AtomicLongBuffer(int maxSize) {
		this.maxSize = maxSize;
		array = new AtomicLong[maxSize];
		for (int i = 0; i < array.length; i++)
			array[i] = new AtomicLong(0);
		idx = new AtomicLong(-1);
	}
	
	@Override
	public long add(long val) {
		long i = idx.incrementAndGet();
		i = i % maxSize;
		return array[(int) i].getAndSet(val);
	}
}
