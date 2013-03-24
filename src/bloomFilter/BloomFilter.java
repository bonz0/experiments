package bloomFilter;

import java.util.BitSet;
import java.util.ArrayList;

public class BloomFilter {
	private static final int DEFAULT_NUM_HASHES = 3;
	private static final int DEFAULT_SIZE = 16;
	private static final int MAXIMUM_SIZE = 1 << 30;

	private BitSet bloomFilter;		// bit array
	private int k;					// number of hashes
	private int n;					// number of elements
	private int m;					// size of the bloom filter

	/*
	 * Creates a bloom filter of initialSize size
	 * with k number of hashes. Default k = 3
	 */
	public BloomFilter (int initialSize, int k) {
		// size of filter cannot be <= 0
		if(initialSize < 0) {
			throw new IllegalArgumentException("Illegal size: " + initialSize);
		}
		initialSize = (initialSize > MAXIMUM_SIZE) ? MAXIMUM_SIZE : initialSize;
		int size = 1;
		while(size < initialSize) {
			size <<= 1;
		}
		initialSize = size;
		initialSize = (initialSize < DEFAULT_SIZE) ? DEFAULT_SIZE : initialSize;
		this.bloomFilter = new BitSet(initialSize);
		this.k = (k < 3) ? DEFAULT_NUM_HASHES : k;
		this.n = 0;
		this.m = size;
	}

	public BloomFilter () {
		this(DEFAULT_SIZE, DEFAULT_NUM_HASHES);
	}

	public BloomFilter (int initialSize) {
		this(initialSize, DEFAULT_NUM_HASHES);
	}

	/*
	 * Returns the size of the bloom filter array
	 */
	public int size() {
		return this.m;
	}

	/*
	 * Adds an object to the bloom filter
	 */
	public void add(Object obj) {
		int hashCode = obj.hashCode();
		boolean alreadyPresent = true;
		/*
		 * The following loop begins from 1 and not 0.
		 * This is because, since we're multiplying the
		 * index with the hashCode to get the different
		 * hashes, if the loop began from 0, the first
		 * hash would always map to index 0 in the filter.
		 */
		// TODO
		// Need to improve hashing
		for(int iii = 1; iii <= k; iii++) {
			int index = Math.abs(hashCode + hashCode * iii) & (this.m - 1);
			alreadyPresent &= this.bloomFilter.get(index);
			this.bloomFilter.set(index);
		}
		if(!alreadyPresent)
			this.n++;
	}

	public void addAll(ArrayList<?> list) {
		for(Object obj: list) {
			this.add(obj);
		}
	}
	/*
	 * Returns the number of Objects
	 * stored in the bloom filter
	 */
	public int numberOfElements() {
		return this.n;
	}

	/*
	 * Returns true if the filter may, or may not
	 * contain the Object.
	 * Returns false if the filter does not
	 * contain the Object.
	 */
	public boolean contains(Object obj) {
		int hashCode = obj.hashCode();
		boolean alreadyPresent = true;
		for(int iii = 1; iii <= k; iii++) {
			int index = Math.abs(hashCode + hashCode * iii) & (this.m - 1);
			alreadyPresent &= this.bloomFilter.get(index);
		}
		return alreadyPresent;
	}

	/*
	 * Prints the bloom filter.
	 * For debugging purposes only.
	 */
	@Override
	public String toString() {
		String filterString = "";
		for(int iii = 0; iii < m; iii++) {
			if(this.bloomFilter.get(iii))
				filterString += 1;
			else 
				filterString += 0;
		}
		return filterString;
	}

	/*
	 * Returns the filter's false positive rate
	 */
	public double getFalsePositiveRate() {
		int kn = this.k * this.n;
		double ePower = -1.0 * ((double) kn) / ((double) this.m);
		double fpr = Math.pow((1.0 - Math.exp(ePower)), this.k);
		return fpr;
	}
}