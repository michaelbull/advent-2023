package com.github.michaelbull.advent2023.day25

import org.jgrapht.Graph
import org.jgrapht.alg.StoerWagnerMinimumCut
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph

fun Sequence<String>.toWiringDiagram(): WiringDiagram {
    return WiringDiagram(map(String::toConnection).toList())
}

data class WiringDiagram(
    val connections: List<Connection>,
) {

    private val vertices = connections.map(Connection::name) + connections.flatMap(Connection::connections)

    private val edges = connections.flatMap { (from, connections) ->
        connections.map { to -> Pair(from, to) }
    }

    fun disconnectedGroups(): Pair<Set<String>, Set<String>> {
        val graph = toGraph()
        val minCut = StoerWagnerMinimumCut(graph).minCut()
        val vertices = graph.vertexSet()

        graph.removeAllVertices(minCut)

        return vertices to minCut
    }

    private fun toGraph() = graph(::DefaultWeightedEdge) {
        vertices.forEach(::addVertex)
        edges.forEach(::addEdge)
    }
}

private fun <V, E> Graph<V, E>.addEdge(vertices: Pair<V, V>): E {
    return addEdge(vertices.first, vertices.second)
}

private inline fun <V, E> graph(
    noinline edgeSupplier: () -> E,
    block: Graph<V, E>.() -> Unit,
): SimpleWeightedGraph<V, E> {
    return SimpleWeightedGraph<V, E>(null, edgeSupplier).apply(block)
}
