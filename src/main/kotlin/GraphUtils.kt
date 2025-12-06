import java.io.BufferedReader

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
fun readUnweightedGraph(bufferedReader: BufferedReader): Graph {
    val vertices = bufferedReader.readLine().toInt()
    val edges = mutableMapOf<Int, MutableMap<Int, Double>>()
    bufferedReader.forEachLine { line ->
        val (vertex1, vertex2) = line.split(" ").map { it.toInt() }
        edges.getOrPut(vertex1) { mutableMapOf() }[vertex2] = 1.0
        edges.getOrPut(vertex2) { mutableMapOf() }[vertex1] = 1.0
    }

    return Graph(vertices, edges)
}

// TODO: Use sparse matrix representation
fun convertToDenseLaplacianMatrix(graph: Graph): SquareMatrix {
    val matrix = Array(graph.vertices) { DoubleArray(graph.vertices) }
    for (fromVertex in graph.edges.keys) {
        var weightSum = 0.0
        graph.edges[fromVertex]?.forEach { (toVertex, weight) ->
            matrix[fromVertex][toVertex] = -1 * weight
            weightSum += weight
        }
        matrix[fromVertex][fromVertex] = weightSum
    }
    return SquareMatrix(matrix)

}
