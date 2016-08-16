package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.Pair;
import control.myUtil.myOptional;
import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public abstract class ExcursionImpl implements Excursion, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private myOptional<Integer> prize;
	private LocalDate dateStart;
	private String name;
	private myOptional<LocalDate> dateEnd;
	private myOptional<String> place;
	private final List<Pair<Member, Boolean>> partecipanti = new ArrayList<>();

	public ExcursionImpl(final LocalDate dateStart, final String name) throws IllegalDateException {
		if (dateStart.isBefore(LocalDate.now())) {
			throw new IllegalDateException();
		}
		this.dateStart = dateStart;
		this.prize = myOptional.empty();
		this.dateEnd = myOptional.empty();
		this.place = myOptional.empty();
		this.name = name;
	}

	public ExcursionImpl(final String name, final LocalDate dateStart, final List<Member> partecipants)
			throws IllegalDateException {

		this(dateStart, name);
		partecipants.forEach(e -> {
			this.partecipanti.add(new Pair<>(e, false));
		});
	}

	protected abstract void check(LocalDate dateStart, LocalDate dateEnd) throws IllegalDateException;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public void addPartecipant(final Member partecipant, final Boolean paied)
			throws ObjectAlreadyContainedException {
		if (this.containMember(partecipant)) {
			throw new ObjectAlreadyContainedException();
		}
		this.partecipanti.add(new Pair<>(partecipant, paied));
	}

	@Override
	public void removePartecipant(final Member partecipante) throws ObjectNotContainedException {
		if (!this.containMember(partecipante)) {
			throw new ObjectNotContainedException();
		}
		if (this.isPaied(partecipante)) {
			this.partecipanti.remove(new Pair<>(partecipante, true));
		} else {
			this.partecipanti.remove(new Pair<>(partecipante, false));
		}
	}

	@Override
	public List<Member> getNotPaied() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (!e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	@Override
	public List<Member> getAllPartecipants() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			tmp.add(e.getX());
		});
		return tmp;
	}

	@Override
	public List<Member> getAllPaied() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	@Override
	public void setPaied(final Member partecipante) throws ObjectNotContainedException {
		if (!this.containMember(partecipante)) {
			throw new ObjectNotContainedException();
		}
		this.partecipanti.forEach(e -> {
			if (e.getX().equals(partecipante)) {
				e.setY(true);
			}
		});
	}

	@Override
	public boolean containMember(final Member partecipante) {
		for (final Pair<Member, Boolean> e : this.partecipanti) {
			if (e.getX().equals(partecipante)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPaied(final Member partecipante) throws ObjectNotContainedException {
		if (!this.containMember(partecipante)) {
			throw new ObjectNotContainedException();
		}
		for (final Pair<Member, Boolean> e : this.partecipanti) {
			if (e.getX().equals(partecipante)) {
				return e.getY();
			}
		}
		return false;
	}

	@Override
	public Integer getPrize() {
		return this.prize.get();
	}

	@Override
	public String getPlace() {
		return this.place.get();
	}

	@Override
	public LocalDate getDateStart() {
		return this.dateStart;
	}

	@Override
	public LocalDate getDateEnd() {
		return this.dateEnd.get();
	}

	@Override
	public void setPrice(final Integer prize) {
		if (prize.compareTo(0) < 0) {
			throw new IllegalArgumentException();
		}
		this.prize = myOptional.of(prize);
	}

	@Override
	public void setPlace(final String place) {
		this.place = myOptional.of(place);
	}

	@Override
	public void setDateStart(final LocalDate dateStart) throws IllegalDateException {
		if (!dateStart.isAfter(LocalDate.now())) {
			throw new IllegalDateException();
		}
		this.dateStart = dateStart;
	}

	@Override
	public void setDateEnd(final LocalDate dateEnd) throws IllegalDateException {
		if (!dateEnd.isAfter(LocalDate.now())) {
			throw new IllegalDateException();
		}
		if (!dateEnd.isAfter(this.dateStart)) {
			throw new IllegalDateException();
		}
		this.dateEnd = myOptional.of(dateEnd);
	}

	@Override
	public List<Member> getAllBirthdays() {
		final List<Member> tmp = new ArrayList<>();
		partecipanti.forEach(e -> {
			if (this.dateEnd.isPresent()) {
				if (e.getX().getBirthday().getDayOfYear() >= this.dateStart.getDayOfYear()
						&& e.getX().getBirthday().getDayOfYear() <= this.dateEnd.get().getDayOfYear()) {
					tmp.add(e.getX());
				}
			} else {
				if (e.getX().getBirthday().getDayOfYear() == this.dateStart.getDayOfYear()) {
					tmp.add(e.getX());
				}
			}
		});
		return tmp;
	}
}
