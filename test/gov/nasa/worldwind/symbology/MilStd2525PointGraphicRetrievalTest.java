/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package gov.nasa.worldwind.symbology;

import gov.nasa.worldwind.avlist.*;
import gov.nasa.worldwind.symbology.milstd2525.MilStd2525PointGraphicRetriever;
import junit.framework.TestCase;

import java.awt.image.*;

/**
 * @author pabercrombie
 * @version $Id$
 */
public class MilStd2525PointGraphicRetrievalTest extends TestCase
{
    // TODO: test all possible values for Standard Identity and Status

    // This path should correspond to the location of the appropriate symbology source icons on your system
    private final static String URL = "http://worldwindserver.net/milstd2525/";

    //////////////////////////////////////////////////////////
    // Test retrieval of a MilStd2525 point graphic from both a remote
    // server and the local file system.
    //////////////////////////////////////////////////////////

    @org.junit.Test
    public void testServerRetrieval()
    {
        IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
        BufferedImage img = symGen.createIcon("GFFPPCB-------X", null);
        assertNotNull(img);
    }

    //////////////////////////////////////////////////////////
    // Test parsing of the Symbol Code.
    // MilStd2525 SymCodes should be exactly 15 characters.
    //////////////////////////////////////////////////////////

    @org.junit.Test
    public void testParseCodeTooShort()
    {
        try
        {
            IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
            symGen.createIcon("SUAPC", null);
            fail("Should raise an IllegalArgumentException");
        }
        catch (Exception e)
        {
        }
    }

    @org.junit.Test
    public void testParseCodeTooLong()
    {
        try
        {
            IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
            symGen.createIcon("SUAPCTEST", null);
            fail("Should raise an IllegalArgumentException");
        }
        catch (Exception e)
        {
        }
    }

    @org.junit.Test
    public void testParseNullCode()
    {
        try
        {
            IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
            symGen.createIcon(null, null);
            fail("Should raise an IllegalArgumentException");
        }
        catch (Exception e)
        {
        }
    }

    //////////////////////////////////////////////////////////
    // Test validity of Symbol Code.
    // Codes containing invalid letters should retrieve a null image.
    // TODO: is this correct?
    //////////////////////////////////////////////////////////

    @org.junit.Test
    public void testInvalidCodingScheme()
    {
        try
        {
            IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
            symGen.createIcon(".FFPPCB-------X", null);
            fail("Should raise an IllegalArgumentException");
        }
        catch (Exception e)
        {
        }
    }

    @org.junit.Test
    public void testInvalidStandardIdentity()
    {
        try
        {
            IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
            symGen.createIcon("G.FPPCB-------X", null);
            fail("Should raise an IllegalArgumentException");
        }
        catch (Exception e)
        {
        }
    }

    @org.junit.Test
    public void testInvalidStatus()
    {
        try
        {
            IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
            symGen.createIcon("GFF.PCB-------X", null);
            fail("Should raise an IllegalArgumentException");
        }
        catch (Exception e)
        {
        }
    }

    @org.junit.Test
    public void testInvalidFunctionID()
    {
        try
        {
            IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
            symGen.createIcon("GFFP...-------X", null);
            fail("Should raise an IllegalArgumentException");
        }
        catch (Exception e)
        {
        }
    }

    /** Test for the presence and retrieval of a every tactical point graphic */
    @org.junit.Test
    public void testTacticalGraphicRetrieval()
    {
        IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
        AVList params = new AVListImpl();
        BufferedImage img;

        for (String s : TacticalGraphicSymbolCodes)
        {
            img = symGen.createIcon(s, params);
            assertNotNull("Icon " + s.toLowerCase() + "-----.png not found.", img);
        }
    }

    /*
     * Test for the presence and retrieval of a every possible Meteorological point graphic
     */
    @org.junit.Test
    public void testMeteorologicalSymbolRetrieval()
    {
        IconRetriever symGen = new MilStd2525PointGraphicRetriever(URL);
        AVList params = new AVListImpl();
        BufferedImage img;

        for (String s : MeteorologicalSymbolCodes)
        {
            img = symGen.createIcon(s, params);
            assertNotNull("Icon " + s.toLowerCase() + "-----.png not found.", img);
        }
    }

