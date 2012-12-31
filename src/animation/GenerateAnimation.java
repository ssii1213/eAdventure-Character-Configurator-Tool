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

package animation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerateAnimation 
{
	private ArrayList<String> imagesNames;
	private String animationPath;
	private String nameAnimation;

	public GenerateAnimation(String folderPath, String nameAnimation, ArrayList<String> imagesNames)
	{
		this.nameAnimation = nameAnimation;
		this.animationPath = folderPath + "/" + nameAnimation + ".eaa";
                this.imagesNames = imagesNames;

                createAnimation();
	}
	
	private void createAnimation() 
	{
            try
            {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance( );
                TransformerFactory tf = TransformerFactory.newInstance( );
                DocumentBuilder db = dbf.newDocumentBuilder( );
                Document doc = db.newDocument( );
                OutputStream fout = null;
                //Creat the main node
                Element mainNode = doc.createElement( "animation");
                mainNode.setAttribute("id", this.nameAnimation);
                mainNode.setAttribute("usetransitions", "no");
        	mainNode.setAttribute("slides", "no");
                
                Iterator<String> it = imagesNames.iterator();              
                //for (int i = 0; i < imagesNames.size(); i++ )
                while (it.hasNext())
		{                
                    //Creat the node "transition"
                    Element transitionNode = doc.createElement("transition");
                    transitionNode.setAttribute("type", "none");
                    transitionNode.setAttribute("time", "0");
                    //Creat the node "frame"
                    Element frameNode = doc.createElement("frame");
                    frameNode.setAttribute("maxSoundTime", "1000");
                    frameNode.setAttribute("soundUri", "");
                    frameNode.setAttribute("time", "150");
                    frameNode.setAttribute("type", "image");
                    frameNode.setAttribute("uri", "assets/animation/" + it.next());
                    frameNode.setAttribute("waitforclick", "no");
                    //Add de nodes to main node
                    mainNode.appendChild(transitionNode);
                    mainNode.appendChild(frameNode);
		}            
                doc.adoptNode(mainNode);
                doc.appendChild(mainNode);
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "animation.dtd");
                try {
                    fout = new FileOutputStream(this.animationPath);
                }
                catch( FileNotFoundException e ) {
                    System.out.println("error");
                }
                OutputStreamWriter writeFile = new OutputStreamWriter(fout, "UTF-8");
                transformer.transform(new DOMSource(doc), new StreamResult(writeFile));
                writeFile.close();
                fout.close();
            }
            catch( Exception e ) {
                System.out.println("error");
            }
        }
}