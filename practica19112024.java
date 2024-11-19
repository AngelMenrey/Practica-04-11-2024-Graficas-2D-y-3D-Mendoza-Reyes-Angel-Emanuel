import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class practica19112024 extends JPanel {

    SimpleUniverse universo;

    public practica19112024() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas3D);

        universo = new SimpleUniverse(canvas3D);
        universo.getViewingPlatform().setNominalViewingTransform();

        BranchGroup escena = crearGrafoEscena();
        escena.compile();

        universo.addBranchGraph(escena);
    }

    public BranchGroup crearGrafoEscena() {
        BranchGroup objetoRaiz = new BranchGroup();

        Appearance apariencia = new Appearance();
        Material material = new Material();
        material.setAmbientColor(new Color3f(Color.DARK_GRAY));
        material.setDiffuseColor(new Color3f(Color.red));
        material.setSpecularColor(new Color3f(Color.white));
        material.setShininess(20.0f);
        apariencia.setMaterial(material);

        Appearance apariencia2 = new Appearance();
        Material material2 = new Material();
        material2.setAmbientColor(new Color3f(Color.DARK_GRAY));
        material2.setDiffuseColor(new Color3f(Color.GREEN));
        material2.setSpecularColor(new Color3f(Color.white));
        material2.setShininess(20.0f);
        apariencia2.setMaterial(material2);

        Cylinder cilindro = new Cylinder(0.3f, 1.0f, Cylinder.GENERATE_NORMALS, apariencia);

        Sphere esfere = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 100, apariencia2);

        Color3f colorAmbiente = new Color3f(Color.DARK_GRAY);

        AmbientLight luzAmbiente = new AmbientLight(colorAmbiente);
        luzAmbiente.setInfluencingBounds(new BoundingSphere(new Point3d(0, 0, 0), 100));

        Color3f colorLuz = new Color3f(Color.white);
        Vector3f dirLuz = new Vector3f(-1.0f, -1.0f, -4.0f);
        DirectionalLight luz = new DirectionalLight(colorLuz, dirLuz);
        luz.setInfluencingBounds(new BoundingSphere(new Point3d(0, 0, 0), 100));

        objetoRaiz.addChild(esfere);
        objetoRaiz.addChild(cilindro);
        objetoRaiz.addChild(luzAmbiente);
        objetoRaiz.addChild(luz);

        return objetoRaiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame("Luces y sombras de objetos");
        practica19112024 panel = new practica19112024();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}