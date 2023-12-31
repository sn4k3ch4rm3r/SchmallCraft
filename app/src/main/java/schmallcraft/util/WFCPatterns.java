package schmallcraft.util;

/**
 * A világ generáláshoz használt minták.
 */
public final class WFCPatterns {
	public static int[][] overworld = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 8, 8, 8, 8, 6, 6,
					6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 37, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 7, 7, 7, 7, 7, 37, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 37, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6,
					6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 37, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8, 8 },
			{ 8, 8, 8, 8, 23, 23, 23, 23, 23, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8,
					8 },
			{ 8, 23, 23, 23, 23, 2, 2, 2, 2, 2, 23, 23, 23, 23, 23, 23, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6,
					8, 8 },
			{ 23, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 23, 23, 23, 23, 23, 23, 23, 23, 23, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8,
					8, 8 },
			{ 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 23, 23, 23, 23, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8,
					8 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 23, 23, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8, 8 },
			{ 2, 2, 2, 2, 2, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 23,
					23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8,
					8, 8 },
			{ 2, 2, 2, 23, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 23, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 23,
					23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6, 8, 8, 8, 8,
					8, 8 },
			{ 2, 23, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 23, 23, 23, 2, 2, 2, 2, 2, 2, 2, 2,
					2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 8, 8, 8, 8, 8,
					8, 8 },
			{ 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 23, 23, 2, 2, 2, 2, 2,
					2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 23, 2, 2, 2, 2,
					2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23,
					23 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2,
					2, 2, 2, 23, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23,
					2 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 23, 2,
					2, 2, 2, 2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2,
					2 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23,
					23, 2, 2, 2, 2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2,
					2 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23,
					23, 2, 2, 2, 2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					23, 23, 23, 2, 2, 2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2,
					2 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 23, 2, 2, 2, 2, 2, 23, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 2, 2, 2, 2, 2, 2 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2,
					23 },
			{ 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 23,
					23 },
			{ 8, 8, 8, 8, 8, 6, 6, 42, 42, 42, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 23, 23, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 23,
					8 },
			{ 8, 8, 8, 8, 8, 6, 42, 42, 42, 42, 42, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 23, 23, 23, 8, 8, 23, 23, 23, 2, 2, 2, 2, 2, 2,
					23, 23, 8 },
			{ 8, 8, 8, 8, 6, 6, 42, 42, 42, 42, 42, 42, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 6, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 23, 2, 2, 2, 2, 2, 2, 23, 23, 23, 23, 2, 2, 2, 2, 2, 2, 2, 23,
					23, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 42, 42, 42, 42, 42, 6, 6, 6, 8, 8, 8, 8, 8, 6, 6, 6, 42, 42, 42, 42, 42, 6,
					6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 23,
					8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 6, 8, 8, 8, 8, 6, 6, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 42, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					2, 23, 23, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 6, 6, 6, 6, 6, 6, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 42, 42, 42, 6, 6, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					2, 23, 23, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 42, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 41, 41, 41,
					41, 41, 41, 42, 42, 42, 42, 42, 6, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					23, 23, 23, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 41, 41, 41, 41,
					41, 41, 41, 41, 42, 42, 42, 42, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 23, 23, 23, 23, 23, 23, 23, 23,
					23, 23, 23, 23, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 41, 41, 41, 41, 41, 41,
					37, 41, 41, 41, 41, 41, 42, 42, 42, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 41, 41, 37, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 37, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 42, 42, 42, 42, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 6, 6, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 6, 6, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 6, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 41, 41, 37, 41, 41, 41,
					41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 6, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 6, 6, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41, 37, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 6, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 6, 6, 42, 42, 42, 6, 6, 6, 6, 6, 6, 6, 42, 42, 42, 42, 41, 41, 41, 41, 41, 41, 42,
					42, 42, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 6, 6, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 8, 8, 8, 8, 8, 6, 6, 6, 42, 42, 42, 41, 41, 42, 42, 42, 42, 42,
					42, 42, 42, 41, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 42, 42, 42, 42, 42, 42, 42, 6, 6, 6,
					42, 42, 42, 42, 41, 41, 41, 41, 41, 41, 42, 42, 42, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 42, 42, 42, 42, 6, 6, 6, 8, 6, 6,
					42, 42, 42, 42, 42, 41, 41, 41, 41, 42, 42, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 8, 8, 8, 8, 6, 6, 6,
					42, 42, 42, 42, 42, 42, 42, 42, 42, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6,
					6, 6, 6, 42, 42, 42, 42, 42, 42, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 6, 6, 6, 6, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 } };

	public static int[][] underworld = {
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 44, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 36, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 36, 41, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 27, 45, 45, 45, 45, 45, 44, 36, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41 },
			{ 41, 41, 41, 36, 45, 45, 41, 41, 44, 45, 45, 45, 45, 42, 42, 42, 45, 45, 41, 41, 41, 41, 41, 45, 45, 45,
					45, 41, 41, 41, 41, 41 },
			{ 41, 41, 41, 45, 42, 45, 45, 45, 45, 45, 42, 42, 42, 42, 42, 42, 42, 45, 41, 41, 41, 41, 45, 45, 42, 42,
					45, 41, 41, 41, 41, 41 },
			{ 41, 41, 41, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 36, 44, 41, 45, 45, 42, 42, 42,
					45, 45, 45, 45, 45, 41 },
			{ 41, 41, 45, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 45, 45, 45, 45, 42, 42, 42, 42,
					42, 42, 42, 42, 45, 41 },
			{ 41, 41, 45, 42, 42, 37, 42, 42, 45, 45, 45, 45, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 37, 42, 42, 45, 41 },
			{ 41, 41, 45, 42, 42, 42, 42, 42, 45, 41, 41, 41, 45, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 45, 45, 41 },
			{ 41, 41, 45, 42, 42, 42, 42, 45, 45, 41, 41, 41, 41, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 45,
					45, 45, 45, 45, 41, 41 },
			{ 41, 41, 45, 45, 42, 42, 45, 45, 36, 41, 41, 41, 41, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 45, 44,
					41, 41, 41, 41, 41, 41 },
			{ 41, 41, 36, 45, 45, 45, 45, 44, 41, 41, 41, 41, 44, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 41, 41,
					41, 41, 41, 41, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 36, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 41, 27,
					41, 45, 45, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 45, 42, 42, 42, 42, 42, 42, 42, 42, 45, 45, 41, 41,
					41, 45, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 45, 42, 42, 42, 42, 42, 42, 42, 42, 45, 36, 41, 41,
					45, 45, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 45, 45, 42, 42, 42, 42, 42, 42, 42, 42, 45, 41, 41, 41,
					45, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 36, 45, 45, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 41, 41, 41,
					45, 42, 42, 45, 41, 41 },
			{ 41, 44, 41, 41, 41, 41, 41, 41, 45, 45, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 44, 36, 41,
					45, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 45, 45, 45, 45,
					45, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 45, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 45, 44, 36 },
			{ 41, 41, 41, 41, 41, 41, 41, 45, 45, 42, 42, 42, 37, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 36, 41, 41, 41, 45, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 45, 45, 42, 42, 42, 42, 45, 45, 45, 45, 45, 45, 45, 42, 42, 42, 42, 42,
					42, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 44, 45, 45, 45, 45, 45, 45, 41, 44, 41, 41, 36, 45, 42, 42, 42, 42, 42,
					37, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 45, 45, 41, 41, 41, 41, 41, 41, 45, 45, 42, 42, 42, 42,
					42, 42, 42, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 45, 42, 42, 42, 42,
					42, 42, 45, 45, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 44, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 45, 45, 45, 42, 42,
					42, 45, 45, 41, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 27, 41, 45, 45, 45,
					45, 45, 44, 41, 41, 41 },
			{ 41, 41, 41, 41, 36, 41, 41, 41, 41, 41, 41, 41, 36, 41, 41, 41, 44, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 36, 41, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41, 41 },
			{ 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44, 41, 41, 41,
					41, 41, 41, 41, 41, 41 } };
}
