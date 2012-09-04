package View;

/**
 * @(#) TermsOfLicenceFrame: Creates a frame that holds terms of license
 * 
 *  @ Can Bayar
 * @version 1.00 
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class TermsOfLicenceFrame extends JFrame 
{
	//Properties
	TextArea termsTA;
	
	public TermsOfLicenceFrame()
	{
		File file = new File("Terms.txt");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        String text = null;

        try 
        {
            reader = new BufferedReader(new FileReader(file));

            while ((text = reader.readLine()) != null) 
            {
                contents.append(text).append(System.getProperty( "line.separator"));
            }
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                if (reader != null) 
                {
                    reader.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
		
		termsTA = new TextArea( contents.toString(), 5, 40);
		termsTA.setEditable( false);
		
		add( termsTA, BorderLayout.CENTER);
		
		setPreferredSize( new Dimension( 700, 450));
		setLocation( 50, 50);
		pack();
		setVisible( true);
		setFocusable( true);
	}
	
	
}
