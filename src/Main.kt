import java.io.BufferedReader
import java.io.File
import kotlin.math.sqrt


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
    val matrix = convertToLaplacianMatrix(graph)
    // Note: for undirected graph, we can check if the Laplacian matrix is irreducible
    // by checking if the Laplacian[i][i] != 0 for all i
    val choleskyMatrix = choleskyFactorization(matrix)
    val recreatedMatrix = choleskyMatrix.multiply(choleskyMatrix.transpose())
    choleskyMatrix.print()
    matrix.print()
    recreatedMatrix.print()
    println(matrix.equals(recreatedMatrix, 0.0001))
}

/**
 * Reads an unweighted graph from a BufferedReader
 *
 * Expected format:
 * <number of vertices>
 * <vertex 1 of edge 1> <vertex 2 of edge 1>
 * <vertex 1 of edge 2> <vertex 2 of edge 2>
 * ...
 *
 * @param bufferedReader the BufferedReader to read from
 * @return the UnweightedGraph read from the BufferedReader
 */
fun readUnweightedGraph(bufferedReader: BufferedReader): UnweightedGraph {
    val vertices = bufferedReader.readLine().toInt()
    val edges = mutableMapOf<Int, MutableSet<Int>>()
    bufferedReader.forEachLine { line ->
        val (vertex1, vertex2) = line.split(" ").map { it.toInt() }
        edges.getOrPut(vertex1) { mutableSetOf() }.add(vertex2)
        edges.getOrPut(vertex2) { mutableSetOf() }.add(vertex1)
    }

    return UnweightedGraph(vertices, edges)
}

// TODO: Use sparse matrix representation
fun convertToLaplacianMatrix(graph: UnweightedGraph): SquareMatrix {
    val matrix = Array(graph.vertices) { DoubleArray(graph.vertices) }
    for (fromVertex in graph.edges.keys) {
        for (toVertex in graph.edges[fromVertex] ?: emptyList()) {
            matrix[fromVertex][toVertex] = -1.0
        }
        matrix[fromVertex][fromVertex] = graph.edges[fromVertex]?.size?.toDouble() ?: 0.0
    }
    return SquareMatrix(matrix)

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
