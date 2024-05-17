package controller;

import DAO.MesageDAO;
import DAO.UserDAO;
import event.Eventt;
import event.PublicEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ModelSendMessage;
import model.ModelUser;
import utilites.UserAction;

public class Controller implements ActionListener, Eventt {

    private JTable table;

    public Controller(JTable table) {
        this.table = table;
        setData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Delete")) {
            delete();
        }
    }

    @Override
    public void setData() {
        List<ModelUser> list = new UserDAO().getUsers();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.setRowCount(0);

        for (ModelUser user : list) {
            model.addRow(new Object[]{
                user.getUserID(),
                user.getUserName(),
                user.getGender(),
                user.getDate(),
                user.getEmail(),
                user.getPassword()
            });
        }
    }

    private void delete() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int i = table.getSelectedRow();
        if (i != -1) {
            ModelUser user = new ModelUser();
            int id = Integer.parseInt(table.getValueAt(i, 0) + "");
            user.setUserID(id);
            new UserDAO().delete(user);
            new MesageDAO().delete(id);
            ModelSendMessage message = new ModelSendMessage(user, UserAction.BAN);
            message.setUsers(new UserDAO().getUsers());
            
            setData();
            
            if(PublicEvent.getInstance().getEvent1() != null) {
                PublicEvent.getInstance().getEvent1().send(message);
            }
        }
    }

}