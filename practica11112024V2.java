import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.geometry.ColorCube;  
import java.awt.BorderLayout;

public class practica11112024V2 extends JPanel{
    SimpleUniverse universo;


public practica11112024V2(){
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3d = new Canvas3D(config);
    setLayout(new BorderLayout());
    add(canvas3d);

    universo = new SimpleUniverse(canvas3d);
    universo.getViewingPlatform().setNominalViewingTransform();
    BranchGroup escena  = crearGrafoEscena();
    escena.compile();
    universo.addBranchGraph(escena);
}

public BranchGroup crearGrafoEscena(){
    BranchGroup objetoRaiz = new BranchGroup();

    TransformGroup mouseGroup = new TransformGroup();
    mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    objetoRaiz.addChild(mouseGroup);
    ColorCube cubo = new ColorCube(0.4f);
    mouseGroup.addChild(cubo);

    MouseRotate mr = new MouseRotate();
    mr.setTransformGroup(mouseGroup);
    mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
    objetoRaiz.addChild(mr);
    return objetoRaiz;
}
    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame("Eventos del Mouse");
        practica11112024V2 panel = new practica11112024V2();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
