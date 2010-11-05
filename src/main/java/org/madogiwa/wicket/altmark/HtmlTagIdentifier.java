/*
 * Copyright (c) 2010 Hidenori Sugiyama
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package org.madogiwa.wicket.altmark;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupElement;
import org.apache.wicket.markup.parser.AbstractMarkupFilter;

/**
 * @author Hidenori Sugiyama
 *
 */
public class HtmlTagIdentifier extends AbstractMarkupFilter {

	private final String prefix;

	private final Pattern pattern;

	private final boolean prefixRemoving;

	/**
	 * @param prefix
	 * @param prefixRemoving
	 */
	public HtmlTagIdentifier(final String prefix, final boolean prefixRemoving) {
		this.prefix = prefix;
		this.prefixRemoving = prefixRemoving;
		this.pattern = Pattern.compile(String.format("\\s*(%s[0-9a-zA-Z_\\-]+)", prefix));
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.parser.IMarkupFilter#nextTag()
	 */
	public MarkupElement nextTag() throws ParseException {
		ComponentTag tag = (ComponentTag)getParent().nextTag();
		if (tag == null) {
			return tag;
		}

		String id = tag.getAttributes().getString("id");
		String clazz = tag.getAttributes().getString("class");
		if (id != null && id.startsWith(prefix)) {
			tag.setId(convertToWicketId(id));
		} else if (clazz != null) {
			Matcher matcher = pattern.matcher(clazz);
			if (matcher.find()) {
				tag.setId(convertToWicketId(matcher.group(1)));
			}
		}
		return tag;
	}

	private String convertToWicketId(String id) {
		return (prefixRemoving) ? id.replaceFirst(prefix, "") : id;
	}
}
