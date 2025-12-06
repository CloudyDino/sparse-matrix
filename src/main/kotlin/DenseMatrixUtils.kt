import kotlin.math.abs

/**
 * Transposes a dense matrix
 * @return the transposed square matrix
 */
fun Array<DoubleArray>.transposed(): Array<DoubleArray> {
    return Array(this[0].size) { i ->
        DoubleArray(this.size) { j ->
            this[j][i]
        }
    }
}

/**
 * Multiplies two dense matrices
 * @return the product of the two matrices
 */
fun Array<DoubleArray>.multiply(other: Array<DoubleArray>): Array<DoubleArray> {
    return Array(this.size) { i ->
        DoubleArray(other[0].size) { j ->
            var sum = 0.0
            for (k in this[0].indices) {
                sum += this[i][k] * other[k][j]
            }
            sum
        }
    }
}

/**
 * Adds two dense matrices
 * @return the sum of the two matrices
 */
fun Array<DoubleArray>.add(other: Array<DoubleArray>): Array<DoubleArray> {
    return Array(this.size) { i ->
        DoubleArray(this[0].size) { j ->
            this[i][j] + other[i][j]
        }
    }
}

/**
 * Subtracts two dense matrices
 * @return the difference of the two matrices
 */
fun Array<DoubleArray>.subtract(other: Array<DoubleArray>): Array<DoubleArray> {
    return Array(this.size) { i ->
        DoubleArray(this[0].size) { j ->
            this[i][j] - other[i][j]
        }
    }
}

/**
 * Checks if two dense matrices are equal
 * @param epsilon the maximum allowed difference between corresponding elements
 * @return true if the matrices are equal, false otherwise
 */
fun Array<DoubleArray>.equals(other: Array<DoubleArray>, epsilon: Double): Boolean {
    if (this.size != other.size || this[0].size != other[0].size) {
        return false
    }
    for (i in this.indices) {
        for (j in this[0].indices) {
            if (abs(this[i][j] - other[i][j]) > epsilon) {
                return false
            }
        }
    }
    return true
}

/**
 * Prints a dense matrix
 * @param maxDecimalPlaces the number of decimal places to print
 * @param startPadding the padding to apply to the start of each entry
 */
fun Array<DoubleArray>.print(maxDecimalPlaces: Int = 2, startPadding: Int = 6) {
    for (i in this.indices) {
        for (j in this[0].indices) {
            print(String.format(" %.${maxDecimalPlaces}f", this[i][j]).padStart(startPadding))
        }
        println()
    }
    println()
}
