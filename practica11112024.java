import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import java.awt.GraphicsConfiguration;
import java.awt.Panel;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import javax.swing.JFrame;
import com.sun.j3d.utils.geometry.ColorCube;  
import java.awt.BorderLayout;
import javax.media.j3d.*;

public class practica11112024 extends Panel {
    
    public practica11112024() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3d = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas3d);
        SimpleUniverse universo = new SimpleUniverse(canvas3d);
        Vector3f posicionVista = new Vector3f(2f, 5f, 20f);
        Transform3D transformVista = new Transform3D();
        transformVista.setTranslation(posicionVista);
        Transform3D rotacion = new Transform3D();
        rotacion.rotX(Math.toRadians(45));
        rotacion.mul(transformVista);
        universo.getViewingPlatform().getViewPlatformTransform().setTransform(rotacion);
        universo.getViewingPlatform().getViewPlatformTransform().getTransform(transformVista);
        BranchGroup escena = crearGrafoEscena();
        escena.compile();
        universo.addBranchGraph(escena);
    }
    public BranchGroup crearGrafoEscena(){
        BranchGroup objetoRaiz = new BranchGroup();
        TransformGroup objetoGiro = new TransformGroup();
        objetoGiro.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objetoRaiz.addChild(objetoGiro);
        ColorCube cubo = new ColorCube(0.8f);
        objetoRaiz.addChild(cubo);
        Alpha rotacionAlpha = new Alpha(-1, 4000);
        RotationInterpolator rotacion = new RotationInterpolator(rotacionAlpha, objetoGiro);
        rotacion.setSchedulingBounds(new BoundingSphere());
        return objetoRaiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame();
        practica11112024 panel = new practica11112024();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
