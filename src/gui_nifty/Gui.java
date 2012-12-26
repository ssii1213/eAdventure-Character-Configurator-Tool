package gui_nifty;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import imagesProcessing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tipos.*;
import window_color.*;

public class Gui extends SimpleApplication{

    private StartScreen startScreen;
    private ScreenshotAppState screenShotState;
    private NiftyJmeDisplay niftyDisplay;
    
    private AnimChannel channel;
    private AnimControl control;
    private Spatial model;
    private Material mat;
    private ImagesProcessing img;
    private String destinationPath;
    private BufferedImage skin, trousers, tShirt, eyes, shoes;
    
    private int indexCaptura = 0;
    private int indexImage = 0;
    
    String [] arraySkin, arrayShoesColor, arrayShoesShadow, arrayTrouserColor, arrayTrouserShadow, arrayTShirtColor, arrayTShirtShadow, arrayEyes;
    private int indexSkin = -1;
    private int indexTShirt = -1;
    private int indexShoes = -1;
    private int indexTrouser = -1;
    private int indexEyes = -1;
    private int numSkins;
    private int numShoes;
    private int numTrousers;
    private int numTShirts;
    private int numEyes;
    
    private Gender gender;
    private Age age;
    private TypeObject typeObject;
    
    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setResolution(800, 600);
        Gui app = new Gui();
        app.setShowSettings(false);
        app.setSettings(settings);
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        
        //CHICO
        //Andar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Andar/polySurfaceShape4.mesh.xml");
        //Coger
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Coger/polySurfaceShape4.mesh.xml");
        //Hablar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Hablar/polySurfaceShape4.mesh.xml");
        //Parado
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Parado/polySurfaceShape4.mesh.xml");

        //CHICA
        //Andar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Andar/polySurfaceShape12.mesh.xml");
        //Coger
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Coger/polySurfaceShape12.mesh.xml");
        //Hablar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Hablar/polySurfaceShape12.mesh.xml");
        //Parada
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Parada/polySurfaceShape12.mesh.xml");

        //Animacion cambiada con blender
        /*model = assetManager.loadModel("Models/prueba/polySurfaceShape4.mesh.xml");
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",assetManager.loadTexture("Textures/OriginalTexture.png"));  
        model.setMaterial(mat);*/
        
        startScreen = new StartScreen(this);
        stateManager.attach(startScreen);
        
        screenShotState = new ScreenshotAppState();
        stateManager.attach(screenShotState);
        
