package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import model.Personne;
import model.PersonneDAO;
import view.FormPersonne;
import view.MenuPrincipal;

public class MouseListenerDisplayFormPersonne implements MouseListener {

	private MenuPrincipal menuPrincipal;
	
	public MouseListenerDisplayFormPersonne(MenuPrincipal menu){
		this.menuPrincipal = menu;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			JTable table =(JTable) e.getSource();
	        Point p = e.getPoint();
	        int row = table.rowAtPoint(p);
	        PersonneDAO pDAO = new PersonneDAO();
        	Personne pers = null;
        	try{
        		pers = pDAO.selectById(Integer.parseInt(table.getValueAt(row, 0).toString()));
        		FormPersonne formPers = new FormPersonne(this.menuPrincipal.getEntreprise(), pers);
        		this.menuPrincipal.dispose();
        	}
        	catch(Exception exception){
        		exception.printStackTrace();
        	}
        	
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
