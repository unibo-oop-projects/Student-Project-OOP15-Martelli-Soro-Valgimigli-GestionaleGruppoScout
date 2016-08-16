package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.exception.MemberSexException;
import control.myUtil.myOptional;
import model.Campo;
import model.CampoImpl;
import model.Capo;

import model.CapoImpl;


import model.EventiDiZona;
import model.EventiDiZonaImpl;
import model.ExcursionImpl;
import model.Gemellaggi;
import model.GemellaggiImpl;
import model.Member;
import model.MemberImpl;
import model.Reparto;
import model.RepartoImpl;
import model.Squadron;
import model.SquadronImpl;
import model.Tutor;
import model.TutorImpl;
import model.Uscita;
import model.UscitaImpl;
import model.UscitaSquadriglia;
import model.UscitaSquadrigliaImpl;
import model.exception.IllegalDateException;
import view.general_utility.WarningNotice;

public class ProjectFactoryImpl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6423409525615896639L;
	/**
	 * Returns a Instance of class Member. This method wants only basic parameters
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @return
	 */
	public static Member getSimpleMember(String nome,String cognome,LocalDate dataNascita, boolean sex){
		Member prjMember = null;
		try{
			prjMember = new MemberImpl(nome, cognome, dataNascita, sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		
		return prjMember;
	}
	/**
	 *  Returns a instance of class Member. This method can accept each possible parameter 
	 * @param name
	 * @param surname
	 * @param birthday
	 * @param sex
	 * @param nameTutor
	 * @param mailTutor
	 * @param phoneTutor
	 * @return
	 */
	public static Member getMember(String name, String surname, LocalDate birthday, boolean sex,
			myOptional<String> nameTutor, myOptional<String> mailTutor, myOptional<Long> phoneTutor){
		Member prjMember = null;
		Tutor prjTutor = null;
		try{
			prjTutor = new TutorImpl();
			if(mailTutor.isPresent()){
				prjTutor.setEmail(mailTutor.get());
			}
			if(nameTutor.isPresent()){
				prjTutor.setName(nameTutor.get());
			}
			if(phoneTutor.isPresent()){
				prjTutor.setPhone(phoneTutor.get());
			}
			prjMember = new MemberImpl(name, surname, birthday, sex, prjTutor);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return prjMember;
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param place
	 * @return
	 */
	public static ExcursionImpl getGeneralExcursion(LocalDate dateStart, myOptional<LocalDate> dateEnd,
			myOptional<String> place) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param name
	 * @param sex
	 * @return
	 */
	public static Squadron getSquadron(String name, Boolean sex) {
		Squadron sq = null;
		try{
			sq = new SquadronImpl(name, sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return sq;
	}
	/**
	 * 
	 * @param capoMaschio
	 * @param capoFemmina
	 * @param name
	 * @return
	 * @throws MemberSexException 
	 */

	public static Reparto getReparto(Capo leaderM, Capo leaderF, String name){
		try {
			return new RepartoImpl(leaderM, leaderF, new ArrayList<>(), name);
		} catch (MemberSexException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param capoMaschio
	 * @param capoFemmina
	 * @param aiutanti
	 * @param name
	 * @return
	 * @throws MemberSexException 
	 */

	public static Reparto getReparto(Capo leaderM, Capo leaderF, List<Capo> helper,String name){
		try {
			return new RepartoImpl(leaderM, leaderF, helper, name);
		} catch (MemberSexException e) {
			new WarningNotice(e.getMessage());
			return null;
		}

	}
	/**
	 * 
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param number
	 * @return
	 */
	public static Capo getLeaderM(String name, String surname, LocalDate birthDay, String number){
		try{
			return new CapoImpl(name, surname, birthDay, true, number);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return null;	
	}
	/**
	 * 
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param number
	 * @return
	 */
	public static Capo getLeaderF(String name, String surname, LocalDate birthDay, String number){
		try{
			return new CapoImpl(name, surname, birthDay, false, number);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return null;
	}
	/**
	 * 
	 * @param dateStart
	 * @param reparto
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Uscita getStdExcursion(LocalDate dateStart,Reparto reparto,String name) throws Exception {
		try {
			return new UscitaImpl(dateStart, reparto, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param sq
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static UscitaSquadriglia getSqExcursion(LocalDate dateStart,int duration,Squadron sq,String name) throws Exception{
		try {
			return new UscitaSquadrigliaImpl(dateStart, duration, sq, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param sq
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static UscitaSquadriglia getSqExcursion(LocalDate dateStart,LocalDate dateEnd,Squadron sq,String name) throws Exception{
		try {
			return new UscitaSquadrigliaImpl(dateStart, dateEnd, sq, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param rp
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Campo getCamp(LocalDate dateStart,LocalDate dateEnd,Reparto rp,String name) throws Exception{
		try {
			return new CampoImpl(dateStart, dateEnd, rp, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param rp
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Campo getCamp(LocalDate dateStart,int duration,Reparto rp,String name) throws Exception{
		try {
			return new CampoImpl(dateStart, duration, rp, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param rp
	 * @return
	 */
	public static Unit getUnit(Reparto rp){
		return new UnitImpl(rp);
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param reparto
	 * @param name
	 * @param altriReparti
	 * @return
	 */
	public static EventiDiZona getLocalEvent(LocalDate dateStart, LocalDate dateEnd, Reparto reparto, String name,
			List<String> others){
		try {
			return new EventiDiZonaImpl(dateStart, dateEnd, reparto, name, others);
		} catch (Exception e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param reparto
	 * @param name
	 * @param others
	 * @return
	 */
	public static EventiDiZona getLocalEvent(LocalDate dateStart, int duration, Reparto reparto, String name,
			List<String> others){
		try {
			return new EventiDiZonaImpl(dateStart, duration, reparto, name, others);
		} catch (Exception e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param reparto
	 * @param name
	 * @param others
	 * @return
	 */
	public static Gemellaggi getEventTwoUnit(LocalDate dateStart,LocalDate dateEnd,Reparto reparto,
			String name,List<String> others){
		try {
			return new GemellaggiImpl(dateStart, dateEnd, reparto, name, others);
		} catch (Exception e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param reparto
	 * @param name
	 * @param others
	 * @return
	 */
	public static Gemellaggi getEventMoreUnit(LocalDate dateStart,int duration,Reparto reparto,
			String name,List<String> others){
		try {
			return new GemellaggiImpl(dateStart, duration, reparto, name, others);
		} catch (Exception e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
}