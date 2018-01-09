package edu.txstate.CS3320.gardenhire.data.strategy;

import edu.txstate.CS3320.gardenhire.data.film.Film;

public abstract class SelectorStrategy  {
	public abstract boolean meetsCriteria (Film searchCandidate);
}