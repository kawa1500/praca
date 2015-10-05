package application.view;


import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
 
import java.awt.BorderLayout;
import java.awt.EventQueue;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;
 
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
 
public class Settings extends JFrame implements SerialPortEventListener {
 
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private JPanel contentPane;
        private final JPanel panel = new JPanel();
        private final JPanel panel_1 = new JPanel();
        private final JPanel panel_2 = new JPanel();
        private final JScrollPane scrollPane = new JScrollPane();
        private final JScrollPane scrollPane_1 = new JScrollPane();
        private final JTextArea textArea = new JTextArea();
        private final JTextArea textArea_1 = new JTextArea();
        private final JComboBox<String> comboBox = new JComboBox<String>();
        private final JComboBox<String> comboBox_1 = new JComboBox<String>();
        private final JButton btnNewButton = new JButton("Open");
        private final JButton btnNewButton_1 = new JButton("Send");
        private final JLabel lblNewLabel = new JLabel("port zamkni\u0119ty ...");
 
        private CommPortIdentifier portIdentifier;
        private SerialPort serialPort;
        private InputStream inputStream;
        private OutputStream outputStream;
        private String nazwaPortuCOM;
        private int baudRateCOM;
        private boolean portOFF;
       
        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                try {
                        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Throwable e) {
                        e.printStackTrace();
                }
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                	Settings frame = new Settings();
                                        frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
 
        /**
         * Create the frame.
         */
        public Settings() {
                initComponents();
                comboBox.setSelectedIndex(0);
                nazwaPortuCOM = (String) comboBox.getSelectedItem();
                comboBox_1.setSelectedIndex(5);
                baudRateCOM = Integer.parseInt((String) comboBox_1.getSelectedItem());         
        }
       
