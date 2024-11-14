import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class practica14112024 extends JPanel {
    private SimpleUniverse universo;

    public practica14112024(){
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
    BranchGroup objetoRaiz = new BranchGroup ();

    TransformGroup mouseGroup = new TransformGroup();
    mouseGroup.setCapability (TransformGroup.ALLOW_TRANSFORM_READ) ;
    mouseGroup.setCapability (TransformGroup.ALLOW_TRANSFORM_WRITE) ;
    objetoRaiz.addChild(mouseGroup);

    Transform3D traslacion1 = new Transform3D() ;
    traslacion1.set (new Vector3f(-0.4f, 0f, 0));
    Transform3D traslacion2 = new Transform3D();
    traslacion2.set (new Vector3f(0.4f, 0.4f, -1f));

    TransformGroup tg1 = new TransformGroup (traslacion1);  
    TransformGroup tg2 = new TransformGroup (traslacion2);

    ColorCube cubo1 = new ColorCube(0.2f);
    ColorCube cubo2 = new ColorCube(0.2f);

    tg1.addChild(cubo1);
    tg2.addChild(cubo2);

    mouseGroup.addChild(tg1);
    mouseGroup.addChild(tg2);

    MouseRotate mr = new MouseRotate () ;

    mr.setTransformGroup (mouseGroup);
    mr.setSchedulingBounds (new BoundingSphere(new Point3d(), 1000f));
    objetoRaiz.addChild(mr);

    return objetoRaiz;
}
    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame();
        practica14112024 panel = new practica14112024();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}