package com.jscrot;

import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;


public class jscrot {

    // Take a screen shot
    public static BufferedImage scrot() throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        return robot.createScreenCapture(screenRectangle);
    }

    public static void takeScreenshot() throws Exception {
        // Take the screen shot
        BufferedImage screenShot = scrot();
        // Choose file
        final JFileChooser fc = new JFileChooser();
        //fc.addChoosableFileFilter(new ImageFilter());
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file (.jpg)", "jpg", "jpeg", "JPG", "JPEG"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("PNG file (.png)", "png", "PNG"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("GIF file (.gif)", "gif", "GIF"));
        fc.setFileFilter(fc.getChoosableFileFilters()[1]);

        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = new File(fc.getSelectedFile().getCanonicalPath() + "." + ((FileNameExtensionFilter) fc.getFileFilter()).getExtensions()[0]);

            try {
                ImageIO.write(screenShot, Utils.getExtension(file), file);
            } catch (Exception e) {
                System.out.println("Error: You selected an invalid file type!");
            }
        }
    }
    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = jscrot.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    public static void main(String[] args) throws Exception {

        // If tray icon is NOT supported:
        if (!SystemTray.isSupported()) {
            // Notice
            System.out.println("Tray icons are not supported in this platform. A screenshot is going to be taken now.");
            // Try and take a screenshot
            try {
                takeScreenshot();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // Exit
            System.out.println("Thanks for using JTakeScrot!");
            System.exit(0);
        }

        // Try and make things look nicer
        try {
            // Set the Look and Feel of the application to the operating
            // system's look and feel.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Couldn't set native look and feel. Using swing instead.");
        }
        // Create the tray icon

        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("images/bulb.gif", "jscrot"));
        final SystemTray tray = SystemTray.getSystemTray();
        // Create a popup menu components
        MenuItem exitItem = new MenuItem("Exit");

        //Add components to popup menu
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    takeScreenshot();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }
}


class Utils {


    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}