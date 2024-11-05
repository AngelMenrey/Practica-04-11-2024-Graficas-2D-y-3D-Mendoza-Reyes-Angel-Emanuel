import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.swing.JPanel;
import javax.vecmath.Color3f;

public class practica05112024 extends JPanel {
    public practica05112024() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3d = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas3d, BorderLayout.CENTER);
        SimpleUniverse universo = new SimpleUniverse(canvas3d);
        universo.getViewingPlatform().setNominalViewingTransform();
        BranchGroup escena = crearGrafoEscena();
        escena.compile();
        universo.addBranchGraph(escena);
    }
    
    public BranchGroup crearGrafoEscena() {
        BranchGroup objetoRaiz = new BranchGroup();
        
        Appearance apariencia = new Appearance();
        Color3f colorAzul = new Color3f(Color.BLUE);
        ColoringAttributes atributo = new ColoringAttributes();
        atributo.setColor(colorAzul);
        apariencia.setColoringAttributes(atributo);
        
        Box cubo = new Box(0.4f, 0.4f, 0.4f, apariencia);
        objetoRaiz.addChild(cubo);
        
        Color3f luzColor = new Color3f(1.0f, 1.0f, 1.0f);
        BoundingSphere bounds = new BoundingSphere();
        DirectionalLight luzDireccional = new DirectionalLight(luzColor, new javax.vecmath.Vector3f(-1.0f, -1.0f, -1.0f));
        luzDireccional.setInfluencingBounds(bounds);
        objetoRaiz.addChild(luzDireccional);
        
        return objetoRaiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame("Figuras Geometricas 3D");
        practica05112024 panel = new practica05112024();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}