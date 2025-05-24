package src.utils;

import src.models.Prodotto;
import src.models.categories.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Inserimento extends JPanel {
    // Campi comuni a tutti i prodotti
    private final JTextField nome = new JTextField();
    private final JTextField id = new JTextField();
    private final JTextField marca = new JTextField();
    private final JTextField prezzo = new JTextField();
    private final JButton selezionaImmagine = new JButton("Seleziona Immagine");
    private ImageIcon iconaSelezionata;
    private final JTextArea descrizione = new JTextArea();

    // ComboBox per la selezione della categoria
    private final JComboBox<String> categoriaComboBox = new JComboBox<>();

    // Pannello per i campi specifici della categoria
    private final JPanel specificFieldsPanel = new JPanel();

    // Mappa per memorizzare i campi specifici di ogni categoria
    private final Map<String, JComponent[]> specificFieldsMap = new HashMap<>();

    public Inserimento() {
        setLayout(new BorderLayout(10, 10));

        // Pannello per i campi comuni
        JPanel commonFieldsPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        JScrollPane descriptionScrollPane = new JScrollPane(descrizione);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descrizione.setLineWrap(true);
        descrizione.setWrapStyleWord(true);

        // Inizializza la ComboBox con le categorie disponibili
        initializeCategories();

        // Aggiungi i componenti al pannello dei campi comuni
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
        commonFieldsPanel.add(new JLabel("Immagine:"));
        commonFieldsPanel.add(selezionaImmagine);

        // Configura il listener per la ComboBox
        categoriaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSpecificFields();
            }
        });

        // Pannello per il pulsante di selezione immagine
        selezionaImmagine.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getPath());
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                iconaSelezionata = new ImageIcon(image);
            }
        });

        // Aggiungi i pannelli al layout principale
        add(commonFieldsPanel, BorderLayout.NORTH);
        add(new JScrollPane(specificFieldsPanel), BorderLayout.CENTER);

        // Aggiorna i campi specifici iniziali
        updateSpecificFields();
    }

    private void initializeCategories() {
        // Aggiungi tutte le categorie disponibili
        categoriaComboBox.addItem("ComputerDesktop");
        categoriaComboBox.addItem("AccessoriPC");
        categoriaComboBox.addItem("ComponentiPC");
        categoriaComboBox.addItem("UsatoGarantito");
        categoriaComboBox.addItem("AccessoriSmartPhone");
        categoriaComboBox.addItem("AudioVideoGaming");
        categoriaComboBox.addItem("Consumabili");
        categoriaComboBox.addItem("CasaEUfficio");
        categoriaComboBox.addItem("Cavetteria");
        categoriaComboBox.addItem("NotebookEAccessori");

        // Inizializza i campi specifici per ogni categoria
        initializeSpecificFields();
    }

    private void initializeSpecificFields() {
        // ComputerDesktop
        JTextField cpuField = new JTextField();
        JTextField gpuField = new JTextField();
        JTextField ramField = new JTextField();
        JTextField memoriaField = new JTextField();
        JTextField soField = new JTextField();
        specificFieldsMap.put("ComputerDesktop", new JComponent[]{
                new JLabel("CPU:"), cpuField,
                new JLabel("GPU:"), gpuField,
                new JLabel("RAM (GB):"), ramField,
                new JLabel("Memoria (GB):"), memoriaField,
                new JLabel("Sistema Operativo:"), soField
        });

        // AccessoriPC
        JTextField tipoAccessorioField = new JTextField();
        JTextField connettivitaField = new JTextField();
        JCheckBox rgbCheckBox = new JCheckBox();
        JTextField compatibilitaField = new JTextField();
        JTextField dimensioniField = new JTextField();
        JCheckBox ergonomicoCheckBox = new JCheckBox();
        specificFieldsMap.put("AccessoriPC", new JComponent[]{
                new JLabel("Tipo Accessorio:"), tipoAccessorioField,
                new JLabel("Connettività:"), connettivitaField,
                new JLabel("RGB:"), rgbCheckBox,
                new JLabel("Compatibilità:"), compatibilitaField,
                new JLabel("Dimensioni:"), dimensioniField,
                new JLabel("Ergonomico:"), ergonomicoCheckBox
        });

        // ComponentiPC
        JTextField tipoComponenteField = new JTextField();
        JTextField specificaTecnicaField = new JTextField();
        JTextField capacitaGBField = new JTextField();
        JTextField compatibilita2Field = new JTextField();
        specificFieldsMap.put("ComponentiPC", new JComponent[]{
                new JLabel("Tipo Componente:"), tipoComponenteField,
                new JLabel("Specifica Tecnica:"), specificaTecnicaField,
                new JLabel("Capacità (GB):"), capacitaGBField,
                new JLabel("Compatibilità:"), compatibilita2Field
        });

        // UsatoGarantito
        JTextField statoUsuraField = new JTextField();
        JTextField annoProduzioneField = new JTextField();
        JTextField mesiGaranziaField = new JTextField();
        JCheckBox accessoriOriginaliCheckBox = new JCheckBox();
        specificFieldsMap.put("UsatoGarantito", new JComponent[]{
                new JLabel("Stato Usura:"), statoUsuraField,
                new JLabel("Anno Produzione:"), annoProduzioneField,
                new JLabel("Mesi Garanzia:"), mesiGaranziaField,
                new JLabel("Accessori Originali:"), accessoriOriginaliCheckBox
        });

        // AccessoriSmartPhone
        JTextField tipoMaterialeField = new JTextField();
        JTextField capacitaPowerBankField = new JTextField();
        JCheckBox ricaricaWirelessCheckBox = new JCheckBox();
        JCheckBox magneticoCheckBox = new JCheckBox();
        specificFieldsMap.put("AccessoriSmartPhone", new JComponent[]{
                new JLabel("Tipo Materiale:"), tipoMaterialeField,
                new JLabel("Capacità PowerBank:"), capacitaPowerBankField,
                new JLabel("Ricarica Wireless:"), ricaricaWirelessCheckBox,
                new JLabel("Magnetico:"), magneticoCheckBox
        });

        // AudioVideoGaming
        JTextField tipoProdottoField = new JTextField();
        JTextField polliciField = new JTextField();
        JTextField hzField = new JTextField();
        JCheckBox supportaHDRCheckBox = new JCheckBox();
        JCheckBox wirelessCheckBox = new JCheckBox();
        specificFieldsMap.put("AudioVideoGaming", new JComponent[]{
                new JLabel("Tipo Prodotto:"), tipoProdottoField,
                new JLabel("Pollici:"), polliciField,
                new JLabel("Hz:"), hzField,
                new JLabel("Supporta HDR:"), supportaHDRCheckBox,
                new JLabel("Wireless:"), wirelessCheckBox
        });

        // Consumabili
        JTextField quantitaField = new JTextField();
        JTextField unitaMisuraField = new JTextField();
        specificFieldsMap.put("Consumabili", new JComponent[]{
                new JLabel("Quantità:"), quantitaField,
                new JLabel("Unità di Misura:"), unitaMisuraField
        });

        // CasaEUfficio
        JTextField tecnologiaField = new JTextField();
        JTextField velocitaStampaField = new JTextField();
        JCheckBox fronteRetroCheckBox = new JCheckBox();
        JCheckBox wifiIntegratoCheckBox = new JCheckBox();
        specificFieldsMap.put("CasaEUfficio", new JComponent[]{
                new JLabel("Tecnologia:"), tecnologiaField,
                new JLabel("Velocità Stampa (PPM):"), velocitaStampaField,
                new JLabel("Fronte/Retro:"), fronteRetroCheckBox,
                new JLabel("WiFi Integrato:"), wifiIntegratoCheckBox
        });

        // Cavetteria
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

        // NotebookEAccessori
        JTextField dimensioneDisplayField = new JTextField();
        JTextField risoluzioneField = new JTextField();
        JCheckBox touchscreenCheckBox = new JCheckBox();
        JTextField autonomiaField = new JTextField();
        JTextField cpu2Field = new JTextField();
        JTextField ram2Field = new JTextField();
        specificFieldsMap.put("NotebookEAccessori", new JComponent[]{
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
        iconaSelezionata = null;
        descrizione.setText("");

        // Resetta tutti i campi specifici
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

            // Creazione del prodotto in base alla categoria selezionata
            switch (selectedCategory) {
                case "ComputerDesktop":
                    return new ComputerDesktop(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // CPU
                            ((JTextField) fields[3]).getText(),  // GPU
                            Integer.parseInt(((JTextField) fields[5]).getText()),  // RAM
                            Integer.parseInt(((JTextField) fields[7]).getText()),  // Memoria
                            ((JTextField) fields[9]).getText()   // SO
                    );

                case "AccessoriPC":
                    return new AccessoriPC(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // Tipo Accessorio
                            ((JTextField) fields[3]).getText(),  // Connettività
                            ((JCheckBox) fields[5]).isSelected(),  // RGB
                            ((JTextField) fields[7]).getText(),  // Compatibilità
                            ((JTextField) fields[9]).getText(),  // Dimensioni
                            ((JCheckBox) fields[11]).isSelected()  // Ergonomico
                    );

                case "ComponentiPC":
                    return new ComponentiPC(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // Tipo Componente
                            ((JTextField) fields[3]).getText(),  // Specifica Tecnica
                            Integer.parseInt(((JTextField) fields[5]).getText()),  // Capacità GB
                            ((JTextField) fields[7]).getText()   // Compatibilità
                    );

                case "UsatoGarantito":
                    return new UsatoGarantito(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // Stato Usura
                            Integer.parseInt(((JTextField) fields[3]).getText()),  // Anno Produzione
                            Integer.parseInt(((JTextField) fields[5]).getText()),  // Mesi Garanzia
                            ((JCheckBox) fields[7]).isSelected()  // Accessori Originali
                    );

                case "AccessoriSmartPhone":
                    return new AccessoriSmartPhone(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // Tipo Materiale
                            Integer.parseInt(((JTextField) fields[3]).getText()),  // Capacità PowerBank
                            ((JCheckBox) fields[5]).isSelected(),  // Ricarica Wireless
                            ((JCheckBox) fields[7]).isSelected()  // Magnetico
                    );

                case "AudioVideoGaming":
                    return new AudioVideoGaming(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // Tipo Prodotto
                            Integer.parseInt(((JTextField) fields[3]).getText()),  // Pollici
                            Integer.parseInt(((JTextField) fields[5]).getText()),  // Hz
                            ((JCheckBox) fields[7]).isSelected(),  // Supporta HDR
                            ((JCheckBox) fields[9]).isSelected()  // Wireless
                    );

                case "Consumabili":
                    return new Consumabili(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            Double.parseDouble(((JTextField) fields[1]).getText()),  // Quantità
                            ((JTextField) fields[3]).getText()  // Unità di Misura
                    );

                case "CasaEUfficio":
                    return new CasaEUfficio(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // Tecnologia
                            Integer.parseInt(((JTextField) fields[3]).getText()),  // Velocità Stampa
                            ((JCheckBox) fields[5]).isSelected(),  // Fronte/Retro
                            ((JCheckBox) fields[7]).isSelected()  // WiFi Integrato
                    );

                case "Cavetteria":
                    return new Cavetteria(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            ((JTextField) fields[1]).getText(),  // Tipo Connettori
                            Double.parseDouble(((JTextField) fields[3]).getText()),  // Lunghezza
                            ((JCheckBox) fields[5]).isSelected(),  // Supporta 4K
                            ((JCheckBox) fields[7]).isSelected()  // Rinforzato
                    );

                case "NotebookEAccessori":
                    return new NotebookEAccessori(
                            nome.getText(), id.getText(), marca.getText(), selectedCategory,
                            iconaSelezionata, Double.parseDouble(prezzo.getText()), descrizione.getText(),
                            Double.parseDouble(((JTextField) fields[1]).getText()),  // Dimensione Display
                            ((JTextField) fields[3]).getText(),  // Risoluzione
                            ((JCheckBox) fields[5]).isSelected(),  // Touchscreen
                            Integer.parseInt(((JTextField) fields[7]).getText()),  // Autonomia
                            ((JTextField) fields[9]).getText(),  // CPU
                            Integer.parseInt(((JTextField) fields[11]).getText())  // RAM
                    );

                default:
                    throw new IllegalArgumentException("Categoria non supportata: " + selectedCategory);
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