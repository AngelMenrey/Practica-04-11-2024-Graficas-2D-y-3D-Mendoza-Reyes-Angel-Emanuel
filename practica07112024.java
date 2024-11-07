import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class practica07112024 extends JFrame {
    public practica07112024() {
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
        BranchGroup objectoRaiz = new BranchGroup();
        TransformGroup objectoGiro = new TransformGroup();
        objectoGiro.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objectoRaiz.addChild(objectoGiro);
        ColorCube cubo = new ColorCube(0.2f);
        objectoGiro.addChild(cubo);
        Alpha rotacionAlpha = new Alpha(-1, 4000);
        RotationInterpolator rotacion = new RotationInterpolator(rotacionAlpha, objectoGiro);
        rotacion.setSchedulingBounds(new BoundingSphere());
        objectoRaiz.addChild(rotacion);
        return objectoRaiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        practica07112024 panel = new practica07112024();
        panel.setSize(700, 700);
        panel.setVisible(true);
        panel.setLocationRelativeTo(null);
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}