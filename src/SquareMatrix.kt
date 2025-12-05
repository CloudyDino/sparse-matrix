import kotlin.math.abs

@JvmInline
value class SquareMatrix(val data: Array<DoubleArray>) {
    init {
        require(data.all { it.size == data.size })
    }
}

fun SquareMatrix.transpose(): SquareMatrix {
    val transposed = Array(data.size) { DoubleArray(data.size) }
    for (i in data.indices) {
        for (j in data.indices) {
            transposed[i][j] = data[j][i]
        }
    }
    return SquareMatrix(transposed)
}

fun SquareMatrix.multiply(other: SquareMatrix): SquareMatrix {
    val product = Array(data.size) { DoubleArray(data.size) }
    for (i in data.indices) {
        for (j in data.indices) {
            for (k in data.indices) {
                product[i][j] += data[i][k] * other.data[k][j]
            }
        }
    }
    return SquareMatrix(product)
}

fun SquareMatrix.add(other: SquareMatrix): SquareMatrix {
    val sum = Array(data.size) { DoubleArray(data.size) }
    for (i in data.indices) {
        for (j in data.indices) {
            sum[i][j] = data[i][j] + other.data[i][j]
        }
    }
    return SquareMatrix(sum)
}

fun SquareMatrix.subtract(other: SquareMatrix): SquareMatrix {
    val difference = Array(data.size) { DoubleArray(data.size) }
    for (i in data.indices) {
        for (j in data.indices) {
            difference[i][j] = data[i][j] - other.data[i][j]
        }
    }
    return SquareMatrix(difference)
}

fun SquareMatrix.equals(other: SquareMatrix, epsilon: Double): Boolean {
    for (i in data.indices) {
        for (j in data.indices) {
            if (abs(data[i][j] - other.data[i][j]) > epsilon) {
                return false
            }
        }
    }
    return true
}

fun SquareMatrix.print() {
    for (i in data.indices) {
        for (j in data.indices) {
            print(String.format(" %.2f", data[i][j]).padStart(6))
        }
        println()
    }
    println()
}