    public static void main(String[] args)
    {
        new junit.textui.TestRunner().doRun(new junit.framework.TestSuite(MilStd2525PointGraphicRetrievalTest.class));
    }

    /**
     * Appendix B: Military Symbology
     * <p/>
     * Note that this list includes only point graphics. Line and area graphics are not retrieved from the server.
     */
    protected final static String[] TacticalGraphicSymbolCodes = {
        "GFFPPCB-------X",
        "GFFPPCH-------X",
        "GFFPPCL-------X",
        "GFFPPCR-------X",
        "GFFPPCS-------X",
        "GFFPPS--------X",
        "GFFPPTN-------X",
        "GFFPPTS-------X",
        "GFGPAPD-------X",
        "GFGPDPT-------X",
        "GFGPGPAA------X",
        "GFGPGPAC------X",
        "GFGPGPAH------X",
        "GFGPGPAK------X",
        "GFGPGPAL------X",
        "GFGPGPAM------X",
        "GFGPGPAO------X",
        "GFGPGPAP------X",
        "GFGPGPAR------X",
        "GFGPGPAS------X",
        "GFGPGPAT------X",
        "GFGPGPAW------X",
        "GFGPGPF-------X",
        "GFGPGPH-------X",
        "GFGPGPHA------X",
        "GFGPGPHQ------X",
        "GFGPGPHX------X",
        "GFGPGPHY------X",
        "GFGPGPO-------X",
        "GFGPGPOD------X",
        "GFGPGPOP------X",
        "GFGPGPOR------X",
        "GFGPGPOW------X",
        "GFGPGPOZ------X",
        "GFGPGPP-------X",
        "GFGPGPPC------X",
        "GFGPGPPD------X",
        "GFGPGPPE------X",
        "GFGPGPPK------X",
        "GFGPGPPL------X",
        "GFGPGPPO------X",
        "GFGPGPPP------X",
        "GFGPGPPR------X",
        "GFGPGPPS------X",
        "GFGPGPPW------X",
        "GFGPGPRD------X",
        "GFGPGPRI------X",
        "GFGPGPRN------X",
        "GFGPGPRS------X",
        "GFGPGPUS------X",
        "GFGPGPUSA-----X",
        "GFGPGPUSC-----X",
        "GFGPGPUSD-----X",
        "GFGPGPUUB-----X",
        "GFGPGPUUD-----X",
        "GFGPGPUUL-----X",
        "GFGPGPUUS-----X",
        "GFGPGPUY------X",
        "GFGPGPUYA-----X",
        "GFGPGPUYK-----X",
        "GFGPGPUYP-----X",
        "GFGPGPUYR-----X",
        "GFGPGPUYT-----X",
        "GFGPGPWA------X",
        "GFGPGPWD------X",
        "GFGPGPWE------X",
        "GFGPGPWG------X",
        "GFGPGPWI------X",
        "GFGPGPWM------X",
        "GFGPGPWP------X",
        "GFGPOPP-------X",
        "GFMPBCP-------X",
        "GFMPNEB-------X",
        "GFMPNEC-------X",
        "GFMPNF--------X",
        "GFMPNZ--------X",
        "GFMPOB--------X",
        "GFMPOMD-------X",
        "GFMPOME-------X",
        "GFMPOMP-------X",
        "GFMPOMT-------X",
        "GFMPOMU-------X",
        "GFMPOMW-------X",
        "GFMPSE--------X",
        "GFMPSF--------X",
        "GFMPSS--------X",
        "GFMPSU--------X",
        "GFMPNDP-------X",
        "GFOPED--------X",
        "GFOPEP--------X",
        "GFOPEV--------X",
        "GFOPFA--------X",
        "GFOPFE--------X",
        "GFOPFO--------X",
        "GFOPHI--------X",
        "GFOPHM--------X",
        "GFOPHO--------X",
        "GFOPSB--------X",
        "GFOPSBM-------X",
        "GFOPSBN-------X",
        "GFOPSBW-------X",
        "GFOPSBWD------X",
        "GFOPSM--------X",
        "GFOPSS--------X",
        "GFSPPAS-------X",
        "GFSPPAT-------X",
        "GFSPPC--------X",
        "GFSPPD--------X",
        "GFSPPE--------X",
        "GFSPPI--------X",
        "GFSPPL--------X",
        "GFSPPM--------X",
        "GFSPPN--------X",
        "GFSPPO--------X",
        "GFSPPR--------X",
        "GFSPPSA-------X",
        "GFSPPSB-------X",
        "GFSPPSC-------X",
        "GFSPPSD-------X",
        "GFSPPSE-------X",
        "GFSPPSF-------X",
        "GFSPPSG-------X",
        "GFSPPSH-------X",
        "GFSPPSI-------X",
        "GFSPPSJ-------X",
        "GFSPPSZ-------X",
        "GFSPPT--------X",
        "GFSPPU--------X",
        "GFSPPX--------X",
        "GFSPPY--------X"
    };

