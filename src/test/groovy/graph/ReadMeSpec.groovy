package graph

import graph.type.directed.DirectedGraphType
import spock.lang.Specification
import static Graph.graph

class ReadMeSpec extends Specification {

    def setup() {

    }

    def 'graph method'() {
        when:
        def graph = graph {
            edge step1, step2
        }
        then:
        graph.vertices.keySet() == ['step1', 'step2'] as Set //vertices were created!
        graph.edges.size() == 1
        graph.edges.first() == new Edge(one: 'step1', two: 'step2') //edge was created!
    }

    def 'usage 3'() {
        when:
        def graph = graph {
            type DirectedGraphType
            vertex A {
                connectsTo 'B', 'D', 'E'
                connectsFrom 'D'
            }

            vertex D {
                connectsTo 'C', 'E'
                connectsFrom 'B'
            }

            edge B, C
        }

        graph.depthFirstTraversal {
            root = 'A'
            preorder { vertex ->
                println "preorder $vertex.name"
            }
        }

        graph.breadthFirstTraversal {
            root = 'A'
            visit { vertex ->
                println "bft $vertex.name"
            }
        }

        then:
        true
    }

    def 'main readme example'() {
        when:
        Graph graph = Graph.graph {
            type DirectedGraphType
            vertex a {
                connectsTo 'b', 'd'
                connectsFrom 'd'
            }

            vertex d([connectsTo:'c']) {
                connectsTo 'e'
            }

            edge 'f', 'g'
            edge g, d

            println collectBfs { it.name }
        }

        then:
        graph != null
    }
}