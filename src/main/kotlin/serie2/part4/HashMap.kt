package serie2.part4

class HashMap<K, V> (initialCapacity: Int = 16, val loadFactor: Float = 0.75f) /*: MutableMap<K, V>*/ {
    private class HashNode<K, V>(override val key: K, override var value: V,
                                 var next: HashNode<K, V>? = null
                                ): MutableMap.MutableEntry<K,V> {
        var hc = key.hashCode()
        override fun setValue(newValue: V): V {
            val oldValue = value
            value = newValue
            return oldValue
        }
    }

    private var table: Array<HashNode<K, V>?> = arrayOfNulls(initialCapacity)


    private fun expand() {
        TODO()
    }
}