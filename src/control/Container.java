package control;

import java.util.List;
import java.util.function.Predicate;

import control.exception.MemberNotExistException;
import control.exception.SquadronNotExistException;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Member;
import model.Squadron;
import model.Uscita;
import model.UscitaSquadriglia;
import model.exception.ObjectNotContainedException;

/**
 * Class that contains infos, entities and relations.
 * This class implements methods to search entities
 * @author Valgio
 *
 */
public interface Container {

	/**
	 * Research of a member in the whole list. If two or more members match a special graphic
	 * interface will be called.
	 * @param Name
	 * @return
	 * List of Members named Name
	 * @throws IllegalArgumentException
	 */
	public List<Member> findMember(String name)throws IllegalArgumentException;
	 /**
	  * 
	  * @return
	  * Entire list of members
	  */
	public List<Member> getMembers();
	/**
	 * 
	 * @return
	 * 	All members which are incomplete
	 */
	public List<Member> membersIncomplete();
	/**
	 * 
	 * @param p 
	 * @return
	 * A list of member which match with the Predicate
	 */
	public List<Member> members(Predicate<? super Member> p);
	/**
	 * Research of a Squadron in the whole list
	 * @param name
	 * @return
	 * Squadron researched
	 */
	public Squadron findSquadron(String name);
	/**
	 * 
	 * @return
	 * the entire list of squadrons
	 */
	public List<Squadron> getSquadrons();
	/**
	 * 
	 * @param m
	 */
	
	public List<Member> getFreeMember();
	/**
	 * To detached member from his squadron
	 * @param member
	 * @param sq
	 * @throws SquadronNotExistException
	 * @throws MemberNotExistException
	 * @throws ObjectNotContainedException
	 */
	public void removeMeberFromSquadron(Member member, Squadron sq) 
			throws SquadronNotExistException, MemberNotExistException, ObjectNotContainedException;
	/**
	 * 
	 * @return
	 */
	public List<Excursion> getExcursion();
	/**
	 * 
	 * @param p
	 * @return
	 */
	public List<Excursion> excursions(Predicate<? super Excursion> p);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Excursion getExcursionNamed(String name);
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public Member getMember(String name, String surname);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Uscita getExit(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public UscitaSquadriglia getExcursionSq(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Gemellaggi getTwoUnitEvent(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public EventiDiZona getLocalEvent(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Campo getCamp(String name);

	

}
