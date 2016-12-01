package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import model.Equipe;
import model.EquipeDAO;
import view.FormEquipe;
import view.MenuPrincipal;

public class MouseListenerDisplayFormEquipe implements MouseListener {

	private MenuPrincipal menuPrincipal;
	
	public MouseListenerDisplayFormEquipe(MenuPrincipal menuPrincipal){
		this.menuPrincipal = menuPrincipal;
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
        	EquipeDAO eqDAO = new EquipeDAO();
        	Equipe equipe = null;
        	try {
        		equipe = eqDAO.selectById(Integer.parseInt(table.getValueAt(row, 0).toString()));
        		FormEquipe formEqu = new FormEquipe(this.menuPrincipal.getEntreprise(), equipe);
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
