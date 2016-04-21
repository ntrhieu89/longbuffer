
public class Benchmark {
	public static final int NUM_THREADS = 100;
	public static final int NUM_NUMBERS = 1000000;
	
	public static void main(String[] args) {
		LongBuffer buffer;
		
		// benchmark SynchronizedLongBuffer
		buffer = new SynchronizedLongBuffer(20);
		benchmark(buffer, NUM_THREADS, NUM_NUMBERS);
		
		// benchmark AtomicLongBuffer
		buffer = new AtomicLongBuffer(20);
		benchmark(buffer, NUM_THREADS, NUM_NUMBERS);
	}
	
	/**
	 * Benchmarking a LongBuffer implementation.
	 * @param buffer
	 * @param numThreads
	 * @param numNumbers
	 */
	public static void benchmark(LongBuffer buffer, int numThreads, int numNumbers) {
		// init threads
		Thread[] threads = new Thread[numThreads];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread() {
				@Override
				public void run() {
					int cnt = 0;
					while (cnt++ <= numNumbers)
						buffer.add(cnt);
				}
			};
		}
		
		// start threads
		long start = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++)
			threads[i].start();
		
		// wait for all threads to finish
		for (int i = 0; i < threads.length; i++)
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println(buffer.getClass().getName()+": executed in "+
				(System.currentTimeMillis()-start));
	}
}
