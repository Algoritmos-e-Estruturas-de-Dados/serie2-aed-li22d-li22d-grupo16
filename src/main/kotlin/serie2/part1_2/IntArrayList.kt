package serie2.part1_2

/**
 * A fixed-size FIFO (First-In, First-Out) list for storing integers with O(1) operations.
 *
 * This implementation uses a circular array and an offset value to efficiently support
 * appending, removing, retrieving, and bulk increment operations in constant time.
 *
 * @property capacity The maximum number of elements the list can store.
 */
class IntArrayList(private val capacity: Int) : Iterable<Int> {
    private val array = IntArray(capacity)      // Stores the integer elements
    private var start = 0                       // Index of the first (oldest) element
    private var end = 0                         // Index of the next insertion position
    private var elements = 0                    // Current number of elements in the list
    private var offset = 0                      // Logical value to add to each element (for addToAll)

    /**
     * Appends an integer to the end of the list.
     *
     * The value is stored relative to the current offset so that addToAll can be O(1).
     *
     * @param x The integer to append.
     * @return True if the value was added, false if the list is full.
     */
    fun append(x: Int): Boolean {
        if (elements == capacity) return false
        array[end] = x - offset // Store value adjusted for current offset
        end = (end + 1) % capacity     // Move end index circularly
        elements++
        return true
    }

    /**
     * Retrieves the nth element of the list (0-based index).
     *
     * The value is returned with the current offset applied.
     *
     * @param n The index of the element to retrieve.
     * @return The integer at position n, or null if the index is invalid.
     */
    fun get(n: Int): Int? {
        if (n < 0 || n >= elements) return null
        val index = (start + n) % capacity
        return array[index] + offset
    }

    /**
     * Adds a value to all elements in the list.
     *
     * This is done logically by updating the offset.
     *
     * @param x The value to add.
     */
    fun addToAll(x: Int) {
        offset += x
    }

    /**
     * Removes the first (oldest) element from the list.
     *
     * @return True if an element was removed, false if the list is empty.
     */
    fun remove(): Boolean {
        if (elements == 0) return false
        start = (start + 1) % capacity // Move start index circularly
        elements--
        return true
    }

    /**
     * Returns an iterator over the valid elements in the list in FIFO order.
     *
     * The iterator yields exactly the `size` elements currently stored,
     * starting from the logical start index and applying the current offset
     * to each value.
     *
     * This allows clients to traverse the list as if it were a standard FIFO queue,
     * abstracting away the internal circular array and offset logic.
     *
     * @return An iterator over the list elements in logical order.
     */
    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var index = 0
            override fun hasNext(): Boolean = index < elements
            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                val value = array[(start + index) % capacity] + offset
                index++
                return value
            }
        }
    }
}
