/*******************************************************************************
 * <eAdventure Character Configurator> is a research project of the <e-UCM>
 *          research group.
 *
 *    Developed by: Alejandro Muñoz del Rey, Sergio de Luis Nieto and David González
 *    Ledesma.
 *    Under the supervision of Baltasar Fernández-Manjón and Javier Torrente
 * 
 *    Copyright 2012-2013 <e-UCM> research group.
 *  
 *     <e-UCM> is a research group of the Department of Software Engineering
 *          and Artificial Intelligence at the Complutense University of Madrid
 *          (School of Computer Science).
 *  
 *          C Profesor Jose Garcia Santesmases sn,
 *          28040 Madrid (Madrid), Spain.
 *  
 *          For more info please visit:  <http://character.e-ucm.es>, 
 *          <http://e-adventure.e-ucm.es> or <http://www.e-ucm.es>
 *  
 *  ****************************************************************************
 *      <eAdventure Character Configurator> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eAdventure Character Configurator> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eAdventure Character Configurator>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package XML;

import Model.Model;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import types.Gender;
import types.Shoes;
import types.TShirt;
import types.Trouser;

public class XMLReader {
    private Model model;
    private String file;
    private Gender gender;
    
    public XMLReader(Model model, String file, Gender gender)
    {
        this.model = model;
        this.file = file;
        this.gender = gender;
    }
    //This method read the XML configuration document.
    public void readXML()
    {
        if (pathOK(file)){
            String gender_word = "";
            switch(gender) {
                case Male:
                    gender_word = "man";
                    break;
                case Female:
                    gender_word = "woman";
                    break;
            }        
            try {
                //Assing the file to the DOM doc.
                File xmlFile = new File(file);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                //Read the doc.
                //Read all nodes with name man or woman
                NodeList nListMan = doc.getElementsByTagName(gender_word);
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

                        int numSkins = nListSkins.getLength();
                        String [] arraySkin = new String[numSkins];
                        String [] arraySkinIcon = new String[numSkins];

                        int indexSkinReaded;
                        for (indexSkinReaded = 0; indexSkinReaded < nListSkins.getLength(); indexSkinReaded++){
                            Node nNode3 = nListSkins.item(indexSkinReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Node nValue = attributes.getNamedItem("path");
                                String pathSkinReaded = nValue.getNodeValue();
                                if (pathOK(pathSkinReaded)){
                                    //Print the data readed
                                    System.out.println("Skin "+indexSkinReaded+": " + pathSkinReaded);
                                    //Save the path of this skin
                                    arraySkin[indexSkinReaded] = pathSkinReaded;
                                }
                                else{
                                    System.out.println("El siguiente path del skin es incorrecto: "+pathSkinReaded);
                                    return;
                                }
                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconSkinReaded = nValue.getNodeValue();
                                String pathIconSkinReaded_aux = "assets/"+pathIconSkinReaded;
                                if (pathOK(pathIconSkinReaded_aux)){
                                    //Print the data readed
                                    System.out.println("Skin "+indexSkinReaded+": " + pathIconSkinReaded);
                                    //Save the path of this skin's icon
                                    arraySkinIcon[indexSkinReaded] = pathIconSkinReaded;
                                }
                                else{
                                    System.out.println("El siguiente path del skin es incorrecto: "+pathIconSkinReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumSkins(numSkins);
                        model.setArraySkin(arraySkin);
                        model.setArraySkinIcon(arraySkinIcon);

                        NodeList nListEyes = eElement2.getElementsByTagName("eyes");

                        int numEyes = nListEyes.getLength();
                        String [] arrayEyes = new String[numEyes];
                        String [] arrayEyesIcon = new String[numEyes];

                        int indexEyesReaded;
                        for (indexEyesReaded = 0; indexEyesReaded < nListEyes.getLength(); indexEyesReaded++){
                            Node nNode3 = nListEyes.item(indexEyesReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Node nValue = attributes.getNamedItem("path");
                                String pathEyesReaded = nValue.getNodeValue();
                                if (pathOK(pathEyesReaded)){
                                    //Print the data readed
                                    System.out.println("Eyes "+indexEyesReaded+": " + pathEyesReaded);
                                    //Save the path of these eyes
                                    arrayEyes[indexEyesReaded] = pathEyesReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de eyes es incorrecto: "+pathEyesReaded);
                                    return;
                                }

                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconEyesReaded = nValue.getNodeValue();
                                String pathIconEyesReaded_aux = "assets/"+pathIconEyesReaded;
                                if (pathOK(pathIconEyesReaded_aux)){
                                    //Print the data readed
                                    System.out.println("Eyes "+indexEyesReaded+": " + pathIconEyesReaded);           
                                    //Save the path of these eyes
                                    arrayEyesIcon[indexEyesReaded] = pathIconEyesReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de eyes es incorrecto: "+pathIconEyesReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumEyes(numEyes);
                        model.setArrayEyes(arrayEyes);
                        model.setArrayEyesIcon(arrayEyesIcon);

                        NodeList nListTShirts = eElement2.getElementsByTagName("tshirt");

                        int numTShirts = nListTShirts.getLength();
                        TShirt [] arrayTShirt = new TShirt[numTShirts];
                        String [] arrayTShirtIcon = new String[numTShirts];

                        int indexTShirtReaded;
                        for (indexTShirtReaded = 0; indexTShirtReaded < nListTShirts.getLength(); indexTShirtReaded++){
                            Node nNode3 = nListTShirts.item(indexTShirtReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                TShirt tShirt = new TShirt();
                                Node nValue = attributes.getNamedItem("pathColor");
                                String pathTShirtColorReaded = nValue.getNodeValue();
                                if (pathOK(pathTShirtColorReaded)){
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtColorReaded);
                                    //Save the path of this tshirt
                                    tShirt.setPathTShirt(pathTShirtColorReaded);
                                }
                                else{
                                    System.out.println("El siguiente path de tshirt es incorrecto: "+pathTShirtColorReaded);
                                    return;
                                }
                                nValue = attributes.getNamedItem("pathShadow");
                                if (nValue != null){
                                    String pathTShirtShadowReaded = nValue.getNodeValue();
                                    if (pathOK(pathTShirtShadowReaded)){
                                        //Print the data readed
                                        System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtShadowReaded);
                                        //Save the path of this tshirt
                                        tShirt.setPathShadow(pathTShirtShadowReaded);
                                    }
                                    else{
                                        System.out.println("El siguiente path de tshirt es incorrecto: "+pathTShirtShadowReaded);
                                        return;
                                    }
                                }
                                nValue = attributes.getNamedItem("pathDetail");
                                if (nValue != null){
                                    String pathTShirtDetailReaded = nValue.getNodeValue();
                                    if (pathOK(pathTShirtDetailReaded)){
                                        //Print the data readed
                                        System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtDetailReaded);
                                        //Save the path of this tshirt
                                        tShirt.setPathDetail(pathTShirtDetailReaded);
                                    }
                                    else{
                                        System.out.println("El siguiente path de tshirt es incorrecto: "+pathTShirtDetailReaded);
                                        return;
                                    }
                                }
                                nValue = attributes.getNamedItem("transparencyShadow");
                                if (nValue != null){
                                    int transparencyShadowReaded = Integer.parseInt(nValue.getNodeValue());
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + transparencyShadowReaded);
                                    //Save the transparencyShadow of this tshirt
                                    tShirt.setTransparencyShadow(transparencyShadowReaded);
                                }
                                arrayTShirt[indexTShirtReaded] = tShirt;
                                
                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconTShirtReaded = nValue.getNodeValue();
                                String pathIconTShirtReaded_aux = "assets/"+pathIconTShirtReaded;
                                if (pathOK(pathIconTShirtReaded_aux)){
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathIconTShirtReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtIcon[indexTShirtReaded] = pathIconTShirtReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de tshirt es incorrecto: "+pathIconTShirtReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumTShirts(numSkins);
                        model.setArrayTShirt(arrayTShirt);
                        model.setArrayTShirtIcon(arrayTShirtIcon);

                        NodeList nListTrousers = eElement2.getElementsByTagName("trouser");

                        int numTrousers = nListTrousers.getLength();
                        Trouser [] arrayTrouser = new Trouser[numTrousers];
                        String [] arrayTrouserIcon = new String[numTrousers];

                        int indexTrouserReaded;
                        for (indexTrouserReaded = 0; indexTrouserReaded < nListTrousers.getLength(); indexTrouserReaded++){
                            Node nNode3 = nListTrousers.item(indexTrouserReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Trouser trouser = new Trouser();
                                Node nValue = attributes.getNamedItem("pathColor");
                                String pathTrouserColorReaded = nValue.getNodeValue();
                                if (pathOK(pathTrouserColorReaded)){
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserColorReaded);
                                    //Save the path of this trouser
                                    trouser.setPathTrouser(pathTrouserColorReaded);
                                }
                                else{
                                    System.out.println("El siguiente path de trouser es incorrecto: "+pathTrouserColorReaded);
                                    return;
                                }
                                
                                nValue = attributes.getNamedItem("pathShadow");
                                if (nValue != null){
                                    String pathTrouserShadowReaded = nValue.getNodeValue();
                                    if (pathOK(pathTrouserShadowReaded)){
                                        //Print the data readed
                                        System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserShadowReaded);
                                        //Save the path of this trouser
                                        trouser.setPathShadow(pathTrouserShadowReaded);
                                    }
                                    else{
                                        System.out.println("El siguiente path de trouser es incorrecto: "+pathTrouserShadowReaded);
                                        return;
                                    }
                                }
                                nValue = attributes.getNamedItem("pathDetail");
                                if (nValue != null){
                                    String pathTrouserDetailReaded = nValue.getNodeValue();
                                    if (pathOK(pathTrouserDetailReaded)){
                                        //Print the data readed
                                        System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserDetailReaded);
                                        //Save the path of this trouser
                                        trouser.setPathDetail(pathTrouserDetailReaded);
                                    }
                                    else{
                                        System.out.println("El siguiente path de trouser es incorrecto: "+pathTrouserDetailReaded);
                                        return;
                                    }
                                }
                                nValue = attributes.getNamedItem("transparencyShadow");
                                if (nValue != null){
                                    int transparencyShadowReaded = Integer.parseInt(nValue.getNodeValue());
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + transparencyShadowReaded);
                                    //Save the transparencyShadow of this trouser
                                    trouser.setTransparencyShadow(transparencyShadowReaded);
                                }
                                arrayTrouser[indexTrouserReaded] = trouser;
                                
                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconTrouserReaded = nValue.getNodeValue();
                                String pathIconTrouserReaded_aux = "assets/"+pathIconTrouserReaded;
                                if (pathOK(pathIconTrouserReaded_aux)){
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathIconTrouserReaded);
                                    //Save the path of this trouser
                                    arrayTrouserIcon[indexTrouserReaded] = pathIconTrouserReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de trouser es incorrecto: "+pathIconTrouserReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumTrousers(numTrousers);
                        model.setArrayTrouser(arrayTrouser);
                        model.setArrayTrouserIcon(arrayTrouserIcon);

                        NodeList nListShoes = eElement2.getElementsByTagName("shoes");

                        int numShoes = nListShoes.getLength();
                        Shoes [] arrayShoes = new Shoes[numShoes];
                        String [] arrayShoesIcon = new String[numShoes];

                        int indexShoesReaded;
                        for (indexShoesReaded = 0; indexShoesReaded < nListShoes.getLength(); indexShoesReaded++){
                            Node nNode3 = nListShoes.item(indexShoesReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Shoes shoes = new Shoes();
                                Node nValue = attributes.getNamedItem("pathColor");
                                String pathShoesColorReaded = nValue.getNodeValue();
                                if (pathOK(pathShoesColorReaded)){
                                    //Print the data readed
                                    System.out.println("Shoes "+indexShoesReaded+": " + pathShoesColorReaded);
                                    //Save the path of this shoe
                                    shoes.setPathShoes(pathShoesColorReaded);
                                }
                                else{
                                    System.out.println("El siguiente path de shoes es incorrecto: "+pathShoesColorReaded);
                                    return;
                                }

                                nValue = attributes.getNamedItem("pathShadow");
                                if (nValue != null){
                                    String pathShoesShadowReaded = nValue.getNodeValue();
                                    if (pathOK(pathShoesShadowReaded)){
                                        //Print the data readed
                                        System.out.println("Shoes "+indexShoesReaded+": " + pathShoesShadowReaded);
                                        //Save the path of this shoe
                                        shoes.setPathShadow(pathShoesShadowReaded);
                                    }
                                    else{
                                        System.out.println("El siguiente path de shoes es incorrecto: "+pathShoesShadowReaded);
                                        return;
                                    }
                                }
                                nValue = attributes.getNamedItem("transparencyShadow");
                                if (nValue != null){
                                    int transparencyShadowReaded = Integer.parseInt(nValue.getNodeValue());
                                    //Print the data readed
                                    System.out.println("Shoes "+indexShoesReaded+": " + transparencyShadowReaded);
                                    //Save the transparencyShadow of this shoe
                                    shoes.setTransparencyShadow(transparencyShadowReaded);
                                }
                                arrayShoes[indexShoesReaded] = shoes;

                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconShoesReaded = nValue.getNodeValue();
                                String pathIconShoesReaded_aux = "assets/"+pathIconShoesReaded;
                                if (pathOK(pathIconShoesReaded_aux)){
                                    //Print the data readed
                                    System.out.println("Shoes "+indexShoesReaded+": " + pathIconShoesReaded);
                                    //Save the path of this shoe
                                    arrayShoesIcon[indexShoesReaded] = pathIconShoesReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de shoes es incorrecto: "+pathIconShoesReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumShoes(numShoes);
                        model.setArrayShoes(arrayShoes);
                        model.setArrayShoesIcon(arrayShoesIcon);
                      }
                      NodeList nListAnimations = eElement.getElementsByTagName("model");        
                      Node nNode3 = nListAnimations.item(0);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("path");
                            String pathAnimationReaded = nValue.getNodeValue();
                            String pathAnimation_aux = "assets/"+pathAnimationReaded;
                            if (pathOK(pathAnimation_aux)){
                                //Print the data readed
                                System.out.println("Animation : " + pathAnimationReaded);
                                //Save the path of this model
                                model.setModelPath(pathAnimationReaded);
                            }
                            else{
                                    System.out.println("El siguiente path de model es incorrecto: "+pathAnimation_aux);
                                    return;
                                }
                            //In eElement2, we have the node model
                            Element eElement2 = (Element) nNode3;
                            NodeList nListBones = eElement2.getElementsByTagName("bones");                     
                            Node nNode4 = nListBones.item(0);
                            int numBones = nNode4.getChildNodes().getLength();
                            String [] nameBones= new String[numBones];
                            
                            //In eElement3, we have the node bones
                            Element eElement3 = (Element) nNode4;
                            //Head
                            NodeList nListHeadBone = eElement3.getElementsByTagName("head");
                            nNode4 = nListHeadBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameHead = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Head bone : " + nameHead);
                                //Save the name of this bone
                                nameBones[0] = nameHead;        
                            }
                            //Torax
                            NodeList nListToraxBone = eElement3.getElementsByTagName("torax");
                            nNode4 = nListToraxBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameTorax = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Torax bone : " + nameTorax);
                                //Save the name of this bone
                                nameBones[1] = nameTorax;        
                            }
                            //Left Wrist
                            NodeList nListLeftWristBone = eElement3.getElementsByTagName("leftWrist");
                            nNode4 = nListLeftWristBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameLeftWristBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Left Wrist bone : " + nameLeftWristBone);
                                //Save the name of this bone
                                nameBones[2] = nameLeftWristBone;        
                            }
                            //Right Wrist
                            NodeList nListRightWristBone = eElement3.getElementsByTagName("rightWrist");
                            nNode4 = nListRightWristBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameRightWristBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Right Wrist bone : " + nameRightWristBone);
                                //Save the name of this bone
                                nameBones[3] = nameRightWristBone;        
                            }
                            //Left Ankle
                            NodeList nListLeftAnkleBone = eElement3.getElementsByTagName("leftAnkle");
                            nNode4 = nListLeftAnkleBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameLeftAnkleBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Left Ankle bone : " + nameLeftAnkleBone);
                                //Save the name of this bone
                                nameBones[4] = nameLeftAnkleBone;        
                            }
                            //Right Ankle
                            NodeList nListRightAnkleBone = eElement3.getElementsByTagName("rightAnkle");
                            nNode4 = nListRightAnkleBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameRightAnkleBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Right Ankle bone : " + nameRightAnkleBone);
                                //Save the name of this bone
                                nameBones[5] = nameRightAnkleBone;        
                            }
                            //Left Femur
                            NodeList nListLeftFemurBone = eElement3.getElementsByTagName("leftFemur");
                            nNode4 = nListLeftFemurBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameLeftFemurBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Left Femur bone : " + nameLeftFemurBone);
                                //Save the name of this bone
                                nameBones[6] = nameLeftFemurBone;        
                            }
                            //Right Femur
                            NodeList nListRightFemurBone = eElement3.getElementsByTagName("rightFemur");
                            nNode4 = nListRightFemurBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameRightFemurBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Right Femur bone : " + nameRightFemurBone);
                                //Save the name of this bone
                                nameBones[7] = nameRightFemurBone;        
                            }
                            //Left Clavicle
                            NodeList nListLeftClavicleBone = eElement3.getElementsByTagName("leftClavicle");
                            nNode4 = nListLeftClavicleBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameLeftClavicleBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Left Clavicle bone : " + nameLeftClavicleBone);
                                //Save the name of this bone
                                nameBones[8] = nameLeftClavicleBone;        
                            }
                            
                            //Right Clavicle
                            NodeList nListRightClavicleBone = eElement3.getElementsByTagName("rightClavicle");
                            nNode4 = nListRightClavicleBone.item(0);
                            if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes2 = nNode4.getAttributes();
                                Node nValue2 = attributes2.getNamedItem("boneName");
                                String nameRightClavicleBone = nValue2.getNodeValue();
                                //Print the data readed
                                System.out.println("Right Clavicle bone : " + nameRightClavicleBone);
                                //Save the name of this bone
                                nameBones[9] = nameRightClavicleBone;        
                            }
                            model.setNamesBones(nameBones);
                        }                   
                    }
                }
            } catch (Exception e) {
                  e.printStackTrace();
            }
        }
        else {
            System.out.println("Ruta del fichero XML incorrecta");
        }
    }
    
    private boolean pathOK (String path){
        File fileReaded = new File(path);
        if (fileReaded.exists()){
            return true;
        }
        else{
            return false;
        }
    }
}