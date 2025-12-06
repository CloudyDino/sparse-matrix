@JvmInline
value class SquareMatrix(val data: Array<DoubleArray>) {
    init {
        require(data.all { it.size == data.size })
    }
}

fun SquareMatrix.transpose(): SquareMatrix {
    return SquareMatrix(this.data.transposed())
}

fun SquareMatrix.multiply(other: SquareMatrix): SquareMatrix {
    return SquareMatrix(this.data.multiply(other.data))
}

fun SquareMatrix.add(other: SquareMatrix): SquareMatrix {
    return SquareMatrix(this.data.add(other.data))
}

fun SquareMatrix.subtract(other: SquareMatrix): SquareMatrix {
    return SquareMatrix(this.data.subtract(other.data))
}

fun SquareMatrix.equals(other: SquareMatrix, epsilon: Double): Boolean {
    return this.data.equals(other.data, epsilon)
}

fun SquareMatrix.print() {
    this.data.print()
}