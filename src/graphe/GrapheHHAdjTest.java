package graphe;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrapheHHAdjTest {
    private GrapheHHAdj grapheString;

    @BeforeEach
    void setUp() {
        grapheString = new GrapheHHAdj();
    }

    @Test
    @DisplayName("Test ajout de sommets")
    void testAjouterSommet() {
        // Test avec des String
        grapheString.ajouterSommet("A");
        grapheString.ajouterSommet("B");

        // Vérifier que les sommets existent en vérifiant getSucc
        assertNotNull(grapheString.getSucc("A"));
        assertNotNull(grapheString.getSucc("B"));
        assertTrue(grapheString.getSucc("A").isEmpty());
        assertTrue(grapheString.getSucc("B").isEmpty());
    }

    @Test
    @DisplayName("Test ajout d'arcs")
    void testAjouterArc() {
        // Ajouter des arcs
        grapheString.ajouterArc("A", "B", 5);
        grapheString.ajouterArc("A", "C", 10);
        grapheString.ajouterArc("B", "C", 3);

        // Vérifier les successeurs de A
        List<Graph.graph.Arc<String>> succA = grapheString.getSucc("A");
        assertEquals(2, succA.size());

        // Vérifier les valeurs et destinations
        boolean foundB = false;
        boolean foundC = false;
        for (Graph.graph.Arc<String> arc : succA) {
            if (arc.dst().equals("B") && arc.val() == 5) foundB = true;
            if (arc.dst().equals("C") && arc.val() == 10) foundC = true;
        }
        assertTrue(foundB, "Arc A->B(5) non trouvé");
        assertTrue(foundC, "Arc A->C(10) non trouvé");

        // Vérifier les successeurs de B
        List<Graph.graph.Arc<String>> succB = grapheString.getSucc("B");
        assertEquals(1, succB.size());
        assertEquals("C", succB.get(0).dst());
        assertEquals(3, succB.get(0).val());
    }

    @Test
    @DisplayName("Test getSucc avec sommet inexistant")
    void testGetSuccSommetInexistant() {
        grapheString.ajouterArc("A", "B", 5);

        // Un sommet qui n'existe pas doit retourner une liste vide
        List<Graph.graph.Arc<String>> succZ = grapheString.getSucc("Z");
        assertNotNull(succZ);
        assertTrue(succZ.isEmpty());
    }

    @Test
    @DisplayName("Test ajout de sommets déjà existants")
    void testAjouterSommetExistant() {
        grapheString.ajouterSommet("A");
        grapheString.ajouterArc("A", "B", 5);

        // Ajouter à nouveau le sommet A ne doit pas effacer ses arcs
        grapheString.ajouterSommet("A");

        List<Graph.graph.Arc<String>> succA = grapheString.getSucc("A");
        assertEquals(1, succA.size());
        assertEquals("B", succA.get(0).dst());
    }

    @Test
    @DisplayName("Test arcs multiples entre mêmes sommets")
    void testArcsMultiples() {
        // Ajouter plusieurs arcs entre les mêmes sommets
        grapheString.ajouterArc("A", "B", 5);
        grapheString.ajouterArc("A", "B", 10);

        List<Graph.graph.Arc<String>> succA = grapheString.getSucc("A");
        assertEquals(2, succA.size());

        // Les deux arcs doivent exister
        boolean found5 = false;
        boolean found10 = false;
        for (Graph.graph.Arc<String> arc : succA) {
            if (arc.dst().equals("B")) {
                if (arc.val() == 5) found5 = true;
                if (arc.val() == 10) found10 = true;
            }
        }
        assertTrue(found5 && found10);
    }

    @Test
    @DisplayName("Test boucle sur un sommet")
    void testBoucle() {
        grapheString.ajouterArc("A", "A", 5);

        List<Graph.graph.Arc<String>> succA = grapheString.getSucc("A");
        assertEquals(1, succA.size());
        assertEquals("A", succA.get(0).dst());
        assertEquals(5, succA.get(0).val());
    }

    @Test
    @DisplayName("Test toString avec graphe vide")
    void testToStringGrapheVide() {
        String result = grapheString.toString();
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test toString avec graphe simple")
    void testToStringGrapheSimple() {
        grapheString.ajouterArc("A", "B", 5);
        grapheString.ajouterArc("B", "C", 3);
        grapheString.ajouterSommet("D"); // Sommet isolé

        String result = grapheString.toString();

        // Vérifier que chaque ligne est présente (l'ordre peut varier)
        assertTrue(result.contains("A-B(5)"));
        assertTrue(result.contains("B-C(3)"));
        assertTrue(result.contains("D-"));
    }

    @Test
    @DisplayName("Test graphe complet (exemple plus complexe)")
    void testGrapheComplet() {
        // Créer un graphe complet K3
        String[] sommets = {"A", "B", "C"};

        for (String s1 : sommets) {
            for (String s2 : sommets) {
                if (!s1.equals(s2)) {
                    grapheString.ajouterArc(s1, s2, 1);
                }
            }
        }

        // Chaque sommet doit avoir 2 successeurs
        for (String s : sommets) {
            assertEquals(2, grapheString.getSucc(s).size());
        }
    }

    @Test
    @DisplayName("Test vérification d'existence de sommet")
    void testVerifierExistenceSommet() {
        grapheString.ajouterSommet("A");

        assertTrue(grapheString.verifierExistenceSommet("A"));
        assertFalse(grapheString.verifierExistenceSommet("B"));

        grapheString.ajouterArc("A", "B", 5);

        assertTrue(grapheString.verifierExistenceSommet("B"));
    }
}