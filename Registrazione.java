import javax.swing.*;
import java.awt.*;

public class Registrazione extends JFrame {

    GestionePannelloRegistrazione gestionePannelloRegistrazione = new GestionePannelloRegistrazione();

    public Registrazione(){
        super("Registrazione");

        setLayout(new BorderLayout());

        add(gestionePannelloRegistrazione,BorderLayout.WEST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        setVisible(true);
    }

    public static void main(String [] args){
        new Registrazione();
    }
}