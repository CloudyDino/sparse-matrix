// TODO: make graph have directed edges
// TODO: make graph edges have weights
/**
 * Graph is a directed graph with vertices and edges
 * vertices are 0-indexed
 *
 * @param vertices number of vertices
 * @param edges map of edges where key is from and value is a list of to vertices
 */
data class UnweightedGraph(
    val vertices: Int,
    val edges: Map<Int, Set<Int>>
)

fun UnweightedGraph.isStronglyConnected(): Boolean {
    val reached = mutableSetOf<Int>()
    val stack = ArrayDeque<Int>().apply { add(0) }

    while (stack.isNotEmpty()) {
        val vertex = stack.removeFirst()
        if (!reached.add(vertex)) {
            continue
        }

        for (toVertex in edges[vertex] ?: emptyList()) {
            stack.add(toVertex)
        }
    }
    return reached.size == vertices
}