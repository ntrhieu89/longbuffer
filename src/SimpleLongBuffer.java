import java.util.concurrent.atomic.AtomicLong;

public class SimpleLongBuffer implements LongBuffer {
	int maxSize;
	AtomicLong[] array;
	int idx;
	
	public SimpleLongBuffer(int maxSize) {
		this.maxSize = maxSize;
		array = new AtomicLong[maxSize];
		for (int i = 0; i < array.length; i++)
			array[i] = new AtomicLong(0);
		idx = -1;
	}
	
	@Override
	public long add(long val) {
		return array[(++idx % maxSize)].getAndSet(val);
	}

}
