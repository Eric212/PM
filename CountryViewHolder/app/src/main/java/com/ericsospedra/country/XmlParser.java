package com.ericsospedra.country;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParser {
    private Context c;
    private Country[] countries;

    public XmlParser(Context c) {
        this.c=c;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    private void setCountries(Country[] countries) {
        this.countries = countries;
    }

    public Country[] getCountries() {
        return countries;
    }

    public boolean parseXml() {
        try {
            InputStream datos = getC().getResources().openRawResource(R.raw.countries);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(datos);
            Country[] countries;

            Element root = doc.getDocumentElement();
            NodeList items = root.getElementsByTagName("country");
            countries = new Country[items.getLength()];
            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);
                String countryCode = item.getAttributes().getNamedItem("countryCode").getNodeValue();
                String countrName = item.getAttributes().getNamedItem("countryName").getNodeValue();
                long population = Long.parseLong(item.getAttributes().getNamedItem("population").getNodeValue());
                String countryCapital = item.getAttributes().getNamedItem("capital").getNodeValue();
                String isoAlpha3 = item.getAttributes().getNamedItem("isoAlpha3").getNodeValue();
                countries[i] = new Country(countryCode, countrName, population, countryCapital, isoAlpha3);
            }
            setCountries(countries);
            return true;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.err.println("Error");
            return false;
        }
    }

}