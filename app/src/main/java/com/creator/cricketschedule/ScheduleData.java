package com.creator.cricketschedule;

import android.os.StrictMode;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;

/**
 * Created by Mathi on 01-17-16.
 */
public class ScheduleData {
    public String[] seriesList;
    public String[] matchList;
    public String[] onlySeriesList;
    public NodeList typeNodes;
    Document doc;
    public void getDataFromURL(String type) {
        try {
            URL url = new URL("http://synd.cricbuzz.com/j2me/1.0/sch_calender.xml");
            URLConnection urlConnection = url.openConnection();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(urlConnection.getInputStream());
            typeNodes = doc.getElementsByTagName(type);
            Log.d("#Node Length", String.format("%d", typeNodes.getLength()));
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            Log.d("Exception : ", " " + errorMsg);
        }

    }

    public String[] getSeriesList()
    {
        getDataFromURL("srs");
        Log.d("#Nodes", " " + typeNodes);
        seriesList = new String[typeNodes.getLength()];
        onlySeriesList = new String[typeNodes.getLength()];
        for (int i = 0; i < typeNodes.getLength(); i++) {
            Element e = (Element) typeNodes.item(i);
            seriesList[i] = e.getAttribute("info");
            onlySeriesList[i] = seriesList[i].split(",")[1];
            //Log.d("Series List : ",seriesList[i]);
        }
        return seriesList;
    }

    public String[] getMatchDataForSeries(String seriesName)
    {
        String expression = ".//mch[@srs='"+seriesName+"']";
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList matchListDesc = null;
        try
        {
            matchListDesc = (NodeList) xpath.compile(expression).evaluate(doc,XPathConstants.NODESET);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        String[] matchData = new String[matchListDesc.getLength()];
        for(int i=0;i<matchListDesc.getLength();i++)
        {
            Element e = (Element) matchListDesc.item(i);
            matchData[i] = e.getAttribute("desc")+" "+e.getAttribute("ddt")+" "+e.getAttribute("mnth_yr");
        }
        return matchData;
    }
}
