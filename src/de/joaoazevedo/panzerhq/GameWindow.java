package de.joaoazevedo.panzerhq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    private final GamePanel panzerGamePanel;

    public GameWindow() {

        this.panzerGamePanel = new GamePanel();

        JPanel fenster = new JPanel();
        fenster.setPreferredSize(new Dimension(800,600));

        registerWindowListener();
        createMenu();

        add(panzerGamePanel);
        pack();

        add(fenster);
        pack();

        setTitle("PanzerHQ");
        setLocation(10, 10);
        setResizable(false);

        setVisible(true);
    }

    private void createMenu() {

        JMenuBar menuLeiste = new JMenuBar();
        this.setJMenuBar(menuLeiste);

        JMenu dateiMenu = new JMenu("Datei");
        JMenu spielMenu = new JMenu("Spiel");
        JMenu einstellungenMenu = new JMenu("Einstellungen");

        menuLeiste.add(dateiMenu);
        menuLeiste.add(spielMenu);
        menuLeiste.add(einstellungenMenu);

        addFileMenuItems(dateiMenu);
        addGameMenuItems(spielMenu);
    }

    private void addFileMenuItems(JMenu dateiMenu) {

        JMenuItem beenden = new JMenuItem("Beenden");
        dateiMenu.add(beenden);
        beenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(0);

                //Fragt, ob der Nutzer das Spiel beenden möchte
                String[] ObjButtons = {"Ja", "Nein"};
                int promptResult = JOptionPane.showOptionDialog(null, "Möchstest du das Spiel wirklich beenden?", "Spiel beenden", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                if(promptResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void addGameMenuItems(JMenu gameMenu) {

        JMenuItem pauseItem = new JMenuItem("Pause");
        gameMenu.add(pauseItem);
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.pauseGame();
            }
        });

        JMenuItem continueItem = new JMenuItem("Continue");
        gameMenu.add(continueItem);
        continueItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.continueGame();
            }
        });

        gameMenu.addSeparator();
        JMenuItem restartItem = new JMenuItem("Restart");
        gameMenu.add(restartItem);
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.restartGame();
            }
        });
    }

    private void registerWindowListener() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }

            @Override
            public void windowClosing(WindowEvent e) {
                String[] ObjButtons = {"Ja", "Nein"};
                int promptResult = JOptionPane.showOptionDialog(null, "Möchstest du das Spiel wirklich beenden?", "Spiel beenden", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                if(promptResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                panzerGamePanel.pauseGame();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                panzerGamePanel.continueGame();
            }
        });
    }
}
