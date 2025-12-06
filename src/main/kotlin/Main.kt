import java.io.File
import kotlin.math.sqrt

const val EPSILON = 0.0001
fun main() {
    val file = File("./sampleMatricies/simpleRing.txt")
    val graph = readUnweightedGraph(file.bufferedReader())
    val isStronglyConnected = graph.isStronglyConnected()
    if (!isStronglyConnected) {
        println(
            "We can't compute the Cholesky factorization of the Laplacian matrix " +
                    "since the graph is not strongly connected"
        )
        return
    }
    val matrix = convertToDenseLaplacianMatrix(graph)
    // Note: for undirected graph, we can check if the Laplacian matrix is irreducible
    // by checking if the Laplacian[i][i] != 0 for all i
    val choleskyMatrix = choleskyFactorization(matrix)
    val recreatedMatrix = choleskyMatrix.multiply(choleskyMatrix.transpose())
    choleskyMatrix.print()
    matrix.print()
    recreatedMatrix.print()
    println(matrix.equals(recreatedMatrix, EPSILON))
}

fun choleskyFactorization(matrix: SquareMatrix): SquareMatrix {
    val n = matrix.data.size
    var currentMatrix = matrix
    val choleskyMatrix = Array(n) { DoubleArray(n) }
    for (k in 0 until currentMatrix.data.size - 1) {
        val vertexDegree = currentMatrix.data[k][k]
        val root = sqrt(vertexDegree)
        for (i in currentMatrix.data.indices) {
            choleskyMatrix[i][k] = currentMatrix.data[i][k] / root
        }
        val denseSchur = Array(n) { i ->
            DoubleArray(n) { j ->
                currentMatrix.data[i][k] * currentMatrix.data[k][j] / vertexDegree
            }
        }
        currentMatrix = currentMatrix.subtract(SquareMatrix(denseSchur))
    }
    return SquareMatrix(choleskyMatrix)
}
