import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point3d;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class practica12112024 extends JPanel {
    private SimpleUniverse universo;

    public practica12112024(){
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3d = new Canvas3D(config);
    setLayout (new BorderLayout () ) ;
    add (canvas3d);

    universo = new SimpleUniverse(canvas3d);
    universo.getViewingPlatform().setNominalViewingTransform();

    BranchGroup escena = crearGrafoEscena();
    escena.compile ();   
    universo.addBranchGraph(escena);
}

public BranchGroup crearGrafoEscena(){
    BranchGroup objetoRaiz = new BranchGroup();
    TransformGroup tecladoGroup = new TransformGroup();
    objetoRaiz.addChild(tecladoGroup);
    ColorCube cubo = new ColorCube(0.3);
    tecladoGroup.addChild(cubo);
    KeyNavigatorBehavior knb = new KeyNavigatorBehavior(
        universo.getViewingPlatform().getViewPlatformTransform()
    );
    BoundingSphere bs = new BoundingSphere(new Point3d(), 1000.0);
    knb.setSchedulingBounds(bs);
    tecladoGroup.addChild(knb);
    return objetoRaiz;

}
    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame("Rotacion con Teclado");
        practica12112024 panel = new practica12112024();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}