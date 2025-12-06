// TODO: make graph have directed edges
/**
 * Graph is a directed graph with vertices and edges
 * vertices are 0-indexed
 *
 * @param vertices number of vertices
 * @param edges map from-vertex -> (to-vertex -> weight of edge)
 */
data class Graph(
    val vertices: Int,
    val edges: Map<Int, Map<Int, Double>>
)

fun Graph.isStronglyConnected(): Boolean {
    val reached = mutableSetOf<Int>()
    val stack = ArrayDeque<Int>().apply { add(0) }

    while (stack.isNotEmpty()) {
        val vertex = stack.removeFirst()
        if (!reached.add(vertex)) {
            continue
        }

        for (toVertex in edges.getOrDefault(vertex, emptyMap()).keys) {
            stack.add(toVertex)
        }
    }
    return reached.size == vertices
}