package edu.txstate.CS3320.gardenhire.data.helper;

import edu.txstate.CS3320.gardenhire.data.actor.Actor;

public class ActorFactory {
	
	public ActorFactory () {

	}
	public Actor makeActor (String id, String firstName, String lastName) {
		int intID = Integer.valueOf(id);
		return new Actor (intID, firstName, lastName);
	}

	public Actor makeActor (int id, String firstName, String lastName) {
		return new Actor (id, firstName, lastName);
	}
}
