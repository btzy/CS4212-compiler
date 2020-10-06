package util;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class LocationRange {
	public final Location left, right;

	public LocationRange(Location left, Location right) {
		this.left = left;
		this.right = right;
	}
}