    /**
     * Appendix C: Meteorological and Oceanographic Symbology
     * <p/>
     * Note that this list includes only point graphics. Line and area graphics are not retrieved from the server.
     */
    protected final static String[] MeteorologicalSymbolCodes = {
        "WAS-CCCSBCP----",
        "WAS-CCCSCSP----",
        "WAS-CCCSFCP----",
        "WAS-CCCSOBP----",
        "WAS-CCCSOCP----",
        "WAS-CCCSSCP----",
        "WAS-GNDEWCP----",
        "WAS-GNFL--P----",
        "WAS-GNFZ--P----",
        "WAS-GNG-TIP----",
        "WAS-GNM---P----",
        "WAS-GSI---P----",
        "WAS-GSSDC-P----",
        "WAS-ICL---P----",
        "WAS-ICM---P----",
        "WAS-ICS---P----",
        "WAS-IML---P----",
        "WAS-IMM---P----",
        "WAS-IMS---P----",
        "WAS-IRL---P----",
        "WAS-IRM---P----",
        "WAS-IRS---P----",
        "WAS-PA----P----",
        "WAS-PC----P----",
        "WAS-PH----P----",
        "WAS-PHT---P----",
        "WAS-PL----P----",
        "WAS-PLT---P----",
        "WAS-T-MW--P----",
        "WAS-TE----P----",
        "WAS-TL----P----",
        "WAS-TM----P----",
        "WAS-TS----P----",
        "WAS-WC----P----",
        "WAS-WSBD--P----",
        "WAS-WSBR--P----",
        "WAS-WSD-HCP----",
        "WAS-WSD-HIP----",
        "WAS-WSD-LCP----",
        "WAS-WSD-LIP----",
        "WAS-WSD-MCP----",
        "WAS-WSD-MIP----",
        "WAS-WSDD--P----",
        "WAS-WSDFL-P----",
        "WAS-WSDFMHP----",
        "WAS-WSDSLMP----",
        "WAS-WSDSS-P----",
        "WAS-WSF-LVP----",
        "WAS-WSFGCSP----",
        "WAS-WSFGFOP----",
        "WAS-WSFGFVP----",
        "WAS-WSFGP-P----",
        "WAS-WSFGPSP----",
        "WAS-WSFGSOP----",
        "WAS-WSFGSVP----",
        "WAS-WSFU--P----",
        "WAS-WSGRL-P----",
        "WAS-WSGRMHP----",
        "WAS-WSHZ--P----",
        "WAS-WSIC--P----",
        "WAS-WSM-L-P----",
        "WAS-WSM-MHP----",
        "WAS-WSMSL-P----",
        "WAS-WSMSMHP----",
        "WAS-WSPLH-P----",
        "WAS-WSPLL-P----",
        "WAS-WSPLM-P----",
        "WAS-WSR-HCP----",
        "WAS-WSR-HIP----",
        "WAS-WSR-LCP----",
        "WAS-WSR-LIP----",
        "WAS-WSR-MCP----",
        "WAS-WSR-MIP----",
        "WAS-WSRFL-P----",
        "WAS-WSRFMHP----",
        "WAS-WSRSL-P----",
        "WAS-WSRSMHP----",
        "WAS-WSRST-P----",
        "WAS-WSS-HCP----",
        "WAS-WSS-HIP----",
        "WAS-WSS-LCP----",
        "WAS-WSS-LIP----",
        "WAS-WSS-MCP----",
        "WAS-WSS-MIP----",
        "WAS-WSSBH-P----",
        "WAS-WSSBLMP----",
        "WAS-WSSG--P----",
        "WAS-WSSSL-P----",
        "WAS-WSSSMHP----",
        "WAS-WST-FCP----",
        "WAS-WST-LGP----",
        "WAS-WST-LVP----",
        "WAS-WST-NPP----",
        "WAS-WST-SQP----",
        "WAS-WSTHH-P----",
        "WAS-WSTHR-P----",
        "WAS-WSTMH-P----",
        "WAS-WSTMR-P----",
        "WAS-WSTSD-P----",
        "WAS-WSTSH-P----",
        "WAS-WSTSS-P----",
        "WAS-WSUKP-P----",
        "WAS-WSVA--P----",
        "WAS-WSVE--P----",
        "WO-DHALLA--L---",
        "WO-DHHDB---L---",
        "WO-DHHDK--P----",
        "WO-DHPBA----A--",
        "WO-DHPBA---L---",
        "WO-DHPMO---L---",
        "WO-DHPMO--P----",
        "WO-DIDID---L---",
        "WOS-BFC-CBP----",
        "WOS-BFC-CLP----",
        "WOS-BFC-COP----",
        "WOS-BFC-G-P----",
        "WOS-BFC-M-P----",
        "WOS-BFC-P-P----",
        "WOS-BFC-R-P----",
        "WOS-BFC-S-P----",
        "WOS-BFC-SHP----",
        "WOS-BFC-SIP----",
        "WOS-BFC-STP----",
        "WOS-BFQ-C-P----",
        "WOS-BFQ-F-P----",
        "WOS-BFQ-M-P----",
        "WOS-HABA--P----",
        "WOS-HABB--P----",
        "WOS-HABM--P----",
        "WOS-HABP--P----",
        "WOS-HAL---P----",
        "WOS-HALH--P----",
        "WOS-HALV--P----",
        "WOS-HDS---P----",
        "WOS-HHDE--P----",
        "WOS-HHDF--P----",
        "WOS-HHDMDBP----",
        "WOS-HHDMDFP----",
        "WOS-HHDR---L---",
        "WOS-HHDS--P----",
        "WOS-HHDWA-P----",
        "WOS-HHDWB-P----",
        "WOS-HHRA--P----",
        "WOS-HHRS--P----",
        "WOS-HPB-A-P----",
        "WOS-HPB-O-P----",
        "WOS-HPBA--P----",
        "WOS-HPCP--P----",
        "WOS-HPD---P----",
        "WOS-HPFF----A--",
        "WOS-HPFH--P----",
        "WOS-HPFS---L---",
        "WOS-HPFS--P----",
        "WOS-HPM-CC-L---",
        "WOS-HPM-FC-L---",
        "WOS-HPM-R-P----",
        "WOS-HPML--P----",
        "WOS-IB----P----",
        "WOS-IBBB--P----",
        "WOS-IBBBM-P----",
        "WOS-IBBS--P----",
        "WOS-IBF---P----",
        "WOS-IBG---P----",
        "WOS-IBGL--P----",
        "WOS-IBGLM-P----",
        "WOS-IBII--P----",
        "WOS-IBM---P----",
        "WOS-IBMG--P----",
        "WOS-ICIF--P----",
        "WOS-ICWB--P----",
        "WOS-ICWR--P----",
        "WOS-IDC---P----",
        "WOS-IDD---P----",
        "WOS-IDS---P----",
        "WOS-II----P----",
        "WOS-IIP---P----",
        "WOS-ISC---P----",
        "WOS-ISS---P----",
        "WOS-ITBB--P----",
        "WOS-ITR---P----",
        "WOS-ITRH--P----",
        "WOS-MF----P----",
        "WOS-ML----P----",
        "WOS-MOA---P----",
        "WOS-MPA---P----",
        "WOS-TCCTD-P----",
        "WOS-TCCTG-P----",
        "WOS-TCCW--P----"
    };
}