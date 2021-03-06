package com.nexteducation.dependencyTool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.CharacterIterator;
import java.text.ParseException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *
 */
public class StringUtil {

	public static String forXML(final String aText) {
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '\"') {
				result.append("&quot;");
			} else if (character == '\'') {
				result.append("&#039;");
			} else if (character == '&') {
				result.append("&amp;");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	public static String forHTML(final String aText) {
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '&') {
				result.append("&amp;");
			} else if (character == '\"') {
				result.append("&quot;");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	public static String htmlSpecialChars(final String src) {
		return htmlSpecialChars(new StringBuffer(src)).toString();
	}

	/**
	 * Convert html special charactors.
	 * 
	 * @param src
	 *            Source.
	 * @return Changed String.
	 */
	public static StringBuffer htmlSpecialChars(final StringBuffer src) {
		if (src == null) {
			return null;
		}
		final int srcLength = src.length();

		for (int i = 0; i < srcLength; i++) {
			/*
			 * if (i > 0 && src.charAt(i - 1) == ' ' && src.charAt(i) == ' ') {
			 * src.replace(i - 1, i, "&nbsp;"); src.replace(i + 5, i + 6,
			 * "&nbsp;"); srcLength += 10; i = i + 10; }
			 */
		}
		return src;
	}

	public static String getHtmlOutCode(final String str, final String defaultValue) {
		if (str == null) {
			return defaultValue;
		} else {
			return str;
		}
	}

	public static String getToString(final Object object) {
		final StringBuffer buffer = new StringBuffer("");
		final Method[] methods = object.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			try {
				final String methodName = methods[i].getName();
				if (methodName.startsWith("get") && !methodName.equals("getToString")) {
					String result = "";
					try {
						result = methods[i].invoke(object).toString();
					} catch (final Exception e) {
					}
					if (result == null) {
						result = "";
					}
					buffer.append(methodName + "=" + result + "\n");
				}

			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	public static String leftTrim(String bigString, final String ch) {
		if (bigString == null) {
			return null;
		}
		if (bigString.length() == 0) {
			return "";
		}

		while (bigString.substring(0, 1).equals(ch)) {
			bigString = bigString.substring(1, bigString.length());
		}

		return bigString.toString();
	}

	public static String trim(final String bigString, final int limit) {
		if (bigString == null) {
			return null;
		}
		final int newLimit = limit < bigString.length() ? limit : bigString.length();
		if (newLimit < limit) {
			return bigString;
		}

		final StringBuffer trimmedString = new StringBuffer(bigString.substring(0, newLimit));
		trimmedString.append("...");
		return trimmedString.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String getIds(final Vector ids) {
		if (ids.size() == 0) {
			return " ";
		}
		final StringBuffer idString = new StringBuffer();
		idString.append(ids.get(0));
		for (int i = 1; i < ids.size(); i++) {
			idString.append("," + ids.get(i));
		}
		return idString.toString();
	}

	/*
	 * public static String getIds(final List<SchoolUser> adminUsers) {
	 * if(adminUsers==null || adminUsers.size()==0) { return ""; } final
	 * StringBuffer ids = new StringBuffer(""); for (int i=0;
	 * i<adminUsers.size();i++) { final SchoolUser adminUser =
	 * adminUsers.get(i); ids.append(adminUser.getId());
	 * if(i<adminUsers.size()-1) { ids.append(", "); } } return ids.toString();
	 * }
	 * 
	 * public static String getNodeIds(final List<SyllabusNode> nodes) {
	 * if(nodes==null || nodes.size()==0) { return ""; } final StringBuffer ids
	 * = new StringBuffer(""); for (int i=0; i<nodes.size();i++) { final
	 * SyllabusNode node = nodes.get(i); ids.append(node.getId());
	 * if(i<nodes.size()-1) { ids.append(", "); } } return ids.toString(); }
	 */

	public static String getNumberTag(final int inNumber) {
		String tag = "";
		if (inNumber == 1 || inNumber > 20 && inNumber % 10 == 1) {
			tag = "st";
		} else if (inNumber == 2 || inNumber > 20 && inNumber % 10 == 2) {
			tag = "nd";
		} else if (inNumber == 3 || inNumber > 20 && inNumber % 10 == 3) {
			tag = "rd";
		} else {
			tag = "th";
		}
		return tag;
	}

	public static final String getMD5Code(final String str) {
		try {
			final MessageDigest md = MessageDigest.getInstance("MD5");
			final byte[] byt = str.getBytes();
			md.update(byt);
			final byte[] digBytes = md.digest();
			final StringBuffer hexStr = new StringBuffer();
			for (int i = 0; i < digBytes.length; i++) {
				final byte tempbyte = digBytes[i];
				String s;
				if (tempbyte >= 0 && tempbyte <= 0xF) {
					s = "0" + Integer.toHexString(0xF & tempbyte);
				} else {
					s = Integer.toHexString(0xFF & tempbyte);
				}
				hexStr.append(s);
			}
			return hexStr.toString();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String splitForHtml(final String bigString, final int limit) {
		if (bigString == null) {
			return null;
		}
		if (bigString.length() <= limit) {
			return bigString;
		}
		final int chunks = (int) Math.ceil(bigString.length() / limit);
		final StringBuffer newString = new StringBuffer("");
		for (int i = 0; i <= chunks; i++) {
			try {
				final int start = i * limit;
				int end = i * limit + limit;
				if (end > bigString.length()) {
					end = bigString.length();
				}
				newString.append(bigString.substring(start, end));
				newString.append("<br>");
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return newString.toString();
	}

	public static String splitForHtml(final String bigString, final String delimiter, final int limit) {
		if (bigString == null) {
			return null;
		}
		final StringTokenizer tokenizer = new StringTokenizer(bigString, delimiter);
		final StringBuffer newString = new StringBuffer("");
		while (tokenizer.hasMoreTokens()) {
			newString.append(splitForHtml(tokenizer.nextToken(), limit));
			newString.append("<br>");
		}
		return newString.toString();
	}

	public static String getStringFromArray(final String[] values) {
		if (values == null || values.length == 0) {
			return "";
		}
		return getStringFromArray(values, ",");
	}

	public static String getStringFromArray(final String[] values, final String delimiter) {
		if (values == null || values.length == 0) {
			return "";
		}
		final StringBuffer sb = new StringBuffer("");
		String comma = "";
		for (int i = 0; i < values.length; i++) {
			sb.append(comma);
			sb.append(values[i]);
			comma = delimiter;
		}
		return sb.toString();
	}

	public static String[] getArrayFromString(final String values) {
		if (values == null || values.trim().length() == 0) {
			return new String[0];
		}
		return getArrayFromString(values, ",");
	}

	public static String[] getArrayFromString(final String values, final String delimiter) {
		if (values == null || values.trim().length() == 0) {
			return new String[0];
		}
		final StringTokenizer tokenizer = new StringTokenizer(values, delimiter);
		final String[] stringArray = new String[tokenizer.countTokens()];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = tokenizer.nextToken();
		}
		return stringArray;
	}

	public static List<String> getListFromString(final String values, final String delimiter) {
		if (values == null || values.trim().length() == 0) {
			return new ArrayList<String>();
		}
		if (values == null || values.trim().length() == 0) {
			return new ArrayList<String>();
		}
		final StringTokenizer tokenizer = new StringTokenizer(values, delimiter);
		// String[] stringArray = new String[tokenizer.countTokens()];
		final List<String> stringList = new ArrayList<String>();
		// for (int i = 0; i < stringArray.length; i++) {
		while (tokenizer.hasMoreTokens()) {
			// stringArray[i]=tokenizer.nextToken();
			stringList.add(tokenizer.nextToken());
		}
		return stringList;
	}

	@SuppressWarnings("rawtypes")
	public static Vector getVectorFromString(final String values) {
		if (values == null || values.trim().length() == 0) {
			return new Vector();
		}
		return getVectorFromString(values, ",");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getVectorFromString(final String values, final String delimiter) {
		if (values == null || values.trim().length() == 0) {
			return new Vector();
		}
		final StringTokenizer tokenizer = new StringTokenizer(values, delimiter);
		final Vector stringVector = new Vector();
		while (tokenizer.hasMoreElements()) {
			stringVector.add(tokenizer.nextToken());
		}
		return stringVector;
	}

	public static String getNameFromEmail(final String email) {
		final StringTokenizer st = new StringTokenizer(email, ".@");
		final String[] names = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			names[i] = st.nextToken();
			i++;
		}
		final StringBuffer sb = new StringBuffer("");
		sb.append(names[0].substring(0, 1).toUpperCase());
		sb.append(names[0].substring(1, names[0].length()));
		sb.append(" ");
		sb.append(names[1].substring(0, 1).toUpperCase());
		sb.append(names[1].substring(1, names[1].length()));
		return sb.toString();
	}

	public static String trimExtraSpaces(String str) {

		if (str == null) {
			return null;
		}

		final String patternStr = "\\s+";
		final String replaceStr = " ";
		final Pattern pattern = Pattern.compile(patternStr);
		final Matcher matcher = pattern.matcher(str);
		str = matcher.replaceAll(replaceStr);
		return str;
	}

	public static String replaceCharacter(final String str) {
		if (str == null) {
			return "";
		}
		return str.replace(' ', '-').toLowerCase();
	}

	public static String replaceWord(String original, final String find, final String replacement) {
		int i = original.indexOf(find);
		if (i < 0) {
			return original;
		}
		do {
			final String partBefore = original.substring(0, i);
			final String partAfter = original.substring(i + find.length());
			original = partBefore + replacement + partAfter;
			i = original.indexOf(find);
		} while (i > 0);
		return original;
	}

	// Has a bug
	// In case the tag has some attributes and no child, it doesnt replace it
	public static String replaceTagAndBody(String original, final String find, final String replacement) {
		if (original.indexOf("<" + find + "/>") != -1) {
			original = replaceWord(original, "<" + find + "/>", "");
		}
		int i = original.indexOf("<" + find);
		if (i < 0) {
			return original;
		}
		do {
			final String partBefore = original.substring(0, i);
			final int j = original.indexOf("</" + find, i);
			final String partAfter = original.substring(j + ("</" + find + ">").length());
			original = partBefore + replacement + partAfter;
			i = original.indexOf("<" + find);
		} while (i > 0);
		return original;
	}

	public static String removeTag(String original, final String find) {
		int i = original.indexOf("<" + find);
		if (i < 0) {
			return original;
		}
		do {
			final String partBefore = original.substring(0, i);
			final int j = original.indexOf(">", i);
			int k = original.indexOf("</" + find.trim(), i);
			if (original.indexOf("/>", i) > 0 && (original.indexOf("/>", i) < k || k == -1)) {
				k = original.indexOf("/>", i);
				final String partAfter = original.substring(k + "/>".length());
				original = partBefore + partAfter;
			} else {
				final String inside = original.substring(j + 1, k);
				final String partAfter = original.substring(k + ("</" + find.trim() + ">").length());
				original = partBefore + inside + partAfter;
			}
			i = original.indexOf("<" + find);
		} while (i > 0);
		return original;
	}

	public static String removeTag(final String original, final String find, final String replacement) {
		if (original.indexOf("<" + find) < 0) {
			return original;
		}
		final String clean = removeTag(original, find);
		return replacement + clean;
	}

	public static String removeTagAttributes(String original, final String find) {
		int i = original.indexOf("<" + find);
		int lastIndex = 0;
		if (i < 0) {
			return original;
		}
		do {
			final String partBefore = original.substring(lastIndex, i);
			final int j = original.indexOf(">", i);
			final int k = original.indexOf("</" + find.trim(), i);
			final String inside = original.substring(j + 1, k);
			final String partAfter = original.substring(k + ("</" + find.trim() + ">").length());
			original = partBefore + "<" + find.trim() + ">" + inside + "</" + find.trim() + ">" + partAfter;
			lastIndex = i;
			i = original.indexOf("<" + find, i);
		} while (i > 0);
		return original;
	}

	public static String getUsefulMathText(String original) {
		if (original != null) {
			if (original.indexOf("m:annotation") > 0 || original.indexOf("m:semantics") > 0
					|| original.indexOf("span") > 0 || original.indexOf("<p ") > 0) {
				/*
				 * original = StringUtil.replaceWord(original,
				 * "<m:math xmlns:m=\"http://www.w3.org/1998/Math/MathML\" style=\"background-color:#\">"
				 * , ""); original = StringUtil.replaceWord(original,
				 * "<m:math xmlns='http://www.w3.org/1998/Math/MathML'>", "");
				 * original = StringUtil.replaceWord(original, "<m:math>", "");
				 * original = StringUtil.replaceWord(original,
				 * "<m:math style='background-color:#'>", ""); original =
				 * StringUtil.replaceWord(original, "</m:math>", "");
				 */

				original = replaceWord(original, "<m:semantics>", "");
				original = replaceWord(original, "</m:semantics>", "");
				original = replaceTagAndBody(original, "m:annotation", "");
				// original = removeTag(original, "span");
				return original;
			} else {
				return original;
			}
		}
		return null;
	}

	/*
	 * Returns 6 chars alphanumeric code (promoCode) if true else 6 chars
	 * numeric code(Activation code)
	 */
	public static String getRandomString(final boolean alphanumeric, final int maxChars) {
		final String[] codeArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		int maxIndex = 0;
		if (alphanumeric) {
			maxIndex = 35;
		} else {
			maxIndex = 9;
		}
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < maxChars; i++) {
			final Random rand = new Random();
			final int num = rand.nextInt(maxIndex);
			buffer.append(codeArray[num]);
		}

		return buffer.toString();
	}

	/**
	 * Change to sentence case - ie first character in caps, the rest in lower.
	 * 
	 * @param word
	 *            The word to be manipulated
	 * @return The altered word
	 */
	public static String toSentenceCase(String word) {

		if (word == null || word.length() == 0) {
			return "";
		}
		word = word.trim();
		if (word.length() == 1) {
			return String.valueOf(Character.toUpperCase(word.charAt(0)));
		}
		return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
	}

	/**
	 * Change to sentence case - ie first character in caps, the rest in lower.
	 * 
	 * @param word
	 *            The word to be manipulated
	 * @return The altered word
	 */
	public static String toSentenceCaseChange(final String sentence) {

		if (sentence == null || sentence.length() == 0) {
			return "";
		}
		String tempWord = "";
		String[] strArr = sentence.split(",");
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i] != "" && strArr[i].trim().length() > 0) {
				if (i == 0) {
					tempWord = Character.toUpperCase(strArr[i].charAt(0)) + strArr[i].substring(1).toLowerCase();
				} else {
					tempWord = tempWord + ", " + Character.toUpperCase(strArr[i].trim().charAt(0))
							+ strArr[i].trim().substring(1).toLowerCase();
				}

				// System.out.println(tempWord + " " + i);
			} else {
				continue;
			}
		}
		String tempWord1 = "";
		strArr = tempWord.split(" ");
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].trim().length() == 0) {
				continue;
			}
			tempWord1 = tempWord1 + " " + Character.toUpperCase(strArr[i].charAt(0))
					+ strArr[i].substring(1).toLowerCase();
			// System.out.println(tempWord1 + " " + i);
		}

		// System.out.println(tempWord1);
		return tempWord1;
	}

	public static List<Long> getNumericTokens(final String str) {
		if (str == null) {
			return null;
		}
		final StringTokenizer tokenizer = new StringTokenizer(str, " \t\r\n,;:");
		final Set<Long> idSet = new HashSet<Long>();
		long start = 0, end = 0;
		while (tokenizer.hasMoreElements()) {
			final String id = tokenizer.nextToken();
			if (id.indexOf('-') != -1) {
				final StringTokenizer tokenizer1 = new StringTokenizer(str, " \t\r\n,;:-");
				if (tokenizer1.countTokens() == 2) {
					start = Long.parseLong((String) tokenizer1.nextElement());
					end = Long.parseLong((String) tokenizer1.nextElement());
				}
				for (long i = start; i <= end; i++) {
					idSet.add(i);
				}
			} else {
				idSet.add(Long.parseLong(id));
			}
		}

		final List<Long> idList = new ArrayList<Long>();
		idList.addAll(idSet);
		return idList;

	}

	public static String formatScratchCardNumber(final String num) {
		final StringBuffer sb = new StringBuffer();
		sb.append(num.substring(0, 4));
		sb.append('-');
		sb.append(num.substring(4, 8));
		sb.append('-');
		sb.append(num.substring(8, 12));
		sb.append('-');
		sb.append(num.substring(12, 16));
		return sb.toString();
	}

	public static String formatConfirmationCode(final String confirmationCode) {
		final StringBuffer sb = new StringBuffer();
		sb.append(confirmationCode.substring(0, 5));
		sb.append('-');
		sb.append(confirmationCode.substring(5, 9));
		sb.append('-');
		sb.append(confirmationCode.substring(9, 13));
		sb.append('-');
		sb.append(confirmationCode.substring(13));
		return sb.toString();
	}

	public static List<String> tokenizeCSVtoList(final String csvString) {
		if (csvString != null && csvString.length() > 0) {
			final List<String> output = new ArrayList<String>();
			final StringTokenizer tokenizer = new StringTokenizer(csvString, ",");
			while (tokenizer.hasMoreElements()) {
				final String string = (String) tokenizer.nextElement();
				output.add(string.trim());
			}
			return output;
		}
		return null;
	}

	public static void main(final String[] args) {
		System.out.println(toSentenceCaseChange("  a    "));
		final String str = "Which of the following is an example of solid fuel<del xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xml:space=\"preserve\"/>?";
		System.out.println(str);

		final String text = replaceTagAndBody(str, "del", "");
		System.out.println(text);
	}

	public static void main1(final String[] args) throws ParseException {
		String line = "216.79.174.100******** Wed May 13 00:00:00 IST 2009 || http://www.learnnext.com/?[?] TimeTaken=157";
		final File file = new File("F:/test/urls.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			final BufferedReader d = new BufferedReader(new InputStreamReader(fis));
			new StringBuilder();
			final StringBuffer sb1 = new StringBuffer();
			while ((line = d.readLine()) != null) {
				try {
					int beginIndex = line.indexOf("Wed May 13");
					final String substr1 = line.substring(beginIndex, beginIndex + 28);
					beginIndex = line.indexOf("TimeTaken=");
					final String substr2 = line.substring(beginIndex + 10, line.length());
					beginIndex = line.indexOf("||");
					final int endIndex = line.indexOf("[");
					final String substr3 = line.substring(beginIndex + 3, endIndex);
					final long endTime = DU.parse(substr1, "EEE MMM dd HH:mm:ss z yyyy").getTime();
					final long startTime = endTime - Integer.parseInt(substr2);
					System.out.println(substr3 + "," + DU.format(new Date(startTime), "EEE MMM dd HH:mm:ss z yyyy")
							+ "," + substr1 + "," + substr2);
				} catch (final Exception e) {
					// System.out.println(e.getMessage());
					sb1.append(line);
				}
			}
			fis.close();
			// System.out.println(errors);
			// System.out.println(sb1.toString());
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}

	public static String getPlainText(final String inputText) {
		String outputText = replaceTagAndBody(inputText, "m:math", "");
		outputText = replaceTagAndBody(outputText, "math", "");
		outputText = outputText.replaceAll("\\<.*?\\>", "");
		outputText = outputText.replaceAll("\\&[a-zA-Z0-9]+\\;", " ");
		// outputText = removeTags(outputText);
		return outputText;
	}

	public static String removeTags(String str) {

		if (str == null) {
			return null;
		}

		// First Index of <
		int lessThanIndex = str.indexOf("<");
		// First Index of >
		int greaterThanIndex = str.indexOf(">");

		String tagToBeRemoved = null;

		while (lessThanIndex != -1 && greaterThanIndex != -1) {
			tagToBeRemoved = str.substring(lessThanIndex, greaterThanIndex + 1);

			if (tagToBeRemoved.indexOf(" ") == -1) {
				str = str.replaceAll(tagToBeRemoved, "");
			}

			lessThanIndex = str.indexOf("<", lessThanIndex + 1);
			greaterThanIndex = str.indexOf(">", lessThanIndex + 2);
		}

		return str;
	}

}
