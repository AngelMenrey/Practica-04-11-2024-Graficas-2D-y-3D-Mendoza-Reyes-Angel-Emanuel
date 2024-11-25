import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Box;

public class practica25112024 extends JPanel {

    public practica25112024() {
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

        Background fondo = new Background(new Color3f(Color.blue));
        fondo.setApplicationBounds(new BoundingSphere());
        objetoRaiz.addChild(fondo);

        TransformGroup mouseGroup = new TransformGroup();
        mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objetoRaiz.addChild(mouseGroup);

        Sphere esfera = new Sphere(0.3f, app());
        mouseGroup.addChild(esfera);

        MouseRotate mr = new MouseRotate();
        mr.setTransformGroup(mouseGroup);
        mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
        objetoRaiz.addChild(mr);

        return objetoRaiz;
    }

    Appearance app() {
        Appearance apariencia = new Appearance();

        TexCoordGeneration texCoord = new TexCoordGeneration(
            TexCoordGeneration.OBJECT_LINEAR,
            TexCoordGeneration.TEXTURE_COORDINATE_2
        );
        apariencia.setTexCoordGeneration(texCoord);

        String ladrillo = "ladrillo.jpg";

        TextureLoader loader = new TextureLoader(ladrillo, this);
        ImageComponent2D imagen = loader.getImage();

        Texture2D textura = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
            imagen.getWidth(), imagen.getHeight());
        textura.setImage(0, imagen);
        textura.setEnable(true);

        textura.setMagFilter(Texture.BASE_LEVEL_LINEAR);
        textura.setMinFilter(Texture.BASE_LEVEL_LINEAR);

        apariencia.setTexture(textura);
        apariencia.setTextureAttributes(new TextureAttributes());

        return apariencia;
    }

    public BranchGroup DcrearGrafoEscena() {
        BranchGroup objetoRaiz = new BranchGroup();
        Appearance apariencia = new Appearance();
        Color3f rojo = new Color3f(Color.BLUE);
        ColoringAttributes atributo = new ColoringAttributes();
        atributo.setColor(rojo);
        apariencia.setColoringAttributes(atributo);

        Box cubo = new Box(0.2f, 0.2f, 0.2f, apariencia);

        objetoRaiz.addChild(cubo);

        return objetoRaiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame("Figuras Geometricas 3D texturas");
        practica25112024 panel = new practica25112024();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}