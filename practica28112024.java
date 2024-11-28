import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Box;

public class practica28112024 extends JPanel {

    public practica28112024() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3d = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas3d);
        SimpleUniverse universo = new SimpleUniverse(canvas3d);
        universo.getViewingPlatform().setNominalViewingTransform();
        BranchGroup escena = crearGrafoEscena();
        escena.compile();
        universo.addBranchGraph(escena);
    }

    public BranchGroup crearGrafoEscena() {
        BranchGroup objetoRaiz = new BranchGroup();
        Appearance apariencia = new Appearance();
        ColoringAttributes color = new ColoringAttributes();
        color.setColor(new Color3f(Color.RED));
        color.setShadeModel(ColoringAttributes.NICEST);
        apariencia.setColoringAttributes(color);

        Font3D fuente3d = new Font3D(new Font("Courier", Font.PLAIN, 1), new FontExtrusion());
        Text3D texto3d = new Text3D(fuente3d, "Texto 3D", new Point3f(0f, 0f, 0.5f));
        texto3d.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D forma3d = new Shape3D();
        forma3d.setGeometry(texto3d);

        Transform3D escala3d = new Transform3D();
        escala3d.setScale(0.3f);
        TransformGroup escala = new TransformGroup(escala3d);

        TransformGroup mouseGroup = new TransformGroup();
        mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objetoRaiz.addChild(mouseGroup);

        mouseGroup.addChild(escala);
        escala.addChild(forma3d);

        MouseRotate mr = new MouseRotate();
        mr.setTransformGroup(mouseGroup);
        mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
        objetoRaiz.addChild(mr);

        return objetoRaiz;
    }

    public BranchGroup DcrearGrafoEscena() {
        BranchGroup objetoRaiz = new BranchGroup();
        Appearance apariencia = new Appearance();
        Color3f blue = new Color3f(Color.BLUE);
        ColoringAttributes atributo = new ColoringAttributes();
        atributo.setColor(blue);
        apariencia.setColoringAttributes(atributo);

        Box cubo = new Box(0.2f, 0.2f, 0.2f, apariencia);

        objetoRaiz.addChild(cubo);

        return objetoRaiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame("Texto 3D");
        practica28112024 panel = new practica28112024();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}