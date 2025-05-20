import javax.swing.*;
import java.awt.*;

public class PannelloLaterale extends JPanel {

    String[] categorie = {
            "Accessori PC", "Accessori SmartPhone", "Audio, Video e Gaming",
            "Casa e Ufficio", "Cavetteria", "Componenti PC",
            "Computer Desktop", "Consumabili", "Notebook e Accessori",
            "Usato Garantito"
    };

    public PannelloLaterale() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(50, 50, 50));

        JLabel label = new JLabel("Categorie");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(label, BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String categoria : categorie) {
            model.addElement(categoria);
        }

        JList<String> listaCategorie = new JList<>(model);
        listaCategorie.setFont(new Font("Arial", Font.PLAIN, 14));
        listaCategorie.setBackground(new Color(70, 70, 70));
        listaCategorie.setForeground(Color.WHITE);
        listaCategorie.setSelectionBackground(new Color(100, 150, 200));
        listaCategorie.setSelectionForeground(Color.BLACK);
        listaCategorie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane scroller = new JScrollPane(listaCategorie);
        scroller.setBorder(BorderFactory.createEmptyBorder());
        add(scroller, BorderLayout.CENTER);

        setPreferredSize(new Dimension(200, 400));
    }
}
