package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.exception.MemberSexException;
import control.exception.MoreLeadersNotPermitException;
import control.myUtil.MyOptional;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * Class that describes a squadriglia providing all functions.
 * 
 * @author riccardo
 *
 */

public class SquadronImpl implements Serializable, Squadron {
	/**
	 * 
	 */
	private static final long serialVersionUID = -742316483975432020L;

	private final Map<Member, Roles> map;

	private MyOptional<Member> capoSq;
	private MyOptional<Member> viceSq;
	private MyOptional<Member> triceSq;

	private String nomeSq;
	private Boolean sessoSq; // true maschi, false donne

	private Float cash;
	private MyOptional<String> noteCassa;
	private MyOptional<String> noteCancelleria;
	private MyOptional<String> noteBatteria;

	public SquadronImpl(final String nome, final Boolean sesso) {
		if (nome == null || sesso == null) {
			throw new IllegalArgumentException();
		}
		this.nomeSq = nome;
		this.sessoSq = sesso;
		map = new HashMap<>();
		this.cash = Float.valueOf(0);
		capoSq = MyOptional.empty();
		viceSq = MyOptional.empty();
		triceSq = MyOptional.empty();
		noteCassa = MyOptional.empty();
		noteCancelleria = MyOptional.empty();
		noteBatteria = MyOptional.empty();

	}

	public void setNome(final String nome) {
		this.nomeSq = nome;
	}

	/**
	 * 
	 * @return
	 */
	public String getNome() {
		return this.nomeSq;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getSesso() {
		return this.sessoSq;
	}

	/**
	 * 
	 * @param sex
	 */
	public void setSesso(final Boolean sex) {
		this.sessoSq = sex;
	}

	/**
	 * 
	 * @param capo
	 * @throws MoreLeadersNotPermitException
	 */
	public void setCapoSq(final Member capo) throws MoreLeadersNotPermitException {
		if (this.capoSq.isPresent()){
			throw new MoreLeadersNotPermitException();
		}
		this.capoSq = MyOptional.of(capo);
	}

	/**
	 * 
	 * @return
	 */
	public Member getCapo() {
		return this.capoSq.get();
	}

	/**
	 * 
	 * @param vicecapo
	 * @throws MoreLeadersNotPermitException
	 */
	public void setVicecapoSq(final Member vicecapo) throws MoreLeadersNotPermitException {
		if (this.viceSq.isPresent()){
			throw new MoreLeadersNotPermitException();
		}
		this.viceSq = MyOptional.of(vicecapo);
	}

	/**
	 * 
	 * @return
	 */
	public Member getVice() {
		return this.viceSq.get();
	}

	public boolean isCapoPresent() {
		return this.capoSq.isPresent();
	}

	public boolean isVicecapoPresent() {
		return this.viceSq.isPresent();
	}

	public boolean isTricecapoPresent() {
		return this.triceSq.isPresent();
	}

	public void setTriceSq(final Member trice) throws MoreLeadersNotPermitException {
		if (this.triceSq.isPresent()){
			throw new MoreLeadersNotPermitException();
		}
		this.triceSq = MyOptional.of(trice);
	}

	/**
	 * 
	 * @return
	 */
	public Member getTrice() {
		return this.triceSq.get();
	}

	/**
	 * 
	 * @return
	 */
	public String getNoteCassa() {
		return this.noteCassa.orElse("Non ci sono note di cassa presenti." + System.lineSeparator()
				+ "Recarsi nella sezione di gestione della squadriglia per aggiungerle");
	}

	/**
	 * 
	 * @param note
	 */
	public void setNoteCassa(final String note) {
		this.noteCassa = MyOptional.of(note);
	}

	/**
	 * 
	 * @return
	 */
	public String getNoteBatteria() {
		return this.noteBatteria.orElse("Non ci sono note di batteria presenti." + System.lineSeparator()
				+ "Recarsi nella sezione di gestione della squadriglia per aggiungerle");
	}

	/**
	 * 
	 * @param note
	 */
	public void setNoteBatteria(final String note) {
		this.noteBatteria = MyOptional.of(note);
	}

	/**
	 * 
	 * @return
	 */
	public String getNoteCancelleria() {
		return this.noteCancelleria.orElse("Non ci sono note di cancelleria presenti." + System.lineSeparator()
				+ "Recarsi nella sezione di gestione della squadriglia per aggiungerle");
	}

	/**
	 * 
	 * @param note
	 */
	public void setNoteCancelleria(final String note) {
	
		this.noteCancelleria = MyOptional.of(note);
	}

	/**
	 * 
	 * @return
	 */
	public Map<Member, Roles> getMembri() {
		return this.map;
	}

	/**
	 * 
	 * @param membro
	 * @param ruolo
	 */
	public boolean containMember(final Member membro) {
		return this.map.containsKey(membro);
	}

	public void addMembro(final Member membro, final Roles ruolo)
			throws MemberSexException, ObjectAlreadyContainedException {
		if (!membro.getSex().equals(this.sessoSq)) {
			throw new MemberSexException();
		}
		if (map.containsKey(membro)) {
			throw new ObjectAlreadyContainedException();
		}
		map.put(membro, ruolo);
	}

	public void setCash(final Float cash) {
		if (cash < 0){
			throw new IllegalArgumentException();
		}
		this.cash = cash;
	}

	public float getCash() {
		return cash;
	}

	@Override
	public void removeMembro(final Member membro) throws ObjectNotContainedException {
		if (map.containsKey(membro)){
				map.remove(membro);
		}else{
			throw new ObjectNotContainedException();
		}
	}

	@Override
	public void removeCapo() throws ObjectNotContainedException {
		if (this.isCapoPresent()) {
			this.capoSq = MyOptional.empty();
		}
		throw new ObjectNotContainedException();
	}

	@Override
	public void removeVice() throws ObjectNotContainedException {
		if (this.isVicecapoPresent()) {
			this.viceSq = MyOptional.empty();
		}
		throw new ObjectNotContainedException();
	}

	@Override
	public void removeTrice() throws ObjectNotContainedException {
		if (this.isVicecapoPresent()) {
			this.triceSq = MyOptional.empty();
		}
		throw new ObjectNotContainedException();
	}

	@Override
	public List<Member> getMemberCelebretingBirthday() {
		final List<Member> tmp = new ArrayList<>();
		this.map.keySet().forEach(e -> {
			if (e.isBirthday()){
				tmp.add(e);
			}
		});
		return tmp;
	}

}
