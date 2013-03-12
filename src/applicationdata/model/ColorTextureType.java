//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: AM.03.11 a las 10:45:40 AM CET 
//


package applicationdata.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para colorTextureType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="colorTextureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="texture" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="default" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="idPanel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="layer" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "colorTextureType", propOrder = {
    "texture"
})
public class ColorTextureType {

    @XmlElement(required = true)
    protected List<ColorTextureType.Texture> texture;
    @XmlAttribute(name = "idPanel", required = true)
    protected String idPanel;
    @XmlAttribute(name = "layer", required = true)
    protected BigInteger layer;

    /**
     * Gets the value of the texture property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the texture property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTexture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ColorTextureType.Texture }
     * 
     * 
     */
    public List<ColorTextureType.Texture> getTexture() {
        if (texture == null) {
            texture = new ArrayList<ColorTextureType.Texture>();
        }
        return this.texture;
    }

    /**
     * Obtiene el valor de la propiedad idPanel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPanel() {
        return idPanel;
    }

    /**
     * Define el valor de la propiedad idPanel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPanel(String value) {
        this.idPanel = value;
    }

    /**
     * Obtiene el valor de la propiedad layer.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLayer() {
        return layer;
    }

    /**
     * Define el valor de la propiedad layer.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLayer(BigInteger value) {
        this.layer = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="default" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "path",
        "iconPath"
    })
    public static class Texture {

        @XmlElement(required = true)
        protected String path;
        @XmlElement(required = true)
        protected String iconPath;
        @XmlAttribute(name = "default", required = true)
        protected boolean _default;

        /**
         * Obtiene el valor de la propiedad path.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPath() {
            return path;
        }

        /**
         * Define el valor de la propiedad path.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPath(String value) {
            this.path = value;
        }

        /**
         * Obtiene el valor de la propiedad iconPath.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIconPath() {
            return iconPath;
        }

        /**
         * Define el valor de la propiedad iconPath.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIconPath(String value) {
            this.iconPath = value;
        }

        /**
         * Obtiene el valor de la propiedad default.
         * 
         */
        public boolean isDefault() {
            return _default;
        }

        /**
         * Define el valor de la propiedad default.
         * 
         */
        public void setDefault(boolean value) {
            this._default = value;
        }

    }

}