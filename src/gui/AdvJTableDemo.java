package gui;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.awt.*;
import java.util.Vector;

class AdvJTableDemo extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;
    JComboBox<String> comboBox;
    TableModel dataModel;

    Container ContentPane;

    AdvJTableDemo() {
        super("Sudoku");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final String[] colHeader = { "Name", "Proficient In", "Experience",
                "Relocate" };
        final Object[][] tableData = {
                { "kranthi", "Java", new Integer(3), new Boolean(false) },
                { "Santosh", "JSP", new Integer(2), new Boolean(true) },
                { "Ram", "VB", new Integer(4), new Boolean(false) },
                { "Raju", "Java", new Integer(5), new Boolean(true) } };

        table = new JTable(tableData,colHeader);

        TableColumn proficient = table.getColumn("Proficient In");

        comboBox = new JComboBox();
        comboBox.addItem("C");
        comboBox.addItem("CPP");
        comboBox.addItem("VB");
        comboBox.addItem("Oracle");
        comboBox.addItem("Java");
        comboBox.addItem("JSP");

        proficient.setCellEditor(new DefaultCellEditor(comboBox));

        TableColumn experiance = table.getColumn("Experience");

        DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer() {
            public void setValue(Object value) {
                int cellValue = (value instanceof Number) ? ((Number) value)
                        .intValue() : 0;
                setForeground((cellValue > 3) ? Color.green : Color.black);
                setBackground((cellValue > 3) ? Color.black : Color.white);
                setText(value.toString());
            }
        };

        
        getRenderer().setHorizontalAlignment(JLabel.RIGHT);

        experiance.setCellRenderer(getRenderer());

        ContentPane = getContentPane();

        ContentPane.add(new JScrollPane(table));

        pack();
        setVisible(true);
    }

    public static DefaultTableCellRenderer getRenderer() {
        // Applying background color to cell
        return new DefaultTableCellRenderer() {
            public void setValue(Object value) {
                int cellValue = (value instanceof Number) ? ((Number) value)
                        .intValue() : 0;
                setForeground((cellValue > 3) ? Color.green : Color.black);
                setBackground((cellValue > 3) ? Color.black : Color.white);
                setText(value.toString());
            }
        };
    }
}

