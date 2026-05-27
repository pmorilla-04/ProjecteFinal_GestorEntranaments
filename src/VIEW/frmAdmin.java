package VIEW;

import CONTROLLER.GestiorFitxersTXT;
import DATA.Querys;
import MODEL.Entrenament;
import MODEL.Estadistica;
import MODEL.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class frmAdmin extends JFrame {

    private JTable tblInfo;

    private JComboBox<String> cbmInfo;
    private JTextField txtData;

    private JButton btnEntrenaments;
    private JButton btnUsuaris;

    public frmAdmin() {

        setTitle("Panell Administrador");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        txtData.setEnabled(false);

        Querys.mostrarEntrenaments();
        omplirEntrenaments();

        afegirListenerData();
    }

    private void initComponents() {

        Color bg = new Color(20, 20, 20);
        Color panel = new Color(35, 35, 35);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(bg);

        // ================= HEADER =================

        JLabel lblTitol = new JLabel("ADMINISTRADOR");
        lblTitol.setForeground(Color.RED);
        lblTitol.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitol.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        principal.add(lblTitol, BorderLayout.NORTH);

        // ================= CONTENT =================

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBackground(bg);
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ================= TOP PANEL =================

        JPanel top = new JPanel(null);
        top.setPreferredSize(new Dimension(1000, 120));
        top.setBackground(panel);

        JLabel lblData = new JLabel("DATA");
        lblData.setForeground(Color.WHITE);
        lblData.setBounds(30, 20, 100, 25);
        top.add(lblData);

        txtData = new JTextField();
        txtData.setBounds(30, 50, 150, 35);
        top.add(txtData);

        JLabel lblInfo = new JLabel("INFORMACIÓ");
        lblInfo.setForeground(Color.WHITE);
        lblInfo.setBounds(250, 20, 150, 25);
        top.add(lblInfo);

        cbmInfo = new JComboBox<>(new String[]{
                "Estadístiques",
                "Registres",
                "Entrenaments"
        });

        cbmInfo.setBounds(250, 50, 200, 35);
        top.add(cbmInfo);

        content.add(top, BorderLayout.NORTH);

        // ================= TABLE =================

        tblInfo = new JTable();
        JScrollPane sp = new JScrollPane(tblInfo);

        content.add(sp, BorderLayout.CENTER);

        // ================= BUTTONS =================

        JPanel bottom = new JPanel();
        bottom.setBackground(panel);

        btnEntrenaments = new JButton("GESTIÓ ENTRENAMENTS");
        btnUsuaris = new JButton("GESTIÓ USUARIS");

        estilBoto(btnEntrenaments, new Color(0, 150, 150));
        estilBoto(btnUsuaris, new Color(255, 120, 0));

        bottom.add(btnEntrenaments);
        bottom.add(btnUsuaris);

        content.add(bottom, BorderLayout.SOUTH);

        principal.add(content);

        add(principal);

        // EVENTS
        cbmInfo.addActionListener(e -> canviarSeccio());

        btnEntrenaments.addActionListener(e -> {
            new frmEntranaments().setVisible(true);
            GestiorFitxersTXT.escripturaAFitxerLog("Obert formulari entrenaments");
        });

        btnUsuaris.addActionListener(e -> {
            new frmUsuari().setVisible(true);
            GestiorFitxersTXT.escripturaAFitxerLog("Obert formulari usuaris");
        });
    }

    // ================= ESTILS =================

    private void estilBoto(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // ================= LOGICA =================

    private void afegirListenerData() {

        txtData.addActionListener(e -> {

            String data = txtData.getText();

            GestiorFitxersTXT.escripturaAFitxerLog(
                    "Cercant logs: " + data
            );

            GestiorFitxersTXT.mostrarLogsPerData(data);

            omplirRegistres();
        });
    }

    private void canviarSeccio() {

        String opcio = (String) cbmInfo.getSelectedItem();

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Secció: " + opcio
        );

        switch (opcio) {

            case "Estadístiques":
                txtData.setEnabled(false);
                Querys.mostrarEstadistiques();
                omplirEstadistiques();
                break;

            case "Registres":
                txtData.setEnabled(true);
                omplirRegistres();
                break;

            case "Entrenaments":
                txtData.setEnabled(false);
                Querys.mostrarEntrenaments();
                omplirEntrenaments();
                break;
        }
    }

    // ================= TAULES =================

    private void omplirEntrenaments() {

        String[] cols = {
                "ID", "DATA", "DURADA", "DISTÀNCIA",
                "DESCRIPCIÓ", "INTENSITAT", "COMPLETAT",
                "ID USUARI", "TIPUS"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);

        for (Entrenament e : CONTROLLER.Principal.entrenaments) {

            Object[] fila = {
                    e.getId(),
                    e.getData(),
                    e.getDuradaMinuts(),
                    e.getDistancia(),
                    e.getDescripcio(),
                    e.getIntensitat(),
                    e.isCompletat(),
                    e.getUsuariId(),
                    e.getTipusEsportId()
            };

            model.addRow(fila);
        }

        tblInfo.setModel(model);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Taula entrenaments carregada"
        );
    }

    private void omplirRegistres() {

        String[] cols = {"HORA", "MISSATGE"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);

        for (Log l : CONTROLLER.Principal.logs) {

            Object[] fila = {
                    l.getHora(),
                    l.getMissatge()
            };

            model.addRow(fila);
        }

        tblInfo.setModel(model);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Taula logs carregada"
        );
    }

    private void omplirEstadistiques() {

        String[] cols = {
                "ESPORT", "ENTRENAMENTS", "USUARIS",
                "MINUTS", "KM", "MITJANA", "COMPLETATS"
        };

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);

        for (Estadistica e : CONTROLLER.Principal.estadistiques) {

            Object[] fila = {
                    e.getEsport(),
                    e.getTotalEntrenaments(),
                    e.getUsuarisActius(),
                    e.getMinutsTotals(),
                    e.getKmTotals(),
                    e.getMitjanaDurada(),
                    e.getCompletats()
            };

            model.addRow(fila);
        }

        tblInfo.setModel(model);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Taula estadístiques carregada"
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frmAdmin().setVisible(true));
    }
}