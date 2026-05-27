package VIEW;

import CONTROLLER.GestiorFitxersTXT;
import CONTROLLER.Principal;
import DATA.Querys;
import MODEL.Entrenament.Intensitat;
import MODEL.TipusEsport;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmEntranaments extends JFrame {

    // COMPONENTS
    private JTable tblEntrenaments;

    private JTextField txtId;
    private JTextField txtData;
    private JTextField txtDurada;
    private JTextField txtDistancia;
    private JTextField txtDescripcio;
    private JTextField txtUsuari;

    private JComboBox<String> cbmIntensitat;
    private JComboBox<TipusEsport> cbmTipus;

    private JCheckBox chkCompletat;

    private JButton btnNou;
    private JButton btnModificar;
    private JButton btnEliminar;

    public frmEntranaments() {

        setTitle("Gestio Entrenaments");
        setSize(1400, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        desactivarCamps();

        carregarTipusEsport();
        carregarIntensitats();

        Querys.mostrarEntrenaments();
        omplirTaulaEntrenaments();

        afegirListenerSeleccioTaula();
    }

    private void initComponents() {

        // COLORS
        Color bg = new Color(20, 20, 20);
        Color panel = new Color(35, 35, 35);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(bg);

        // =====================================
        // HEADER
        // =====================================

        JLabel lblTitol = new JLabel("GESTIO ENTRENAMENTS");

        lblTitol.setForeground(new Color(0, 200, 180));
        lblTitol.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitol.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        principal.add(lblTitol, BorderLayout.NORTH);

        // =====================================
        // CONTENT
        // =====================================

        JPanel content = new JPanel(new GridLayout(1, 2, 20, 20));

        content.setBackground(bg);
        content.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // =====================================
        // LEFT PANEL
        // =====================================

        JPanel leftPanel = new JPanel();

        leftPanel.setBackground(panel);
        leftPanel.setLayout(null);

        JLabel lblForm = new JLabel("DADES ENTRENAMENT");

        lblForm.setForeground(Color.WHITE);
        lblForm.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblForm.setBounds(30, 20, 300, 30);

        leftPanel.add(lblForm);

        // ID
        addLabel(leftPanel, "ID", 30, 80);
        txtId = addTextField(leftPanel, 30, 105);

        // DATA
        addLabel(leftPanel, "DATA (yyyy-MM-dd)", 220, 80);
        txtData = addTextField(leftPanel, 220, 105);

        // DURADA
        addLabel(leftPanel, "DURADA", 30, 170);
        txtDurada = addTextField(leftPanel, 30, 195);

        // DISTANCIA
        addLabel(leftPanel, "DISTÀNCIA", 220, 170);
        txtDistancia = addTextField(leftPanel, 220, 195);

        // INTENSITAT
        addLabel(leftPanel, "INTENSITAT", 30, 260);

        cbmIntensitat = new JComboBox<>();
        cbmIntensitat.setBounds(30, 290, 150, 35);

        leftPanel.add(cbmIntensitat);

        // TIPUS ESPORT
        addLabel(leftPanel, "TIPUS ESPORT", 220, 260);

        cbmTipus = new JComboBox<>();
        cbmTipus.setBounds(220, 290, 180, 35);

        leftPanel.add(cbmTipus);

        // USUARI
        addLabel(leftPanel, "ID USUARI", 30, 350);
        txtUsuari = addTextField(leftPanel, 30, 375);

        // CHECK
        chkCompletat = new JCheckBox("COMPLETAT");

        chkCompletat.setBounds(220, 375, 150, 30);
        chkCompletat.setForeground(Color.WHITE);
        chkCompletat.setBackground(panel);

        leftPanel.add(chkCompletat);

        // DESCRIPCIO
        addLabel(leftPanel, "DESCRIPCIÓ", 30, 450);

        txtDescripcio = new JTextField();

        txtDescripcio.setBounds(30, 480, 370, 40);

        leftPanel.add(txtDescripcio);

        // BOTONS
        btnNou = new JButton("NOU");
        btnNou.setBounds(30, 580, 110, 45);

        estilBoto(btnNou, new Color(0, 170, 90));

        btnModificar = new JButton("MODIFICAR");
        btnModificar.setBounds(160, 580, 140, 45);

        estilBoto(btnModificar, new Color(255, 140, 0));

        btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBounds(320, 580, 130, 45);

        estilBoto(btnEliminar, new Color(220, 53, 69));

        leftPanel.add(btnNou);
        leftPanel.add(btnModificar);
        leftPanel.add(btnEliminar);

        // =====================================
        // RIGHT PANEL
        // =====================================

        JPanel rightPanel = new JPanel(new BorderLayout());

        rightPanel.setBackground(panel);

        JLabel lblTaula = new JLabel("ENTRENAMENTS");

        lblTaula.setForeground(Color.WHITE);
        lblTaula.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTaula.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tblEntrenaments = new JTable();

        JScrollPane scroll = new JScrollPane(tblEntrenaments);

        rightPanel.add(lblTaula, BorderLayout.NORTH);
        rightPanel.add(scroll, BorderLayout.CENTER);

        // AFEGIR
        content.add(leftPanel);
        content.add(rightPanel);

        principal.add(content, BorderLayout.CENTER);

        add(principal);

        // EVENTS
        btnNou.addActionListener(e -> afegirEntrenament());

        btnModificar.addActionListener(e -> modificarEntrenament());

        btnEliminar.addActionListener(e -> eliminarEntrenament());
    }

    // =====================================
    // ESTILS
    // =====================================

    private void estilBoto(JButton boto, Color color) {

        boto.setBackground(color);
        boto.setForeground(Color.WHITE);
        boto.setFocusPainted(false);
        boto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addLabel(JPanel panel, String text, int x, int y) {

        JLabel lbl = new JLabel(text);

        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setBounds(x, y, 180, 25);

        panel.add(lbl);
    }

    private JTextField addTextField(JPanel panel, int x, int y) {

        JTextField txt = new JTextField();

        txt.setBounds(x, y, 150, 35);

        panel.add(txt);

        return txt;
    }

    // =====================================
    // ACTIVAR / DESACTIVAR
    // =====================================

    public void activarCamps() {

        txtId.setEditable(true);

        txtData.setEnabled(true);
        txtDurada.setEnabled(true);
        txtDistancia.setEnabled(true);
        txtUsuari.setEnabled(true);
        txtDescripcio.setEnabled(true);

        cbmIntensitat.setEnabled(true);
        cbmTipus.setEnabled(true);

        chkCompletat.setEnabled(true);

        btnNou.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }

    public void desactivarCamps() {

        txtId.setEditable(false);

        txtData.setEnabled(false);
        txtDurada.setEnabled(false);
        txtDistancia.setEnabled(false);
        txtUsuari.setEnabled(false);
        txtDescripcio.setEnabled(false);

        cbmIntensitat.setEnabled(false);
        cbmTipus.setEnabled(false);

        chkCompletat.setEnabled(false);

        btnNou.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    // =====================================
    // TAULA
    // =====================================

    private void afegirListenerSeleccioTaula() {

        tblEntrenaments.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tblEntrenaments.getSelectedRow();

                if (fila != -1) {

                    activarCamps();

                    txtId.setText(
                            tblEntrenaments.getValueAt(fila, 0).toString()
                    );

                    txtData.setText(
                            tblEntrenaments.getValueAt(fila, 1).toString()
                    );

                    txtDurada.setText(
                            tblEntrenaments.getValueAt(fila, 2).toString()
                    );

                    txtDistancia.setText(
                            tblEntrenaments.getValueAt(fila, 3).toString()
                    );

                    txtDescripcio.setText(
                            tblEntrenaments.getValueAt(fila, 4).toString()
                    );

                    cbmIntensitat.setSelectedItem(
                            tblEntrenaments.getValueAt(fila, 5).toString()
                    );

                    chkCompletat.setSelected(
                            Boolean.parseBoolean(
                                    tblEntrenaments.getValueAt(fila, 6)
                                            .toString()
                            )
                    );

                    txtUsuari.setText(
                            tblEntrenaments.getValueAt(fila, 7).toString()
                    );

                    cbmTipus.setSelectedItem(
                            tblEntrenaments.getValueAt(fila, 8).toString()
                    );
                }
            }
        });
    }

    // =====================================
    // COMBOBOX
    // =====================================

    private void carregarIntensitats() {

        cbmIntensitat.removeAllItems();

        cbmIntensitat.addItem(null);

        try {

            ResultSet rs = Querys.getIntensitats();

            if (rs.next()) {

                String enumValue = rs.getString(2);

                enumValue = enumValue
                        .replace("enum(", "")
                        .replace(")", "")
                        .replace("'", "");

                String[] values = enumValue.split(",");

                for (String v : values) {

                    cbmIntensitat.addItem(v.trim());

                    GestiorFitxersTXT.escripturaAFitxerLog(
                            "Carreguem intensitats"
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private void carregarTipusEsport() {

        cbmTipus.removeAllItems();

        cbmTipus.addItem(null);

        Principal.tipusesports.clear();

        try {

            ResultSet rs = Querys.getTipusEsport();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nom = rs.getString("nom");

                TipusEsport tipus = new TipusEsport(id, nom);

                cbmTipus.addItem(tipus);

                Principal.tipusesports.add(tipus);

                GestiorFitxersTXT.escripturaAFitxerLog(
                        "Carreguem tipus esport"
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =====================================
    // TAULA
    // =====================================

    public void omplirTaulaEntrenaments() {

        String[] columnes = {
                "ID",
                "DATA",
                "DURADA",
                "DISTANCIA",
                "DESCRIPCIO",
                "INTENSITAT",
                "COMPLETAT",
                "ID_USUARI",
                "TIPUS ESPORT"
        };

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(columnes);

        for (MODEL.Entrenament e : Principal.entrenaments) {

            Object[] fila = {
                    e.getId(),
                    e.getData(),
                    e.getDuradaMinuts(),
                    e.getDistancia(),
                    e.getDescripcio(),
                    e.getIntensitat(),
                    e.isCompletat(),
                    e.getUsuariId(),
                    obtenirNomTipusEsport(e.getTipusEsportId())
            };

            model.addRow(fila);
        }

        tblEntrenaments.setModel(model);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Omplint taula entrenaments"
        );
    }

    private String obtenirNomTipusEsport(int idTipus) {

        for (TipusEsport t : Principal.tipusesports) {

            if (t.getId() == idTipus) {

                return t.getNom();
            }
        }

        return "";
    }

    // =====================================
    // CRUD
    // =====================================

    private void eliminarEntrenament() {

        int id = Integer.parseInt(txtId.getText());

        Querys.eliminarEntrenament(id);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Entrenament eliminat"
        );

        netejarCamps();

        Querys.mostrarEntrenaments();

        omplirTaulaEntrenaments();

        desactivarCamps();
    }

    private void afegirEntrenament() {

        LocalDate data = LocalDate.parse(txtData.getText());

        int durada = Integer.parseInt(txtDurada.getText());

        int distancia = Integer.parseInt(txtDistancia.getText());

        String descripcio = txtDescripcio.getText();

        Intensitat intensitat = Intensitat.valueOf(
                cbmIntensitat.getSelectedItem().toString()
        );

        boolean completat = chkCompletat.isSelected();

        int usuariId = Integer.parseInt(txtUsuari.getText());

        TipusEsport tipus =
                (TipusEsport) cbmTipus.getSelectedItem();

        int tipusId = tipus.getId();

        Querys.afegirEntrenament(
                data,
                durada,
                distancia,
                descripcio,
                intensitat,
                completat,
                usuariId,
                tipusId
        );

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Entrenament afegit"
        );

        netejarCamps();

        Querys.mostrarEntrenaments();

        omplirTaulaEntrenaments();

        desactivarCamps();
    }

    private void modificarEntrenament() {

        int id = Integer.parseInt(txtId.getText());

        LocalDate data;

        try {

            data = LocalDate.parse(txtData.getText());

        } catch (Exception e) {

            data = null;
        }

        Integer durada;

        try {

            durada = Integer.parseInt(txtDurada.getText());

        } catch (Exception e) {

            durada = null;
        }

        Integer distancia;

        try {

            distancia = Integer.parseInt(txtDistancia.getText());

        } catch (Exception e) {

            distancia = null;
        }

        String descripcio = txtDescripcio.getText();

        if (descripcio.isBlank()) {

            descripcio = null;
        }

        Intensitat intensitat;

        try {

            intensitat = Intensitat.valueOf(
                    cbmIntensitat.getSelectedItem().toString()
            );

        } catch (Exception e) {

            intensitat = null;
        }

        boolean completat = chkCompletat.isSelected();

        Integer usuariId;

        try {

            usuariId = Integer.parseInt(txtUsuari.getText());

        } catch (Exception e) {

            usuariId = null;
        }

        Integer tipusid;

        try {

            TipusEsport tipus =
                    (TipusEsport) cbmTipus.getSelectedItem();

            tipusid = tipus.getId();

        } catch (Exception e) {

            tipusid = null;
        }

        Querys.actualitzarEntrenament(
                id,
                data,
                durada,
                distancia,
                descripcio,
                intensitat,
                completat,
                tipusid,
                usuariId
        );

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Entrenament modificat"
        );

        netejarCamps();

        Querys.mostrarEntrenaments();

        omplirTaulaEntrenaments();

        desactivarCamps();
    }

    // =====================================
    // ALTRES
    // =====================================

    private void netejarCamps() {

        txtId.setText("");
        txtData.setText("");
        txtDurada.setText("");
        txtDistancia.setText("");
        txtDescripcio.setText("");
        txtUsuari.setText("");

        chkCompletat.setSelected(false);

        cbmIntensitat.setSelectedIndex(0);
        cbmTipus.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new frmEntranaments().setVisible(true);
        });
    }
}