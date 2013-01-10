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

package gui_nifty;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import types.Age;
import types.Gender;

public class StartScreen extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Application app;
    private Screen screen;
    private Gui gui;
    private String selection;
    
    public StartScreen(Gui gui){
        this.gui = gui;
    }
    
    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
    }

    public void quitGame() {
        app.stop();
    }

    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    public void onStartScreen() {}

    public void onEndScreen() {}
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) 
    {
        this.app = app;
    }
    
    public void manSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setGender(Gender.Male);
    }
    
    public void womanSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setGender(Gender.Female);
    }
    
    public void adultSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setAgeModel(Age.Adult);
        gui.loadModel();    
    }
    
    public void youngSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen 
        gui.setAgeModel(Age.Young);
        gui.loadModel();        
    }
    
    public void changeScreen(String param)
    {
        selection = param;
        if(param.equals("skin")){
            nifty.gotoScreen("skinScreen");
        }
        if(param.equals("eyes")){
            nifty.gotoScreen("eyesScreen");
        }
        if(param.equals("tshirt")){
            nifty.gotoScreen("tshirtScreen");
        }
        if(param.equals("trousers")){
            nifty.gotoScreen("trousersScreen");
        }
        if(param.equals("shoes")){
            nifty.gotoScreen("shoesScreen");
        }
        if(param.equals("accesories")){
            nifty.gotoScreen("accesoriesScreen");
        }
        if(param.equals("bones")){
            nifty.gotoScreen("bonesScreen");
        }
    }
    
    public String getMenu(String param)
    {
        if(param.equals("yes")){
            return "Interface/MenuRojo.png";
        }
        else{
            return "Interface/MenuAzul.png";
        }
    }
    
    public String getButton(String param)
    {
        if(param.equals("left")){
            return "Interface/ant.png";
        }
        else{
            if(param.equals("right")){
                return "Interface/sig.png";
            }
            else{
                if(param.equals("color")){
                    return "Interface/ColorButton.png";
                }
                else{
                    return "Interface/Finish.png";
                }
            }
        }
    }
    
    public void changeTexture(String steep)
    {
        int i;
        if(steep.equals("+")){i = 1;}
        else{i = -1;}
        if(selection.equals("skin")){gui.changeSkin(i);}
        if(selection.equals("eyes")){gui.changeEyes(i);}
        if(selection.equals("tshirt")){gui.changeTShirt(i);}
        if(selection.equals("trousers")){gui.changeTrousers(i);}
        if(selection.equals("shoes")){}
        if(selection.equals("accesories")){}
    }
    
    public void showWindowChangeColor() throws InterruptedException
    {
        if(selection.equals("skin")){}
        if(selection.equals("eyes")){}
        if(selection.equals("tshirt")){gui.showWindowChangeColorTShirt();}
        if(selection.equals("trousers")){gui.showWindowChangeColorTrouser();}
        if(selection.equals("shoes")){gui.showWindowChangeColorShoes();}
        if(selection.equals("accesories")){}
    }
    
    @NiftyEventSubscriber(id="head")
    public void onHeadSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        String[] namesBones = {"Cabeza"};
        gui.scaleModel(namesBones, inc);
    }
    
    @NiftyEventSubscriber(id="torso")
    public void onTorsoSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        String[] namesBones = {"Torax"};
        gui.scaleModel(namesBones, inc);
    }
    
    @NiftyEventSubscriber(id="hands")
    public void onHandsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        String[] namesBones = {"MuniecaIz","MuniecaDer"};
        gui.scaleModel(namesBones, inc);
    }
    
    @NiftyEventSubscriber(id="feet")
    public void onFeetSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        String[] namesBones = {"TobilloIz","TobilloDer"};
        gui.scaleModel(namesBones, inc);
    }
    
    @NiftyEventSubscriber(id="legs")
    public void onLegsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        String[] namesBones = {"FemurIz","FemurDer"};
        gui.scaleModel(namesBones, inc);
    }
    
    @NiftyEventSubscriber(id="arms")
    public void onArmsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        String[] namesBones = {"CodoIz","CodoDer"};
        gui.scaleModel(namesBones, inc);
    }
    
    public void screenshot() 
    {
        gui.screenshot();
        nifty.gotoScreen("finishScreen");
    }
}