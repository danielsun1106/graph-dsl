package graph

import graph.type.directed.DirectedGraphType
import spock.lang.Specification

import static Graph.graph
import static graph.TraversalState.CONTINUE

class ReadMeSpec extends Specification {

    def 'usage 3'() {
        when:
        def graph = graph {
            type DirectedGraphType
            vertex('A') {
                connectsTo 'B', 'D', 'E'
                connectsFrom 'D'
            }

            vertex('D') {
                connectsTo 'C', 'E'
                connectsFrom 'B'
            }

            edge 'B', 'C'
        }

        graph.preOrder('A') { vertex ->
            println "preorder $vertex.getId"
            CONTINUE
        }

        graph.breadthFirstTraversal('A') { vertex ->
            println "bft $vertex.getId"
            CONTINUE
        }

        then:
        true
    }

    def 'graphviz readme'() {
        given:
        Graph graph = graph {
            type 'directed-graph'
            plugin 'graphviz'
            vertex('A') {
                connectsTo('B') {
                    connectsTo 'C', 'D'
                }
                connectsTo('D') {
                    connectsTo 'C'
                    connectsTo 'E'
                }
                connectsFrom 'D'
            }
            vertex 'F', [connectsTo: 'G']
            edge 'G', 'D'
        }

        expect:
        true
    }
}
