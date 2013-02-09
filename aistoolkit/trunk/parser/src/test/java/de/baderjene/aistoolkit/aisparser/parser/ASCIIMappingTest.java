package de.baderjene.aistoolkit.aisparser.parser;

import org.junit.Assert;
import org.junit.Test;

import de.baderjene.aistoolkit.aisparser.AISParserException;

/**
 * Tests for {@link ASCIIMapping}.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public class ASCIIMappingTest {

	private static final int NR_OF_MAPPED_CHARS = 64;

	/**
	 * Tests whther loading a char with a negative index throws an exception.
	 * 
	 * @throws AISParserException when loading a char with a negative index
	 */
	@Test(expected = AISParserException.class)
	public void testGetChar1() throws AISParserException {
		ASCIIMapping.getChar(-1);
	}

	/**
	 * Test if loading the 20th character returns "T" (just a sample test).
	 * 
	 * @throws AISParserException should not occur
	 */
	@Test
	public void testGetChar2() throws AISParserException {
		final String expectedChar = "T";
		final String mappedChar = ASCIIMapping.getChar(20);
		Assert.assertEquals(expectedChar, mappedChar);
	}

	/**
	 * Loading an unmapped char should throw an exception.
	 * 
	 * @throws AISParserException when loading a char with a too high index
	 */
	@Test(expected = AISParserException.class)
	public void testGetChar3() throws AISParserException {
		ASCIIMapping.getChar(NR_OF_MAPPED_CHARS);
	}

	/**
	 * Tries to load all mapped chars in a loop.
	 * 
	 * @throws AISParserException when trying to load a char with a too high or
	 *             too low index
	 */
	@Test
	public void testGetChar4() throws AISParserException {
		for (int i = 0; i < NR_OF_MAPPED_CHARS; i++) {
			final String mappedChar = ASCIIMapping.getChar(i);
			Assert.assertNotNull(mappedChar);
		}
	}

}
