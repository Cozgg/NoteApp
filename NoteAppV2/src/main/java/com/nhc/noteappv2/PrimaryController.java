package com.nhc.noteappv2;

import com.nhc.pojo.Notes;
import com.nhc.pojo.Tags;
import com.nhc.services.notes.AlertViewer;
import com.nhc.services.notes.BoldNoteDecorator;
import com.nhc.services.notes.DialogViewer;
import com.nhc.services.notes.INote;
import com.nhc.services.notes.ItalicNoteDecorator;
import com.nhc.services.notes.NoteViewer;
import com.nhc.services.notes.SimpleNote;
import com.nhc.services.notes.TextFlowViewer;
import com.nhc.utils.MyAlert;
import com.nhc.utils.MyConfigs;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {

    @FXML
    private TableView<Notes> tbNotes;
    @FXML
    private ComboBox<Tags> cbTags;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtContent;
    @FXML
    private Button addNotes;
    @FXML
    private CheckBox chkBold;
    @FXML
    private CheckBox chkItalic;
    @FXML
    private TextFlow txtShow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadColumns();
        this.loadTableData();
    }

    private void loadTableData() {
        try {
            this.tbNotes.setItems(FXCollections.observableList(MyConfigs.NoteServices.list()));
            this.cbTags.setItems(FXCollections.observableList(MyConfigs.TagServices.list()));
        } catch (SQLException e) {
            MyAlert.getInstance().showMsg("Khong the tai database", Alert.AlertType.ERROR);
        }
    }

    private void loadColumns() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(80);
        TableColumn colTitle = new TableColumn("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTitle.setPrefWidth(150);
        TableColumn colContent = new TableColumn("Content");
        colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        colContent.setPrefWidth(400);
        TableColumn colDate = new TableColumn("Date");
        colDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        colDate.setPrefWidth(100);
        TableColumn colTagId = new TableColumn("Tag");
        colTagId.setCellValueFactory(new PropertyValueFactory<>("tagName"));
        colTagId.setPrefWidth(100);

        TableColumn colAction = new TableColumn("Action");
        colAction.setCellFactory(p -> {
            TableCell cell = new TableCell();

            Button b = new Button("Xoa");

            b.setOnAction(event -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMsg("Ban muon xoa note khong?", Alert.AlertType.CONFIRMATION);

                if (t.isPresent() && t.get().equals(ButtonType.OK)) {
                    Notes n = (Notes) cell.getTableRow().getItem();

                    try {
                        if (MyConfigs.uNServices.delNote(n.getId()) == true) {
                            MyAlert.getInstance().showMsg("Xoa cau hoi thanh cong");

                            this.tbNotes.getItems().remove(n);
                        } else {
                            MyAlert.getInstance().showMsg("Xoa cau hoi that bai", Alert.AlertType.WARNING);
                        }
                    } catch (SQLException e) {
                        MyAlert.getInstance().showMsg("He thong co loi " + e.getMessage(), Alert.AlertType.WARNING);
                    }
                }

            });

            cell.setGraphic(b);
            return cell;
        });
        TableColumn<Notes, Void> colWatch = new TableColumn<>("Xem");
        colWatch.setCellFactory(p -> new TableCell<Notes, Void>() {
            private final Button watchButton = new Button("Xem");

            {
                watchButton.setOnAction(event -> {
                    Notes note = getTableView().getItems().get(getIndex());
                    if (note != null) {
                        // phuong phap cu
//                        displayFormattedContent(note);
                        NoteViewer viewer;
                        
                        switch (note.getTagId()) {
                            case 1:
                                viewer = new AlertViewer();
                                break;
                            case 3:
                                viewer = new DialogViewer();
                                break;
                            default:
                                viewer = new TextFlowViewer(txtShow);
                                break;
                        }
                        
                        viewer.display(note);
                    }
                    
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
               
                setGraphic(empty ? null : watchButton);
            }
        });

        this.tbNotes.getColumns().clear();
        this.tbNotes.getColumns().addAll(colId, colTitle, colContent, colDate, colTagId, colAction, colWatch);
    }

    public void handleAddNote(ActionEvent event) {
        String title = this.txtTitle.getText(); 
        String content = this.txtContent.getText();
        Tags selectedTag = this.cbTags.getSelectionModel().getSelectedItem();
        if (title == null || title.isEmpty() || this.txtContent.getText().isEmpty() || selectedTag == null) {
            MyAlert.getInstance().showMsg("Vui lòng nhập đầy đủ tiêu đề, nội dung và chọn tag!", Alert.AlertType.WARNING);
            return;
        }

        Notes newNote = new Notes();
        
        newNote.setTitle(title);
        newNote.setContent(content);
        newNote.setTagId(selectedTag.getId());
        newNote.setCreationDate(new java.util.Date());
        
        newNote.setBold(this.chkBold.isSelected());
        newNote.setItalic(this.chkItalic.isSelected());
        
        try {
            if(MyConfigs.uNServices.addNote(newNote)){
                MyAlert.getInstance().showMsg("Them ghi chu thanh cong", Alert.AlertType.CONFIRMATION);
                this.loadTableData();
                
                this.txtTitle.clear();
                this.txtContent.clear();
                this.cbTags.setValue(null);
                this.chkBold.setSelected(false);
                this.chkItalic.setSelected(false);
            }
            else {
                MyAlert.getInstance().showMsg("Them ghi chu that bai", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            MyAlert.getInstance().showMsg("Loi co so du lieu " + e.getMessage(), Alert.AlertType.ERROR);
        }

        
    }

//    private void displayFormattedContent(Notes n) {
//        
//        if(n == null) return;
//        
//        txtShow.getChildren().clear();
//        
//        INote noteToDisplay = new SimpleNote();
//        
//        if(n.isBold()){
//            noteToDisplay = new BoldNoteDecorator(noteToDisplay);
//        }
//        
//        if(n.isItalic()){
//            noteToDisplay = new ItalicNoteDecorator(noteToDisplay);
//        }
//        
//        String finalStyle = noteToDisplay.getStyle();
//        
//        Text textNote = new Text(n.getContent());
//        if(!finalStyle.isEmpty()){
//            textNote.setStyle(finalStyle);
//        }
//        txtShow.getChildren().add(textNote);
//       
//    }
}
