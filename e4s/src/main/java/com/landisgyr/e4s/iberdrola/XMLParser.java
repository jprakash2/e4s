package com.landisgyr.e4s.iberdrola;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Iterator;

// *****************************************************************************************
// Iberdrola has traditionally used XML for their S reports.  Their headend system (the STG)
// expects XML formatted reports today.  However, E4S applications use a Web Things model
// and JSON format to exchange data.  As of February, 2021, the E4S group has decided to
// switch the S report format from XML to JSON.    Thus, this XML parser included here
// may prove to have a limited utility within E4S.   However, the STG system still expects
// XML formatted reports, and this will likely NOT change.  So in the future, we may be
// asked to deliver an XML-formatted outage report to the STG (but this is beyond the
// current scope of the Phase 2 POC).
// *****************************************************************************************

/** An XML STAX (pull) parser used to parse XML-based SReports files */
public class XMLParser {
    // uses a STAX parser to parse XML files
    public static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    public XMLEventReader reader;
    private static final Logger logger= LogManager.getLogger(XMLParser.class);

    /** Constructor - each XMLParser instance has a reader */
    public XMLParser(String xmlFilePath) {
        try {
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(String.valueOf(Paths.get(xmlFilePath))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** Scans for the next start element in the document */
    public StartElement findNextStartElement() {
        boolean found = false;
        StartElement startElem = null;

        if (reader != null) {
            // scan the report for the next StartElement
            while (reader.hasNext() && !found) {
                XMLEvent nextEvent = null;
                try {
                    nextEvent = reader.nextEvent();
                    if (nextEvent.isStartElement()) {
                        startElem = nextEvent.asStartElement();
                        found = true;
                    }
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }

        if (found) logger.log(Level.INFO,"Found startElement: " + startElem);

        return startElem;
    }

    /** Returns the next XMLEvent in the document */
    public XMLEvent getNextEvent() {
        XMLEvent nextEvent = null;

        try {
            if (reader != null) {
                nextEvent = reader.nextEvent();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextEvent;
    }

    /** finds the SReport header and imports the attributes (IdRpt, IdPet and Version). */
    public SReport getReportHeader(String reportPath, XMLParser xmlParser) {
        SReport sReportHeader = new SReport(reportPath);
        StartElement se = xmlParser.findNextStartElement();
        boolean found = false;

        // find the "Report" start element
        while (!found && se != null) {
            if (se.getName().getLocalPart().contains("Report")) found = true;
            else se = xmlParser.findNextStartElement();
        }

        if (found) {
            // get the attributes
            Iterator<Attribute> attribute = se.asStartElement().getAttributes();
            while(attribute.hasNext()) {
                Attribute a = attribute.next();
                String name = a.getName().toString();
                logger.log(Level.INFO,"Atribute: " + name);
                if (name.equals("IdRpt")) sReportHeader.idRpt = a.getValue();
                else if (name.equals("IdPet")) sReportHeader.idPet = a.getValue();
                else if (name.equals("Version")) sReportHeader.version = a.getValue();
            }
        }

        return sReportHeader;
    }
}
