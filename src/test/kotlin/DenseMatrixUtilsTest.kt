import kotlin.test.Test

class DenseMatrixUtilsTest {
    @Test
    fun transposed() {
        val matrix = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        val expectedTranspose = arrayOf(
            doubleArrayOf(1.0, 4.0),
            doubleArrayOf(2.0, 5.0),
            doubleArrayOf(3.0, 6.0)
        )
        assert(matrix.transposed().equals(expectedTranspose, 0.0))
        assert(matrix.equals(matrix.transposed().transposed(), 0.0))
    }

    @Test
    fun multiply() {
        val matrix = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        val expected = arrayOf(
            doubleArrayOf(14.0, 32.0),
            doubleArrayOf(32.0, 77.0)
        )
        assert(matrix.multiply(matrix.transposed()).equals(expected, 0.0))
    }

}
