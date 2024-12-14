

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Book;
import model.BookDAO;

public class BookManagement extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	JLabel lblid,lblname,lblaname,lblprice;
	JTextField txtid,txtname,txtaname,txtprice;
	JButton btnsave,btnupdate,btndelete,btnsearch,btnclear;
	BookDAO dao;
	
	
	public BookManagement(String t) throws ClassNotFoundException, SQLException {
	Container cr=getContentPane();
	cr.setLayout(new GridLayout(7,2,5,5));
	dao=new BookDAO();
	
	lblid=new JLabel("Book ID");
	txtid=new JTextField();
	
	lblname=new JLabel("Book Name");
	txtname=new JTextField();
	
	lblaname=new JLabel("Book Author");
	txtaname=new JTextField();
	
	lblprice=new JLabel("Book Price");
	txtprice=new JTextField();
	
	btnsave=new JButton("Save");
	btnupdate=new JButton("Update");
	btndelete=new JButton("Delete");
	btnsearch=new JButton("Search");
	btnclear=new JButton("Clear");
	
	cr.add(lblid);
	cr.add(txtid);
	cr.add(lblname);
	cr.add(txtname);
	cr.add(lblaname);
	cr.add(txtaname);
	cr.add(lblprice);
	cr.add(txtprice);
	cr.add(btnsave);
	cr.add(btnupdate);
	cr.add(btndelete);
	cr.add(btnsearch);
	cr.add(btnclear);
	
	btnsave.addActionListener(this);
	btnupdate.addActionListener(this);
	btndelete.addActionListener(this);
	btnsearch.addActionListener(this);
	btnclear.addActionListener(this);
	
	setSize(400,400);
	setVisible(true);
	
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		new BookManagement("Library System");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource()==btnsave) {
				int bid=Integer.parseInt(txtid.getText());
				String nm=txtname.getText();
				String au=txtaname.getText();
				double p=Double.parseDouble(txtprice.getText());
				
				Book bobj=new Book(bid, nm, au, p);
				
				boolean r=dao.saveBook(bobj);
				if(r)
					JOptionPane.showMessageDialog(this, "Record Saved...");
				
			}
			else
				if(e.getSource()==btnupdate) {
					int bid=Integer.parseInt(txtid.getText());
					double p=Double.parseDouble(txtprice.getText());
					
					Book bobj=new Book(bid, null, null, p);
					
				boolean r=dao.updateBook(bobj);
				if(r)
					JOptionPane.showMessageDialog(this, "Records Updated..");
				else
					JOptionPane.showMessageDialog(this, "Records Not Found..");
				}
			else if(e.getSource()==btndelete) {
				int bid=Integer.parseInt(txtid.getText());
				
				boolean r=dao.deleteBook(bid);
				if(r)
					JOptionPane.showMessageDialog(this, "Record Deleted..");
				else
					JOptionPane.showMessageDialog(this, "Records Not Found..");
				
			}
			else if(e.getSource()==btnsearch) {
				int bid=Integer.parseInt(txtid.getText());
				
				ResultSet rs=dao.searchBook(bid);
				if(rs.next())
					JOptionPane.showMessageDialog(this, rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4));
				else
					JOptionPane.showMessageDialog(this, "Records Not Found..");
				
			}
			else if(e.getSource()==btnclear) {
				txtname.setText("");
				txtid.setText("");
				txtaname.setText("");
				txtprice.setText("");
				
			}
		} catch (NumberFormatException | SQLException e1) {
			
			e1.printStackTrace();
		}
	}

}