        /**
        * Åctivate the Nifty-JME integration: 
        */
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);
        nifty.fromXml("Interface/screen.xml", "start", startScreen);
        //nifty.setDebugOptionPanelColors(true);

        flyCam.setDragToRotate(true); // you need the mouse for clicking now
    }
    
     public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) 
    {
        
    }
 
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) 
    {
    }
    
    public void changeSkin()
    {
        indexSkin++;
        skin = readBuffer(arraySkin[indexSkin%numSkins]);
        //Creat the image             
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);                 
        indexImage++;                
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";                
        img.fusionaImagenes(destinationPath);                
        //Set the texture to the material                
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));                  
        model.setMaterial(mat);                
        //Delete the file                
        Path file = Paths.get(destinationPath);                
        try {                   
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }            
    }
    
    public void changeShoes()
    {
        indexShoes++;   
        shoes = readBuffer(arrayShoesColor[indexShoes%numShoes], arrayShoesShadow[indexShoes%numShoes], TypeObject.shoes);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);                  
        indexImage++;                
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";                
        img.fusionaImagenes(destinationPath);                
        //Set the texture to the material               
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));                
        model.setMaterial(mat);               
        //Delete the file               
        Path file = Paths.get(destinationPath);               
        try {                    
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void changeTrousers()
    {
        indexTrouser++;   
        trousers = readBuffer(arrayTrouserColor[indexTrouser%numTrousers], arrayTrouserShadow[indexTrouser%numTrousers], TypeObject.trouser);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);           
        indexImage++;        
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
        //Set the texture to the material        
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));        
        model.setMaterial(mat);        
        //Delete the file        
        Path file = Paths.get(destinationPath);        
        try {        
            Files.delete(file);            
        } catch (IOException ex) {        
            System.out.println("Failed deleting file");            
        }
    }
    
    public void changeTShirt()
    {
        indexTShirt++;
        tShirt = readBuffer(arrayTShirtColor[indexTShirt%numTShirts], arrayTShirtShadow[indexTShirt%numTShirts], TypeObject.t_shirt);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);        
        indexImage++;        
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
        //Set the texture to the material        
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));          
        model.setMaterial(mat);        
        //Delete the file        
        Path file = Paths.get(destinationPath);        
        try {        
            Files.delete(file);            
        } catch (IOException ex) {        
            System.out.println("Failed deleting file");            
        }
    }
    
    public void changeEyes()
    {
        indexEyes++; 
        eyes = readBuffer(arrayEyes[indexEyes%numEyes]);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);      
        indexImage++;        
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
        //Set the texture to the material        
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));          
        model.setMaterial(mat);        
        //Delete the file        
        Path file = Paths.get(destinationPath);        
        try {        
            Files.delete(file);            
        } catch (IOException ex) {        
            System.out.println("Failed deleting file");            
        }        
    }
    
    public void changeColor(int color) throws IOException{
        ColoringImage coloringImage;
        Path file;
        BufferedImage coloredImage;
        switch(typeObject) {
            case t_shirt:
                coloringImage = new  ColoringImage(arrayTShirtColor[indexTShirt%numTShirts], arrayTShirtShadow[indexTShirt%numTShirts], color, TypeObject.t_shirt); 
                tShirt = coloringImage.coloringImage();      
                break;
            case trouser:
                coloringImage = new  ColoringImage(arrayTrouserColor[indexTrouser%numTrousers], arrayTrouserShadow[indexTrouser%numTrousers], color, TypeObject.trouser);
                trousers = coloringImage.coloringImage(); 
                break;      
            case shoes:
                coloringImage = new  ColoringImage(arrayShoesColor[indexShoes%numShoes], arrayShoesShadow[indexShoes%numShoes], color, TypeObject.shoes);
                shoes = coloringImage.coloringImage();
                break; 
        }    
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);  
        indexImage++; 
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png"; 
        img.fusionaImagenes(destinationPath); 
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));     
        model.setMaterial(mat);
        //Delete the file               
        file = Paths.get(destinationPath);               
        try {                    
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void screenshot() 
    {
        guiViewPort.removeProcessor(niftyDisplay);
        float length = control.getAnimationLength(channel.getAnimationName());
        ScreenshotThread sst = new ScreenshotThread(screenShotState,length,
                channel.getAnimationName(),channel,guiViewPort,niftyDisplay);
        sst.start();        
    }
    
    /** Custom Keybinding: Map named actions to inputs. */
    private void initKeys() 
    {
        inputManager.addMapping("ChangeSkin", new KeyTrigger(KeyInput.KEY_NUMPAD2));
        inputManager.addListener(actionListener, "ChangeSkin");
        inputManager.addMapping("ChangeShoes", new KeyTrigger(KeyInput.KEY_NUMPAD0));
        inputManager.addListener(actionListener, "ChangeShoes");
        inputManager.addMapping("ChangeTrouser", new KeyTrigger(KeyInput.KEY_NUMPAD1));
        inputManager.addListener(actionListener, "ChangeTrouser");
        inputManager.addMapping("ChangeTShirt", new KeyTrigger(KeyInput.KEY_NUMPAD4));
        inputManager.addListener(actionListener, "ChangeTShirt"); 
        inputManager.addMapping("ChangeEyes", new KeyTrigger(KeyInput.KEY_NUMPAD7));
        inputManager.addListener(actionListener, "ChangeEyes"); 
        inputManager.addMapping("Capture", new KeyTrigger(KeyInput.KEY_NUMPAD5));
        inputManager.addListener(actionListener, "Capture"); 
    }
  
    public ActionListener actionListener = new ActionListener() 
    {
        public void onAction(String name, boolean keyPressed, float tpf) 
        {
            if (name.equals("ChangeSkin") && !keyPressed) {
                changeSkin();
            }
            else if (name.equals("ChangeShoes") && !keyPressed) {
                changeShoes();
            }
            else if (name.equals("ChangeTrouser") && !keyPressed) {
                changeTrousers();
            }
            else if (name.equals("ChangeTShirt") && !keyPressed) {
                changeTShirt();
            }
             else if (name.equals("ChangeEyes") && !keyPressed) {
                changeEyes();
            }
            else if (name.equals("Capture") && !keyPressed) {          
                screenshot();          
            }
        }
    };
    
    public void showWindowChangeColorTShirt() throws InterruptedException
    {
        typeObject = TypeObject.t_shirt;
        ColorChooser window = new ColorChooser(this, guiViewPort); 
        guiViewPort.setEnabled(false);
    }
    
    public void showWindowChangeColorTrouser() throws InterruptedException
    {
        typeObject = TypeObject.trouser;
        ColorChooser window = new ColorChooser(this, guiViewPort); 
        guiViewPort.setEnabled(false);
    }
    
    public void showWindowChangeColorShoes() throws InterruptedException
    {
        typeObject = TypeObject.shoes;
        ColorChooser window = new ColorChooser(this, guiViewPort); 
        guiViewPort.setEnabled(false);
    }
    
    public Gender getGender() 
    {
        return gender;
    }

    public void setGender(Gender gender) 
    {
        this.gender = gender;
    }

    public Age getAge() 
    {
        return age;
    }

    public void setAge(Age age) 
    {
        this.age = age;
    }
    
    public void loadModel(){ //HACER
        readXML("assets/XML Configuration/configuration.xml", gender);
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);

        readBuffers();
        
        switch(gender) {
            case Male:
                model = assetManager.loadModel("Models/prueba/polySurfaceShape4.mesh.xml");
                mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setTexture("ColorMap",assetManager.loadTexture("Textures/OriginalTexture.png"));  
                model.setMaterial(mat);
                model.rotate(1.5f, 0.0f, 0.0f);
                model.setLocalTranslation(0.0f, -3.0f, 0.0f);                

                control = model.getControl(AnimControl.class);
                channel = control.createChannel();
                channel.setAnim("my_animation");
                channel.setLoopMode(LoopMode.DontLoop);
                
                if (age == Age.Young){
                    //Scale the model
                    model.scale(0.65f, 0.65f, 0.65f);
                } 
                rootNode.attachChild(model);
                break;
            case Female:
                //Cargar el modelo de la mujer
                break;      
        }
        //Borrar la imagen
        Path file = Paths.get(destinationPath);
        try {
            Files.delete(file);
        } 
        catch (IOException ex) {
            System.out.println("Error al borrar el fichero");
        }
    }
    
    //This method read the XML document of configuration.
    private void readXML(String file, Gender gender1)
    {
        switch(gender) {
            case Male:
                try {
                    //Assing the file to the DOM doc.
                    File xmlFile = new File(file);
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(xmlFile);
                    doc.getDocumentElement().normalize();

                    //Read the doc.
                    //Read all nodes with name man
                    NodeList nListMan = doc.getElementsByTagName("man");
                    for (int temp = 0; temp < nListMan.getLength(); temp++) {
                       Node nNode = nListMan.item(temp); 
                       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                          //Here, we have the element man 
                          Element eElement = (Element) nNode;
                          NodeList nListObjects = eElement.getElementsByTagName("objects");
                          Node nNode2 = (Node) nListObjects.item(0);
                          if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                            //In eElement2, we have the node objects
                            Element eElement2 = (Element) nNode2;
                            NodeList nListSkins = eElement2.getElementsByTagName("skin");
                            
                            numSkins = nListSkins.getLength();
                            arraySkin = new String[numSkins];
                            
                            int indexSkinReaded;
                            for (indexSkinReaded = 0; indexSkinReaded < nListSkins.getLength(); indexSkinReaded++){
                                Node nNode3 = nListSkins.item(indexSkinReaded);
                                if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element3, we have the node skin
                                    Element eElement3 = (Element) nNode3;
                                    NodeList nListPathSkin = eElement3.getElementsByTagName("path").item(0).getChildNodes();
                                    Node nValue = (Node) nListPathSkin.item(0);
                                    String pathSkinReaded = nValue.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Skin "+indexSkinReaded+": " + pathSkinReaded);
                                    //Save the path of this skin
                                    arraySkin[indexSkinReaded] = pathSkinReaded;
                                }
                            }
                            NodeList nListEyes = eElement2.getElementsByTagName("eyes");
                            
                            numEyes = nListEyes.getLength();
                            arrayEyes = new String[numEyes];
                            
                            int indexEyesReaded;
                            for (indexEyesReaded = 0; indexEyesReaded < nListEyes.getLength(); indexEyesReaded++){
                                Node nNode4 = nListEyes.item(indexEyesReaded);
                                if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element4, we have the node eyes
                                    Element eElement4 = (Element) nNode4;
                                    NodeList nListPathEyes = eElement4.getElementsByTagName("path").item(0).getChildNodes();
                                    Node nValue2 = (Node) nListPathEyes.item(0);
                                    String pathEyesReaded = nValue2.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Eyes "+indexEyesReaded+": " + pathEyesReaded);
                                    //Save the path of these eyes
                                    arrayEyes[indexEyesReaded] = pathEyesReaded;
                                }
                            }
                            NodeList nListTShirts = eElement2.getElementsByTagName("tshirt");
                            
                            numTShirts = nListTShirts.getLength();
                            arrayTShirtColor = new String[numTShirts];
                            arrayTShirtShadow = new String[numTShirts];
                            
                            int indexTShirtReaded;
                            for (indexTShirtReaded = 0; indexTShirtReaded < nListTShirts.getLength(); indexTShirtReaded++){
                                Node nNode5 = nListTShirts.item(indexTShirtReaded);
                                if (nNode5.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element5, we have the node tshirt
                                    Element eElement5 = (Element) nNode5;
                                    NodeList nListPathColorTshirt = eElement5.getElementsByTagName("pathColor").item(0).getChildNodes();
                                    Node nValue3 = (Node) nListPathColorTshirt.item(0);
                                    String pathTShirtColorReaded = nValue3.getNodeValue();
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtColorReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtColor[indexTShirtReaded] = pathTShirtColorReaded;

                                    NodeList nListPathShadowTshirt = eElement5.getElementsByTagName("pathShadow").item(0).getChildNodes();
                                    Node nValue4 = (Node) nListPathShadowTshirt.item(0);
                                    String pathTShirtShadowReaded = nValue4.getNodeValue();
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtShadowReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtShadow[indexTShirtReaded] = pathTShirtShadowReaded;
                                }
                            }
                            NodeList nListTrousers = eElement2.getElementsByTagName("trouser");
                            
                            numTrousers = nListTrousers.getLength();
                            arrayTrouserColor = new String[numTrousers];
                            arrayTrouserShadow = new String[numTrousers];
                            
                            int indexTrouserReaded;
                            for (indexTrouserReaded = 0; indexTrouserReaded < nListTrousers.getLength(); indexTrouserReaded++){
                                Node nNode6 = nListTrousers.item(indexTrouserReaded);
                                if (nNode6.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element6, we have the node trouser
                                    Element eElement6 = (Element) nNode6;
                                    NodeList nListPathColorTrouser = eElement6.getElementsByTagName("pathColor").item(0).getChildNodes();
                                    Node nValue5 = (Node) nListPathColorTrouser.item(0);
                                    String pathTrouserColorReaded = nValue5.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserColorReaded);
                                    //Save the path of this trouser
                                    arrayTrouserColor[indexTrouserReaded] = pathTrouserColorReaded;

                                    NodeList nListPathShadowTrouser = eElement6.getElementsByTagName("pathShadow").item(0).getChildNodes();
                                    Node nValue6 = (Node) nListPathShadowTrouser.item(0);
                                    String pathTrouserShadowReaded = nValue6.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserShadowReaded);
                                    //Save the path of this tshirt
                                    arrayTrouserShadow[indexTrouserReaded] = pathTrouserShadowReaded;
                                }
                            }
                            NodeList nListShoes = eElement2.getElementsByTagName("shoes");
                            
                            numShoes = nListShoes.getLength();
                            arrayShoesColor = new String[numShoes];
                            arrayShoesShadow = new String[numShoes];
                            
                            int indexShoesReaded;
                            for (indexShoesReaded = 0; indexShoesReaded < nListShoes.getLength(); indexShoesReaded++){
                                Node nNode7 = nListShoes.item(indexShoesReaded);
                                if (nNode7.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element7, we have the node shoes
                                    Element eElement7 = (Element) nNode7;
                                    NodeList nListPathColorShoes = eElement7.getElementsByTagName("pathColor").item(0).getChildNodes();
                                    Node nValue7 = (Node) nListPathColorShoes.item(0);
                                    String pathShoesColorReaded = nValue7.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Eyes "+indexShoesReaded+": " + pathShoesColorReaded);
                                    //Save the path of this trouser
                                    arrayShoesColor[indexShoesReaded] = pathShoesColorReaded;

                                    NodeList nListPathShadowShoes = eElement7.getElementsByTagName("pathShadow").item(0).getChildNodes();
                                    Node nValue8 = (Node) nListPathShadowShoes.item(0);
                                    String pathShoesShadowReaded = nValue8.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Eyes "+indexShoesReaded+": " + pathShoesShadowReaded);
                                    //Save the path of this tshirt
                                    arrayShoesShadow[indexShoesReaded] = pathShoesShadowReaded;
                                }
                            }
                          }
                       }
                    }
                } catch (Exception e) {
                      e.printStackTrace();
                }
                break;
            case Female:
                break;
        }     
    }
    
    private void readBuffers()
    {
        indexSkin++;
        String skinsPath = arraySkin[indexSkin];
        indexShoes++;
        String shoesColorPath = arrayShoesColor[indexShoes];
        String shoesShadowPath = arrayShoesShadow[indexShoes];
        indexTrouser++;
        String trousersColorPath = arrayTrouserColor[indexTrouser];
        String trousersShadowPath = arrayTrouserShadow[indexTrouser];
        indexTShirt++;
        String tShirtsColorPath = arrayTShirtColor[indexTShirt];
        String tShirtsShadowPath = arrayTShirtShadow[indexTShirt];
        indexEyes++;
        String eyesPath = arrayEyes[indexEyes]; 
        
        try {
            skin = ImageIO.read(new File(skinsPath));
            ColoringImage coloredImage = new ColoringImage(trousersColorPath, trousersShadowPath, -3394561, TypeObject.trouser);
            trousers = coloredImage.coloringImage();
            coloredImage = new ColoringImage(tShirtsColorPath, tShirtsShadowPath, -3394561, TypeObject.t_shirt);
            tShirt = coloredImage.coloringImage();
            eyes = ImageIO.read(new File(eyesPath));
            coloredImage = new ColoringImage(shoesColorPath, shoesShadowPath, -3394561, TypeObject.shoes);
            shoes = coloredImage.coloringImage();
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }     
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);
        destinationPath = "assets/Textures/OriginalTexture.png";
        img.fusionaImagenes(destinationPath);
    }
    
    private BufferedImage readBuffer(String path)
    {
        try {
            BufferedImage aux = ImageIO.read(new File(path));
            return aux;
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private BufferedImage readBuffer(String pathColor, String pathShadow, TypeObject typeObject)
    {
        try {
            ColoringImage coloredImage = new ColoringImage(pathColor, pathShadow, -3394561, typeObject);
            BufferedImage aux = coloredImage.coloringImage();
            return aux;
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}