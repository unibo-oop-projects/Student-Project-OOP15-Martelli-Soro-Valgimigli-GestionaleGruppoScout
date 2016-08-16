package view.gestioneTasse.utility;

import java.awt.BorderLayout;
import java.time.Year;

import javax.swing.JDialog;

import model.Member;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class MemberTasseJDialog extends JDialog {

	private static final long serialVersionUID = -3887913606041748337L;
	private final int fontSizeLabel=19;
	
	public MemberTasseJDialog(Member mem, EditableMemberPanelImpl<Member> parent){
		super();
		MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl panBot=new MyJPanelImpl();
		panel.add(panel.createJLabel("<html><U>Pagamento Membro</U></html>", fontSizeLabel),BorderLayout.NORTH);
		panel.add(panel.createJLabel("<html>Al momento "+mem.getName()+" "+mem.getSurname()+
				" non ha pagato<br>nessuna quota di partecipazione agli Scout.<br>"
				+ "Cliccando su \"Paga\" verr� registrata<br>la quota come pagata.", fontSizeLabel),BorderLayout.CENTER);
		panBot.add(panel.createButton("Paga", u->{
			mem.setTax(Year.now().getValue());
			MyJFrameSingletonImpl.getInstance().setNeedToSave();
			parent.updateMember();;
			this.dispose();
		}));
		panBot.add(panel.createButton("Annulla", u->this.dispose()));
		panel.add(panBot,BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
	}
}
