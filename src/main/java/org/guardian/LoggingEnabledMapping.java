package org.guardian;

public abstract class LoggingEnabledMapping
{
	private final boolean[] logging = new boolean[Logging.length];

	public void setLogging(Logging l, boolean enabled) {
		logging[l.ordinal()] = enabled;
	}

	public boolean isLogging(Logging l) {
		return logging[l.ordinal()];
	}
}
