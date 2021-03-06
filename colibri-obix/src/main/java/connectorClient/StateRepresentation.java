package connectorClient;

import model.obix.parameter.Parameter;

import javax.swing.*;

/**
 * This class is used to represent states of parameters of {@link model.obix.ObixObject} in the GUI.
 */
public class StateRepresentation {

    /******************************************************************
     *                            Variables                           *
     ******************************************************************/

    private JButton addButton;
    private JButton deleteButton;
    private JLabel stateNameLabel;
    private JTextField stateNameTextField;
    private JLabel stateValueLabel;
    private JTextField stateValueTextField;
    private JLabel stateUriLabel;
    private JTextField stateUriTextField;
    private Parameter parameter;
    private Box containerVBox;
    private JSeparator horizontalLine;

    /******************************************************************
     *                            Constructors                        *
     ******************************************************************/

    public StateRepresentation(JButton addButton, JButton deleteButton, JLabel stateNameLabel, JTextField stateNameTextField,
                               JLabel stateValueLabel, JTextField stateValueTextField,
                               JLabel stateUriLabel, JTextField stateUriTextField, JSeparator horizontalLine,
                               Box containerVBox, Parameter parameter) {
        this.addButton = addButton;
        this.deleteButton = deleteButton;
        this.stateNameLabel = stateNameLabel;
        this.stateNameTextField = stateNameTextField;
        this.stateValueLabel = stateValueLabel;
        this.stateValueTextField = stateValueTextField;
        this.stateUriLabel = stateUriLabel;
        this.stateUriTextField = stateUriTextField;
        this.horizontalLine = horizontalLine;
        this.containerVBox = containerVBox;
        this.parameter = parameter;
    }

    /******************************************************************
     *                      Getter and Setter                         *
     ******************************************************************/

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JTextField getStateNameTextField() {
        return stateNameTextField;
    }

    public void setStateNameTextField(JTextField stateNameTextField) {
        this.stateNameTextField = stateNameTextField;
    }

    public JTextField getStateValueTextField() {
        return stateValueTextField;
    }

    public void setStateValueTextField(JTextField stateValueTextField) {
        this.stateValueTextField = stateValueTextField;
    }

    public JTextField getStateUriTextField() {
        return stateUriTextField;
    }

    public void setStateUriTextField(JTextField stateUriTextField) {
        this.stateUriTextField = stateUriTextField;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public JLabel getStateNameLabel() {
        return stateNameLabel;
    }

    public void setStateNameLabel(JLabel stateNameLabel) {
        this.stateNameLabel = stateNameLabel;
    }

    public JLabel getStateValueLabel() {
        return stateValueLabel;
    }

    public void setStateValueLabel(JLabel stateValueLabel) {
        this.stateValueLabel = stateValueLabel;
    }

    public JLabel getStateUriLabel() {
        return stateUriLabel;
    }

    public void setStateUriLabel(JLabel stateUriLabel) {
        this.stateUriLabel = stateUriLabel;
    }

    public Box getContainerVBox() {
        return containerVBox;
    }

    public void setContainerVBox(Box containerVBox) {
        this.containerVBox = containerVBox;
    }

    public JSeparator getHorizontalLine() {
        return horizontalLine;
    }

    public void setHorizontalLine(JSeparator horizontalLine) {
        this.horizontalLine = horizontalLine;
    }
}
