package src.view.admin.prodotti;

import src.models.Prodotto;
import src.models.categories.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inserimento extends JPanel {

    private final JTextField nome = new JTextField();
    private final JTextField id = new JTextField();
    private final JTextField marca = new JTextField();
    private final JTextField prezzo = new JTextField();
    private final JTextField urlImage = new JTextField();
    private final JButton verificaImmagine = new JButton("Verifica Immagine");
    private ImageIcon iconaSelezionata;
    private final JTextArea descrizione = new JTextArea();


    private final JComboBox<String> categoriaComboBox = new JComboBox<>();


    private final JPanel specificFieldsPanel = new JPanel();


    private final Map<String, JComponent[]> specificFieldsMap = new HashMap<>();

    public Inserimento() {
        setLayout(new BorderLayout(10, 10));


        JPanel commonFieldsPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        JScrollPane descriptionScrollPane = new JScrollPane(descrizione);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descrizione.setLineWrap(true);
        descrizione.setWrapStyleWord(true);


        initializeCategories();


        commonFieldsPanel.add(new JLabel("Categoria:"));
        commonFieldsPanel.add(categoriaComboBox);
        commonFieldsPanel.add(new JLabel("Nome:"));
        commonFieldsPanel.add(nome);
        commonFieldsPanel.add(new JLabel("ID:"));
        commonFieldsPanel.add(id);
        commonFieldsPanel.add(new JLabel("Marca:"));
        commonFieldsPanel.add(marca);
        commonFieldsPanel.add(new JLabel("Prezzo:"));
        commonFieldsPanel.add(prezzo);
        commonFieldsPanel.add(new JLabel("Descrizione:"));
        commonFieldsPanel.add(descriptionScrollPane);
        commonFieldsPanel.add(new JLabel("URL Immagine:"));
        commonFieldsPanel.add(urlImage);
        commonFieldsPanel.add(new JLabel(""));
        commonFieldsPanel.add(verificaImmagine);


        categoriaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSpecificFields();
            }
        });


        verificaImmagine.addActionListener(e -> {
            try {
                String imageUrl = urlImage.getText();
                if (!imageUrl.isEmpty()) {
                    ImageIcon icon = new ImageIcon(new URL(imageUrl));
                    Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    iconaSelezionata = new ImageIcon(image);
                    JOptionPane.showMessageDialog(this, "Immagine verificata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (MalformedURLException ex) {
                JOptionPane.showMessageDialog(this, "URL non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                iconaSelezionata = null;
            }
        });


        add(commonFieldsPanel, BorderLayout.NORTH);
        add(new JScrollPane(specificFieldsPanel), BorderLayout.CENTER);


        updateSpecificFields();
    }

    private void initializeCategories() {

        categoriaComboBox.addItem("Computer Desktop");
        categoriaComboBox.addItem("Accessori PC");
        categoriaComboBox.addItem("Componenti PC");
        categoriaComboBox.addItem("Usato Garantito");
        categoriaComboBox.addItem("Accessori SmartPhone");
        categoriaComboBox.addItem("Audio Video Gaming");
        categoriaComboBox.addItem("Consumabili");
        categoriaComboBox.addItem("Casa E Ufficio");
        categoriaComboBox.addItem("Cavetteria");
        categoriaComboBox.addItem("Notebook E Accessori");


        initializeSpecificFields();
    }

    private void initializeSpecificFields() {

        JTextField cpuField = new JTextField();
        JTextField gpuField = new JTextField();
        JTextField ramField = new JTextField();
        JTextField memoriaField = new JTextField();
        JTextField soField = new JTextField();
        specificFieldsMap.put("Computer Desktop", new JComponent[]{
                new JLabel("CPU:"), cpuField,
                new JLabel("GPU:"), gpuField,
                new JLabel("RAM (GB):"), ramField,
                new JLabel("Memoria (GB):"), memoriaField,
                new JLabel("Sistema Operativo:"), soField
        });


        JTextField tipoAccessorioField = new JTextField();
        JTextField connettivitaField = new JTextField();
        JCheckBox rgbCheckBox = new JCheckBox();
        JTextField compatibilitaField = new JTextField();
        JTextField dimensioniField = new JTextField();
        JCheckBox ergonomicoCheckBox = new JCheckBox();
        specificFieldsMap.put("Accessori PC", new JComponent[]{
                new JLabel("Tipo Accessorio:"), tipoAccessorioField,
                new JLabel("Connettività:"), connettivitaField,
                new JLabel("RGB:"), rgbCheckBox,
                new JLabel("Compatibilità:"), compatibilitaField,
                new JLabel("Dimensioni:"), dimensioniField,
                new JLabel("Ergonomico:"), ergonomicoCheckBox
        });


        JTextField tipoComponenteField = new JTextField();
        JTextField specificaTecnicaField = new JTextField();
        JTextField capacitaGBField = new JTextField();
        JTextField compatibilita2Field = new JTextField();
        specificFieldsMap.put("Componenti PC", new JComponent[]{
                new JLabel("Tipo Componente:"), tipoComponenteField,
                new JLabel("Specifica Tecnica:"), specificaTecnicaField,
                new JLabel("Capacità (GB):"), capacitaGBField,
                new JLabel("Compatibilità:"), compatibilita2Field
        });


        JTextField statoUsuraField = new JTextField();
        JTextField annoProduzioneField = new JTextField();
        JTextField mesiGaranziaField = new JTextField();
        JCheckBox accessoriOriginaliCheckBox = new JCheckBox();
        specificFieldsMap.put("Usato Garantito", new JComponent[]{
                new JLabel("Stato Usura:"), statoUsuraField,
                new JLabel("Anno Produzione:"), annoProduzioneField,
                new JLabel("Mesi Garanzia:"), mesiGaranziaField,
                new JLabel("Accessori Originali:"), accessoriOriginaliCheckBox
        });


        JTextField tipoMaterialeField = new JTextField();
        JTextField capacitaPowerBankField = new JTextField();
        JCheckBox ricaricaWirelessCheckBox = new JCheckBox();
        JCheckBox magneticoCheckBox = new JCheckBox();
        specificFieldsMap.put("Accessori SmartPhone", new JComponent[]{
                new JLabel("Tipo Materiale:"), tipoMaterialeField,
                new JLabel("Capacità PowerBank:"), capacitaPowerBankField,
                new JLabel("Ricarica Wireless:"), ricaricaWirelessCheckBox,
                new JLabel("Magnetico:"), magneticoCheckBox
        });


        JTextField tipoProdottoField = new JTextField();
        JTextField polliciField = new JTextField();
        JTextField hzField = new JTextField();
        JCheckBox supportaHDRCheckBox = new JCheckBox();
        JCheckBox wirelessCheckBox = new JCheckBox();
        specificFieldsMap.put("Audio Video Gaming", new JComponent[]{
                new JLabel("Tipo Prodotto:"), tipoProdottoField,
                new JLabel("Pollici:"), polliciField,
                new JLabel("Hz:"), hzField,
                new JLabel("Supporta HDR:"), supportaHDRCheckBox,
                new JLabel("Wireless:"), wirelessCheckBox
        });


        JTextField quantitaField = new JTextField();
        JTextField unitaMisuraField = new JTextField();
        specificFieldsMap.put("Consumabili", new JComponent[]{
                new JLabel("Quantità:"), quantitaField,
                new JLabel("Unità di Misura:"), unitaMisuraField
        });


        JTextField tecnologiaField = new JTextField();
        JTextField velocitaStampaField = new JTextField();
        JCheckBox fronteRetroCheckBox = new JCheckBox();
        JCheckBox wifiIntegratoCheckBox = new JCheckBox();
        specificFieldsMap.put("Casa E Ufficio", new JComponent[]{
                new JLabel("Tecnologia:"), tecnologiaField,
                new JLabel("Velocità Stampa (PPM):"), velocitaStampaField,
                new JLabel("Fronte/Retro:"), fronteRetroCheckBox,
                new JLabel("WiFi Integrato:"), wifiIntegratoCheckBox
        });


        JTextField tipoConnettoriField = new JTextField();
        JTextField lunghezzaField = new JTextField();
        JCheckBox supporta4KCheckBox = new JCheckBox();
        JCheckBox rinforzatoCheckBox = new JCheckBox();
        specificFieldsMap.put("Cavetteria", new JComponent[]{
                new JLabel("Tipo Connettori:"), tipoConnettoriField,
                new JLabel("Lunghezza (m):"), lunghezzaField,
                new JLabel("Supporta 4K:"), supporta4KCheckBox,
                new JLabel("Rinforzato:"), rinforzatoCheckBox
        });


        JTextField dimensioneDisplayField = new JTextField();
        JTextField risoluzioneField = new JTextField();
        JCheckBox touchscreenCheckBox = new JCheckBox();
        JTextField autonomiaField = new JTextField();
        JTextField cpu2Field = new JTextField();
        JTextField ram2Field = new JTextField();
        specificFieldsMap.put("Notebook E Accessori", new JComponent[]{
                new JLabel("Dimensione Display (pollici):"), dimensioneDisplayField,
                new JLabel("Risoluzione:"), risoluzioneField,
                new JLabel("Touchscreen:"), touchscreenCheckBox,
                new JLabel("Autonomia (ore):"), autonomiaField,
                new JLabel("CPU:"), cpu2Field,
                new JLabel("RAM (GB):"), ram2Field
        });
    }

    private void updateSpecificFields() {
        specificFieldsPanel.removeAll();
        specificFieldsPanel.setLayout(new GridLayout(0, 2, 10, 10));

        String selectedCategory = (String) categoriaComboBox.getSelectedItem();
        JComponent[] fields = specificFieldsMap.get(selectedCategory);

        if (fields != null) {
            for (JComponent component : fields) {
                specificFieldsPanel.add(component);
            }
        }

        specificFieldsPanel.revalidate();
        specificFieldsPanel.repaint();
    }

    public void resetFields() {
        nome.setText("");
        id.setText("");
        marca.setText("");
        prezzo.setText("");
        descrizione.setText("");
        urlImage.setText("");
        iconaSelezionata = null;


        for (JComponent[] components : specificFieldsMap.values()) {
            for (JComponent component : components) {
                if (component instanceof JTextField) {
                    ((JTextField) component).setText(descrizione.getText());
                } else if (component instanceof JCheckBox) {
                    ((JCheckBox) component).setSelected(false);
                }
            }
        }
    }

    public Prodotto getProdotto() {
        try {
            String selectedCategory = (String) categoriaComboBox.getSelectedItem();
            JComponent[] fields = specificFieldsMap.get(selectedCategory);
            switch (Objects.requireNonNull(selectedCategory)) {
                case "Computer Desktop":
                    return new ComputerDesktop(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            ((JTextField) fields[3]).getText(),
                            Integer.parseInt(((JTextField) fields[5]).getText()),
                            Integer.parseInt(((JTextField) fields[7]).getText()),
                            ((JTextField) fields[9]).getText()
                    );

                case "Accessori PC":
                    return new AccessoriPC(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            ((JTextField) fields[3]).getText(),
                            ((JCheckBox) fields[5]).isSelected(),
                            ((JTextField) fields[7]).getText(),
                            ((JTextField) fields[9]).getText(),
                            ((JCheckBox) fields[11]).isSelected()
                    );

                case "Componenti PC":
                    return new ComponentiPC(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            ((JTextField) fields[3]).getText(),
                            Integer.parseInt(((JTextField) fields[5]).getText()),
                            ((JTextField) fields[7]).getText()
                    );

                case "Usato Garantito":
                    return new UsatoGarantito(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            Integer.parseInt(((JTextField) fields[3]).getText()),
                            Integer.parseInt(((JTextField) fields[5]).getText()),
                            ((JCheckBox) fields[7]).isSelected()
                    );

                case "Accessori SmartPhone":
                    return new AccessoriSmartPhone(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            Integer.parseInt(((JTextField) fields[3]).getText()),
                            ((JCheckBox) fields[5]).isSelected(),
                            ((JCheckBox) fields[7]).isSelected()
                    );

                case "Audio Video Gaming":
                    return new AudioVideoGaming(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            Integer.parseInt(((JTextField) fields[3]).getText()),
                            Integer.parseInt(((JTextField) fields[5]).getText()),
                            ((JCheckBox) fields[7]).isSelected(),
                            ((JCheckBox) fields[9]).isSelected()
                    );

                case "Consumabili":
                    return new Consumabili(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            Double.parseDouble(((JTextField) fields[1]).getText()),
                            ((JTextField) fields[3]).getText()
                    );

                case "Casa E Ufficio":
                    return new CasaEUfficio(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            Integer.parseInt(((JTextField) fields[3]).getText()),
                            ((JCheckBox) fields[5]).isSelected(),
                            ((JCheckBox) fields[7]).isSelected()
                    );

                case "Cavetteria":
                    return new Cavetteria(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),
                            Double.parseDouble(((JTextField) fields[3]).getText()),
                            ((JCheckBox) fields[5]).isSelected(),
                            ((JCheckBox) fields[7]).isSelected()
                    );

                case "Notebook E Accessori":
                    return new NotebookEAccessori(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory.replaceAll("\\s+",""),
                            urlImage.getText(), Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            Double.parseDouble(((JTextField) fields[1]).getText()),
                            ((JTextField) fields[3]).getText(),
                            ((JCheckBox) fields[5]).isSelected(),
                            Integer.parseInt(((JTextField) fields[7]).getText()),
                            ((JTextField) fields[9]).getText(),
                            Integer.parseInt(((JTextField) fields[11]).getText())
                    );

                default:
                    throw new IllegalArgumentException("Categoria non supportata: " + selectedCategory.replaceAll("\\s+",""));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire valori numerici validi!", "Errore", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}