package gui;

import db.Employee;
import db.EmployeeDao;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePanel extends JPanel {
    List<Employee> employees;

    EmployeeDao employeeDao = new EmployeeDao();

    List<String> columnNames;

    List<Employee> employeesSearchedByValue;

    boolean searchedByValue = false;

    public void refresh() {
        columnNames = employeeDao.getColumnNames();
        employees = employeeDao.getAll();
    }

    public void searchByValue() {
        columnNames = employeeDao.getColumnNames();
        employees = employeesSearchedByValue;
    }

    public EmployeePanel() {
        setLayout(new FlowLayout());
        EmployeeTableModel employeeTableModel = new EmployeeTableModel();
        JTable employeeTable = new JTable(employeeTableModel);
        JScrollPane jScrollPane = new JScrollPane(employeeTable);
        jScrollPane.setPreferredSize(new Dimension(jScrollPane.getPreferredSize().width, 200));
        add(jScrollPane);
        JButton[] buttons = new JButton[5];
        for (int i = 0; i < buttons.length; i++)
            add(buttons[i] = new JButton());
        buttons[0].setText("Insert");

        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame insertFrame = new JFrame("Insert");
                JLabel name = new JLabel("Name: ");
                insertFrame.add(name);
                JTextField nameField = new JTextField();
                nameField.setPreferredSize(new Dimension(100, 20));
                insertFrame.add(nameField);
                JLabel surname = new JLabel("Surname: ");
                insertFrame.add(surname);
                JTextField surnameField = new JTextField();
                surnameField.setPreferredSize(new Dimension(100, 20));
                insertFrame.add(surnameField);
                JLabel age = new JLabel("Age: ");
                insertFrame.add(age);
                JTextField ageField = new JTextField();
                ageField.setPreferredSize(new Dimension(30, 20));
                insertFrame.add(ageField);
                JLabel address = new JLabel("Address: ");
                insertFrame.add(address);
                JTextField addressField = new JTextField();
                addressField.setPreferredSize(new Dimension(150, 20));
                insertFrame.add(addressField);
                JLabel salary = new JLabel("Salary: ");
                insertFrame.add(salary);
                JTextField salaryField = new JTextField();
                salaryField.setPreferredSize(new Dimension(70, 20));
                insertFrame.add(salaryField);
                JButton insertButton = new JButton("Insert");
                insertFrame.add(insertButton);
                insertButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Employee employee = new Employee(nameField.getText(), surnameField.getText(),
                                Integer.parseInt(ageField.getText()), addressField.getText(),
                                Integer.parseInt(salaryField.getText()));

                        employeeDao.insert(employee);
                        insertFrame.dispose();

                    }
                });
                insertFrame.setLayout(new FlowLayout());
                insertFrame.setSize(800, 100);
                insertFrame.setVisible(true);
                insertFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


            }
        });

        buttons[1].setText("Update");
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame selectUpdateFrame = new JFrame("select id");
                JLabel selectUpdate = new JLabel("Select id: ");
                selectUpdateFrame.add(selectUpdate);
                JTextField selectUpdateField = new JTextField();
                selectUpdateField.setPreferredSize(new Dimension(50, 20));
                selectUpdateFrame.add(selectUpdateField);
                JButton updateOnIdButton = new JButton("Select");
                selectUpdateFrame.add(updateOnIdButton);
                selectUpdateFrame.setLayout(new FlowLayout());
                selectUpdateFrame.pack();
                selectUpdateFrame.setVisible(true);
                updateOnIdButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectUpdateFrame.dispose();
                        employees = employeeDao.getAll();
                        int foundId = 0;
                        int actualId = 0;
                        for (int i = 0; i < employees.size(); i++) {
                            if (employees.get(i).getId() == Integer.parseInt(selectUpdateField.getText())) {
                                foundId = i;
                                actualId = employees.get(i).getId();
                            }
                        }
                        JFrame updateFrame = new JFrame("Update");
                        JLabel updateName = new JLabel("Name: ");
                        updateFrame.add(updateName);
                        JTextField updateNameField = new JTextField(employees.get(foundId).getName());
                        updateNameField.setPreferredSize(new Dimension(100, 20));
                        updateFrame.add(updateNameField);
                        JLabel updateSurname = new JLabel("Surname: ");
                        updateFrame.add(updateSurname);
                        JTextField updateSurnameField = new JTextField(employees.get(foundId).getSurname());
                        updateSurnameField.setPreferredSize(new Dimension(100, 20));
                        updateFrame.add(updateSurnameField);
                        JLabel updateAge = new JLabel("Age: ");
                        updateFrame.add(updateAge);
                        JTextField updateAgeField = new JTextField(String.valueOf(employees.get(foundId).getAge()));
                        updateAgeField.setPreferredSize(new Dimension(30, 20));
                        updateFrame.add(updateAgeField);
                        JLabel updateAddress = new JLabel("Address: ");
                        updateFrame.add(updateAddress);
                        JTextField updateAddressField = new JTextField(employees.get(foundId).getAddress());
                        updateAddressField.setPreferredSize(new Dimension(150, 20));
                        updateFrame.add(updateAddressField);
                        JLabel updateSalary = new JLabel("Salary: ");
                        updateFrame.add(updateSalary);
                        JTextField updateSalaryField = new JTextField(String.valueOf(employees.get(foundId).getSalary()));
                        updateSalaryField.setPreferredSize(new Dimension(70, 20));
                        updateFrame.add(updateSalaryField);
                        JButton updateButton = new JButton("Update");
                        updateFrame.add(updateButton);
                        int finalActualId1 = actualId;
                        updateButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                Employee employee = employeeDao.getEmployee(finalActualId1);
                                employee.setName(updateNameField.getText());
                                employee.setSurname(updateSurnameField.getText());
                                employee.setAge(Integer.parseInt(updateAgeField.getText()));
                                employee.setAddress(updateAddressField.getText());
                                employee.setSalary(Integer.parseInt(updateSalaryField.getText()));

                                employeeDao.update(employee);

                                updateFrame.dispose();
                            }
                        });
                        updateFrame.setLayout(new FlowLayout());
                        updateFrame.setSize(800, 100);
                        updateFrame.setVisible(true);
                        updateFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    }
                });


            }
        });

        buttons[2].setText("Delete");
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame deleteFrame = new JFrame("Delete");
                JLabel delete = new JLabel("Select id to delete: ");
                deleteFrame.add(delete);
                JTextField deleteField = new JTextField();
                deleteField.setPreferredSize(new Dimension(50, 20));
                deleteFrame.add(deleteField);
                JButton deleteOnSelect = new JButton("Delete");
                deleteFrame.add(deleteOnSelect);
                deleteOnSelect.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int foundId = Integer.parseInt(deleteField.getText());
                        Employee employee = employeeDao.getEmployee(foundId);
                        employeeDao.delete(employee);
                        deleteFrame.dispose();
                    }
                });
                deleteFrame.setLayout(new FlowLayout());
                deleteFrame.pack();
                deleteFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                deleteFrame.setVisible(true);
            }
        });

        buttons[3].setText("Search");
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame searchFrame = new JFrame("Search");
                JLabel searchName = new JLabel("Search by name: ");
                searchFrame.add(searchName);
                JTextField searchNameField = new JTextField();
                searchNameField.setPreferredSize(new Dimension(100, 20));
                searchFrame.add(searchNameField);
                JButton searchByName = new JButton("Search");
                searchFrame.add(searchByName);
                searchFrame.setLayout(new FlowLayout());
                searchFrame.setSize(400, 200);
                searchFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                searchFrame.setVisible(true);
                searchByName.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        employeesSearchedByValue = employeeDao.searchByName(searchNameField.getText());

                            searchedByValue = true;
                            searchByValue();
                        searchFrame.dispose();
                    }
                });
                JLabel searchSurname = new JLabel("Search by surname: ");
                searchFrame.add(searchSurname);
                JTextField searchSurnameField = new JTextField();
                searchSurnameField.setPreferredSize(new Dimension(100, 20));
                searchFrame.add(searchSurnameField);
                JButton searchBySurname = new JButton("Search");
                searchFrame.add(searchBySurname);
                searchBySurname.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        employeesSearchedByValue = employeeDao.searchBySurname(searchSurnameField.getText());

                            searchedByValue = true;
                            searchByValue();

                        searchFrame.dispose();
                    }
                });
                JLabel searchAge = new JLabel("Search by age: ");
                searchFrame.add(searchAge);
                JTextField searchAgeField = new JTextField();
                searchAgeField.setPreferredSize(new Dimension(100, 20));
                searchFrame.add(searchAgeField);
                JButton searchByAge = new JButton("Search");
                searchFrame.add(searchByAge);
                searchByAge.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        employeesSearchedByValue = employeeDao.searchByAge(Integer.parseInt(searchAgeField.getText()));

                            searchedByValue = true;
                            searchByValue();

                        searchFrame.dispose();
                    }
                });
                JLabel searchAddress = new JLabel("Search by address: ");
                searchFrame.add(searchAddress);
                JTextField searchAddressField = new JTextField();
                searchAddressField.setPreferredSize(new Dimension(100, 20));
                searchFrame.add(searchAddressField);
                JButton searchByAddress = new JButton("Search");
                searchFrame.add(searchByAddress);
                searchByAddress.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        employeesSearchedByValue = employeeDao.searchByAddress(searchAddressField.getText());
                            searchedByValue = true;
                            searchByValue();

                        searchFrame.dispose();
                    }
                });
                JLabel searchSalary = new JLabel("Search by salary: ");
                searchFrame.add(searchSalary);
                JTextField searchSalaryField = new JTextField();
                searchSalaryField.setPreferredSize(new Dimension(100, 20));
                searchFrame.add(searchSalaryField);
                JButton searchBySalary = new JButton("Search");
                searchFrame.add(searchBySalary);
                searchBySalary.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        employeesSearchedByValue = employeeDao.searchBySalary(Integer.parseInt(searchSalaryField.getText()));

                            searchedByValue = true;
                            searchByValue();

                        searchFrame.dispose();
                    }
                });
            }
        });

        buttons[4].setText("Show All");
        buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchedByValue = false;
                refresh();
                employeeTable.repaint();

            }
        });

    }

    class EmployeeTableModel extends AbstractTableModel {

        EmployeeTableModel() {

            if (!searchedByValue)
                refresh();
            else
                searchByValue();
        }

        @Override
        public int getRowCount() {
            return employees.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Employee employee = employees.get(rowIndex);
            EmployeeWrapper employeeWrapper = new EmployeeWrapper(employee);
            return employeeWrapper.getColumnValue(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            return columnNames.get(column);
        }

    }

}
