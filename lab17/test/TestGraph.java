import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.*;

public class TestGraph {

    @Test
    public void testPath() {
        Graph g = new Graph(2);
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(0, 1)).isFalse();
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(1, 0)).isFalse();

        List<Integer> path1 = g.path(0, 1);
        List<Integer> path2 = g.path(1, 0);

        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path1.size() == 0);
        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path2.size() == 0);
    }

    @Test
    public void testPath1() {
        Graph g = new Graph(5);
        g.generateG1();

        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(0, 1)).isTrue();
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(1, 0)).isTrue();

        List<Integer> path1 = g.path(0, 1);
        List<Integer> path2 = g.path(1, 0);

        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path1.size()).isEqualTo(2);
        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path2.size()).isEqualTo(3);
    }

    @Test
    public void testPath2() {
        Graph g = new Graph(5);
        g.generateG2();

        g.topologicalSort();
        g.printTopologicalSort();
    }

    @Test
    public void testPath3() {
        Graph g = new Graph(7);
        g.generateG3();
        g.printTopologicalSort();
    }

    @Test
    public void testPath4() {
        Graph g = new Graph(5);
        g.generateG4();

        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(0, 1)).isTrue();
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(1, 0)).isTrue();

        List<Integer> path1 = g.path(0, 1);
        List<Integer> path2 = g.path(1, 0);

        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path1.size() == 0);
        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path2.size() == 0);
    }

    @Test
    public void testPaths() {
        Graph g = new Graph(7);
        g.generateG5();

        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(i == j) {
                    continue;
                }
                assertThat(g.pathExists(i, j)).isTrue();
                List<Integer> vertices = g.path(i, j);
                for(int k = 1; k < vertices.size(); k++) {
                    assertThat(g.isAdjacent(vertices.get(k-1), vertices.get(k))).isTrue();
                }
            }
        }
    }
}
