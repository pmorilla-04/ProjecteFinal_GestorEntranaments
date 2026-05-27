package VIEW;

import CONTROLLER.GestioFitxersXML;
import CONTROLLER.GestiorFitxersTXT;
import DATA.Querys;
import MODEL.Usuari;
import MODEL.Usuari.Rol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class frmUsuari extends JFrame {

    private JTable tblUsuaris;

    private JTextField txtNom;
    private JPasswordField txtPassword;
    private JComboBox<String> cbmTipus;

    private JButton btnAfegir;
    private JButton btnModificar;
    private JButton btnEliminar;

    public frmUsuari() {

        setTitle("Panell Usuaris");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        desactivarCamps();

        Querys.mostrarUsuaris();
        omplirTaulaUsuaris();

        listennerUsuari();
    }

    private void initComponents() {

        Color bg = new Color(20, 20, 20);
        Color panel = new Color(35, 35, 35);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(bg);

        // ================= HEADER =================

        JLabel lblTitol = new JLabel("GESTIÓ D'USUARIS");
        lblTitol.setForeground(new Color(255, 140, 0));
        lblTitol.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitol.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        principal.add(lblTitol, BorderLayout.NORTH);

        // ================= CONTENT =================

        JPanel content = new JPanel(new GridLayout(1, 2, 20, 20));
        content.setBackground(bg);
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ================= LEFT PANEL =================

        JPanel left = new JPanel();
        left.setBackground(panel);
        left.setLayout(null);

        JLabel lblForm = new JLabel("USUARI");
        lblForm.setForeground(Color.WHITE);
        lblForm.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblForm.setBounds(30, 20, 200, 30);
        left.add(lblForm);

        addLabel(left, "Nom", 30, 80);
        txtNom = addTextField(left, 30, 110);

        addLabel(left, "Contrasenya", 30, 160);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(30, 190, 200, 35);
        left.add(txtPassword);

        addLabel(left, "Tipus", 30, 250);
        cbmTipus = new JComboBox<>(new String[]{
                "Esportista", "Entrenador", "Administrador"
        });
        cbmTipus.setBounds(30, 280, 200, 35);
        left.add(cbmTipus);

        btnAfegir = new JButton("AFEGIR");
        btnModificar = new JButton("MODIFICAR");
        btnEliminar = new JButton("ELIMINAR");

        estilBoto(btnAfegir, new Color(0, 200, 0));
        estilBoto(btnModificar, new Color(255, 200, 0));
        estilBoto(btnEliminar, new Color(220, 50, 50));

        btnAfegir.setBounds(30, 360, 200, 40);
        btnModificar.setBounds(30, 410, 200, 40);
        btnEliminar.setBounds(30, 460, 200, 40);

        left.add(btnAfegir);
        left.add(btnModificar);
        left.add(btnEliminar);

        // ================= RIGHT PANEL =================

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(panel);

        JLabel lblTaula = new JLabel("USUARIS");
        lblTaula.setForeground(Color.WHITE);
        lblTaula.setFont(new Font("Segoe UI", Font.BOLD, 20));

        tblUsuaris = new JTable();
        JScrollPane sp = new JScrollPane(tblUsuaris);

        right.add(lblTaula, BorderLayout.NORTH);
        right.add(sp, BorderLayout.CENTER);

        content.add(left);
        content.add(right);

        principal.add(content, BorderLayout.CENTER);

        add(principal);

        // EVENTS
        btnAfegir.addActionListener(e -> afegirUsuari());
        btnModificar.addActionListener(e -> modificarUsuari());
        btnEliminar.addActionListener(e -> eliminarUsuari());
    }

    // ================= ESTILS =================

    private void estilBoto(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addLabel(JPanel p, String text, int x, int y) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        l.setBounds(x, y, 150, 25);
        p.add(l);
    }

    private JTextField addTextField(JPanel p, int x, int y) {
        JTextField t = new JTextField();
        t.setBounds(x, y, 200, 35);
        p.add(t);
        return t;
    }

    // ================= LOGICA =================

    private void listennerUsuari() {

        tblUsuaris.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tblUsuaris.getSelectedRow();

                if (fila != -1) {

                    activarCamps();

                    txtNom.setText(tblUsuaris.getValueAt(fila, 1).toString());
                    txtPassword.setText(tblUsuaris.getValueAt(fila, 2).toString());
                    cbmTipus.setSelectedItem(tblUsuaris.getValueAt(fila, 3).toString());

                    GestiorFitxersTXT.escripturaAFitxerLog(
                            "Usuari seleccionat"
                    );
                }
            }
        });
    }

    private void activarCamps() {
        txtNom.setEnabled(true);
        txtPassword.setEnabled(true);
        cbmTipus.setEnabled(true);

        btnAfegir.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }

    private void desactivarCamps() {
        txtNom.setEnabled(false);
        txtPassword.setEnabled(false);
        cbmTipus.setEnabled(false);

        btnAfegir.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void afegirUsuari() {

        String nom = txtNom.getText();
        String pass = new String(txtPassword.getPassword());

        Rol rol = Rol.valueOf(
                cbmTipus.getSelectedItem().toString().toUpperCase()
        );

        Querys.afegirUsuari(nom, pass, rol);

        Querys.mostrarUsuaris();
        omplirTaulaUsuaris();

        netejar();
        desactivarCamps();

        GestiorFitxersTXT.escripturaAFitxerLog("Usuari afegit");
    }

    private void modificarUsuari() {

        int fila = tblUsuaris.getSelectedRow();

        if (fila == -1) return;

        int id = (int) tblUsuaris.getValueAt(fila, 0);

        String nom = txtNom.getText();
        String pass = new String(txtPassword.getPassword());

        Rol rol = Rol.valueOf(
                cbmTipus.getSelectedItem().toString().toUpperCase()
        );

        Querys.modificarUsuari(id, nom, pass, rol);

        Querys.mostrarUsuaris();
        omplirTaulaUsuaris();

        netejar();
        desactivarCamps();

        GestiorFitxersTXT.escripturaAFitxerLog("Usuari modificat");
    }

    private void eliminarUsuari() {

        int fila = tblUsuaris.getSelectedRow();

        if (fila == -1) return;

        int id = (int) tblUsuaris.getValueAt(fila, 0);

        Querys.eliminarUsuari(id);

        Querys.mostrarUsuaris();
        omplirTaulaUsuaris();

        netejar();
        desactivarCamps();

        GestiorFitxersTXT.escripturaAFitxerLog("Usuari eliminat");
    }

    private void netejar() {

        txtNom.setText("");
        txtPassword.setText("");
        cbmTipus.setSelectedIndex(0);

        tblUsuaris.clearSelection();
    }

    // ================= TAULA =================

    public void omplirTaulaUsuaris() {

        String[] cols = {"ID", "NOM", "CONTRASENYA", "ROL"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);

        for (Usuari u : CONTROLLER.Principal.usuaris) {

            Object[] fila = {
                    u.getId(),
                    u.getNom(),
                    u.getContassenya(),
                    u.getRol()
            };

            model.addRow(fila);
        }

        tblUsuaris.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frmUsuari().setVisible(true));
    }
}