import java.util.concurrent.atomic.AtomicLong;

/**
 * LongBuffer using synchronized method.
 * @author cavinas
 *
 */
public class SynchronizedLongBuffer implements LongBuffer{
	int maxSize;
	long[] array;
	int idx;
	
	public SynchronizedLongBuffer(int maxSize) {
		this.maxSize = maxSize;
		array = new long[maxSize];
		for (int i = 0; i < array.length; i++)
			array[i] = 0;
		idx = -1;
	}
	
	@Override
	public synchronized long add(long val) {
		if (++idx == maxSize)
			idx = 0;
		long retval = array[idx];
		array[idx] = val;
		
		return retval;
	}
}
