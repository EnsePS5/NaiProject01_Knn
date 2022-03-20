package main.java;

import javax.swing.*;

public class PreparedSimulation {

    PreparedData data;

    public PreparedSimulation(PreparedData data){
        SwingUtilities.invokeLater(this::createGUI);
        this.data = data;
    }
    private void createGUI(){

        String mainTitle = "NAIProject01_Knn";

        JFrame frame = new JFrame();
        frame.setTitle(mainTitle);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setVisible(true);

    }
}