        private void initComponents() {
                //setIconImage(Toolkit.getDefaultToolkit().getImage(Settings.class.getResource("/Image/Laptop-32.png")));
                setTitle("Terminal");
                addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent arg0) {
                                do_this_windowClosing(arg0);
                        }
                });
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 500, 350);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(new BorderLayout(0, 0));
                {
                        panel.setBorder(new EmptyBorder(10, 0, 10, 0));
                        panel.setPreferredSize(new Dimension(10, 50));
                        contentPane.add(panel, BorderLayout.NORTH);
                }
                panel.setLayout(new GridLayout(1, 4, 20, 0));
                comboBox.addItemListener(new ItemListener() {
                        public void itemStateChanged(ItemEvent arg0) {
                                do_comboBox_itemStateChanged(arg0);
                        }
                });
                comboBox.setModel(new DefaultComboBoxModel<String>(ListaPortow()));
                panel.add(comboBox);
                {
                        comboBox_1.addItemListener(new ItemListener() {
                                public void itemStateChanged(ItemEvent e) {
                                        do_comboBox_1_itemStateChanged(e);
                                }
                        });
                        comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"2400", "4800", "7200", "9600", "14400", "19200", "38400", "57600", "115200", "128000"}));
                        panel.add(comboBox_1);
                }
                {
                       // btnNewButton.setIcon(new ImageIcon(Settings.class.getResource("/Image/World-32.png")));
                        btnNewButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent arg0) {
                                        do_btnNewButton_actionPerformed(arg0);
                                }
                        });
                        panel.add(btnNewButton);
                }
                {
                        btnNewButton_1.setEnabled(false);
                       // btnNewButton_1.setIcon(new ImageIcon(Settings.class.getResource("/Image/User-32.png")));
                        btnNewButton_1.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent arg0) {
                                        do_btnNewButton_1_actionPerformed(arg0);
                                }
                        });
                        panel.add(btnNewButton_1);
                }
                {
                        contentPane.add(panel_1, BorderLayout.CENTER);
                }
                panel_1.setLayout(new GridLayout(2, 1, 0, 0));
                {
                        panel_1.add(scrollPane);
                }
                {
                        textArea.setBorder(new TitledBorder(null, "odbi\u00F3r", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                        textArea.setEditable(false);
                        scrollPane.setViewportView(textArea);
                }
                {
                        panel_1.add(scrollPane_1);
                }
                {
                        textArea_1.setBorder(new TitledBorder(null, "wysy\u0142ka", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                        scrollPane_1.setViewportView(textArea_1);
                }
                {
                        panel_2.setPreferredSize(new Dimension(10, 50));
                        contentPane.add(panel_2, BorderLayout.SOUTH);
                }
                panel_2.setLayout(new BorderLayout(0, 0));
                {
                        lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
                        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        panel_2.add(lblNewLabel, BorderLayout.CENTER);
                }
        }
       
        //procedura listujaca porty szeregowe
    public String[] ListaPortow() {
        Enumeration<?> ports = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> lista = new ArrayList<String>();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                lista.add(port.getName());
            }
        }
        String portArray[];
        portArray = (String[]) lista.toArray(new String[0]);
        return portArray;
    }//koniec
       
        // procedura otwierajaca port do komunikacji
        public void OtwarciePortu(String getPort, int baudRate) {
                boolean stanportu = false;
                try {
                        portIdentifier = CommPortIdentifier.getPortIdentifier(getPort);
                } catch (NoSuchPortException e) {
                        System.out.println(e.toString());
                }
                if (portIdentifier.isCurrentlyOwned()) { // testowanie portu
                        System.out.println("... Port " + portIdentifier.getName()
                                        + " jest zajêty - nie otwieram tego portu ...");
                } else { // port wolny mozna otwierac ...
                        CommPort commPort = null;
                        try {
                                // tytul aplikacji, czas otwarcia aplikacji
                                commPort = portIdentifier.open(this.getTitle(), 2000);
                        } catch (PortInUseException e) {
                                System.out.println(" --- Niemogê otworzyæ portu "
                                                + portIdentifier.getName() + " !!!... --- ");
                                System.out.println(e.toString());
                        }
                        serialPort = (SerialPort) commPort;
                        try {
                                // inicjalizacja strumienia wejsciowego
                                inputStream = serialPort.getInputStream();
                                System.out
                                                .println("  ... inicjalizacja strumienia wejœciowego");
                                // inicjalizacja strumienia wyjsciowego
                                outputStream = serialPort.getOutputStream();
                                System.out
                                                .println("  ... inicjalizacja strumienia wyjœciowego");
                        } catch (IOException e) {
                                System.out.println(e.toString());
                                stanportu = true;
                        }
                        if (stanportu) {
                                ZamknieciePortu();
                                javax.swing.JOptionPane.showMessageDialog(
                                                new javax.swing.JFrame(), "Uwaga!... " + getPort
                                                                + " ten port jest zajêty...");
                        } else {
                                // --- w³aczenie zdarzen portu RS (true) ---
                                serialPort.notifyOnBreakInterrupt(false);
                                serialPort.notifyOnFramingError(false);
                                serialPort.notifyOnParityError(false);
                                serialPort.notifyOnOverrunError(false);
                                serialPort.notifyOnCarrierDetect(false);
                                serialPort.notifyOnRingIndicator(false);
                                serialPort.notifyOnDSR(false);
                                serialPort.notifyOnCTS(false);
                                serialPort.notifyOnOutputEmpty(false);
                                serialPort.notifyOnDataAvailable(true); // odczyt portu
                                // --- wlaczenie zdarzen portu RS ---
                                try {
                                        serialPort.setSerialPortParams(baudRate,
                                                        SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                                        SerialPort.PARITY_NONE);
                                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);// opcjonalnie
                                } catch (UnsupportedCommOperationException e) {
                                        System.out.println(e.toString());
                                }
                                try {
                                        // inicjalizacja listenera do obslugi zdarzeñ
                                        serialPort.addEventListener(this);
                                        System.out
                                                        .println("  ... inicjalizacja listenera do obslugi zdarzeñ na RS232");
                                } catch (TooManyListenersException ex) {
                                        System.out.println(ex.toString());
                                }
                                System.out.println("  ... port RS232 zosta³ otwarty ...");
                        }
                }
        }// koniec
 
        // procedura zamykajaca port
        public void ZamknieciePortu() {
                if (inputStream != null) {
                        try {
                                inputStream.close(); // zamkniêcie strumienia
                                System.out.println("  ... zamkniêcie strumienia wejsciowego");
                        } catch (IOException ex) {
                                System.out.println(ex.toString());
                        }
                }
                if (outputStream != null) {
                        try {
                                outputStream.close(); // zamkniêcie strumienia
                                System.out.println("  ... zamkniêcie strumienia wyjsciowego");
                        } catch (IOException ex) {
                                System.out.println(ex.toString());
                        }
                }
                if (serialPort != null) {
                        // usuniêcie listenera do obslugi zdarzeñ
                        serialPort.removeEventListener();
                        System.out
                                        .println("  ... usuniêcie listenera do obslugi zdarzeñ na RS232");
                        serialPort.close(); // zamkniêcie portu
                        System.out.println("  ... port RS232 zosta³ zamkniêty ...");
                }
        }// koniec
 
        // procedura wysy³aj¹ca dane na port
        public void ZapiszBuforDoPortu(String dane) {
                // .concat(Character.toString('\r')) -> doklejenie znaku '\r'
                dane = dane.concat(Character.toString('\r'));
                try {
                        // wysyla do portu dane
                        outputStream.write(dane.getBytes());
                        outputStream.flush(); // czyszczenie strumienia wysylajacego dane
                } catch (IOException ex) {
                        System.out.println(ex.toString());
                }
        }// koniec
 
        // procedura odbieraj¹ca dane z portu
        @Override
        public void serialEvent(SerialPortEvent arg0) {
                switch (arg0.getEventType()) {
                // --- odczyt zdarzeñ odczytanych z portu ---
                case SerialPortEvent.BI:
                        break;
                case SerialPortEvent.OE:
                        break;
                case SerialPortEvent.FE:
                        break;
                case SerialPortEvent.PE:
                        break;
                case SerialPortEvent.CD:
                        break;
                case SerialPortEvent.CTS:
                        break;
                case SerialPortEvent.DSR:
                        break;
                case SerialPortEvent.RI:
                        break;
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                        break;
                case SerialPortEvent.DATA_AVAILABLE:
                        try {
                                // iloœæ bajtów odczytana ze strumienia
                                int nb = inputStream.available();
                                // odczyt buforowany danych ze strunienia
                                while (nb > 0) {
                                        byte[] readBuffer = new byte[nb];
                                        inputStream.read(readBuffer);
                                        String str = new String(readBuffer);
                                        // str = str.replace("\r","\n");
                                        textArea.append(str + "\n");
                                        nb = inputStream.available();
                                }
                        } catch (IOException ex) {
                                ex.printStackTrace();
                        }
                        // Pod¹¿aanie za tekstem odczytu
                        textArea.setCaretPosition(textArea.getText().length());
                        break;
                // --- odczyt zdarzeñ odczytanych z portu ---
                }
        }// koniec     
   
        protected void do_comboBox_itemStateChanged(ItemEvent arg0) {
                nazwaPortuCOM = (String) comboBox.getSelectedItem();
        }
        protected void do_comboBox_1_itemStateChanged(ItemEvent e) {
                baudRateCOM = Integer.parseInt((String) comboBox_1.getSelectedItem());
        }
        protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
                if (portOFF) {
                        portOFF = false;
                        comboBox.setEnabled(true);
                        comboBox_1.setEnabled(true);
                        btnNewButton_1.setEnabled(false);
                        textArea.setText("");
                        textArea_1.setText("");
                        btnNewButton.setText("Open");
                        ZamknieciePortu();
                        lblNewLabel.setText("port zamkniêty ...");
                } else {
                        portOFF = true;
                        comboBox.setEnabled(false);
                        comboBox_1.setEnabled(false);
                        btnNewButton.setText("Close");
                        btnNewButton_1.setEnabled(true);
                        OtwarciePortu(nazwaPortuCOM, baudRateCOM);
                        lblNewLabel.setText("port otwarty:    " + nazwaPortuCOM + "  "
                                        + baudRateCOM + "  " + SerialPort.DATABITS_8 + "  "
                                        + SerialPort.STOPBITS_1 + "  " + SerialPort.PARITY_NONE);
                        textArea_1.grabFocus();
                }
        }
        protected void do_btnNewButton_1_actionPerformed(ActionEvent arg0) {
                ZapiszBuforDoPortu(textArea_1.getText());
                textArea_1.setText("");
                textArea_1.grabFocus();
        }
        protected void do_this_windowClosing(WindowEvent arg0) {
                ZamknieciePortu();
        }
}