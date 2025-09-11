package io.github.anamitraupadhyay.Quarklets.experimetal.datastructure;

public enum Jsontype {
	OBJECT(false, true),
	ARRAY(true, true),
	VALUES(true, false);

	private final boolean keycanbenull;
	private final boolean canhavechildren;

	Jsontype(boolean keycanbenull, boolean canhavechildren) {
		this.keycanbenull = keycanbenull;
		this.canhavechildren = canhavechildren;
	}

	public boolean getkeycanbenull() {
		return keycanbenull;
	}

	public boolean getcanhavechildren() {
		return canhavechildren;
	}
}
