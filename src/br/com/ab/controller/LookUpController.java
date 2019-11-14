/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.controller;


import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.dao.TableModelInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author RENATO
 */
public class LookUpController implements Initializable, LookUpControllerInterface {
    @FXML
    private AnchorPane apLookUp;
    @FXML
    private Button btnSelecionar;
    @FXML
    private Button btnCancelar;
    @FXML
    private BorderPane pnPesquisa;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Object> tblDados;
    
    private TableModelInterface tm;
    private Object selecionado;
    private boolean closeRequested;

    public boolean isCloseRequested() {
        return closeRequested;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    private void selecionar(ActionEvent event) {
        this.selecionado = tblDados.getSelectionModel().getSelectedItem();
        this.closeRequested = true;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        this.selecionado = null;
        this.closeRequested = true;
    }

    @FXML
    private void pesquisarClicked(ActionEvent event) {
        this.tblDados.getItems().clear();
        this.tblDados.getItems().addAll(tm.pesquisar(txtPesquisa.getText()));
    }
    
    public void configurar(TableModelInterface tm, String titulo){
        this.tm = tm;
    }
    
    @Override
    public Parent getLayout() {
        return this.apLookUp;
    }

    @Override
    public void setModel(Object model) {
        
    }
    
    @Override
    public Object getModel() {
        return selecionado;  
    }

    @Override
    public void inicializar() {
       this.closeRequested = false;
       this.btnSelecionar.setDisable(true);
       this.tblDados.getItems().clear();
       this.tblDados.getColumns().clear();
       this.tblDados.getColumns().addAll(tm.getCols());
       this.tblDados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
        if (newValue!=null){
            this.btnSelecionar.setDisable(false);
        }else {
            this.btnSelecionar.setDisable(true);
        }
    });
        
    }

    
    

    @Override
    public void setLookUp(LookUpControllerInterface lkp) {
        
    }

    @Override
    public BooleanProperty hasActiveLookup() {
        return null;
    }

    
}
