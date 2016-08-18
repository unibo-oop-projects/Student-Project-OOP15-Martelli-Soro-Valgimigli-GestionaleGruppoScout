package view.gestione_tasse;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import control.myUtil.MyOptional;
import model.Member;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJPanelImpl;

public class GestioneTasseSquadrigliaImpl  {

	
	
	private final String squadName;
	public GestioneTasseSquadrigliaImpl(final String squadName){
		
		this.squadName=squadName;
	}
	
	public class GestioneTasseSquadrigliaImplPane extends MyJPanelImpl{
	
		private static final long serialVersionUID = 9026474403307334343L;
		private final MyJPanelImpl panelIscrizione;
		private final MyJPanelImpl panelEscursioni;
		private final MyJPanelImpl panelSplitted;
		private final static int FONTSIZE=19;
		private final static int FONTSIZEBIG=26;
		public GestioneTasseSquadrigliaImplPane(){
			super(new BorderLayout());
			this.panelSplitted=new MyJPanelImpl(new GridLayout(2, 1));
			this.panelIscrizione=new MyJPanelImpl(new BorderLayout());
			this.panelEscursioni=new MyJPanelImpl(new BorderLayout());
			panelIscrizione.add(new EditableMemberPanelImpl<Member>(Type.RETTASQUAD, MyOptional.of(squadName)),BorderLayout.CENTER);
			panelEscursioni.add(new EditableMemberPanelImpl<Member>(Type.TASSEEXCSQUAD, MyOptional.of(squadName)),BorderLayout.CENTER);
			panelSplitted.add(panelIscrizione);
			panelSplitted.add(panelEscursioni);
			this.add(panelSplitted,BorderLayout.CENTER);
			this.add(createJLabel("<html><U>Tasse "+squadName+"</U></html>", FONTSIZEBIG), BorderLayout.NORTH);
			panelIscrizione.add(createJLabel( "Pagamenti quota annuale", FONTSIZE), BorderLayout.NORTH);
			panelEscursioni.add(createJLabel("Pagamenti escursioni", FONTSIZE),BorderLayout.NORTH);
		}
	}
	
	public String toString(){
		return "Tasse Squadriglia";
	}
}
