package control;

import java.util.List;

import model.Member;

public interface SortMember extends SorterList {
	/**
	 * Sort the list according to the name
	 * @param members
	 * @return
	 */
	public List<Member> sortByName(List<Member> members);
	/**
	 * Sort the list according to the Age
	 * @param members
	 * @return
	 */
	public List<Member> sortByAge(List<Member> members);
	/**
	 * Sort the list according to the Surname
	 * @param members
	 * @return
	 */
	public List<Member> sortBySurname(List<Member> members);
	/**
	 * Sort the list according to the Numbers of Specialties
	 * @param members
	 * @return
	 */
	public List<Member> sortByNOfSpecialties(List<Member> members);
	/**
	 * Sort the list according to the Number of Competences
	 * @param members
	 * @return
	 */
	public List<Member> sortByNOfCompetences(List<Member> members);
}
